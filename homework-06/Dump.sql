-- MySQL dump 10.13  Distrib 8.0.25, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: spring-home-work-db
-- ------------------------------------------------------
-- Server version	8.0.24

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
-- Table structure for table `buyers`
--

DROP TABLE IF EXISTS `buyers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `buyers` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `buyers`
--

LOCK TABLES `buyers` WRITE;
/*!40000 ALTER TABLE `buyers` DISABLE KEYS */;
INSERT INTO `buyers` VALUES (1,'Alex'),(2,'Daniel'),(3,'Maria'),(4,'Maria'),(5,'Goodwin'),(6,'Alex'),(7,'Daniel'),(8,'Maria'),(9,'Maria'),(10,'Goodwin');
/*!40000 ALTER TABLE `buyers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `price` int DEFAULT NULL,
  `title` varchar(255) NOT NULL,
  `buyer_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKk28lyx5vbpu44sheaeeah6ucw` (`buyer_id`),
  CONSTRAINT `FKk28lyx5vbpu44sheaeeah6ucw` FOREIGN KEY (`buyer_id`) REFERENCES `buyers` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,45,'Bread',1),(2,85,'Buckwheat',1),(3,65,'Onion',1),(4,70,'Potatoes',1),(5,75,'Milk',2),(6,115,'Apple',2),(7,185,'Orange',2),(8,70,'Potatoes',2),(9,95,'Spaghetti',2),(10,70,'Potatoes',3),(11,115,'Apple',3),(12,250,'Butter',3),(13,95,'Spaghetti',3),(14,85,'Buckwheat',3),(15,75,'Milk',3),(16,65,'Onion',3),(17,70,'Potatoes',4),(18,115,'Apple',4),(19,75,'Milk',4),(20,65,'Onion',4),(21,75,'Milk',5),(22,115,'Apple',5),(23,185,'Orange',5),(24,70,'Potatoes',5),(25,250,'Butter',5),(26,85,'Buckwheat',5),(27,95,'Spaghetti',5),(28,45,'Bread',6),(29,85,'Buckwheat',6),(30,65,'Onion',6),(31,70,'Potatoes',6),(32,75,'Milk',7),(33,115,'Apple',7),(34,185,'Orange',7),(35,70,'Potatoes',7),(36,95,'Spaghetti',7),(37,70,'Potatoes',8),(38,115,'Apple',8),(39,250,'Butter',8),(40,95,'Spaghetti',8),(41,85,'Buckwheat',8),(42,75,'Milk',8),(43,65,'Onion',8),(44,70,'Potatoes',9),(45,115,'Apple',9),(46,75,'Milk',9),(47,65,'Onion',9),(48,75,'Milk',10),(49,115,'Apple',10),(50,185,'Orange',10),(51,70,'Potatoes',10),(52,250,'Butter',10),(53,85,'Buckwheat',10),(54,95,'Spaghetti',10);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'spring-home-work-db'
--

--
-- Dumping routines for database 'spring-home-work-db'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-04-19  5:17:12
