CREATE DATABASE  IF NOT EXISTS `kmap` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `kmap`;
-- MySQL dump 10.13  Distrib 5.7.9, for osx10.9 (x86_64)
--
-- Host: 127.0.0.1    Database: kmap
-- ------------------------------------------------------
-- Server version	5.7.9

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cc`
--

DROP TABLE IF EXISTS `cc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cc` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `tail` int(10) unsigned DEFAULT NULL COMMENT '前驱课程',
  `head` int(10) unsigned DEFAULT NULL COMMENT '后继课程',
  `majorId` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `tail` (`tail`),
  KEY `head` (`head`),
  CONSTRAINT `cc_ibfk_1` FOREIGN KEY (`tail`) REFERENCES `course` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `cc_ibfk_2` FOREIGN KEY (`head`) REFERENCES `course` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cc`
--

LOCK TABLES `cc` WRITE;
/*!40000 ALTER TABLE `cc` DISABLE KEYS */;
INSERT INTO `cc` VALUES (1,1,13,0),(2,1,20,0),(3,1,4,0),(4,1,2,0),(5,2,13,0),(6,2,20,0),(7,2,4,0),(10,4,13,0),(11,4,20,0),(12,7,23,0),(13,7,42,0),(14,7,29,0),(15,7,26,0),(16,7,8,0),(17,8,12,0),(18,8,18,0),(19,9,33,0),(20,9,16,0),(21,9,14,0),(22,10,14,0),(23,10,39,0),(24,10,15,0),(25,10,16,0),(26,11,27,0),(27,11,26,0),(28,11,32,0),(29,11,17,0),(30,12,30,0),(31,12,32,0),(32,12,45,0),(33,12,24,0),(34,12,31,0),(35,13,14,0),(36,13,44,0),(37,14,30,0),(38,17,44,0),(39,17,12,0),(40,17,18,0),(41,18,30,0),(42,18,31,0),(43,18,44,0),(44,18,32,0),(45,19,21,0),(46,19,14,0),(47,20,13,0),(48,20,14,0),(49,21,14,0),(50,23,26,0),(51,24,28,0),(52,25,28,0),(53,25,1,0),(55,25,4,0),(56,26,30,0),(57,29,30,0),(58,31,30,0),(59,33,14,0),(60,44,32,0),(61,38,14,0),(62,39,38,0),(63,1,1,0),(64,1,2,0),(65,5,5,0),(66,6,6,0),(67,5,1,0),(68,4,4,0),(69,6,5,0),(70,1,6,0),(71,50,47,0),(72,58,48,0),(74,47,50,0),(75,51,53,0),(76,54,51,0),(77,50,52,0),(78,53,54,0),(79,54,55,0),(80,59,56,0),(81,54,57,0),(82,51,58,0),(83,59,59,0),(84,48,48,0),(85,51,50,0),(86,50,51,0),(88,61,60,0),(90,62,60,0),(91,63,60,0),(92,64,60,0),(93,65,60,0),(96,60,62,0),(97,60,63,0),(98,60,64,0),(99,60,65,0),(101,60,66,0);
/*!40000 ALTER TABLE `cc` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-03-17 11:23:58