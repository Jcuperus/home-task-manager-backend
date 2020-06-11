-- noinspection SqlNoDataSourceInspectionForFile

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

CREATE TABLE IF NOT EXISTS `hometaskmanager`.`user` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `password` VARCHAR(200) NULL,
  `email` VARCHAR(45) NULL,
  `role` CHAR(1) NULL,
  PRIMARY KEY (`Id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hometaskmanager`.`Group`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hometaskmanager`.`group` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hometaskmanager`.`Task`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hometaskmanager`.`task` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `description` VARCHAR(255) NULL,
  `due_date` DATETIME NULL,
  `user_id` INT UNSIGNED NOT NULL,
  `group_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_task_user_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_task_group1_idx` (`group_id` ASC) VISIBLE,
  CONSTRAINT `fk_task_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `mydb`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_task_group1`
    FOREIGN KEY (`group_id`)
    REFERENCES `mydb`.`group` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hometaskmanager`.`GroupHasUser`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hometaskmanager`.`grouphasuser` (
  `user_id` INT UNSIGNED NOT NULL,
  `group_id` INT UNSIGNED NOT NULL,
  INDEX `fk_grouphasuser_user1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_grouphasuser_group1_idx` (`group_id` ASC) VISIBLE,
  CONSTRAINT `fk_grouphasuser_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `mydb`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_grouphasuser_group1`
    FOREIGN KEY (`group_id`)
    REFERENCES `mydb`.`group` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;