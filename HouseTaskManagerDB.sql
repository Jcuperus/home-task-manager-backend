-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `hometaskmanager` DEFAULT CHARACTER SET utf8;

-- -----------------------------------------------------
-- Table `hometaskmanager`.`User`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `hometaskmanager`.`User` (
  `Id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NULL,
  `Password` VARCHAR(200) NULL,
  `Email` VARCHAR(45) NULL,
  `Role` CHAR(1) NULL,
  PRIMARY KEY (`Id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hometaskmanager`.`Group`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hometaskmanager`.`Group` (
  `Id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NULL,
  PRIMARY KEY (`Id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hometaskmanager`.`Task`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hometaskmanager`.`Task` (
  `Id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NULL,
  `Description` VARCHAR(255) NULL,
  `DueDate` DATETIME NULL,
  `User_Id` INT UNSIGNED NOT NULL,
  `Group_Id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`Id`, `User_Id`, `Group_Id`),
  INDEX `fk_Task_User_idx` (`User_Id` ASC) VISIBLE,
  INDEX `fk_Task_Group1_idx` (`Group_Id` ASC) VISIBLE,
  CONSTRAINT `fk_Task_User`
    FOREIGN KEY (`User_Id`)
    REFERENCES `mydb`.`User` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Task_Group1`
    FOREIGN KEY (`Group_Id`)
    REFERENCES `mydb`.`Group` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hometaskmanager`.`GroupHasUser`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hometaskmanager`.`GroupHasUser` (
  `User_Id` INT UNSIGNED NOT NULL,
  `Group_Id` INT UNSIGNED NOT NULL,
  INDEX `fk_GroupHasUser_User1_idx` (`User_Id` ASC) VISIBLE,
  INDEX `fk_GroupHasUser_Group1_idx` (`Group_Id` ASC) VISIBLE,
  CONSTRAINT `fk_GroupHasUser_User1`
    FOREIGN KEY (`User_Id`)
    REFERENCES `mydb`.`User` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_GroupHasUser_Group1`
    FOREIGN KEY (`Group_Id`)
    REFERENCES `mydb`.`Group` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;