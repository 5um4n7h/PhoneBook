-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: localhost    Database: contacts
-- ------------------------------------------------------
-- Server version	8.0.21

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
-- Temporary view structure for view `all_contacts`
--

DROP TABLE IF EXISTS `all_contacts`;
/*!50001 DROP VIEW IF EXISTS `all_contacts`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `all_contacts` AS SELECT 
 1 AS `id`,
 1 AS `category`,
 1 AS `name`,
 1 AS `description`,
 1 AS `address`,
 1 AS `address_link`,
 1 AS `no1`,
 1 AS `no2`,
 1 AS `email`,
 1 AS `website`,
 1 AS `facebook`,
 1 AS `instagram`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `allcontacts`
--

DROP TABLE IF EXISTS `allcontacts`;
/*!50001 DROP VIEW IF EXISTS `allcontacts`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `allcontacts` AS SELECT 
 1 AS `id`,
 1 AS `category`,
 1 AS `name`,
 1 AS `description`,
 1 AS `address`,
 1 AS `address_link`,
 1 AS `no1`,
 1 AS `no2`,
 1 AS `email`,
 1 AS `website`,
 1 AS `facebook`,
 1 AS `instagram`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `authentication`
--

DROP TABLE IF EXISTS `authentication`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `authentication` (
  `userid` int NOT NULL,
  `type` varchar(45) NOT NULL,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authentication`
--

LOCK TABLES `authentication` WRITE;
/*!40000 ALTER TABLE `authentication` DISABLE KEYS */;
INSERT INTO `authentication` VALUES (1,'admin','admin','admin'),(2,'user','user','user');
/*!40000 ALTER TABLE `authentication` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `defaultcontactdetails`
--

DROP TABLE IF EXISTS `defaultcontactdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `defaultcontactdetails` (
  `id` varchar(45) NOT NULL,
  `no1` varchar(45) DEFAULT NULL,
  `no2` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `website` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_idx` (`id`),
  CONSTRAINT `fk1` FOREIGN KEY (`id`) REFERENCES `defaultcontacts` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `defaultcontactdetails`
--

LOCK TABLES `defaultcontactdetails` WRITE;
/*!40000 ALTER TABLE `defaultcontactdetails` DISABLE KEYS */;
INSERT INTO `defaultcontactdetails` VALUES ('d122','8212403735','8212403734','admissions@nieit.ac.in','https://nieit.ac.in');
/*!40000 ALTER TABLE `defaultcontactdetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `defaultcontacts`
--

DROP TABLE IF EXISTS `defaultcontacts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `defaultcontacts` (
  `id` varchar(45) NOT NULL,
  `category` int DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(400) DEFAULT NULL,
  `address` varchar(250) DEFAULT NULL,
  `address_link` varchar(85) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `defaultcontacts`
--

LOCK TABLES `defaultcontacts` WRITE;
/*!40000 ALTER TABLE `defaultcontacts` DISABLE KEYS */;
INSERT INTO `defaultcontacts` VALUES ('d122',6,'NIE Institute of Technology ','NIE Institute of Technology is a premier educational institute affiliated to Visvesvaraya Technological University, approved by UGC and recognised by AICTE. The institute was established in 2008. It offers courses in engineering and technology.','No 50, Koorgalli Village, Hootagalli Industrial Area, next to BEML, Mysuru, Karnataka 570018','https://goo.gl/maps/juHmmQHNRxm2VXhB7'),('d123',0,'a','a','a','aa');
/*!40000 ALTER TABLE `defaultcontacts` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `defaultcontacts_AFTER_INSERT` AFTER INSERT ON `defaultcontacts` FOR EACH ROW BEGIN
update contacts.id SET id.id=id.id+1;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `defaultcontactsocial`
--

DROP TABLE IF EXISTS `defaultcontactsocial`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `defaultcontactsocial` (
  `id` varchar(45) NOT NULL,
  `facebook` varchar(80) DEFAULT NULL,
  `instagram` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk2` FOREIGN KEY (`id`) REFERENCES `defaultcontacts` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `defaultcontactsocial`
--

LOCK TABLES `defaultcontactsocial` WRITE;
/*!40000 ALTER TABLE `defaultcontactsocial` DISABLE KEYS */;
INSERT INTO `defaultcontactsocial` VALUES ('d122','https://www.facebook.com/nieitofficial','https://www.instagram.com/nie_institute_of_technology/'),('d123','a','a');
/*!40000 ALTER TABLE `defaultcontactsocial` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `id`
--

DROP TABLE IF EXISTS `id`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `id` (
  `id` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `id`
--

LOCK TABLES `id` WRITE;
/*!40000 ALTER TABLE `id` DISABLE KEYS */;
INSERT INTO `id` VALUES (140);
/*!40000 ALTER TABLE `id` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usercontactdetails`
--

DROP TABLE IF EXISTS `usercontactdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usercontactdetails` (
  `id` varchar(45) NOT NULL,
  `no1` varchar(45) DEFAULT NULL,
  `no2` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `website` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk3` FOREIGN KEY (`id`) REFERENCES `usercontacts` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usercontactdetails`
--

LOCK TABLES `usercontactdetails` WRITE;
/*!40000 ALTER TABLE `usercontactdetails` DISABLE KEYS */;
INSERT INTO `usercontactdetails` VALUES ('u124','46','35','test','test');
/*!40000 ALTER TABLE `usercontactdetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usercontacts`
--

DROP TABLE IF EXISTS `usercontacts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usercontacts` (
  `id` varchar(45) NOT NULL,
  `category` int DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `address_link` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usercontacts`
--

LOCK TABLES `usercontacts` WRITE;
/*!40000 ALTER TABLE `usercontacts` DISABLE KEYS */;
INSERT INTO `usercontacts` VALUES ('u124',0,'est','test','test','test');
/*!40000 ALTER TABLE `usercontacts` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `usercontacts_AFTER_INSERT` AFTER INSERT ON `usercontacts` FOR EACH ROW BEGIN
update contacts.id SET id.id=id.id+1;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `usercontactsocial`
--

DROP TABLE IF EXISTS `usercontactsocial`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usercontactsocial` (
  `id` varchar(45) NOT NULL,
  `facebook` varchar(45) DEFAULT NULL,
  `instagram` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk4` FOREIGN KEY (`id`) REFERENCES `usercontacts` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usercontactsocial`
--

LOCK TABLES `usercontactsocial` WRITE;
/*!40000 ALTER TABLE `usercontactsocial` DISABLE KEYS */;
INSERT INTO `usercontactsocial` VALUES ('u124','est','test');
/*!40000 ALTER TABLE `usercontactsocial` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'contacts'
--

--
-- Dumping routines for database 'contacts'
--
/*!50003 DROP PROCEDURE IF EXISTS `getAllContacts` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllContacts`(IN cat INT)
BEGIN
    SELECT 
     *
    FROM
        ((`defaultcontacts`
        JOIN `defaultcontactdetails` ON ((`defaultcontacts`.`id` = `defaultcontactdetails`.`id`)))
        JOIN `defaultcontactsocial` ON ((`defaultcontacts`.`id` = `defaultcontactsocial`.`id`))) 
        where defaultcontacts.category=cat
    UNION ALL SELECT 
 *
    FROM
        ((`usercontacts`
        JOIN `usercontactdetails` ON ((`usercontacts`.`id` = `usercontactdetails`.`id`)))
        JOIN `usercontactsocial` ON ((`usercontacts`.`id` = `usercontactsocial`.`id`)))
where usercontacts.category=cat;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `searchByName` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `searchByName`(IN cat INT,IN name VARCHAR(30))
BEGIN
   SELECT 
     *
    FROM
        ((`defaultcontacts`
        JOIN `defaultcontactdetails` ON ((`defaultcontacts`.`id` = `defaultcontactdetails`.`id`)))
        JOIN `defaultcontactsocial` ON ((`defaultcontacts`.`id` = `defaultcontactsocial`.`id`))) 
        where defaultcontacts.category=cat AND defaultcontacts.name like name 
    UNION ALL SELECT 
 *
    FROM
        ((`usercontacts`
        JOIN `usercontactdetails` ON ((`usercontacts`.`id` = `usercontactdetails`.`id`)))
        JOIN `usercontactsocial` ON ((`usercontacts`.`id` = `usercontactsocial`.`id`)))
where usercontacts.category=cat AND usercontacts.name like name;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Final view structure for view `all_contacts`
--

/*!50001 DROP VIEW IF EXISTS `all_contacts`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `all_contacts` AS select `defaultcontacts`.`id` AS `id`,`defaultcontacts`.`category` AS `category`,`defaultcontacts`.`name` AS `name`,`defaultcontacts`.`description` AS `description`,`defaultcontacts`.`address` AS `address`,`defaultcontacts`.`address_link` AS `address_link`,`defaultcontactdetails`.`no1` AS `no1`,`defaultcontactdetails`.`no2` AS `no2`,`defaultcontactdetails`.`email` AS `email`,`defaultcontactdetails`.`website` AS `website`,`defaultcontactsocial`.`facebook` AS `facebook`,`defaultcontactsocial`.`instagram` AS `instagram` from ((`defaultcontacts` join `defaultcontactdetails` on((`defaultcontacts`.`id` = `defaultcontactdetails`.`id`))) join `defaultcontactsocial` on((`defaultcontacts`.`id` = `defaultcontactsocial`.`id`))) union all select `usercontacts`.`id` AS `id`,`usercontacts`.`category` AS `category`,`usercontacts`.`name` AS `name`,`usercontacts`.`description` AS `description`,`usercontacts`.`address` AS `address`,`usercontacts`.`address_link` AS `address_link`,`usercontactdetails`.`no1` AS `no1`,`usercontactdetails`.`no2` AS `no2`,`usercontactdetails`.`email` AS `email`,`usercontactdetails`.`website` AS `website`,`usercontactsocial`.`facebook` AS `facebook`,`usercontactsocial`.`instagram` AS `instagram` from ((`usercontacts` join `usercontactdetails` on((`usercontacts`.`id` = `usercontactdetails`.`id`))) join `usercontactsocial` on((`usercontacts`.`id` = `usercontactsocial`.`id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `allcontacts`
--

/*!50001 DROP VIEW IF EXISTS `allcontacts`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `allcontacts` AS select `defaultcontacts`.`id` AS `id`,`defaultcontacts`.`category` AS `category`,`defaultcontacts`.`name` AS `name`,`defaultcontacts`.`description` AS `description`,`defaultcontacts`.`address` AS `address`,`defaultcontacts`.`address_link` AS `address_link`,`defaultcontactdetails`.`no1` AS `no1`,`defaultcontactdetails`.`no2` AS `no2`,`defaultcontactdetails`.`email` AS `email`,`defaultcontactdetails`.`website` AS `website`,`defaultcontactsocial`.`facebook` AS `facebook`,`defaultcontactsocial`.`instagram` AS `instagram` from ((`defaultcontacts` join `defaultcontactdetails` on((`defaultcontacts`.`id` = `defaultcontactdetails`.`id`))) join `defaultcontactsocial` on((`defaultcontacts`.`id` = `defaultcontactsocial`.`id`))) union all select `usercontacts`.`id` AS `id`,`usercontacts`.`category` AS `category`,`usercontacts`.`name` AS `name`,`usercontacts`.`description` AS `description`,`usercontacts`.`address` AS `address`,`usercontacts`.`address_link` AS `address_link`,`usercontactdetails`.`no1` AS `no1`,`usercontactdetails`.`no2` AS `no2`,`usercontactdetails`.`email` AS `email`,`usercontactdetails`.`website` AS `website`,`usercontactsocial`.`facebook` AS `facebook`,`usercontactsocial`.`instagram` AS `instagram` from ((`usercontacts` join `usercontactdetails` on((`usercontacts`.`id` = `usercontactdetails`.`id`))) join `usercontactsocial` on((`usercontacts`.`id` = `usercontactsocial`.`id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-12-24 15:21:28
