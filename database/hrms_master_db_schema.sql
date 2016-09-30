-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: hrms
-- ------------------------------------------------------
-- Server version	5.0.96-community-nt

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
-- Not dumping tablespaces as no INFORMATION_SCHEMA.FILES table on this server
--

--
-- Table structure for table `client_history`
--

DROP TABLE IF EXISTS `client_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `client_history` (
  `client_history_id` int(11) NOT NULL auto_increment,
  `client_id` int(11) NOT NULL,
  `client_sub_domain_name` varchar(120) NOT NULL,
  `status` varchar(20) NOT NULL,
  `status_date` datetime default NULL,
  PRIMARY KEY  (`client_history_id`)
) ENGINE=MyISAM AUTO_INCREMENT=590 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client_history`
--

LOCK TABLES `client_history` WRITE;
/*!40000 ALTER TABLE `client_history` DISABLE KEYS */;
/*!40000 ALTER TABLE `client_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client_info_bkup`
--

DROP TABLE IF EXISTS `client_info_bkup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `client_info_bkup` (
  `fid` int(11) NOT NULL default '0',
  `firstname` varchar(50) NOT NULL,
  `lastname` varchar(50) NOT NULL,
  `jobtitle` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `companyname` varchar(255) NOT NULL,
  `shortname` varchar(255) NOT NULL,
  `city` varchar(100) NOT NULL,
  `state` varchar(100) NOT NULL,
  `country` varchar(100) NOT NULL,
  `contactnumber` int(10) NOT NULL,
  `emailid` varchar(255) NOT NULL,
  `website` varchar(255) NOT NULL,
  `employees` int(10) NOT NULL,
  `knowus` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `status` enum('open','closed') NOT NULL default 'open',
  `problem` longtext NOT NULL,
  `addedon` timestamp NOT NULL default '0000-00-00 00:00:00'
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client_info_bkup`
--

LOCK TABLES `client_info_bkup` WRITE;
/*!40000 ALTER TABLE `client_info_bkup` DISABLE KEYS */;
/*!40000 ALTER TABLE `client_info_bkup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client_information`
--

DROP TABLE IF EXISTS `client_information`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `client_information` (
  `Client_id` int(11) NOT NULL auto_increment,
  `Client_name` varchar(120) NOT NULL,
  `Client_sub_domain_name` varchar(120) NOT NULL,
  `Client_db_path` varchar(120) NOT NULL,
  `Client_db_user_name` varchar(120) NOT NULL,
  `Client_db_password` varchar(120) NOT NULL,
  `User_type` varchar(120) NOT NULL,
  `IsActive` smallint(1) NOT NULL,
  `Created` datetime NOT NULL,
  `Last_login` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `Allocated_Space` varchar(20) default NULL,
  `Utilized_Space` varchar(20) default NULL,
  PRIMARY KEY  (`Client_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client_information`
--

LOCK TABLES `client_information` WRITE;
/*!40000 ALTER TABLE `client_information` DISABLE KEYS */;
INSERT INTO `client_information` VALUES (1,'hrms','hrms','rlite','rlite','12345','free_user',1,'2012-04-12 12:01:54','2016-05-28 12:46:28','20','0.00');
/*!40000 ALTER TABLE `client_information` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client_status`
--

DROP TABLE IF EXISTS `client_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `client_status` (
  `client_status_id` int(11) NOT NULL auto_increment,
  `client_id` int(11) NOT NULL,
  `client_name` varchar(120) NOT NULL,
  `client_sub_domain_name` varchar(120) NOT NULL,
  `client_email` varchar(120) NOT NULL,
  `client_status` varchar(50) default NULL,
  `last_status_change` datetime default NULL,
  `signup_fid` int(11) default NULL,
  `client_table` varchar(1) default NULL,
  `subscription_start` datetime default NULL,
  `subscription_end` datetime default NULL,
  PRIMARY KEY  (`client_status_id`)
) ENGINE=MyISAM AUTO_INCREMENT=156 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client_status`
--

LOCK TABLES `client_status` WRITE;
/*!40000 ALTER TABLE `client_status` DISABLE KEYS */;
/*!40000 ALTER TABLE `client_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_answer`
--

DROP TABLE IF EXISTS `hcmo_answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_answer` (
  `HcmoAnswerId` int(11) NOT NULL auto_increment,
  `HcmoQuestionGeneralInfoGroupId` int(11) default NULL,
  `HcmoQuestionGroupNameId` int(11) default NULL,
  `Answer` varchar(100) default NULL,
  `Status` varchar(100) default NULL,
  `AppraiserEmployeeId` int(11) default NULL,
  `AppraisingEmployeeId` int(11) default NULL,
  `Created` date default NULL,
  `CreatedBy` int(11) default NULL,
  `Updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) default NULL,
  `IsActive` tinyint(1) default '1',
  `hcmoclientid` int(11) default NULL,
  PRIMARY KEY  (`HcmoAnswerId`),
  KEY `fk_hcmo_answer_updatedby` (`UpdatedBy`),
  KEY `fk_hcmo_answer_createdby` (`CreatedBy`),
  KEY `fk_hcmo_answer_hcmoQuestionGroupNameId` (`HcmoQuestionGroupNameId`),
  KEY `fk_hcmo_answer_appraiseremployeeid` (`AppraiserEmployeeId`),
  KEY `fk_hcmo_answer_hcmoquestiongeneralinfogroupid` (`HcmoQuestionGeneralInfoGroupId`),
  KEY `fk_hcmo_answer_appraisingemployeeid` (`AppraisingEmployeeId`),
  CONSTRAINT `fk_hcmo_answer_appraiseremployeeid` FOREIGN KEY (`AppraiserEmployeeId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_answer_appraisingemployeeid` FOREIGN KEY (`AppraisingEmployeeId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_answer_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_answer_hcmoquestiongeneralinfogroupid` FOREIGN KEY (`HcmoQuestionGeneralInfoGroupId`) REFERENCES `hcmo_question_general_info_group` (`HcmoQuestionGeneralInfoGroupId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_answer_hcmoQuestionGroupNameId` FOREIGN KEY (`HcmoQuestionGroupNameId`) REFERENCES `hcmo_question_group_name` (`HcmoQuestionGroupNameId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_answer_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_answer`
--

LOCK TABLES `hcmo_answer` WRITE;
/*!40000 ALTER TABLE `hcmo_answer` DISABLE KEYS */;
/*!40000 ALTER TABLE `hcmo_answer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_assets`
--

DROP TABLE IF EXISTS `hcmo_assets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_assets` (
  `AssetTypeId` int(11) NOT NULL,
  `AssetTypeName` varchar(100) default NULL,
  `created` date default NULL,
  `CreatedBy` int(11) default NULL,
  `updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) default NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  PRIMARY KEY  (`AssetTypeId`),
  KEY `fk_hcmo_assets_updatedby` (`UpdatedBy`),
  KEY `fk_hcmo_assets_createdby` (`CreatedBy`),
  CONSTRAINT `fk_hcmo_assets_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_assets_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_assets`
--

LOCK TABLES `hcmo_assets` WRITE;
/*!40000 ALTER TABLE `hcmo_assets` DISABLE KEYS */;
/*!40000 ALTER TABLE `hcmo_assets` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_benefits`
--

DROP TABLE IF EXISTS `hcmo_benefits`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_benefits` (
  `HcmoBenefitsId` int(11) NOT NULL auto_increment,
  `Year` int(11) default NULL,
  `BenefitsName` varchar(50) default NULL,
  `BenefitsAttachFileName` varchar(100) default NULL,
  `EmployeeId` varchar(255) default NULL,
  `EmployeeEmailId` text,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  `hcmoclientid` int(11) default NULL,
  PRIMARY KEY  (`HcmoBenefitsId`),
  KEY `fk_hcmo_benefits_updatedby` (`UpdatedBy`),
  KEY `fk_hcmo_benefits_createdby` (`CreatedBy`),
  CONSTRAINT `fk_hcmo_benefits_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_benefits_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_benefits`
--

LOCK TABLES `hcmo_benefits` WRITE;
/*!40000 ALTER TABLE `hcmo_benefits` DISABLE KEYS */;
/*!40000 ALTER TABLE `hcmo_benefits` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_benefitsplan`
--

DROP TABLE IF EXISTS `hcmo_benefitsplan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_benefitsplan` (
  `HcmoBenefitsPlanId` int(11) NOT NULL auto_increment,
  `hcmoclientid` int(11) default NULL,
  `EmployeeContribution` double default NULL,
  `EmployeerContribution` double default NULL,
  `PreTax` double default NULL,
  `UpperLimit` double default NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  PRIMARY KEY  (`HcmoBenefitsPlanId`),
  KEY `fk_hcmo_benefitsplan_updatedby` (`UpdatedBy`),
  KEY `fk_hcmo_benefitsplan_createdby` (`CreatedBy`),
  CONSTRAINT `fk_hcmo_benefitsplan_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_benefitsplan_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_benefitsplan`
--

LOCK TABLES `hcmo_benefitsplan` WRITE;
/*!40000 ALTER TABLE `hcmo_benefitsplan` DISABLE KEYS */;
/*!40000 ALTER TABLE `hcmo_benefitsplan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_benefitstype`
--

DROP TABLE IF EXISTS `hcmo_benefitstype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_benefitstype` (
  `HcmoBenefitsTypeId` int(11) NOT NULL auto_increment,
  `hcmoclientid` int(11) default NULL,
  `BenefitsType` varchar(20) default NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  PRIMARY KEY  (`HcmoBenefitsTypeId`),
  KEY `fk_hcmo_benefitstype_updatedby` (`UpdatedBy`),
  KEY `fk_hcmo_benefitstype_createdby` (`CreatedBy`),
  CONSTRAINT `fk_hcmo_benefitstype_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_benefitstype_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_benefitstype`
--

LOCK TABLES `hcmo_benefitstype` WRITE;
/*!40000 ALTER TABLE `hcmo_benefitstype` DISABLE KEYS */;
/*!40000 ALTER TABLE `hcmo_benefitstype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_billing_info`
--

DROP TABLE IF EXISTS `hcmo_billing_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_billing_info` (
  `hcmobillingid` int(11) NOT NULL auto_increment,
  `contact_id` int(11) default NULL,
  `address_line1` varchar(500) default NULL,
  `address_line2` varchar(500) default NULL,
  `city` varchar(150) default NULL,
  `state` varchar(150) default NULL,
  `country_id` int(11) default NULL,
  `phone_no` varchar(50) default NULL,
  `paymnt_type` int(11) default NULL COMMENT '1-Paypal,2-Google,3-card',
  `entry_datetime` datetime default NULL,
  `mody_datetime` timestamp NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`hcmobillingid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_billing_info`
--

LOCK TABLES `hcmo_billing_info` WRITE;
/*!40000 ALTER TABLE `hcmo_billing_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `hcmo_billing_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_category`
--

DROP TABLE IF EXISTS `hcmo_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_category` (
  `HcmoCategoryId` int(11) NOT NULL auto_increment,
  `hcmoclientid` int(11) default NULL,
  `CategoryName` varchar(100) default NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  PRIMARY KEY  (`HcmoCategoryId`),
  UNIQUE KEY `Name` (`CategoryName`,`IsActive`),
  KEY `fk_hcmo_category_updatedby` (`UpdatedBy`),
  KEY `fk_hcmo_category_createdby` (`CreatedBy`),
  CONSTRAINT `fk_hcmo_category_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_category_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_category`
--

LOCK TABLES `hcmo_category` WRITE;
/*!40000 ALTER TABLE `hcmo_category` DISABLE KEYS */;
/*!40000 ALTER TABLE `hcmo_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_client`
--

DROP TABLE IF EXISTS `hcmo_client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_client` (
  `HcmoClientId` int(11) NOT NULL auto_increment,
  `HcmoCountryId` int(11) default NULL,
  `CompanyName` varchar(60) default NULL,
  `NoOfEmployee` int(11) default NULL,
  `NoOfBranch` int(11) default NULL,
  `LogoName` varchar(100) default NULL,
  `Address1` varchar(60) default NULL,
  `Address2` varchar(60) default NULL,
  `ZipCode` varchar(20) default NULL,
  `Region` varchar(60) default NULL,
  `City` varchar(100) default NULL,
  `Phone` varchar(20) default NULL,
  `TaxId` varchar(30) default NULL,
  `Fax` varchar(20) default NULL,
  `Comments` text,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  PRIMARY KEY  (`HcmoClientId`),
  UNIQUE KEY `CompanyName` (`CompanyName`,`IsActive`),
  UNIQUE KEY `TaxId` (`TaxId`,`IsActive`),
  UNIQUE KEY `ClientPhone` (`Phone`,`IsActive`),
  UNIQUE KEY `ClientFax` (`Fax`,`IsActive`),
  KEY `fk_hcmo_client_updatedby` (`UpdatedBy`),
  KEY `fk_hcmo_client_createdby` (`CreatedBy`),
  KEY `fk_hcmo_client_hcmocountryid` (`HcmoCountryId`),
  CONSTRAINT `fk_hcmo_client_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_client_hcmocountryid` FOREIGN KEY (`HcmoCountryId`) REFERENCES `hcmo_country` (`HcmoCountryId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_client_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_client`
--

LOCK TABLES `hcmo_client` WRITE;
/*!40000 ALTER TABLE `hcmo_client` DISABLE KEYS */;
/*!40000 ALTER TABLE `hcmo_client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_client_reg`
--

DROP TABLE IF EXISTS `hcmo_client_reg`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_client_reg` (
  `hcmoclientregid` int(11) NOT NULL auto_increment,
  `hcmoclientid` int(11) default NULL,
  `contact_firstname` varchar(100) default NULL,
  `contact_lastname` varchar(100) default NULL,
  `contact_mail` varchar(50) default NULL,
  `companyName` varchar(50) default NULL,
  `contact_address` varchar(100) default NULL,
  `contact_addressoptional` varchar(100) default NULL,
  `contactPhoneno` varchar(50) default NULL,
  `adminuserid` varchar(50) default NULL,
  `password` varchar(50) default NULL,
  `Entry_datetime` date default NULL,
  `UpdatedDate` timestamp NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `status` int(2) default NULL,
  `activationKey` varchar(255) default NULL,
  `moduleId` int(2) default NULL,
  PRIMARY KEY  (`hcmoclientregid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_client_reg`
--

LOCK TABLES `hcmo_client_reg` WRITE;
/*!40000 ALTER TABLE `hcmo_client_reg` DISABLE KEYS */;
INSERT INTO `hcmo_client_reg` VALUES (1,NULL,'Client1','l','client@abc.com','client1 company','client1 address','','99999999','client@abc.com','12345','2016-05-30',NULL,0,NULL,NULL),(2,NULL,'ajmal','saifan','aju.saifan@gmail.com','gq',NULL,NULL,'9876543210',NULL,'we1c@me321','2016-06-03','2016-06-03 10:18:41',0,'blxdfI+pNmU=',2),(3,NULL,'sdfs','sdfsd','aju.saifan@gmail.com','dfsd',NULL,NULL,'213123123',NULL,'233','2016-06-03','2016-06-03 10:26:02',1,'Nk8FY7PWbsQ=',0),(4,NULL,'Pandi','rajan','aju.saifan@gmail.com','gq',NULL,NULL,'9876543121',NULL,'5652','2016-06-03','2016-06-03 10:32:01',1,'rtE1lQ/uGuA=',3),(5,NULL,'anu','sds','aju.saifan@gmail.com','dd',NULL,NULL,'32123',NULL,'112','2016-06-03','2016-06-03 12:03:33',0,'8b+t1a2Qm+w=',1);
/*!40000 ALTER TABLE `hcmo_client_reg` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_clientreg`
--

DROP TABLE IF EXISTS `hcmo_clientreg`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_clientreg` (
  `hcmoClientregid` int(11) NOT NULL auto_increment,
  `hcmoclientid` int(11) default NULL,
  `First_name` varchar(50) default NULL,
  `Last_name` varchar(50) default NULL,
  `Email_id` varchar(100) default NULL,
  `Password` varchar(40) default NULL,
  `Compny_name` varchar(50) default NULL,
  `Phone_no` varchar(15) default NULL,
  `Address_line1` varchar(500) default NULL,
  `Address_line2` varchar(500) default NULL,
  `City` varchar(70) default NULL,
  `State_id` int(11) default NULL,
  `Zipcode` varchar(10) default NULL,
  `Country_id` int(11) default NULL,
  `Entry_datetime` datetime default NULL,
  `status` int(2) NOT NULL default '1',
  PRIMARY KEY  (`hcmoClientregid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_clientreg`
--

LOCK TABLES `hcmo_clientreg` WRITE;
/*!40000 ALTER TABLE `hcmo_clientreg` DISABLE KEYS */;
/*!40000 ALTER TABLE `hcmo_clientreg` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_configuration`
--

DROP TABLE IF EXISTS `hcmo_configuration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_configuration` (
  `HcmoConfigurationId` int(11) NOT NULL auto_increment,
  `hcmoclientid` int(11) default NULL,
  `MailConfiguration` tinyint(1) default '0',
  `Client` tinyint(1) default '0',
  `Location` tinyint(1) default '0',
  `Region` tinyint(1) default '0',
  `SalaryGrade` tinyint(1) default '0',
  `JobTitle` tinyint(1) default '0',
  `Department` tinyint(1) default '0',
  `Team` tinyint(1) default '0',
  `Nationality` tinyint(1) default '0',
  `EthnicRace` tinyint(1) default '0',
  `Employee` tinyint(1) default '0',
  `Skip` tinyint(1) default '0',
  `Status` varchar(100) default NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  PRIMARY KEY  (`HcmoConfigurationId`),
  KEY `fk_hcmo_configuration_updatedby` (`UpdatedBy`),
  KEY `fk_hcmo_configuration_createdby` (`CreatedBy`),
  CONSTRAINT `fk_hcmo_configuration_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_configuration_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_configuration`
--

LOCK TABLES `hcmo_configuration` WRITE;
/*!40000 ALTER TABLE `hcmo_configuration` DISABLE KEYS */;
INSERT INTO `hcmo_configuration` VALUES (1,1,1,1,1,1,1,1,1,1,1,1,1,0,'skippedpermanantely','2016-05-30',1,'2016-06-03 06:37:53',1,1);
/*!40000 ALTER TABLE `hcmo_configuration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_country`
--

DROP TABLE IF EXISTS `hcmo_country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_country` (
  `HcmoCountryId` int(11) NOT NULL auto_increment,
  `Name` varchar(100) default NULL,
  `Description` text,
  `CountryCode` varchar(60) default NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  `hcmoclientid` int(11) default NULL,
  PRIMARY KEY  (`HcmoCountryId`),
  UNIQUE KEY `Name` (`Name`,`IsActive`),
  UNIQUE KEY `CountryCode` (`CountryCode`,`IsActive`)
) ENGINE=InnoDB AUTO_INCREMENT=256 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_country`
--

LOCK TABLES `hcmo_country` WRITE;
/*!40000 ALTER TABLE `hcmo_country` DISABLE KEYS */;
INSERT INTO `hcmo_country` VALUES (29,'United States of America','','1',1,1),(31,'Egypt (Arab Republic of)','','20',1,1),(32,'Morocco (Kingdom of)','','212',1,1),(33,'Algeria (People\'s Democratic Republic of)','','213',1,1),(34,'Tunisia','','216',1,1),(35,'Libya (Socialist People\'s Libyan Arab Jamahiriya)','','218',1,1),(36,'Gambia (Republic of the)','','220',1,1),(37,'Senegal (Republic of)','','221',1,1),(38,'Mauritania (Islamic Republic of)','','222',1,1),(39,'Mali (Republic of)','','223',1,1),(40,'Guinea (Republic of)','','224',1,1),(41,'CÃ´te d\'Ivoire (Republic of)','','225',1,1),(42,'Burkina Faso','','226',1,1),(43,'Niger (Republic of the)','','227',1,1),(44,'Togolese Republic','','228',1,1),(45,'Benin (Republic of)','','229',1,1),(46,'Mauritius (Republic of)','','230',1,1),(47,'Liberia (Republic of)','Liberia (Republic of)','231',1,1),(48,'Sierra Leone','Sierra Leone','232',1,1),(49,'Ghana','','233',1,1),(50,'Nigeria (Federal Republic of)','','234',1,1),(51,'Chad (Republic of)','','235',1,1),(52,'Central African Republic','','236',1,1),(53,'Cameroon (Republic of)','','237',1,1),(54,'Cape Verde (Republic of)','Cape Verde (Republic of)','238',1,1),(55,'Sao Tome and Principe (Democratic Republic of)','Sao Tome and Principe (Democratic Republic of)','239',1,1),(56,'Equatorial Guinea (Republic of)','Equatorial Guinea (Republic of)','240',1,1),(57,'Gabonese Republic','','241',1,1),(58,'Congo (Republic of the)','','242',1,1),(59,'Democratic Republic of the Congo','','243',1,1),(60,'Angola (Republic of)','Angola (Republic of)','244',1,1),(61,'Guinea-Bissau (Republic of)','','245',1,1),(62,'Diego Garcia','','246',1,1),(63,'Ascension','Ascension','247',1,1),(64,'Seychelles (Republic of)','Seychelles (Republic of)','248',1,1),(65,'Sudan (Republic of the)','Sudan (Republic of the)','249',1,1),(66,'Rwanda (Republic of)','','250',1,1),(67,'Ethiopia (Federal Democratic Republic of)','','251',1,1),(68,'Somali Democratic Republic','','252',1,1),(69,'Djibouti (Republic of)','Djibouti (Republic of)','253',1,1),(70,'Kenya (Republic of)','','254',1,1),(71,'Tanzania (United Republic of)','Tanzania (United Republic of)','255',1,1),(72,'Uganda (Republic of)','','256',1,1),(73,'Burundi (Republic of)','','257',1,1),(74,'Mozambique (Republic of)','','258',1,1),(75,'Zambia (Republic of)','Zambia (Republic of)','260',1,1),(76,'Madagascar (Republic of)','','261',1,1),(77,'Reunion (French Department of)','','262',1,1),(78,'Zimbabwe (Republic of)','Zimbabwe (Republic of)','263',1,1),(79,'Namibia (Republic of)','Namibia (Republic of)','264',1,1),(80,'Malawi','Malawi','265',1,1),(81,'Lesotho (Kingdom of)','Lesotho (Kingdom of)','266',1,1),(82,'Botswana (Republic of)','','267',1,1),(83,'Swaziland (Kingdom of)','','268',1,1),(84,'Comoros (Union of the)','','269',1,1),(86,'South Africa (Republic of)','','27',1,1),(87,'Saint Helena','Saint Helena','290',1,1),(88,'Eritrea','','291',1,1),(89,'Aruba','','297',1,1),(90,'Faroe Islands','Faroe Islands','298',1,1),(91,'Greenland (Denmark)','Greenland (Denmark)','299',1,1),(92,'Greece','Greece','30',1,1),(93,'Netherlands (Kingdom of the)','Netherlands (Kingdom of the)','31',1,1),(94,'Belgium','','32',1,1),(95,'France','','33',1,1),(96,'Spain','','34',1,1),(97,'Gibraltar','Gibraltar','350',1,1),(98,'Portugal','Portugal','351',1,1),(99,'Luxembourg','','352',1,1),(100,'Ireland','Ireland','353',1,1),(101,'Iceland','','354',1,1),(102,'Albania (Republic of)','Albania (Republic of)','355',1,1),(103,'Malta','Malta','356',1,1),(104,'Cyprus (Republic of)','','357',1,1),(105,'Finland','Finland','358',1,1),(106,'Bulgaria (Republic of)','','359',1,1),(107,'Hungary (Republic of)','','36',1,1),(108,'Lithuania (Republic of)','','370',1,1),(109,'Latvia (Republic of)','Latvia (Republic of)','371',1,1),(110,'Estonia (Republic of)','Estonia (Republic of)','372',1,1),(111,'Moldova (Republic of)','Moldova (Republic of)','373',1,1),(112,'Armenia (Republic of)','Armenia (Republic of)','374',1,1),(113,'Belarus (Republic of)','Belarus (Republic of)','375',1,1),(114,'Andorra (Principality of)','Andorra (Principality of)','376',1,1),(115,'Monaco (Principality of)','Monaco (Principality of)','377',1,1),(116,'San Marino (Republic of)','San Marino (Republic of)','378',1,1),(117,'Vatican City State','Vatican City State','379',1,1),(118,'Ukraine','Ukraine','380',1,1),(119,'Serbia and Montenegro','Serbia and Montenegro','381',1,1),(120,'Croatia (Republic of)','Croatia (Republic of)','385',1,1),(121,'Slovenia (Republic of)','','386',1,1),(122,'Bosnia and Herzegovina','','387',1,1),(123,'Group of countries, shared code','Group of countries, shared code','388',1,1),(124,'The Former Yugoslav Republic of Macedonia','The Former Yugoslav Republic of Macedonia','389',1,1),(125,'Italy','Italy','39',1,1),(127,'Romania','Romania','40',1,1),(128,'Switzerland (Confederation of)','','41',1,1),(129,'Czech Republic','Czech Republic','420',1,1),(130,'Slovak Republic','Slovak Republic','421',1,1),(131,'Liechtenstein (Principality of)','Liechtenstein (Principality of)','423',1,1),(132,'Austria','Austria','43',1,1),(133,'United Kingdom of Great Britain and Northern Ireland','United Kingdom of Great Britain and Northern Ireland','44',1,1),(134,'Denmark','Denmark','45',1,1),(135,'Sweden','Sweden','46',1,1),(136,'Norway','Norway','47',1,1),(137,'Poland (Republic of)','Poland (Republic of)','48',1,1),(138,'Germany (Federal Republic of)','','49',1,1),(139,'Falkland Islands (Malvinas)','Falkland Islands (Malvinas)','500',1,1),(140,'Belize','Belize','501',1,1),(141,'Guatemala (Republic of)','','502',1,1),(142,'El Salvador (Republic of)','','503',1,1),(143,'Honduras (Republic of)','','504',1,1),(144,'Nicaragua','Nicaragua','505',1,1),(145,'Costa Rica','','506',1,1),(146,'Panama (Republic of)','','507',1,1),(147,'Saint Pierre and Miquelon (CollectivitÃ© territoriale de la RÃ©publique franÃ§aise)','Saint Pierre and Miquelon (CollectivitÃƒÂ© territoriale de la RÃƒÂ©publique franÃƒÂ§aise)','508',1,1),(148,'Haiti (Republic of)','','509',1,1),(149,'Peru','','51',1,1),(150,'Mexico','Mexico','52',1,1),(151,'Cuba','Cuba','53',1,1),(152,'Argentine Republic','Argentine Republic','54',1,1),(153,'Brazil (Federative Republic of)','Brazil (Federative Republic of)','55',1,1),(154,'Chile','','56',1,1),(155,'Colombia (Republic of)','Colombia (Republic of)','57',1,1),(156,'Venezuela (Bolivarian Republic of)','Venezuela (Bolivarian Republic of)','58',1,1),(157,'Guadeloupe (French Department of)','','590',1,1),(158,'Bolivia (Republic of)','','591',1,1),(159,'Guyana','','592',1,1),(160,'Ecuador','','593',1,1),(161,'French Guiana (French Department of)','','594',1,1),(162,'Paraguay (Republic of)','Paraguay (Republic of)','595',1,1),(163,'Martinique (French Department of)','Martinique (French Department of)','596',1,1),(164,'Suriname (Republic of)','Suriname (Republic of)','597',1,1),(165,'Uruguay (Eastern Republic of)','Uruguay (Eastern Republic of)','598',1,1),(166,'Netherlands Antilles','','599',1,1),(167,'Malaysia','','60',1,1),(168,'Australia','','61',1,1),(169,'Indonesia (Republic of)','','62',1,1),(170,'Philippines (Republic of the)','','63',1,1),(171,'New Zealand','New Zealand','64',1,1),(172,'Singapore (Republic of)','Singapore (Republic of)','65',1,1),(173,'Thailand','Thailand','66',1,1),(174,'Democratic Republic of Timor-Leste','','670',1,1),(175,'Australian External Territories','Australian External Territories','672',1,1),(176,'Brunei Darussalam','','673',1,1),(177,'Nauru (Republic of)','','674',1,1),(178,'Papua New Guinea','','675',1,1),(179,'Tonga (Kingdom of)','Tonga (Kingdom of)','676',1,1),(180,'Solomon Islands','Solomon Islands','677',1,1),(181,'Vanuatu (Republic of)','Vanuatu (Republic of)','678',1,1),(182,'Fiji (Republic of)','','679',1,1),(183,'Palau (Republic of)','','680',1,1),(184,'Wallis and Futuna (Territoire franÃ§ais d\'outre-mer)','','681',1,1),(185,'Cook Islands','','682',1,1),(186,'Niue','Niue','683',1,1),(187,'Samoa (Independent State of','Samoa (Independent State of','685',1,1),(188,'Kiribati (Republic of)','','686',1,1),(189,'New Caledonia (Territoire franÃ§ais d\'outre-mer)','','687',1,1),(190,'Tuvalu','Tuvalu','688',1,1),(191,'French Polynesia (Territoire franÃ§ais d\'outre-mer)','French Polynesia (Territoire franÃƒÂ§ais d\'outre-mer)','689',1,1),(192,'Tokelau','Tokelau','690',1,1),(193,'Micronesia (Federated States of)','','691',1,1),(194,'Marshall Islands (Republic of the)','Marshall Islands (Republic of the)','692',1,1),(196,'Russian Federation','Russian Federation','7',1,1),(199,'Japan','Japan','81',1,1),(200,'Korea (Republic of)','Korea (Republic of)','82',1,1),(201,'Viet Nam (Socialist Republic of)','Viet Nam (Socialist Republic of)','84',1,1),(202,'Democratic People\'s Republic of Korea','Democratic People\'s Republic of Korea','850',1,1),(203,'Hong Kong, China','Hong Kong, China','852',1,1),(204,'Macao, China','Macao, China','853',1,1),(205,'Cambodia (Kingdom of)','Cambodia (Kingdom of)','855',1,1),(206,'Lao People\'s Democratic Republic','Lao People\'s Democratic Republic','856',1,1),(207,'China (People\'s Republic of)','China (People\'s Republic of)','86',1,1),(208,'Inmarsat SNAC','Inmarsat SNAC','870',1,1),(209,'Inmarsat (Atlantic Ocean-East)','Inmarsat (Atlantic Ocean-East)','871',1,1),(210,'Inmarsat (Pacific Ocean)','Inmarsat (Pacific Ocean)','872',1,1),(211,'Inmarsat (Indian Ocean)','Inmarsat (Indian Ocean)','873',1,1),(212,'Inmarsat (Atlantic Ocean-West)','Inmarsat (Atlantic Ocean-West)','874',1,1),(218,'Bangladesh (People\'s Republic of)','Bangladesh (People\'s Republic of)','880',1,1),(223,'Turkey','Turkey','90',1,1),(224,'India (Republic of)','India (Republic of)','91',1,1),(225,'Pakistan (Islamic Republic of)','Pakistan (Islamic Republic of)','92',1,1),(226,'Afghanistan','Afghanistan','93',1,1),(227,'Sri Lanka (Democratic Socialist Republic of)','Sri Lanka (Democratic Socialist Republic of)','94',1,1),(228,'Myanmar (Union of)','Myanmar (Union of)','95',1,1),(229,'Maldives (Republic of)','Maldives (Republic of)','960',1,1),(230,'Lebanon','Lebanon','961',1,1),(231,'Jordan (Hashemite Kingdom of)','','962',1,1),(232,'Syrian Arab Republic','','963',1,1),(233,'Iraq (Republic of)','','964',1,1),(234,'Kuwait (State of)','','965',1,1),(235,'Saudi Arabia (Kingdom of)','','966',1,1),(236,'Yemen (Republic of)','','967',1,1),(237,'Oman (Sultanate of)','','968',1,1),(240,'United Arab Emirates','','971',1,1),(241,'Israel (State of)','','972',1,1),(242,'Bahrain (Kingdom of)','','973',1,1),(243,'Qatar (State of)','','974',1,1),(244,'Bhutan (Kingdom of)','','975',1,1),(245,'Mongolia','','976',1,1),(246,'Nepal','','977',1,1),(248,'Iran (Islamic Republic of)','Iran (Islamic Republic of)','98',1,1),(249,'Trial of a proposed new international telecommunication public correspondence service, shared code','','991',1,1),(250,'Tajikistan (Republic of)','Tajikistan (Republic of)','992',1,1),(251,'Turkmenistan','Turkmenistan','993',1,1),(252,'Azerbaijani Republic','Azerbaijani Republic','994',1,1),(253,'Georgia','Georgia','995',1,1),(254,'Kyrgyz Republic','Kyrgyz Republic','996',1,1),(255,'Uzbekistan (Republic of)','','998',1,1);
/*!40000 ALTER TABLE `hcmo_country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_currency`
--

DROP TABLE IF EXISTS `hcmo_currency`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_currency` (
  `HcmoCurrencyId` int(11) NOT NULL auto_increment,
  `CurrencyType` varchar(20) default NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  `hcmoclientid` int(11) NOT NULL,
  PRIMARY KEY  (`HcmoCurrencyId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_currency`
--

LOCK TABLES `hcmo_currency` WRITE;
/*!40000 ALTER TABLE `hcmo_currency` DISABLE KEYS */;
/*!40000 ALTER TABLE `hcmo_currency` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_customer`
--

DROP TABLE IF EXISTS `hcmo_customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_customer` (
  `HcmoCustomerId` int(11) NOT NULL auto_increment,
  `hcmoclientid` int(11) default NULL,
  `Name` varchar(100) default NULL,
  `Description` text,
  `PhoneNumber` varchar(20) default NULL,
  `Fax` varchar(20) default NULL,
  `Address1` varchar(100) default NULL,
  `Address2` varchar(100) default NULL,
  `ContactName` varchar(60) default NULL,
  `Email` varchar(100) default NULL,
  `Deleted` tinyint(1) NOT NULL default '0',
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  PRIMARY KEY  (`HcmoCustomerId`),
  UNIQUE KEY `Name` (`Name`,`IsActive`),
  UNIQUE KEY `Email` (`Email`,`IsActive`),
  UNIQUE KEY `CustomerPhone` (`PhoneNumber`,`IsActive`),
  UNIQUE KEY `CustomerFax` (`Fax`,`IsActive`),
  KEY `fk_hcmo_customer_updatedby` (`UpdatedBy`),
  KEY `fk_hcmo_customer_createdby` (`CreatedBy`),
  CONSTRAINT `fk_hcmo_customer_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_customer_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_customer`
--

LOCK TABLES `hcmo_customer` WRITE;
/*!40000 ALTER TABLE `hcmo_customer` DISABLE KEYS */;
/*!40000 ALTER TABLE `hcmo_customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_deductions`
--

DROP TABLE IF EXISTS `hcmo_deductions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_deductions` (
  `HcmoDeductionId` int(11) NOT NULL auto_increment,
  `hcmoclientid` int(11) default NULL,
  `DeductionName` varchar(100) default NULL,
  `DeductionType` varchar(20) default NULL,
  `DeductionMode` varchar(20) default NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  PRIMARY KEY  (`HcmoDeductionId`),
  UNIQUE KEY `DeductionName` (`DeductionName`,`DeductionType`,`DeductionMode`),
  KEY `fk_hcmo_deductions_updatedby` (`UpdatedBy`),
  KEY `fk_hcmo_deductions_createdby` (`CreatedBy`),
  CONSTRAINT `fk_hcmo_deductions_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_deductions_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_deductions`
--

LOCK TABLES `hcmo_deductions` WRITE;
/*!40000 ALTER TABLE `hcmo_deductions` DISABLE KEYS */;
/*!40000 ALTER TABLE `hcmo_deductions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_department`
--

DROP TABLE IF EXISTS `hcmo_department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_department` (
  `HcmoDepartmentId` int(11) NOT NULL auto_increment,
  `hcmoclientid` int(11) default NULL,
  `DeptName` varchar(120) default NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  PRIMARY KEY  (`HcmoDepartmentId`),
  UNIQUE KEY `DeptName` (`DeptName`,`IsActive`),
  KEY `fk_hcmo_department_updatedby` (`UpdatedBy`),
  KEY `fk_hcmo_department_createdby` (`CreatedBy`),
  CONSTRAINT `fk_hcmo_department_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_department_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_department`
--

LOCK TABLES `hcmo_department` WRITE;
/*!40000 ALTER TABLE `hcmo_department` DISABLE KEYS */;
INSERT INTO `hcmo_department` VALUES (1,1,'admin','2015-12-28',1,'2015-12-28 12:33:32',1,1);
/*!40000 ALTER TABLE `hcmo_department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_document`
--

DROP TABLE IF EXISTS `hcmo_document`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_document` (
  `HcmoDocumentId` int(11) NOT NULL auto_increment,
  `HcmoEmployeeId` int(11) NOT NULL,
  `documentName` varchar(255) default NULL,
  `documentType` varchar(100) default NULL,
  `created` date default NULL,
  `CreatedBy` int(11) default NULL,
  `updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) default NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  PRIMARY KEY  (`HcmoDocumentId`),
  KEY `fk_hcmo_document_updatedby` (`UpdatedBy`),
  KEY `fk_hcmo_document_createdby` (`CreatedBy`),
  KEY `fk_hcmo_document_hcmoemployeeid` (`HcmoEmployeeId`),
  CONSTRAINT `fk_hcmo_document_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_document_hcmoemployeeid` FOREIGN KEY (`HcmoEmployeeId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_document_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_document`
--

LOCK TABLES `hcmo_document` WRITE;
/*!40000 ALTER TABLE `hcmo_document` DISABLE KEYS */;
INSERT INTO `hcmo_document` VALUES (1,8,'test','html','2016-06-21',1,'2016-06-21 09:42:29',1,1);
/*!40000 ALTER TABLE `hcmo_document` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_document_template`
--

DROP TABLE IF EXISTS `hcmo_document_template`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_document_template` (
  `HcmoDocumentTemplateId` int(11) NOT NULL auto_increment,
  `HcmoDocumentId` int(11) NOT NULL,
  `HcmoEmployeeId` int(11) NOT NULL,
  `documentName` varchar(255) default NULL,
  `documentType` varchar(100) default NULL,
  `vendor_view_permission` varchar(100) default NULL,
  `vendor_edit_permission` varchar(100) default NULL,
  `vendor_delete_permission` varchar(100) default NULL,
  `vendor_share_permission` varchar(100) default NULL,
  `created` date default NULL,
  `CreatedBy` int(11) default NULL,
  `updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) default NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  PRIMARY KEY  (`HcmoDocumentTemplateId`),
  KEY `fk_hcmo_document_template_updatedby` (`UpdatedBy`),
  KEY `fk_hcmo_document_template_createdby` (`CreatedBy`),
  KEY `fk_hcmo_document_template_hcmodocumentid` (`HcmoDocumentId`),
  KEY `fk_hcmo_document_template_hcmoemployeeid` (`HcmoEmployeeId`),
  CONSTRAINT `fk_hcmo_document_template_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_document_template_hcmodocumentid` FOREIGN KEY (`HcmoDocumentId`) REFERENCES `hcmo_document` (`HcmoDocumentId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_document_template_hcmoemployeeid` FOREIGN KEY (`HcmoEmployeeId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_document_template_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_document_template`
--

LOCK TABLES `hcmo_document_template` WRITE;
/*!40000 ALTER TABLE `hcmo_document_template` DISABLE KEYS */;
/*!40000 ALTER TABLE `hcmo_document_template` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_emp_assets`
--

DROP TABLE IF EXISTS `hcmo_emp_assets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_emp_assets` (
  `HcmoAssetId` int(11) NOT NULL auto_increment,
  `HcmoEmployeeId` int(11) NOT NULL,
  `SrNo` int(11) default NULL,
  `AssetTypeId` int(11) default NULL,
  `AssetName` varchar(255) default NULL,
  `IssuedDate` date default NULL,
  `ReleasedDate` date default NULL,
  `created` date default NULL,
  `CreatedBy` int(11) default NULL,
  `updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) default NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  PRIMARY KEY  (`HcmoAssetId`),
  KEY `fk_hcmo_emp_assets_updatedby` (`UpdatedBy`),
  KEY `fk_hcmo_emp_assets_createdby` (`CreatedBy`),
  KEY `fk_hcmo_emp_assets_assettypeid` (`AssetTypeId`),
  KEY `fk_hcmo_emp_assets_hcmoemployeeid` (`HcmoEmployeeId`),
  CONSTRAINT `fk_hcmo_emp_assets_assettypeid` FOREIGN KEY (`AssetTypeId`) REFERENCES `hcmo_assets` (`AssetTypeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_emp_assets_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_emp_assets_hcmoemployeeid` FOREIGN KEY (`HcmoEmployeeId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_emp_assets_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_emp_assets`
--

LOCK TABLES `hcmo_emp_assets` WRITE;
/*!40000 ALTER TABLE `hcmo_emp_assets` DISABLE KEYS */;
/*!40000 ALTER TABLE `hcmo_emp_assets` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_emp_attachment`
--

DROP TABLE IF EXISTS `hcmo_emp_attachment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_emp_attachment` (
  `HcmoEmployeeId` int(11) NOT NULL,
  `EattachId` decimal(10,0) default NULL,
  `EattachDesc` text,
  `EattachFileName` varchar(100) default NULL,
  `EattachSize` int(11) default NULL,
  `EattachType` varchar(50) default NULL,
  `HcmoEmpChildrenId` int(11) default NULL,
  `ECName` varchar(100) default NULL,
  `ECDateOfBirth` date default NULL,
  `ECSeqNo` decimal(19,2) default NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  `hcmoclientid` int(11) default NULL,
  KEY `fk_hcmo_emp_attachment_hcmoemployeeid` (`HcmoEmployeeId`),
  KEY `fk_hcmo_emp_attachment_updatedby` (`UpdatedBy`),
  KEY `fk_hcmo_emp_attachment_createdby` (`CreatedBy`),
  CONSTRAINT `fk_hcmo_emp_attachment_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_emp_attachment_hcmoemployeeid` FOREIGN KEY (`HcmoEmployeeId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_emp_attachment_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_emp_attachment`
--

LOCK TABLES `hcmo_emp_attachment` WRITE;
/*!40000 ALTER TABLE `hcmo_emp_attachment` DISABLE KEYS */;
/*!40000 ALTER TABLE `hcmo_emp_attachment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_emp_children`
--

DROP TABLE IF EXISTS `hcmo_emp_children`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_emp_children` (
  `HcmoEmpChildrenId` int(11) NOT NULL auto_increment,
  `HcmoEmployeeId` int(11) NOT NULL,
  `ECName` varchar(100) default NULL,
  `ECDateOfBirth` date default NULL,
  `ECSeqNo` decimal(19,2) default NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  `hcmoclientid` int(11) default NULL,
  PRIMARY KEY  (`HcmoEmpChildrenId`),
  UNIQUE KEY `EmpChild` (`HcmoEmployeeId`,`ECName`,`IsActive`),
  KEY `fk_hcmo_emp_children_hcmoemployeeid` (`HcmoEmployeeId`),
  KEY `fk_hcmo_emp_children_updatedby` (`UpdatedBy`),
  KEY `fk_hcmo_emp_children_createdby` (`CreatedBy`),
  CONSTRAINT `fk_hcmo_emp_children_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_emp_children_hcmoemployeeid` FOREIGN KEY (`HcmoEmployeeId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_emp_children_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_emp_children`
--

LOCK TABLES `hcmo_emp_children` WRITE;
/*!40000 ALTER TABLE `hcmo_emp_children` DISABLE KEYS */;
/*!40000 ALTER TABLE `hcmo_emp_children` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_emp_direct_debit`
--

DROP TABLE IF EXISTS `hcmo_emp_direct_debit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_emp_direct_debit` (
  `HcmoEmpDirectDebitId` int(11) NOT NULL auto_increment,
  `HcmoEmployeeId` int(11) NOT NULL,
  `DDRoutingNum` int(9) default NULL,
  `DDAccount` varchar(100) default NULL,
  `DDAmount` decimal(11,2) default NULL,
  `DDAccountType` varchar(20) default NULL,
  `DDTransactionType` varchar(20) default NULL,
  `DDSeqNo` decimal(19,2) default NULL,
  `DDPreferedAccount` tinyint(1) default NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  `hcmoclientid` int(11) default NULL,
  PRIMARY KEY  (`HcmoEmpDirectDebitId`),
  KEY `fk_hcmo_emp_direct_debit_hcmoemployeeid` (`HcmoEmployeeId`),
  KEY `fk_hcmo_emp_direct_debit_updatedby` (`UpdatedBy`),
  KEY `fk_hcmo_emp_direct_debit_createdby` (`CreatedBy`),
  CONSTRAINT `fk_hcmo_emp_direct_debit_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_emp_direct_debit_hcmoemployeeid` FOREIGN KEY (`HcmoEmployeeId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_emp_direct_debit_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_emp_direct_debit`
--

LOCK TABLES `hcmo_emp_direct_debit` WRITE;
/*!40000 ALTER TABLE `hcmo_emp_direct_debit` DISABLE KEYS */;
/*!40000 ALTER TABLE `hcmo_emp_direct_debit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_emp_education`
--

DROP TABLE IF EXISTS `hcmo_emp_education`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_emp_education` (
  `HcmoEmpEducationId` int(11) NOT NULL auto_increment,
  `HcmoEmployeeId` int(11) NOT NULL,
  `EduMajor` varchar(100) default NULL,
  `EduYear` int(4) default NULL,
  `EduGpa` varchar(25) default NULL,
  `EduStartDate` datetime default NULL,
  `EduEndDate` datetime default NULL,
  `EduUni` varchar(100) default NULL,
  `EduDeg` varchar(60) default NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  `hcmoclientid` int(11) default NULL,
  `gradStatus` tinyint(1) default NULL,
  PRIMARY KEY  (`HcmoEmpEducationId`),
  UNIQUE KEY `EmpMajorDegree` (`HcmoEmployeeId`,`EduMajor`,`EduDeg`,`IsActive`),
  KEY `fk_hcmo_emp_education_hcmoemployeeid` (`HcmoEmployeeId`),
  KEY `fk_hcmo_emp_education_updatedby` (`UpdatedBy`),
  KEY `fk_hcmo_emp_education_createdby` (`CreatedBy`),
  CONSTRAINT `fk_hcmo_emp_education_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_emp_education_hcmoemployeeid` FOREIGN KEY (`HcmoEmployeeId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_emp_education_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_emp_education`
--

LOCK TABLES `hcmo_emp_education` WRITE;
/*!40000 ALTER TABLE `hcmo_emp_education` DISABLE KEYS */;
/*!40000 ALTER TABLE `hcmo_emp_education` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_emp_eeo`
--

DROP TABLE IF EXISTS `hcmo_emp_eeo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_emp_eeo` (
  `HcmoEEOId` int(11) NOT NULL auto_increment,
  `HcmoEmployeeId` int(11) NOT NULL,
  `HcmoEthnicRaceId` int(11) default NULL,
  `maritalStatus` varchar(100) default NULL,
  `dependents` int(11) default NULL,
  `dependDetail` varchar(255) default NULL,
  `veteran` varchar(100) default NULL,
  `militaryStatus` varchar(100) default NULL,
  `disability` varchar(100) default NULL,
  `ethnicDocumentId` int(11) default NULL,
  `veteranDocumentId` int(11) default NULL,
  `militaryDocumentId` int(11) default NULL,
  `disabilityDocumentId` int(11) default NULL,
  `emergenConName` varchar(100) default NULL,
  `emergenPhNo` int(20) default NULL,
  `created` date default NULL,
  `CreatedBy` int(11) default NULL,
  `updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) default NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  `HcmoClientId` int(11) default NULL,
  PRIMARY KEY  (`HcmoEEOId`),
  KEY `fk_hcmo_emp_eeo_updatedby` (`UpdatedBy`),
  KEY `fk_hcmo_emp_eeo_createdby` (`CreatedBy`),
  KEY `fk_hcmo_emp_eeo_hcmoethnicraceid` (`HcmoEthnicRaceId`),
  KEY `fk_hcmo_emp_eeo_ethnicdocumentid` (`ethnicDocumentId`),
  KEY `fk_hcmo_emp_eeo_veterandocumentid` (`veteranDocumentId`),
  KEY `fk_hcmo_emp_eeo_militarydocumentid` (`militaryDocumentId`),
  KEY `fk_hcmo_emp_eeo_disabilitydocumentid` (`disabilityDocumentId`),
  KEY `fk_hcmo_emp_eeo_hcmoemployeeid` (`HcmoEmployeeId`),
  CONSTRAINT `fk_hcmo_emp_eeo_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_emp_eeo_disabilitydocumentid` FOREIGN KEY (`disabilityDocumentId`) REFERENCES `hcmo_document` (`HcmoDocumentId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_emp_eeo_ethnicdocumentid` FOREIGN KEY (`ethnicDocumentId`) REFERENCES `hcmo_document` (`HcmoDocumentId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_emp_eeo_hcmoemployeeid` FOREIGN KEY (`HcmoEmployeeId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_emp_eeo_hcmoethnicraceid` FOREIGN KEY (`HcmoEthnicRaceId`) REFERENCES `hcmo_ethnic_race` (`HcmoEthnicRaceId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_emp_eeo_militarydocumentid` FOREIGN KEY (`militaryDocumentId`) REFERENCES `hcmo_document` (`HcmoDocumentId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_emp_eeo_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_emp_eeo_veterandocumentid` FOREIGN KEY (`veteranDocumentId`) REFERENCES `hcmo_document` (`HcmoDocumentId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_emp_eeo`
--

LOCK TABLES `hcmo_emp_eeo` WRITE;
/*!40000 ALTER TABLE `hcmo_emp_eeo` DISABLE KEYS */;
/*!40000 ALTER TABLE `hcmo_emp_eeo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_emp_leave_quota`
--

DROP TABLE IF EXISTS `hcmo_emp_leave_quota`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_emp_leave_quota` (
  `HcmoEmpLeaveQuotaId` int(11) NOT NULL auto_increment,
  `HcmoLeaveTypeId` int(11) NOT NULL,
  `HcmoEmployeeId` int(11) NOT NULL,
  `Year` int(4) default NULL,
  `NoOfDaysAlloted` decimal(6,2) default NULL,
  `Hours` decimal(6,2) default NULL,
  `Minutes` decimal(6,2) default NULL,
  `RemainingDays` decimal(6,2) default NULL,
  `RemainingHours` decimal(6,2) default NULL,
  `RemainingMinutes` decimal(6,2) default NULL,
  `LeaveTakenDays` decimal(6,2) default NULL,
  `LeaveTakenHours` decimal(6,2) default NULL,
  `LeaveTakenMinutes` decimal(6,2) default NULL,
  `PreCarryFwdDays` decimal(6,2) default NULL,
  `PreCarryFwdHours` decimal(6,2) default NULL,
  `PreCarryFwdMinutes` decimal(6,2) default NULL,
  `LeaveTaken` decimal(10,2) default NULL,
  `PrvYearCarryingForward` decimal(10,2) default NULL,
  `LeaveCarryingForward` decimal(10,2) default NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  `hcmoclientid` int(11) default NULL,
  PRIMARY KEY  (`HcmoEmpLeaveQuotaId`),
  KEY `fk_hcmo_emp_leave_quota_hcmoemployeeid` (`HcmoEmployeeId`),
  KEY `fk_hcmo_emp_leave_quota_hcmoleavetypeid` (`HcmoLeaveTypeId`),
  KEY `fk_hcmo_emp_leave_quota_updatedby` (`UpdatedBy`),
  KEY `fk_hcmo_emp_leave_quota_createdby` (`CreatedBy`),
  CONSTRAINT `fk_hcmo_emp_leave_quota_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_emp_leave_quota_hcmoemployeeid` FOREIGN KEY (`HcmoEmployeeId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_emp_leave_quota_hcmoleavetypeid` FOREIGN KEY (`HcmoLeaveTypeId`) REFERENCES `hcmo_leave_type` (`HcmoLeaveTypeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_emp_leave_quota_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_emp_leave_quota`
--

LOCK TABLES `hcmo_emp_leave_quota` WRITE;
/*!40000 ALTER TABLE `hcmo_emp_leave_quota` DISABLE KEYS */;
/*!40000 ALTER TABLE `hcmo_emp_leave_quota` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_emp_license`
--

DROP TABLE IF EXISTS `hcmo_emp_license`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_emp_license` (
  `HcmoEmpLicenseId` int(11) NOT NULL auto_increment,
  `HcmoEmployeeId` int(11) NOT NULL,
  `LicenseNumber` varchar(100) default NULL,
  `LicenseDate` date default NULL,
  `LicenseRenewalDate` date default NULL,
  `LicenseDesc` text,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  `hcmoclientid` int(11) default NULL,
  PRIMARY KEY  (`HcmoEmpLicenseId`),
  UNIQUE KEY `LicenseNumber` (`LicenseNumber`,`IsActive`),
  KEY `fk_hcmo_emp_license_hcmoemployeeid` (`HcmoEmployeeId`),
  KEY `fk_hcmo_emp_license_updatedby` (`UpdatedBy`),
  KEY `fk_hcmo_emp_license_createdby` (`CreatedBy`),
  CONSTRAINT `fk_hcmo_emp_license_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_emp_license_hcmoemployeeid` FOREIGN KEY (`HcmoEmployeeId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_emp_license_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_emp_license`
--

LOCK TABLES `hcmo_emp_license` WRITE;
/*!40000 ALTER TABLE `hcmo_emp_license` DISABLE KEYS */;
/*!40000 ALTER TABLE `hcmo_emp_license` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_emp_location_history`
--

DROP TABLE IF EXISTS `hcmo_emp_location_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_emp_location_history` (
  `HcmoEmpLocHistoryId` int(11) NOT NULL auto_increment,
  `HcmoEmployeeId` int(11) default NULL,
  `HcmoClientId` int(11) default NULL,
  `HcmoLocationId` int(11) default NULL,
  `StartDate` datetime default NULL,
  `EndDate` datetime default NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  PRIMARY KEY  (`HcmoEmpLocHistoryId`),
  KEY `fk_hcmo_emp_location_history_hcmoemployeeid` (`HcmoEmployeeId`),
  KEY `fk_hcmo_emp_location_history_hcmoclientid` (`HcmoClientId`),
  KEY `fk_hcmo_emp_location_history_hcmolocationid` (`HcmoLocationId`),
  KEY `fk_hcmo_emp_location_history_updatedby` (`UpdatedBy`),
  KEY `fk_hcmo_emp_location_history_createdby` (`CreatedBy`),
  CONSTRAINT `fk_hcmo_emp_location_history_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_emp_location_history_hcmoclientid` FOREIGN KEY (`HcmoClientId`) REFERENCES `hcmo_client` (`HcmoClientId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_emp_location_history_hcmoemployeeid` FOREIGN KEY (`HcmoEmployeeId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_emp_location_history_hcmolocationid` FOREIGN KEY (`HcmoLocationId`) REFERENCES `hcmo_location` (`HcmoLocationId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_emp_location_history_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_emp_location_history`
--

LOCK TABLES `hcmo_emp_location_history` WRITE;
/*!40000 ALTER TABLE `hcmo_emp_location_history` DISABLE KEYS */;
/*!40000 ALTER TABLE `hcmo_emp_location_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_emp_passport`
--

DROP TABLE IF EXISTS `hcmo_emp_passport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_emp_passport` (
  `HcmoEmpPassportId` int(11) NOT NULL auto_increment,
  `HcmoEmployeeId` int(11) NOT NULL,
  `HcmoCountryId` int(11) default NULL,
  `EpPassportNum` varchar(100) default NULL,
  `EpPassportIssueDate` datetime default NULL,
  `EpPassportExpireDate` datetime default NULL,
  `EpComments` text,
  `EpPassportTypeFlg` varchar(1) default NULL,
  `Ep19Status` varchar(100) default NULL,
  `Ep19ReviewDate` date default NULL,
  `EpSeqNo` decimal(19,2) default NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  `hcmoclientid` int(11) default NULL,
  PRIMARY KEY  (`HcmoEmpPassportId`),
  KEY `fk_hcmo_emp_passport_hcmoemployeeid` (`HcmoEmployeeId`),
  KEY `fk_hcmo_emp_passport_hcmocountryid` (`HcmoCountryId`),
  KEY `fk_hcmo_emp_passport_updatedby` (`UpdatedBy`),
  KEY `fk_hcmo_emp_passport_createdby` (`CreatedBy`),
  CONSTRAINT `fk_hcmo_emp_passport_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_emp_passport_hcmocountryid` FOREIGN KEY (`HcmoCountryId`) REFERENCES `hcmo_country` (`HcmoCountryId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_emp_passport_hcmoemployeeid` FOREIGN KEY (`HcmoEmployeeId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_emp_passport_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_emp_passport`
--

LOCK TABLES `hcmo_emp_passport` WRITE;
/*!40000 ALTER TABLE `hcmo_emp_passport` DISABLE KEYS */;
/*!40000 ALTER TABLE `hcmo_emp_passport` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_emp_report_to`
--

DROP TABLE IF EXISTS `hcmo_emp_report_to`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_emp_report_to` (
  `HcmoEmpReportToId` int(11) NOT NULL auto_increment,
  `HcmoEmployeeId` int(11) NOT NULL,
  `ERepSupEmpNumber` int(11) default NULL,
  `ERepReportingMode` smallint(6) default NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  `hcmoclientid` int(11) default NULL,
  PRIMARY KEY  (`HcmoEmpReportToId`),
  KEY `fk_hcmo_emp_report_to_hcmoemployeeid` (`HcmoEmployeeId`),
  KEY `fk_hcmo_emp_report_to_erepsupempnumber` (`ERepSupEmpNumber`),
  KEY `fk_hcmo_emp_report_to_updatedby` (`UpdatedBy`),
  KEY `fk_hcmo_emp_report_to_createdby` (`CreatedBy`),
  CONSTRAINT `fk_hcmo_emp_report_to_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_emp_report_to_erepsupempnumber` FOREIGN KEY (`ERepSupEmpNumber`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_emp_report_to_hcmoemployeeid` FOREIGN KEY (`HcmoEmployeeId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_emp_report_to_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_emp_report_to`
--

LOCK TABLES `hcmo_emp_report_to` WRITE;
/*!40000 ALTER TABLE `hcmo_emp_report_to` DISABLE KEYS */;
/*!40000 ALTER TABLE `hcmo_emp_report_to` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_emp_stat`
--

DROP TABLE IF EXISTS `hcmo_emp_stat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_emp_stat` (
  `HcmoEmpStatId` int(11) NOT NULL auto_increment,
  `hcmoclientid` int(11) default NULL,
  `EStatName` varchar(60) default NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  PRIMARY KEY  (`HcmoEmpStatId`),
  UNIQUE KEY `EStatName` (`EStatName`,`IsActive`),
  KEY `fk_hcmo_emp_stat_updatedby` (`UpdatedBy`),
  KEY `fk_hcmo_emp_stat_createdby` (`CreatedBy`),
  CONSTRAINT `fk_hcmo_emp_stat_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_emp_stat_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_emp_stat`
--

LOCK TABLES `hcmo_emp_stat` WRITE;
/*!40000 ALTER TABLE `hcmo_emp_stat` DISABLE KEYS */;
INSERT INTO `hcmo_emp_stat` VALUES (1,1,'Joined','2015-12-28',1,'2015-12-28 12:23:07',1,1),(2,1,'Terminated','2015-12-28',1,'2015-12-28 12:23:56',1,1);
/*!40000 ALTER TABLE `hcmo_emp_stat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_emp_target_goal`
--

DROP TABLE IF EXISTS `hcmo_emp_target_goal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_emp_target_goal` (
  `HcmoEmpTgId` int(11) NOT NULL auto_increment,
  `HcmoEmployeeId` int(11) default NULL,
  `HcmoProjectId` int(11) default NULL,
  `HcmoProjectActivityId` int(11) default NULL,
  `EmpTargetName` varchar(120) default NULL,
  `EmpTargetType` varchar(120) default NULL,
  `EmpTargetMode` varchar(120) default NULL,
  `EmpGoalName` varchar(255) default NULL,
  `EmpTargetAchieved` text,
  `EmpTargetNotes` text,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  `hcmoclientid` int(11) NOT NULL,
  PRIMARY KEY  (`HcmoEmpTgId`),
  KEY `fk_hcmo_emp_target_goal_hcmoemployeeid` (`HcmoEmployeeId`),
  KEY `fk_hcmo_emp_target_goal_hcmoprojectid` (`HcmoProjectId`),
  KEY `fk_hcmo_emp_target_goal_hcmoprojectactivityid` (`HcmoProjectActivityId`),
  KEY `fk_hcmo_emp_target_goal_updatedby` (`UpdatedBy`),
  KEY `fk_hcmo_emp_target_goal_createdby` (`CreatedBy`),
  CONSTRAINT `fk_hcmo_emp_target_goal_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_emp_target_goal_hcmoemployeeid` FOREIGN KEY (`HcmoEmployeeId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_emp_target_goal_hcmoprojectactivityid` FOREIGN KEY (`HcmoProjectActivityId`) REFERENCES `hcmo_project_activity` (`HcmoProjectActivityId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_emp_target_goal_hcmoprojectid` FOREIGN KEY (`HcmoProjectId`) REFERENCES `hcmo_project` (`HcmoProjectId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_emp_target_goal_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_emp_target_goal`
--

LOCK TABLES `hcmo_emp_target_goal` WRITE;
/*!40000 ALTER TABLE `hcmo_emp_target_goal` DISABLE KEYS */;
/*!40000 ALTER TABLE `hcmo_emp_target_goal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_emp_us_tax`
--

DROP TABLE IF EXISTS `hcmo_emp_us_tax`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_emp_us_tax` (
  `HcmoEmpUsTaxId` int(11) NOT NULL auto_increment,
  `HcmoEmployeeId` int(11) NOT NULL,
  `TaxFederalStatus` varchar(20) default NULL,
  `TaxFederalExceptions` varchar(20) default NULL,
  `TaxState` int(11) default NULL,
  `TaxStateStatus` varchar(20) default NULL,
  `TaxStateExceptions` varchar(20) default NULL,
  `TaxUnempState` int(11) default NULL,
  `TaxWorkState` int(11) default NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  `hcmoclientid` int(11) default NULL,
  PRIMARY KEY  (`HcmoEmpUsTaxId`),
  KEY `fk_hcmo_emp_us_tax_hcmoemployeeid` (`HcmoEmployeeId`),
  KEY `fk_hcmo_emp_us_tax_taxstate` (`TaxState`),
  KEY `fk_hcmo_emp_us_tax_taxunempstate` (`TaxUnempState`),
  KEY `fk_hcmo_emp_us_tax_taxworkstate` (`TaxWorkState`),
  KEY `fk_hcmo_emp_us_tax_updatedby` (`UpdatedBy`),
  KEY `fk_hcmo_emp_us_tax_createdby` (`CreatedBy`),
  CONSTRAINT `fk_hcmo_emp_us_tax_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_emp_us_tax_hcmoemployeeid` FOREIGN KEY (`HcmoEmployeeId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_emp_us_tax_taxstate` FOREIGN KEY (`TaxState`) REFERENCES `hcmo_region` (`HcmoRegionId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_emp_us_tax_taxunempstate` FOREIGN KEY (`TaxUnempState`) REFERENCES `hcmo_region` (`HcmoRegionId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_emp_us_tax_taxworkstate` FOREIGN KEY (`TaxWorkState`) REFERENCES `hcmo_region` (`HcmoRegionId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_emp_us_tax_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_emp_us_tax`
--

LOCK TABLES `hcmo_emp_us_tax` WRITE;
/*!40000 ALTER TABLE `hcmo_emp_us_tax` DISABLE KEYS */;
/*!40000 ALTER TABLE `hcmo_emp_us_tax` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_emp_work_experience`
--

DROP TABLE IF EXISTS `hcmo_emp_work_experience`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_emp_work_experience` (
  `HcmoEmpWorkExperienceId` int(11) NOT NULL auto_increment,
  `HcmoEmployeeId` int(11) NOT NULL,
  `EExpEmployer` varchar(100) default NULL,
  `EExpJobTit` varchar(120) default NULL,
  `EExpFromDate` datetime default NULL,
  `EExpToDate` datetime default NULL,
  `EExpComments` text,
  `EExpSeqNo` decimal(19,2) default NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  `hcmoclientid` int(11) default NULL,
  PRIMARY KEY  (`HcmoEmpWorkExperienceId`),
  KEY `fk_hcmo_emp_work_experience_hcmoemployeeid` (`HcmoEmployeeId`),
  KEY `fk_hcmo_emp_work_experience_updatedby` (`UpdatedBy`),
  KEY `fk_hcmo_emp_work_experience_createdby` (`CreatedBy`),
  CONSTRAINT `fk_hcmo_emp_work_experience_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_emp_work_experience_hcmoemployeeid` FOREIGN KEY (`HcmoEmployeeId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_emp_work_experience_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_emp_work_experience`
--

LOCK TABLES `hcmo_emp_work_experience` WRITE;
/*!40000 ALTER TABLE `hcmo_emp_work_experience` DISABLE KEYS */;
/*!40000 ALTER TABLE `hcmo_emp_work_experience` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_employee`
--

DROP TABLE IF EXISTS `hcmo_employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_employee` (
  `HcmoEmployeeId` int(11) NOT NULL auto_increment,
  `HcmoClientId` int(11) default NULL,
  `HcmoEthnicRaceId` int(11) default NULL,
  `HcmoNationalityId` int(11) default NULL,
  `HcmoJobTitleId` int(11) default NULL,
  `HcmoRoleId` int(11) default NULL,
  `HcmoCountryId` int(11) default NULL,
  `HcmoEmpStatusId` int(11) default NULL,
  `HcmoDepartmentId` int(11) default NULL,
  `HcmoTeamId` int(11) default NULL,
  `HcmoVendor` int(11) default NULL,
  `EmpFirstName` varchar(100) default NULL,
  `EmpMiddleName` varchar(100) default NULL,
  `EmpLastName` varchar(100) default NULL,
  `EmpNickName` varchar(100) default NULL,
  `EmpFullName` varchar(255) default NULL,
  `EmpSmoker` tinyint(1) default NULL,
  `EmpBirthDay` date default NULL,
  `EmpGender` varchar(1) default NULL,
  `EmpMaritalStatus` varchar(20) default NULL,
  `EmpSSNNum` varchar(100) default NULL,
  `EmpOtherId` varchar(100) default NULL,
  `EmpOtherName` varchar(60) default NULL,
  `EmpDriLiceNum` varchar(100) default NULL,
  `EmpDriLiceExpDate` date default NULL,
  `EmpMilitaryService` varchar(100) default NULL,
  `EmpStreet1` varchar(100) default NULL,
  `EmpStreet2` varchar(100) default NULL,
  `CityName` varchar(100) default NULL,
  `CounName` varchar(100) default NULL,
  `EmpZipCode` varchar(20) default NULL,
  `EmpHmTelephone` varchar(50) default NULL,
  `EmpMobile` varchar(50) default NULL,
  `EmpWorkTelephone` varchar(50) default NULL,
  `EmpWorkEmail` varchar(100) default NULL,
  `JoinedDate` date default NULL,
  `EmpOthEmail` varchar(100) default NULL,
  `TerminatedDate` date default NULL,
  `TerminationReason` varchar(255) default NULL,
  `Custom1` varchar(255) default NULL,
  `Custom2` varchar(255) default NULL,
  `EmpSpace` varchar(255) default NULL,
  `EmpPicturePath` varchar(255) default NULL,
  `EmpStatus` varchar(13) default NULL,
  `employee_wage` varchar(32) default NULL,
  `EmpType` varchar(20) default NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  `Access_type` varchar(255) default NULL,
  `resumeDocumentId` int(11) default NULL,
  `rLiteAccess` tinyint(1) default NULL,
  PRIMARY KEY  (`HcmoEmployeeId`),
  UNIQUE KEY `EmpWorkEmail` (`EmpWorkEmail`,`IsActive`),
  KEY `fk_hcmo_employee_resumedocumentid` (`resumeDocumentId`),
  CONSTRAINT `fk_hcmo_employee_resumedocumentid` FOREIGN KEY (`resumeDocumentId`) REFERENCES `hcmo_document` (`HcmoDocumentId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_employee`
--

LOCK TABLES `hcmo_employee` WRITE;
/*!40000 ALTER TABLE `hcmo_employee` DISABLE KEYS */;
INSERT INTO `hcmo_employee` VALUES (1,1,1,1,1,1,224,1,1,1,NULL,'admin','','admin','aaaa','admin admin,(admin@bonsai.com)',1,'1985-10-14','M','Single','','',NULL,'1598541','2018-01-03','','no:22 adyar','','Chennai','Tamilnadu','600021','2514875259','','','admin@bonsai.com','2010-01-01','',NULL,'','','','2097152',NULL,NULL,'Monthly','Direct','2009-11-21',1,'2010-02-18 05:00:00',1,1,NULL,NULL,NULL),(2,0,NULL,NULL,NULL,1,NULL,NULL,1,NULL,NULL,'admin',NULL,'addsf',NULL,'admin addsf,(ddffd)',0,'1987-08-09','1',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'dsfsd','dsfds',NULL,NULL,'23242',NULL,'ddffd',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'xz','2016-06-21',1,'2016-06-21 07:25:44',1,1,'sd',NULL,0),(3,0,NULL,NULL,NULL,0,NULL,NULL,0,NULL,NULL,'anu',NULL,'aksaya',NULL,'anu aksaya,(aju.saifan@gmail.com)',0,'1987-02-09','1',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'zcz','ZXZX',NULL,NULL,'9876543220',NULL,'aju.saifan@gmail.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'permanent','2016-06-21',1,'2016-06-21 07:50:20',1,1,'sadasd',NULL,0),(4,0,NULL,NULL,NULL,1,NULL,NULL,1,NULL,NULL,'sdasd',NULL,'asdas',NULL,'sdasd asdas,(esdsdf)',0,'1987-08-09','1',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'sadasd','czxzxc',NULL,NULL,'342342',NULL,'esdsdf',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'sdsd','2016-06-21',1,'2016-06-21 08:27:17',1,1,'xzcx',NULL,0),(5,0,NULL,NULL,NULL,1,NULL,NULL,0,NULL,NULL,'XDS',NULL,'SDFSD',NULL,'XDS SDFSD,(SDFSD)',0,'1987-08-09','1',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'SDFSD','XVV',NULL,NULL,'23434',NULL,'SDFSD',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'CXXCV','2016-06-21',1,'2016-06-21 08:37:00',1,1,'VXCV',NULL,0),(6,0,NULL,NULL,NULL,1,NULL,NULL,0,NULL,NULL,'df',NULL,'sdf',NULL,'df sdf,(vxc)',0,'1987-08-09','1',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'xccv','cvvb',NULL,NULL,'4354534',NULL,'vxc',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'cxcv','2016-06-21',1,'2016-06-21 08:41:33',1,1,'cxzcx',NULL,0),(7,0,NULL,NULL,NULL,1,NULL,NULL,1,NULL,NULL,'banu',NULL,'zsas',NULL,'banu zsas,(asd)',0,'1987-08-09','1',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'asd','sdsa',NULL,NULL,'23221',NULL,'asd',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'xczc','2016-06-21',1,'2016-06-21 09:30:13',1,1,'xzc',NULL,0),(8,0,NULL,NULL,NULL,1,NULL,NULL,1,NULL,NULL,'scxas',NULL,'asasd',NULL,'scxas asasd,(ssad)',0,'1987-08-09','1',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'asdasd','zczsda',NULL,NULL,'2312321',NULL,'ssad',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'cxxz','2016-06-21',1,'2016-06-21 09:36:21',1,1,'zxc',NULL,0),(9,0,NULL,NULL,NULL,0,NULL,NULL,1,NULL,NULL,'sdf',NULL,'sdf',NULL,'sdf sdf,(dsfsd)',0,'1987-08-09','1',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'xdfd','vxc',NULL,NULL,'345345',NULL,'dsfsd',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'dcsd','2016-06-21',1,'2016-06-21 09:42:29',1,1,'sdfs',NULL,NULL),(10,0,NULL,NULL,NULL,1,NULL,NULL,1,NULL,NULL,'ddfdgdsg',NULL,'ddsf',NULL,'ddfdgdsg ddsf,(ddf)',0,'1987-08-09','1',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'sdfsd','fdfg',NULL,NULL,'8747357457',NULL,'ddf',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'nzcn','2016-06-21',1,'2016-06-21 15:33:47',1,1,'sdff',NULL,0),(11,0,NULL,NULL,NULL,1,NULL,NULL,1,NULL,NULL,'dddd',NULL,'dd',NULL,'dddd dd,(ddd)',0,'1987-09-08','0',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'ddd','ccc',NULL,NULL,'923373',NULL,'ddd',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'dds','2016-06-21',1,'2016-06-21 15:44:07',1,1,'dd',NULL,0);
/*!40000 ALTER TABLE `hcmo_employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_employee_expenses`
--

DROP TABLE IF EXISTS `hcmo_employee_expenses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_employee_expenses` (
  `HcmoExpensesId` int(11) NOT NULL auto_increment,
  `HcmoEmployeeId` int(11) default NULL,
  `HcmoAccountantId` int(11) default NULL,
  `CreatedDate` date default NULL,
  `ApprovalStatus1` varchar(255) default NULL,
  `ApprovalStatus2` varchar(255) default NULL,
  `ApprovalStatus3` varchar(255) default NULL,
  `HcmoApproverId1` int(11) default NULL,
  `HcmoApproverId2` int(11) default NULL,
  `HcmoApproverId3` int(11) default NULL,
  `TotalAmount` decimal(19,2) default NULL,
  `Approval1_Time` date default NULL,
  `Approval2_Time` date default NULL,
  `Approval3_Time` date default NULL,
  `ReimbursementAmount` double default NULL,
  `ReimbursementDate` date default NULL,
  `AccountingNotes` text,
  `ProjectId` int(11) default NULL,
  `Status` varchar(255) default NULL,
  `NextLevelId` int(11) default NULL,
  `ExpenseName` varchar(255) default NULL,
  `Receipt` varchar(255) default NULL,
  `ExpenseFromDate` date default NULL,
  `ExpenseToDate` date default NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  `hcmoclientid` int(11) default NULL,
  PRIMARY KEY  (`HcmoExpensesId`),
  KEY `fk_hcmo_employee_expenses_projectid` (`ProjectId`),
  KEY `fk_hcmo_employee_expenses_hcmoaccountantid` (`HcmoAccountantId`),
  KEY `fk_hcmo_employee_expenses_hcmoemployeeid` (`HcmoEmployeeId`),
  KEY `fk_hcmo_employee_expenses_updatedby` (`UpdatedBy`),
  KEY `fk_hcmo_employee_expenses_createdby` (`CreatedBy`),
  CONSTRAINT `fk_hcmo_employee_expenses_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_employee_expenses_hcmoaccountantid` FOREIGN KEY (`HcmoAccountantId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_employee_expenses_hcmoemployeeid` FOREIGN KEY (`HcmoEmployeeId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_employee_expenses_projectid` FOREIGN KEY (`ProjectId`) REFERENCES `hcmo_project` (`HcmoProjectId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_employee_expenses_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_employee_expenses`
--

LOCK TABLES `hcmo_employee_expenses` WRITE;
/*!40000 ALTER TABLE `hcmo_employee_expenses` DISABLE KEYS */;
/*!40000 ALTER TABLE `hcmo_employee_expenses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_employee_shift`
--

DROP TABLE IF EXISTS `hcmo_employee_shift`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_employee_shift` (
  `HcmoShiftId` int(11) NOT NULL auto_increment,
  `HcmoEmployeeId` int(11) NOT NULL,
  `shiftType` varchar(100) default NULL,
  `startDate` date default NULL,
  `endDate` date default NULL,
  `startTime` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `endTime` timestamp NOT NULL default '0000-00-00 00:00:00',
  `created` date default NULL,
  `CreatedBy` int(11) default NULL,
  `updated` timestamp NOT NULL default '0000-00-00 00:00:00',
  `UpdatedBy` int(11) default NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  PRIMARY KEY  (`HcmoShiftId`),
  KEY `fk_hcmo_employee_shift_updatedby` (`UpdatedBy`),
  KEY `fk_hcmo_employee_shift_createdby` (`CreatedBy`),
  KEY `fk_hcmo_employee_shift_hcmoemployeeid` (`HcmoEmployeeId`),
  CONSTRAINT `fk_hcmo_employee_shift_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_employee_shift_hcmoemployeeid` FOREIGN KEY (`HcmoEmployeeId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_employee_shift_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_employee_shift`
--

LOCK TABLES `hcmo_employee_shift` WRITE;
/*!40000 ALTER TABLE `hcmo_employee_shift` DISABLE KEYS */;
/*!40000 ALTER TABLE `hcmo_employee_shift` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_empprotimesheet`
--

DROP TABLE IF EXISTS `hcmo_empprotimesheet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_empprotimesheet` (
  `hcmo_empprotimesheet_id` int(11) NOT NULL auto_increment,
  `enter_date` date default NULL,
  `enter_time` double default NULL,
  `rejected` smallint(6) default NULL,
  `rework` smallint(6) default NULL,
  `approve` smallint(6) default NULL,
  `employee_id` int(11) default NULL,
  `project_id` int(11) default NULL,
  `projectActivity_id` int(11) default NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  `hcmoclientid` int(11) NOT NULL,
  PRIMARY KEY  (`hcmo_empprotimesheet_id`),
  KEY `fk_hcmo_empprotimesheet_projectid` (`project_id`),
  KEY `fk_hcmo_empprotimesheet_employeeid` (`employee_id`),
  KEY `fk_hcmo_empprotimesheet_projectActivityid` (`projectActivity_id`),
  KEY `fk_hcmo_empprotimesheet_updatedby` (`UpdatedBy`),
  KEY `fk_hcmo_empprotimesheet_createdby` (`CreatedBy`),
  CONSTRAINT `fk_hcmo_empprotimesheet_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_empprotimesheet_employeeid` FOREIGN KEY (`employee_id`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_empprotimesheet_projectActivityid` FOREIGN KEY (`projectActivity_id`) REFERENCES `hcmo_project_activity` (`HcmoProjectActivityId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_empprotimesheet_projectid` FOREIGN KEY (`project_id`) REFERENCES `hcmo_project` (`HcmoProjectId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_empprotimesheet_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_empprotimesheet`
--

LOCK TABLES `hcmo_empprotimesheet` WRITE;
/*!40000 ALTER TABLE `hcmo_empprotimesheet` DISABLE KEYS */;
/*!40000 ALTER TABLE `hcmo_empprotimesheet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_empspace`
--

DROP TABLE IF EXISTS `hcmo_empspace`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_empspace` (
  `HcmoEmpSpaceId` int(11) NOT NULL auto_increment,
  `HcmoEmployeeId` int(11) NOT NULL,
  `SharedFileTitle` varchar(100) default NULL,
  `SharedFileDesc` text,
  `EmpSpaceAttachFileName` varchar(100) default NULL,
  `SharedEmpIds` varchar(100) default NULL,
  `SharedEmpFirstName` varchar(255) default NULL,
  `SharedEmpEmailId` varchar(255) default NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  `hcmoclientid` int(11) NOT NULL,
  PRIMARY KEY  (`HcmoEmpSpaceId`),
  KEY `fk_hcmo_empspace_hcmoemployeeid` (`HcmoEmployeeId`),
  KEY `fk_hcmo_empspace_updatedby` (`UpdatedBy`),
  KEY `fk_hcmo_empspace_createdby` (`CreatedBy`),
  CONSTRAINT `fk_hcmo_empspace_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_empspace_hcmoemployeeid` FOREIGN KEY (`HcmoEmployeeId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_empspace_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_empspace`
--

LOCK TABLES `hcmo_empspace` WRITE;
/*!40000 ALTER TABLE `hcmo_empspace` DISABLE KEYS */;
/*!40000 ALTER TABLE `hcmo_empspace` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_emptimesheet`
--

DROP TABLE IF EXISTS `hcmo_emptimesheet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_emptimesheet` (
  `hcmo_emptimesheet_id` int(11) NOT NULL auto_increment,
  `enter_date` date default NULL,
  `enter_time` double default NULL,
  `rejected` tinyint(1) default NULL,
  `rework` tinyint(1) default NULL,
  `approve` tinyint(1) default NULL,
  `employee_id` int(11) default NULL,
  `category_id` int(11) default NULL,
  `hcmo_empprotimesheet_id` int(11) default NULL,
  `project_id` int(11) default NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  `hcmoclientid` int(11) default NULL,
  PRIMARY KEY  (`hcmo_emptimesheet_id`),
  KEY `fk_hcmo_emptimesheet_categoryid` (`category_id`),
  KEY `fk_hcmo_emptimesheet_employeeid` (`employee_id`),
  KEY `fk_hcmo_emptimesheet_projectid` (`project_id`),
  KEY `fk_hcmo_emptimesheet_updatedby` (`UpdatedBy`),
  KEY `fk_hcmo_emptimesheet_createdby` (`CreatedBy`),
  CONSTRAINT `fk_hcmo_emptimesheet_categoryid` FOREIGN KEY (`category_id`) REFERENCES `hcmo_timesheetcat` (`hcmo_timesheetcat_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_emptimesheet_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_emptimesheet_employeeid` FOREIGN KEY (`employee_id`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_emptimesheet_projectid` FOREIGN KEY (`project_id`) REFERENCES `hcmo_project` (`HcmoProjectId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_emptimesheet_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_emptimesheet`
--

LOCK TABLES `hcmo_emptimesheet` WRITE;
/*!40000 ALTER TABLE `hcmo_emptimesheet` DISABLE KEYS */;
/*!40000 ALTER TABLE `hcmo_emptimesheet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_ethnic_race`
--

DROP TABLE IF EXISTS `hcmo_ethnic_race`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_ethnic_race` (
  `HcmoEthnicRaceId` int(11) NOT NULL auto_increment,
  `EthnicRaceDesc` varchar(255) default NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  `hcmoclientid` int(11) default NULL,
  PRIMARY KEY  (`HcmoEthnicRaceId`),
  UNIQUE KEY `EthnicRaceDesc` (`EthnicRaceDesc`,`IsActive`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_ethnic_race`
--

LOCK TABLES `hcmo_ethnic_race` WRITE;
/*!40000 ALTER TABLE `hcmo_ethnic_race` DISABLE KEYS */;
INSERT INTO `hcmo_ethnic_race` VALUES (1,'American Indian or Alaska Native',1,1),(2,'Asian',1,1),(3,'Black or African American',1,1),(4,'Hispanic or Latino',1,1),(5,'Native Hawaiian or Other Pacific Islander',1,1),(6,'Two or More Races',1,1),(7,'White',1,1);
/*!40000 ALTER TABLE `hcmo_ethnic_race` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_events`
--

DROP TABLE IF EXISTS `hcmo_events`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_events` (
  `HcmoEventID` int(10) NOT NULL auto_increment,
  `EmployeeID` int(10) NOT NULL,
  `EventName` varchar(50) default NULL,
  `Description` varchar(200) default NULL,
  `StartDate` datetime default NULL,
  `GroupId` int(15) default NULL,
  `TimeZone` varchar(150) default NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  `hcmoclientid` int(11) default NULL,
  PRIMARY KEY  (`HcmoEventID`),
  KEY `fk_hcmo_events_updatedby` (`UpdatedBy`),
  KEY `fk_hcmo_events_createdby` (`CreatedBy`),
  CONSTRAINT `fk_hcmo_events_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_events_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_events`
--

LOCK TABLES `hcmo_events` WRITE;
/*!40000 ALTER TABLE `hcmo_events` DISABLE KEYS */;
/*!40000 ALTER TABLE `hcmo_events` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_expense_status_tracker`
--

DROP TABLE IF EXISTS `hcmo_expense_status_tracker`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_expense_status_tracker` (
  `HcmoExpensesStatusTrackerId` int(11) NOT NULL auto_increment,
  `HcmoExpensesId` int(11) default NULL,
  `ApproverId` int(11) default NULL,
  `RejectedNotes` text,
  `RejectedId` int(11) default NULL,
  `AccountantId` int(11) default NULL,
  `NextLevelId` int(11) default NULL,
  `ReviewedId` int(11) default NULL,
  `ApprovalStatus` varchar(100) default 'ForApproval',
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `hcmoclientid` int(11) NOT NULL,
  PRIMARY KEY  (`HcmoExpensesStatusTrackerId`),
  KEY `fk_hcmo_expense_status_tracker_hcmoexpensesid` (`HcmoExpensesId`),
  KEY `fk_hcmo_expense_status_tracker_accountantid` (`AccountantId`),
  KEY `fk_hcmo_expense_status_tracker_approverid` (`ApproverId`),
  KEY `fk_hcmo_expense_status_tracker_nextlevelid` (`NextLevelId`),
  KEY `fk_hcmo_expense_status_tracker_reviewedid` (`ReviewedId`),
  KEY `fk_hcmo_expense_status_tracker_rejectedid` (`RejectedId`),
  KEY `fk_hcmo_expense_status_tracker_updatedby` (`UpdatedBy`),
  KEY `fk_hcmo_expense_status_tracker_createdby` (`CreatedBy`),
  CONSTRAINT `fk_hcmo_expense_status_tracker_accountantid` FOREIGN KEY (`AccountantId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_expense_status_tracker_approverid` FOREIGN KEY (`ApproverId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_expense_status_tracker_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_expense_status_tracker_hcmoexpensesid` FOREIGN KEY (`HcmoExpensesId`) REFERENCES `hcmo_employee_expenses` (`HcmoExpensesId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_expense_status_tracker_nextlevelid` FOREIGN KEY (`NextLevelId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_expense_status_tracker_rejectedid` FOREIGN KEY (`RejectedId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_expense_status_tracker_reviewedid` FOREIGN KEY (`ReviewedId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_expense_status_tracker_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_expense_status_tracker`
--

LOCK TABLES `hcmo_expense_status_tracker` WRITE;
/*!40000 ALTER TABLE `hcmo_expense_status_tracker` DISABLE KEYS */;
/*!40000 ALTER TABLE `hcmo_expense_status_tracker` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_expenses_accountant`
--

DROP TABLE IF EXISTS `hcmo_expenses_accountant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_expenses_accountant` (
  `HcmoExpensesAccountantId` int(11) NOT NULL auto_increment,
  `ExpensesAccountantId` int(11) NOT NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  `hcmoclientid` int(11) default NULL,
  PRIMARY KEY  (`HcmoExpensesAccountantId`),
  UNIQUE KEY `ExpensesAccountantId` (`ExpensesAccountantId`,`IsActive`),
  KEY `fk_hcmo_expenses_accountant_expensesaccountantid` (`ExpensesAccountantId`),
  KEY `fk_hcmo_expenses_accountant_updatedby` (`UpdatedBy`),
  KEY `fk_hcmo_expenses_accountant_createdby` (`CreatedBy`),
  CONSTRAINT `fk_hcmo_expenses_accountant_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_expenses_accountant_expensesaccountantid` FOREIGN KEY (`ExpensesAccountantId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_expenses_accountant_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_expenses_accountant`
--

LOCK TABLES `hcmo_expenses_accountant` WRITE;
/*!40000 ALTER TABLE `hcmo_expenses_accountant` DISABLE KEYS */;
/*!40000 ALTER TABLE `hcmo_expenses_accountant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_expenses_approver`
--

DROP TABLE IF EXISTS `hcmo_expenses_approver`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_expenses_approver` (
  `ApproverId` int(11) NOT NULL auto_increment,
  `HcmoEmployeeId` int(11) NOT NULL,
  `ApprovingEmployeeId` int(11) default NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  `hcmoclientid` int(11) default NULL,
  PRIMARY KEY  (`ApproverId`),
  KEY `fk_hcmo_expenses_approver_hcmoemployeeid` (`HcmoEmployeeId`),
  KEY `fk_hcmo_expenses_approver_approvingemployeeid` (`ApprovingEmployeeId`),
  KEY `fk_hcmo_expenses_approver_updatedby` (`UpdatedBy`),
  KEY `fk_hcmo_expenses_approver_createdby` (`CreatedBy`),
  CONSTRAINT `fk_hcmo_expenses_approver_approvingemployeeid` FOREIGN KEY (`ApprovingEmployeeId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_expenses_approver_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_expenses_approver_hcmoemployeeid` FOREIGN KEY (`HcmoEmployeeId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_expenses_approver_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_expenses_approver`
--

LOCK TABLES `hcmo_expenses_approver` WRITE;
/*!40000 ALTER TABLE `hcmo_expenses_approver` DISABLE KEYS */;
/*!40000 ALTER TABLE `hcmo_expenses_approver` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_expenses_attachment`
--

DROP TABLE IF EXISTS `hcmo_expenses_attachment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_expenses_attachment` (
  `HcmoExpensesAttachId` int(11) NOT NULL auto_increment,
  `HcmoEmployeeId` int(11) default NULL,
  `HcmoExpensesId` int(11) default NULL,
  `ExpensesAttachFileName` varchar(255) default NULL,
  `ExpensesAttachSize` int(11) default NULL,
  `ExpensesAttachType` varchar(255) default NULL,
  `ExpensesAttachId` int(11) default NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  `hcmoclientid` int(11) default NULL,
  PRIMARY KEY  (`HcmoExpensesAttachId`),
  KEY `fk_hcmo_expenses_attachment_hcmoemployeeid` (`HcmoEmployeeId`),
  KEY `fk_hcmo_expenses_attachment_updatedby` (`UpdatedBy`),
  KEY `fk_hcmo_expenses_attachment_createdby` (`CreatedBy`),
  CONSTRAINT `fk_hcmo_expenses_attachment_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_expenses_attachment_hcmoemployeeid` FOREIGN KEY (`HcmoEmployeeId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_expenses_attachment_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_expenses_attachment`
--

LOCK TABLES `hcmo_expenses_attachment` WRITE;
/*!40000 ALTER TABLE `hcmo_expenses_attachment` DISABLE KEYS */;
/*!40000 ALTER TABLE `hcmo_expenses_attachment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_expenses_details`
--

DROP TABLE IF EXISTS `hcmo_expenses_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_expenses_details` (
  `HcmoExpensesDetailsId` int(11) NOT NULL auto_increment,
  `HcmoExpensesTypeId` int(11) default NULL,
  `HcmoExpensesId` int(11) default NULL,
  `ProjectId` int(11) default NULL,
  `customerid` int(11) default NULL,
  `deptId` int(11) default NULL,
  `Amount` decimal(19,2) default NULL,
  `Note` text,
  `Description` text,
  `ExpenseDate` date default NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  `hcmoclientid` int(11) default NULL,
  PRIMARY KEY  (`HcmoExpensesDetailsId`),
  KEY `fk_hcmo_expenses_details_hcmoexpensesid` (`HcmoExpensesId`),
  KEY `fk_hcmo_expenses_details_hcmoexpensestypeid` (`HcmoExpensesTypeId`),
  KEY `fk_hcmo_expenses_details_updatedby` (`UpdatedBy`),
  KEY `fk_hcmo_expenses_details_createdby` (`CreatedBy`),
  CONSTRAINT `fk_hcmo_expenses_details_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_expenses_details_hcmoexpensesid` FOREIGN KEY (`HcmoExpensesId`) REFERENCES `hcmo_employee_expenses` (`HcmoExpensesId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_expenses_details_hcmoexpensestypeid` FOREIGN KEY (`HcmoExpensesTypeId`) REFERENCES `hcmo_expenses_type` (`HcmoExpensesTypeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_expenses_details_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_expenses_details`
--

LOCK TABLES `hcmo_expenses_details` WRITE;
/*!40000 ALTER TABLE `hcmo_expenses_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `hcmo_expenses_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_expenses_type`
--

DROP TABLE IF EXISTS `hcmo_expenses_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_expenses_type` (
  `HcmoExpensesTypeId` int(11) NOT NULL auto_increment,
  `Name` varchar(255) default NULL,
  `hcmoclientid` int(11) default NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  PRIMARY KEY  (`HcmoExpensesTypeId`),
  UNIQUE KEY `Name` (`Name`,`IsActive`),
  KEY `fk_hcmo_expenses_type_updatedby` (`UpdatedBy`),
  KEY `fk_hcmo_expenses_type_createdby` (`CreatedBy`),
  CONSTRAINT `fk_hcmo_expenses_type_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_expenses_type_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_expenses_type`
--

LOCK TABLES `hcmo_expenses_type` WRITE;
/*!40000 ALTER TABLE `hcmo_expenses_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `hcmo_expenses_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_goal`
--

DROP TABLE IF EXISTS `hcmo_goal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_goal` (
  `HcmoGoalId` int(11) NOT NULL auto_increment,
  `GoalName` varchar(255) default NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  `hcmoclientid` int(11) NOT NULL,
  PRIMARY KEY  (`HcmoGoalId`),
  UNIQUE KEY `GoalName` (`GoalName`,`IsActive`),
  KEY `fk_hcmo_goal_updatedby` (`UpdatedBy`),
  KEY `fk_hcmo_goal_createdby` (`CreatedBy`),
  CONSTRAINT `fk_hcmo_goal_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_goal_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_goal`
--

LOCK TABLES `hcmo_goal` WRITE;
/*!40000 ALTER TABLE `hcmo_goal` DISABLE KEYS */;
/*!40000 ALTER TABLE `hcmo_goal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_holidays`
--

DROP TABLE IF EXISTS `hcmo_holidays`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_holidays` (
  `HcmoHolidaysId` int(11) NOT NULL auto_increment,
  `hcmoclientid` int(11) default NULL,
  `Description` varchar(255) default NULL,
  `Date` date default NULL,
  `Recurring` tinyint(1) default NULL,
  `Length` tinyint(2) default NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  PRIMARY KEY  (`HcmoHolidaysId`),
  UNIQUE KEY `Description` (`Description`,`IsActive`),
  KEY `fk_hcmo_holidays_updatedby` (`UpdatedBy`),
  KEY `fk_hcmo_holidays_createdby` (`CreatedBy`),
  CONSTRAINT `fk_hcmo_holidays_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_holidays_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_holidays`
--

LOCK TABLES `hcmo_holidays` WRITE;
/*!40000 ALTER TABLE `hcmo_holidays` DISABLE KEYS */;
/*!40000 ALTER TABLE `hcmo_holidays` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_i9`
--

DROP TABLE IF EXISTS `hcmo_i9`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_i9` (
  `HcmoI9DocumentId` int(11) NOT NULL auto_increment,
  `hcmoclientid` int(11) default NULL,
  `HcmoEmployeeId` int(10) default NULL,
  `EmployeeLName` varchar(50) default NULL,
  `EmployeeFName` varchar(50) default NULL,
  `EmployeeMName` varchar(50) default NULL,
  `EmployeeOName` varchar(50) default NULL,
  `EmployeeAddrs` varchar(200) default NULL,
  `AptNumber` varchar(8) default NULL,
  `EmployeeCity` varchar(21) default NULL,
  `EmployeeState` varchar(3) default NULL,
  `EmployeeZip` varchar(15) default NULL,
  `EmployeeDOB` varchar(20) default NULL,
  `EmployeeSSN` varchar(9) default NULL,
  `EmployeeEmail` varchar(40) default NULL,
  `EmployeePhoneNo` varchar(15) default NULL,
  `Citizen` varchar(20) default NULL,
  `NonCitizen` varchar(20) default NULL,
  `PermanentResident` varchar(20) default NULL,
  `AuthorizedToWork` varchar(20) default NULL,
  `AlienRegNo1` varchar(9) default NULL,
  `AlienWorkExpiry` varchar(20) default NULL,
  `AlienRegNo2` varchar(9) default NULL,
  `I94AdmissNo` varchar(11) default NULL,
  `PassportNo` varchar(36) default NULL,
  `IssueCountry` varchar(50) default NULL,
  `EmployeeSignDate` varchar(20) default NULL,
  `PreparerSignDate` varchar(20) default NULL,
  `PreparerLName` varchar(50) default NULL,
  `PreparerFName` varchar(50) default NULL,
  `PreparerAddress` varchar(200) default NULL,
  `PreparerCity` varchar(22) default NULL,
  `PreparerState` varchar(5) default NULL,
  `PreparerZip` varchar(15) default NULL,
  `EmployeeLFMName` varchar(150) default NULL,
  `ListADocTitle1` varchar(30) default NULL,
  `ListBDocTitle` varchar(30) default NULL,
  `ListCDocTitle` varchar(30) default NULL,
  `ListAIssueAuth1` varchar(30) default NULL,
  `ListBIssueAuth` varchar(30) default NULL,
  `ListCIssueAuth` varchar(30) default NULL,
  `ListADocNo1` varchar(30) default NULL,
  `ListBDocNo` varchar(30) default NULL,
  `ListCDocNo` varchar(30) default NULL,
  `ListAExpDate1` varchar(20) default NULL,
  `ListBExpDate` varchar(20) default NULL,
  `ListCExpDate` varchar(20) default NULL,
  `ListADocTitle2` varchar(30) default NULL,
  `ListAIssueAuth2` varchar(30) default NULL,
  `ListADocNo2` varchar(30) default NULL,
  `ListAExpDate2` varchar(20) default NULL,
  `ListADocTitle3` varchar(30) default NULL,
  `ListAIssueAuth3` varchar(30) default NULL,
  `ListADocNo3` varchar(30) default NULL,
  `ListAExpDate3` varchar(20) default NULL,
  `EmployeeFirstDay` varchar(20) default NULL,
  `EmployerSignDate` varchar(20) default NULL,
  `EmployerTitle` varchar(33) default NULL,
  `EmployerLName` varchar(50) default NULL,
  `EmployerFName` varchar(50) default NULL,
  `EmployerOrgName` varchar(50) default NULL,
  `EmployerOrgAddrs` varchar(200) default NULL,
  `EmployerCity` varchar(23) default NULL,
  `EmployerState` varchar(5) default NULL,
  `EmployerZip` varchar(15) default NULL,
  `RehireLName` varchar(50) default NULL,
  `RehireFName` varchar(50) default NULL,
  `RehireMName` varchar(50) default NULL,
  `RehireDate` varchar(20) default NULL,
  `RehireDocTitle` varchar(35) default NULL,
  `RehireDocNo` varchar(30) default NULL,
  `RehireExpDate` varchar(20) default NULL,
  `RehireSignDate` varchar(20) default NULL,
  `RehireEmployerName` varchar(50) default NULL,
  `EmployeeStatus` int(11) default '0',
  `EmployerStatus` int(11) default '0',
  `CreatedBy` int(11) default NULL,
  `UpdatedBy` int(11) default NULL,
  `Created` date default NULL,
  `EmployeeSign` longtext,
  `UpdatedDt` timestamp NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `IsActive` smallint(6) default '1',
  `HcmoI9RecvDate` varchar(200) default NULL,
  `I9Bypass` smallint(6) default '0',
  PRIMARY KEY  (`HcmoI9DocumentId`),
  KEY `FKE69A6E081F4362DA` (`UpdatedBy`),
  KEY `FKE69A6E08B71C29C7` (`CreatedBy`),
  KEY `FK8330C6281F4362DA` (`UpdatedBy`),
  KEY `FK8330C628B71C29C7` (`CreatedBy`),
  CONSTRAINT `FK8330C6281F4362DA` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`),
  CONSTRAINT `FK8330C628B71C29C7` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_i9`
--

LOCK TABLES `hcmo_i9` WRITE;
/*!40000 ALTER TABLE `hcmo_i9` DISABLE KEYS */;
/*!40000 ALTER TABLE `hcmo_i9` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_important_news`
--

DROP TABLE IF EXISTS `hcmo_important_news`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_important_news` (
  `HcmoImportantNewsId` int(11) NOT NULL auto_increment,
  `hcmoclientid` int(11) NOT NULL,
  `HcmoEmployeeId` int(11) default NULL,
  `Subject` varchar(255) default NULL,
  `Message` text NOT NULL,
  `ShowMessage` tinyint(1) default '0',
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  PRIMARY KEY  (`HcmoImportantNewsId`),
  KEY `fk_hcmo_important_news_hcmoemployeeid` (`HcmoEmployeeId`),
  KEY `fk_hcmo_important_news_updatedby` (`UpdatedBy`),
  KEY `fk_hcmo_important_news_createdby` (`CreatedBy`),
  CONSTRAINT `fk_hcmo_important_news_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_important_news_hcmoemployeeid` FOREIGN KEY (`HcmoEmployeeId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_important_news_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_important_news`
--

LOCK TABLES `hcmo_important_news` WRITE;
/*!40000 ALTER TABLE `hcmo_important_news` DISABLE KEYS */;
/*!40000 ALTER TABLE `hcmo_important_news` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_job_title`
--

DROP TABLE IF EXISTS `hcmo_job_title`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_job_title` (
  `HcmoJobTitleId` int(11) NOT NULL auto_increment,
  `HcmoSalaryGradeId` int(11) NOT NULL,
  `JobTitleName` varchar(60) NOT NULL,
  `JobTitleDesc` text,
  `JobTitleComm` text,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  `hcmoclientid` int(11) NOT NULL,
  PRIMARY KEY  (`HcmoJobTitleId`),
  UNIQUE KEY `JobTitleName` (`JobTitleName`,`IsActive`),
  KEY `fk_hcmo_job_title_hcmosalarygradeid` (`HcmoSalaryGradeId`),
  KEY `fk_hcmo_job_title_updatedby` (`UpdatedBy`),
  KEY `fk_hcmo_job_title_createdby` (`CreatedBy`),
  CONSTRAINT `fk_hcmo_job_title_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_job_title_hcmosalarygradeid` FOREIGN KEY (`HcmoSalaryGradeId`) REFERENCES `hcmo_salary_grade` (`HcmoSalaryGradeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_job_title_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_job_title`
--

LOCK TABLES `hcmo_job_title` WRITE;
/*!40000 ALTER TABLE `hcmo_job_title` DISABLE KEYS */;
INSERT INTO `hcmo_job_title` VALUES (1,1,'admin',NULL,NULL,'2015-12-28',1,'2015-12-28 12:19:34',1,1,1);
/*!40000 ALTER TABLE `hcmo_job_title` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_leave_approver`
--

DROP TABLE IF EXISTS `hcmo_leave_approver`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_leave_approver` (
  `HcmoLeaveApproverId` int(11) NOT NULL auto_increment,
  `HcmoEmployeeId` int(11) NOT NULL,
  `HcmoApprovingEmpId` int(11) default NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  `hcmoclientid` int(11) default NULL,
  PRIMARY KEY  (`HcmoLeaveApproverId`),
  KEY `fk_hcmo_leave_approver_hcmoemployeeid` (`HcmoEmployeeId`),
  KEY `fk_hcmo_leave_approver_hcmoapprovingempid` (`HcmoApprovingEmpId`),
  KEY `fk_hcmo_leave_approver_updatedby` (`UpdatedBy`),
  KEY `fk_hcmo_leave_approver_createdby` (`CreatedBy`),
  CONSTRAINT `fk_hcmo_leave_approver_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_leave_approver_hcmoapprovingempid` FOREIGN KEY (`HcmoApprovingEmpId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_leave_approver_hcmoemployeeid` FOREIGN KEY (`HcmoEmployeeId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_leave_approver_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_leave_approver`
--

LOCK TABLES `hcmo_leave_approver` WRITE;
/*!40000 ALTER TABLE `hcmo_leave_approver` DISABLE KEYS */;
/*!40000 ALTER TABLE `hcmo_leave_approver` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_leave_history`
--

DROP TABLE IF EXISTS `hcmo_leave_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_leave_history` (
  `HcmoLeaveHistoryId` int(11) NOT NULL auto_increment,
  `HcmoLeaveApproverId` int(11) default NULL,
  `HcmoEmployeeId` int(11) NOT NULL,
  `HcmoLeaveTypeId` int(11) NOT NULL,
  `LeaveRequestId` int(11) default NULL,
  `LeaveDate` date default NULL,
  `LeaveLengthHours` decimal(19,1) default NULL,
  `LeaveLengthDays` decimal(19,1) default NULL,
  `Days` decimal(6,2) default NULL,
  `Hours` decimal(6,2) default NULL,
  `Minutes` decimal(6,2) default NULL,
  `LeaveStatus` varchar(20) default NULL,
  `LeaveComments` text,
  `ApproveNotes` text,
  `DisApproveNotes` text,
  `StartTime` varchar(20) default NULL,
  `EndTime` varchar(20) default NULL,
  `LeaveEndDate` date default NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  `hcmoclientid` int(11) NOT NULL,
  PRIMARY KEY  (`HcmoLeaveHistoryId`),
  KEY `fk_hcmo_leave_history_hcmoemployeeid` (`HcmoEmployeeId`),
  KEY `fk_hcmo_leave_history_hcmoleaveapproverid` (`HcmoLeaveApproverId`),
  KEY `fk_hcmo_leave_history_hcmoleavetypeid` (`HcmoLeaveTypeId`),
  KEY `fk_hcmo_leave_history_updatedby` (`UpdatedBy`),
  KEY `fk_hcmo_leave_history_createdby` (`CreatedBy`),
  CONSTRAINT `fk_hcmo_leave_history_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_leave_history_hcmoemployeeid` FOREIGN KEY (`HcmoEmployeeId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_leave_history_hcmoleaveapproverid` FOREIGN KEY (`HcmoLeaveApproverId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_leave_history_hcmoleavetypeid` FOREIGN KEY (`HcmoLeaveApproverId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_leave_history_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_leave_history`
--

LOCK TABLES `hcmo_leave_history` WRITE;
/*!40000 ALTER TABLE `hcmo_leave_history` DISABLE KEYS */;
/*!40000 ALTER TABLE `hcmo_leave_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_leave_reqs_approval`
--

DROP TABLE IF EXISTS `hcmo_leave_reqs_approval`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_leave_reqs_approval` (
  `HcmoLeaveReqsApprovalId` int(11) NOT NULL auto_increment,
  `HcmoApproverId` int(11) default NULL,
  `HcmoLeaveTypeId` int(11) NOT NULL,
  `HcmoEmployeeId` int(11) NOT NULL,
  `HcmoLeaveApproverId` int(11) default NULL,
  `DateApplied` date NOT NULL,
  `DateApprDisappr` date default NULL,
  `Comments` text,
  `LeaveReqStatus` varchar(20) default 'For Approval',
  `noOfDays` decimal(6,2) default NULL,
  `NoOfHours` decimal(6,2) default NULL,
  `NoOfMins` decimal(6,2) default NULL,
  `ApproveNotes` text,
  `DisApproveNotes` text,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  `hcmoclientid` int(11) NOT NULL,
  PRIMARY KEY  (`HcmoLeaveReqsApprovalId`),
  KEY `fk_hcmo_leave_reqs_approval_hcmoemployeeid` (`HcmoEmployeeId`),
  KEY `fk_hcmo_leave_reqs_approval_hcmoleavetypeid` (`HcmoLeaveTypeId`),
  KEY `fk_hcmo_leave_reqs_approval_hcmoapproverid` (`HcmoApproverId`),
  KEY `fk_hcmo_leave_reqs_approval_hcmoleaveapproverid` (`HcmoLeaveApproverId`),
  KEY `fk_hcmo_leave_reqs_approval_updatedby` (`UpdatedBy`),
  KEY `fk_hcmo_leave_reqs_approval_createdby` (`CreatedBy`),
  CONSTRAINT `fk_hcmo_leave_reqs_approval_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_leave_reqs_approval_hcmoapproverid` FOREIGN KEY (`HcmoApproverId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_leave_reqs_approval_hcmoemployeeid` FOREIGN KEY (`HcmoEmployeeId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_leave_reqs_approval_hcmoleaveapproverid` FOREIGN KEY (`HcmoLeaveApproverId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_leave_reqs_approval_hcmoleavetypeid` FOREIGN KEY (`HcmoLeaveTypeId`) REFERENCES `hcmo_leave_type` (`HcmoLeaveTypeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_leave_reqs_approval_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_leave_reqs_approval`
--

LOCK TABLES `hcmo_leave_reqs_approval` WRITE;
/*!40000 ALTER TABLE `hcmo_leave_reqs_approval` DISABLE KEYS */;
/*!40000 ALTER TABLE `hcmo_leave_reqs_approval` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_leave_type`
--

DROP TABLE IF EXISTS `hcmo_leave_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_leave_type` (
  `HcmoLeaveTypeId` int(11) NOT NULL auto_increment,
  `LeaveTypeName` varchar(20) default NULL,
  `hcmoclientid` int(11) default NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  PRIMARY KEY  (`HcmoLeaveTypeId`),
  UNIQUE KEY `LeaveTypeName` (`LeaveTypeName`,`IsActive`),
  KEY `fk_hcmo_leave_type_updatedby` (`UpdatedBy`),
  KEY `fk_hcmo_leave_type_createdby` (`CreatedBy`),
  CONSTRAINT `fk_hcmo_leave_type_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_leave_type_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_leave_type`
--

LOCK TABLES `hcmo_leave_type` WRITE;
/*!40000 ALTER TABLE `hcmo_leave_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `hcmo_leave_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_location`
--

DROP TABLE IF EXISTS `hcmo_location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_location` (
  `HcmoLocationId` int(11) NOT NULL auto_increment,
  `HcmoCountryId` int(11) NOT NULL,
  `Name` varchar(60) default NULL,
  `Address1` varchar(60) default NULL,
  `Address2` varchar(60) default NULL,
  `City` varchar(60) default NULL,
  `Region` varchar(60) default NULL,
  `Phone` varchar(20) default NULL,
  `Fax` varchar(20) default NULL,
  `Comments` text,
  `ZipCode` varchar(20) default NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  PRIMARY KEY  (`HcmoLocationId`),
  UNIQUE KEY `Name` (`Name`,`IsActive`),
  KEY `fk_hcmo_location_hcmocountryid` (`HcmoCountryId`),
  CONSTRAINT `fk_hcmo_location_hcmocountryid` FOREIGN KEY (`HcmoCountryId`) REFERENCES `hcmo_country` (`HcmoCountryId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_location`
--

LOCK TABLES `hcmo_location` WRITE;
/*!40000 ALTER TABLE `hcmo_location` DISABLE KEYS */;
/*!40000 ALTER TABLE `hcmo_location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_mail_configuration`
--

DROP TABLE IF EXISTS `hcmo_mail_configuration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_mail_configuration` (
  `HcmoMailConfigurationId` int(11) NOT NULL auto_increment,
  `hcmoclientid` int(11) default NULL,
  `SmtpHost` varchar(255) default NULL,
  `Username` varchar(255) default NULL,
  `Password` varchar(255) default NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  PRIMARY KEY  (`HcmoMailConfigurationId`),
  KEY `fk_hcmo_mail_configuration_updatedby` (`UpdatedBy`),
  KEY `fk_hcmo_mail_configuration_createdby` (`CreatedBy`),
  CONSTRAINT `fk_hcmo_mail_configuration_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_mail_configuration_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_mail_configuration`
--

LOCK TABLES `hcmo_mail_configuration` WRITE;
/*!40000 ALTER TABLE `hcmo_mail_configuration` DISABLE KEYS */;
INSERT INTO `hcmo_mail_configuration` VALUES (1,1,'smtp.gmail.com','aju.saifan@gmail.com','we1c@me321','2016-05-23',1,'2016-05-23 06:39:06',1,1);
/*!40000 ALTER TABLE `hcmo_mail_configuration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_message`
--

DROP TABLE IF EXISTS `hcmo_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_message` (
  `HcmoMessageId` int(11) NOT NULL auto_increment,
  `hcmoclientid` int(11) NOT NULL,
  `sender` int(11) default NULL,
  `Receiver` varchar(255) default NULL,
  `ReceiverEmailId` varchar(255) default NULL,
  `Cc` varchar(255) default NULL,
  `CcEmailId` text,
  `Bcc` varchar(255) default NULL,
  `BccEmailId` text,
  `Subject` varchar(255) default NULL,
  `Message` text,
  `Signature` text,
  `Type` varchar(20) default NULL,
  `DeletedReceiverId` varchar(255) default NULL,
  `DeletedCcId` varchar(255) default NULL,
  `DeletedBccId` varchar(255) default NULL,
  `SenderDelete` smallint(6) default NULL,
  `ReceiverDelete` smallint(6) default NULL,
  `IsNewMail` smallint(6) default NULL,
  `IsRead` smallint(6) default NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  PRIMARY KEY  (`HcmoMessageId`),
  KEY `fk_hcmo_message_sender` (`sender`),
  KEY `fk_hcmo_message_updatedby` (`UpdatedBy`),
  KEY `fk_hcmo_message_createdby` (`CreatedBy`),
  CONSTRAINT `fk_hcmo_message_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_message_sender` FOREIGN KEY (`sender`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_message_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_message`
--

LOCK TABLES `hcmo_message` WRITE;
/*!40000 ALTER TABLE `hcmo_message` DISABLE KEYS */;
/*!40000 ALTER TABLE `hcmo_message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_module`
--

DROP TABLE IF EXISTS `hcmo_module`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_module` (
  `moduleId` int(2) NOT NULL,
  `moduleType` varchar(20) default NULL,
  PRIMARY KEY  (`moduleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_module`
--

LOCK TABLES `hcmo_module` WRITE;
/*!40000 ALTER TABLE `hcmo_module` DISABLE KEYS */;
INSERT INTO `hcmo_module` VALUES (1,'HCM'),(2,'Recruit'),(3,'Both');
/*!40000 ALTER TABLE `hcmo_module` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_nationality`
--

DROP TABLE IF EXISTS `hcmo_nationality`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_nationality` (
  `HcmoNationalityId` int(11) NOT NULL auto_increment,
  `NatName` varchar(120) default NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  `hcmoclientid` int(11) NOT NULL,
  PRIMARY KEY  (`HcmoNationalityId`),
  UNIQUE KEY `NatName` (`NatName`,`IsActive`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_nationality`
--

LOCK TABLES `hcmo_nationality` WRITE;
/*!40000 ALTER TABLE `hcmo_nationality` DISABLE KEYS */;
INSERT INTO `hcmo_nationality` VALUES (1,'America',1,1);
/*!40000 ALTER TABLE `hcmo_nationality` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_org`
--

DROP TABLE IF EXISTS `hcmo_org`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_org` (
  `HcmoOrgId` int(11) NOT NULL auto_increment,
  `HcmoLocationId` int(11) NOT NULL,
  `HcmoOrgTypeId` int(11) NOT NULL,
  `ParentOrgId` int(11) default NULL,
  `Name` varchar(60) NOT NULL,
  `Description` text,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  `hcmoclientid` int(11) NOT NULL,
  PRIMARY KEY  (`HcmoOrgId`),
  UNIQUE KEY `Name` (`Name`,`IsActive`),
  KEY `fk_hcmo_org_hcmolocationid` (`HcmoLocationId`),
  KEY `fk_hcmo_org_hcmoorgtypeid` (`HcmoOrgTypeId`),
  KEY `fk_hcmo_org_updatedby` (`UpdatedBy`),
  KEY `fk_hcmo_org_createdby` (`CreatedBy`),
  CONSTRAINT `fk_hcmo_org_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_org_hcmolocationid` FOREIGN KEY (`HcmoLocationId`) REFERENCES `hcmo_location` (`HcmoLocationId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_org_hcmoorgtypeid` FOREIGN KEY (`HcmoOrgTypeId`) REFERENCES `hcmo_org_type` (`HcmoOrgTypeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_org_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_org`
--

LOCK TABLES `hcmo_org` WRITE;
/*!40000 ALTER TABLE `hcmo_org` DISABLE KEYS */;
/*!40000 ALTER TABLE `hcmo_org` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_org_type`
--

DROP TABLE IF EXISTS `hcmo_org_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_org_type` (
  `HcmoOrgTypeId` int(11) NOT NULL auto_increment,
  `hcmoclientid` int(11) NOT NULL,
  `Name` varchar(60) NOT NULL,
  `Description` text,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  PRIMARY KEY  (`HcmoOrgTypeId`),
  UNIQUE KEY `Name` (`Name`,`IsActive`),
  KEY `fk_hcmo_org_type_updatedby` (`UpdatedBy`),
  KEY `fk_hcmo_org_type_createdby` (`CreatedBy`),
  CONSTRAINT `fk_hcmo_org_type_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_org_type_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_org_type`
--

LOCK TABLES `hcmo_org_type` WRITE;
/*!40000 ALTER TABLE `hcmo_org_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `hcmo_org_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_paymentinfo`
--

DROP TABLE IF EXISTS `hcmo_paymentinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_paymentinfo` (
  `invoice_id` varchar(45) default NULL,
  `contact_id` int(11) default NULL,
  `payr_id` varchar(25) default NULL,
  `payr_first_name` varchar(150) default NULL,
  `payr_last_name` varchar(100) default NULL,
  `payr_name` varchar(200) default NULL,
  `payr_email` varchar(250) default NULL,
  `payr_res_country` varchar(100) default NULL,
  `payr_comm_country` varchar(100) default NULL,
  `payr_comm_state` varchar(150) default NULL,
  `payr_comm_city` varchar(150) default NULL,
  `payr_comm_street` varchar(200) default NULL,
  `payr_comm_pincode` varchar(20) default NULL,
  `payr_comm_status` varchar(25) default NULL,
  `payr_country_code` varchar(10) default NULL,
  `payr_status` varchar(10) default NULL,
  `admin_email` varchar(200) default NULL,
  `receiver_id` varchar(20) default NULL,
  `rec_email` varchar(200) default NULL,
  `trans_id` varchar(50) default NULL,
  `trans_sub` varchar(300) default NULL,
  `trans_type` varchar(25) default NULL,
  `trans_action` varchar(10) default NULL,
  `item_id` varchar(11) default NULL,
  `item_name` varchar(100) default NULL,
  `mc_gross` varchar(10) default NULL,
  `mc_currency` varchar(10) default NULL,
  `mc_fee` varchar(10) default NULL,
  `tot_qty` varchar(11) default NULL,
  `pay_amnt` varchar(10) default NULL,
  `paymnt_fee` varchar(10) default NULL,
  `paymnt_gross` varchar(10) default NULL,
  `paymnt_date` varchar(25) default NULL,
  `paymnt_type` varchar(25) default NULL,
  `paymnt_stat` varchar(25) default NULL,
  `shipping_fee` varchar(10) default NULL,
  `handling_charge` varchar(10) default NULL,
  `tax` varchar(10) default NULL,
  `auth_code` varchar(250) default NULL,
  `versign_id` varchar(250) default NULL,
  `test_IPN` char(10) default NULL,
  `prot_eligibility` varchar(20) default NULL,
  `url_status` varchar(20) default NULL,
  `notify_version` char(5) default NULL,
  `charter` varchar(100) default NULL,
  `pend_response` varchar(50) default NULL,
  `comments` varchar(455) default NULL,
  `entry_datetime` timestamp NOT NULL default CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_paymentinfo`
--

LOCK TABLES `hcmo_paymentinfo` WRITE;
/*!40000 ALTER TABLE `hcmo_paymentinfo` DISABLE KEYS */;
/*!40000 ALTER TABLE `hcmo_paymentinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_paystub`
--

DROP TABLE IF EXISTS `hcmo_paystub`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_paystub` (
  `HcmoPayStubId` int(11) NOT NULL auto_increment,
  `HcmoEmployeeId` int(11) default NULL,
  `GrossSalary` double(13,2) default NULL,
  `NetSalary` double(13,2) default NULL,
  `DeclarationDate` date default NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  `hcmoclientid` int(11) default NULL,
  `empType` varchar(100) default NULL,
  `commission` double(13,2) default NULL,
  `bonus` double(13,2) default NULL,
  `regularRate` double(13,2) default NULL,
  `overtimeRate` double(13,2) default NULL,
  `isBenefit` tinyint(1) default NULL,
  `role` varchar(100) default NULL,
  `paymentType` varchar(100) default NULL,
  `firstDay` varchar(100) default NULL,
  `payDate` date default NULL,
  `payFreq` double(13,2) default NULL,
  PRIMARY KEY  (`HcmoPayStubId`),
  UNIQUE KEY `HcmoEmployeeId` (`HcmoEmployeeId`,`IsActive`),
  KEY `fk_hcmo_paystub_hcmoemployeeid` (`HcmoEmployeeId`),
  KEY `fk_hcmo_paystub_updatedby` (`UpdatedBy`),
  KEY `fk_hcmo_paystub_createdby` (`CreatedBy`),
  CONSTRAINT `fk_hcmo_paystub_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_paystub_hcmoemployeeid` FOREIGN KEY (`HcmoEmployeeId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_paystub_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_paystub`
--

LOCK TABLES `hcmo_paystub` WRITE;
/*!40000 ALTER TABLE `hcmo_paystub` DISABLE KEYS */;
/*!40000 ALTER TABLE `hcmo_paystub` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_paystub_archive`
--

DROP TABLE IF EXISTS `hcmo_paystub_archive`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_paystub_archive` (
  `HcmoPayStubArchiveId` int(11) NOT NULL auto_increment,
  `PayStub_HcmoPayStubId` int(11) default NULL,
  `PayStub_HcmoEmployeeId` int(11) default NULL,
  `PayStub_GrossSalary` double(13,2) default NULL,
  `PayStub_NetSalary` double(13,2) default NULL,
  `PayStub_DeclarationDate` date default NULL,
  `PayStub_Created` date default NULL,
  `PayStub_CreatedBy` int(11) default NULL,
  `PayStub_Updated` timestamp NULL default NULL,
  `PayStub_UpdatedBy` int(11) default NULL,
  `PayStub_IsActive` tinyint(1) default '1',
  `PayStubDeduction_Hcmo_PayStub_DeductionsId` int(11) default NULL,
  `PayStubDeduction_HcmoPayStubId` int(11) default NULL,
  `PayStubDeduction_HcmoDeductionId` int(11) default NULL,
  `PayStubDeduction_DeductionAmount` double(13,2) default NULL,
  `PayStubDeduction_EffectiveDate` date default NULL,
  `PayStubDeduction_Created` date default NULL,
  `PayStubDeduction_CreatedBy` int(11) default NULL,
  `PayStubDeduction_Updated` timestamp NULL default NULL,
  `PayStubDeduction_UpdatedBy` int(11) default NULL,
  `PayStubDeduction_IsActive` tinyint(1) default '1',
  `Deleted` timestamp NOT NULL default '0000-00-00 00:00:00',
  `DeletedBy` int(11) NOT NULL,
  `hcmoclientid` int(11) NOT NULL,
  PRIMARY KEY  (`HcmoPayStubArchiveId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_paystub_archive`
--

LOCK TABLES `hcmo_paystub_archive` WRITE;
/*!40000 ALTER TABLE `hcmo_paystub_archive` DISABLE KEYS */;
/*!40000 ALTER TABLE `hcmo_paystub_archive` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_paystub_deductions`
--

DROP TABLE IF EXISTS `hcmo_paystub_deductions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_paystub_deductions` (
  `Hcmo_PayStub_DeductionsId` int(11) NOT NULL auto_increment,
  `HcmoPayStubId` int(11) NOT NULL,
  `HcmoDeductionId` int(11) NOT NULL,
  `DeductionAmount` double(13,2) default NULL,
  `EffectiveDate` date default NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  `hcmoclientid` int(11) default NULL,
  PRIMARY KEY  (`Hcmo_PayStub_DeductionsId`),
  KEY `fk_hcmo_paystub_deductions_hcmodeductionid` (`HcmoDeductionId`),
  KEY `fk_hcmo_paystub_deductions_hcmopaystubid` (`HcmoPayStubId`),
  KEY `fk_hcmo_paystub_deductions_updatedby` (`UpdatedBy`),
  KEY `fk_hcmo_paystub_deductions_createdby` (`CreatedBy`),
  CONSTRAINT `fk_hcmo_paystub_deductions_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_paystub_deductions_hcmodeductionid` FOREIGN KEY (`HcmoDeductionId`) REFERENCES `hcmo_deductions` (`HcmoDeductionId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_paystub_deductions_hcmopaystubid` FOREIGN KEY (`HcmoPayStubId`) REFERENCES `hcmo_paystub` (`HcmoPayStubId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_paystub_deductions_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_paystub_deductions`
--

LOCK TABLES `hcmo_paystub_deductions` WRITE;
/*!40000 ALTER TABLE `hcmo_paystub_deductions` DISABLE KEYS */;
/*!40000 ALTER TABLE `hcmo_paystub_deductions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_plan`
--

DROP TABLE IF EXISTS `hcmo_plan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_plan` (
  `HcmoPlanId` int(11) NOT NULL auto_increment,
  `PlanName` varchar(255) NOT NULL default '0',
  `NoOfPerson` int(11) NOT NULL default '0',
  `SubscriptionAmount` int(11) NOT NULL default '0',
  `hcmoclientid` int(11) NOT NULL,
  PRIMARY KEY  (`HcmoPlanId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_plan`
--

LOCK TABLES `hcmo_plan` WRITE;
/*!40000 ALTER TABLE `hcmo_plan` DISABLE KEYS */;
/*!40000 ALTER TABLE `hcmo_plan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_plan_subscription`
--

DROP TABLE IF EXISTS `hcmo_plan_subscription`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_plan_subscription` (
  `HcmoPlanClientId` int(11) NOT NULL auto_increment,
  `hcmoplanid` int(11) NOT NULL default '0',
  `hcmoclientid` int(11) NOT NULL default '0',
  `subscribedDate` date NOT NULL default '0000-00-00',
  `SubscriptionEndDate` date NOT NULL default '0000-00-00',
  `RenewedCount` int(11) NOT NULL default '0',
  `TrialCountDays` int(11) NOT NULL default '0',
  `isActive` int(2) default NULL,
  PRIMARY KEY  (`HcmoPlanClientId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_plan_subscription`
--

LOCK TABLES `hcmo_plan_subscription` WRITE;
/*!40000 ALTER TABLE `hcmo_plan_subscription` DISABLE KEYS */;
/*!40000 ALTER TABLE `hcmo_plan_subscription` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_project`
--

DROP TABLE IF EXISTS `hcmo_project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_project` (
  `HcmoProjectId` int(11) NOT NULL auto_increment,
  `HcmoCustomerId` int(11) NOT NULL,
  `ProjectOwner` int(11) NOT NULL,
  `Name` varchar(120) default NULL,
  `Description` text,
  `EstimatedHours` varchar(255) default NULL,
  `Deleted` tinyint(1) default '0',
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  `hcmoclientid` int(11) default NULL,
  PRIMARY KEY  (`HcmoProjectId`),
  UNIQUE KEY `Name` (`Name`,`IsActive`),
  KEY `fk_hcmo_project_hcmocustomerid` (`HcmoCustomerId`),
  KEY `fk_hcmo_project_projectowner` (`ProjectOwner`),
  KEY `fk_hcmo_project_updatedby` (`UpdatedBy`),
  KEY `fk_hcmo_project_createdby` (`CreatedBy`),
  CONSTRAINT `fk_hcmo_project_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_project_hcmocustomerid` FOREIGN KEY (`HcmoCustomerId`) REFERENCES `hcmo_customer` (`HcmoCustomerId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_project_projectowner` FOREIGN KEY (`ProjectOwner`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_project_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_project`
--

LOCK TABLES `hcmo_project` WRITE;
/*!40000 ALTER TABLE `hcmo_project` DISABLE KEYS */;
/*!40000 ALTER TABLE `hcmo_project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_project_activity`
--

DROP TABLE IF EXISTS `hcmo_project_activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_project_activity` (
  `HcmoProjectActivityId` int(11) NOT NULL auto_increment,
  `HcmoProjectId` int(11) NOT NULL,
  `ActivityName` varchar(120) default NULL,
  `ProjectActivityOwner` int(11) default NULL,
  `Notes` text,
  `EstimatedHours` varchar(255) default NULL,
  `Deleted` tinyint(1) NOT NULL default '0',
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  `hcmoclientid` int(11) default NULL,
  `HcmoDepartmentId` int(11) NOT NULL,
  `HcmoEmployeeId` int(11) NOT NULL,
  `taskStartDate` date default NULL,
  `taskEndDate` date default NULL,
  `description` varchar(255) default NULL,
  PRIMARY KEY  (`HcmoProjectActivityId`),
  KEY `fk_hcmo_project_activity_hcmoprojectid` (`HcmoProjectId`),
  KEY `fk_hcmo_project_activity_projectactivityowner` (`ProjectActivityOwner`),
  KEY `fk_hcmo_project_activity_updatedby` (`UpdatedBy`),
  KEY `fk_hcmo_project_activity_createdby` (`CreatedBy`),
  KEY `fk_hcmo_project_activity_hcmodepartmentid` (`HcmoDepartmentId`),
  KEY `fk_hcmo_project_activity_hcmoemployeeid` (`HcmoEmployeeId`),
  CONSTRAINT `fk_hcmo_project_activity_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_project_activity_hcmodepartmentid` FOREIGN KEY (`HcmoDepartmentId`) REFERENCES `hcmo_department` (`HcmoDepartmentId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_project_activity_hcmoemployeeid` FOREIGN KEY (`HcmoEmployeeId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_project_activity_hcmoprojectid` FOREIGN KEY (`HcmoProjectId`) REFERENCES `hcmo_project` (`HcmoProjectId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_project_activity_projectactivityowner` FOREIGN KEY (`ProjectActivityOwner`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_project_activity_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_project_activity`
--

LOCK TABLES `hcmo_project_activity` WRITE;
/*!40000 ALTER TABLE `hcmo_project_activity` DISABLE KEYS */;
/*!40000 ALTER TABLE `hcmo_project_activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_question`
--

DROP TABLE IF EXISTS `hcmo_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_question` (
  `HcmoQuestionId` int(11) NOT NULL auto_increment,
  `HcmoCategoryId` int(11) NOT NULL,
  `HcmoSubCategoryId` int(11) NOT NULL,
  `Question` varchar(100) NOT NULL,
  `QuestionType` varchar(100) NOT NULL,
  `Option1` varchar(100) default NULL,
  `Option2` varchar(100) default NULL,
  `Option3` varchar(100) default NULL,
  `Option4` varchar(100) default NULL,
  `Option5` varchar(100) default NULL,
  `Option6` varchar(100) default NULL,
  `MinRate` varchar(100) default NULL,
  `MaxRate` varchar(100) default NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  `hcmoclientid` int(11) NOT NULL,
  PRIMARY KEY  (`HcmoQuestionId`),
  KEY `fk_hcmo_question_updatedby` (`UpdatedBy`),
  KEY `fk_hcmo_question_createdby` (`CreatedBy`),
  KEY `fk_hcmo_question_hcmosubcategoryid` (`HcmoSubCategoryId`),
  KEY `fk_hcmo_question_hcmocategoryid` (`HcmoCategoryId`),
  CONSTRAINT `fk_hcmo_question_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_question_hcmocategoryid` FOREIGN KEY (`HcmoCategoryId`) REFERENCES `hcmo_category` (`HcmoCategoryId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_question_hcmosubcategoryid` FOREIGN KEY (`HcmoSubCategoryId`) REFERENCES `hcmo_sub_category` (`HcmoSubCategoryId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_question_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_question`
--

LOCK TABLES `hcmo_question` WRITE;
/*!40000 ALTER TABLE `hcmo_question` DISABLE KEYS */;
/*!40000 ALTER TABLE `hcmo_question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_question_general_info`
--

DROP TABLE IF EXISTS `hcmo_question_general_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_question_general_info` (
  `HcmoQuestionGeneralInfoId` int(11) NOT NULL auto_increment,
  `HcmoApprovingEmployeeId` int(11) default NULL,
  `HcmoAdminId` int(11) default NULL,
  `HcmoQuestionGeneralInfoGroupId` int(11) NOT NULL,
  `HcmoQuestionGroupNameId` int(11) default NULL,
  `HcmoQuestionGroupNameIdentifiId` int(11) NOT NULL,
  `HcmoDepartmentId` int(11) NOT NULL,
  `HcmoJobTitleId` int(11) NOT NULL,
  `HcmoTeamId` int(11) NOT NULL,
  `ApprovingEmpComments` varchar(100) default NULL,
  `AdminComments` varchar(100) default NULL,
  `EmployeeType` varchar(100) NOT NULL,
  `PerformanceIndStartDate` varchar(100) NOT NULL,
  `PerformanceIndEndDate` varchar(100) NOT NULL,
  `HcmoEmployeeId` int(11) NOT NULL,
  `PeerEmployeeId` int(11) default NULL,
  `Status` varchar(100) default NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  `hcmoclientid` int(11) NOT NULL,
  PRIMARY KEY  (`HcmoQuestionGeneralInfoId`),
  KEY `fk_hcmo_question_general_info_updatedby` (`UpdatedBy`),
  KEY `fk_hcmo_question_general_info_createdby` (`CreatedBy`),
  KEY `fk_hcmo_question_general_info_hcmoquestiongroupnameid` (`HcmoQuestionGroupNameId`),
  KEY `fk_hcmo_question_general_info_hcmodepartmentid` (`HcmoDepartmentId`),
  KEY `fk_hcmo_question_general_info_hcmojobtitleid` (`HcmoJobTitleId`),
  KEY `fk_hcmo_question_general_info_hcmoteamid` (`HcmoTeamId`),
  KEY `fk_hcmo_question_general_info_hcmoemployeeid` (`HcmoEmployeeId`),
  KEY `fk_hcmo_question_general_info_peeremployeeid` (`PeerEmployeeId`),
  KEY `fk_hcmo_question_general_info_hcmoApprovingEmployeeId` (`HcmoApprovingEmployeeId`),
  KEY `fk_hcmo_question_general_info_hcmoquestiongroupnameidentifiid` (`HcmoQuestionGroupNameIdentifiId`),
  KEY `fk_hcmo_question_general_info_hcmoquestiongeneralinfogroupid` (`HcmoQuestionGeneralInfoGroupId`),
  CONSTRAINT `fk_hcmo_question_general_info_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_question_general_info_hcmoApprovingEmployeeId` FOREIGN KEY (`HcmoApprovingEmployeeId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_question_general_info_hcmodepartmentid` FOREIGN KEY (`HcmoDepartmentId`) REFERENCES `hcmo_department` (`HcmoDepartmentId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_question_general_info_hcmoemployeeid` FOREIGN KEY (`HcmoEmployeeId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_question_general_info_hcmojobtitleid` FOREIGN KEY (`HcmoJobTitleId`) REFERENCES `hcmo_job_title` (`HcmoJobTitleId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_question_general_info_hcmoquestiongeneralinfogroupid` FOREIGN KEY (`HcmoQuestionGeneralInfoGroupId`) REFERENCES `hcmo_question_general_info_group` (`HcmoQuestionGeneralInfoGroupId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_question_general_info_hcmoquestiongroupnameid` FOREIGN KEY (`HcmoQuestionGroupNameId`) REFERENCES `hcmo_question_group_name` (`HcmoQuestionGroupNameId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_question_general_info_hcmoquestiongroupnameidentifiid` FOREIGN KEY (`HcmoQuestionGroupNameIdentifiId`) REFERENCES `hcmo_question_group_name_identification` (`HcmoQuestionGroupNameIdentificationId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_question_general_info_hcmoteamid` FOREIGN KEY (`HcmoTeamId`) REFERENCES `hcmo_team` (`HcmoTeamId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_question_general_info_peeremployeeid` FOREIGN KEY (`PeerEmployeeId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_question_general_info_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_question_general_info`
--

LOCK TABLES `hcmo_question_general_info` WRITE;
/*!40000 ALTER TABLE `hcmo_question_general_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `hcmo_question_general_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_question_general_info_group`
--

DROP TABLE IF EXISTS `hcmo_question_general_info_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_question_general_info_group` (
  `HcmoQuestionGeneralInfoGroupId` int(11) NOT NULL auto_increment,
  `Name` varchar(100) NOT NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  `hcmoclientid` int(11) NOT NULL,
  PRIMARY KEY  (`HcmoQuestionGeneralInfoGroupId`),
  KEY `fk_hcmo_employee_assigned_question_group_updatedby` (`UpdatedBy`),
  KEY `fk_hcmo_employee_assigned_question_group_createdby` (`CreatedBy`),
  CONSTRAINT `fk_hcmo_employee_assigned_question_group_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_employee_assigned_question_group_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_question_general_info_group`
--

LOCK TABLES `hcmo_question_general_info_group` WRITE;
/*!40000 ALTER TABLE `hcmo_question_general_info_group` DISABLE KEYS */;
/*!40000 ALTER TABLE `hcmo_question_general_info_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_question_group_name`
--

DROP TABLE IF EXISTS `hcmo_question_group_name`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_question_group_name` (
  `HcmoQuestionGroupNameId` int(11) NOT NULL auto_increment,
  `HcmoQuestionId` int(11) NOT NULL,
  `HcmoQuestionGroupNameIdentifiId` int(11) NOT NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  `hcmoclientid` int(11) NOT NULL,
  PRIMARY KEY  (`HcmoQuestionGroupNameId`),
  KEY `fk_hcmo_question_group_name_updatedby` (`UpdatedBy`),
  KEY `fk_hcmo_question_group_name_createdby` (`CreatedBy`),
  KEY `fk_hcmo_question_group_name_hcmoquestionid` (`HcmoQuestionId`),
  KEY `fk_hcmo_question_group_name_hcmoQuestionGroupNameIdentifiId` (`HcmoQuestionGroupNameIdentifiId`),
  CONSTRAINT `fk_hcmo_question_group_name_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_question_group_name_hcmoQuestionGroupNameIdentifiId` FOREIGN KEY (`HcmoQuestionGroupNameIdentifiId`) REFERENCES `hcmo_question_group_name_identification` (`HcmoQuestionGroupNameIdentificationId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_question_group_name_hcmoquestionid` FOREIGN KEY (`HcmoQuestionId`) REFERENCES `hcmo_question` (`HcmoQuestionId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_question_group_name_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_question_group_name`
--

LOCK TABLES `hcmo_question_group_name` WRITE;
/*!40000 ALTER TABLE `hcmo_question_group_name` DISABLE KEYS */;
/*!40000 ALTER TABLE `hcmo_question_group_name` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_question_group_name_identification`
--

DROP TABLE IF EXISTS `hcmo_question_group_name_identification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_question_group_name_identification` (
  `HcmoQuestionGroupNameIdentificationId` int(11) NOT NULL auto_increment,
  `Name` varchar(100) NOT NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  `hcmoclientid` int(11) NOT NULL,
  PRIMARY KEY  (`HcmoQuestionGroupNameIdentificationId`),
  KEY `fk_hcmo_question_group_name_identification_updatedby` (`UpdatedBy`),
  KEY `fk_hcmo_question_group_name_identification_createdby` (`CreatedBy`),
  CONSTRAINT `fk_hcmo_question_group_name_identification_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_question_group_name_identification_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_question_group_name_identification`
--

LOCK TABLES `hcmo_question_group_name_identification` WRITE;
/*!40000 ALTER TABLE `hcmo_question_group_name_identification` DISABLE KEYS */;
/*!40000 ALTER TABLE `hcmo_question_group_name_identification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_recurring_profile`
--

DROP TABLE IF EXISTS `hcmo_recurring_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_recurring_profile` (
  `recurring_prof_id` int(11) NOT NULL auto_increment,
  `hcmoclientid` int(11) NOT NULL,
  `payment_gateway` varchar(30) default NULL,
  `merchant_gateway` varchar(50) default NULL COMMENT 'Merchant getway for engine',
  `paymnt_request_type` int(11) default NULL COMMENT '1-Premium subscription,2-Job referral',
  `bill_agreemntid` varchar(50) default NULL,
  `bill_agreemnt_status` varchar(30) default NULL,
  `name` varchar(70) default NULL,
  `email` varchar(100) default NULL,
  `currencycode` varchar(10) default NULL,
  `country_code` varchar(100) default NULL,
  `country_name` varchar(100) default NULL,
  `city` varchar(100) default NULL,
  `street` varchar(150) default NULL,
  `payr_verification_status` varchar(30) default NULL,
  `token_id` varchar(50) default NULL,
  `tot_qty` int(11) default NULL,
  `paymnt_status` varchar(30) default NULL,
  `ack_status` varchar(30) default NULL,
  `recurring_status` int(11) default NULL COMMENT '1-enable,2diable',
  `entry_datetime` datetime default NULL,
  `update_datetime` datetime default NULL,
  PRIMARY KEY  (`recurring_prof_id`),
  UNIQUE KEY `bill_agreemntid` (`hcmoclientid`,`bill_agreemntid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_recurring_profile`
--

LOCK TABLES `hcmo_recurring_profile` WRITE;
/*!40000 ALTER TABLE `hcmo_recurring_profile` DISABLE KEYS */;
/*!40000 ALTER TABLE `hcmo_recurring_profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_region`
--

DROP TABLE IF EXISTS `hcmo_region`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_region` (
  `HcmoRegionId` int(11) NOT NULL auto_increment,
  `HcmoCountryId` int(11) NOT NULL,
  `ZipCode` varchar(50) NOT NULL default '',
  `City` varchar(100) NOT NULL default '',
  `Name` varchar(100) NOT NULL,
  `RegionCode` varchar(5) NOT NULL,
  `CountyField` varchar(100) default NULL,
  `AreaCode` varchar(50) default '',
  `TimeZone` varchar(50) default '',
  `Latitude` varchar(50) default '',
  `Longitude` varchar(50) default '',
  `IsActive` tinyint(1) NOT NULL default '1',
  `hcmoclientid` int(11) NOT NULL,
  PRIMARY KEY  (`HcmoRegionId`),
  KEY `fk_hcmo_region_hcmocountryid` (`HcmoCountryId`),
  CONSTRAINT `fk_hcmo_region_hcmocountryid` FOREIGN KEY (`HcmoCountryId`) REFERENCES `hcmo_country` (`HcmoCountryId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_region`
--

LOCK TABLES `hcmo_region` WRITE;
/*!40000 ALTER TABLE `hcmo_region` DISABLE KEYS */;
/*!40000 ALTER TABLE `hcmo_region` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_role`
--

DROP TABLE IF EXISTS `hcmo_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_role` (
  `HcmoRoleId` int(11) NOT NULL auto_increment,
  `RoleName` varchar(45) default NULL,
  `XmlPath` varchar(500) default NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  `hcmoclientid` int(11) default NULL,
  PRIMARY KEY  (`HcmoRoleId`),
  UNIQUE KEY `RoleName` (`RoleName`,`IsActive`),
  KEY `fk_hcmo_role_updatedby` (`UpdatedBy`),
  KEY `fk_hcmo_role_createdby` (`CreatedBy`),
  CONSTRAINT `fk_hcmo_role_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_role_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_role`
--

LOCK TABLES `hcmo_role` WRITE;
/*!40000 ALTER TABLE `hcmo_role` DISABLE KEYS */;
INSERT INTO `hcmo_role` VALUES (1,'admin','Admin.xml','2015-12-28',1,'2015-12-28 09:08:00',1,1,1),(2,'HR','','2016-06-14',1,'2016-06-14 12:13:58',1,0,0),(6,'Communication','Communication.xml','2016-06-14',1,'2016-06-14 12:15:13',1,0,0),(7,'Developement','Developement.xml','2016-06-14',1,'2016-06-14 12:00:29',1,1,0);
/*!40000 ALTER TABLE `hcmo_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_saas_contract`
--

DROP TABLE IF EXISTS `hcmo_saas_contract`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_saas_contract` (
  `HcmoSaasContractId` int(11) NOT NULL auto_increment,
  `hcmoclientid` int(11) NOT NULL,
  `SaasName` varchar(255) default NULL,
  `SaasType` varchar(255) default NULL,
  `SaasSize` int(11) default '0',
  `CompanyName` varchar(255) default NULL,
  `PersonName` varchar(255) default NULL,
  `Designation` varchar(255) default NULL,
  `SaasSignDate` datetime default NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  PRIMARY KEY  (`HcmoSaasContractId`),
  KEY `hcmo_saas_contract_createdby` (`CreatedBy`),
  KEY `hcmo_saas_contract_updatedby` (`UpdatedBy`),
  CONSTRAINT `hcmo_saas_contract_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `hcmo_saas_contract_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_saas_contract`
--

LOCK TABLES `hcmo_saas_contract` WRITE;
/*!40000 ALTER TABLE `hcmo_saas_contract` DISABLE KEYS */;
INSERT INTO `hcmo_saas_contract` VALUES (1,0,'Saas_Contract_localhost_2016-05-30.pdf','application/pdf',4921,'gits','bala','dir','2016-05-30 00:00:00','2016-05-30',1,'2016-05-30 12:24:25',1,1);
/*!40000 ALTER TABLE `hcmo_saas_contract` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_salary`
--

DROP TABLE IF EXISTS `hcmo_salary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_salary` (
  `HcmoSalaryId` int(11) NOT NULL auto_increment,
  `HcmoEmployeeId` int(11) NOT NULL,
  `DeclarationDate` datetime NOT NULL,
  `Salary` double(13,2) NOT NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  `hcmoclientid` int(11) NOT NULL,
  PRIMARY KEY  (`HcmoSalaryId`),
  KEY `fk_hcmo_salary_hcmoemployeeid` (`HcmoEmployeeId`),
  KEY `fk_hcmo_salary_updatedby` (`UpdatedBy`),
  KEY `fk_hcmo_salary_createdby` (`CreatedBy`),
  CONSTRAINT `fk_hcmo_salary_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_salary_hcmoemployeeid` FOREIGN KEY (`HcmoEmployeeId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_salary_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_salary`
--

LOCK TABLES `hcmo_salary` WRITE;
/*!40000 ALTER TABLE `hcmo_salary` DISABLE KEYS */;
/*!40000 ALTER TABLE `hcmo_salary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_salary_grade`
--

DROP TABLE IF EXISTS `hcmo_salary_grade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_salary_grade` (
  `HcmoSalaryGradeId` int(11) NOT NULL auto_increment,
  `SalGrdName` varchar(60) NOT NULL,
  `hcmoclientid` int(11) NOT NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  PRIMARY KEY  (`HcmoSalaryGradeId`),
  UNIQUE KEY `SalGrdName` (`SalGrdName`,`IsActive`),
  KEY `fk_hcmo_salary_grade_updatedby` (`UpdatedBy`),
  KEY `fk_hcmo_salary_grade_createdby` (`CreatedBy`),
  CONSTRAINT `fk_hcmo_salary_grade_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_salary_grade_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_salary_grade`
--

LOCK TABLES `hcmo_salary_grade` WRITE;
/*!40000 ALTER TABLE `hcmo_salary_grade` DISABLE KEYS */;
INSERT INTO `hcmo_salary_grade` VALUES (1,'A-Grade',1,'2015-12-28',1,'2015-12-28 12:18:51',1,1);
/*!40000 ALTER TABLE `hcmo_salary_grade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_signature`
--

DROP TABLE IF EXISTS `hcmo_signature`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_signature` (
  `HcmoSignatureId` int(11) NOT NULL auto_increment,
  `HcmoEmployeeId` int(11) NOT NULL,
  `Signature` text,
  `PreferedSignature` tinyint(1) default NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  `hcmoclientid` int(11) NOT NULL,
  PRIMARY KEY  (`HcmoSignatureId`),
  KEY `fk_hcmo_signature_hcmoemployeeid` (`HcmoEmployeeId`),
  KEY `fk_hcmo_signature_updatedby` (`UpdatedBy`),
  KEY `fk_hcmo_signature_createdby` (`CreatedBy`),
  CONSTRAINT `fk_hcmo_signature_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_signature_hcmoemployeeid` FOREIGN KEY (`HcmoEmployeeId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_signature_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_signature`
--

LOCK TABLES `hcmo_signature` WRITE;
/*!40000 ALTER TABLE `hcmo_signature` DISABLE KEYS */;
/*!40000 ALTER TABLE `hcmo_signature` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_sub_category`
--

DROP TABLE IF EXISTS `hcmo_sub_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_sub_category` (
  `HcmoSubCategoryId` int(11) NOT NULL auto_increment,
  `HcmoCategoryId` int(11) NOT NULL,
  `SubCategoryName` varchar(100) NOT NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  `hcmoclientid` int(11) NOT NULL,
  PRIMARY KEY  (`HcmoSubCategoryId`),
  KEY `fk_hcmo_sub_category_updatedby` (`UpdatedBy`),
  KEY `fk_hcmo_sub_category_createdby` (`CreatedBy`),
  KEY `fk_hcmo_sub_category_hcmocategoryid` (`HcmoCategoryId`),
  CONSTRAINT `fk_hcmo_sub_category_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_sub_category_hcmocategoryid` FOREIGN KEY (`HcmoCategoryId`) REFERENCES `hcmo_category` (`HcmoCategoryId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_sub_category_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_sub_category`
--

LOCK TABLES `hcmo_sub_category` WRITE;
/*!40000 ALTER TABLE `hcmo_sub_category` DISABLE KEYS */;
/*!40000 ALTER TABLE `hcmo_sub_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_support`
--

DROP TABLE IF EXISTS `hcmo_support`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_support` (
  `HcmoSupportId` int(11) NOT NULL auto_increment,
  `hcmoclientid` int(11) NOT NULL,
  `SupportPriority` varchar(60) default NULL,
  `Subject` varchar(255) NOT NULL,
  `Message` text,
  `AttachFileName` varchar(100) default NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  PRIMARY KEY  (`HcmoSupportId`),
  KEY `fk_hcmo_support_updatedby` (`UpdatedBy`),
  KEY `fk_hcmo_support_createdby` (`CreatedBy`),
  CONSTRAINT `fk_hcmo_support_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_support_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_support`
--

LOCK TABLES `hcmo_support` WRITE;
/*!40000 ALTER TABLE `hcmo_support` DISABLE KEYS */;
/*!40000 ALTER TABLE `hcmo_support` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_target`
--

DROP TABLE IF EXISTS `hcmo_target`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_target` (
  `HcmoTargetId` int(11) NOT NULL auto_increment,
  `HcmoProjectId` int(11) NOT NULL,
  `HcmoProjectActivityId` int(11) NOT NULL,
  `HcmoTargetTypeId` int(11) NOT NULL,
  `TargetName` varchar(120) NOT NULL,
  `TargetMode` varchar(120) NOT NULL,
  `TargetValue` varchar(120) NOT NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  `hcmoclientid` int(11) NOT NULL,
  PRIMARY KEY  (`HcmoTargetId`),
  UNIQUE KEY `TargetName` (`TargetName`,`IsActive`),
  KEY `fk_hcmo_target_projectid` (`HcmoProjectId`),
  KEY `fk_hcmo_target_projectactivityid` (`HcmoProjectActivityId`),
  KEY `fk_hcmo_target_hcmotargettypeid` (`HcmoTargetTypeId`),
  KEY `fk_hcmo_target_updatedby` (`UpdatedBy`),
  KEY `fk_hcmo_target_createdby` (`CreatedBy`),
  CONSTRAINT `fk_hcmo_target_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_target_hcmotargettypeid` FOREIGN KEY (`HcmoTargetTypeId`) REFERENCES `hcmo_target_type` (`HcmoTargetTypeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_target_projectactivityid` FOREIGN KEY (`HcmoProjectActivityId`) REFERENCES `hcmo_project_activity` (`HcmoProjectActivityId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_target_projectid` FOREIGN KEY (`HcmoProjectId`) REFERENCES `hcmo_project` (`HcmoProjectId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_target_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_target`
--

LOCK TABLES `hcmo_target` WRITE;
/*!40000 ALTER TABLE `hcmo_target` DISABLE KEYS */;
/*!40000 ALTER TABLE `hcmo_target` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_target_type`
--

DROP TABLE IF EXISTS `hcmo_target_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_target_type` (
  `HcmoTargetTypeId` int(11) NOT NULL auto_increment,
  `TargetTypeName` varchar(120) NOT NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  `hcmoclientid` int(11) NOT NULL,
  PRIMARY KEY  (`HcmoTargetTypeId`),
  UNIQUE KEY `TargetTypeName` (`HcmoTargetTypeId`,`IsActive`),
  KEY `fk_hcmo_target_type_createdby` (`CreatedBy`),
  KEY `fk_hcmo_target_type_updatedby` (`UpdatedBy`),
  CONSTRAINT `fk_hcmo_target_type_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_target_type_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_target_type`
--

LOCK TABLES `hcmo_target_type` WRITE;
/*!40000 ALTER TABLE `hcmo_target_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `hcmo_target_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_team`
--

DROP TABLE IF EXISTS `hcmo_team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_team` (
  `HcmoTeamId` int(11) NOT NULL auto_increment,
  `hcmoclientid` int(11) NOT NULL,
  `TeamName` varchar(120) NOT NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  PRIMARY KEY  (`HcmoTeamId`),
  UNIQUE KEY `TeamName` (`TeamName`,`IsActive`),
  KEY `fk_hcmo_team_updatedby` (`UpdatedBy`),
  KEY `fk_hcmo_team_createdby` (`CreatedBy`),
  CONSTRAINT `fk_hcmo_team_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_team_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_team`
--

LOCK TABLES `hcmo_team` WRITE;
/*!40000 ALTER TABLE `hcmo_team` DISABLE KEYS */;
INSERT INTO `hcmo_team` VALUES (1,1,'Management','2015-12-28',1,'2015-12-28 12:37:37',1,1);
/*!40000 ALTER TABLE `hcmo_team` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_timesheet_notes`
--

DROP TABLE IF EXISTS `hcmo_timesheet_notes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_timesheet_notes` (
  `HcmoTsNotesId` int(11) NOT NULL auto_increment,
  `HcmoEmployeeId` int(11) NOT NULL,
  `Date` date NOT NULL,
  `Notes` text NOT NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  `hcmoclientid` int(11) NOT NULL,
  PRIMARY KEY  (`HcmoTsNotesId`),
  UNIQUE KEY `EmpDate` (`HcmoEmployeeId`,`Date`),
  KEY `fk_hcmo_timesheet_notes_updatedby` (`UpdatedBy`),
  KEY `fk_hcmo_timesheet_notes_createdby` (`CreatedBy`),
  CONSTRAINT `fk_hcmo_timesheet_hcmoemployeeid` FOREIGN KEY (`HcmoEmployeeId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_timesheet_notes_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_timesheet_notes_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_timesheet_notes`
--

LOCK TABLES `hcmo_timesheet_notes` WRITE;
/*!40000 ALTER TABLE `hcmo_timesheet_notes` DISABLE KEYS */;
/*!40000 ALTER TABLE `hcmo_timesheet_notes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_timesheetcat`
--

DROP TABLE IF EXISTS `hcmo_timesheetcat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_timesheetcat` (
  `hcmo_timesheetcat_id` int(11) NOT NULL auto_increment,
  `hcmoclientid` int(11) NOT NULL,
  `name` varchar(60) default NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  PRIMARY KEY  (`hcmo_timesheetcat_id`),
  UNIQUE KEY `name` (`name`),
  KEY `fk_hcmo_timesheetcat_updatedby` (`UpdatedBy`),
  KEY `fk_hcmo_timesheetcat_createdby` (`CreatedBy`),
  CONSTRAINT `fk_hcmo_timesheetcat_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_timesheetcat_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_timesheetcat`
--

LOCK TABLES `hcmo_timesheetcat` WRITE;
/*!40000 ALTER TABLE `hcmo_timesheetcat` DISABLE KEYS */;
/*!40000 ALTER TABLE `hcmo_timesheetcat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_ts_achieved_target`
--

DROP TABLE IF EXISTS `hcmo_ts_achieved_target`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_ts_achieved_target` (
  `HcmoTsAchivedTargetId` int(11) NOT NULL auto_increment,
  `HcmoTsAssignedTargetId` int(11) NOT NULL,
  `Date` date default NULL,
  `StartDate` date default NULL,
  `EndDate` date default NULL,
  `Status` varchar(100) default NULL,
  `TargetAchieved` text,
  `TargetNotes` text,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  `hcmoclientid` int(11) NOT NULL,
  PRIMARY KEY  (`HcmoTsAchivedTargetId`),
  KEY `fk_hcmo_ts_achieved_target_hcmotsassignedtargetid` (`HcmoTsAssignedTargetId`),
  KEY `fk_hcmo_ts_achieved_target_updatedby` (`UpdatedBy`),
  KEY `fk_hcmo_ts_achieved_target_createdby` (`CreatedBy`),
  CONSTRAINT `fk_hcmo_ts_achieved_target_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_ts_achieved_target_hcmotsassignedtargetid` FOREIGN KEY (`HcmoTsAssignedTargetId`) REFERENCES `hcmo_ts_assign_proj_target` (`HcmoTsAssignProjTargetId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_ts_achieved_target_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_ts_achieved_target`
--

LOCK TABLES `hcmo_ts_achieved_target` WRITE;
/*!40000 ALTER TABLE `hcmo_ts_achieved_target` DISABLE KEYS */;
/*!40000 ALTER TABLE `hcmo_ts_achieved_target` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_ts_approver`
--

DROP TABLE IF EXISTS `hcmo_ts_approver`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_ts_approver` (
  `HcmoApproverId` int(11) NOT NULL auto_increment,
  `HcmoEmployeeId` int(11) NOT NULL,
  `HcmoApprovingEmpId` int(11) NOT NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  `hcmoclientid` int(11) default NULL,
  PRIMARY KEY  (`HcmoApproverId`),
  KEY `fk_hcmo_ts_approver_hcmoemployeeid` (`HcmoEmployeeId`),
  KEY `fk_hcmo_ts_approver_hcmoapprovingempid` (`HcmoApprovingEmpId`),
  KEY `fk_hcmo_ts_approver_updatedby` (`UpdatedBy`),
  KEY `fk_hcmo_ts_approver_createdby` (`CreatedBy`),
  CONSTRAINT `fk_hcmo_ts_approver_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_ts_approver_hcmoapprovingempid` FOREIGN KEY (`HcmoApprovingEmpId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_ts_approver_hcmoemployeeid` FOREIGN KEY (`HcmoEmployeeId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_ts_approver_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_ts_approver`
--

LOCK TABLES `hcmo_ts_approver` WRITE;
/*!40000 ALTER TABLE `hcmo_ts_approver` DISABLE KEYS */;
/*!40000 ALTER TABLE `hcmo_ts_approver` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_ts_assign_proj_target`
--

DROP TABLE IF EXISTS `hcmo_ts_assign_proj_target`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_ts_assign_proj_target` (
  `HcmoTsAssignProjTargetId` int(11) NOT NULL auto_increment,
  `HcmoTsEmpProjRelId` int(11) NOT NULL,
  `HcmoTargetId` int(11) default NULL,
  `HcmoEmployeeId` int(11) NOT NULL,
  `TargetAchieved` text,
  `TargetNotes` text,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  `hcmoclientid` int(11) NOT NULL,
  PRIMARY KEY  (`HcmoTsAssignProjTargetId`),
  KEY `fk_hcmo_ts_assign_proj_target_hcmotsempprojrelid` (`HcmoTsEmpProjRelId`),
  KEY `fk_hcmo_ts_assign_proj_target_hcmotargetid` (`HcmoTargetId`),
  KEY `fk_hcmo_ts_assign_proj_target_hcmoemployeeid` (`HcmoEmployeeId`),
  KEY `fk_hcmo_ts_assign_proj_target_updatedby` (`UpdatedBy`),
  KEY `fk_hcmo_ts_assign_proj_target_createdby` (`CreatedBy`),
  CONSTRAINT `fk_hcmo_ts_assign_proj_target_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_ts_assign_proj_target_hcmoemployeeid` FOREIGN KEY (`HcmoEmployeeId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_ts_assign_proj_target_hcmotargetid` FOREIGN KEY (`HcmoTargetId`) REFERENCES `hcmo_target` (`HcmoTargetId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_ts_assign_proj_target_hcmotsempprojrelid` FOREIGN KEY (`HcmoTsEmpProjRelId`) REFERENCES `hcmo_ts_emp_proj_rel` (`HcmoTsEmpProjRelId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_ts_assign_proj_target_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_ts_assign_proj_target`
--

LOCK TABLES `hcmo_ts_assign_proj_target` WRITE;
/*!40000 ALTER TABLE `hcmo_ts_assign_proj_target` DISABLE KEYS */;
/*!40000 ALTER TABLE `hcmo_ts_assign_proj_target` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_ts_attachment`
--

DROP TABLE IF EXISTS `hcmo_ts_attachment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_ts_attachment` (
  `HcmoTsAttachmentId` int(11) NOT NULL auto_increment,
  `HcmoEmployeeId` int(11) default NULL,
  `HcmoProjectId` int(11) default NULL,
  `Year` int(4) default NULL,
  `Month` varchar(20) default NULL,
  `Week` int(1) default NULL,
  `FileName` varchar(250) default NULL,
  `Location` varchar(1000) default NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  `hcmoclientid` int(11) NOT NULL,
  PRIMARY KEY  (`HcmoTsAttachmentId`),
  KEY `fk_hcmo_ts_attachment_hcmoemployeeid` (`HcmoEmployeeId`),
  KEY `fk_hcmo_ts_attachment_hcmoprojectid` (`HcmoProjectId`),
  KEY `fk_hcmo_ts_attachment_updatedby` (`UpdatedBy`),
  KEY `fk_hcmo_ts_attachment_createdby` (`CreatedBy`),
  CONSTRAINT `fk_hcmo_ts_attachment_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_ts_attachment_hcmoemployeeid` FOREIGN KEY (`HcmoEmployeeId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_ts_attachment_hcmoprojectid` FOREIGN KEY (`HcmoProjectId`) REFERENCES `hcmo_project` (`HcmoProjectId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_ts_attachment_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_ts_attachment`
--

LOCK TABLES `hcmo_ts_attachment` WRITE;
/*!40000 ALTER TABLE `hcmo_ts_attachment` DISABLE KEYS */;
/*!40000 ALTER TABLE `hcmo_ts_attachment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_ts_detail`
--

DROP TABLE IF EXISTS `hcmo_ts_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_ts_detail` (
  `hcmo_ts_detail` int(11) NOT NULL auto_increment,
  `employee_id` int(11) default NULL,
  `category_id` int(11) default NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  `hcmoclientid` int(11) NOT NULL,
  PRIMARY KEY  (`hcmo_ts_detail`),
  UNIQUE KEY `employee_id` (`employee_id`,`category_id`),
  KEY `fk_hcmo_ts_detail_updatedby` (`UpdatedBy`),
  KEY `fk_hcmo_ts_detail_createdby` (`CreatedBy`),
  KEY `fk_hcmo_ts_detail_categoryid` (`category_id`),
  KEY `fk_hcmo_ts_detail_employeeid` (`employee_id`),
  CONSTRAINT `fk_hcmo_ts_detail_categoryid` FOREIGN KEY (`category_id`) REFERENCES `hcmo_timesheetcat` (`hcmo_timesheetcat_id`),
  CONSTRAINT `fk_hcmo_ts_detail_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`),
  CONSTRAINT `fk_hcmo_ts_detail_employeeid` FOREIGN KEY (`employee_id`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`),
  CONSTRAINT `fk_hcmo_ts_detail_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_ts_detail`
--

LOCK TABLES `hcmo_ts_detail` WRITE;
/*!40000 ALTER TABLE `hcmo_ts_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `hcmo_ts_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_ts_emp_proj_rel`
--

DROP TABLE IF EXISTS `hcmo_ts_emp_proj_rel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_ts_emp_proj_rel` (
  `HcmoTsEmpProjRelId` int(11) NOT NULL auto_increment,
  `HcmoEmployeeId` int(11) default NULL,
  `HcmoProjectId` int(11) default NULL,
  `HcmoProjectActivityId` int(11) default NULL,
  `ProjTargetName` varchar(120) default NULL,
  `ProjTargetType` varchar(120) default NULL,
  `ProjTargetMode` varchar(120) default NULL,
  `ProjGoalName` text,
  `ProjectStartDate` date default NULL,
  `ProjectEndDate` date default NULL,
  `AllocatedHours` varchar(255) default NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  `hcmoclientid` int(11) NOT NULL,
  `HcmoCustomerId` int(11) NOT NULL,
  `HcmoDepartmentId` int(11) NOT NULL,
  `ProjectOwnerId` int(11) NOT NULL,
  `description` varchar(255) default NULL,
  `isBillable` tinyint(1) default NULL,
  PRIMARY KEY  (`HcmoTsEmpProjRelId`),
  KEY `fk_hcmo_ts_emp_proj_rel_hcmoemployeeid` (`HcmoEmployeeId`),
  KEY `fk_hcmo_ts_emp_proj_rel_hcmoprojectid` (`HcmoProjectId`),
  KEY `fk_hcmo_ts_emp_proj_rel_hcmoprojectactivityid` (`HcmoProjectActivityId`),
  KEY `fk_hcmo_ts_emp_proj_rel_updatedby` (`UpdatedBy`),
  KEY `fk_hcmo_ts_emp_proj_rel_createdby` (`CreatedBy`),
  KEY `fk_hcmo_ts_emp_proj_rel_hcmodepartmentid` (`HcmoDepartmentId`),
  KEY `fk_hcmo_ts_emp_proj_rel_hcmocustomerid` (`HcmoCustomerId`),
  KEY `fk_hcmo_ts_emp_proj_rel_projectownerid` (`ProjectOwnerId`),
  CONSTRAINT `fk_hcmo_ts_emp_proj_rel_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_ts_emp_proj_rel_hcmocustomerid` FOREIGN KEY (`HcmoCustomerId`) REFERENCES `hcmo_customer` (`HcmoCustomerId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_ts_emp_proj_rel_hcmodepartmentid` FOREIGN KEY (`HcmoDepartmentId`) REFERENCES `hcmo_department` (`HcmoDepartmentId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_ts_emp_proj_rel_hcmoemployeeid` FOREIGN KEY (`HcmoEmployeeId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_ts_emp_proj_rel_hcmoprojectactivityid` FOREIGN KEY (`HcmoProjectActivityId`) REFERENCES `hcmo_project_activity` (`HcmoProjectActivityId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_ts_emp_proj_rel_hcmoprojectid` FOREIGN KEY (`HcmoProjectId`) REFERENCES `hcmo_project` (`HcmoProjectId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_ts_emp_proj_rel_projectownerid` FOREIGN KEY (`ProjectOwnerId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_ts_emp_proj_rel_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_ts_emp_proj_rel`
--

LOCK TABLES `hcmo_ts_emp_proj_rel` WRITE;
/*!40000 ALTER TABLE `hcmo_ts_emp_proj_rel` DISABLE KEYS */;
/*!40000 ALTER TABLE `hcmo_ts_emp_proj_rel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_ts_timetrack`
--

DROP TABLE IF EXISTS `hcmo_ts_timetrack`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_ts_timetrack` (
  `HcmoEmptimesheetId` int(11) NOT NULL auto_increment,
  `empid` int(11) NOT NULL,
  `CategoryId` int(11) NOT NULL,
  `ProjectId` int(11) default NULL,
  `ActivityId` int(11) default NULL,
  `Start` datetime default NULL,
  `Stop` datetime default NULL,
  `Duration` double NOT NULL default '0',
  `CheckIn` tinyint(1) NOT NULL,
  `CheckOut` tinyint(1) NOT NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  `hcmoclientid` int(11) NOT NULL,
  PRIMARY KEY  (`HcmoEmptimesheetId`),
  KEY `fk_hcmo_ts_timetrack_updatedby` (`UpdatedBy`),
  KEY `fk_hcmo_ts_timetrack_createdby` (`CreatedBy`),
  CONSTRAINT `fk_hcmo_ts_timetrack_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_ts_timetrack_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_ts_timetrack`
--

LOCK TABLES `hcmo_ts_timetrack` WRITE;
/*!40000 ALTER TABLE `hcmo_ts_timetrack` DISABLE KEYS */;
/*!40000 ALTER TABLE `hcmo_ts_timetrack` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_user`
--

DROP TABLE IF EXISTS `hcmo_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_user` (
  `HcmoUserId` int(11) NOT NULL auto_increment,
  `HcmoEmployeeId` int(11) NOT NULL,
  `UserName` varchar(120) NOT NULL,
  `Password` varchar(16) NOT NULL,
  `CanCreate` tinyint(1) NOT NULL default '0',
  `ForgotPassword` tinyint(1) NOT NULL default '0',
  `LastSucessfulLogin` datetime default NULL,
  `LastFailureLogin` datetime default NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  `hcmoclientid` int(11) NOT NULL,
  PRIMARY KEY  (`HcmoUserId`),
  UNIQUE KEY `UserName` (`UserName`,`IsActive`),
  UNIQUE KEY `Password` (`Password`,`IsActive`),
  UNIQUE KEY `EmployeeId` (`HcmoEmployeeId`,`IsActive`),
  KEY `fk_hcmo_user_hcmoemployeeid` (`HcmoEmployeeId`),
  KEY `fk_hcmo_user_updatedby` (`UpdatedBy`),
  KEY `fk_hcmo_user_createdby` (`CreatedBy`),
  CONSTRAINT `fk_hcmo_user_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_user_hcmoemployeeid` FOREIGN KEY (`HcmoEmployeeId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_user_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_user`
--

LOCK TABLES `hcmo_user` WRITE;
/*!40000 ALTER TABLE `hcmo_user` DISABLE KEYS */;
INSERT INTO `hcmo_user` VALUES (1,1,'admin','admin',0,0,'2015-12-28 14:37:03','2015-12-28 14:37:04','2015-12-28',1,'2015-12-28 09:07:06',1,1,1);
/*!40000 ALTER TABLE `hcmo_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcmo_vendor`
--

DROP TABLE IF EXISTS `hcmo_vendor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcmo_vendor` (
  `HcmoVendorId` int(11) NOT NULL auto_increment,
  `hcmoclientid` int(11) NOT NULL,
  `HcmoCountryId` int(11) NOT NULL,
  `TaxId` varchar(30) NOT NULL,
  `VendorName` varchar(100) NOT NULL,
  `VendorShName` varchar(100) default NULL,
  `ContactPerson1` varchar(100) NOT NULL,
  `EmailAddress1` varchar(100) NOT NULL,
  `ContactPerson2` varchar(100) NOT NULL,
  `EmailAddress2` varchar(100) NOT NULL,
  `UserName` varchar(60) NOT NULL,
  `Password` varchar(16) NOT NULL,
  `CompanyName` varchar(60) NOT NULL,
  `Address1` varchar(100) default NULL,
  `Address2` varchar(100) default NULL,
  `State` varchar(60) default NULL,
  `City` varchar(100) default NULL,
  `ZipCode` varchar(20) default NULL,
  `Phone` varchar(20) default NULL,
  `Extension` varchar(10) default NULL,
  `Fax` varchar(20) default NULL,
  `Website` varchar(100) default NULL,
  `Custom1` varchar(255) default NULL,
  `Custom2` varchar(255) default NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  PRIMARY KEY  (`HcmoVendorId`),
  KEY `fk_hcmo_vendor_hcmocountryid` (`HcmoCountryId`),
  KEY `fk_hcmo_vendor_updatedby` (`UpdatedBy`),
  KEY `fk_hcmo_vendor_createdby` (`CreatedBy`),
  CONSTRAINT `fk_hcmo_vendor_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_vendor_hcmocountryid` FOREIGN KEY (`HcmoCountryId`) REFERENCES `hcmo_country` (`HcmoCountryId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hcmo_vendor_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcmo_vendor`
--

LOCK TABLES `hcmo_vendor` WRITE;
/*!40000 ALTER TABLE `hcmo_vendor` DISABLE KEYS */;
/*!40000 ALTER TABLE `hcmo_vendor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_applicants_addl_info`
--

DROP TABLE IF EXISTS `qr_applicants_addl_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_applicants_addl_info` (
  `QrApplicantsAddlId` int(11) NOT NULL auto_increment,
  `QrApplicantsBaseId` int(11) NOT NULL,
  `QrEthnicRaceId` int(11) NOT NULL,
  `QrNationalityId` int(11) NOT NULL,
  `WorkAuth` varchar(60) default NULL,
  `Reloc` varchar(5) default NULL,
  `Reloc1` varchar(60) default NULL,
  `Reloc2` varchar(60) default NULL,
  `Reloc3` varchar(60) default NULL,
  `CreatedDt` datetime default NULL,
  `CreatedBy` int(11) default NULL,
  `UpdatedDt` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) default NULL,
  `Isactive` tinyint(1) default '1',
  `Smoker` bit(1) default NULL,
  `BirthDay` date default NULL,
  `MaritalStatus` varchar(20) default NULL,
  `LicenseNum` varchar(100) default NULL,
  `LicenseDate` date default NULL,
  `MilitaryService` varchar(100) default NULL,
  `ApplicantsPicturePath` text,
  `SSN` varchar(15) default NULL,
  PRIMARY KEY  (`QrApplicantsAddlId`),
  KEY `CreatedBy` (`CreatedBy`),
  KEY `UpdatedBy` (`UpdatedBy`),
  KEY `QrApplicantsBaseId` (`QrApplicantsBaseId`),
  KEY `QrEthnicRaceId` (`QrEthnicRaceId`),
  KEY `QrNationalityId` (`QrNationalityId`),
  KEY `FKA0992AD25C0693FE` (`QrNationalityId`),
  KEY `FKA0992AD247BC20AD` (`UpdatedBy`),
  KEY `FKA0992AD23F998942` (`QrEthnicRaceId`),
  KEY `FKA0992AD29816EB55` (`QrApplicantsBaseId`),
  CONSTRAINT `FKA0992AD23F998942` FOREIGN KEY (`QrEthnicRaceId`) REFERENCES `qr_ethnic_race` (`QrEthnicRaceId`),
  CONSTRAINT `FKA0992AD247BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `FKA0992AD25C0693FE` FOREIGN KEY (`QrNationalityId`) REFERENCES `qr_nationality` (`QrNationalityId`),
  CONSTRAINT `FKA0992AD29816EB55` FOREIGN KEY (`QrApplicantsBaseId`) REFERENCES `qr_applicants_base_info` (`QrApplicantsBaseId`),
  CONSTRAINT `FKA0992AD2DF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_applicants_addl_info_ibfk_1` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_applicants_addl_info_ibfk_2` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_applicants_addl_info_ibfk_3` FOREIGN KEY (`QrApplicantsBaseId`) REFERENCES `qr_applicants_base_info` (`QrApplicantsBaseId`),
  CONSTRAINT `qr_applicants_addl_info_ibfk_4` FOREIGN KEY (`QrEthnicRaceId`) REFERENCES `qr_ethnic_race` (`QrEthnicRaceId`),
  CONSTRAINT `qr_applicants_addl_info_ibfk_6` FOREIGN KEY (`QrNationalityId`) REFERENCES `qr_nationality` (`QrNationalityId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_applicants_addl_info`
--

LOCK TABLES `qr_applicants_addl_info` WRITE;
/*!40000 ALTER TABLE `qr_applicants_addl_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `qr_applicants_addl_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_applicants_attach`
--

DROP TABLE IF EXISTS `qr_applicants_attach`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_applicants_attach` (
  `QrapplicantsAttachId` int(11) NOT NULL auto_increment,
  `QrApplicantsBaseId` int(11) NOT NULL,
  `ApplicantsAttachName` varchar(255) NOT NULL,
  `ApplicantsAttachType` varchar(255) default NULL,
  `ApplicantsAttachSize` int(11) NOT NULL,
  `CreatedDt` datetime default NULL,
  `CreatedBy` int(11) default NULL,
  `UpdatedDt` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) default NULL,
  `Isactive` tinyint(1) default '1',
  PRIMARY KEY  (`QrapplicantsAttachId`),
  KEY `CreatedBy` (`CreatedBy`),
  KEY `UpdatedBy` (`UpdatedBy`),
  KEY `QrApplicantsBaseId` (`QrApplicantsBaseId`),
  KEY `FKF68F21F547BC20AD` (`UpdatedBy`),
  KEY `FKF68F21F5DF94E79A` (`CreatedBy`),
  KEY `FKF68F21F59816EB55` (`QrApplicantsBaseId`),
  CONSTRAINT `FKF68F21F547BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `FKF68F21F59816EB55` FOREIGN KEY (`QrApplicantsBaseId`) REFERENCES `qr_applicants_base_info` (`QrApplicantsBaseId`),
  CONSTRAINT `FKF68F21F5DF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_applicants_attach_ibfk_1` FOREIGN KEY (`QrApplicantsBaseId`) REFERENCES `qr_applicants_base_info` (`QrApplicantsBaseId`),
  CONSTRAINT `qr_applicants_attach_ibfk_2` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_applicants_attach_ibfk_3` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_applicants_attach`
--

LOCK TABLES `qr_applicants_attach` WRITE;
/*!40000 ALTER TABLE `qr_applicants_attach` DISABLE KEYS */;
/*!40000 ALTER TABLE `qr_applicants_attach` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_applicants_base_info`
--

DROP TABLE IF EXISTS `qr_applicants_base_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_applicants_base_info` (
  `QrApplicantsBaseId` int(11) NOT NULL auto_increment,
  `QrVendorBaseId` int(11) default NULL,
  `Salutation` varchar(20) default NULL,
  `ApplicantsFName` varchar(150) NOT NULL,
  `ApplicantsMName` varchar(150) default NULL,
  `ApplicantsLName` varchar(150) default NULL,
  `ApplicantsDob` date default NULL,
  `NickName` varchar(150) default NULL,
  `TotYrExp` varchar(150) default NULL,
  `Address1` varchar(150) default NULL,
  `Address2` varchar(150) default NULL,
  `QrCountryId` int(11) NOT NULL,
  `City` varchar(60) default NULL,
  `State` varchar(60) default NULL,
  `Postalcode` varchar(20) default NULL,
  `PhoneWorking` varchar(100) default NULL,
  `PhoneWorkingExt` varchar(10) default NULL,
  `PhoneWorkAdd` varchar(20) default NULL,
  `PhoneHome` varchar(20) default NULL,
  `PhoneMobile` varchar(20) default NULL,
  `ApplicantsPriEmailId` varchar(200) NOT NULL,
  `ApplicantsSecondaryEmail` varchar(200) default NULL,
  `ReferredByName` varchar(60) default NULL,
  `Rate` varchar(20) default NULL,
  `RateOption` varchar(20) default NULL,
  `VendorType` varchar(20) default NULL,
  `RatePerHr` double default NULL,
  `Isexternal` tinyint(1) default '0',
  `PrivilegeType` varchar(100) default 'public',
  `TextResume` text,
  `TitleKeyword` varchar(150) default NULL,
  `BulkMailPreference` varchar(10) default NULL,
  `CreatedDt` datetime default NULL,
  `CreatedBy` int(11) default NULL,
  `UpdatedDt` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) default NULL,
  `Isactive` tinyint(1) default '1',
  `QrPrivilegeId` int(11) default NULL,
  PRIMARY KEY  (`QrApplicantsBaseId`),
  KEY `UpdatedBy` (`UpdatedBy`),
  KEY `CreatedBy` (`CreatedBy`),
  KEY `QrVendorBaseId` (`QrVendorBaseId`),
  KEY `FKAC59FAC47BC20AD` (`UpdatedBy`),
  KEY `FKAC59FACDF94E79A` (`CreatedBy`),
  KEY `FKAC59FAC3FFA5283` (`QrVendorBaseId`),
  KEY `FKAC59FACBC4D3B2` (`QrCountryId`),
  CONSTRAINT `FKAC59FAC3FFA5283` FOREIGN KEY (`QrVendorBaseId`) REFERENCES `qr_vendor_base_info` (`QrVendorBaseId`),
  CONSTRAINT `FKAC59FAC47BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `FKAC59FACBC4D3B2` FOREIGN KEY (`QrCountryId`) REFERENCES `qr_country` (`QrCountryId`),
  CONSTRAINT `FKAC59FACDF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_applicants_base_info_ibfk_1` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_applicants_base_info_ibfk_2` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_applicants_base_info_ibfk_3` FOREIGN KEY (`QrVendorBaseId`) REFERENCES `qr_vendor_base_info` (`QrVendorBaseId`),
  CONSTRAINT `qr_applicants_base_info_ibfk_4` FOREIGN KEY (`QrCountryId`) REFERENCES `qr_country` (`QrCountryId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_applicants_base_info`
--

LOCK TABLES `qr_applicants_base_info` WRITE;
/*!40000 ALTER TABLE `qr_applicants_base_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `qr_applicants_base_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_applicants_broadcast`
--

DROP TABLE IF EXISTS `qr_applicants_broadcast`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_applicants_broadcast` (
  `QrApplicantBroadcastId` int(11) NOT NULL auto_increment,
  `QrHrUserId` int(11) NOT NULL,
  `QrRequirementId` int(11) NOT NULL,
  `QrApplicantsBaseId` int(11) NOT NULL,
  `CreatedDt` datetime NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `UpdatedDt` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  PRIMARY KEY  (`QrApplicantBroadcastId`),
  KEY `FK1690D71126FAA32C` (`QrHrUserId`),
  KEY `FK1690D71147BC20AD` (`UpdatedBy`),
  KEY `FK1690D7116D2BC58C` (`QrRequirementId`),
  KEY `FK1690D711DF94E79A` (`CreatedBy`),
  KEY `FK1690D7119816EB55` (`QrApplicantsBaseId`),
  CONSTRAINT `FK1690D71126FAA32C` FOREIGN KEY (`QrHrUserId`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `FK1690D71147BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `FK1690D7116D2BC58C` FOREIGN KEY (`QrRequirementId`) REFERENCES `qr_requirement` (`QrRequirementId`),
  CONSTRAINT `FK1690D7119816EB55` FOREIGN KEY (`QrApplicantsBaseId`) REFERENCES `qr_applicants_base_info` (`QrApplicantsBaseId`),
  CONSTRAINT `FK1690D711DF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_applicants_broadcast_ibfk_1` FOREIGN KEY (`QrHrUserId`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_applicants_broadcast_ibfk_2` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_applicants_broadcast_ibfk_3` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_applicants_broadcast_ibfk_4` FOREIGN KEY (`QrRequirementId`) REFERENCES `qr_requirement` (`QrRequirementId`),
  CONSTRAINT `qr_applicants_broadcast_ibfk_5` FOREIGN KEY (`QrApplicantsBaseId`) REFERENCES `qr_applicants_base_info` (`QrApplicantsBaseId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_applicants_broadcast`
--

LOCK TABLES `qr_applicants_broadcast` WRITE;
/*!40000 ALTER TABLE `qr_applicants_broadcast` DISABLE KEYS */;
/*!40000 ALTER TABLE `qr_applicants_broadcast` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_applicants_formatted_attach`
--

DROP TABLE IF EXISTS `qr_applicants_formatted_attach`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_applicants_formatted_attach` (
  `QrApplicantsFormatAttachId` int(11) NOT NULL auto_increment,
  `QrApplicantsBaseId` int(11) NOT NULL,
  `ApplicantsFormatAttachName` varchar(255) NOT NULL,
  `ApplicantsFormatAttachType` varchar(255) default NULL,
  `ApplicantsFormatAttachSize` int(11) NOT NULL,
  `CreatedDt` datetime default NULL,
  `CreatedBy` int(11) default NULL,
  `UpdatedDt` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) default NULL,
  `Isactive` tinyint(1) default '1',
  PRIMARY KEY  (`QrApplicantsFormatAttachId`),
  KEY `CreatedBy` (`CreatedBy`),
  KEY `UpdatedBy` (`UpdatedBy`),
  KEY `QrApplicantsBaseId` (`QrApplicantsBaseId`),
  KEY `FK57D286D847BC20AD` (`UpdatedBy`),
  KEY `FK57D286D8DF94E79A` (`CreatedBy`),
  KEY `FK57D286D89816EB55` (`QrApplicantsBaseId`),
  CONSTRAINT `FK57D286D847BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `FK57D286D89816EB55` FOREIGN KEY (`QrApplicantsBaseId`) REFERENCES `qr_applicants_base_info` (`QrApplicantsBaseId`),
  CONSTRAINT `FK57D286D8DF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_applicants_formatted_attach_ibfk_1` FOREIGN KEY (`QrApplicantsBaseId`) REFERENCES `qr_applicants_base_info` (`QrApplicantsBaseId`),
  CONSTRAINT `qr_applicants_formatted_attach_ibfk_2` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_applicants_formatted_attach_ibfk_3` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_applicants_formatted_attach`
--

LOCK TABLES `qr_applicants_formatted_attach` WRITE;
/*!40000 ALTER TABLE `qr_applicants_formatted_attach` DISABLE KEYS */;
/*!40000 ALTER TABLE `qr_applicants_formatted_attach` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_applicants_notes`
--

DROP TABLE IF EXISTS `qr_applicants_notes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_applicants_notes` (
  `QrApplicantsNotesId` int(11) NOT NULL auto_increment,
  `QrHrUserId` int(11) NOT NULL,
  `QrApplicantsBaseId` int(11) NOT NULL,
  `QrRequirementId` int(11) NOT NULL,
  `Notes` text,
  `NotesType` varchar(50) NOT NULL,
  `CallogTo` text,
  `CallogTime` datetime NOT NULL,
  `IsApplicantVisible` tinyint(1) default '0',
  `NotesOwner` tinyint(1) default '0',
  `CreatedDt` datetime default NULL,
  `CreatedBy` int(11) default NULL,
  `UpdatedDt` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) default NULL,
  `Isactive` tinyint(1) default '1',
  `ApplicantsNotes` text,
  PRIMARY KEY  (`QrApplicantsNotesId`),
  KEY `QrHrUserId` (`QrHrUserId`),
  KEY `CreatedBy` (`CreatedBy`),
  KEY `UpdatedBy` (`UpdatedBy`),
  KEY `QrApplicantsBaseId` (`QrApplicantsBaseId`),
  KEY `QrRequirementId` (`QrRequirementId`),
  KEY `FKF824E51126FAA32C` (`QrHrUserId`),
  KEY `FKF824E51147BC20AD` (`UpdatedBy`),
  KEY `FKF824E5116D2BC58C` (`QrRequirementId`),
  KEY `FKF824E511DF94E79A` (`CreatedBy`),
  KEY `FKF824E5119816EB55` (`QrApplicantsBaseId`),
  CONSTRAINT `FKF824E51126FAA32C` FOREIGN KEY (`QrHrUserId`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `FKF824E51147BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `FKF824E5116D2BC58C` FOREIGN KEY (`QrRequirementId`) REFERENCES `qr_requirement` (`QrRequirementId`),
  CONSTRAINT `FKF824E5119816EB55` FOREIGN KEY (`QrApplicantsBaseId`) REFERENCES `qr_applicants_base_info` (`QrApplicantsBaseId`),
  CONSTRAINT `FKF824E511DF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_applicants_notes_ibfk_1` FOREIGN KEY (`QrHrUserId`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_applicants_notes_ibfk_2` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_applicants_notes_ibfk_3` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_applicants_notes_ibfk_4` FOREIGN KEY (`QrApplicantsBaseId`) REFERENCES `qr_applicants_base_info` (`QrApplicantsBaseId`),
  CONSTRAINT `qr_applicants_notes_ibfk_5` FOREIGN KEY (`QrRequirementId`) REFERENCES `qr_requirement` (`QrRequirementId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_applicants_notes`
--

LOCK TABLES `qr_applicants_notes` WRITE;
/*!40000 ALTER TABLE `qr_applicants_notes` DISABLE KEYS */;
/*!40000 ALTER TABLE `qr_applicants_notes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_applicants_notes_attach`
--

DROP TABLE IF EXISTS `qr_applicants_notes_attach`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_applicants_notes_attach` (
  `QrApplicantsNotesAttachId` int(11) NOT NULL auto_increment,
  `QrApplicantsNotesId` int(11) NOT NULL,
  `ApplicantsNotesAttachName` varchar(255) NOT NULL,
  `ApplicantsNotesAttachType` varchar(255) default NULL,
  `ApplicantsNotesAttachSize` int(11) NOT NULL,
  `CreatedDt` datetime NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `UpdatedDt` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `Isactive` tinyint(1) NOT NULL default '1',
  `IsApplicantAttach` smallint(6) default NULL,
  PRIMARY KEY  (`QrApplicantsNotesAttachId`),
  KEY `CreatedBy` (`CreatedBy`),
  KEY `UpdatedBy` (`UpdatedBy`),
  KEY `QrApplicantsNotesId` (`QrApplicantsNotesId`),
  KEY `FKF802613347BC20AD` (`UpdatedBy`),
  KEY `FKF8026133DF94E79A` (`CreatedBy`),
  KEY `FKF8026133C02B94C6` (`QrApplicantsNotesId`),
  CONSTRAINT `FKF802613347BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `FKF8026133C02B94C6` FOREIGN KEY (`QrApplicantsNotesId`) REFERENCES `qr_applicants_notes` (`QrApplicantsNotesId`),
  CONSTRAINT `FKF8026133DF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_applicants_notes_attach_ibfk_1` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_applicants_notes_attach_ibfk_2` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_applicants_notes_attach_ibfk_3` FOREIGN KEY (`QrApplicantsNotesId`) REFERENCES `qr_applicants_notes` (`QrApplicantsNotesId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_applicants_notes_attach`
--

LOCK TABLES `qr_applicants_notes_attach` WRITE;
/*!40000 ALTER TABLE `qr_applicants_notes_attach` DISABLE KEYS */;
/*!40000 ALTER TABLE `qr_applicants_notes_attach` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_applicants_privilege_current`
--

DROP TABLE IF EXISTS `qr_applicants_privilege_current`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_applicants_privilege_current` (
  `QrApplicantsPrivileveCurrentId` int(11) NOT NULL auto_increment,
  `QrApplicantsBaseId` int(11) NOT NULL,
  `PrivHrUserId` int(11) default NULL,
  `PrivSharedHrUserId` varchar(20) default NULL,
  `PrivilegeType` varchar(100) default NULL,
  `CreatedDt` datetime NOT NULL,
  `CreatedBy` int(11) default NULL,
  `UpdatedDt` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) default NULL,
  `IsActive` tinyint(1) NOT NULL,
  PRIMARY KEY  (`QrApplicantsPrivileveCurrentId`),
  KEY `CreatedBy` (`CreatedBy`),
  KEY `UpdatedBy` (`UpdatedBy`),
  KEY `QrApplicantsBaseId` (`QrApplicantsBaseId`),
  KEY `FK9B54B83B47BC20AD` (`UpdatedBy`),
  KEY `FK9B54B83B8150C9BA` (`PrivHrUserId`),
  KEY `FK9B54B83BDF94E79A` (`CreatedBy`),
  KEY `FK9B54B83B9816EB55` (`QrApplicantsBaseId`),
  CONSTRAINT `FK9B54B83B47BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `FK9B54B83B8150C9BA` FOREIGN KEY (`PrivHrUserId`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `FK9B54B83B9816EB55` FOREIGN KEY (`QrApplicantsBaseId`) REFERENCES `qr_applicants_base_info` (`QrApplicantsBaseId`),
  CONSTRAINT `FK9B54B83BDF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_applicants_privilege_current_ibfk_1` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_applicants_privilege_current_ibfk_2` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_applicants_privilege_current_ibfk_3` FOREIGN KEY (`QrApplicantsBaseId`) REFERENCES `qr_applicants_base_info` (`QrApplicantsBaseId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_applicants_privilege_current`
--

LOCK TABLES `qr_applicants_privilege_current` WRITE;
/*!40000 ALTER TABLE `qr_applicants_privilege_current` DISABLE KEYS */;
/*!40000 ALTER TABLE `qr_applicants_privilege_current` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_applicants_privilege_history`
--

DROP TABLE IF EXISTS `qr_applicants_privilege_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_applicants_privilege_history` (
  `QrPrivilegeHistoryId` int(11) NOT NULL auto_increment,
  `QrApplicantsBaseId` int(11) NOT NULL,
  `QrRequirementId` int(11) default NULL,
  `QrHrUserId` int(11) default NULL,
  `PrivilegeType` varchar(100) default NULL,
  `CreatedDt` datetime default NULL,
  `CreatedBy` int(11) default NULL,
  `UpdatedDt` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) default NULL,
  `Isactive` tinyint(1) default '1',
  PRIMARY KEY  (`QrPrivilegeHistoryId`),
  KEY `qr_applicants_previlage_history_ibfk_1` (`UpdatedBy`),
  KEY `qr_applicants_previlage_history_ibfk_2` (`CreatedBy`),
  KEY `qr_applicants_previlage_history_ibfk_3` (`QrHrUserId`),
  KEY `qr_applicants_previlage_history_ibfk_4` (`QrApplicantsBaseId`),
  KEY `qr_applicants_previlage_history_ibfk_5` (`QrRequirementId`),
  KEY `FK8F68DF5626FAA32C` (`QrHrUserId`),
  KEY `FK8F68DF5647BC20AD` (`UpdatedBy`),
  KEY `FK8F68DF566D2BC58C` (`QrRequirementId`),
  KEY `FK8F68DF56DF94E79A` (`CreatedBy`),
  KEY `FK8F68DF569816EB55` (`QrApplicantsBaseId`),
  CONSTRAINT `FK8F68DF5626FAA32C` FOREIGN KEY (`QrHrUserId`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `FK8F68DF5647BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `FK8F68DF566D2BC58C` FOREIGN KEY (`QrRequirementId`) REFERENCES `qr_requirement` (`QrRequirementId`),
  CONSTRAINT `FK8F68DF569816EB55` FOREIGN KEY (`QrApplicantsBaseId`) REFERENCES `qr_applicants_base_info` (`QrApplicantsBaseId`),
  CONSTRAINT `FK8F68DF56DF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_applicants_previlage_history_ibfk_1` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_applicants_previlage_history_ibfk_2` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_applicants_previlage_history_ibfk_3` FOREIGN KEY (`QrHrUserId`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_applicants_previlage_history_ibfk_4` FOREIGN KEY (`QrApplicantsBaseId`) REFERENCES `qr_applicants_base_info` (`QrApplicantsBaseId`),
  CONSTRAINT `qr_applicants_previlage_history_ibfk_5` FOREIGN KEY (`QrRequirementId`) REFERENCES `qr_requirement` (`QrRequirementId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_applicants_privilege_history`
--

LOCK TABLES `qr_applicants_privilege_history` WRITE;
/*!40000 ALTER TABLE `qr_applicants_privilege_history` DISABLE KEYS */;
/*!40000 ALTER TABLE `qr_applicants_privilege_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_applicants_req_mail`
--

DROP TABLE IF EXISTS `qr_applicants_req_mail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_applicants_req_mail` (
  `QrApplicantsReqMailId` int(11) NOT NULL auto_increment,
  `QrApplicantsBaseId` int(11) NOT NULL,
  `QrRequirementId` int(11) NOT NULL,
  `ApplicantsReqMailSubject` varchar(100) default NULL,
  `ApplicantsReqMailMessage` text,
  `MailSendTime` datetime default NULL,
  `CreatedDt` datetime NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `UpdatedDt` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL,
  PRIMARY KEY  (`QrApplicantsReqMailId`),
  KEY `qr_applicants_req_mail_ibfk_1` (`QrApplicantsBaseId`),
  KEY `qr_applicants_req_mail_ibfk_2` (`QrRequirementId`),
  KEY `qr_applicants_req_mail_ibfk_3` (`CreatedBy`),
  KEY `qr_applicants_req_mail_ibfk_4` (`UpdatedBy`),
  KEY `FK568EC06847BC20AD` (`UpdatedBy`),
  KEY `FK568EC0686D2BC58C` (`QrRequirementId`),
  KEY `FK568EC068DF94E79A` (`CreatedBy`),
  KEY `FK568EC0689816EB55` (`QrApplicantsBaseId`),
  CONSTRAINT `FK568EC06847BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `FK568EC0686D2BC58C` FOREIGN KEY (`QrRequirementId`) REFERENCES `qr_requirement` (`QrRequirementId`),
  CONSTRAINT `FK568EC0689816EB55` FOREIGN KEY (`QrApplicantsBaseId`) REFERENCES `qr_applicants_base_info` (`QrApplicantsBaseId`),
  CONSTRAINT `FK568EC068DF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_applicants_req_mail_ibfk_1` FOREIGN KEY (`QrApplicantsBaseId`) REFERENCES `qr_applicants_base_info` (`QrApplicantsBaseId`),
  CONSTRAINT `qr_applicants_req_mail_ibfk_2` FOREIGN KEY (`QrRequirementId`) REFERENCES `qr_requirement` (`QrRequirementId`),
  CONSTRAINT `qr_applicants_req_mail_ibfk_3` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_applicants_req_mail_ibfk_4` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_applicants_req_mail`
--

LOCK TABLES `qr_applicants_req_mail` WRITE;
/*!40000 ALTER TABLE `qr_applicants_req_mail` DISABLE KEYS */;
/*!40000 ALTER TABLE `qr_applicants_req_mail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_applicants_skill_matrix`
--

DROP TABLE IF EXISTS `qr_applicants_skill_matrix`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_applicants_skill_matrix` (
  `QrApplicantsSkillMatrixId` int(11) NOT NULL auto_increment,
  `QrApplicantsBaseId` int(11) NOT NULL,
  `SkillName` varchar(60) NOT NULL,
  `LastUsed` varchar(20) NOT NULL,
  `Proficiency` varchar(20) NOT NULL,
  `CreatedDt` datetime default NULL,
  `CreatedBy` int(11) default NULL,
  `UpdatedDt` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) default NULL,
  `Isactive` tinyint(1) default '1',
  PRIMARY KEY  (`QrApplicantsSkillMatrixId`),
  KEY `QrApplicantsBaseId` (`QrApplicantsBaseId`),
  KEY `CreatedBy` (`CreatedBy`),
  KEY `UpdatedBy` (`UpdatedBy`),
  KEY `FK95E56D1F47BC20AD` (`UpdatedBy`),
  KEY `FK95E56D1FDF94E79A` (`CreatedBy`),
  KEY `FK95E56D1F9816EB55` (`QrApplicantsBaseId`),
  CONSTRAINT `FK95E56D1F47BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `FK95E56D1F9816EB55` FOREIGN KEY (`QrApplicantsBaseId`) REFERENCES `qr_applicants_base_info` (`QrApplicantsBaseId`),
  CONSTRAINT `FK95E56D1FDF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_applicants_skill_matrixFK1` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_applicants_skill_matrixFK2` FOREIGN KEY (`QrApplicantsBaseId`) REFERENCES `qr_applicants_base_info` (`QrApplicantsBaseId`),
  CONSTRAINT `qr_applicants_skill_matrixFK3` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_applicants_skill_matrix`
--

LOCK TABLES `qr_applicants_skill_matrix` WRITE;
/*!40000 ALTER TABLE `qr_applicants_skill_matrix` DISABLE KEYS */;
/*!40000 ALTER TABLE `qr_applicants_skill_matrix` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_applicants_submission`
--

DROP TABLE IF EXISTS `qr_applicants_submission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_applicants_submission` (
  `QrApplicantsSubmissionId` int(11) NOT NULL auto_increment,
  `QrApplicantsBaseId` int(11) NOT NULL,
  `QrHrUserId` int(11) NOT NULL,
  `QrApplicantsNotesId` int(11) NOT NULL,
  `QrRequirementId` int(11) NOT NULL,
  `QrVendorBaseId` int(11) NOT NULL,
  `QrApplicationReceivedId` int(11) default NULL,
  `SubmittedDate` datetime default NULL,
  `ApplicantsApproved` tinyint(1) default '0',
  `LeadApproved` tinyint(1) default '0',
  `SubmitToLead` tinyint(1) default '0',
  `SubmitToClient` tinyint(1) default '0',
  `SubmitToLeadIds` varchar(50) default NULL,
  `ApprovedLeadId` varchar(10) default NULL,
  `DirectSubmitToClient` tinyint(1) NOT NULL default '0',
  `CreatedDt` datetime NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `UpdatedDt` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  PRIMARY KEY  (`QrApplicantsSubmissionId`),
  KEY `qr_applicants_submission_ibfk_1` (`QrApplicantsBaseId`),
  KEY `qr_applicants_submission_ibfk_2` (`QrHrUserId`),
  KEY `qr_applicants_submission_ibfk_3` (`QrRequirementId`),
  KEY `qr_applicants_submission_ibfk_4` (`QrVendorBaseId`),
  KEY `qr_applicants_submission_ibfk_5` (`CreatedBy`),
  KEY `qr_applicants_submission_ibfk_6` (`UpdatedBy`),
  KEY `qr_applicants_submission_ibfk_7` (`QrApplicantsNotesId`),
  KEY `FKEF0516FC26FAA32C` (`QrHrUserId`),
  KEY `FKEF0516FC47BC20AD` (`UpdatedBy`),
  KEY `FKEF0516FC6D2BC58C` (`QrRequirementId`),
  KEY `FKEF0516FC3FFA5283` (`QrVendorBaseId`),
  KEY `FKEF0516FCDF94E79A` (`CreatedBy`),
  KEY `FKEF0516FC9816EB55` (`QrApplicantsBaseId`),
  KEY `FKEF0516FCC02B94C6` (`QrApplicantsNotesId`),
  KEY `FKEF0516FCE5144E88` (`QrApplicationReceivedId`),
  CONSTRAINT `FKEF0516FC26FAA32C` FOREIGN KEY (`QrHrUserId`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `FKEF0516FC3FFA5283` FOREIGN KEY (`QrVendorBaseId`) REFERENCES `qr_vendor_base_info` (`QrVendorBaseId`),
  CONSTRAINT `FKEF0516FC47BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `FKEF0516FC6D2BC58C` FOREIGN KEY (`QrRequirementId`) REFERENCES `qr_requirement` (`QrRequirementId`),
  CONSTRAINT `FKEF0516FC9816EB55` FOREIGN KEY (`QrApplicantsBaseId`) REFERENCES `qr_applicants_base_info` (`QrApplicantsBaseId`),
  CONSTRAINT `FKEF0516FCC02B94C6` FOREIGN KEY (`QrApplicantsNotesId`) REFERENCES `qr_applicants_notes` (`QrApplicantsNotesId`),
  CONSTRAINT `FKEF0516FCDF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `FKEF0516FCE5144E88` FOREIGN KEY (`QrApplicationReceivedId`) REFERENCES `qr_application_received` (`QrApplicationReceivedId`),
  CONSTRAINT `qr_applicants_submission_ibfk_1` FOREIGN KEY (`QrApplicantsBaseId`) REFERENCES `qr_applicants_base_info` (`QrApplicantsBaseId`),
  CONSTRAINT `qr_applicants_submission_ibfk_2` FOREIGN KEY (`QrHrUserId`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_applicants_submission_ibfk_3` FOREIGN KEY (`QrRequirementId`) REFERENCES `qr_requirement` (`QrRequirementId`),
  CONSTRAINT `qr_applicants_submission_ibfk_4` FOREIGN KEY (`QrVendorBaseId`) REFERENCES `qr_vendor_base_info` (`QrVendorBaseId`),
  CONSTRAINT `qr_applicants_submission_ibfk_5` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_applicants_submission_ibfk_6` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_applicants_submission_ibfk_7` FOREIGN KEY (`QrApplicantsNotesId`) REFERENCES `qr_applicants_notes` (`QrApplicantsNotesId`),
  CONSTRAINT `qr_applicants_submission_ibfk_8` FOREIGN KEY (`QrApplicationReceivedId`) REFERENCES `qr_application_received` (`QrApplicationReceivedId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_applicants_submission`
--

LOCK TABLES `qr_applicants_submission` WRITE;
/*!40000 ALTER TABLE `qr_applicants_submission` DISABLE KEYS */;
/*!40000 ALTER TABLE `qr_applicants_submission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_applicants_trai_cert`
--

DROP TABLE IF EXISTS `qr_applicants_trai_cert`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_applicants_trai_cert` (
  `QrApplicantsTraiCertId` int(11) NOT NULL auto_increment,
  `QrApplicantsBaseId` int(11) NOT NULL,
  `TraiCertName` varchar(60) NOT NULL,
  `TraiCertType` varchar(20) NOT NULL,
  `TraiCertDescription` text,
  `TraiCertLevel` varchar(20) NOT NULL,
  `TraiCertQualifiedDate` date default NULL,
  `TraiCertValidUpto` date default NULL,
  `CreatedDt` datetime NOT NULL,
  `CreatedBy` int(11) default NULL,
  `UpdatedDt` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) default NULL,
  `Isactive` tinyint(1) default '1',
  `JoinedDate` date default NULL,
  `TerminateDate` date default NULL,
  PRIMARY KEY  (`QrApplicantsTraiCertId`),
  KEY `QrApplicantsBaseId` (`QrApplicantsBaseId`),
  KEY `CreatedBy` (`CreatedBy`),
  KEY `UpdatedBy` (`UpdatedBy`),
  KEY `FKAC29C5ED47BC20AD` (`UpdatedBy`),
  KEY `FKAC29C5EDDF94E79A` (`CreatedBy`),
  KEY `FKAC29C5ED9816EB55` (`QrApplicantsBaseId`),
  CONSTRAINT `FKAC29C5ED47BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `FKAC29C5ED9816EB55` FOREIGN KEY (`QrApplicantsBaseId`) REFERENCES `qr_applicants_base_info` (`QrApplicantsBaseId`),
  CONSTRAINT `FKAC29C5EDDF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_applicants_trai_cert_ibfk_1` FOREIGN KEY (`QrApplicantsBaseId`) REFERENCES `qr_applicants_base_info` (`QrApplicantsBaseId`),
  CONSTRAINT `qr_applicants_trai_cert_ibfk_2` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_applicants_trai_cert_ibfk_3` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_applicants_trai_cert`
--

LOCK TABLES `qr_applicants_trai_cert` WRITE;
/*!40000 ALTER TABLE `qr_applicants_trai_cert` DISABLE KEYS */;
/*!40000 ALTER TABLE `qr_applicants_trai_cert` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_application_received`
--

DROP TABLE IF EXISTS `qr_application_received`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_application_received` (
  `QrApplicationReceivedId` int(11) NOT NULL auto_increment,
  `QrApplicantsBaseId` int(11) NOT NULL,
  `QrRequirementId` int(11) NOT NULL,
  `ApplicationAppliedDate` date default NULL,
  `ReqApply` tinyint(1) default '0',
  `CreatedDt` datetime NOT NULL,
  `CreatedBy` int(11) default NULL,
  `UpdatedDt` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) default NULL,
  `IsActive` tinyint(1) default NULL,
  PRIMARY KEY  (`QrApplicationReceivedId`),
  KEY `qr_application_received_ibfk_1` (`QrApplicantsBaseId`),
  KEY `qr_application_received_ibfk_2` (`QrRequirementId`),
  KEY `FK8B6AEB4E47BC20AD` (`UpdatedBy`),
  KEY `FK8B6AEB4E6D2BC58C` (`QrRequirementId`),
  KEY `FK8B6AEB4EDF94E79A` (`CreatedBy`),
  KEY `FK8B6AEB4E9816EB55` (`QrApplicantsBaseId`),
  CONSTRAINT `FK8B6AEB4E47BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `FK8B6AEB4E6D2BC58C` FOREIGN KEY (`QrRequirementId`) REFERENCES `qr_requirement` (`QrRequirementId`),
  CONSTRAINT `FK8B6AEB4E9816EB55` FOREIGN KEY (`QrApplicantsBaseId`) REFERENCES `qr_applicants_base_info` (`QrApplicantsBaseId`),
  CONSTRAINT `FK8B6AEB4EDF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_application_received_ibfk_1` FOREIGN KEY (`QrApplicantsBaseId`) REFERENCES `qr_applicants_base_info` (`QrApplicantsBaseId`),
  CONSTRAINT `qr_application_received_ibfk_2` FOREIGN KEY (`QrRequirementId`) REFERENCES `qr_requirement` (`QrRequirementId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_application_received`
--

LOCK TABLES `qr_application_received` WRITE;
/*!40000 ALTER TABLE `qr_application_received` DISABLE KEYS */;
/*!40000 ALTER TABLE `qr_application_received` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_assigned_requirement`
--

DROP TABLE IF EXISTS `qr_assigned_requirement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_assigned_requirement` (
  `QrAssignedRequirementId` int(11) NOT NULL auto_increment,
  `QrRequirementId` int(11) NOT NULL,
  `AssignToId` int(11) NOT NULL,
  `AssignById` int(11) NOT NULL,
  `AssignDate` date NOT NULL,
  `AssignedComments` text,
  `DeAssignFlag` tinyint(1) default '1',
  `CreatedDt` datetime NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `UpdatedDt` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `Isactive` tinyint(1) NOT NULL default '1',
  PRIMARY KEY  (`QrAssignedRequirementId`),
  KEY `UpdatedBy` (`UpdatedBy`),
  KEY `CreatedBy` (`CreatedBy`),
  KEY `QrRequirementId` (`QrRequirementId`),
  KEY `AssignToId` (`AssignToId`),
  KEY `AssignById` (`AssignById`),
  KEY `FK7581DEB0F911477C` (`AssignById`),
  KEY `FK7581DEB047BC20AD` (`UpdatedBy`),
  KEY `FK7581DEB0F91950A0` (`AssignToId`),
  KEY `FK7581DEB06D2BC58C` (`QrRequirementId`),
  KEY `FK7581DEB0DF94E79A` (`CreatedBy`),
  CONSTRAINT `FK7581DEB047BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `FK7581DEB06D2BC58C` FOREIGN KEY (`QrRequirementId`) REFERENCES `qr_requirement` (`QrRequirementId`),
  CONSTRAINT `FK7581DEB0DF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `FK7581DEB0F911477C` FOREIGN KEY (`AssignById`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `FK7581DEB0F91950A0` FOREIGN KEY (`AssignToId`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_assigned_requirement_ibfk_1` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_assigned_requirement_ibfk_2` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_assigned_requirement_ibfk_3` FOREIGN KEY (`QrRequirementId`) REFERENCES `qr_requirement` (`QrRequirementId`),
  CONSTRAINT `qr_assigned_requirement_ibfk_4` FOREIGN KEY (`AssignToId`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_assigned_requirement_ibfk_5` FOREIGN KEY (`AssignById`) REFERENCES `qr_hr_user_info` (`QrHrUserId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_assigned_requirement`
--

LOCK TABLES `qr_assigned_requirement` WRITE;
/*!40000 ALTER TABLE `qr_assigned_requirement` DISABLE KEYS */;
/*!40000 ALTER TABLE `qr_assigned_requirement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_calendar`
--

DROP TABLE IF EXISTS `qr_calendar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_calendar` (
  `EventID` int(10) NOT NULL auto_increment,
  `UserID` int(10) default NULL,
  `JobId` varchar(20) default NULL,
  `QrApplicantName` varchar(150) default NULL,
  `QrClientName` varchar(150) NOT NULL,
  `StartDate` datetime default NULL,
  `EndDate` datetime default NULL,
  `EventName` varchar(30) default NULL,
  `Description` varchar(150) default NULL,
  `Category` varchar(20) default NULL,
  `IsRecEvent` tinyint(1) default NULL,
  `Timezone` varchar(150) default NULL,
  `Created` datetime default NULL,
  `CreatedBy` int(11) default NULL,
  `Updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) default NULL,
  `IsActive` tinyint(1) default '1',
  `QrApplicantsBaseId` int(11) default NULL,
  `MailTo` varchar(60) default NULL,
  `MailCC` varchar(60) default NULL,
  PRIMARY KEY  (`EventID`),
  KEY `FK7C6B739C47BC20AD` (`UpdatedBy`),
  KEY `FK7C6B739CDF94E79A` (`CreatedBy`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_calendar`
--

LOCK TABLES `qr_calendar` WRITE;
/*!40000 ALTER TABLE `qr_calendar` DISABLE KEYS */;
/*!40000 ALTER TABLE `qr_calendar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_city`
--

DROP TABLE IF EXISTS `qr_city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_city` (
  `QrCityId` int(11) NOT NULL auto_increment,
  `Name` varchar(50) default NULL,
  `QrStateId` int(11) NOT NULL,
  PRIMARY KEY  (`QrCityId`),
  KEY `QrStateId` (`QrStateId`),
  KEY `Name` (`Name`),
  KEY `FK2191F0C9F6874CA8` (`QrStateId`),
  CONSTRAINT `FK2191F0C9F6874CA8` FOREIGN KEY (`QrStateId`) REFERENCES `qr_state` (`QrStateId`),
  CONSTRAINT `qr_city_ibfk_1` FOREIGN KEY (`QrStateId`) REFERENCES `qr_state` (`QrStateId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_city`
--

LOCK TABLES `qr_city` WRITE;
/*!40000 ALTER TABLE `qr_city` DISABLE KEYS */;
/*!40000 ALTER TABLE `qr_city` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_client_base_info`
--

DROP TABLE IF EXISTS `qr_client_base_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_client_base_info` (
  `QrClientBaseId` int(11) NOT NULL auto_increment,
  `QrClientName` varchar(150) NOT NULL,
  `QrClientShName` varchar(150) default NULL,
  `Address1` varchar(150) default NULL,
  `Address2` varchar(150) default NULL,
  `QrCountryId` int(11) NOT NULL,
  `City` varchar(60) default NULL,
  `State` varchar(60) default NULL,
  `PostalCode` varchar(20) default NULL,
  `Fax` varchar(20) default NULL,
  `Website` varchar(100) default NULL,
  `Industry` varchar(60) default NULL,
  `BusinessCategory` varchar(60) default NULL,
  `client_contract` tinyint(1) default '0',
  `Owner` tinyint(1) NOT NULL default '0',
  `CreatedDt` datetime NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `UpdatedDt` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `Isactive` tinyint(1) NOT NULL default '1',
  PRIMARY KEY  (`QrClientBaseId`),
  KEY `UpdatedBy` (`UpdatedBy`),
  KEY `CreatedBy` (`CreatedBy`),
  KEY `FK22C5D84647BC20AD` (`UpdatedBy`),
  KEY `FK22C5D846DF94E79A` (`CreatedBy`),
  KEY `FK22C5D846BC4D3B2` (`QrCountryId`),
  CONSTRAINT `FK22C5D84647BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `FK22C5D846BC4D3B2` FOREIGN KEY (`QrCountryId`) REFERENCES `qr_country` (`QrCountryId`),
  CONSTRAINT `FK22C5D846DF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_client_base_info_ibfk_1` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_client_base_info_ibfk_2` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_client_base_info_ibfk_3` FOREIGN KEY (`QrCountryId`) REFERENCES `qr_country` (`QrCountryId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_client_base_info`
--

LOCK TABLES `qr_client_base_info` WRITE;
/*!40000 ALTER TABLE `qr_client_base_info` DISABLE KEYS */;
INSERT INTO `qr_client_base_info` VALUES (1,'localhost',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,'2016-05-28 00:00:00',1,'2016-05-28 11:57:29',1,1);
/*!40000 ALTER TABLE `qr_client_base_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_client_broadcast`
--

DROP TABLE IF EXISTS `qr_client_broadcast`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_client_broadcast` (
  `QrDirectClientId` int(11) NOT NULL auto_increment,
  `QrApplicantsBaseId` int(11) NOT NULL,
  `QrClientBaseId` int(11) NOT NULL,
  `CreatedDt` datetime NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `UpdatedDt` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  PRIMARY KEY  (`QrDirectClientId`),
  KEY `QrApplicantsBaseId` (`QrApplicantsBaseId`),
  KEY `QrClientBaseId` (`QrClientBaseId`),
  KEY `CreatedBy` (`CreatedBy`),
  KEY `UpdatedBy` (`UpdatedBy`),
  KEY `FK2E910FAB44E72C89` (`QrClientBaseId`),
  KEY `FK2E910FAB47BC20AD` (`UpdatedBy`),
  KEY `FK2E910FABDF94E79A` (`CreatedBy`),
  KEY `FK2E910FAB9816EB55` (`QrApplicantsBaseId`),
  CONSTRAINT `FK2E910FAB44E72C89` FOREIGN KEY (`QrClientBaseId`) REFERENCES `qr_client_base_info` (`QrClientBaseId`),
  CONSTRAINT `FK2E910FAB47BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `FK2E910FAB9816EB55` FOREIGN KEY (`QrApplicantsBaseId`) REFERENCES `qr_applicants_base_info` (`QrApplicantsBaseId`),
  CONSTRAINT `FK2E910FABDF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_direct_client_ibfk_1` FOREIGN KEY (`QrApplicantsBaseId`) REFERENCES `qr_applicants_base_info` (`QrApplicantsBaseId`),
  CONSTRAINT `qr_direct_client_ibfk_3` FOREIGN KEY (`QrClientBaseId`) REFERENCES `qr_client_base_info` (`QrClientBaseId`),
  CONSTRAINT `qr_direct_client_ibfk_4` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_direct_client_ibfk_5` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_client_broadcast`
--

LOCK TABLES `qr_client_broadcast` WRITE;
/*!40000 ALTER TABLE `qr_client_broadcast` DISABLE KEYS */;
/*!40000 ALTER TABLE `qr_client_broadcast` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_client_contact_info`
--

DROP TABLE IF EXISTS `qr_client_contact_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_client_contact_info` (
  `QrClientContactId` int(11) NOT NULL auto_increment,
  `QrClientBaseId` int(11) default NULL,
  `Salutation` varchar(20) default NULL,
  `ContFName` varchar(150) NOT NULL,
  `ContMName` varchar(150) default NULL,
  `ContLName` varchar(150) NOT NULL,
  `Phone` varchar(20) default NULL,
  `PhoneWorkExt` varchar(10) default NULL,
  `PhoneWorkAdd` varchar(20) default NULL,
  `PhoneMob` varchar(20) default NULL,
  `ClientPriMailId` varchar(100) NOT NULL,
  `ClientSecMailId` varchar(100) default NULL,
  `CreatedDt` datetime NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `UpdatedDt` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `Isactive` tinyint(1) NOT NULL default '1',
  PRIMARY KEY  (`QrClientContactId`),
  UNIQUE KEY `ClientPriMailId` (`ClientPriMailId`,`Isactive`),
  KEY `CreatedBy` (`CreatedBy`),
  KEY `UpdatedBy` (`UpdatedBy`),
  KEY `QrClientBaseId` (`QrClientBaseId`),
  KEY `FK4068064344E72C89` (`QrClientBaseId`),
  KEY `FK4068064347BC20AD` (`UpdatedBy`),
  KEY `FK40680643DF94E79A` (`CreatedBy`),
  CONSTRAINT `FK4068064344E72C89` FOREIGN KEY (`QrClientBaseId`) REFERENCES `qr_client_base_info` (`QrClientBaseId`),
  CONSTRAINT `FK4068064347BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `FK40680643DF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_client_contact_info_ibfk_1` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_client_contact_info_ibfk_2` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_client_contact_info_ibfk_3` FOREIGN KEY (`QrClientBaseId`) REFERENCES `qr_client_base_info` (`QrClientBaseId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_client_contact_info`
--

LOCK TABLES `qr_client_contact_info` WRITE;
/*!40000 ALTER TABLE `qr_client_contact_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `qr_client_contact_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_client_notes`
--

DROP TABLE IF EXISTS `qr_client_notes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_client_notes` (
  `QrClientNotesId` int(11) NOT NULL auto_increment,
  `QrClientBaseId` int(11) NOT NULL,
  `QrHrUserId` int(11) NOT NULL,
  `QrClientContactId` int(11) default NULL,
  `ClientComments` text NOT NULL,
  `CreatedDt` datetime NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `UpdatedDt` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `Isactive` tinyint(1) NOT NULL default '1',
  PRIMARY KEY  (`QrClientNotesId`),
  KEY `QrClientBaseId` (`QrClientBaseId`),
  KEY `CreatedBy` (`CreatedBy`),
  KEY `UpdatedBy` (`UpdatedBy`),
  KEY `FK7FFD7AAB26FAA32C` (`QrHrUserId`),
  KEY `FK7FFD7AAB44E72C89` (`QrClientBaseId`),
  KEY `FK7FFD7AAB47BC20AD` (`UpdatedBy`),
  KEY `FK7FFD7AABDF94E79A` (`CreatedBy`),
  KEY `FK7FFD7AABF7E6CB50` (`QrClientContactId`),
  CONSTRAINT `FK7FFD7AAB26FAA32C` FOREIGN KEY (`QrHrUserId`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `FK7FFD7AAB44E72C89` FOREIGN KEY (`QrClientBaseId`) REFERENCES `qr_client_base_info` (`QrClientBaseId`),
  CONSTRAINT `FK7FFD7AAB47BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `FK7FFD7AABDF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `FK7FFD7AABF7E6CB50` FOREIGN KEY (`QrClientContactId`) REFERENCES `qr_client_contact_info` (`QrClientContactId`),
  CONSTRAINT `qr_client_notes_ibfk_1` FOREIGN KEY (`QrClientBaseId`) REFERENCES `qr_client_base_info` (`QrClientBaseId`),
  CONSTRAINT `qr_client_notes_ibfk_2` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_client_notes_ibfk_3` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_client_notes_ibfk_4` FOREIGN KEY (`QrClientContactId`) REFERENCES `qr_client_contact_info` (`QrClientContactId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_client_notes`
--

LOCK TABLES `qr_client_notes` WRITE;
/*!40000 ALTER TABLE `qr_client_notes` DISABLE KEYS */;
/*!40000 ALTER TABLE `qr_client_notes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_client_submission`
--

DROP TABLE IF EXISTS `qr_client_submission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_client_submission` (
  `QrClientSubmissionId` int(11) NOT NULL auto_increment,
  `QrClientBaseId` int(11) default NULL,
  `QrApplicantsBaseId` int(11) default NULL,
  `QrHrUserId` int(11) default NULL,
  `QrRequirementId` int(11) default NULL,
  `QrApplicationReceivedId` int(11) default NULL,
  `ClientContactIds` varchar(500) default NULL,
  `ClientContactEmailIds` varchar(500) default NULL,
  `SubmissionDate` date default NULL,
  `SubmissionRate` double default NULL,
  `SubmissionCount` int(11) default '0',
  `ResendCount` int(11) default '0',
  `ForwardCount` int(11) default '0',
  `CreatedDt` datetime NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `UpdatedDt` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL,
  PRIMARY KEY  (`QrClientSubmissionId`),
  KEY `qr_client_submission_ibfk_2` (`QrClientBaseId`),
  KEY `qr_client_submission_ibfk_3` (`QrApplicantsBaseId`),
  KEY `qr_client_submission_ibfk_4` (`QrHrUserId`),
  KEY `qr_client_submission_ibfk_5` (`QrRequirementId`),
  KEY `qr_client_submission_ibfk_6` (`CreatedBy`),
  KEY `qr_client_submission_ibfk_7` (`UpdatedBy`),
  KEY `FKD70BF1A226FAA32C` (`QrHrUserId`),
  KEY `FKD70BF1A244E72C89` (`QrClientBaseId`),
  KEY `FKD70BF1A247BC20AD` (`UpdatedBy`),
  KEY `FKD70BF1A26D2BC58C` (`QrRequirementId`),
  KEY `FKD70BF1A2DF94E79A` (`CreatedBy`),
  KEY `FKD70BF1A29816EB55` (`QrApplicantsBaseId`),
  KEY `FKD70BF1A2E5144E88` (`QrApplicationReceivedId`),
  CONSTRAINT `FKD70BF1A226FAA32C` FOREIGN KEY (`QrHrUserId`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `FKD70BF1A244E72C89` FOREIGN KEY (`QrClientBaseId`) REFERENCES `qr_client_base_info` (`QrClientBaseId`),
  CONSTRAINT `FKD70BF1A247BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `FKD70BF1A26D2BC58C` FOREIGN KEY (`QrRequirementId`) REFERENCES `qr_requirement` (`QrRequirementId`),
  CONSTRAINT `FKD70BF1A29816EB55` FOREIGN KEY (`QrApplicantsBaseId`) REFERENCES `qr_applicants_base_info` (`QrApplicantsBaseId`),
  CONSTRAINT `FKD70BF1A2DF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `FKD70BF1A2E5144E88` FOREIGN KEY (`QrApplicationReceivedId`) REFERENCES `qr_application_received` (`QrApplicationReceivedId`),
  CONSTRAINT `qr_client_submission_ibfk_2` FOREIGN KEY (`QrClientBaseId`) REFERENCES `qr_client_base_info` (`QrClientBaseId`),
  CONSTRAINT `qr_client_submission_ibfk_4` FOREIGN KEY (`QrHrUserId`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_client_submission_ibfk_5` FOREIGN KEY (`QrRequirementId`) REFERENCES `qr_requirement` (`QrRequirementId`),
  CONSTRAINT `qr_client_submission_ibfk_6` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_client_submission_ibfk_7` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_client_submission_ibfk_8` FOREIGN KEY (`QrApplicantsBaseId`) REFERENCES `qr_applicants_base_info` (`QrApplicantsBaseId`),
  CONSTRAINT `qr_client_submission_ibfk_9` FOREIGN KEY (`QrApplicationReceivedId`) REFERENCES `qr_application_received` (`QrApplicationReceivedId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_client_submission`
--

LOCK TABLES `qr_client_submission` WRITE;
/*!40000 ALTER TABLE `qr_client_submission` DISABLE KEYS */;
/*!40000 ALTER TABLE `qr_client_submission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_corp_corp_feed_jobs`
--

DROP TABLE IF EXISTS `qr_corp_corp_feed_jobs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_corp_corp_feed_jobs` (
  `QrCorpCorpFeedJobsId` int(11) NOT NULL auto_increment,
  `QrRequirementId` int(11) NOT NULL,
  `QrHrUserId` int(11) NOT NULL,
  `JobFeedDate` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `UpdatedBy` int(11) NOT NULL,
  `CreatedDt` date default NULL,
  `UpdatedDt` datetime default NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  PRIMARY KEY  (`QrCorpCorpFeedJobsId`),
  KEY `CreatedBy` (`CreatedBy`),
  KEY `UpdatedBy` (`UpdatedBy`),
  KEY `QrHrUserId` (`QrHrUserId`),
  KEY `QrRequirementId` (`QrRequirementId`),
  KEY `FK443B961926FAA32C` (`QrHrUserId`),
  KEY `FK443B961947BC20AD` (`UpdatedBy`),
  KEY `FK443B96196D2BC58C` (`QrRequirementId`),
  KEY `FK443B9619DF94E79A` (`CreatedBy`),
  CONSTRAINT `FK443B961926FAA32C` FOREIGN KEY (`QrHrUserId`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `FK443B961947BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `FK443B96196D2BC58C` FOREIGN KEY (`QrRequirementId`) REFERENCES `qr_requirement` (`QrRequirementId`),
  CONSTRAINT `FK443B9619DF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_corp_corp_feed_jobs_ibfk_1` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_corp_corp_feed_jobs_ibfk_2` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_corp_corp_feed_jobs_ibfk_3` FOREIGN KEY (`QrHrUserId`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_corp_corp_feed_jobs_ibfk_4` FOREIGN KEY (`QrRequirementId`) REFERENCES `qr_requirement` (`QrRequirementId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_corp_corp_feed_jobs`
--

LOCK TABLES `qr_corp_corp_feed_jobs` WRITE;
/*!40000 ALTER TABLE `qr_corp_corp_feed_jobs` DISABLE KEYS */;
/*!40000 ALTER TABLE `qr_corp_corp_feed_jobs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_country`
--

DROP TABLE IF EXISTS `qr_country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_country` (
  `QrCountryId` int(11) NOT NULL auto_increment,
  `Name` varchar(100) NOT NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  PRIMARY KEY  (`QrCountryId`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_country`
--

LOCK TABLES `qr_country` WRITE;
/*!40000 ALTER TABLE `qr_country` DISABLE KEYS */;
INSERT INTO `qr_country` VALUES (1,'USA',1),(2,'Canada',1),(3,'India',1),(4,'Albania',1),(5,'Angola',1),(6,'Austria',1),(7,'Belarus',1),(8,'Belgium',1),(9,'Bosnia and Herzegovina',1),(10,'Bulgaria',1),(11,'Croatia',1),(12,'Cyprus',1),(13,'Czeck Republic',1),(14,'Denmark',1),(15,'Estonia',1),(16,'Finland',1),(17,'France',1),(18,'Germany',1),(19,'Greece',1),(20,'Hungary',1),(21,'Iceland',1),(22,'Ireland',1),(23,'Italy',1),(24,'Latvia',1),(25,'Liechtenstein',1),(26,'Lithuania',1),(27,'Luxembourg',1),(28,'Macedonia',1),(29,'Malta',1),(30,'Moldova',1),(31,'Monaco',1),(32,'Netherlands',1),(33,'Norway',1),(34,'Poland',1),(35,'Portugal',1),(36,'Romainia',1),(37,'Russia',1),(38,'San Marino',1),(39,'Slovakia',1),(40,'Slovenia',1),(41,'Spain',1),(42,'Sweden',1),(43,'Switzerland',1),(44,'Turkey',1),(45,'Ukraine',1),(46,'United Kingdom',1),(47,'Vatican City',1);
/*!40000 ALTER TABLE `qr_country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_educational_qualification`
--

DROP TABLE IF EXISTS `qr_educational_qualification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_educational_qualification` (
  `QrQualificationId` int(11) NOT NULL auto_increment,
  `QualificationName` varchar(60) NOT NULL,
  `CreatedDt` datetime NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `UpdatedDt` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `Isactive` tinyint(1) NOT NULL,
  PRIMARY KEY  (`QrQualificationId`),
  KEY `qr_educational_qualification_ibfk_1` (`CreatedBy`),
  KEY `qr_educational_qualification_ibfk_2` (`UpdatedBy`),
  KEY `FKEFCFBE547BC20AD` (`UpdatedBy`),
  KEY `FKEFCFBE5DF94E79A` (`CreatedBy`),
  CONSTRAINT `FKEFCFBE547BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `FKEFCFBE5DF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_educational_qualification_ibfk_1` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_educational_qualification_ibfk_2` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_educational_qualification`
--

LOCK TABLES `qr_educational_qualification` WRITE;
/*!40000 ALTER TABLE `qr_educational_qualification` DISABLE KEYS */;
/*!40000 ALTER TABLE `qr_educational_qualification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_email_reader_configuration`
--

DROP TABLE IF EXISTS `qr_email_reader_configuration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_email_reader_configuration` (
  `QrEmailReaderConfigurationId` int(11) NOT NULL auto_increment,
  `EmailReaderHostName` varchar(255) NOT NULL,
  `EmailReaderHostServerType` varchar(50) NOT NULL,
  `EmailReaderUserId` varchar(255) default NULL,
  `EmailReaderPassword` varchar(255) NOT NULL,
  `EmailReaderEmailId` varchar(255) NOT NULL,
  `EmailReaderType` varchar(50) NOT NULL,
  `CreatedDt` datetime NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `UpdatedDt` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `Isactive` tinyint(1) NOT NULL default '1',
  PRIMARY KEY  (`QrEmailReaderConfigurationId`),
  KEY `CreatedBy` (`CreatedBy`),
  KEY `UpdatedBy` (`UpdatedBy`),
  KEY `FKA888541B47BC20AD` (`UpdatedBy`),
  KEY `FKA888541BDF94E79A` (`CreatedBy`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_email_reader_configuration`
--

LOCK TABLES `qr_email_reader_configuration` WRITE;
/*!40000 ALTER TABLE `qr_email_reader_configuration` DISABLE KEYS */;
/*!40000 ALTER TABLE `qr_email_reader_configuration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_email_send_to_client`
--

DROP TABLE IF EXISTS `qr_email_send_to_client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_email_send_to_client` (
  `QrEmailSendToClientId` int(11) NOT NULL auto_increment,
  `SubmissionMailedBy` int(11) default NULL,
  `SubmissionMailedTo` varchar(500) default NULL,
  `SubMailCc` varchar(100) default NULL,
  `SubMailBcc` varchar(400) default NULL,
  `SubMailSubject` varchar(500) default NULL,
  `SubMailMessage` text,
  `MailSignature` varchar(500) default NULL,
  `MailAttachName` varchar(60) default NULL,
  `SubMailResumeAttached` tinyint(1) NOT NULL,
  `SkillDetails` tinyint(1) NOT NULL,
  `CreatedDt` datetime NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `UpdatedDt` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL,
  `QrClientSubmissionId` int(11) default NULL,
  `SubmissionType` varchar(50) NOT NULL default 'SUBMISSION',
  PRIMARY KEY  (`QrEmailSendToClientId`),
  KEY `qr_email_send_to_client_ibfk_1` (`SubmissionMailedBy`),
  KEY `qr_email_send_to_client_ibfk_2` (`SubmissionMailedTo`(255)),
  KEY `qr_email_send_to_client_ibfk_3` (`CreatedBy`),
  KEY `qr_email_send_to_client_ibfk_4` (`UpdatedBy`),
  KEY `FK94E9847999A533B0` (`QrClientSubmissionId`),
  KEY `FK94E9847947BC20AD` (`UpdatedBy`),
  KEY `FK94E9847973716514` (`SubmissionMailedBy`),
  KEY `FK94E98479DF94E79A` (`CreatedBy`),
  CONSTRAINT `FK94E9847947BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `FK94E9847973716514` FOREIGN KEY (`SubmissionMailedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `FK94E98479DF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_email_send_to_client_ibfk_1` FOREIGN KEY (`SubmissionMailedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_email_send_to_client_ibfk_3` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_email_send_to_client_ibfk_4` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_email_send_to_client`
--

LOCK TABLES `qr_email_send_to_client` WRITE;
/*!40000 ALTER TABLE `qr_email_send_to_client` DISABLE KEYS */;
/*!40000 ALTER TABLE `qr_email_send_to_client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_ethnic_race`
--

DROP TABLE IF EXISTS `qr_ethnic_race`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_ethnic_race` (
  `QrEthnicRaceId` int(11) NOT NULL auto_increment,
  `EthnicRaceDesc` varchar(255) NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  `CreatedDt` date default NULL,
  `UpdatedDt` datetime default NULL,
  PRIMARY KEY  (`QrEthnicRaceId`),
  KEY `CreatedBy` (`CreatedBy`),
  KEY `UpdatedBy` (`UpdatedBy`),
  KEY `FK7309822347BC20AD` (`UpdatedBy`),
  KEY `FK73098223DF94E79A` (`CreatedBy`),
  CONSTRAINT `FK7309822347BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `FK73098223DF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_ethnic_race_ibfk_1` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_ethnic_race_ibfk_2` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_ethnic_race`
--

LOCK TABLES `qr_ethnic_race` WRITE;
/*!40000 ALTER TABLE `qr_ethnic_race` DISABLE KEYS */;
/*!40000 ALTER TABLE `qr_ethnic_race` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_hr_user_info`
--

DROP TABLE IF EXISTS `qr_hr_user_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_hr_user_info` (
  `QrHrUserId` int(11) NOT NULL auto_increment,
  `QrRoleId` int(11) NOT NULL,
  `HrUserFName` varchar(150) NOT NULL,
  `HrUserMName` varchar(150) default NULL,
  `HrUserLName` varchar(150) NOT NULL,
  `Salutation` varchar(20) NOT NULL,
  `Address1` varchar(150) NOT NULL,
  `Address2` varchar(150) default NULL,
  `QrCountryId` int(11) NOT NULL,
  `City` varchar(60) NOT NULL,
  `State` varchar(60) NOT NULL,
  `PostalCode` varchar(20) NOT NULL,
  `PhoneWork` varchar(20) NOT NULL,
  `PhoneWorkExt` varchar(10) default NULL,
  `PhoneWorkAdd` varchar(20) default NULL,
  `PhoneMob` varchar(20) default NULL,
  `HrUserPriEmail` varchar(100) NOT NULL,
  `HrUserSecEmail` varchar(100) default NULL,
  `ReportingTo` varchar(50) default NULL,
  `HrUserJobTitle` varchar(60) default NULL,
  `JoinedDate` date NOT NULL,
  `EmergencyContactName` varchar(60) default NULL,
  `EmergencyContactNum` varchar(50) default NULL,
  `TerminateDate` date default NULL,
  `TotYearExp` varchar(20) NOT NULL,
  `IsCustomer` tinyint(1) NOT NULL default '0',
  `CreatedDt` datetime NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `UpdatedDt` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `Isactive` tinyint(1) NOT NULL default '1',
  `BccMail` varchar(100) default NULL,
  PRIMARY KEY  (`QrHrUserId`),
  KEY `CreatedBy` (`CreatedBy`),
  KEY `UpdatedBy` (`UpdatedBy`),
  KEY `FKEAB8B50B47BC20AD` (`UpdatedBy`),
  KEY `FKEAB8B50BDF94E79A` (`CreatedBy`),
  KEY `FKEAB8B50BADC27784` (`ReportingTo`),
  KEY `QrRoleId` (`QrRoleId`),
  KEY `FKEAB8B50B8827D34E` (`QrRoleId`),
  KEY `FKEAB8B50BBC4D3B2` (`QrCountryId`),
  CONSTRAINT `FKEAB8B50B47BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `FKEAB8B50BBC4D3B2` FOREIGN KEY (`QrCountryId`) REFERENCES `qr_country` (`QrCountryId`),
  CONSTRAINT `FKEAB8B50BDF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_hr_user_info_ibfk_1` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_hr_user_info_ibfk_2` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_hr_user_info_ibfk_3` FOREIGN KEY (`QrRoleId`) REFERENCES `qr_role` (`QrRoleId`),
  CONSTRAINT `qr_hr_user_info_ibfk_4` FOREIGN KEY (`QrCountryId`) REFERENCES `qr_country` (`QrCountryId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_hr_user_info`
--

LOCK TABLES `qr_hr_user_info` WRITE;
/*!40000 ALTER TABLE `qr_hr_user_info` DISABLE KEYS */;
INSERT INTO `qr_hr_user_info` VALUES (1,2,'Admin','Admin','Admin','Mr','NA','NA',1,'NA','NA','123456','1234567890','','','','username@domainname.com','','1','','2012-04-01','','',NULL,'0',0,'2012-01-31 00:00:00',1,'2012-02-06 20:37:51',1,1,NULL);
/*!40000 ALTER TABLE `qr_hr_user_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_hr_user_login`
--

DROP TABLE IF EXISTS `qr_hr_user_login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_hr_user_login` (
  `QrHrUserLoginId` int(11) NOT NULL auto_increment,
  `QrHrUserId` int(11) NOT NULL,
  `UserName` varchar(60) NOT NULL,
  `Password` varchar(60) NOT NULL,
  `ForgetPassword` tinyint(1) default '0',
  `CreatedDt` datetime default NULL,
  `CreatedBy` int(11) default NULL,
  `UpdatedDt` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) default NULL,
  `Isactive` tinyint(1) default '1',
  PRIMARY KEY  (`QrHrUserLoginId`),
  KEY `CreatedBy` (`CreatedBy`),
  KEY `UpdatedBy` (`UpdatedBy`),
  KEY `QrHrUserId` (`QrHrUserId`),
  KEY `FK6C88AAAC26FAA32C` (`QrHrUserId`),
  KEY `FK6C88AAAC47BC20AD` (`UpdatedBy`),
  KEY `FK6C88AAACDF94E79A` (`CreatedBy`),
  CONSTRAINT `FK6C88AAAC26FAA32C` FOREIGN KEY (`QrHrUserId`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `FK6C88AAAC47BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `FK6C88AAACDF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_hr_user_login_ibfk_1` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_hr_user_login_ibfk_2` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_hr_user_login_ibfk_3` FOREIGN KEY (`QrHrUserId`) REFERENCES `qr_hr_user_info` (`QrHrUserId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_hr_user_login`
--

LOCK TABLES `qr_hr_user_login` WRITE;
/*!40000 ALTER TABLE `qr_hr_user_login` DISABLE KEYS */;
INSERT INTO `qr_hr_user_login` VALUES (1,1,'admin','admin@12',0,'2011-01-20 12:48:21',1,'2012-01-13 04:22:45',1,1);
/*!40000 ALTER TABLE `qr_hr_user_login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_idiligo_config`
--

DROP TABLE IF EXISTS `qr_idiligo_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_idiligo_config` (
  `QrIdiligoId` int(11) NOT NULL auto_increment,
  `IdiligoUserName` varchar(255) default NULL,
  `IdiligoPassword` varchar(255) default NULL,
  `CreatedDt` date default NULL,
  `UpdatedDt` datetime default NULL,
  `IsActive` smallint(6) default NULL,
  `CreatedBy` int(11) default NULL,
  `UpdatedBy` int(11) default NULL,
  PRIMARY KEY  (`QrIdiligoId`),
  KEY `FK62D4B80C47BC20AD` (`UpdatedBy`),
  KEY `FK62D4B80CDF94E79A` (`CreatedBy`),
  CONSTRAINT `FK62D4B80C47BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `FK62D4B80CDF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_idiligo_config`
--

LOCK TABLES `qr_idiligo_config` WRITE;
/*!40000 ALTER TABLE `qr_idiligo_config` DISABLE KEYS */;
/*!40000 ALTER TABLE `qr_idiligo_config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_import_resumes`
--

DROP TABLE IF EXISTS `qr_import_resumes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_import_resumes` (
  `QrImportResumesId` int(11) NOT NULL auto_increment,
  `QrHrUserId` int(11) default NULL,
  `TextResume` text,
  `ImpFolderName` varchar(60) NOT NULL,
  `ResumeName` varchar(50) default NULL,
  `CreatedDt` datetime NOT NULL,
  `CreatedBy` int(11) default NULL,
  `UpdatedDt` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) default NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  PRIMARY KEY  (`QrImportResumesId`),
  KEY `UpdatedBy` (`UpdatedBy`),
  KEY `CreatedBy` (`CreatedBy`),
  KEY `QrHrUserId` (`QrHrUserId`),
  KEY `FK4AB11D2A26FAA32C` (`QrHrUserId`),
  KEY `FK4AB11D2A47BC20AD` (`UpdatedBy`),
  KEY `FK4AB11D2ADF94E79A` (`CreatedBy`),
  CONSTRAINT `FK4AB11D2A26FAA32C` FOREIGN KEY (`QrHrUserId`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `FK4AB11D2A47BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `FK4AB11D2ADF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_import_resumes_ibfk_1` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_import_resumes_ibfk_2` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_import_resumes_ibfk_3` FOREIGN KEY (`QrHrUserId`) REFERENCES `qr_hr_user_info` (`QrHrUserId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_import_resumes`
--

LOCK TABLES `qr_import_resumes` WRITE;
/*!40000 ALTER TABLE `qr_import_resumes` DISABLE KEYS */;
/*!40000 ALTER TABLE `qr_import_resumes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_job_board_config`
--

DROP TABLE IF EXISTS `qr_job_board_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_job_board_config` (
  `QrJobBoardConfigId` int(11) NOT NULL auto_increment,
  `QrHrUserId` int(11) NOT NULL,
  `JobBoardUserName` varchar(255) NOT NULL,
  `JobBoardPassword` varchar(255) NOT NULL,
  `JobBoardName` varchar(255) NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `UpdatedBy` int(11) NOT NULL,
  `CreatedDt` datetime NOT NULL,
  `UpdatedDt` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `IsActive` tinyint(1) NOT NULL default '1',
  PRIMARY KEY  (`QrJobBoardConfigId`),
  KEY `CreatedBy` (`CreatedBy`),
  KEY `UpdatedBy` (`UpdatedBy`),
  KEY `QrHrUserId` (`QrHrUserId`),
  KEY `FK9261E65B26FAA32C` (`QrHrUserId`),
  KEY `FK9261E65B47BC20AD` (`UpdatedBy`),
  KEY `FK9261E65BDF94E79A` (`CreatedBy`),
  CONSTRAINT `FK9261E65B26FAA32C` FOREIGN KEY (`QrHrUserId`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `FK9261E65B47BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `FK9261E65BDF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_job_board_config_ibfk_1` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_job_board_config_ibfk_2` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_job_board_config_ibfk_3` FOREIGN KEY (`QrHrUserId`) REFERENCES `qr_hr_user_info` (`QrHrUserId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_job_board_config`
--

LOCK TABLES `qr_job_board_config` WRITE;
/*!40000 ALTER TABLE `qr_job_board_config` DISABLE KEYS */;
/*!40000 ALTER TABLE `qr_job_board_config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_job_board_config_corp_corp`
--

DROP TABLE IF EXISTS `qr_job_board_config_corp_corp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_job_board_config_corp_corp` (
  `QrJobBoardConfigCorpCorpId` int(11) NOT NULL auto_increment,
  `QrHrUserId` int(11) NOT NULL,
  `CorpCorpUserName` varchar(255) NOT NULL,
  `CorpCorpPassword` varchar(255) NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `UpdatedBy` int(11) NOT NULL,
  `CreatedDt` date default NULL,
  `UpdatedDt` datetime default NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  PRIMARY KEY  (`QrJobBoardConfigCorpCorpId`),
  KEY `CreatedBy` (`CreatedBy`),
  KEY `UpdatedBy` (`UpdatedBy`),
  KEY `QrHrUserId` (`QrHrUserId`),
  KEY `FK8E6EDE3B26FAA32C` (`QrHrUserId`),
  KEY `FK8E6EDE3B47BC20AD` (`UpdatedBy`),
  KEY `FK8E6EDE3BDF94E79A` (`CreatedBy`),
  CONSTRAINT `FK8E6EDE3B26FAA32C` FOREIGN KEY (`QrHrUserId`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `FK8E6EDE3B47BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `FK8E6EDE3BDF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_job_board_config_corp_corp_ibfk_1` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_job_board_config_corp_corp_ibfk_2` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_job_board_config_corp_corp_ibfk_3` FOREIGN KEY (`QrHrUserId`) REFERENCES `qr_hr_user_info` (`QrHrUserId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_job_board_config_corp_corp`
--

LOCK TABLES `qr_job_board_config_corp_corp` WRITE;
/*!40000 ALTER TABLE `qr_job_board_config_corp_corp` DISABLE KEYS */;
/*!40000 ALTER TABLE `qr_job_board_config_corp_corp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_job_board_config_indeed`
--

DROP TABLE IF EXISTS `qr_job_board_config_indeed`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_job_board_config_indeed` (
  `QrJobBoardConfigIndeedId` int(11) NOT NULL auto_increment,
  `QrHrUserId` int(11) NOT NULL,
  `IndeedUserName` varchar(255) NOT NULL,
  `IndeedPassword` varchar(255) NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `UpdatedBy` int(11) NOT NULL,
  `CreatedDt` date default NULL,
  `UpdatedDt` datetime default NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  PRIMARY KEY  (`QrJobBoardConfigIndeedId`),
  KEY `CreatedBy` (`CreatedBy`),
  KEY `UpdatedBy` (`UpdatedBy`),
  KEY `QrHrUserId` (`QrHrUserId`),
  CONSTRAINT `qr_job_board_config_indeed_ibfk_1` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_job_board_config_indeed_ibfk_2` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_job_board_config_indeed_ibfk_3` FOREIGN KEY (`QrHrUserId`) REFERENCES `qr_hr_user_info` (`QrHrUserId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_job_board_config_indeed`
--

LOCK TABLES `qr_job_board_config_indeed` WRITE;
/*!40000 ALTER TABLE `qr_job_board_config_indeed` DISABLE KEYS */;
/*!40000 ALTER TABLE `qr_job_board_config_indeed` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_job_board_post_jobs`
--

DROP TABLE IF EXISTS `qr_job_board_post_jobs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_job_board_post_jobs` (
  `QrJobBoardPostJobsId` int(11) NOT NULL auto_increment,
  `QrHrUserId` int(11) NOT NULL,
  `QrRequirementId` int(11) NOT NULL,
  `JobBoardName` varchar(255) NOT NULL,
  `JobPostDate` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `UpdatedBy` int(11) NOT NULL,
  `CreatedDt` datetime NOT NULL,
  `UpdatedDt` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `IsActive` tinyint(1) NOT NULL default '1',
  PRIMARY KEY  (`QrJobBoardPostJobsId`),
  KEY `CreatedBy` (`CreatedBy`),
  KEY `UpdatedBy` (`UpdatedBy`),
  KEY `QrHrUserId` (`QrHrUserId`),
  KEY `QrRequirementId` (`QrRequirementId`),
  KEY `FK56087ADC26FAA32C` (`QrHrUserId`),
  KEY `FK56087ADC47BC20AD` (`UpdatedBy`),
  KEY `FK56087ADC6D2BC58C` (`QrRequirementId`),
  KEY `FK56087ADCDF94E79A` (`CreatedBy`),
  CONSTRAINT `FK56087ADC26FAA32C` FOREIGN KEY (`QrHrUserId`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `FK56087ADC47BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `FK56087ADC6D2BC58C` FOREIGN KEY (`QrRequirementId`) REFERENCES `qr_requirement` (`QrRequirementId`),
  CONSTRAINT `FK56087ADCDF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_corp_corp_post_jobs_ibfk_4` FOREIGN KEY (`QrRequirementId`) REFERENCES `qr_requirement` (`QrRequirementId`),
  CONSTRAINT `qr_job_board_post_jobs_ibfk_1` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_job_board_post_jobs_ibfk_2` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_job_board_post_jobs_ibfk_3` FOREIGN KEY (`QrHrUserId`) REFERENCES `qr_hr_user_info` (`QrHrUserId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_job_board_post_jobs`
--

LOCK TABLES `qr_job_board_post_jobs` WRITE;
/*!40000 ALTER TABLE `qr_job_board_post_jobs` DISABLE KEYS */;
/*!40000 ALTER TABLE `qr_job_board_post_jobs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_jobcategory_info`
--

DROP TABLE IF EXISTS `qr_jobcategory_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_jobcategory_info` (
  `QrJobCategoryId` int(11) NOT NULL auto_increment,
  `JobCategoryName` varchar(100) NOT NULL,
  `Isactive` tinyint(1) default '1',
  PRIMARY KEY  (`QrJobCategoryId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_jobcategory_info`
--

LOCK TABLES `qr_jobcategory_info` WRITE;
/*!40000 ALTER TABLE `qr_jobcategory_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `qr_jobcategory_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_location`
--

DROP TABLE IF EXISTS `qr_location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_location` (
  `QrLocationId` int(11) NOT NULL auto_increment,
  `QrCountryId` int(11) NOT NULL,
  `Name` varchar(150) NOT NULL,
  `Address1` varchar(150) NOT NULL,
  `Address2` varchar(150) default NULL,
  `City` varchar(60) NOT NULL,
  `Region` varchar(60) NOT NULL,
  `PostalCode` varchar(20) NOT NULL,
  `Phone` varchar(20) NOT NULL,
  `Fax` varchar(20) default NULL,
  `Comments` text,
  `CreatedDt` datetime NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `UpdatedDt` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `Isactive` tinyint(1) NOT NULL default '1',
  PRIMARY KEY  (`QrLocationId`),
  KEY `QrCountryId` (`QrCountryId`),
  KEY `CreatedBy` (`CreatedBy`),
  KEY `UpdatedBy` (`UpdatedBy`),
  KEY `FKF85C181347BC20AD` (`UpdatedBy`),
  KEY `FKF85C1813BC4D3B2` (`QrCountryId`),
  KEY `FKF85C1813DF94E79A` (`CreatedBy`),
  CONSTRAINT `FKF85C181347BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `FKF85C1813BC4D3B2` FOREIGN KEY (`QrCountryId`) REFERENCES `qr_country` (`QrCountryId`),
  CONSTRAINT `FKF85C1813DF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_location_ibfk_1` FOREIGN KEY (`QrCountryId`) REFERENCES `qr_country` (`QrCountryId`),
  CONSTRAINT `qr_location_ibfk_2` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_location_ibfk_3` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_location`
--

LOCK TABLES `qr_location` WRITE;
/*!40000 ALTER TABLE `qr_location` DISABLE KEYS */;
/*!40000 ALTER TABLE `qr_location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_logo`
--

DROP TABLE IF EXISTS `qr_logo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_logo` (
  `QrClientLogoId` int(11) NOT NULL auto_increment,
  `ClientLogoName` varchar(255) NOT NULL,
  `ClientLogoType` varchar(255) NOT NULL,
  `ClientLogoSize` int(11) NOT NULL,
  `CreatedDt` datetime NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `UpdatedDt` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) default '1',
  PRIMARY KEY  (`QrClientLogoId`),
  KEY `CreatedBy` (`CreatedBy`),
  KEY `UpdatedBy` (`UpdatedBy`),
  KEY `FK21961D0947BC20AD` (`UpdatedBy`),
  KEY `FK21961D09DF94E79A` (`CreatedBy`),
  CONSTRAINT `FK21961D0947BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `FK21961D09DF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_logo_ibfk_1` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_logo_ibfk_2` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_logo`
--

LOCK TABLES `qr_logo` WRITE;
/*!40000 ALTER TABLE `qr_logo` DISABLE KEYS */;
/*!40000 ALTER TABLE `qr_logo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_mail_configuration`
--

DROP TABLE IF EXISTS `qr_mail_configuration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_mail_configuration` (
  `QrMailConfigurationId` int(11) NOT NULL auto_increment,
  `QrHrUserId` int(11) NOT NULL,
  `SmtpHost` varchar(255) default NULL,
  `UserName` varchar(255) default NULL,
  `Password` varchar(255) default NULL,
  `FromMailId` varchar(255) default NULL,
  `CreatedDt` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `UpdatedDt` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  `EnableSSL` tinyint(1) NOT NULL default '0',
  `Port` int(5) NOT NULL default '25',
  PRIMARY KEY  (`QrMailConfigurationId`),
  KEY `UpdatedBy` (`UpdatedBy`),
  KEY `CreatedBy` (`CreatedBy`),
  KEY `FKFC309B4C47BC20AD` (`UpdatedBy`),
  KEY `FKFC309B4CDF94E79A` (`CreatedBy`),
  KEY `QrHrUserId` (`QrHrUserId`),
  KEY `FKFC309B4C26FAA32C` (`QrHrUserId`),
  CONSTRAINT `FKFC309B4C26FAA32C` FOREIGN KEY (`QrHrUserId`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `FKFC309B4C47BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `FKFC309B4CDF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `hcmo_mail_configuration_ibfk_1` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `hcmo_mail_configuration_ibfk_2` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `rlite_mail_configuration_ibfk_3` FOREIGN KEY (`QrHrUserId`) REFERENCES `qr_hr_user_info` (`QrHrUserId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_mail_configuration`
--

LOCK TABLES `qr_mail_configuration` WRITE;
/*!40000 ALTER TABLE `qr_mail_configuration` DISABLE KEYS */;
/*!40000 ALTER TABLE `qr_mail_configuration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_nationality`
--

DROP TABLE IF EXISTS `qr_nationality`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_nationality` (
  `QrNationalityId` int(11) NOT NULL auto_increment,
  `NatName` varchar(120) NOT NULL,
  `CreatedDt` datetime NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `UpdatedDt` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  PRIMARY KEY  (`QrNationalityId`),
  KEY `CreatedBy` (`CreatedBy`),
  KEY `UpdatedBy` (`UpdatedBy`),
  KEY `FKC9B4009E47BC20AD` (`UpdatedBy`),
  KEY `FKC9B4009EDF94E79A` (`CreatedBy`),
  CONSTRAINT `FKC9B4009E47BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `FKC9B4009EDF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_nationality_ibfk_1` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_nationality_ibfk_2` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_nationality`
--

LOCK TABLES `qr_nationality` WRITE;
/*!40000 ALTER TABLE `qr_nationality` DISABLE KEYS */;
/*!40000 ALTER TABLE `qr_nationality` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_on_off_board_applicants`
--

DROP TABLE IF EXISTS `qr_on_off_board_applicants`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_on_off_board_applicants` (
  `QrOnOffBoardApplId` int(11) NOT NULL auto_increment,
  `DateOnBoard` date default NULL,
  `DateOffBoard` date default NULL,
  `OnOffStatus` varchar(60) default NULL,
  `CreatedDt` date default NULL,
  `UpdatedDt` datetime default NULL,
  `IsActive` smallint(6) default NULL,
  `QrApplicantsBaseId` int(11) default NULL,
  `QrRequirementId` int(11) default NULL,
  `QrHrUserId` int(11) default NULL,
  `CreatedBy` int(11) default NULL,
  `UpdatedBy` int(11) default NULL,
  PRIMARY KEY  (`QrOnOffBoardApplId`),
  KEY `FKC2CD1BBC26FAA32C` (`QrHrUserId`),
  KEY `FKC2CD1BBC47BC20AD` (`UpdatedBy`),
  KEY `FKC2CD1BBC6D2BC58C` (`QrRequirementId`),
  KEY `FKC2CD1BBCDF94E79A` (`CreatedBy`),
  KEY `FKC2CD1BBC9816EB55` (`QrApplicantsBaseId`),
  CONSTRAINT `FKC2CD1BBC26FAA32C` FOREIGN KEY (`QrHrUserId`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `FKC2CD1BBC47BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `FKC2CD1BBC6D2BC58C` FOREIGN KEY (`QrRequirementId`) REFERENCES `qr_requirement` (`QrRequirementId`),
  CONSTRAINT `FKC2CD1BBC9816EB55` FOREIGN KEY (`QrApplicantsBaseId`) REFERENCES `qr_applicants_base_info` (`QrApplicantsBaseId`),
  CONSTRAINT `FKC2CD1BBCDF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_on_off_board_applicants`
--

LOCK TABLES `qr_on_off_board_applicants` WRITE;
/*!40000 ALTER TABLE `qr_on_off_board_applicants` DISABLE KEYS */;
/*!40000 ALTER TABLE `qr_on_off_board_applicants` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_onboarded_attach`
--

DROP TABLE IF EXISTS `qr_onboarded_attach`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_onboarded_attach` (
  `QrOnBoardedAttachId` int(11) NOT NULL auto_increment,
  `OnBoardedApplicantAttachName` varchar(255) default NULL,
  `OnBoardedApplicantAttachType` varchar(60) default NULL,
  `OnBoardedApplicantAttachSize` bigint(20) default NULL,
  `CreatedDt` date default NULL,
  `UpdatedDt` datetime default NULL,
  `IsActive` smallint(6) default NULL,
  `QrApplicantNotesId` int(11) default NULL,
  `CreatedBy` int(11) default NULL,
  `UpdatedBy` int(11) default NULL,
  PRIMARY KEY  (`QrOnBoardedAttachId`),
  KEY `FK91CEC7BC889F9E77` (`QrApplicantNotesId`),
  KEY `FK91CEC7BC47BC20AD` (`UpdatedBy`),
  KEY `FK91CEC7BCDF94E79A` (`CreatedBy`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_onboarded_attach`
--

LOCK TABLES `qr_onboarded_attach` WRITE;
/*!40000 ALTER TABLE `qr_onboarded_attach` DISABLE KEYS */;
/*!40000 ALTER TABLE `qr_onboarded_attach` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_org`
--

DROP TABLE IF EXISTS `qr_org`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_org` (
  `QrOrgId` int(11) NOT NULL auto_increment,
  `ParentOrgId` int(11) default NULL,
  `Name` varchar(150) default NULL,
  `Description` text,
  `CreatedDt` date default NULL,
  `UpdatedDt` datetime default NULL,
  `IsActive` smallint(6) default NULL,
  `CreatedBy` int(11) default NULL,
  `UpdatedBy` int(11) default NULL,
  `QrLocationId` int(11) default NULL,
  `QrOrgTypeId` int(11) default NULL,
  PRIMARY KEY  (`QrOrgId`),
  KEY `FKC746F3C69DECF34C` (`QrLocationId`),
  KEY `FKC746F3C6BD54C3EB` (`QrOrgTypeId`),
  KEY `FKC746F3C647BC20AD` (`UpdatedBy`),
  KEY `FKC746F3C6DF94E79A` (`CreatedBy`),
  CONSTRAINT `FKC746F3C647BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `FKC746F3C69DECF34C` FOREIGN KEY (`QrLocationId`) REFERENCES `qr_location` (`QrLocationId`),
  CONSTRAINT `FKC746F3C6BD54C3EB` FOREIGN KEY (`QrOrgTypeId`) REFERENCES `qr_org_type` (`QrOrgTypeId`),
  CONSTRAINT `FKC746F3C6DF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_org`
--

LOCK TABLES `qr_org` WRITE;
/*!40000 ALTER TABLE `qr_org` DISABLE KEYS */;
/*!40000 ALTER TABLE `qr_org` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_org_type`
--

DROP TABLE IF EXISTS `qr_org_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_org_type` (
  `QrOrgTypeId` int(11) NOT NULL auto_increment,
  `Name` varchar(150) NOT NULL,
  `Description` text,
  `CreatedDt` datetime NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `UpdatedDt` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  PRIMARY KEY  (`QrOrgTypeId`),
  KEY `UpdatedBy` (`UpdatedBy`),
  KEY `CreatedBy` (`CreatedBy`),
  KEY `FKD569C01347BC20AD` (`UpdatedBy`),
  KEY `FKD569C013DF94E79A` (`CreatedBy`),
  CONSTRAINT `FKD569C01347BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `FKD569C013DF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_org_type_ibfk_1` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_org_type_ibfk_2` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_org_type`
--

LOCK TABLES `qr_org_type` WRITE;
/*!40000 ALTER TABLE `qr_org_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `qr_org_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_privilege`
--

DROP TABLE IF EXISTS `qr_privilege`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_privilege` (
  `QrPrivilegeId` int(11) NOT NULL auto_increment,
  `PrivilegeName` varchar(255) default NULL,
  `CreatedDt` date default NULL,
  `UpdatedDt` datetime default NULL,
  `IsActive` smallint(6) default NULL,
  `CreatedBy` int(11) default NULL,
  `UpdatedBy` int(11) default NULL,
  PRIMARY KEY  (`QrPrivilegeId`),
  KEY `FKFC7D8A1347BC20AD` (`UpdatedBy`),
  KEY `FKFC7D8A13DF94E79A` (`CreatedBy`),
  CONSTRAINT `FKFC7D8A1347BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `FKFC7D8A13DF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_privilege`
--

LOCK TABLES `qr_privilege` WRITE;
/*!40000 ALTER TABLE `qr_privilege` DISABLE KEYS */;
/*!40000 ALTER TABLE `qr_privilege` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_req_attach`
--

DROP TABLE IF EXISTS `qr_req_attach`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_req_attach` (
  `QrReqAttachId` int(11) NOT NULL auto_increment,
  `QrAttachName` varchar(255) default NULL,
  `QrReqAttachType` varchar(255) default NULL,
  `QrReqAttachSize` bigint(20) default NULL,
  `CreatedDt` date default NULL,
  `UpdatedDt` datetime default NULL,
  `IsActive` smallint(6) default NULL,
  `QrRequirementId` int(11) default NULL,
  `CreatedBy` int(11) default NULL,
  `UpdatedBy` int(11) default NULL,
  PRIMARY KEY  (`QrReqAttachId`),
  KEY `FK6577BDE447BC20AD` (`UpdatedBy`),
  KEY `FK6577BDE46D2BC58C` (`QrRequirementId`),
  KEY `FK6577BDE4DF94E79A` (`CreatedBy`),
  CONSTRAINT `FK6577BDE447BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `FK6577BDE46D2BC58C` FOREIGN KEY (`QrRequirementId`) REFERENCES `qr_requirement` (`QrRequirementId`),
  CONSTRAINT `FK6577BDE4DF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_req_attach`
--

LOCK TABLES `qr_req_attach` WRITE;
/*!40000 ALTER TABLE `qr_req_attach` DISABLE KEYS */;
/*!40000 ALTER TABLE `qr_req_attach` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_requirement`
--

DROP TABLE IF EXISTS `qr_requirement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_requirement` (
  `QrRequirementId` int(11) NOT NULL auto_increment,
  `QrClientBaseId` int(11) default NULL,
  `ReqDate` date NOT NULL,
  `JobId` varchar(20) default NULL,
  `TempJobId` varchar(20) default NULL,
  `JobTitle` varchar(60) NOT NULL,
  `QrCountryId` int(11) NOT NULL,
  `City` varchar(60) NOT NULL,
  `State` varchar(60) NOT NULL,
  `PostalCode` varchar(20) NOT NULL,
  `ProjectDuration` varchar(20) NOT NULL,
  `NoOfOpening` varchar(20) NOT NULL,
  `Rate` varchar(20) default NULL,
  `RateOption` varchar(20) default NULL,
  `RatePerHr` double default NULL,
  `Critical` varchar(60) default NULL,
  `Experience` varchar(20) default NULL,
  `WorkType` varchar(60) NOT NULL default '',
  `WorkStatus` varchar(60) NOT NULL default '',
  `JobType` varchar(60) NOT NULL default '',
  `EmploymentType` varchar(60) NOT NULL default '',
  `SubmittedBy` varchar(120) NOT NULL default '',
  `Email` varchar(120) NOT NULL default '',
  `ContactNo` varchar(60) NOT NULL default '',
  `SkillSet` text,
  `OptionalSkills` text,
  `AdditionalNotes` text,
  `ProjectDesc` text,
  `sglassdoor` tinyint(1) default '0',
  `CreatedDt` datetime NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `UpdatedDt` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `Isactive` tinyint(1) NOT NULL default '1',
  `Priority` varchar(60) default NULL,
  `Status` varchar(5) NOT NULL,
  PRIMARY KEY  (`QrRequirementId`),
  KEY `CreatedBy` (`CreatedBy`),
  KEY `UpdatedBy` (`UpdatedBy`),
  KEY `qr_requirement_ibfk_3` (`QrClientBaseId`),
  KEY `FKD9D41F2544E72C89` (`QrClientBaseId`),
  KEY `FKD9D41F2547BC20AD` (`UpdatedBy`),
  KEY `FKD9D41F25DF94E79A` (`CreatedBy`),
  KEY `FKD9D41F25BC4D3B2` (`QrCountryId`),
  CONSTRAINT `FKD9D41F2544E72C89` FOREIGN KEY (`QrClientBaseId`) REFERENCES `qr_client_base_info` (`QrClientBaseId`),
  CONSTRAINT `FKD9D41F2547BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `FKD9D41F25BC4D3B2` FOREIGN KEY (`QrCountryId`) REFERENCES `qr_country` (`QrCountryId`),
  CONSTRAINT `FKD9D41F25DF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_requirement_ibfk_1` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_requirement_ibfk_2` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_requirement_ibfk_3` FOREIGN KEY (`QrClientBaseId`) REFERENCES `qr_client_base_info` (`QrClientBaseId`),
  CONSTRAINT `qr_requirement_ibfk_4` FOREIGN KEY (`QrCountryId`) REFERENCES `qr_country` (`QrCountryId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_requirement`
--

LOCK TABLES `qr_requirement` WRITE;
/*!40000 ALTER TABLE `qr_requirement` DISABLE KEYS */;
/*!40000 ALTER TABLE `qr_requirement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_resume_parser`
--

DROP TABLE IF EXISTS `qr_resume_parser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_resume_parser` (
  `QrResumeParserId` int(11) NOT NULL auto_increment,
  `QrImportResumesId` int(11) default NULL,
  `FirstName` varchar(150) default NULL,
  `Middlename` varchar(150) default NULL,
  `LastName` varchar(150) default NULL,
  `Email` varchar(100) default NULL,
  `Phone` varchar(150) default NULL,
  `Address` varchar(150) default NULL,
  `City` varchar(60) default NULL,
  `State` varchar(60) default NULL,
  `ZipCode` varchar(20) default NULL,
  `Category` varchar(60) default NULL,
  `DateOfBirth` datetime default NULL,
  `CurrentEmployer` text,
  `JobProfile` text,
  `WorkedPeriod` varchar(255) default NULL,
  `Gender` varchar(60) default NULL,
  `FatherName` varchar(60) default NULL,
  `MaritalStatus` varchar(60) default NULL,
  `PassportNo` varchar(60) default NULL,
  `Nationality` varchar(255) default NULL,
  `CurrentSalary` mediumtext,
  `ExpectedSalary` mediumtext,
  `Qualification` mediumtext,
  `Skills` mediumtext,
  `Experience` mediumtext,
  `DetailResume` longtext,
  `CreatedDt` datetime default NULL,
  `CreatedBy` int(11) default NULL,
  `UpdatedDt` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) default NULL,
  `Isactive` tinyint(1) default '1',
  `IsCopied` tinyint(1) default '0',
  PRIMARY KEY  (`QrResumeParserId`),
  KEY `QrImportResumesId` (`QrImportResumesId`),
  KEY `CreatedBy` (`CreatedBy`),
  KEY `UpdatedBy` (`UpdatedBy`),
  KEY `FKB13766D3E4DB80D3` (`QrImportResumesId`),
  KEY `FKB13766D347BC20AD` (`UpdatedBy`),
  KEY `FKB13766D3DF94E79A` (`CreatedBy`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_resume_parser`
--

LOCK TABLES `qr_resume_parser` WRITE;
/*!40000 ALTER TABLE `qr_resume_parser` DISABLE KEYS */;
/*!40000 ALTER TABLE `qr_resume_parser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_role`
--

DROP TABLE IF EXISTS `qr_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_role` (
  `QrRoleId` int(11) NOT NULL auto_increment,
  `RoleName` varchar(45) NOT NULL,
  `XmlPath` varchar(500) NOT NULL,
  `CreatedDt` datetime NOT NULL,
  `CreatedBy` int(11) default NULL,
  `UpdatedDt` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) default NULL,
  `Isactive` tinyint(1) NOT NULL default '1',
  PRIMARY KEY  (`QrRoleId`),
  KEY `FK2198D7D447BC20AD` (`UpdatedBy`),
  KEY `FK2198D7D4DF94E79A` (`CreatedBy`),
  CONSTRAINT `FK2198D7D447BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `FK2198D7D4DF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_role`
--

LOCK TABLES `qr_role` WRITE;
/*!40000 ALTER TABLE `qr_role` DISABLE KEYS */;
INSERT INTO `qr_role` VALUES (1,'default','Default.xml','2011-01-20 12:48:57',1,'2011-01-20 12:19:00',1,0),(2,'Admin','Admin.xml','2011-01-20 12:49:02',1,'2011-12-13 23:30:00',1,1);
/*!40000 ALTER TABLE `qr_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_saas_contract`
--

DROP TABLE IF EXISTS `qr_saas_contract`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_saas_contract` (
  `QrSaasContractId` int(11) NOT NULL auto_increment,
  `SaasName` varchar(255) default NULL,
  `SaasType` varchar(255) default NULL,
  `SaasSize` int(11) default '0',
  `CompanyName` varchar(255) default NULL,
  `PersonName` varchar(255) default NULL,
  `Designation` varchar(255) default NULL,
  `SaasSignDate` datetime default NULL,
  `CreatedDt` datetime default NULL,
  `CreatedBy` int(11) default NULL,
  `UpdatedDt` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) default NULL,
  `Isactive` tinyint(1) default '1',
  PRIMARY KEY  (`QrSaasContractId`),
  KEY `CreatedBy` (`CreatedBy`),
  KEY `UpdatedBy` (`UpdatedBy`),
  KEY `FK80D175D347BC20AD` (`UpdatedBy`),
  KEY `FK80D175D3DF94E79A` (`CreatedBy`),
  CONSTRAINT `FK80D175D347BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `FK80D175D3DF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_saas_contract_ibfk_1` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_saas_contract_ibfk_2` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_saas_contract`
--

LOCK TABLES `qr_saas_contract` WRITE;
/*!40000 ALTER TABLE `qr_saas_contract` DISABLE KEYS */;
/*!40000 ALTER TABLE `qr_saas_contract` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_signature`
--

DROP TABLE IF EXISTS `qr_signature`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_signature` (
  `QrSignatureId` int(11) NOT NULL auto_increment,
  `QrHrUserId` int(11) default NULL,
  `Signature` text,
  `CreatedDt` datetime default NULL,
  `CreatedBy` int(11) default NULL,
  `UpdatedDt` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) default NULL,
  `Isactive` tinyint(1) default '1',
  PRIMARY KEY  (`QrSignatureId`),
  KEY `UpdatedBy` (`UpdatedBy`),
  KEY `CreatedBy` (`CreatedBy`),
  KEY `QrHrUserId` (`QrHrUserId`),
  KEY `FK9A802C1A26FAA32C` (`QrHrUserId`),
  KEY `FK9A802C1A47BC20AD` (`UpdatedBy`),
  KEY `FK9A802C1ADF94E79A` (`CreatedBy`),
  CONSTRAINT `FK9A802C1A26FAA32C` FOREIGN KEY (`QrHrUserId`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `FK9A802C1A47BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `FK9A802C1ADF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_signature_info_ibfk_1` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_signature_info_ibfk_2` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_signature_info_ibfk_3` FOREIGN KEY (`QrHrUserId`) REFERENCES `qr_hr_user_info` (`QrHrUserId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_signature`
--

LOCK TABLES `qr_signature` WRITE;
/*!40000 ALTER TABLE `qr_signature` DISABLE KEYS */;
/*!40000 ALTER TABLE `qr_signature` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_skillset_info`
--

DROP TABLE IF EXISTS `qr_skillset_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_skillset_info` (
  `QrSkillSetId` int(11) NOT NULL auto_increment,
  `SkillSetName` varchar(100) NOT NULL,
  `Isactive` tinyint(1) default '1',
  PRIMARY KEY  (`QrSkillSetId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_skillset_info`
--

LOCK TABLES `qr_skillset_info` WRITE;
/*!40000 ALTER TABLE `qr_skillset_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `qr_skillset_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_social_configuration`
--

DROP TABLE IF EXISTS `qr_social_configuration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_social_configuration` (
  `QrSocialId` int(11) NOT NULL auto_increment,
  `QrHrUserId` int(11) NOT NULL,
  `SocialType` varchar(25) default NULL,
  `SocialUrl` varchar(500) default NULL,
  `SocialPageId` varchar(250) default NULL,
  `CreatedDt` date default NULL,
  `CreatedBy` int(11) default NULL,
  `UpdatedDt` timestamp NOT NULL default '0000-00-00 00:00:00' on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) default NULL,
  `IsActive` tinyint(1) default NULL,
  PRIMARY KEY  (`QrSocialId`),
  KEY `FK4E36370226FAA32C` (`QrHrUserId`),
  KEY `FK4E36370247BC20AD` (`UpdatedBy`),
  KEY `FK4E363702DF94E79A` (`CreatedBy`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_social_configuration`
--

LOCK TABLES `qr_social_configuration` WRITE;
/*!40000 ALTER TABLE `qr_social_configuration` DISABLE KEYS */;
/*!40000 ALTER TABLE `qr_social_configuration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_state`
--

DROP TABLE IF EXISTS `qr_state`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_state` (
  `QrStateId` int(11) NOT NULL auto_increment,
  `Name` varchar(100) NOT NULL,
  `QrCountryId` int(11) NOT NULL,
  PRIMARY KEY  (`QrStateId`),
  KEY `QrCountryId` (`QrCountryId`),
  KEY `FK119258F3BC4D3B2` (`QrCountryId`),
  CONSTRAINT `FK119258F3BC4D3B2` FOREIGN KEY (`QrCountryId`) REFERENCES `qr_country` (`QrCountryId`),
  CONSTRAINT `qr_state_ibfk_1` FOREIGN KEY (`QrCountryId`) REFERENCES `qr_country` (`QrCountryId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_state`
--

LOCK TABLES `qr_state` WRITE;
/*!40000 ALTER TABLE `qr_state` DISABLE KEYS */;
/*!40000 ALTER TABLE `qr_state` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_submission_docs`
--

DROP TABLE IF EXISTS `qr_submission_docs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_submission_docs` (
  `QrSubmissionDocsId` int(11) NOT NULL auto_increment,
  `QrRequirementId` int(11) default NULL,
  `QrHrUserId` int(11) default NULL,
  `QrClientBaseId` int(11) default NULL,
  `QrApplicantsBaseId` int(11) default NULL,
  `QrClientSubmissionId` int(11) default NULL,
  `SubAttachName` varchar(255) NOT NULL,
  `SubAttachType` varchar(255) default NULL,
  `SubAttachSize` int(11) NOT NULL,
  `CreatedDt` datetime NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `UpdatedDt` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  PRIMARY KEY  (`QrSubmissionDocsId`),
  KEY `FK47BBD13026FAA32C` (`QrHrUserId`),
  KEY `FK47BBD13044E72C89` (`QrClientBaseId`),
  KEY `FK47BBD13047BC20AD` (`UpdatedBy`),
  KEY `FK47BBD1306D2BC58C` (`QrRequirementId`),
  KEY `FK47BBD130DF94E79A` (`CreatedBy`),
  KEY `FK47BBD1309816EB55` (`QrApplicantsBaseId`),
  KEY `FK47BBD13099A533B0` (`QrClientSubmissionId`),
  CONSTRAINT `FK47BBD13026FAA32C` FOREIGN KEY (`QrHrUserId`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `FK47BBD13044E72C89` FOREIGN KEY (`QrClientBaseId`) REFERENCES `qr_client_base_info` (`QrClientBaseId`),
  CONSTRAINT `FK47BBD13047BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `FK47BBD1306D2BC58C` FOREIGN KEY (`QrRequirementId`) REFERENCES `qr_requirement` (`QrRequirementId`),
  CONSTRAINT `FK47BBD1309816EB55` FOREIGN KEY (`QrApplicantsBaseId`) REFERENCES `qr_applicants_base_info` (`QrApplicantsBaseId`),
  CONSTRAINT `FK47BBD13099A533B0` FOREIGN KEY (`QrClientSubmissionId`) REFERENCES `qr_client_submission` (`QrClientSubmissionId`),
  CONSTRAINT `FK47BBD130DF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_submission_docs_ibfk_1` FOREIGN KEY (`QrApplicantsBaseId`) REFERENCES `qr_applicants_base_info` (`QrApplicantsBaseId`),
  CONSTRAINT `qr_submission_docs_ibfk_2` FOREIGN KEY (`QrRequirementId`) REFERENCES `qr_requirement` (`QrRequirementId`),
  CONSTRAINT `qr_submission_docs_ibfk_3` FOREIGN KEY (`QrClientBaseId`) REFERENCES `qr_client_base_info` (`QrClientBaseId`),
  CONSTRAINT `qr_submission_docs_ibfk_4` FOREIGN KEY (`QrHrUserId`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_submission_docs_ibfk_5` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_submission_docs_ibfk_6` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_submission_docs_ibfk_8` FOREIGN KEY (`QrClientSubmissionId`) REFERENCES `qr_client_submission` (`QrClientSubmissionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_submission_docs`
--

LOCK TABLES `qr_submission_docs` WRITE;
/*!40000 ALTER TABLE `qr_submission_docs` DISABLE KEYS */;
/*!40000 ALTER TABLE `qr_submission_docs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_support`
--

DROP TABLE IF EXISTS `qr_support`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_support` (
  `QrSupportId` int(11) NOT NULL auto_increment,
  `FullName` varchar(150) NOT NULL,
  `EmailId` varchar(150) NOT NULL,
  `Priority` varchar(100) NOT NULL,
  `Subject` varchar(255) NOT NULL,
  `Message` text,
  `CreatedDt` datetime NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `UpdatedDt` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `Isactive` tinyint(1) NOT NULL default '1',
  `phoneNo` varchar(20) default NULL,
  PRIMARY KEY  (`QrSupportId`),
  KEY `UpdatedBy` (`UpdatedBy`),
  KEY `CreatedBy` (`CreatedBy`),
  KEY `FKF8E683B147BC20AD` (`UpdatedBy`),
  KEY `FKF8E683B1DF94E79A` (`CreatedBy`),
  CONSTRAINT `FKF8E683B147BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `FKF8E683B1DF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_support_ibfk_1` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_support_ibfk_2` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_support`
--

LOCK TABLES `qr_support` WRITE;
/*!40000 ALTER TABLE `qr_support` DISABLE KEYS */;
/*!40000 ALTER TABLE `qr_support` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_support_docs`
--

DROP TABLE IF EXISTS `qr_support_docs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_support_docs` (
  `QrSupportDocsId` int(11) NOT NULL auto_increment,
  `QrSupportId` int(11) NOT NULL,
  `SupAttachName` varchar(255) NOT NULL,
  `SupAttachType` varchar(255) default NULL,
  `SupAttachSize` int(11) default NULL,
  `CreatedDt` datetime NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `UpdatedDt` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  PRIMARY KEY  (`QrSupportDocsId`),
  KEY `QrSupportId` (`QrSupportId`),
  KEY `UpdatedBy` (`UpdatedBy`),
  KEY `CreatedBy` (`CreatedBy`),
  KEY `FK6526FE096828CFA4` (`QrSupportId`),
  KEY `FK6526FE0947BC20AD` (`UpdatedBy`),
  KEY `FK6526FE09DF94E79A` (`CreatedBy`),
  CONSTRAINT `FK6526FE0947BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `FK6526FE096828CFA4` FOREIGN KEY (`QrSupportId`) REFERENCES `qr_support` (`QrSupportId`),
  CONSTRAINT `FK6526FE09DF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_support_docs_1` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_support_docs_2` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_support_docs_3` FOREIGN KEY (`QrSupportId`) REFERENCES `qr_support` (`QrSupportId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_support_docs`
--

LOCK TABLES `qr_support_docs` WRITE;
/*!40000 ALTER TABLE `qr_support_docs` DISABLE KEYS */;
/*!40000 ALTER TABLE `qr_support_docs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_vendor_base_info`
--

DROP TABLE IF EXISTS `qr_vendor_base_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_vendor_base_info` (
  `QrVendorBaseId` int(11) NOT NULL auto_increment,
  `vendor_id` int(11) default NULL,
  `VendorName` varchar(150) NOT NULL,
  `VendorRegNo` varchar(60) default NULL,
  `VendorShName` varchar(150) default NULL,
  `Address1` varchar(150) default NULL,
  `Address2` varchar(150) default NULL,
  `QrCountryId` int(11) NOT NULL,
  `City` varchar(60) default NULL,
  `State` varchar(60) default NULL,
  `PostalCode` varchar(20) default NULL,
  `Phone` varchar(20) default NULL,
  `Fax` varchar(20) default NULL,
  `Website` varchar(100) default NULL,
  `VendorSkillSet` text,
  `isexternal` tinyint(1) default '0',
  `vendor_contract` tinyint(1) default '0',
  `CreatedDt` datetime NOT NULL,
  `CreatedBy` int(11) default NULL,
  `UpdatedDt` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) default NULL,
  `Isactive` tinyint(1) NOT NULL default '1',
  PRIMARY KEY  (`QrVendorBaseId`),
  KEY `UpdatedBy` (`UpdatedBy`),
  KEY `CreatedBy` (`CreatedBy`),
  KEY `FKC453918347BC20AD` (`UpdatedBy`),
  KEY `FKC4539183DF94E79A` (`CreatedBy`),
  KEY `FKC4539183BC4D3B2` (`QrCountryId`),
  CONSTRAINT `FKC453918347BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `FKC4539183BC4D3B2` FOREIGN KEY (`QrCountryId`) REFERENCES `qr_country` (`QrCountryId`),
  CONSTRAINT `FKC4539183DF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_vendor_base_info_ibfk_1` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_vendor_base_info_ibfk_2` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_vendor_base_info_ibfk_3` FOREIGN KEY (`QrCountryId`) REFERENCES `qr_country` (`QrCountryId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_vendor_base_info`
--

LOCK TABLES `qr_vendor_base_info` WRITE;
/*!40000 ALTER TABLE `qr_vendor_base_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `qr_vendor_base_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_vendor_broadcast`
--

DROP TABLE IF EXISTS `qr_vendor_broadcast`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_vendor_broadcast` (
  `QrVendorBroadcastId` int(11) NOT NULL auto_increment,
  `QrRequirementId` int(11) NOT NULL,
  `QrVendorBaseId` int(11) NOT NULL,
  `CreatedDt` datetime NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `UpdatedDt` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL default '1',
  PRIMARY KEY  (`QrVendorBroadcastId`),
  KEY `QrRequirementId` (`QrRequirementId`),
  KEY `QrVendorBaseId` (`QrVendorBaseId`),
  KEY `CreatedBy` (`CreatedBy`),
  KEY `UpdatedBy` (`UpdatedBy`),
  KEY `FKD01EC8E847BC20AD` (`UpdatedBy`),
  KEY `FKD01EC8E86D2BC58C` (`QrRequirementId`),
  KEY `FKD01EC8E8DF94E79A` (`CreatedBy`),
  KEY `FKD01EC8E83FFA5283` (`QrVendorBaseId`),
  CONSTRAINT `FKD01EC8E83FFA5283` FOREIGN KEY (`QrVendorBaseId`) REFERENCES `qr_vendor_base_info` (`QrVendorBaseId`),
  CONSTRAINT `FKD01EC8E847BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `FKD01EC8E86D2BC58C` FOREIGN KEY (`QrRequirementId`) REFERENCES `qr_requirement` (`QrRequirementId`),
  CONSTRAINT `FKD01EC8E8DF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_vendor_broadcast_ibfk_1` FOREIGN KEY (`QrRequirementId`) REFERENCES `qr_requirement` (`QrRequirementId`),
  CONSTRAINT `qr_vendor_broadcast_ibfk_3` FOREIGN KEY (`QrVendorBaseId`) REFERENCES `qr_vendor_base_info` (`QrVendorBaseId`),
  CONSTRAINT `qr_vendor_broadcast_ibfk_4` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_vendor_broadcast_ibfk_5` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_vendor_broadcast`
--

LOCK TABLES `qr_vendor_broadcast` WRITE;
/*!40000 ALTER TABLE `qr_vendor_broadcast` DISABLE KEYS */;
/*!40000 ALTER TABLE `qr_vendor_broadcast` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_vendor_contact_info`
--

DROP TABLE IF EXISTS `qr_vendor_contact_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_vendor_contact_info` (
  `QrVendorContactId` int(11) NOT NULL auto_increment,
  `QrVendorBaseId` int(11) NOT NULL,
  `Salutation` varchar(20) default NULL,
  `VendorFName` varchar(150) NOT NULL,
  `VendorMName` varchar(150) default NULL,
  `VendorLName` varchar(150) NOT NULL,
  `PhoneWork` varchar(20) default NULL,
  `PhoneWorkExt` varchar(10) default NULL,
  `PhoneWorkAdd` varchar(20) default NULL,
  `PhoneMob` varchar(20) default NULL,
  `VendorPriEmailId` varchar(100) NOT NULL,
  `VendorSecEmail` varchar(100) default NULL,
  `IsExternal` tinyint(1) default '0',
  `CreatedDt` datetime NOT NULL,
  `CreatedBy` int(11) default NULL,
  `UpdatedDt` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) default NULL,
  `Isactive` tinyint(1) NOT NULL default '1',
  PRIMARY KEY  (`QrVendorContactId`),
  KEY `CreatedBy` (`CreatedBy`),
  KEY `UpdatedBy` (`UpdatedBy`),
  KEY `QrVendorBaseId` (`QrVendorBaseId`),
  KEY `FK6BEF67E647BC20AD` (`UpdatedBy`),
  KEY `FK6BEF67E6DF94E79A` (`CreatedBy`),
  KEY `FK6BEF67E63FFA5283` (`QrVendorBaseId`),
  CONSTRAINT `FK6BEF67E63FFA5283` FOREIGN KEY (`QrVendorBaseId`) REFERENCES `qr_vendor_base_info` (`QrVendorBaseId`),
  CONSTRAINT `FK6BEF67E647BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `FK6BEF67E6DF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_vendor_contact_info_ibfk_1` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_vendor_contact_info_ibfk_2` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_vendor_contact_info_ibfk_3` FOREIGN KEY (`QrVendorBaseId`) REFERENCES `qr_vendor_base_info` (`QrVendorBaseId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_vendor_contact_info`
--

LOCK TABLES `qr_vendor_contact_info` WRITE;
/*!40000 ALTER TABLE `qr_vendor_contact_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `qr_vendor_contact_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_vendor_notes`
--

DROP TABLE IF EXISTS `qr_vendor_notes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_vendor_notes` (
  `QrVendorNotesId` int(11) NOT NULL auto_increment,
  `QrVendorBaseId` int(11) NOT NULL,
  `QrHrUserId` int(11) NOT NULL,
  `VendorComments` varchar(500) NOT NULL,
  `CreatedDt` datetime NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `UpdatedDt` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `Isactive` tinyint(4) NOT NULL,
  PRIMARY KEY  (`QrVendorNotesId`),
  KEY `qr_vendor_notes_ibfk1` (`QrVendorBaseId`),
  KEY `qr_vendor_notes_ibfk2` (`QrHrUserId`),
  KEY `qr_vendor_notes_ibfk3` (`CreatedBy`),
  KEY `qr_vendor_notes_ibfk4` (`UpdatedBy`),
  KEY `FK2F2B5A6826FAA32C` (`QrHrUserId`),
  KEY `FK2F2B5A6847BC20AD` (`UpdatedBy`),
  KEY `FK2F2B5A68DF94E79A` (`CreatedBy`),
  KEY `FK2F2B5A683FFA5283` (`QrVendorBaseId`),
  CONSTRAINT `FK2F2B5A6826FAA32C` FOREIGN KEY (`QrHrUserId`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `FK2F2B5A683FFA5283` FOREIGN KEY (`QrVendorBaseId`) REFERENCES `qr_vendor_base_info` (`QrVendorBaseId`),
  CONSTRAINT `FK2F2B5A6847BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `FK2F2B5A68DF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_vendor_notes_ibfk1` FOREIGN KEY (`QrVendorBaseId`) REFERENCES `qr_vendor_base_info` (`QrVendorBaseId`),
  CONSTRAINT `qr_vendor_notes_ibfk2` FOREIGN KEY (`QrHrUserId`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_vendor_notes_ibfk3` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  CONSTRAINT `qr_vendor_notes_ibfk4` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_vendor_notes`
--

LOCK TABLES `qr_vendor_notes` WRITE;
/*!40000 ALTER TABLE `qr_vendor_notes` DISABLE KEYS */;
/*!40000 ALTER TABLE `qr_vendor_notes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_zipcode`
--

DROP TABLE IF EXISTS `qr_zipcode`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qr_zipcode` (
  `QrZipId` int(11) NOT NULL auto_increment,
  `Name` varchar(10) NOT NULL,
  `QrCityId` int(11) default NULL,
  PRIMARY KEY  (`QrZipId`),
  KEY `QrCityId` (`QrCityId`),
  KEY `FK56B21070545532B8` (`QrCityId`),
  CONSTRAINT `FK56B21070545532B8` FOREIGN KEY (`QrCityId`) REFERENCES `qr_city` (`QrCityId`),
  CONSTRAINT `qr_zipcode_ibfk_1` FOREIGN KEY (`QrCityId`) REFERENCES `qr_city` (`QrCityId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_zipcode`
--

LOCK TABLES `qr_zipcode` WRITE;
/*!40000 ALTER TABLE `qr_zipcode` DISABLE KEYS */;
/*!40000 ALTER TABLE `qr_zipcode` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-06-23 12:56:29
