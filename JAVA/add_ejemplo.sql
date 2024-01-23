-- MySQL Script generated by MySQL Workbench
-- Wed Jan 17 16:37:48 2024
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema add_ejemplo
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `add_ejemplo` ;

-- -----------------------------------------------------
-- Schema add_ejemplo
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `add_ejemplo` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `add_ejemplo` ;

-- -----------------------------------------------------
-- Table `add_ejemplo`.`departamento`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `add_ejemplo`.`departamento` ;

CREATE TABLE IF NOT EXISTS `add_ejemplo`.`departamento` (
  `dept_no` TINYINT NOT NULL,
  `dnombre` VARCHAR(15) NOT NULL,
  `loc` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`dept_no`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `add_ejemplo`.`empleado`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `add_ejemplo`.`empleado` ;

CREATE TABLE IF NOT EXISTS `add_ejemplo`.`empleado` (
  `emp_no` SMALLINT NOT NULL,
  `apellido` VARCHAR(10) NULL DEFAULT NULL,
  `oficio` VARCHAR(10) NOT NULL,
  `director` SMALLINT NOT NULL,
  `fecha_alta` DATE NULL,
  `salario` FLOAT(6,2) NOT NULL,
  `comision` FLOAT(6,2) NULL DEFAULT '0.00',
  `dept_no` TINYINT NOT NULL,
  PRIMARY KEY (`emp_no`),
  INDEX `fk_depto` (`dept_no` ASC) VISIBLE,
  INDEX `fk_dire` (`director` ASC) VISIBLE,
  CONSTRAINT `fk_depto`
    FOREIGN KEY (`dept_no`)
    REFERENCES `add_ejemplo`.`departamento` (`dept_no`),
  CONSTRAINT `fk_dire`
    FOREIGN KEY (`director`)
    REFERENCES `add_ejemplo`.`empleado` (`emp_no`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
