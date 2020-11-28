USE tp_banco;

-- Insert de valores en Tipos de Cuenta
INSERT INTO `tp_banco`.`tiposdecuentas` (`IdTipoDeCuenta`, `Descripcion`) VALUES ('1', 'Caja de Ahorro');
INSERT INTO `tp_banco`.`tiposdecuentas` (`IdTipoDeCuenta`, `Descripcion`) VALUES ('2', 'Cuenta Corriente');

-- Insert de valores en Tipo de Movimientos
INSERT INTO `tp_banco`.`tiposdemovimientos` (`IdTipoMovimiento`, `DescripcionTipoDeMovimiento`) VALUES ('1', 'Alta de Cuenta');
INSERT INTO `tp_banco`.`tiposdemovimientos` (`IdTipoMovimiento`, `DescripcionTipoDeMovimiento`) VALUES ('2', 'Alta de Un Prestamo');
INSERT INTO `tp_banco`.`tiposdemovimientos` (`IdTipoMovimiento`, `DescripcionTipoDeMovimiento`) VALUES ('3', 'Pago de Prestamo');
INSERT INTO `tp_banco`.`tiposdemovimientos` (`IdTipoMovimiento`, `DescripcionTipoDeMovimiento`) VALUES ('4', 'Transferencia');

-- Insert de valores en Generos
INSERT INTO `tp_banco`.`generos` (`IdGenero`, `DescripcionGenero`) VALUES ('1', 'Masculino');
INSERT INTO `tp_banco`.`generos` (`IdGenero`, `DescripcionGenero`) VALUES ('2', 'Femenino');
INSERT INTO `tp_banco`.`generos` (`IdGenero`, `DescripcionGenero`) VALUES ('3', 'n/a');

-- Insert de valores en Estados de Prestamo
INSERT INTO `tp_banco`.`estadosdeprestamo` (`IdEstado`, `Descripcion`) VALUES ('1', 'Pendiente');
INSERT INTO `tp_banco`.`estadosdeprestamo` (`IdEstado`, `Descripcion`) VALUES ('2', 'Confirmado');
INSERT INTO `tp_banco`.`estadosdeprestamo` (`IdEstado`, `Descripcion`) VALUES ('3', 'Rechazado');


-- insert de valores en Tipo de Usuarios
INSERT INTO `tp_banco`.`tiposdeusuarios` (`IdTipoDeUsuario`, `DescripcionTipoDeUsuario`) VALUES ('1', 'Cliente');
INSERT INTO `tp_banco`.`tiposdeusuarios` (`IdTipoDeUsuario`, `DescripcionTipoDeUsuario`) VALUES ('2', 'Administrador');


-- Insert de valores en Usuarios (admin y uauario).
INSERT INTO `tp_banco`.`usuarios` (`DNI`, `Cuil`, `Nombre`, `Apellido`, `FechaNacimiento`, `Email`, `NombreUsuario`, `Contraseña`, `IdGenero`, `ESTADO`, `IdTipoDeUsuario`, `NumeroDeTelefono`, `Direccion`) VALUES ('1', '1', 'admin', 'admin', '2020-11-19', 'admin@admin.com', 'admin', 'admin','1', 1, '2', '2', 'Admin 1234');
INSERT INTO `tp_banco`.`usuarios` (`DNI`, `Cuil`, `Nombre`, `Apellido`, `FechaNacimiento`, `Email`, `NombreUsuario`, `Contraseña`, `IdGenero`, `ESTADO`, `IdTipoDeUsuario`, `NumeroDeTelefono`, `Direccion`) VALUES ('2', '2', 'usuario', 'usuario', '2020-11-19', 'usuario@usuario.com', 'usuario', 'usuario', '1', 1, '1', '2', 'Usuario 1234');



-- Creacion de Stored Procedure prMovTransferencias.

DROP PROCEDURE IF EXISTS prMovTransferencias;
DELIMITER //
CREATE PROCEDURE prMovTransferencias (
	IN  importe DECIMAL(10,2),
		cuentaorigen INT,
	    cuentadestino INT,
        usuarioorigen INT,
        usuariodestino INT,
        Detalle varchar(50),
        IdTipoMovimiento INT
)
BEGIN

SET @SaldoOrigen = ((SELECT Saldo From Cuentas WHERE NroDeCuenta= cuentaorigen) - importe);
SET @SaldoDestino = ((SELECT Saldo From Cuentas WHERE NroDeCuenta= cuentadestino) + importe);
  
UPDATE `tp_banco`.`cuentas` SET `Saldo` = @SaldoOrigen WHERE (`NroDeCuenta` = cuentaorigen);
UPDATE `tp_banco`.`cuentas` SET `Saldo` = @SaldoDestino WHERE (`NroDeCuenta` = cuentadestino);
INSERT INTO `tp_banco`.`movimientos` (`IdMovimiento`,`IdUsuario`, `Fecha`, `Detalle`, `Importe`, `IdTipoMovimiento`,`NroDeCuenta`) VALUES (null,usuarioorigen, (select NOW()), Detalle,(-1 * importe), IdTipoMovimiento, cuentaorigen);
INSERT INTO `tp_banco`.`movimientos` (`IdMovimiento`,`IdUsuario`, `Fecha`, `Detalle`, `Importe`, `IdTipoMovimiento`,`NroDeCuenta`) VALUES (null,usuariodestino, (select NOW()), Detalle, (importe) , IdTipoMovimiento, cuentadestino);
    
END //
DELIMITER ;

DELIMITER //
DROP TRIGGER `tp_banco`.trigger_CreateMovementAndRowOfFees

DELIMITER $$
CREATE TRIGGER trigger_CreateMovementAndRowOfFees
    AFTER UPDATE
    ON prestamos FOR EACH ROW 
BEGIN
  declare NroCuota INT default 0;
  declare SaldoActual DECIMAL(10,2) default 0;
 IF (NEW.IdEstado = 2) THEN
  SET NroCuota = 1;
  WHILE NroCuota <= NEW.PlazoDePago DO

	INSERT INTO `tp_banco`.`prestamoporcuota` (`IdPrestamo`, `NroCuota`,`FechaPago`, `Estado`) 
	VALUES (NEW.IdPrestamo, NroCuota, (select DATE_ADD(NOW(),INTERVAL NroCuota MONTH)), 0 );

    SET NroCuota = NroCuota + 1;
  END WHILE;
  
 INSERT INTO `tp_banco`.`movimientos` (`IdMovimiento`,`IdUsuario`, `Fecha`, `Detalle`, `Importe`, `IdTipoMovimiento`,`NroDeCuenta`) 
 VALUES (null,NEW.IdUsuario, (select NOW()), 'Alta de prestamo', NEW.ImporteSolicitado , '2', NEW.NroDeCuenta);

SET SaldoActual = (Select Saldo from cuentas where NroDeCuenta = NEW.NroDeCuenta);
UPDATE `tp_banco`.`cuentas` SET `Saldo` =  SaldoActual+NEW.importeSolicitado WHERE (`NroDeCuenta` = NEW.NroDeCuenta);
   END IF;
  
END$$    
DELIMITER ;



-- Creacion de Stored Procedure SP_PagoCuota.

DROP PROCEDURE IF EXISTS SP_PagoCuota;
DELIMITER //
CREATE PROCEDURE SP_PagoCuota (
	IN  
		cuenta INT,
	    IdPrestamo INT,
        NroDeCuota INT,
        Importe decimal(10,2)
)
BEGIN

SET @Saldo = ((SELECT Saldo From Cuentas WHERE NroDeCuenta= cuenta) - Importe);
SET @Usuario = (SELECT distinct IdUsuario From Cuentas WHERE NroDeCuenta = cuenta);

  
UPDATE `tp_banco`.`cuentas` SET `Saldo` = @Saldo WHERE (`NroDeCuenta` = cuenta);
INSERT INTO `tp_banco`.`movimientos` (`IdUsuario`, `Fecha`, `Detalle`, `Importe`, `IdTipoMovimiento`,`NroDeCuenta`) VALUES (@Usuario, (select NOW()),'Pago de cuota',Importe,3,cuenta);
UPDATE `tp_banco`.`prestamoporcuota` SET `Estado` = 1 WHERE (`IdPrestamo` = IdPrestamo) and (`NroCuota` = NroDeCuota);
    
END //
DELIMITER ;

















