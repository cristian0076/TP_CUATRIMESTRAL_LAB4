USE tp_banco;

ALTER TABLE `tp_banco`.`movimientos` 
CHANGE COLUMN `Importe` `Importe` DECIMAL(10,2) NOT NULL ;


INSERT INTO `tp_banco`.`tiposdemovimientos` (`IdTipoMovimiento`, `DescripcionTipoDeMovimiento`) VALUES ('1', 'Alta de Cuenta');
INSERT INTO `tp_banco`.`tiposdemovimientos` (`IdTipoMovimiento`, `DescripcionTipoDeMovimiento`) VALUES ('2', 'Alta de Un Prestamo');
INSERT INTO `tp_banco`.`tiposdemovimientos` (`IdTipoMovimiento`, `DescripcionTipoDeMovimiento`) VALUES ('3', 'Pago de Prestamo');
INSERT INTO `tp_banco`.`tiposdemovimientos` (`IdTipoMovimiento`, `DescripcionTipoDeMovimiento`) VALUES ('4', 'Transferencia');



DROP PROCEDURE IF EXISTS prMovTransferencias;
DELIMITER //
CREATE PROCEDURE prMovTransferencias (
	IN  importe DECIMAL(10,2),
		cuentaorigen INT,
	    cuentadestino INT,
        usuarioorigen INT,
        usuariodestino INT,
        Detalle varchar(50)
)
BEGIN

SET @SaldoOrigen = ((SELECT Saldo From Cuentas WHERE NroDeCuenta= cuentaorigen) - importe);
SET @SaldoDestino = ((SELECT Saldo From Cuentas WHERE NroDeCuenta= cuentadestino) + importe);
SET @IdTipodeMovimiento = 4;

  
UPDATE `tp_banco`.`cuentas` SET `Saldo` = @SaldoOrigen WHERE (`NroDeCuenta` = cuentaorigen);
UPDATE `tp_banco`.`cuentas` SET `Saldo` = @SaldoDestino WHERE (`NroDeCuenta` = cuentadestino);
INSERT INTO `tp_banco`.`movimientos` (`IdMovimiento`,`IdUsuario`, `Fecha`, `Detalle`, `Importe`, `IdTipoMovimiento`) VALUES (null,usuarioorigen, (select NOW()), Detalle,(-1 * importe), @IdTipodeMovimiento);
INSERT INTO `tp_banco`.`movimientos` (`IdMovimiento`,`IdUsuario`, `Fecha`, `Detalle`, `Importe`, `IdTipoMovimiento`) VALUES (null,usuariodestino, (select NOW()), Detalle, (importe) , @IdTipodeMovimiento);

    
END //
DELIMITER ;
DELIMITER //




