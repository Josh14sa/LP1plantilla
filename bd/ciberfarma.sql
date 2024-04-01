CREATE DATABASE  IF NOT EXISTS `ciberfarma` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `ciberfarma`;
-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: ciberfarma
-- ------------------------------------------------------
-- Server version	8.0.32

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
-- Table structure for table `tb_cab_boleta`
--

DROP TABLE IF EXISTS `tb_cab_boleta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_cab_boleta` (
  `num_bol` char(5) NOT NULL,
  `fch_bol` date DEFAULT NULL,
  `cod_cliente` int NOT NULL,
  `cod_vendedor` int NOT NULL,
  `total_bol` decimal(8,2) DEFAULT NULL,
  PRIMARY KEY (`num_bol`),
  KEY `cod_cliente` (`cod_cliente`),
  KEY `cod_vendedor` (`cod_vendedor`),
  CONSTRAINT `tb_cab_boleta_ibfk_1` FOREIGN KEY (`cod_cliente`) REFERENCES `tb_usuarios` (`codigo`),
  CONSTRAINT `tb_cab_boleta_ibfk_2` FOREIGN KEY (`cod_vendedor`) REFERENCES `tb_usuarios` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_cab_boleta`
--

LOCK TABLES `tb_cab_boleta` WRITE;
/*!40000 ALTER TABLE `tb_cab_boleta` DISABLE KEYS */;
INSERT INTO `tb_cab_boleta` VALUES ('B0001','2023-03-17',1,10,7.50);
/*!40000 ALTER TABLE `tb_cab_boleta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_categorias`
--

DROP TABLE IF EXISTS `tb_categorias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_categorias` (
  `idtipo` int NOT NULL,
  `descripcion` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idtipo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_categorias`
--

LOCK TABLES `tb_categorias` WRITE;
/*!40000 ALTER TABLE `tb_categorias` DISABLE KEYS */;
INSERT INTO `tb_categorias` VALUES (1,'Pastillas'),(2,'Jarabe'),(3,'Cremas'),(4,'Tocador'),(5,'Cuidado'),(6,'Otros');
/*!40000 ALTER TABLE `tb_categorias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_det_boleta`
--

DROP TABLE IF EXISTS `tb_det_boleta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_det_boleta` (
  `num_bol` char(5) NOT NULL,
  `idprod` char(5) NOT NULL,
  `cantidad` int DEFAULT NULL,
  `preciovta` decimal(8,2) DEFAULT NULL,
  `importe` decimal(8,2) DEFAULT NULL,
  PRIMARY KEY (`num_bol`,`idprod`),
  KEY `idprod` (`idprod`),
  CONSTRAINT `tb_det_boleta_ibfk_1` FOREIGN KEY (`num_bol`) REFERENCES `tb_cab_boleta` (`num_bol`),
  CONSTRAINT `tb_det_boleta_ibfk_2` FOREIGN KEY (`idprod`) REFERENCES `tb_productos` (`idprod`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_det_boleta`
--

LOCK TABLES `tb_det_boleta` WRITE;
/*!40000 ALTER TABLE `tb_det_boleta` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_det_boleta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_productos`
--

DROP TABLE IF EXISTS `tb_productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_productos` (
  `idprod` char(5) NOT NULL,
  `descripcion` varchar(45) DEFAULT NULL,
  `stock` int DEFAULT NULL,
  `precio` decimal(8,2) DEFAULT NULL,
  `idtipo` int DEFAULT NULL,
  `estado` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`idprod`),
  KEY `idtipo` (`idtipo`),
  CONSTRAINT `tb_productos_ibfk_1` FOREIGN KEY (`idtipo`) REFERENCES `tb_categorias` (`idtipo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_productos`
--

LOCK TABLES `tb_productos` WRITE;
/*!40000 ALTER TABLE `tb_productos` DISABLE KEYS */;
INSERT INTO `tb_productos` VALUES ('P0001','Panadol cj 10',20,1.85,1,1),('P0002','Curitas unidad',100,1.00,3,1),('P0003','Kita tos',80,15.00,2,1),('P0004','Achiz',120,1.00,1,1),('P0005','Jaboncillo cj',120,1.00,3,1),('P0006','Termometro',80,2.80,5,1),('P0007','Panadol jarabe pq',40,10.50,2,1),('P0008','Antalgina',55,1.80,1,1),('P0009','Ibuprofeno',60,15.00,4,1),('P0010','Mejoralito NiÃ±os',10,1.50,1,1),('P0011','Panadol jarabe',10,1.50,2,1),('P0012','Jabon Neko',40,8.50,4,1),('P0013','Pañales x 24u',10,1.50,5,1),('P0014','Mejoralito Forte',10,1.50,1,1),('P0015','Champu Amigo',50,0.99,5,1),('P0016','Mejoralito',10,1.50,4,1),('P0017','SinDolor',23,1.50,6,1),('P0018','Mejoralito Forte',10,1.50,2,1),('P0019','Mejoralito Forte',10,1.50,1,1),('P0020','Mejoralito Forte',10,1.50,3,1),('P0021','Mejoralito Forte',10,1.50,2,1),('P0022','Mejoralito Forte',10,1.50,1,1),('P0023','Mejoralito Forte',10,1.50,1,1),('P0024','Mejoralito Forte',10,1.50,2,1),('P0025','Mejoralito Forte',10,1.50,1,1),('P0026','Mejoralito Forte',10,1.50,3,1),('P0027','Mejoralito Forte',10,1.50,1,1),('P0028','Mejoralito Forte',10,1.50,4,1),('P0029','Mejoralito Forte',10,1.50,1,1),('P0030','Mejoralito Forte',10,1.50,5,1),('P0031','Mejoralito UForte',10,0.99,5,1);
/*!40000 ALTER TABLE `tb_productos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_tipos`
--

DROP TABLE IF EXISTS `tb_tipos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_tipos` (
  `id_tipo` int NOT NULL,
  `des_tipo` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id_tipo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_tipos`
--

LOCK TABLES `tb_tipos` WRITE;
/*!40000 ALTER TABLE `tb_tipos` DISABLE KEYS */;
INSERT INTO `tb_tipos` VALUES (1,'Administrador'),(2,'Cliente'),(3,'Cajero');
/*!40000 ALTER TABLE `tb_tipos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_usuarios`
--

DROP TABLE IF EXISTS `tb_usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_usuarios` (
  `codigo` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(15) DEFAULT NULL,
  `apellido` varchar(25) DEFAULT NULL,
  `usuario` char(4) NOT NULL,
  `clave` char(5) DEFAULT NULL,
  `fnacim` date DEFAULT NULL,
  `tipo` int DEFAULT '2',
  `estado` int DEFAULT '1',
  PRIMARY KEY (`codigo`),
  KEY `tipo` (`tipo`),
  CONSTRAINT `tb_usuarios_ibfk_1` FOREIGN KEY (`tipo`) REFERENCES `tb_tipos` (`id_tipo`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_usuarios`
--

LOCK TABLES `tb_usuarios` WRITE;
/*!40000 ALTER TABLE `tb_usuarios` DISABLE KEYS */;
INSERT INTO `tb_usuarios` VALUES (1,'Tito','Siber','U001','10001','2023-03-17',2,1),(2,'Zoila','Baca','U002','10002','2023-03-17',2,1),(3,'Pedro','Navaja','C001','10001','2023-03-17',3,1),(10,'Jose','Atuncar','ADMI','ADMIN','2023-03-17',1,1),(11,'Marce','Odebrech','CAJA','CAJA2','2023-03-17',3,2);
/*!40000 ALTER TABLE `tb_usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'ciberfarma'
--

--
-- Dumping routines for database 'ciberfarma'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-03-17 19:46:35
