SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=1;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=1;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `school_student_db` DEFAULT CHARACTER SET utf8 ;
USE `school_student_db` ;


CREATE TABLE IF NOT EXISTS `classes` (
`id` BIGINT NOT NULL AUTO_INCREMENT,
  `number` BIGINT NOT NULL,
  `category` VARCHAR(1) NOT NULL,
  PRIMARY KEY (`id`)
)
ENGINE = InnoDB
AUTO_INCREMENT = 1002;

CREATE TABLE IF NOT EXISTS `school_students` (
`id` BIGINT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(100) NOT NULL,
  `last_name` VARCHAR(100) NOT NULL,
  `fatherland` VARCHAR(100) NOT NULL,
  `email` VARCHAR(100)  NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `classes_id` BIGINT NOT NULL,
  ADD CONSTRAINT UK_fatherland UNIQUE (fatherland),
   ADD CONSTRAINT UK_email UNIQUE (email),
   ADD CONSTRAINT UK_password UNIQUE (password),
  PRIMARY KEY (`id`)
)
ENGINE = InnoDB
AUTO_INCREMENT = 1002;
ALTER TABLE `school_students`
ADD FOREIGN KEY (`classes_id`) REFERENCES `classes`(`id`);

 CREATE TABLE IF NOT EXISTS `specific_code_for_registration_in_school` (
`id` BIGINT NOT NULL AUTO_INCREMENT,
`specific_code_for_registration_for_student` VARCHAR(200) NOT NULL,
 `specific_code_for_registration_for_teacher` VARCHAR(200) NOT NULL,
  ADD CONSTRAINT UK_specific_code_for_registration_for_student UNIQUE (specific_code_for_registration_for_student),
   ADD CONSTRAINT UK_specific_code_for_registration_for_teacher UNIQUE (specific_code_for_registration_for_teacher),
  PRIMARY KEY (`id`)
 )
ENGINE = InnoDB
AUTO_INCREMENT = 1002;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
