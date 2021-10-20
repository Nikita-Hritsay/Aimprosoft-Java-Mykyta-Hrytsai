-- MySQL dump 10.13  Distrib 8.0.26, for Linux (x86_64)
--
-- Host: localhost    Database: employees
-- ------------------------------------------------------
-- Server version	8.0.26

/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS = @@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION = @@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE = @@TIME_ZONE */;
/*!40103 SET TIME_ZONE = '+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0 */;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `department`
(
    `iddepartment` int         NOT NULL AUTO_INCREMENT,
    `name`         varchar(45) NOT NULL,
    `address`      varchar(45) NOT NULL,
    PRIMARY KEY (`iddepartment`),
    UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 68
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department`
    DISABLE KEYS */;
INSERT INTO `department`
VALUES (1, 'first department', 'kharkiv, Sumska 22'),
       (24, 'Department Java new', 'Odessa, poltavckiy shlyah'),
       (27, 'Test ewew', 'dsa'),
       (31, 'um', 'jjj'),
       (32, 'vbvcb', 'cvbcv'),
       (34, 'tetet', 'tests'),
       (66, 'ums', 'test address'),
       (67, 'test Dep', 'dcsad');
/*!40000 ALTER TABLE `department`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee`
(
    `idemployee`              int         NOT NULL AUTO_INCREMENT,
    `firstName`               varchar(45) NOT NULL,
    `lastName`                varchar(45) NOT NULL,
    `email`                   varchar(45) NOT NULL,
    `salary`                  int         NOT NULL,
    `hireDate`                varchar(45) NOT NULL,
    `department_iddepartment` int         NOT NULL,
    PRIMARY KEY (`idemployee`, `department_iddepartment`),
    UNIQUE KEY `email` (`email`),
    KEY `fk_employee_department_idx` (`department_iddepartment`),
    CONSTRAINT `fk_employee_department` FOREIGN KEY (`department_iddepartment`) REFERENCES `department` (`iddepartment`) ON DELETE CASCADE
) ENGINE = InnoDB
  AUTO_INCREMENT = 81
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee`
    DISABLE KEYS */;
INSERT INTO `employee`
VALUES (8, 'Nikita', 'Hritsay', 'hritsaynikita8@gmail.com', 500, '2021-09-29', 1),
       (10, 'Artem', 'Simonov', 'hritsaynikita10@gmail.com', 600, '2021-09-23', 1),
       (11, 'Artem', 'second', 'hritsaynikita11@gmail.com', 700, '2021-09-11', 1),
       (25, 'Michael', 'Musienko', 'hritsaynikita25@gmail.com', 300, '2021-09-16', 1),
       (27, 'Artem', 'First', 'hritsaynikita27@gmail.com', 2000, '2021-09-09', 1),
       (28, 'Artem', 'second', 'hritsaynikita28@gmail.com', 400, '2021-09-10', 1),
       (32, 'Artem', 'First', 'hritsaynikita32@gmail.com', 2, '2021-10-08', 1),
       (51, 'Artem', 'Hritsay', 'hritsaynikita100@gmail.com123jkk', 2, '2021-10-08', 1),
       (53, 'Artem', 'First', 'hritsaynikita100@gmail.com12322', 2, '2021-10-07', 1),
       (56, 'Artem', 'First', '321321321@gmail.com', 3213, '2021-09-29', 1),
       (58, 'Kiril ', 'Kirilov', 'KirilKirilov@gmail.com', 309, '2021-09-30', 24),
       (59, 'Alexandr', 'Nikita', 'hritsaynikita100@gmail.com123222', 100, '2021-09-28', 24),
       (61, 'Test validation fistname', 'Test validation lastname', 'Testvalidationfistname@gmail.com', 500,
        '2021-09-30', 24),
       (63, 'adsca', 'sacdas', 'casdsacd@gmail.com', 2323, '2021-09-29', 27),
       (64, 'ascdas', 'Second', 'xz@gmail.com', 222, '3900-02-01', 27),
       (66, 'ceq', 'asdas', 'wqewq@asa.as', 2, '2021-09-28', 24),
       (67, 'cdascdsa', 'casdc', 'casdsacd@gmail.com23', 222, '3900-02-01', 31),
       (68, 'cdsacd', 'sacdcasd', 'hritsaynikita100@gmail.com1232222', 4, '2021-10-11', 32),
       (69, 'asd', 'scda', 'ascdacasd@dacds.cas', 222, '2021-10-11', 32),
       (70, 'cdsacd', 'cdas', 'hritsaynikita100@gm222ail.com1232222', 222, '2021-10-05', 34),
       (72, 'ascdascda', 'ssscdadc', 'hritsaynikita100@ssgmail.com12322', 3, '2021-09-30', 24),
       (73, 'Artem', 'First', 'hritsaynikita100@gmail.com123', 3, '2021-10-06', 34),
       (74, 'Artem', 'First', 'hritsaynikita100@2222gmail.com1232222', 3, '2021-10-06', 32),
       (75, 'dcas', 'cdsadc', '231321@cdsacd.codsa', 3, '2021-10-14', 34),
       (76, 'casdcas', 'dcsadcascd', 'aaaaa@gmail.com', 3, '2021-10-07', 34),
       (80, 'test', 'testsecind', 'test@gmail.cm', 2, '2021-10-06', 34);
/*!40000 ALTER TABLE `employee`
    ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE = @OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS = @OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION = @OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES = @OLD_SQL_NOTES */;

-- Dump completed on 2021-10-12 13:40:52
