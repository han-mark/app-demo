-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: localhost    Database: appdemo
-- ------------------------------------------------------
-- Server version	8.0.16

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8mb4 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tb_log`
--

DROP TABLE IF EXISTS `tb_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tb_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `operation` varchar(50) NOT NULL COMMENT '操作',
  `method` varchar(100) DEFAULT NULL COMMENT '执行方法',
  `params` varchar(500) DEFAULT NULL COMMENT '请求参数',
  `ip` varchar(64) DEFAULT NULL COMMENT 'ip',
  `create_time` datetime NOT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_log`
--

LOCK TABLES `tb_log` WRITE;
/*!40000 ALTER TABLE `tb_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_menus`
--

DROP TABLE IF EXISTS `tb_menus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tb_menus` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(20) NOT NULL COMMENT '菜单名',
  `icon` varchar(20) DEFAULT NULL COMMENT '图标',
  `href` varchar(100) DEFAULT NULL COMMENT '资源地址',
  `perms` varchar(500) DEFAULT NULL COMMENT '权限',
  `spread` varchar(10) NOT NULL COMMENT 'true：展开，false：不展开',
  `parent_id` bigint(20) NOT NULL COMMENT '父节点',
  `sorting` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=107 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_menus`
--

LOCK TABLES `tb_menus` WRITE;
/*!40000 ALTER TABLE `tb_menus` DISABLE KEYS */;
INSERT INTO `tb_menus` VALUES (1,'首页','&#xe68e;','pages/home.jsp','','false',0,9999),(2,'用户管理','&#xe632;','','','false',0,999),(3,'角色管理','&#xe664;','pages/roles.jsp','','false',2,99),(4,'用户列表','&#xe612;','pages/user.jsp','','false',2,98),(5,'菜单管理','&#xe649;','pages/menus.jsp','','false',2,97),(6,'查看','','','roles:list','false',3,NULL),(7,'新增','','','roles:add','false',3,NULL),(8,'修改','','','roles:update','false',3,NULL),(9,'删除','','','roles:delete','false',3,NULL),(10,'查看','','','user:list','false',4,NULL),(11,'新增','','','user:add','false',4,NULL),(12,'修改','','','user:update','false',4,NULL),(13,'删除','','','user:delete','false',4,NULL),(14,'查看','','','menus:list','false',5,NULL),(15,'新增','','','menus:add','false',5,NULL),(16,'修改','','','menus:update','false',5,NULL),(17,'删除','','','menus:delete','false',5,NULL),(91,'学生列表(默认数据源)','&#xe770;','pages/user_test.jsp','','false',0,50),(92,'查看','','','stu:list','false',91,NULL),(93,'新增','','','stu:add','false',91,NULL),(94,'修改','','','stu:update','false',91,NULL),(95,'删除','','','stu:delete','false',91,NULL),(96,'系统日志','&#xe705;','','','false',0,10),(97,'日志管理','&#xe655;','pages/log.jsp','','false',96,9),(98,'查看','','','log:list','false',97,NULL),(99,'学生列表(数据源1)','&#xe770;','pages/user_test1.jsp','','false',0,49),(100,'查看','','','stu:list1','false',99,NULL),(101,'新增','','','stu:add1','false',99,NULL),(102,'修改','','','stu:update1','false',99,NULL),(103,'删除','','','stu:delete1','false',99,NULL);
/*!40000 ALTER TABLE `tb_menus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_roles`
--

DROP TABLE IF EXISTS `tb_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tb_roles` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色编号',
  `role_name` varchar(50) DEFAULT NULL COMMENT '角色名',
  `role_remark` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_roles`
--

LOCK TABLES `tb_roles` WRITE;
/*!40000 ALTER TABLE `tb_roles` DISABLE KEYS */;
INSERT INTO `tb_roles` VALUES (1,'admin','管理员权限'),(70,'normal','普通用户'),(72,'stu','学生管理'),(73,'admin-readonly','用户管理-仅查看'),(75,'admin-b','用户管理的所有操作&业务的浏览操作');
/*!40000 ALTER TABLE `tb_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_roles_menus`
--

DROP TABLE IF EXISTS `tb_roles_menus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tb_roles_menus` (
  `menu_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`menu_id`,`role_id`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `tb_roles_menus_ibfk_1` FOREIGN KEY (`menu_id`) REFERENCES `tb_menus` (`menu_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tb_roles_menus_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `tb_roles` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_roles_menus`
--

LOCK TABLES `tb_roles_menus` WRITE;
/*!40000 ALTER TABLE `tb_roles_menus` DISABLE KEYS */;
INSERT INTO `tb_roles_menus` VALUES (1,1),(2,1),(3,1),(4,1),(5,1),(6,1),(7,1),(8,1),(9,1),(10,1),(11,1),(12,1),(13,1),(14,1),(15,1),(16,1),(17,1),(91,1),(92,1),(93,1),(94,1),(95,1),(96,1),(97,1),(98,1),(99,1),(100,1),(101,1),(102,1),(103,1),(1,70),(2,70),(3,70),(4,70),(5,70),(6,70),(10,70),(14,70),(91,70),(92,70),(93,70),(94,70),(95,70),(96,70),(97,70),(98,70),(99,70),(100,70),(101,70),(102,70),(103,70),(1,72),(91,72),(92,72),(93,72),(94,72),(95,72),(2,73),(3,73),(4,73),(5,73),(6,73),(10,73),(14,73),(1,75),(2,75),(3,75),(4,75),(5,75),(6,75),(7,75),(8,75),(9,75),(10,75),(11,75),(12,75),(13,75),(14,75),(15,75),(16,75),(17,75),(91,75),(92,75),(96,75),(97,75),(98,75),(99,75),(100,75);
/*!40000 ALTER TABLE `tb_roles_menus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_user`
--

DROP TABLE IF EXISTS `tb_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tb_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '管理员id',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `salt` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `fullname` varchar(50) NOT NULL COMMENT '全名',
  `e_mail` varchar(100) DEFAULT NULL,
  `sex` varchar(1) NOT NULL COMMENT '性别：0女，1男,2保密',
  `birthday` date NOT NULL,
  `address` varchar(100) NOT NULL COMMENT '地址',
  `phone` varchar(20) NOT NULL COMMENT '手机号',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色编号',
  PRIMARY KEY (`id`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `tb_user_ibfk` FOREIGN KEY (`role_id`) REFERENCES `tb_roles` (`role_id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_user`
--

LOCK TABLES `tb_user` WRITE;
/*!40000 ALTER TABLE `tb_user` DISABLE KEYS */;
INSERT INTO `tb_user` VALUES (1,'admin','a66abb5684c45962d887564f08346e8d','admin','管理员','751670441@qq.com','1','2013-03-12','西安','15502986569',1),(27,'cuihui','e13dad0b6548a68a277ecd58d302e282','cuihui','崔辉','123@qq.com','1','2019-08-01','as','11111111111',72),(28,'yangna','fddb458b8df4a49b682a41b821e9fb5b','yangna','杨娜','111@qq.com','2','2019-08-05','123','12322222222',70),(30,'admin-b','036b3cfe393a751b57de1cfd61343a8b','admin-b','管理员b','1@qq.com','2','2019-09-04','qwe','11111111111',75);
/*!40000 ALTER TABLE `tb_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_test`
--

DROP TABLE IF EXISTS `user_test`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user_test` (
  `UUID` varchar(50) NOT NULL,
  `UNAME` varchar(50) DEFAULT NULL,
  `SEX` varchar(50) DEFAULT NULL,
  `AGE` varchar(50) DEFAULT NULL,
  `FLAG` varchar(50) DEFAULT NULL,
  `CREATE_TIME` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_test`
--

LOCK TABLES `user_test` WRITE;
/*!40000 ALTER TABLE `user_test` DISABLE KEYS */;
INSERT INTO `user_test` VALUES ('06cb966b-3acb-4263-99e8-0def64573b9a','崔辉','2','250','0','2019-08-15 00:52:18'),('1ebc0b09-2fb1-4d8f-b227-f5cf56865fb5','张小小','2','340','0','2019-08-15 02:50:38'),('22343bc5-713f-4b9f-8c7c-e19ab80268ba','张晓晓','2','21','0','2019-08-20 02:04:00'),('313c38e9-8388-4fce-8ea1-212ca11b930b','ce1','1','1','1','2019-09-05 08:25:58'),('3ad07d61-67fd-43e2-afe4-f3d43bbc885e','12','2','12','1','2019-08-15 00:52:56'),('4010866c-4406-485e-95b2-2e23467c1d90','测试456','1','456','1','2019-09-03 13:24:44'),('58c33847-919f-4e70-9456-9d58b33c1151','李四','1','22','1','2019-07-19 02:18:49'),('60274fab-a1c0-42d7-b62e-d81f9f29d5ef','1','2','1','0','2019-07-26 09:25:25'),('6143d65a-7940-4be6-a9af-8d427f1d3707','123','2','123','1','2019-09-03 13:21:57'),('6dc19408-1a35-4f89-81d9-a1ac8c2e62fa','cuihui','1','28','0','2019-07-19 02:18:48'),('7970bc8a-55db-4c01-bdb4-b416e0925157',NULL,'1',NULL,'0','2019-08-07 08:05:03'),('8d3076ec-879f-4aff-ab16-647c602686a5','测试','1','12','1','2019-09-02 12:07:37'),('8fb3c193-e10c-44f3-8a39-78967e3eb92a','张小明','2','24','1','2019-07-19 02:18:48'),('930b0b98-d8dd-4db7-a4c1-0ad7ad00af37','11111','2','11111','1','2019-09-03 13:32:14'),('a1ce8855-371c-416e-982d-9fa0a3ec5956','张巧转','2','23','1','2019-07-19 02:18:48'),('b39fe0c2-5c31-4dd8-ae9c-a6f181b2d3ec','111','2','111','1','2019-08-15 02:51:00'),('b9e69e3a-4118-4318-853f-771d44b8c231',NULL,'1',NULL,'0','2019-08-07 07:20:29'),('cc1a4346-6b43-4f8f-aac2-9f8ec114152c','杨娜','2','28','0','2019-07-19 02:18:48'),('d1e11692-fb64-4d79-8a87-ab6083b520df','12','2','12','1','2019-09-03 13:21:09'),('d6f0114d-4ced-4e0d-999e-673047c97e88','杨小历','1','33','0','2019-07-19 02:18:49'),('df65c7ff-5e05-45d7-853f-cf496fc279c6','asd','1','44','0','2019-07-19 08:48:38'),('e59df1d4-c31c-4d51-a31f-a89deb01c465','123','2','123','0','2019-07-19 08:52:30'),('ebb14f47-c587-4a3d-b7dc-88d2c9507360','转转','2','23','0','2019-07-19 02:18:49'),('ed514bb7-0396-42c8-aa9c-24571cb6dcbe','默认','2','123','1','2019-08-23 11:53:04'),('f413935f-8905-499a-912a-c493b58931f2','江鱼儿','2','34','0','2019-07-19 02:18:48'),('faf8bdc7-0f9b-44ce-809b-2d1f5cbc7674','asd','2','123','1','2019-09-05 07:59:14'),('ff1010f9-74ce-43e5-b28a-005081871fbf','学生默认','1','12','0','2019-09-05 08:26:45');
/*!40000 ALTER TABLE `user_test` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-09-07 16:56:33
