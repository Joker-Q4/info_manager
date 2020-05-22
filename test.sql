-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: test
-- ------------------------------------------------------
-- Server version	8.0.19

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
-- Table structure for table `entrepreneur`
--

DROP TABLE IF EXISTS `entrepreneur`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `entrepreneur` (
  `account` int NOT NULL,
  `name` varchar(10) NOT NULL,
  `password` varchar(20) NOT NULL,
  `sex` varchar(4) NOT NULL,
  `phone_number` varchar(15) NOT NULL,
  `address` varchar(100) DEFAULT NULL,
  `resume` longtext,
  `current_state` int NOT NULL DEFAULT '3' COMMENT '0申请中；1已拒绝；2已通过',
  `teacher_account` int DEFAULT NULL,
  PRIMARY KEY (`account`),
  KEY `entrepreneur_teacher_account_fk` (`teacher_account`),
  CONSTRAINT `entrepreneur_teacher_account_fk` FOREIGN KEY (`teacher_account`) REFERENCES `teacher` (`account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='创业者';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entrepreneur`
--

LOCK TABLES `entrepreneur` WRITE;
/*!40000 ALTER TABLE `entrepreneur` DISABLE KEYS */;
INSERT INTO `entrepreneur` VALUES (1001,'张三','123456','女','11011113212','辽宁省沈阳市','简历....',2,102),(1002,'李四','987987','男','13898987878','辽宁','简历',3,102),(1003,'王二','123546','女','13888888888','北京市','北京市11',3,NULL),(1004,'淘气','123456','男','13798987878','999','888',3,NULL),(1005,'麻子','987987','男','13698789856','上海市','狂躁',3,NULL);
/*!40000 ALTER TABLE `entrepreneur` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manager`
--

DROP TABLE IF EXISTS `manager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `manager` (
  `account` int NOT NULL,
  `name` varchar(10) NOT NULL,
  `password` varchar(20) DEFAULT NULL,
  `sex` varchar(4) NOT NULL,
  `phone_number` varchar(15) NOT NULL,
  `address` varchar(100) DEFAULT NULL,
  `resume` longtext,
  PRIMARY KEY (`account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='管理员';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manager`
--

LOCK TABLES `manager` WRITE;
/*!40000 ALTER TABLE `manager` DISABLE KEYS */;
INSERT INTO `manager` VALUES (11,'天王老子','123456','女','13845561111','天上人间地狱11','我来自外太空11');
/*!40000 ALTER TABLE `manager` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notice`
--

DROP TABLE IF EXISTS `notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notice` (
  `id` int NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `is_show` int DEFAULT '1' COMMENT '0为不显示',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='公告表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notice`
--

LOCK TABLES `notice` WRITE;
/*!40000 ALTER TABLE `notice` DISABLE KEYS */;
INSERT INTO `notice` VALUES (0,'987654321',0);
/*!40000 ALTER TABLE `notice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `project` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `content` longtext,
  `funds` decimal(6,2) DEFAULT NULL,
  `manager_state` int DEFAULT '0' COMMENT '0待审批；1退回；2通过',
  `manager_back` varchar(100) DEFAULT '',
  `teacher_state` int NOT NULL DEFAULT '0',
  `teacher_back` varchar(100) DEFAULT '',
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  `entrepreneur_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `project_entrepreneur_account_fk` (`entrepreneur_id`),
  CONSTRAINT `project_entrepreneur_account_fk` FOREIGN KEY (`entrepreneur_id`) REFERENCES `entrepreneur` (`account`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='项目';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
INSERT INTO `project` VALUES (1,'大西北支援计划','支援一天11',1000.00,2,'123',1,'321','2020-03-07 11:41:10','2020-03-15 01:51:05',1001),(2,'武汉支援','支援两个月',1100.00,2,'123',1,'','2020-03-08 07:56:16','2020-03-15 01:51:01',1001),(4,'123','QQWEV',456.00,1,'123',2,'','2020-03-09 19:55:16','2020-03-15 01:34:52',1001),(5,'897978','546546546',8976.00,1,'13',2,'321','2020-03-09 19:58:04','2020-03-15 01:35:00',1001),(6,'45645','45345345',45.00,1,'6846',1,'','2020-03-09 20:07:16','2020-03-15 22:49:19',1001),(7,'123','46554',3123.00,1,'5645',2,'','2020-03-10 01:54:49','2020-03-15 22:49:21',1001),(8,'89786','4656456456',456.00,2,'',0,'','2020-03-10 02:20:48','2020-03-15 22:49:08',1001),(9,'223','5345542312',3543.00,0,'',0,'','2020-03-13 05:53:39',NULL,1002),(10,'老工业复兴计划11','东北老工业复兴没指望了',2000.00,1,'89',0,'','2020-03-15 22:34:09','2020-03-15 22:49:23',1002),(11,'肺炎支援11','去武汉支援11',2000.00,2,'',0,'','2020-03-15 22:41:12','2020-03-15 22:42:54',1003);
/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teacher` (
  `account` int NOT NULL,
  `name` varchar(10) NOT NULL,
  `password` varchar(20) NOT NULL,
  `sex` varchar(4) NOT NULL,
  `phone_number` varchar(15) NOT NULL,
  `address` varchar(100) NOT NULL,
  `resume` longtext,
  PRIMARY KEY (`account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='指导教师';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher`
--

LOCK TABLES `teacher` WRITE;
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
INSERT INTO `teacher` VALUES (101,'张艺谋','123456','男','13812349878','沈阳市..','老师简历........'),(102,'冯小刚','123456','女','13798986565','沈阳','123'),(103,'胡歌','123456','女','13897974646','12313','5645'),(104,'霍尊','321654','男','13745678932','广东','请忽略333...');
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-03-16 18:02:03
