-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: BridgesAndCo
-- ------------------------------------------------------
-- Server version	5.7.39

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `active` bit(1) DEFAULT NULL,
  `password` varchar(200) NOT NULL,
  `username` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,_binary '','80ae7cbd9650a6499c40cffd9cbe3d6442874ac5d2d8b585bfc1bcb14fa907812ced461e97f5185de37b441e1b732d67e44c559289e3357ed4e9213262d8a29','forevergone'),(2,_binary '','a3a0c8b42d6bf3aaa17c30d82ed5cbd8116619d3599c6f8b87209348486372194b2a692a19b95c3d68c056128c51dd475bbe2236f06bb9e8570251634d2af','boi'),(3,_binary '','df786dcd78a119a8da8ae9d1e483062b5d06e3dacf69543ce434d09775b64d46d7392ab9ce1dc1cccb9cc4cb99c6a4ecda5164ea5ee52ad610d92cc78cb6','Creeprus'),(4,_binary '','24f172f83bcf29efa5b3f781883422614ba29b5923cbddd95a71d2cf70c34e7b8c4923ac7b91ae6cfc4f77d1f5e531f9ee70fcfbdc314872fe3656ce14','exp'),(5,_binary '','5828bb9f80cc882def88a0ba11b83e7387e1ccf6cc50769bf41e79436aca799425ae5e554745cca6419c6fe2ef52304bab5bdbcc1de4e67b7775b8f927febf','client'),(6,_binary '','2f9d5abf1bd71a99145a8658f584a099d3eaa5e28768d98c230ab87fee3d6a7206b25ac027a68da7968b62472c6317b1448d2a6482ac19243979b7cd133d','client2'),(7,_binary '','a768624d444058fdf5567d7daf43aa0b4abd52327eab28e5f9167daddf631274ee2819364b1ecd7749763cf63aa30926cfbbd8d77c9639e78775f62307293','client3'),(8,_binary '','b819da94ecf584ea5021e3ea6ea20d1fd3bcbdbcfb22824c034484572b062d41cbc9c40521bc69734c5a1cc9f46fabc1914fcdc358dd81e4962e8fda87bb2bf','logist'),(9,_binary '','f1b3571b7badfab7d7cc85447f333fedcf439ff61d5df5b4b909d229b3bf131962632443e4d1a46adc269af9ca74a10e6304d6a3f4defd22918d4174329','courier'),(10,_binary '','30f5b22a778d139a904c50a8771e84c35536c6845705b2bfd769a7922f5ae08e395ee329c292ce56def523445ddb8f319dedb26cfed4050fd61945c842676','Sekiro'),(11,_binary '','4fbbef1ba37678d055d2ba8afca178d622e9343b2f6569752af5aff0b4b2936269133ed5c3814d6d534a86b4771d1dd397fce4a0bbfbc883315def42bab7a7','big'),(12,_binary '','a3f85ae231cc83a0ca33e8e89fe8b9650607af0852b96b1709b11564150c96ed7a134344f5f9164d14efea9038373b10c47a96f751b12b6ad3c06149b55','nice'),(13,_binary '','1ccba9db6ee5655c518347cec837d3ca48ff3af3fc53ef0d8d766282a55225b9c7612a8d817e1845e8be59c1547b69e9112e8e8fd1f4e17905afd5d773d2b','BigBoy'),(14,_binary '','2727a684be752886194629ffdd8839c454d8fc4746168ba6df39f89d54f8b38caebfa5ea84f8debf261d523b302f26894c2aa9e514f0be74b5e87f5cbc553','BigGirl'),(15,_binary '','eae0869b6a1dd5717912216ae3cc3474264b1a4998a859e9930cc8891fd8e505094b50ccefeef0a75bac91775daf94d6bb338130be1a5077f617df95737676','BigRequiem'),(16,_binary '','9980be29ce10f1c8e998c12d37bc8bbcfdebe8a99de9f53ca5c20acdbd4a7cc72adfbc5f939d6655cb126a5a5ecef19a2f63693e525e486b3ca5ea0a597','Tested'),(22,_binary '','20ed032547722b75b6658417d348fc0b03d162d7bb2051c66b351ee4fb7c5d29bc6023e2f99f659c563ae626b9b9fff68d69843da24f5f4c965c22eabc533','TheGodClient'),(23,_binary '','93f019e982aa68d6ecaf24f8497a4f8953b090fb71c0d3999956d196229edd5df2313c9f72b2b375a172fe3f5012c2d0d05b56ae70faa991b8d9c87ace6ec','PepegaLord');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (233),(233),(1);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ordershipment`
--

DROP TABLE IF EXISTS `ordershipment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ordershipment` (
  `id` bigint(20) NOT NULL,
  `amount` int(11) NOT NULL,
  `date_of_depart` datetime DEFAULT NULL,
  `date_of_order` datetime NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `summary` double NOT NULL,
  `pathing_id` bigint(20) DEFAULT NULL,
  `storages_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKkyyth17ospsv1y56mhvwosm6u` (`pathing_id`),
  KEY `FKaocea4qm38ho5u9e43shkrj0l` (`storages_id`),
  CONSTRAINT `FKaocea4qm38ho5u9e43shkrj0l` FOREIGN KEY (`storages_id`) REFERENCES `storage` (`id`),
  CONSTRAINT `FKkyyth17ospsv1y56mhvwosm6u` FOREIGN KEY (`pathing_id`) REFERENCES `pathing` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordershipment`
--

LOCK TABLES `ordershipment` WRITE;
/*!40000 ALTER TABLE `ordershipment` DISABLE KEYS */;
INSERT INTO `ordershipment` VALUES (92,3,NULL,'2022-11-12 20:14:22','Отправлен в доставку',6001,17,52),(93,2,NULL,'2022-11-12 20:15:00','Отменён',0.25,18,34),(94,2,NULL,'2022-11-12 20:16:14','Отправлен в доставку',0.25,19,34),(95,4,NULL,'2022-11-12 20:17:01','Отправлен в доставку',0.44,20,37),(102,1,NULL,'2022-11-12 20:56:58','Доставлен',0.1,27,34),(103,4,NULL,'2022-11-12 20:57:39','Отменён',127.5,28,49),(104,1,NULL,'2022-11-12 21:03:16','Отправлен в доставку',502.65,29,60),(105,10,NULL,'2022-11-12 21:04:53','Доставлен',33352.8,30,63),(106,6,NULL,'2022-11-12 21:23:29','Отменён',8401.4,31,52),(108,5,NULL,'2022-11-13 18:26:49','Доставлен',10,32,72),(111,0,NULL,'2022-11-15 12:40:30','Отменён',30787.2,34,63),(112,2,NULL,'2022-11-15 12:40:48','Отменён',30787.2,35,63),(115,20,NULL,'2022-11-17 22:58:29','Отправлен в доставку',30787.2,36,63),(130,50,NULL,'2022-11-20 16:41:15','Отправлен в доставку',25656,37,63),(131,20,NULL,'2022-11-20 16:54:08','Доставлен',25656,38,63),(142,5,NULL,'2022-11-22 10:20:56','В обработке',440,39,140),(143,5,NULL,'2022-11-22 11:12:08','В обработке',6400,40,134),(144,3,NULL,'2022-11-22 15:50:37','Отменён',330,41,140),(145,10,NULL,'2022-11-22 15:51:40','Доставлен',5400,42,134),(226,22323213,NULL,'2022-12-06 11:13:34','Отменён',15602.6,43,52),(228,3,NULL,'2022-12-06 18:07:27','Доставлен',127.5,44,49);
/*!40000 ALTER TABLE `ordershipment` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_unicode_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `orderadd` AFTER INSERT ON `ordershipment` FOR EACH ROW 
BEGIN
UPDATE storage SET amount = amount-NEW.amount WHERE NEW.storages_id=storage.id;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_unicode_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `orderupdate` AFTER UPDATE ON `ordershipment` FOR EACH ROW BEGIN

UPDATE storage SET amount = amount+NEW.amount WHERE (NEW.storages_id=storage.id AND NEW.status='Отменён');
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `ordershipment_user`
--

DROP TABLE IF EXISTS `ordershipment_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ordershipment_user` (
  `user_id` bigint(20) NOT NULL,
  `ordershipment_id` bigint(20) NOT NULL,
  KEY `FK60357yk9fctooxuepu9akt3gm` (`ordershipment_id`),
  KEY `FKn1mpl1xcvwsfsvkrimwjcmrpv` (`user_id`),
  CONSTRAINT `FK60357yk9fctooxuepu9akt3gm` FOREIGN KEY (`ordershipment_id`) REFERENCES `ordershipment` (`id`),
  CONSTRAINT `FKn1mpl1xcvwsfsvkrimwjcmrpv` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordershipment_user`
--

LOCK TABLES `ordershipment_user` WRITE;
/*!40000 ALTER TABLE `ordershipment_user` DISABLE KEYS */;
INSERT INTO `ordershipment_user` VALUES (69,83),(69,84),(69,85),(86,87),(69,93),(69,103),(86,106),(69,111),(69,112),(110,91),(110,91),(88,108),(109,108),(69,94),(110,94),(69,102),(109,102),(69,92),(113,92),(69,95),(113,95),(114,115),(110,115),(69,105),(109,105),(69,130),(110,130),(69,131),(110,131),(86,142),(69,104),(113,104),(69,143),(69,144),(69,145),(110,145),(69,226),(227,228),(110,228);
/*!40000 ALTER TABLE `ordershipment_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pathing`
--

DROP TABLE IF EXISTS `pathing`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pathing` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `adress` varchar(200) DEFAULT NULL,
  `path_time` varchar(255) DEFAULT NULL,
  `transport` varchar(200) DEFAULT NULL,
  `pathcost` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pathing`
--

LOCK TABLES `pathing` WRITE;
/*!40000 ALTER TABLE `pathing` DISABLE KEYS */;
INSERT INTO `pathing` VALUES (16,'ул ПУшкино дом Колотушкино','00:15','Бег',228),(17,'ул ПУшкино дом Колотушкино','00:00','Бег',35),(18,'Душная улица имени Соколовой','Требует назначения','Требует назначения',0),(19,'Душная улица имени Соколовой','00:50','Мотоцикл',300),(20,'Душная улица имени Соколовой','02:00','Машина курьера',1000),(22,'ул ПУшкино дом Колотушкино','Требует назначения','Требует назначения',0),(26,'ул ПУшкино дом Колотушкино','Требует назначения','Требует назначения',0),(27,'ул ПУшкино дом Колотушкино','10:00','Самолёт',90000),(28,'ул ПУшкино дом Колотушкино','Требует назначения','Требует назначения',0),(29,'Душная улица имени Соколовой','00:16','Машина курьера',0),(30,'Душная улица имени Соколовой','Требует назначения','Требует назначения',0),(31,'Душная улица имени Соколовой','Требует назначения','Требует назначения',0),(32,'Невозможная улица имени Тутанхамона','00:15','Телепортация',10000000),(34,'л Пушкино дом Колт','Требует назначения','Требует назначения',0),(35,'ул Пушкино дом Колотушкино','Требует назначения','Требует назначения',0),(36,'fafafafafafafafa','01:59','Пепеговые коньки',250),(37,'ул Боли и страданий дом 20','00:55','Ход',300),(38,'ул Боли и страданий дом 20','00:50','Машина',300),(39,'ул Пушкино дом Колотушкино','Требует назначения','Требует назначения',0),(40,'В МПТ СРОЧНО!','Требует назначения','Требует назначения',0),(41,'Душная улица имени Соколовой','Требует назначения','Требует назначения',0),(42,'ул Пушкино дом Колотушкино','00:45','Машина курьера',100),(43,'aafagagagaaga','Требует назначения','Требует назначения',0),(44,'ул. Дымская д 20','00:15','Автобус №591',80);
/*!40000 ALTER TABLE `pathing` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `user_id` bigint(20) NOT NULL,
  `role` varchar(255) DEFAULT NULL,
  KEY `FK727f43bssri7socooxn5gknou` (`user_id`),
  CONSTRAINT `FK727f43bssri7socooxn5gknou` FOREIGN KEY (`user_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'Администратор'),(3,'Курьер'),(2,'Кладовщик'),(4,'Поставщик'),(5,'Клиент'),(6,'Клиент'),(7,'Клиент'),(8,'Логист'),(9,'Курьер'),(11,'Курьер'),(12,'Клиент'),(10,'Курьер'),(13,'Клиент'),(14,'Клиент'),(16,'Поставщик'),(15,'Кладовщик'),(22,'Клиент'),(23,'Клиент');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shipment`
--

DROP TABLE IF EXISTS `shipment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shipment` (
  `id` bigint(20) NOT NULL,
  `cost` double DEFAULT NULL,
  `expirationdate` date DEFAULT NULL,
  `shipmentname` varchar(150) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shipment`
--

LOCK TABLES `shipment` WRITE;
/*!40000 ALTER TABLE `shipment` DISABLE KEYS */;
INSERT INTO `shipment` VALUES (32,0.05,'2022-11-16','Мазь Ада Бонг'),(35,0.11,'2022-11-29','Боль и страдания кексиков'),(47,25.5,NULL,'УНИЧТОЖИТЕЛЬНЫЙ МОЛОТОК БОЛИ'),(50,1200.2,'2022-12-10','Кровь и Бетон'),(53,1200.2,'2022-12-10','СМАЗКА САНИ ОТ БОЛИ'),(58,100.53,NULL,'Murasama the one'),(61,256.56,NULL,'Мазь Богов Олимпа'),(70,1,'2022-11-01','111886767557757575'),(132,200,NULL,'Пара Соколовой'),(135,20,NULL,'Чья-то Пара'),(138,22,NULL,'Яростная жимолость'),(230,250,NULL,'Ценный товар');
/*!40000 ALTER TABLE `shipment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `storage`
--

DROP TABLE IF EXISTS `storage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `storage` (
  `id` bigint(20) NOT NULL,
  `amount` int(11) NOT NULL,
  `shipments_id` bigint(20) DEFAULT NULL,
  `supplies_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKa0io5yjxnn66yn0crvv2cks9o` (`shipments_id`),
  KEY `FK5o145ll40vkjsj7ngog2bx0h5` (`supplies_id`),
  CONSTRAINT `FK5o145ll40vkjsj7ngog2bx0h5` FOREIGN KEY (`supplies_id`) REFERENCES `supply` (`id`),
  CONSTRAINT `FKa0io5yjxnn66yn0crvv2cks9o` FOREIGN KEY (`shipments_id`) REFERENCES `shipment` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `storage`
--

LOCK TABLES `storage` WRITE;
/*!40000 ALTER TABLE `storage` DISABLE KEYS */;
INSERT INTO `storage` VALUES (34,15,32,33),(37,7,35,36),(49,2,47,48),(52,13,50,51),(55,12,53,54),(60,4,58,59),(63,120,61,62),(72,15,70,71),(134,17,132,133),(137,100,135,136),(140,14,138,139),(232,30,230,231);
/*!40000 ALTER TABLE `storage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `supplier` (
  `id` bigint(20) NOT NULL,
  `country` varchar(50) DEFAULT NULL,
  `suppliername` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier`
--

LOCK TABLES `supplier` WRITE;
/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
INSERT INTO `supplier` VALUES (1,'Japan','POM-CO'),(64,'Россия','Русские сюрпризы'),(73,'Пепегаланд','Пепегалорды Корпорейтед'),(116,'Россия','Волшебники из МПТ имени Шимбирёва'),(141,'Ад','Адская коалиция'),(200,'Mongolia','NotChengUsHan');
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supply`
--

DROP TABLE IF EXISTS `supply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `supply` (
  `id` bigint(20) NOT NULL,
  `dateofsupply` datetime NOT NULL,
  `supplier_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKh0f0q16hermyythmxxn92j458` (`supplier_id`),
  CONSTRAINT `FKh0f0q16hermyythmxxn92j458` FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supply`
--

LOCK TABLES `supply` WRITE;
/*!40000 ALTER TABLE `supply` DISABLE KEYS */;
INSERT INTO `supply` VALUES (33,'2022-11-04 15:59:34',1),(36,'2022-11-04 16:02:50',64),(48,'2022-11-04 20:27:35',1),(51,'2022-11-04 20:29:18',116),(54,'2022-11-04 20:31:18',1),(59,'2022-11-05 01:12:40',1),(62,'2022-11-05 18:19:28',73),(71,'2022-11-08 16:05:30',1),(133,'2022-11-20 17:12:22',1),(136,'2022-11-20 17:14:07',1),(139,'2022-11-20 17:17:43',141),(231,'2022-12-06 18:13:06',141);
/*!40000 ALTER TABLE `supply` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `name` varchar(30) NOT NULL,
  `patronymic` varchar(255) DEFAULT NULL,
  `surname` varchar(30) NOT NULL,
  `account_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKc3b4xfbq6rbkkrddsdum8t5f0` (`account_id`),
  CONSTRAINT `FKc3b4xfbq6rbkkrddsdum8t5f0` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'isip_l.k.kornienko@mpt.ru','79251330369','Kobeni','Kobenivich','Kobeniv',1),(2,'','','demon','','demon',2),(3,'','','pain','demon','demon',3),(4,'','79251440901','Kobeni','Kobenivich','фвфв',4),(69,'www.creeperpro@ya.ru','','Пепега','Пепегович','Пепегов',5),(86,'','','Владимир','Сергеевич','Владосо',6),(88,'','','Kobeni','Kobenivich','Kobeniv',7),(107,'','89800553535','Ультра','','Логист',8),(109,'www.cadddreeperpro@yandex.ru','','The','','Courier',9),(110,'test@test.test','78434338091','One','Wolf','Armed',10),(113,'','','Big','Big','Big',11),(114,'','','Очень','','Красивый',12),(125,'','','Мальчик','Проверку','НаКрутую',13),(126,'','','GIrl','Girl','Girl',14),(128,'','','Girono','','Giovanna',15),(129,'','','The','AndOnly','One',16),(148,'','89543001952','Божетсво','','Божественное',22),(227,'pepegas@mail.ru','','Василий','','Мечсен',23);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-12-08 14:33:07
