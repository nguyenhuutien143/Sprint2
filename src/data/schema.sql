CREATE DATABASE  IF NOT EXISTS `eviedence_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `eviedence_db`;
-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: eviedence_db
-- ------------------------------------------------------
-- Server version	8.0.29

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `criminal_case`
--

DROP TABLE IF EXISTS `criminal_case`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `criminal_case` (
                                 `id` int NOT NULL,
                                 `version` int DEFAULT NULL,
                                 `number` varchar(45) DEFAULT NULL,
                                 `short_description` varchar(45) DEFAULT NULL,
                                 `detailed_description` varchar(45) DEFAULT NULL,
                                 `createdAt` datetime DEFAULT NULL,
                                 `modifiedAt` datetime DEFAULT NULL,
                                 `type` enum('UNCATEGORIZED','INFRACTION','MISDEMEANOR','FELONY') DEFAULT NULL,
                                 `notes` varchar(45) DEFAULT NULL,
                                 `status` enum('SUBMITTED','UNDER_INVESTIGATION','IN_COURT','CLOSED','DISMISSED','COLD') DEFAULT NULL,
                                 `leadInvestigator` int DEFAULT NULL,
                                 PRIMARY KEY (`id`),
                                 KEY `detectiveId_idx` (`leadInvestigator`),
                                 CONSTRAINT `detectiveId` FOREIGN KEY (`leadInvestigator`) REFERENCES `detective` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `detective`
--

DROP TABLE IF EXISTS `detective`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detective` (
                             `id` int NOT NULL,
                             `version` int DEFAULT NULL,
                             `createdAt` varchar(45) DEFAULT NULL,
                             `modifiedAt` varchar(45) DEFAULT NULL,
                             `username` varchar(45) DEFAULT NULL,
                             `firstName` varchar(45) DEFAULT NULL,
                             `lastName` varchar(45) DEFAULT NULL,
                             `password` varchar(45) DEFAULT NULL,
                             `hiringDate` datetime DEFAULT NULL,
                             `badgeNumber` varchar(45) DEFAULT NULL,
                             `rank` enum('ACTIVE','SUSPENDED','VACATION','UNDER_INVESTIGATION','RETIRED') DEFAULT NULL,
                             `armed` tinyint DEFAULT NULL,
                             `status` enum('ACTIVE','SUSPENDED','VACATION','UNDER_INVESTIGATION','RETIRED') DEFAULT NULL,
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `evidence`
--

DROP TABLE IF EXISTS `evidence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `evidence` (
                            `id` int NOT NULL,
                            `version` int DEFAULT NULL,
                            `createdAt` varchar(45) DEFAULT NULL,
                            `modifiedAt` varchar(45) DEFAULT NULL,
                            `criminalCaseId` int DEFAULT NULL,
                            `strorageId` int DEFAULT NULL,
                            `number` varchar(45) DEFAULT NULL,
                            `itemName` varchar(45) DEFAULT NULL,
                            `notes` varchar(45) DEFAULT NULL,
                            `archived` tinyint DEFAULT NULL,
                            PRIMARY KEY (`id`),
                            CONSTRAINT `case_fk` FOREIGN KEY (`id`) REFERENCES `criminal_case` (`id`),
                            CONSTRAINT `storage_fk` FOREIGN KEY (`id`) REFERENCES `storage` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `storage`
--

DROP TABLE IF EXISTS `storage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `storage` (
                           `id` int NOT NULL,
                           `version` int DEFAULT NULL,
                           `createdAt` datetime DEFAULT NULL,
                           `modifiedAt` datetime DEFAULT NULL,
                           `name` varchar(45) DEFAULT NULL,
                           `location` varchar(45) DEFAULT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='		';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `trackentry`
--

DROP TABLE IF EXISTS `trackentry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trackentry` (
                              `id` int NOT NULL,
                              `version` int DEFAULT NULL,
                              `createdAt` datetime DEFAULT NULL,
                              `modifiedAt` datetime DEFAULT NULL,
                              `evidenceId` int DEFAULT NULL,
                              `detectiveId` int DEFAULT NULL,
                              `action` enum('SUBMITTED','RETRIEVED','RETURNED') DEFAULT NULL,
                              `reason` varchar(45) DEFAULT NULL,
                              PRIMARY KEY (`id`),
                              CONSTRAINT `detective_fk` FOREIGN KEY (`id`) REFERENCES `detective` (`id`),
                              CONSTRAINT `evidence_fk` FOREIGN KEY (`id`) REFERENCES `evidence` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `working_detective_case`
--

DROP TABLE IF EXISTS `working_detective_case`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `working_detective_case` (
                                          `caseId` int NOT NULL,
                                          `detectiveId` int NOT NULL,
                                          PRIMARY KEY (`caseId`,`detectiveId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-15 10:49:49