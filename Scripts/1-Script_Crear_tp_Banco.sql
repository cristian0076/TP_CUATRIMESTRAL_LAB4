-- -----------------------------------------------------
-- Schema tp_banco
-- -----------------------------------------------------

DROP SCHEMA IF EXISTS `tp_banco`;
CREATE SCHEMA IF NOT EXISTS `tp_banco` DEFAULT CHARACTER SET latin1 ;
USE `tp_banco`;

-- -----------------------------------------------------
-- Table `tp_banco`.`tiposdecuentas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tp_banco`.`tiposdecuentas` (
  `IdTipoDeCuenta` INT NOT NULL AUTO_INCREMENT,
  `Descripcion` VARCHAR(50) NULL DEFAULT NULL,
  PRIMARY KEY (`IdTipoDeCuenta`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `tp_banco`.`generos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tp_banco`.`generos` (
  `IdGenero` INT NOT NULL AUTO_INCREMENT,
  `DescripcionGenero` VARCHAR(50) NULL DEFAULT NULL,
  PRIMARY KEY (`IdGenero`))
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `tp_banco`.`tiposdeusuarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tp_banco`.`tiposdeusuarios` (
  `IdTipoDeUsuario` INT NOT NULL AUTO_INCREMENT,
  `DescripcionTipoDeUsuario` VARCHAR(50) NULL DEFAULT NULL,
  PRIMARY KEY (`IdTipoDeUsuario`))
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `tp_banco`.`usuarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tp_banco`.`usuarios` (
 `IdUsuario` int NOT NULL AUTO_INCREMENT,
  `DNI` varchar(11) NOT NULL,
  `Cuil` varchar(50) NOT NULL,
  `Nombre` varchar(50) NOT NULL,
  `Apellido` varchar(50) NOT NULL,
  `FechaNacimiento` date DEFAULT NULL,
  `Email` varchar(50) NOT NULL,
  `NombreUsuario` varchar(50) NOT NULL,
  `Contraseña` varchar(50) NOT NULL,
  `IdGenero` int NOT NULL,
  `ESTADO` bit(1) NOT NULL,
  `IdTipoDeUsuario` int NOT NULL,
  `NumeroDeTelefono` varchar(50) DEFAULT NULL,
  `Direccion` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`IdUsuario`),
  UNIQUE KEY `NombreUsuario_UNIQUE` (`NombreUsuario`),
  UNIQUE KEY `Email_UNIQUE` (`Email`),
   CONSTRAINT `usuarios_ibfk_1` FOREIGN KEY (`IdGenero`) REFERENCES `generos` (`IdGenero`),
 CONSTRAINT `usuarios_ibfk_2` FOREIGN KEY (`IdTipoDeUsuario`) REFERENCES `tiposdeusuarios` (`IdTipoDeUsuario`)
    )
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `tp_banco`.`cuentas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tp_banco`.`cuentas` (
  `NroDeCuenta` INT NOT NULL AUTO_INCREMENT,
  `IdTipoDeCuenta` INT NOT NULL,
  `FechaCreacion` DATE NULL DEFAULT NULL,
  `CBU` VARCHAR(50) NOT NULL,
  `Saldo` DECIMAL(10,2) NOT NULL,
  `IdUsuario` INT NOT NULL,
  PRIMARY KEY (`NroDeCuenta`, `IdTipoDeCuenta`),
  CONSTRAINT `cuentas_ibfk_1`
    FOREIGN KEY (`IdTipoDeCuenta`)
    REFERENCES `tp_banco`.`tiposdecuentas` (`IdTipoDeCuenta`),
  CONSTRAINT `cuentas_ibfk_2`
    FOREIGN KEY (`IdUsuario`)
    REFERENCES `tp_banco`.`usuarios` (`IdUsuario`))
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `tp_banco`.`estadosdeprestamo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tp_banco`.`estadosdeprestamo` (
  `IdEstado` INT NOT NULL AUTO_INCREMENT,
  `Descripcion` VARCHAR(50) NULL DEFAULT NULL,
  PRIMARY KEY (`IdEstado`))
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `tp_banco`.`tiposdemovimientos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tp_banco`.`tiposdemovimientos` (
  `IdTipoMovimiento` INT NOT NULL AUTO_INCREMENT,
  `DescripcionTipoDeMovimiento` VARCHAR(50) NULL DEFAULT NULL,
  PRIMARY KEY (`IdTipoMovimiento`))
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `tp_banco`.`movimientos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tp_banco`.`movimientos` (
  `IdMovimiento` INT NOT NULL AUTO_INCREMENT,
  `IdUsuario` INT NOT NULL,
  `Fecha` DATETIME NOT NULL,
  `Detalle` VARCHAR(50) NULL DEFAULT NULL,
  `Importe` DECIMAL(10,2) NOT NULL,
  `IdTipoMovimiento` INT NOT NULL,
  `NroDeCuenta` INT NOT NULL,
  PRIMARY KEY (`IdMovimiento`),
  CONSTRAINT `movimientos_ibfk_1`
    FOREIGN KEY (`IdTipoMovimiento`)
    REFERENCES `tp_banco`.`tiposdemovimientos` (`IdTipoMovimiento`),
  CONSTRAINT `movimientos_ibfk_2`
    FOREIGN KEY (`IdUsuario`)
    REFERENCES `tp_banco`.`usuarios` (`IdUsuario`),
  CONSTRAINT `movimientos_ibfk_3`
    FOREIGN KEY (`NroDeCuenta`)
    REFERENCES `tp_banco`.`cuentas` (`NroDeCuenta`)
)
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `tp_banco`.`prestamos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tp_banco`.`prestamos` (
  `IdPrestamo` INT NOT NULL AUTO_INCREMENT,
  `Fecha` DATE NOT NULL,
  `ImporteConIntereses` DECIMAL(10,2) NOT NULL,
  `ImporteSolicitado` DECIMAL(10,2) NOT NULL,
  `PlazoDePago` INT NOT NULL,
  `ValorCuotaMensual` DECIMAL(10,2) NOT NULL,
  `IdUsuario` INT NOT NULL,
  `IdEstado` INT NOT NULL,
  `NroDeCuenta` INT NOT NULL,
  PRIMARY KEY (`IdPrestamo`),
  CONSTRAINT `prestamos_ibfk_1`
    FOREIGN KEY (`IdUsuario`)
    REFERENCES `tp_banco`.`usuarios` (`IdUsuario`),
  CONSTRAINT `prestamos_ibfk_2`
    FOREIGN KEY (`IdEstado`)
    REFERENCES `tp_banco`.`estadosdeprestamo` (`IdEstado`),
  CONSTRAINT `prestamos_ibfk_3`
    FOREIGN KEY (`NroDeCuenta`)
    REFERENCES `tp_banco`.`cuentas` (`NroDeCuenta`)
)
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `tp_banco`.`prestamoporcuota`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tp_banco`.`prestamoporcuota` (
  `IdPrestamo` INT NOT NULL,
  `NroCuota` TINYINT NOT NULL,
  `FechaPago` DATE NOT NULL,
  `Estado` BIT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`IdPrestamo`, `NroCuota`),
  CONSTRAINT `prestamoporcuota_ibfk_1`
    FOREIGN KEY (`IdPrestamo`)
    REFERENCES `tp_banco`.`prestamos` (`IdPrestamo`))
DEFAULT CHARACTER SET = latin1;

