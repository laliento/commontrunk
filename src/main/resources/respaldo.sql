CREATE DATABASE  IF NOT EXISTS `commonTrunk` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish2_ci */;
USE `commonTrunk`;
-- MySQL dump 10.16  Distrib 10.2.21-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: commonTrunk
-- ------------------------------------------------------
-- Server version	10.2.21-MariaDB

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
-- Table structure for table `CAT_ESTADO_USUARIO`
--

DROP TABLE IF EXISTS `CAT_ESTADO_USUARIO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CAT_ESTADO_USUARIO` (
  `ID_ESTADO_USUARIO` int(11) NOT NULL AUTO_INCREMENT,
  `DESCRIPCION` varchar(255) COLLATE utf8_spanish2_ci DEFAULT NULL,
  PRIMARY KEY (`ID_ESTADO_USUARIO`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CAT_ESTADO_USUARIO`
--

LOCK TABLES `CAT_ESTADO_USUARIO` WRITE;
/*!40000 ALTER TABLE `CAT_ESTADO_USUARIO` DISABLE KEYS */;
INSERT INTO `CAT_ESTADO_USUARIO` VALUES (1,'Active'),(2,'Inactive');
/*!40000 ALTER TABLE `CAT_ESTADO_USUARIO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CAT_MENU`
--

DROP TABLE IF EXISTS `CAT_MENU`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CAT_MENU` (
  `ID_MENU` int(11) NOT NULL AUTO_INCREMENT,
  `DESCRIPCION` varchar(50) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `ICONO` varchar(50) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `ORDEN` int(11) DEFAULT NULL,
  `URL` varchar(100) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `ID_MENU_PADRE` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID_MENU`),
  KEY `FK_MENU_MENUP` (`ID_MENU_PADRE`),
  CONSTRAINT `FK_MENU_MENUP` FOREIGN KEY (`ID_MENU_PADRE`) REFERENCES `CAT_MENU` (`ID_MENU`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CAT_MENU`
--

LOCK TABLES `CAT_MENU` WRITE;
/*!40000 ALTER TABLE `CAT_MENU` DISABLE KEYS */;
INSERT INTO `CAT_MENU` VALUES (1,'menuPrueba','fa-anchor',1,NULL,NULL),(2,'Submenu','fa-arrows-v',1,'/pages/admin/otro.xhtml',1),(3,'Pedido','fa-anchor',2,NULL,NULL),(4,'Orden','fa-arrows-v',1,'orderView.goPage',3),(5,'Consulta Orden','fa-anchor',2,'consultaOrderView.goPage',3);
/*!40000 ALTER TABLE `CAT_MENU` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CAT_ORDER_STATE`
--

DROP TABLE IF EXISTS `CAT_ORDER_STATE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CAT_ORDER_STATE` (
  `ID_ORDER_STATE` int(11) NOT NULL AUTO_INCREMENT,
  `DESCRIPTION` varchar(45) COLLATE utf8_spanish2_ci NOT NULL,
  PRIMARY KEY (`ID_ORDER_STATE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CAT_ORDER_STATE`
--

LOCK TABLES `CAT_ORDER_STATE` WRITE;
/*!40000 ALTER TABLE `CAT_ORDER_STATE` DISABLE KEYS */;
/*!40000 ALTER TABLE `CAT_ORDER_STATE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CAT_PERFIL`
--

DROP TABLE IF EXISTS `CAT_PERFIL`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CAT_PERFIL` (
  `ID_PERFIL` int(11) NOT NULL AUTO_INCREMENT,
  `DESCRIPCION` varchar(255) COLLATE utf8_spanish2_ci DEFAULT NULL,
  PRIMARY KEY (`ID_PERFIL`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CAT_PERFIL`
--

LOCK TABLES `CAT_PERFIL` WRITE;
/*!40000 ALTER TABLE `CAT_PERFIL` DISABLE KEYS */;
INSERT INTO `CAT_PERFIL` VALUES (1,'ADMIN'),(2,'USER'),(3,'LALO');
/*!40000 ALTER TABLE `CAT_PERFIL` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CAT_PRODUCT_STATE`
--

DROP TABLE IF EXISTS `CAT_PRODUCT_STATE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CAT_PRODUCT_STATE` (
  `ID_PRODUCT_STATE` int(11) NOT NULL AUTO_INCREMENT,
  `DESCRIPTION` varchar(45) COLLATE utf8_spanish2_ci NOT NULL,
  PRIMARY KEY (`ID_PRODUCT_STATE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CAT_PRODUCT_STATE`
--

LOCK TABLES `CAT_PRODUCT_STATE` WRITE;
/*!40000 ALTER TABLE `CAT_PRODUCT_STATE` DISABLE KEYS */;
/*!40000 ALTER TABLE `CAT_PRODUCT_STATE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ENT_ORDER_DET`
--

DROP TABLE IF EXISTS `ENT_ORDER_DET`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ENT_ORDER_DET` (
  `ID_ORDER_DET` int(11) NOT NULL AUTO_INCREMENT,
  `ID_ORDER_ENC` int(11) NOT NULL,
  `ID_PRODUCT` int(11) NOT NULL,
  `PRICE` decimal(15,2) NOT NULL,
  `QUANTITY` int(11) NOT NULL,
  PRIMARY KEY (`ID_ORDER_DET`),
  KEY `fk_ORDERDET_ENC_idx` (`ID_ORDER_ENC`),
  KEY `FK_ORDER_PRODUCT_idx` (`ID_PRODUCT`),
  CONSTRAINT `FK_ORDERDET_ENC` FOREIGN KEY (`ID_ORDER_ENC`) REFERENCES `ENT_ORDER_ENC` (`ID_ORDER_ENC`),
  CONSTRAINT `FK_ORDER_PRODUCT` FOREIGN KEY (`ID_PRODUCT`) REFERENCES `ENT_PRODUCT` (`ID_PRODUCT`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ENT_ORDER_DET`
--

LOCK TABLES `ENT_ORDER_DET` WRITE;
/*!40000 ALTER TABLE `ENT_ORDER_DET` DISABLE KEYS */;
/*!40000 ALTER TABLE `ENT_ORDER_DET` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ENT_ORDER_ENC`
--

DROP TABLE IF EXISTS `ENT_ORDER_ENC`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ENT_ORDER_ENC` (
  `ID_ORDER_ENC` int(11) NOT NULL AUTO_INCREMENT,
  `ID_ADMIN_USER` int(11) NOT NULL,
  `ID_DELIVERY_USER` int(11) NOT NULL,
  `ID_CUSTOMER_USER` int(11) NOT NULL COMMENT '	',
  `ID_ORDER_STATE` int(11) NOT NULL,
  `CREATION_DATE` datetime NOT NULL,
  `LAST_UPDATE` datetime NOT NULL,
  `ADITIONAL_INFO` varchar(500) COLLATE utf8_spanish2_ci DEFAULT NULL,
  PRIMARY KEY (`ID_ORDER_ENC`),
  KEY `FK_ORDER_ADMIN_idx` (`ID_ADMIN_USER`),
  KEY `FK_ORDER_DELIVERY_idx` (`ID_DELIVERY_USER`),
  KEY `FK_ORDER_CUSTOMER_idx` (`ID_CUSTOMER_USER`),
  KEY `FK_ORDER_STATE_idx` (`ID_ORDER_STATE`),
  CONSTRAINT `FK_ORDER_ADMIN` FOREIGN KEY (`ID_ADMIN_USER`) REFERENCES `ENT_USUARIO` (`ID_USUARIO`),
  CONSTRAINT `FK_ORDER_CUSTOMER` FOREIGN KEY (`ID_CUSTOMER_USER`) REFERENCES `ENT_USUARIO` (`ID_USUARIO`),
  CONSTRAINT `FK_ORDER_DELIVERY` FOREIGN KEY (`ID_DELIVERY_USER`) REFERENCES `ENT_USUARIO` (`ID_USUARIO`),
  CONSTRAINT `FK_ORDER_STATE` FOREIGN KEY (`ID_ORDER_STATE`) REFERENCES `CAT_ORDER_STATE` (`ID_ORDER_STATE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ENT_ORDER_ENC`
--

LOCK TABLES `ENT_ORDER_ENC` WRITE;
/*!40000 ALTER TABLE `ENT_ORDER_ENC` DISABLE KEYS */;
/*!40000 ALTER TABLE `ENT_ORDER_ENC` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ENT_PRODUCT`
--

DROP TABLE IF EXISTS `ENT_PRODUCT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ENT_PRODUCT` (
  `ID_PRODUCT` int(11) NOT NULL AUTO_INCREMENT,
  `ID_PRODUCT_STATE` int(11) NOT NULL,
  `PRICE` decimal(15,2) NOT NULL,
  `GENERIC_DESCRIPTION` varchar(200) COLLATE utf8_spanish2_ci NOT NULL,
  `BRAND` varchar(200) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `SUBBRAND` varchar(200) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `MODEL_STYLE_TYPE` varchar(200) COLLATE utf8_spanish2_ci DEFAULT NULL,
  PRIMARY KEY (`ID_PRODUCT`),
  KEY `FK_PRODUCT_STATE_idx` (`ID_PRODUCT_STATE`),
  CONSTRAINT `FK_PRODUCT_STATE` FOREIGN KEY (`ID_PRODUCT_STATE`) REFERENCES `CAT_PRODUCT_STATE` (`ID_PRODUCT_STATE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ENT_PRODUCT`
--

LOCK TABLES `ENT_PRODUCT` WRITE;
/*!40000 ALTER TABLE `ENT_PRODUCT` DISABLE KEYS */;
/*!40000 ALTER TABLE `ENT_PRODUCT` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ENT_USUARIO`
--

DROP TABLE IF EXISTS `ENT_USUARIO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ENT_USUARIO` (
  `ID_USUARIO` int(11) NOT NULL AUTO_INCREMENT,
  `APELLIDO_MATERNO` varchar(255) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `APELLIDO_PATERNO` varchar(255) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `CORREO` varchar(255) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `ENABLE` tinyint(4) DEFAULT NULL,
  `NOMBRE` varchar(255) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `PASSWORD` char(128) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `SALT` char(128) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `USERNAME` varchar(255) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `ID_ESTADO_USUARIO` int(11) NOT NULL,
  `ID_PERFIL` int(11) NOT NULL,
  PRIMARY KEY (`ID_USUARIO`),
  KEY `FK_USUARIO_PERFIL` (`ID_PERFIL`),
  KEY `FK_USUARIO_ESTADO` (`ID_ESTADO_USUARIO`),
  CONSTRAINT `FK_USUARIO_ESTADO` FOREIGN KEY (`ID_ESTADO_USUARIO`) REFERENCES `CAT_ESTADO_USUARIO` (`ID_ESTADO_USUARIO`),
  CONSTRAINT `FK_USUARIO_PERFIL` FOREIGN KEY (`ID_PERFIL`) REFERENCES `CAT_PERFIL` (`ID_PERFIL`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ENT_USUARIO`
--

LOCK TABLES `ENT_USUARIO` WRITE;
/*!40000 ALTER TABLE `ENT_USUARIO` DISABLE KEYS */;
INSERT INTO `ENT_USUARIO` VALUES (1,'Zamorano','Cruz','eduardo.cz.mac@gmail.com',1,'Eduardo','8f5ccebef5619564598c41c20a9c2577ab1d06e108a792b07dbfbaaa7521a347bb9ba5546b529755b419c3a6e9db486a230914faa55752ddb72291e125e2a173','3015caaa9988d2bd4e8fa24014f56c8eeb8287f5e9776630a52b98fcd4609bfec541015b6273139a5fcf0e89cfb1634964358f460559e4ae81bf7a7caabd38a5','laliento',1,3),(2,'appM','appP','usuario@gmail.com',1,'Cliente','8f5ccebef5619564598c41c20a9c2577ab1d06e108a792b07dbfbaaa7521a347bb9ba5546b529755b419c3a6e9db486a230914faa55752ddb72291e125e2a173','3015caaa9988d2bd4e8fa24014f56c8eeb8287f5e9776630a52b98fcd4609bfec541015b6273139a5fcf0e89cfb1634964358f460559e4ae81bf7a7caabd38a5','cliente',1,2);
/*!40000 ALTER TABLE `ENT_USUARIO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `LOGGIN_ATTEMPTS`
--

DROP TABLE IF EXISTS `LOGGIN_ATTEMPTS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `LOGGIN_ATTEMPTS` (
  `ID_LOGG_ATT` int(11) NOT NULL AUTO_INCREMENT,
  `TIEMPO` datetime NOT NULL,
  `ID_USUARIO` int(11) NOT NULL,
  PRIMARY KEY (`ID_LOGG_ATT`),
  KEY `FK_LOGGINATT_USUARIO` (`ID_USUARIO`),
  CONSTRAINT `FK_LOGGINATT_USUARIO` FOREIGN KEY (`ID_USUARIO`) REFERENCES `ENT_USUARIO` (`ID_USUARIO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `LOGGIN_ATTEMPTS`
--

LOCK TABLES `LOGGIN_ATTEMPTS` WRITE;
/*!40000 ALTER TABLE `LOGGIN_ATTEMPTS` DISABLE KEYS */;
/*!40000 ALTER TABLE `LOGGIN_ATTEMPTS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PARAMETRO_GENERAL`
--

DROP TABLE IF EXISTS `PARAMETRO_GENERAL`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PARAMETRO_GENERAL` (
  `CLAVE` varchar(100) COLLATE utf8_spanish2_ci NOT NULL,
  `DESCRIPCION` varchar(100) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `VALOR` varchar(500) COLLATE utf8_spanish2_ci NOT NULL,
  PRIMARY KEY (`CLAVE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PARAMETRO_GENERAL`
--

LOCK TABLES `PARAMETRO_GENERAL` WRITE;
/*!40000 ALTER TABLE `PARAMETRO_GENERAL` DISABLE KEYS */;
INSERT INTO `PARAMETRO_GENERAL` VALUES ('intentosPermitidosLoggin','login fails number','3'),('minutosCuentaBloqueada','bocked time user','25');
/*!40000 ALTER TABLE `PARAMETRO_GENERAL` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `REL_PERFIL_MENU`
--

DROP TABLE IF EXISTS `REL_PERFIL_MENU`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `REL_PERFIL_MENU` (
  `ID_REL_PERFIL_MENU` int(11) NOT NULL AUTO_INCREMENT,
  `ID_MENU` int(11) NOT NULL,
  `ID_PERFIL` int(11) NOT NULL,
  PRIMARY KEY (`ID_REL_PERFIL_MENU`),
  KEY `FK_REL_PERFILMENU_MENU` (`ID_MENU`),
  KEY `FK_REL_PERFILMENU_PERFIL` (`ID_PERFIL`),
  CONSTRAINT `FK_REL_PERFILMENU_MENU` FOREIGN KEY (`ID_MENU`) REFERENCES `CAT_MENU` (`ID_MENU`),
  CONSTRAINT `FK_REL_PERFILMENU_PERFIL` FOREIGN KEY (`ID_PERFIL`) REFERENCES `CAT_PERFIL` (`ID_PERFIL`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `REL_PERFIL_MENU`
--

LOCK TABLES `REL_PERFIL_MENU` WRITE;
/*!40000 ALTER TABLE `REL_PERFIL_MENU` DISABLE KEYS */;
INSERT INTO `REL_PERFIL_MENU` VALUES (1,1,1),(2,2,1),(3,3,2),(4,4,2),(5,5,2);
/*!40000 ALTER TABLE `REL_PERFIL_MENU` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persistent_logins`
--

DROP TABLE IF EXISTS `persistent_logins`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `persistent_logins` (
  `username` varchar(64) COLLATE utf8_spanish2_ci NOT NULL,
  `series` varchar(64) COLLATE utf8_spanish2_ci NOT NULL,
  `token` varchar(64) COLLATE utf8_spanish2_ci NOT NULL,
  `last_used` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persistent_logins`
--

LOCK TABLES `persistent_logins` WRITE;
/*!40000 ALTER TABLE `persistent_logins` DISABLE KEYS */;
/*!40000 ALTER TABLE `persistent_logins` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'commonTrunk'
--

--
-- Dumping routines for database 'commonTrunk'
--
/*!50003 DROP PROCEDURE IF EXISTS `ANALYZE_INVALID_FOREIGN_KEYS` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ANALYZE_INVALID_FOREIGN_KEYS`(
        checked_database_name VARCHAR(64), 
        checked_table_name VARCHAR(64), 
        temporary_result_table ENUM('Y', 'N'))
    READS SQL DATA
BEGIN
        DECLARE TABLE_SCHEMA_VAR VARCHAR(64);
        DECLARE TABLE_NAME_VAR VARCHAR(64);
        DECLARE COLUMN_NAME_VAR VARCHAR(64); 
        DECLARE CONSTRAINT_NAME_VAR VARCHAR(64);
        DECLARE REFERENCED_TABLE_SCHEMA_VAR VARCHAR(64);
        DECLARE REFERENCED_TABLE_NAME_VAR VARCHAR(64);
        DECLARE REFERENCED_COLUMN_NAME_VAR VARCHAR(64);
        DECLARE KEYS_SQL_VAR VARCHAR(1024);

        DECLARE done INT DEFAULT 0;

        DECLARE foreign_key_cursor CURSOR FOR
            SELECT
                `TABLE_SCHEMA`,
                `TABLE_NAME`,
                `COLUMN_NAME`,
                `CONSTRAINT_NAME`,
                `REFERENCED_TABLE_SCHEMA`,
                `REFERENCED_TABLE_NAME`,
                `REFERENCED_COLUMN_NAME`
            FROM 
                information_schema.KEY_COLUMN_USAGE 
            WHERE 
                `CONSTRAINT_SCHEMA` LIKE checked_database_name AND
                `TABLE_NAME` LIKE checked_table_name AND
                `REFERENCED_TABLE_SCHEMA` IS NOT NULL;

        DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;

        IF temporary_result_table = 'N' THEN
            DROP TEMPORARY TABLE IF EXISTS INVALID_FOREIGN_KEYS;
            DROP TABLE IF EXISTS INVALID_FOREIGN_KEYS;

            CREATE TABLE INVALID_FOREIGN_KEYS(
                `TABLE_SCHEMA` VARCHAR(64), 
                `TABLE_NAME` VARCHAR(64), 
                `COLUMN_NAME` VARCHAR(64), 
                `CONSTRAINT_NAME` VARCHAR(64),
                `REFERENCED_TABLE_SCHEMA` VARCHAR(64),
                `REFERENCED_TABLE_NAME` VARCHAR(64),
                `REFERENCED_COLUMN_NAME` VARCHAR(64),
                `INVALID_KEY_COUNT` INT,
                `INVALID_KEY_SQL` VARCHAR(1024)
            );
        ELSEIF temporary_result_table = 'Y' THEN
            DROP TEMPORARY TABLE IF EXISTS INVALID_FOREIGN_KEYS;
            DROP TABLE IF EXISTS INVALID_FOREIGN_KEYS;

            CREATE TEMPORARY TABLE INVALID_FOREIGN_KEYS(
                `TABLE_SCHEMA` VARCHAR(64), 
                `TABLE_NAME` VARCHAR(64), 
                `COLUMN_NAME` VARCHAR(64), 
                `CONSTRAINT_NAME` VARCHAR(64),
                `REFERENCED_TABLE_SCHEMA` VARCHAR(64),
                `REFERENCED_TABLE_NAME` VARCHAR(64),
                `REFERENCED_COLUMN_NAME` VARCHAR(64),
                `INVALID_KEY_COUNT` INT,
                `INVALID_KEY_SQL` VARCHAR(1024)
            );
        END IF;


        OPEN foreign_key_cursor;
        foreign_key_cursor_loop: LOOP
            FETCH foreign_key_cursor INTO 
            TABLE_SCHEMA_VAR, 
            TABLE_NAME_VAR, 
            COLUMN_NAME_VAR, 
            CONSTRAINT_NAME_VAR, 
            REFERENCED_TABLE_SCHEMA_VAR, 
            REFERENCED_TABLE_NAME_VAR, 
            REFERENCED_COLUMN_NAME_VAR;
            IF done THEN
                LEAVE foreign_key_cursor_loop;
            END IF;


            SET @from_part = CONCAT('FROM ', '`', TABLE_SCHEMA_VAR, '`.`', TABLE_NAME_VAR, '`', ' AS REFERRING ', 
                 'LEFT JOIN `', REFERENCED_TABLE_SCHEMA_VAR, '`.`', REFERENCED_TABLE_NAME_VAR, '`', ' AS REFERRED ', 
                 'ON (REFERRING', '.`', COLUMN_NAME_VAR, '`', ' = ', 'REFERRED', '.`', REFERENCED_COLUMN_NAME_VAR, '`', ') ', 
                 'WHERE REFERRING', '.`', COLUMN_NAME_VAR, '`', ' IS NOT NULL ',
                 'AND REFERRED', '.`', REFERENCED_COLUMN_NAME_VAR, '`', ' IS NULL');
            SET @full_query = CONCAT('SELECT COUNT(*) ', @from_part, ' INTO @invalid_key_count;');
            PREPARE stmt FROM @full_query;

            EXECUTE stmt;
            IF @invalid_key_count > 0 THEN
                INSERT INTO 
                    INVALID_FOREIGN_KEYS 
                SET 
                    `TABLE_SCHEMA` = TABLE_SCHEMA_VAR, 
                    `TABLE_NAME` = TABLE_NAME_VAR, 
                    `COLUMN_NAME` = COLUMN_NAME_VAR, 
                    `CONSTRAINT_NAME` = CONSTRAINT_NAME_VAR, 
                    `REFERENCED_TABLE_SCHEMA` = REFERENCED_TABLE_SCHEMA_VAR, 
                    `REFERENCED_TABLE_NAME` = REFERENCED_TABLE_NAME_VAR, 
                    `REFERENCED_COLUMN_NAME` = REFERENCED_COLUMN_NAME_VAR, 
                    `INVALID_KEY_COUNT` = @invalid_key_count,
                    `INVALID_KEY_SQL` = CONCAT('SELECT ', 
                        'REFERRING.', '`', COLUMN_NAME_VAR, '` ', 'AS "Invalid: ', COLUMN_NAME_VAR, '", ', 
                        'REFERRING.* ', 
                        @from_part, ';');
            END IF;
            DEALLOCATE PREPARE stmt; 

        END LOOP foreign_key_cursor_loop;
    END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-02-27 18:09:30
