-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jun 28, 2016 at 02:10 PM
-- Server version: 10.1.13-MariaDB
-- PHP Version: 7.0.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hrms`
--

-- --------------------------------------------------------

--
-- Table structure for table `client_history`
--

CREATE TABLE `client_history` (
  `client_history_id` int(11) NOT NULL,
  `client_id` int(11) NOT NULL,
  `client_sub_domain_name` varchar(120) NOT NULL,
  `status` varchar(20) NOT NULL,
  `status_date` datetime DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `client_information`
--

CREATE TABLE `client_information` (
  `Client_id` int(11) NOT NULL,
  `Client_name` varchar(120) NOT NULL,
  `Client_sub_domain_name` varchar(120) NOT NULL,
  `Client_db_path` varchar(120) NOT NULL,
  `Client_db_user_name` varchar(120) NOT NULL,
  `Client_db_password` varchar(120) NOT NULL,
  `User_type` varchar(120) NOT NULL,
  `IsActive` smallint(1) NOT NULL,
  `Created` datetime NOT NULL,
  `Last_login` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `Allocated_Space` varchar(20) DEFAULT NULL,
  `Utilized_Space` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `client_information`
--

INSERT INTO `client_information` (`Client_id`, `Client_name`, `Client_sub_domain_name`, `Client_db_path`, `Client_db_user_name`, `Client_db_password`, `User_type`, `IsActive`, `Created`, `Last_login`, `Allocated_Space`, `Utilized_Space`) VALUES
(1, 'hrms', 'hrms', 'rlite', 'rlite', '12345', 'free_user', 1, '2012-04-12 12:01:54', '2016-05-28 12:46:28', '20', '0.00');

-- --------------------------------------------------------

--
-- Table structure for table `client_info_bkup`
--

CREATE TABLE `client_info_bkup` (
  `fid` int(11) NOT NULL DEFAULT '0',
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
  `status` enum('open','closed') NOT NULL DEFAULT 'open',
  `problem` longtext NOT NULL,
  `addedon` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00'
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `client_status`
--

CREATE TABLE `client_status` (
  `client_status_id` int(11) NOT NULL,
  `client_id` int(11) NOT NULL,
  `client_name` varchar(120) NOT NULL,
  `client_sub_domain_name` varchar(120) NOT NULL,
  `client_email` varchar(120) NOT NULL,
  `client_status` varchar(50) DEFAULT NULL,
  `last_status_change` datetime DEFAULT NULL,
  `signup_fid` int(11) DEFAULT NULL,
  `client_table` varchar(1) DEFAULT NULL,
  `subscription_start` datetime DEFAULT NULL,
  `subscription_end` datetime DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_answer`
--

CREATE TABLE `hcmo_answer` (
  `HcmoAnswerId` int(11) NOT NULL,
  `HcmoQuestionGeneralInfoGroupId` int(11) DEFAULT NULL,
  `HcmoQuestionGroupNameId` int(11) DEFAULT NULL,
  `Answer` varchar(100) DEFAULT NULL,
  `Status` varchar(100) DEFAULT NULL,
  `AppraiserEmployeeId` int(11) DEFAULT NULL,
  `AppraisingEmployeeId` int(11) DEFAULT NULL,
  `Created` date DEFAULT NULL,
  `CreatedBy` int(11) DEFAULT NULL,
  `Updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) DEFAULT NULL,
  `IsActive` tinyint(1) DEFAULT '1',
  `hcmoclientid` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_assets`
--

CREATE TABLE `hcmo_assets` (
  `AssetTypeId` int(11) NOT NULL,
  `AssetTypeName` varchar(100) DEFAULT NULL,
  `created` date DEFAULT NULL,
  `CreatedBy` int(11) DEFAULT NULL,
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) DEFAULT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1',
  `hcmoclientid` int(11) DEFAULT NULL,
  `HcmoEmployeeId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `hcmo_assets`
--

INSERT INTO `hcmo_assets` (`AssetTypeId`, `AssetTypeName`, `created`, `CreatedBy`, `updated`, `UpdatedBy`, `IsActive`, `hcmoclientid`, `HcmoEmployeeId`) VALUES
(1, 'Software', '2016-06-27', 1, '2016-06-26 18:30:00', NULL, 1, 1, 1),
(2, 'Hardware', '2016-06-27', 1, '2016-06-26 18:30:00', 1, 1, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_benefits`
--

CREATE TABLE `hcmo_benefits` (
  `HcmoBenefitsId` int(11) NOT NULL,
  `Year` int(11) DEFAULT NULL,
  `BenefitsName` varchar(50) DEFAULT NULL,
  `BenefitsAttachFileName` varchar(100) DEFAULT NULL,
  `EmployeeId` varchar(255) DEFAULT NULL,
  `EmployeeEmailId` text,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1',
  `hcmoclientid` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_benefitsplan`
--

CREATE TABLE `hcmo_benefitsplan` (
  `HcmoBenefitsPlanId` int(11) NOT NULL,
  `hcmoclientid` int(11) DEFAULT NULL,
  `EmployeeContribution` double DEFAULT NULL,
  `EmployeerContribution` double DEFAULT NULL,
  `PreTax` double DEFAULT NULL,
  `UpperLimit` double DEFAULT NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_benefitstype`
--

CREATE TABLE `hcmo_benefitstype` (
  `HcmoBenefitsTypeId` int(11) NOT NULL,
  `hcmoclientid` int(11) DEFAULT NULL,
  `BenefitsType` varchar(20) DEFAULT NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_billing_info`
--

CREATE TABLE `hcmo_billing_info` (
  `hcmobillingid` int(11) NOT NULL,
  `contact_id` int(11) DEFAULT NULL,
  `address_line1` varchar(500) DEFAULT NULL,
  `address_line2` varchar(500) DEFAULT NULL,
  `city` varchar(150) DEFAULT NULL,
  `state` varchar(150) DEFAULT NULL,
  `country_id` int(11) DEFAULT NULL,
  `phone_no` varchar(50) DEFAULT NULL,
  `paymnt_type` int(11) DEFAULT NULL COMMENT '1-Paypal,2-Google,3-card',
  `entry_datetime` datetime DEFAULT NULL,
  `mody_datetime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_category`
--

CREATE TABLE `hcmo_category` (
  `HcmoCategoryId` int(11) NOT NULL,
  `hcmoclientid` int(11) DEFAULT NULL,
  `CategoryName` varchar(100) DEFAULT NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_client`
--

CREATE TABLE `hcmo_client` (
  `HcmoClientId` int(11) NOT NULL,
  `HcmoCountryId` int(11) DEFAULT NULL,
  `CompanyName` varchar(60) DEFAULT NULL,
  `NoOfEmployee` int(11) DEFAULT NULL,
  `NoOfBranch` int(11) DEFAULT NULL,
  `LogoName` varchar(100) DEFAULT NULL,
  `Address1` varchar(60) DEFAULT NULL,
  `Address2` varchar(60) DEFAULT NULL,
  `ZipCode` varchar(20) DEFAULT NULL,
  `Region` varchar(60) DEFAULT NULL,
  `City` varchar(100) DEFAULT NULL,
  `Phone` varchar(20) DEFAULT NULL,
  `TaxId` varchar(30) DEFAULT NULL,
  `Fax` varchar(20) DEFAULT NULL,
  `Comments` text,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_clientreg`
--

CREATE TABLE `hcmo_clientreg` (
  `hcmoClientregid` int(11) NOT NULL,
  `hcmoclientid` int(11) DEFAULT NULL,
  `First_name` varchar(50) DEFAULT NULL,
  `Last_name` varchar(50) DEFAULT NULL,
  `Email_id` varchar(100) DEFAULT NULL,
  `Password` varchar(40) DEFAULT NULL,
  `Compny_name` varchar(50) DEFAULT NULL,
  `Phone_no` varchar(15) DEFAULT NULL,
  `Address_line1` varchar(500) DEFAULT NULL,
  `Address_line2` varchar(500) DEFAULT NULL,
  `City` varchar(70) DEFAULT NULL,
  `State_id` int(11) DEFAULT NULL,
  `Zipcode` varchar(10) DEFAULT NULL,
  `Country_id` int(11) DEFAULT NULL,
  `Entry_datetime` datetime DEFAULT NULL,
  `status` int(2) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_client_reg`
--

CREATE TABLE `hcmo_client_reg` (
  `hcmoclientregid` int(11) NOT NULL,
  `hcmoclientid` int(11) DEFAULT NULL,
  `contact_firstname` varchar(100) DEFAULT NULL,
  `contact_lastname` varchar(100) DEFAULT NULL,
  `contact_mail` varchar(50) DEFAULT NULL,
  `companyName` varchar(50) DEFAULT NULL,
  `contact_address` varchar(100) DEFAULT NULL,
  `contact_addressoptional` varchar(100) DEFAULT NULL,
  `contactPhoneno` varchar(50) DEFAULT NULL,
  `adminuserid` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `Entry_datetime` date DEFAULT NULL,
  `UpdatedDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` int(2) DEFAULT NULL,
  `activationKey` varchar(255) DEFAULT NULL,
  `moduleId` int(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `hcmo_client_reg`
--

INSERT INTO `hcmo_client_reg` (`hcmoclientregid`, `hcmoclientid`, `contact_firstname`, `contact_lastname`, `contact_mail`, `companyName`, `contact_address`, `contact_addressoptional`, `contactPhoneno`, `adminuserid`, `password`, `Entry_datetime`, `UpdatedDate`, `status`, `activationKey`, `moduleId`) VALUES
(1, NULL, 'Client1', 'l', 'client@abc.com', 'client1 company', 'client1 address', '', '99999999', 'client@abc.com', '12345', '2016-05-30', NULL, 0, NULL, NULL),
(2, NULL, 'ajmal', 'saifan', 'aju.saifan@gmail.com', 'gq', NULL, NULL, '9876543210', NULL, 'we1c@me321', '2016-06-03', '2016-06-03 10:18:41', 0, 'blxdfI+pNmU=', 2),
(3, NULL, 'sdfs', 'sdfsd', 'aju.saifan@gmail.com', 'dfsd', NULL, NULL, '213123123', NULL, '233', '2016-06-03', '2016-06-03 10:26:02', 1, 'Nk8FY7PWbsQ=', 0),
(4, NULL, 'Pandi', 'rajan', 'aju.saifan@gmail.com', 'gq', NULL, NULL, '9876543121', NULL, '5652', '2016-06-03', '2016-06-03 10:32:01', 1, 'rtE1lQ/uGuA=', 3),
(5, NULL, 'anu', 'sds', 'aju.saifan@gmail.com', 'dd', NULL, NULL, '32123', NULL, '112', '2016-06-03', '2016-06-03 12:03:33', 0, '8b+t1a2Qm+w=', 1);

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_configuration`
--

CREATE TABLE `hcmo_configuration` (
  `HcmoConfigurationId` int(11) NOT NULL,
  `hcmoclientid` int(11) DEFAULT NULL,
  `MailConfiguration` tinyint(1) DEFAULT '0',
  `Client` tinyint(1) DEFAULT '0',
  `Location` tinyint(1) DEFAULT '0',
  `Region` tinyint(1) DEFAULT '0',
  `SalaryGrade` tinyint(1) DEFAULT '0',
  `JobTitle` tinyint(1) DEFAULT '0',
  `Department` tinyint(1) DEFAULT '0',
  `Team` tinyint(1) DEFAULT '0',
  `Nationality` tinyint(1) DEFAULT '0',
  `EthnicRace` tinyint(1) DEFAULT '0',
  `Employee` tinyint(1) DEFAULT '0',
  `Skip` tinyint(1) DEFAULT '0',
  `Status` varchar(100) DEFAULT NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `hcmo_configuration`
--

INSERT INTO `hcmo_configuration` (`HcmoConfigurationId`, `hcmoclientid`, `MailConfiguration`, `Client`, `Location`, `Region`, `SalaryGrade`, `JobTitle`, `Department`, `Team`, `Nationality`, `EthnicRace`, `Employee`, `Skip`, `Status`, `Created`, `CreatedBy`, `Updated`, `UpdatedBy`, `IsActive`) VALUES
(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 'skippedpermanantely', '2016-05-30', 1, '2016-06-03 06:37:53', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_country`
--

CREATE TABLE `hcmo_country` (
  `HcmoCountryId` int(11) NOT NULL,
  `Name` varchar(100) DEFAULT NULL,
  `Description` text,
  `CountryCode` varchar(60) DEFAULT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1',
  `hcmoclientid` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `hcmo_country`
--

INSERT INTO `hcmo_country` (`HcmoCountryId`, `Name`, `Description`, `CountryCode`, `IsActive`, `hcmoclientid`) VALUES
(29, 'United States of America', '', '1', 1, 1),
(31, 'Egypt (Arab Republic of)', '', '20', 1, 1),
(32, 'Morocco (Kingdom of)', '', '212', 1, 1),
(33, 'Algeria (People''s Democratic Republic of)', '', '213', 1, 1),
(34, 'Tunisia', '', '216', 1, 1),
(35, 'Libya (Socialist People''s Libyan Arab Jamahiriya)', '', '218', 1, 1),
(36, 'Gambia (Republic of the)', '', '220', 1, 1),
(37, 'Senegal (Republic of)', '', '221', 1, 1),
(38, 'Mauritania (Islamic Republic of)', '', '222', 1, 1),
(39, 'Mali (Republic of)', '', '223', 1, 1),
(40, 'Guinea (Republic of)', '', '224', 1, 1),
(41, 'CÃ´te d''Ivoire (Republic of)', '', '225', 1, 1),
(42, 'Burkina Faso', '', '226', 1, 1),
(43, 'Niger (Republic of the)', '', '227', 1, 1),
(44, 'Togolese Republic', '', '228', 1, 1),
(45, 'Benin (Republic of)', '', '229', 1, 1),
(46, 'Mauritius (Republic of)', '', '230', 1, 1),
(47, 'Liberia (Republic of)', 'Liberia (Republic of)', '231', 1, 1),
(48, 'Sierra Leone', 'Sierra Leone', '232', 1, 1),
(49, 'Ghana', '', '233', 1, 1),
(50, 'Nigeria (Federal Republic of)', '', '234', 1, 1),
(51, 'Chad (Republic of)', '', '235', 1, 1),
(52, 'Central African Republic', '', '236', 1, 1),
(53, 'Cameroon (Republic of)', '', '237', 1, 1),
(54, 'Cape Verde (Republic of)', 'Cape Verde (Republic of)', '238', 1, 1),
(55, 'Sao Tome and Principe (Democratic Republic of)', 'Sao Tome and Principe (Democratic Republic of)', '239', 1, 1),
(56, 'Equatorial Guinea (Republic of)', 'Equatorial Guinea (Republic of)', '240', 1, 1),
(57, 'Gabonese Republic', '', '241', 1, 1),
(58, 'Congo (Republic of the)', '', '242', 1, 1),
(59, 'Democratic Republic of the Congo', '', '243', 1, 1),
(60, 'Angola (Republic of)', 'Angola (Republic of)', '244', 1, 1),
(61, 'Guinea-Bissau (Republic of)', '', '245', 1, 1),
(62, 'Diego Garcia', '', '246', 1, 1),
(63, 'Ascension', 'Ascension', '247', 1, 1),
(64, 'Seychelles (Republic of)', 'Seychelles (Republic of)', '248', 1, 1),
(65, 'Sudan (Republic of the)', 'Sudan (Republic of the)', '249', 1, 1),
(66, 'Rwanda (Republic of)', '', '250', 1, 1),
(67, 'Ethiopia (Federal Democratic Republic of)', '', '251', 1, 1),
(68, 'Somali Democratic Republic', '', '252', 1, 1),
(69, 'Djibouti (Republic of)', 'Djibouti (Republic of)', '253', 1, 1),
(70, 'Kenya (Republic of)', '', '254', 1, 1),
(71, 'Tanzania (United Republic of)', 'Tanzania (United Republic of)', '255', 1, 1),
(72, 'Uganda (Republic of)', '', '256', 1, 1),
(73, 'Burundi (Republic of)', '', '257', 1, 1),
(74, 'Mozambique (Republic of)', '', '258', 1, 1),
(75, 'Zambia (Republic of)', 'Zambia (Republic of)', '260', 1, 1),
(76, 'Madagascar (Republic of)', '', '261', 1, 1),
(77, 'Reunion (French Department of)', '', '262', 1, 1),
(78, 'Zimbabwe (Republic of)', 'Zimbabwe (Republic of)', '263', 1, 1),
(79, 'Namibia (Republic of)', 'Namibia (Republic of)', '264', 1, 1),
(80, 'Malawi', 'Malawi', '265', 1, 1),
(81, 'Lesotho (Kingdom of)', 'Lesotho (Kingdom of)', '266', 1, 1),
(82, 'Botswana (Republic of)', '', '267', 1, 1),
(83, 'Swaziland (Kingdom of)', '', '268', 1, 1),
(84, 'Comoros (Union of the)', '', '269', 1, 1),
(86, 'South Africa (Republic of)', '', '27', 1, 1),
(87, 'Saint Helena', 'Saint Helena', '290', 1, 1),
(88, 'Eritrea', '', '291', 1, 1),
(89, 'Aruba', '', '297', 1, 1),
(90, 'Faroe Islands', 'Faroe Islands', '298', 1, 1),
(91, 'Greenland (Denmark)', 'Greenland (Denmark)', '299', 1, 1),
(92, 'Greece', 'Greece', '30', 1, 1),
(93, 'Netherlands (Kingdom of the)', 'Netherlands (Kingdom of the)', '31', 1, 1),
(94, 'Belgium', '', '32', 1, 1),
(95, 'France', '', '33', 1, 1),
(96, 'Spain', '', '34', 1, 1),
(97, 'Gibraltar', 'Gibraltar', '350', 1, 1),
(98, 'Portugal', 'Portugal', '351', 1, 1),
(99, 'Luxembourg', '', '352', 1, 1),
(100, 'Ireland', 'Ireland', '353', 1, 1),
(101, 'Iceland', '', '354', 1, 1),
(102, 'Albania (Republic of)', 'Albania (Republic of)', '355', 1, 1),
(103, 'Malta', 'Malta', '356', 1, 1),
(104, 'Cyprus (Republic of)', '', '357', 1, 1),
(105, 'Finland', 'Finland', '358', 1, 1),
(106, 'Bulgaria (Republic of)', '', '359', 1, 1),
(107, 'Hungary (Republic of)', '', '36', 1, 1),
(108, 'Lithuania (Republic of)', '', '370', 1, 1),
(109, 'Latvia (Republic of)', 'Latvia (Republic of)', '371', 1, 1),
(110, 'Estonia (Republic of)', 'Estonia (Republic of)', '372', 1, 1),
(111, 'Moldova (Republic of)', 'Moldova (Republic of)', '373', 1, 1),
(112, 'Armenia (Republic of)', 'Armenia (Republic of)', '374', 1, 1),
(113, 'Belarus (Republic of)', 'Belarus (Republic of)', '375', 1, 1),
(114, 'Andorra (Principality of)', 'Andorra (Principality of)', '376', 1, 1),
(115, 'Monaco (Principality of)', 'Monaco (Principality of)', '377', 1, 1),
(116, 'San Marino (Republic of)', 'San Marino (Republic of)', '378', 1, 1),
(117, 'Vatican City State', 'Vatican City State', '379', 1, 1),
(118, 'Ukraine', 'Ukraine', '380', 1, 1),
(119, 'Serbia and Montenegro', 'Serbia and Montenegro', '381', 1, 1),
(120, 'Croatia (Republic of)', 'Croatia (Republic of)', '385', 1, 1),
(121, 'Slovenia (Republic of)', '', '386', 1, 1),
(122, 'Bosnia and Herzegovina', '', '387', 1, 1),
(123, 'Group of countries, shared code', 'Group of countries, shared code', '388', 1, 1),
(124, 'The Former Yugoslav Republic of Macedonia', 'The Former Yugoslav Republic of Macedonia', '389', 1, 1),
(125, 'Italy', 'Italy', '39', 1, 1),
(127, 'Romania', 'Romania', '40', 1, 1),
(128, 'Switzerland (Confederation of)', '', '41', 1, 1),
(129, 'Czech Republic', 'Czech Republic', '420', 1, 1),
(130, 'Slovak Republic', 'Slovak Republic', '421', 1, 1),
(131, 'Liechtenstein (Principality of)', 'Liechtenstein (Principality of)', '423', 1, 1),
(132, 'Austria', 'Austria', '43', 1, 1),
(133, 'United Kingdom of Great Britain and Northern Ireland', 'United Kingdom of Great Britain and Northern Ireland', '44', 1, 1),
(134, 'Denmark', 'Denmark', '45', 1, 1),
(135, 'Sweden', 'Sweden', '46', 1, 1),
(136, 'Norway', 'Norway', '47', 1, 1),
(137, 'Poland (Republic of)', 'Poland (Republic of)', '48', 1, 1),
(138, 'Germany (Federal Republic of)', '', '49', 1, 1),
(139, 'Falkland Islands (Malvinas)', 'Falkland Islands (Malvinas)', '500', 1, 1),
(140, 'Belize', 'Belize', '501', 1, 1),
(141, 'Guatemala (Republic of)', '', '502', 1, 1),
(142, 'El Salvador (Republic of)', '', '503', 1, 1),
(143, 'Honduras (Republic of)', '', '504', 1, 1),
(144, 'Nicaragua', 'Nicaragua', '505', 1, 1),
(145, 'Costa Rica', '', '506', 1, 1),
(146, 'Panama (Republic of)', '', '507', 1, 1),
(147, 'Saint Pierre and Miquelon (CollectivitÃ© territoriale de la RÃ©publique franÃ§aise)', 'Saint Pierre and Miquelon (CollectivitÃƒÂ© territoriale de la RÃƒÂ©publique franÃƒÂ§aise)', '508', 1, 1),
(148, 'Haiti (Republic of)', '', '509', 1, 1),
(149, 'Peru', '', '51', 1, 1),
(150, 'Mexico', 'Mexico', '52', 1, 1),
(151, 'Cuba', 'Cuba', '53', 1, 1),
(152, 'Argentine Republic', 'Argentine Republic', '54', 1, 1),
(153, 'Brazil (Federative Republic of)', 'Brazil (Federative Republic of)', '55', 1, 1),
(154, 'Chile', '', '56', 1, 1),
(155, 'Colombia (Republic of)', 'Colombia (Republic of)', '57', 1, 1),
(156, 'Venezuela (Bolivarian Republic of)', 'Venezuela (Bolivarian Republic of)', '58', 1, 1),
(157, 'Guadeloupe (French Department of)', '', '590', 1, 1),
(158, 'Bolivia (Republic of)', '', '591', 1, 1),
(159, 'Guyana', '', '592', 1, 1),
(160, 'Ecuador', '', '593', 1, 1),
(161, 'French Guiana (French Department of)', '', '594', 1, 1),
(162, 'Paraguay (Republic of)', 'Paraguay (Republic of)', '595', 1, 1),
(163, 'Martinique (French Department of)', 'Martinique (French Department of)', '596', 1, 1),
(164, 'Suriname (Republic of)', 'Suriname (Republic of)', '597', 1, 1),
(165, 'Uruguay (Eastern Republic of)', 'Uruguay (Eastern Republic of)', '598', 1, 1),
(166, 'Netherlands Antilles', '', '599', 1, 1),
(167, 'Malaysia', '', '60', 1, 1),
(168, 'Australia', '', '61', 1, 1),
(169, 'Indonesia (Republic of)', '', '62', 1, 1),
(170, 'Philippines (Republic of the)', '', '63', 1, 1),
(171, 'New Zealand', 'New Zealand', '64', 1, 1),
(172, 'Singapore (Republic of)', 'Singapore (Republic of)', '65', 1, 1),
(173, 'Thailand', 'Thailand', '66', 1, 1),
(174, 'Democratic Republic of Timor-Leste', '', '670', 1, 1),
(175, 'Australian External Territories', 'Australian External Territories', '672', 1, 1),
(176, 'Brunei Darussalam', '', '673', 1, 1),
(177, 'Nauru (Republic of)', '', '674', 1, 1),
(178, 'Papua New Guinea', '', '675', 1, 1),
(179, 'Tonga (Kingdom of)', 'Tonga (Kingdom of)', '676', 1, 1),
(180, 'Solomon Islands', 'Solomon Islands', '677', 1, 1),
(181, 'Vanuatu (Republic of)', 'Vanuatu (Republic of)', '678', 1, 1),
(182, 'Fiji (Republic of)', '', '679', 1, 1),
(183, 'Palau (Republic of)', '', '680', 1, 1),
(184, 'Wallis and Futuna (Territoire franÃ§ais d''outre-mer)', '', '681', 1, 1),
(185, 'Cook Islands', '', '682', 1, 1),
(186, 'Niue', 'Niue', '683', 1, 1),
(187, 'Samoa (Independent State of', 'Samoa (Independent State of', '685', 1, 1),
(188, 'Kiribati (Republic of)', '', '686', 1, 1),
(189, 'New Caledonia (Territoire franÃ§ais d''outre-mer)', '', '687', 1, 1),
(190, 'Tuvalu', 'Tuvalu', '688', 1, 1),
(191, 'French Polynesia (Territoire franÃ§ais d''outre-mer)', 'French Polynesia (Territoire franÃƒÂ§ais d''outre-mer)', '689', 1, 1),
(192, 'Tokelau', 'Tokelau', '690', 1, 1),
(193, 'Micronesia (Federated States of)', '', '691', 1, 1),
(194, 'Marshall Islands (Republic of the)', 'Marshall Islands (Republic of the)', '692', 1, 1),
(196, 'Russian Federation', 'Russian Federation', '7', 1, 1),
(199, 'Japan', 'Japan', '81', 1, 1),
(200, 'Korea (Republic of)', 'Korea (Republic of)', '82', 1, 1),
(201, 'Viet Nam (Socialist Republic of)', 'Viet Nam (Socialist Republic of)', '84', 1, 1),
(202, 'Democratic People''s Republic of Korea', 'Democratic People''s Republic of Korea', '850', 1, 1),
(203, 'Hong Kong, China', 'Hong Kong, China', '852', 1, 1),
(204, 'Macao, China', 'Macao, China', '853', 1, 1),
(205, 'Cambodia (Kingdom of)', 'Cambodia (Kingdom of)', '855', 1, 1),
(206, 'Lao People''s Democratic Republic', 'Lao People''s Democratic Republic', '856', 1, 1),
(207, 'China (People''s Republic of)', 'China (People''s Republic of)', '86', 1, 1),
(208, 'Inmarsat SNAC', 'Inmarsat SNAC', '870', 1, 1),
(209, 'Inmarsat (Atlantic Ocean-East)', 'Inmarsat (Atlantic Ocean-East)', '871', 1, 1),
(210, 'Inmarsat (Pacific Ocean)', 'Inmarsat (Pacific Ocean)', '872', 1, 1),
(211, 'Inmarsat (Indian Ocean)', 'Inmarsat (Indian Ocean)', '873', 1, 1),
(212, 'Inmarsat (Atlantic Ocean-West)', 'Inmarsat (Atlantic Ocean-West)', '874', 1, 1),
(218, 'Bangladesh (People''s Republic of)', 'Bangladesh (People''s Republic of)', '880', 1, 1),
(223, 'Turkey', 'Turkey', '90', 1, 1),
(224, 'India (Republic of)', 'India (Republic of)', '91', 1, 1),
(225, 'Pakistan (Islamic Republic of)', 'Pakistan (Islamic Republic of)', '92', 1, 1),
(226, 'Afghanistan', 'Afghanistan', '93', 1, 1),
(227, 'Sri Lanka (Democratic Socialist Republic of)', 'Sri Lanka (Democratic Socialist Republic of)', '94', 1, 1),
(228, 'Myanmar (Union of)', 'Myanmar (Union of)', '95', 1, 1),
(229, 'Maldives (Republic of)', 'Maldives (Republic of)', '960', 1, 1),
(230, 'Lebanon', 'Lebanon', '961', 1, 1),
(231, 'Jordan (Hashemite Kingdom of)', '', '962', 1, 1),
(232, 'Syrian Arab Republic', '', '963', 1, 1),
(233, 'Iraq (Republic of)', '', '964', 1, 1),
(234, 'Kuwait (State of)', '', '965', 1, 1),
(235, 'Saudi Arabia (Kingdom of)', '', '966', 1, 1),
(236, 'Yemen (Republic of)', '', '967', 1, 1),
(237, 'Oman (Sultanate of)', '', '968', 1, 1),
(240, 'United Arab Emirates', '', '971', 1, 1),
(241, 'Israel (State of)', '', '972', 1, 1),
(242, 'Bahrain (Kingdom of)', '', '973', 1, 1),
(243, 'Qatar (State of)', '', '974', 1, 1),
(244, 'Bhutan (Kingdom of)', '', '975', 1, 1),
(245, 'Mongolia', '', '976', 1, 1),
(246, 'Nepal', '', '977', 1, 1),
(248, 'Iran (Islamic Republic of)', 'Iran (Islamic Republic of)', '98', 1, 1),
(249, 'Trial of a proposed new international telecommunication public correspondence service, shared code', '', '991', 1, 1),
(250, 'Tajikistan (Republic of)', 'Tajikistan (Republic of)', '992', 1, 1),
(251, 'Turkmenistan', 'Turkmenistan', '993', 1, 1),
(252, 'Azerbaijani Republic', 'Azerbaijani Republic', '994', 1, 1),
(253, 'Georgia', 'Georgia', '995', 1, 1),
(254, 'Kyrgyz Republic', 'Kyrgyz Republic', '996', 1, 1),
(255, 'Uzbekistan (Republic of)', '', '998', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_currency`
--

CREATE TABLE `hcmo_currency` (
  `HcmoCurrencyId` int(11) NOT NULL,
  `CurrencyType` varchar(20) DEFAULT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1',
  `hcmoclientid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_customer`
--

CREATE TABLE `hcmo_customer` (
  `HcmoCustomerId` int(11) NOT NULL,
  `hcmoclientid` int(11) DEFAULT NULL,
  `Name` varchar(100) DEFAULT NULL,
  `Description` text,
  `PhoneNumber` varchar(20) DEFAULT NULL,
  `Fax` varchar(20) DEFAULT NULL,
  `Address1` varchar(100) DEFAULT NULL,
  `Address2` varchar(100) DEFAULT NULL,
  `ContactName` varchar(60) DEFAULT NULL,
  `Email` varchar(100) DEFAULT NULL,
  `Deleted` tinyint(1) NOT NULL DEFAULT '0',
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `hcmo_customer`
--

INSERT INTO `hcmo_customer` (`HcmoCustomerId`, `hcmoclientid`, `Name`, `Description`, `PhoneNumber`, `Fax`, `Address1`, `Address2`, `ContactName`, `Email`, `Deleted`, `Created`, `CreatedBy`, `Updated`, `UpdatedBy`, `IsActive`) VALUES
(1, 1, 'CustomerOne', 'description of customer one ', '99999999', '444', '4444', 'fool', 'fool', 'myeail', 0, '2016-06-24', 1, '2016-06-24 08:36:59', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_deductions`
--

CREATE TABLE `hcmo_deductions` (
  `HcmoDeductionId` int(11) NOT NULL,
  `hcmoclientid` int(11) DEFAULT NULL,
  `DeductionName` varchar(100) DEFAULT NULL,
  `DeductionType` varchar(20) DEFAULT NULL,
  `DeductionMode` varchar(20) DEFAULT NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_department`
--

CREATE TABLE `hcmo_department` (
  `HcmoDepartmentId` int(11) NOT NULL,
  `hcmoclientid` int(11) DEFAULT NULL,
  `DeptName` varchar(120) DEFAULT NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `hcmo_department`
--

INSERT INTO `hcmo_department` (`HcmoDepartmentId`, `hcmoclientid`, `DeptName`, `Created`, `CreatedBy`, `Updated`, `UpdatedBy`, `IsActive`) VALUES
(1, 1, 'admin', '2015-12-28', 1, '2015-12-28 12:33:32', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_document`
--

CREATE TABLE `hcmo_document` (
  `HcmoDocumentId` int(11) NOT NULL,
  `HcmoEmployeeId` int(11) NOT NULL,
  `documentName` varchar(255) DEFAULT NULL,
  `documentType` varchar(100) DEFAULT NULL,
  `created` date DEFAULT NULL,
  `CreatedBy` int(11) DEFAULT NULL,
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) DEFAULT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1',
  `HcmoClientId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_document_template`
--

CREATE TABLE `hcmo_document_template` (
  `HcmoDocumentTemplateId` int(11) NOT NULL,
  `HcmoDocumentId` int(11) NOT NULL,
  `HcmoEmployeeId` int(11) NOT NULL,
  `documentName` varchar(255) DEFAULT NULL,
  `documentType` varchar(100) DEFAULT NULL,
  `vendor_view_permission` varchar(100) DEFAULT NULL,
  `vendor_edit_permission` varchar(100) DEFAULT NULL,
  `vendor_delete_permission` varchar(100) DEFAULT NULL,
  `vendor_share_permission` varchar(100) DEFAULT NULL,
  `created` date DEFAULT NULL,
  `CreatedBy` int(11) DEFAULT NULL,
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) DEFAULT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_employee`
--

CREATE TABLE `hcmo_employee` (
  `HcmoEmployeeId` int(11) NOT NULL,
  `HcmoClientId` int(11) DEFAULT NULL,
  `HcmoEthnicRaceId` int(11) DEFAULT NULL,
  `HcmoNationalityId` int(11) DEFAULT NULL,
  `HcmoJobTitleId` int(11) DEFAULT NULL,
  `HcmoRoleId` int(11) DEFAULT NULL,
  `HcmoCountryId` int(11) DEFAULT NULL,
  `HcmoEmpStatusId` int(11) DEFAULT NULL,
  `HcmoDepartmentId` int(11) DEFAULT NULL,
  `HcmoTeamId` int(11) DEFAULT NULL,
  `HcmoVendor` int(11) DEFAULT NULL,
  `EmpFirstName` varchar(100) DEFAULT NULL,
  `EmpMiddleName` varchar(100) DEFAULT NULL,
  `EmpLastName` varchar(100) DEFAULT NULL,
  `EmpNickName` varchar(100) DEFAULT NULL,
  `EmpFullName` varchar(255) DEFAULT NULL,
  `EmpSmoker` tinyint(1) DEFAULT NULL,
  `EmpBirthDay` date DEFAULT NULL,
  `EmpGender` varchar(1) DEFAULT NULL,
  `EmpMaritalStatus` varchar(20) DEFAULT NULL,
  `EmpSSNNum` varchar(100) DEFAULT NULL,
  `EmpOtherId` varchar(100) DEFAULT NULL,
  `EmpOtherName` varchar(60) DEFAULT NULL,
  `EmpDriLiceNum` varchar(100) DEFAULT NULL,
  `EmpDriLiceExpDate` date DEFAULT NULL,
  `EmpMilitaryService` varchar(100) DEFAULT NULL,
  `EmpStreet1` varchar(100) DEFAULT NULL,
  `EmpStreet2` varchar(100) DEFAULT NULL,
  `CityName` varchar(100) DEFAULT NULL,
  `CounName` varchar(100) DEFAULT NULL,
  `EmpZipCode` varchar(20) DEFAULT NULL,
  `EmpHmTelephone` varchar(50) DEFAULT NULL,
  `EmpMobile` varchar(50) DEFAULT NULL,
  `EmpWorkTelephone` varchar(50) DEFAULT NULL,
  `EmpWorkEmail` varchar(100) DEFAULT NULL,
  `JoinedDate` date DEFAULT NULL,
  `EmpOthEmail` varchar(100) DEFAULT NULL,
  `TerminatedDate` date DEFAULT NULL,
  `TerminationReason` varchar(255) DEFAULT NULL,
  `Custom1` varchar(255) DEFAULT NULL,
  `Custom2` varchar(255) DEFAULT NULL,
  `EmpSpace` varchar(255) DEFAULT NULL,
  `EmpPicturePath` varchar(255) DEFAULT NULL,
  `EmpStatus` varchar(13) DEFAULT NULL,
  `employee_wage` varchar(32) DEFAULT NULL,
  `EmpType` varchar(20) DEFAULT NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1',
  `Access_type` varchar(255) DEFAULT NULL,
  `resumeDocumentId` int(11) DEFAULT NULL,
  `rLiteAccess` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `hcmo_employee`
--

INSERT INTO `hcmo_employee` (`HcmoEmployeeId`, `HcmoClientId`, `HcmoEthnicRaceId`, `HcmoNationalityId`, `HcmoJobTitleId`, `HcmoRoleId`, `HcmoCountryId`, `HcmoEmpStatusId`, `HcmoDepartmentId`, `HcmoTeamId`, `HcmoVendor`, `EmpFirstName`, `EmpMiddleName`, `EmpLastName`, `EmpNickName`, `EmpFullName`, `EmpSmoker`, `EmpBirthDay`, `EmpGender`, `EmpMaritalStatus`, `EmpSSNNum`, `EmpOtherId`, `EmpOtherName`, `EmpDriLiceNum`, `EmpDriLiceExpDate`, `EmpMilitaryService`, `EmpStreet1`, `EmpStreet2`, `CityName`, `CounName`, `EmpZipCode`, `EmpHmTelephone`, `EmpMobile`, `EmpWorkTelephone`, `EmpWorkEmail`, `JoinedDate`, `EmpOthEmail`, `TerminatedDate`, `TerminationReason`, `Custom1`, `Custom2`, `EmpSpace`, `EmpPicturePath`, `EmpStatus`, `employee_wage`, `EmpType`, `Created`, `CreatedBy`, `Updated`, `UpdatedBy`, `IsActive`, `Access_type`, `resumeDocumentId`, `rLiteAccess`) VALUES
(1, 1, 1, 1, 1, 1, 224, 1, 1, 1, NULL, 'admin', '', 'admin12', 'aaaa', 'admin admin12,(admin@bonsai.com)', 1, '2016-06-15', '1', 'Single', '', '', NULL, '1598541', '2018-01-03', '', 'no:22 adyar', '', 'Chennai', 'Tamilnadu', '600021', '2514875259', '4543534543', '', 'admin@bonsai.com', '2010-01-01', '', NULL, '', '', '', '2097152', NULL, NULL, 'Monthly', 'Direct', '2009-11-21', 1, '2010-02-18 05:00:00', 1, 1, '', NULL, NULL),
(18, 1, 1, 1, 1, 1, 224, 1, 1, 1, NULL, 'Sowmiya', NULL, 'shrii12', NULL, 'Sowmiya shrii12,(bala@guruits.com)', 0, NULL, '1', NULL, NULL, NULL, NULL, '1598541', '2018-01-03', NULL, NULL, NULL, 'chennai', 'e4343', NULL, NULL, '9999999', NULL, 'bala@guruits.com', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'my type', '2016-06-24', 1, '2016-06-28 10:04:39', 1, 1, 'my type', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_employee_expenses`
--

CREATE TABLE `hcmo_employee_expenses` (
  `HcmoExpensesId` int(11) NOT NULL,
  `HcmoEmployeeId` int(11) DEFAULT NULL,
  `HcmoAccountantId` int(11) DEFAULT NULL,
  `CreatedDate` date DEFAULT NULL,
  `ApprovalStatus1` varchar(255) DEFAULT NULL,
  `ApprovalStatus2` varchar(255) DEFAULT NULL,
  `ApprovalStatus3` varchar(255) DEFAULT NULL,
  `HcmoApproverId1` int(11) DEFAULT NULL,
  `HcmoApproverId2` int(11) DEFAULT NULL,
  `HcmoApproverId3` int(11) DEFAULT NULL,
  `TotalAmount` decimal(19,2) DEFAULT NULL,
  `Approval1_Time` date DEFAULT NULL,
  `Approval2_Time` date DEFAULT NULL,
  `Approval3_Time` date DEFAULT NULL,
  `ReimbursementAmount` double DEFAULT NULL,
  `ReimbursementDate` date DEFAULT NULL,
  `AccountingNotes` text,
  `ProjectId` int(11) DEFAULT NULL,
  `Status` varchar(255) DEFAULT NULL,
  `NextLevelId` int(11) DEFAULT NULL,
  `ExpenseName` varchar(255) DEFAULT NULL,
  `Receipt` varchar(255) DEFAULT NULL,
  `ExpenseFromDate` date DEFAULT NULL,
  `ExpenseToDate` date DEFAULT NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1',
  `hcmoclientid` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_employee_shift`
--

CREATE TABLE `hcmo_employee_shift` (
  `HcmoShiftId` int(11) NOT NULL,
  `HcmoEmployeeId` int(11) NOT NULL,
  `HcmoShiftTypeId` int(11) DEFAULT NULL,
  `startDate` date DEFAULT NULL,
  `endDate` date DEFAULT NULL,
  `startTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `endTime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `created` date DEFAULT NULL,
  `CreatedBy` int(11) DEFAULT NULL,
  `updated` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `UpdatedBy` int(11) DEFAULT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1',
  `shiftType` varchar(100) DEFAULT NULL,
  `HcmoClientId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `hcmo_employee_shift`
--

INSERT INTO `hcmo_employee_shift` (`HcmoShiftId`, `HcmoEmployeeId`, `HcmoShiftTypeId`, `startDate`, `endDate`, `startTime`, `endTime`, `created`, `CreatedBy`, `updated`, `UpdatedBy`, `IsActive`, `shiftType`, `HcmoClientId`) VALUES
(2, 1, NULL, NULL, NULL, '2016-06-27 17:13:38', '2016-06-27 17:13:38', '2016-06-27', 1, '2016-06-27 17:13:38', 1, 0, '0', 0),
(3, 1, NULL, NULL, NULL, '2016-06-27 17:16:34', '2016-06-27 17:16:34', '2016-06-27', 1, '2016-06-27 17:16:34', 1, 0, '9AM To 6PM', 0);

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_empprotimesheet`
--

CREATE TABLE `hcmo_empprotimesheet` (
  `hcmo_empprotimesheet_id` int(11) NOT NULL,
  `enter_date` date DEFAULT NULL,
  `enter_time` double DEFAULT NULL,
  `rejected` smallint(6) DEFAULT NULL,
  `rework` smallint(6) DEFAULT NULL,
  `approve` smallint(6) DEFAULT NULL,
  `employee_id` int(11) DEFAULT NULL,
  `project_id` int(11) DEFAULT NULL,
  `projectActivity_id` int(11) DEFAULT NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1',
  `hcmoclientid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_empspace`
--

CREATE TABLE `hcmo_empspace` (
  `HcmoEmpSpaceId` int(11) NOT NULL,
  `HcmoEmployeeId` int(11) NOT NULL,
  `SharedFileTitle` varchar(100) DEFAULT NULL,
  `SharedFileDesc` text,
  `EmpSpaceAttachFileName` varchar(100) DEFAULT NULL,
  `SharedEmpIds` varchar(100) DEFAULT NULL,
  `SharedEmpFirstName` varchar(255) DEFAULT NULL,
  `SharedEmpEmailId` varchar(255) DEFAULT NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1',
  `hcmoclientid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_emptimesheet`
--

CREATE TABLE `hcmo_emptimesheet` (
  `hcmo_emptimesheet_id` int(11) NOT NULL,
  `enter_date` date DEFAULT NULL,
  `enter_time` double DEFAULT NULL,
  `rejected` tinyint(1) DEFAULT NULL,
  `rework` tinyint(1) DEFAULT NULL,
  `approve` tinyint(1) DEFAULT NULL,
  `employee_id` int(11) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  `hcmo_empprotimesheet_id` int(11) DEFAULT NULL,
  `project_id` int(11) DEFAULT NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1',
  `hcmoclientid` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_emp_assets`
--

CREATE TABLE `hcmo_emp_assets` (
  `HcmoAssetId` int(11) NOT NULL,
  `HcmoEmployeeId` int(11) NOT NULL,
  `SrNo` int(11) DEFAULT NULL,
  `AssetTypeId` int(11) DEFAULT NULL,
  `AssetName` varchar(255) DEFAULT NULL,
  `IssuedDate` date DEFAULT NULL,
  `ReleasedDate` date DEFAULT NULL,
  `created` date DEFAULT NULL,
  `CreatedBy` int(11) DEFAULT NULL,
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) DEFAULT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1',
  `hcmoclientid` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `hcmo_emp_assets`
--

INSERT INTO `hcmo_emp_assets` (`HcmoAssetId`, `HcmoEmployeeId`, `SrNo`, `AssetTypeId`, `AssetName`, `IssuedDate`, `ReleasedDate`, `created`, `CreatedBy`, `updated`, `UpdatedBy`, `IsActive`, `hcmoclientid`) VALUES
(1, 18, 1, 1, 'wewqe', NULL, NULL, '2016-06-27', 1, '2016-06-27 18:25:18', 1, 0, 1),
(2, 18, 1, 2, 'ww', NULL, NULL, '2016-06-27', 1, '2016-06-27 18:25:53', 1, 0, 1);

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_emp_attachment`
--

CREATE TABLE `hcmo_emp_attachment` (
  `HcmoEmployeeId` int(11) NOT NULL,
  `EattachId` decimal(10,0) DEFAULT NULL,
  `EattachDesc` text,
  `EattachFileName` varchar(100) DEFAULT NULL,
  `EattachSize` int(11) DEFAULT NULL,
  `EattachType` varchar(50) DEFAULT NULL,
  `HcmoEmpChildrenId` int(11) DEFAULT NULL,
  `ECName` varchar(100) DEFAULT NULL,
  `ECDateOfBirth` date DEFAULT NULL,
  `ECSeqNo` decimal(19,2) DEFAULT NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1',
  `hcmoclientid` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_emp_children`
--

CREATE TABLE `hcmo_emp_children` (
  `HcmoEmpChildrenId` int(11) NOT NULL,
  `HcmoEmployeeId` int(11) NOT NULL,
  `ECName` varchar(100) DEFAULT NULL,
  `ECDateOfBirth` date DEFAULT NULL,
  `ECSeqNo` decimal(19,2) DEFAULT NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1',
  `hcmoclientid` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_emp_direct_debit`
--

CREATE TABLE `hcmo_emp_direct_debit` (
  `HcmoEmpDirectDebitId` int(11) NOT NULL,
  `HcmoEmployeeId` int(11) NOT NULL,
  `DDRoutingNum` int(9) DEFAULT NULL,
  `DDAccount` varchar(100) DEFAULT NULL,
  `DDAmount` decimal(11,2) DEFAULT NULL,
  `DDAccountType` varchar(20) DEFAULT NULL,
  `DDTransactionType` varchar(20) DEFAULT NULL,
  `DDSeqNo` decimal(19,2) DEFAULT NULL,
  `DDPreferedAccount` tinyint(1) DEFAULT NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1',
  `hcmoclientid` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_emp_education`
--

CREATE TABLE `hcmo_emp_education` (
  `HcmoEmpEducationId` int(11) NOT NULL,
  `HcmoEmployeeId` int(11) NOT NULL,
  `EduMajor` varchar(100) DEFAULT NULL,
  `EduYear` int(4) DEFAULT NULL,
  `EduGpa` varchar(25) DEFAULT NULL,
  `EduStartDate` datetime DEFAULT NULL,
  `EduEndDate` datetime DEFAULT NULL,
  `EduUni` varchar(100) DEFAULT NULL,
  `EduDeg` varchar(60) DEFAULT NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1',
  `hcmoclientid` int(11) DEFAULT NULL,
  `gradStatus` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `hcmo_emp_education`
--

INSERT INTO `hcmo_emp_education` (`HcmoEmpEducationId`, `HcmoEmployeeId`, `EduMajor`, `EduYear`, `EduGpa`, `EduStartDate`, `EduEndDate`, `EduUni`, `EduDeg`, `Created`, `CreatedBy`, `Updated`, `UpdatedBy`, `IsActive`, `hcmoclientid`, `gradStatus`) VALUES
(1, 18, '0', 0, '3', NULL, NULL, NULL, '0', '2016-06-28', 1, '2016-06-27 18:36:23', 1, 1, 1, 0),
(2, 18, '1', 0, '3', NULL, NULL, NULL, '0', '2016-06-28', 1, '2016-06-27 18:37:24', 1, 1, 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_emp_eeo`
--

CREATE TABLE `hcmo_emp_eeo` (
  `HcmoEEOId` int(11) NOT NULL,
  `HcmoEmployeeId` int(11) NOT NULL,
  `HcmoEthnicRaceId` int(11) DEFAULT NULL,
  `maritalStatus` varchar(100) DEFAULT NULL,
  `dependents` int(11) DEFAULT NULL,
  `dependDetail` varchar(255) DEFAULT NULL,
  `veteran` varchar(100) DEFAULT NULL,
  `militaryStatus` varchar(100) DEFAULT NULL,
  `disability` varchar(100) DEFAULT NULL,
  `ethnicDocumentId` int(11) DEFAULT NULL,
  `veteranDocumentId` int(11) DEFAULT NULL,
  `militaryDocumentId` int(11) DEFAULT NULL,
  `disabilityDocumentId` int(11) DEFAULT NULL,
  `emergenConName` varchar(100) DEFAULT NULL,
  `emergenPhNo` int(20) DEFAULT NULL,
  `created` date DEFAULT NULL,
  `CreatedBy` int(11) DEFAULT NULL,
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) DEFAULT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1',
  `HcmoClientId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `hcmo_emp_eeo`
--

INSERT INTO `hcmo_emp_eeo` (`HcmoEEOId`, `HcmoEmployeeId`, `HcmoEthnicRaceId`, `maritalStatus`, `dependents`, `dependDetail`, `veteran`, `militaryStatus`, `disability`, `ethnicDocumentId`, `veteranDocumentId`, `militaryDocumentId`, `disabilityDocumentId`, `emergenConName`, `emergenPhNo`, `created`, `CreatedBy`, `updated`, `UpdatedBy`, `IsActive`, `HcmoClientId`) VALUES
(9, 18, 1, '0', 1, 'asd', '0', 'sd', 'sd', NULL, NULL, NULL, NULL, '4343', 43534, '2016-06-27', 1, '2016-06-27 17:55:52', 1, 0, 0),
(10, 18, 1, '0', 1, 'asd', '0', 'sd', 'sd', NULL, NULL, NULL, NULL, '4343', 43534, '2016-06-27', 1, '2016-06-27 17:59:06', 1, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_emp_leave_quota`
--

CREATE TABLE `hcmo_emp_leave_quota` (
  `HcmoEmpLeaveQuotaId` int(11) NOT NULL,
  `HcmoLeaveTypeId` int(11) NOT NULL,
  `HcmoEmployeeId` int(11) NOT NULL,
  `Year` int(4) DEFAULT NULL,
  `NoOfDaysAlloted` decimal(6,2) DEFAULT NULL,
  `Hours` decimal(6,2) DEFAULT NULL,
  `Minutes` decimal(6,2) DEFAULT NULL,
  `RemainingDays` decimal(6,2) DEFAULT NULL,
  `RemainingHours` decimal(6,2) DEFAULT NULL,
  `RemainingMinutes` decimal(6,2) DEFAULT NULL,
  `LeaveTakenDays` decimal(6,2) DEFAULT NULL,
  `LeaveTakenHours` decimal(6,2) DEFAULT NULL,
  `LeaveTakenMinutes` decimal(6,2) DEFAULT NULL,
  `PreCarryFwdDays` decimal(6,2) DEFAULT NULL,
  `PreCarryFwdHours` decimal(6,2) DEFAULT NULL,
  `PreCarryFwdMinutes` decimal(6,2) DEFAULT NULL,
  `LeaveTaken` decimal(10,2) DEFAULT NULL,
  `PrvYearCarryingForward` decimal(10,2) DEFAULT NULL,
  `LeaveCarryingForward` decimal(10,2) DEFAULT NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1',
  `hcmoclientid` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_emp_license`
--

CREATE TABLE `hcmo_emp_license` (
  `HcmoEmpLicenseId` int(11) NOT NULL,
  `HcmoEmployeeId` int(11) NOT NULL,
  `LicenseNumber` varchar(100) DEFAULT NULL,
  `LicenseDate` date DEFAULT NULL,
  `LicenseRenewalDate` date DEFAULT NULL,
  `LicenseDesc` text,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1',
  `hcmoclientid` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_emp_location_history`
--

CREATE TABLE `hcmo_emp_location_history` (
  `HcmoEmpLocHistoryId` int(11) NOT NULL,
  `HcmoEmployeeId` int(11) DEFAULT NULL,
  `HcmoClientId` int(11) DEFAULT NULL,
  `HcmoLocationId` int(11) DEFAULT NULL,
  `StartDate` datetime DEFAULT NULL,
  `EndDate` datetime DEFAULT NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_emp_passport`
--

CREATE TABLE `hcmo_emp_passport` (
  `HcmoEmpPassportId` int(11) NOT NULL,
  `HcmoEmployeeId` int(11) NOT NULL,
  `HcmoCountryId` int(11) DEFAULT NULL,
  `EpPassportNum` varchar(100) DEFAULT NULL,
  `EpPassportIssueDate` datetime DEFAULT NULL,
  `EpPassportExpireDate` datetime DEFAULT NULL,
  `EpComments` text,
  `EpPassportTypeFlg` varchar(1) DEFAULT NULL,
  `Ep19Status` varchar(100) DEFAULT NULL,
  `Ep19ReviewDate` date DEFAULT NULL,
  `EpSeqNo` decimal(19,2) DEFAULT NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1',
  `hcmoclientid` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_emp_report_to`
--

CREATE TABLE `hcmo_emp_report_to` (
  `HcmoEmpReportToId` int(11) NOT NULL,
  `HcmoEmployeeId` int(11) NOT NULL,
  `ERepSupEmpNumber` int(11) DEFAULT NULL,
  `ERepReportingMode` smallint(6) DEFAULT NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1',
  `hcmoclientid` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_emp_stat`
--

CREATE TABLE `hcmo_emp_stat` (
  `HcmoEmpStatId` int(11) NOT NULL,
  `hcmoclientid` int(11) DEFAULT NULL,
  `EStatName` varchar(60) DEFAULT NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `hcmo_emp_stat`
--

INSERT INTO `hcmo_emp_stat` (`HcmoEmpStatId`, `hcmoclientid`, `EStatName`, `Created`, `CreatedBy`, `Updated`, `UpdatedBy`, `IsActive`) VALUES
(1, 1, 'Joined', '2015-12-28', 1, '2015-12-28 12:23:07', 1, 1),
(2, 1, 'Terminated', '2015-12-28', 1, '2015-12-28 12:23:56', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_emp_target_goal`
--

CREATE TABLE `hcmo_emp_target_goal` (
  `HcmoEmpTgId` int(11) NOT NULL,
  `HcmoEmployeeId` int(11) DEFAULT NULL,
  `HcmoProjectId` int(11) DEFAULT NULL,
  `HcmoProjectActivityId` int(11) DEFAULT NULL,
  `EmpTargetName` varchar(120) DEFAULT NULL,
  `EmpTargetType` varchar(120) DEFAULT NULL,
  `EmpTargetMode` varchar(120) DEFAULT NULL,
  `EmpGoalName` varchar(255) DEFAULT NULL,
  `EmpTargetAchieved` text,
  `EmpTargetNotes` text,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1',
  `hcmoclientid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_emp_us_tax`
--

CREATE TABLE `hcmo_emp_us_tax` (
  `HcmoEmpUsTaxId` int(11) NOT NULL,
  `HcmoEmployeeId` int(11) NOT NULL,
  `TaxFederalStatus` varchar(20) DEFAULT NULL,
  `TaxFederalExceptions` varchar(20) DEFAULT NULL,
  `TaxState` int(11) DEFAULT NULL,
  `TaxStateStatus` varchar(20) DEFAULT NULL,
  `TaxStateExceptions` varchar(20) DEFAULT NULL,
  `TaxUnempState` int(11) DEFAULT NULL,
  `TaxWorkState` int(11) DEFAULT NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1',
  `hcmoclientid` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_emp_work_experience`
--

CREATE TABLE `hcmo_emp_work_experience` (
  `HcmoEmpWorkExperienceId` int(11) NOT NULL,
  `HcmoEmployeeId` int(11) NOT NULL,
  `EExpEmployer` varchar(100) DEFAULT NULL,
  `EExpJobTit` varchar(120) DEFAULT NULL,
  `EExpFromDate` datetime DEFAULT NULL,
  `EExpToDate` datetime DEFAULT NULL,
  `EExpComments` text,
  `EExpSeqNo` decimal(19,2) DEFAULT NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1',
  `hcmoclientid` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `hcmo_emp_work_experience`
--

INSERT INTO `hcmo_emp_work_experience` (`HcmoEmpWorkExperienceId`, `HcmoEmployeeId`, `EExpEmployer`, `EExpJobTit`, `EExpFromDate`, `EExpToDate`, `EExpComments`, `EExpSeqNo`, `Created`, `CreatedBy`, `Updated`, `UpdatedBy`, `IsActive`, `hcmoclientid`) VALUES
(1, 18, 'dfdaf', 'adsfasdfa', NULL, NULL, 'asfcasf', NULL, '2016-06-28', 1, '2016-06-27 18:30:58', 1, 1, 1),
(2, 18, 'dfdaf', 'adsfasdfa', NULL, NULL, 'ererfqefqe', NULL, '2016-06-28', 1, '2016-06-27 18:32:05', 1, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_ethnic_race`
--

CREATE TABLE `hcmo_ethnic_race` (
  `HcmoEthnicRaceId` int(11) NOT NULL,
  `EthnicRaceDesc` varchar(255) DEFAULT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1',
  `hcmoclientid` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `hcmo_ethnic_race`
--

INSERT INTO `hcmo_ethnic_race` (`HcmoEthnicRaceId`, `EthnicRaceDesc`, `IsActive`, `hcmoclientid`) VALUES
(1, 'American Indian or Alaska Native', 1, 1),
(2, 'Asian', 1, 1),
(3, 'Black or African American', 1, 1),
(4, 'Hispanic or Latino', 1, 1),
(5, 'Native Hawaiian or Other Pacific Islander', 1, 1),
(6, 'Two or More Races', 1, 1),
(7, 'White', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_events`
--

CREATE TABLE `hcmo_events` (
  `HcmoEventID` int(10) NOT NULL,
  `EmployeeID` int(10) NOT NULL,
  `EventName` varchar(50) DEFAULT NULL,
  `Description` varchar(200) DEFAULT NULL,
  `StartDate` datetime DEFAULT NULL,
  `GroupId` int(15) DEFAULT NULL,
  `TimeZone` varchar(150) DEFAULT NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1',
  `hcmoclientid` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_expenses_accountant`
--

CREATE TABLE `hcmo_expenses_accountant` (
  `HcmoExpensesAccountantId` int(11) NOT NULL,
  `ExpensesAccountantId` int(11) NOT NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1',
  `hcmoclientid` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_expenses_approver`
--

CREATE TABLE `hcmo_expenses_approver` (
  `ApproverId` int(11) NOT NULL,
  `HcmoEmployeeId` int(11) NOT NULL,
  `ApprovingEmployeeId` int(11) DEFAULT NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1',
  `hcmoclientid` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `hcmo_expenses_approver`
--

INSERT INTO `hcmo_expenses_approver` (`ApproverId`, `HcmoEmployeeId`, `ApprovingEmployeeId`, `Created`, `CreatedBy`, `Updated`, `UpdatedBy`, `IsActive`, `hcmoclientid`) VALUES
(2, 18, 18, '2016-06-27', 1, '2016-06-27 13:02:34', 1, 1, 0),
(3, 18, 18, '2016-06-27', 1, '2016-06-27 13:05:29', 1, 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_expenses_attachment`
--

CREATE TABLE `hcmo_expenses_attachment` (
  `HcmoExpensesAttachId` int(11) NOT NULL,
  `HcmoEmployeeId` int(11) DEFAULT NULL,
  `HcmoExpensesId` int(11) DEFAULT NULL,
  `ExpensesAttachFileName` varchar(255) DEFAULT NULL,
  `ExpensesAttachSize` int(11) DEFAULT NULL,
  `ExpensesAttachType` varchar(255) DEFAULT NULL,
  `ExpensesAttachId` int(11) DEFAULT NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1',
  `hcmoclientid` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_expenses_details`
--

CREATE TABLE `hcmo_expenses_details` (
  `HcmoExpensesDetailsId` int(11) NOT NULL,
  `HcmoExpensesTypeId` int(11) DEFAULT NULL,
  `HcmoExpensesId` int(11) DEFAULT NULL,
  `ProjectId` int(11) DEFAULT NULL,
  `customerid` int(11) DEFAULT NULL,
  `deptId` int(11) DEFAULT NULL,
  `Amount` decimal(19,2) DEFAULT NULL,
  `Note` text,
  `Description` text,
  `ExpenseDate` date DEFAULT NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1',
  `hcmoclientid` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_expenses_type`
--

CREATE TABLE `hcmo_expenses_type` (
  `HcmoExpensesTypeId` int(11) NOT NULL,
  `Name` varchar(255) DEFAULT NULL,
  `hcmoclientid` int(11) DEFAULT NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_expense_status_tracker`
--

CREATE TABLE `hcmo_expense_status_tracker` (
  `HcmoExpensesStatusTrackerId` int(11) NOT NULL,
  `HcmoExpensesId` int(11) DEFAULT NULL,
  `ApproverId` int(11) DEFAULT NULL,
  `RejectedNotes` text,
  `RejectedId` int(11) DEFAULT NULL,
  `AccountantId` int(11) DEFAULT NULL,
  `NextLevelId` int(11) DEFAULT NULL,
  `ReviewedId` int(11) DEFAULT NULL,
  `ApprovalStatus` varchar(100) DEFAULT 'ForApproval',
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `hcmoclientid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_goal`
--

CREATE TABLE `hcmo_goal` (
  `HcmoGoalId` int(11) NOT NULL,
  `GoalName` varchar(255) DEFAULT NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1',
  `hcmoclientid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_holidays`
--

CREATE TABLE `hcmo_holidays` (
  `HcmoHolidaysId` int(11) NOT NULL,
  `hcmoclientid` int(11) DEFAULT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `Date` date DEFAULT NULL,
  `Recurring` tinyint(1) DEFAULT NULL,
  `Length` tinyint(2) DEFAULT NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_i9`
--

CREATE TABLE `hcmo_i9` (
  `HcmoI9DocumentId` int(11) NOT NULL,
  `hcmoclientid` int(11) DEFAULT NULL,
  `HcmoEmployeeId` int(10) DEFAULT NULL,
  `EmployeeLName` varchar(50) DEFAULT NULL,
  `EmployeeFName` varchar(50) DEFAULT NULL,
  `EmployeeMName` varchar(50) DEFAULT NULL,
  `EmployeeOName` varchar(50) DEFAULT NULL,
  `EmployeeAddrs` varchar(200) DEFAULT NULL,
  `AptNumber` varchar(8) DEFAULT NULL,
  `EmployeeCity` varchar(21) DEFAULT NULL,
  `EmployeeState` varchar(3) DEFAULT NULL,
  `EmployeeZip` varchar(15) DEFAULT NULL,
  `EmployeeDOB` varchar(20) DEFAULT NULL,
  `EmployeeSSN` varchar(9) DEFAULT NULL,
  `EmployeeEmail` varchar(40) DEFAULT NULL,
  `EmployeePhoneNo` varchar(15) DEFAULT NULL,
  `Citizen` varchar(20) DEFAULT NULL,
  `NonCitizen` varchar(20) DEFAULT NULL,
  `PermanentResident` varchar(20) DEFAULT NULL,
  `AuthorizedToWork` varchar(20) DEFAULT NULL,
  `AlienRegNo1` varchar(9) DEFAULT NULL,
  `AlienWorkExpiry` varchar(20) DEFAULT NULL,
  `AlienRegNo2` varchar(9) DEFAULT NULL,
  `I94AdmissNo` varchar(11) DEFAULT NULL,
  `PassportNo` varchar(36) DEFAULT NULL,
  `IssueCountry` varchar(50) DEFAULT NULL,
  `EmployeeSignDate` varchar(20) DEFAULT NULL,
  `PreparerSignDate` varchar(20) DEFAULT NULL,
  `PreparerLName` varchar(50) DEFAULT NULL,
  `PreparerFName` varchar(50) DEFAULT NULL,
  `PreparerAddress` varchar(200) DEFAULT NULL,
  `PreparerCity` varchar(22) DEFAULT NULL,
  `PreparerState` varchar(5) DEFAULT NULL,
  `PreparerZip` varchar(15) DEFAULT NULL,
  `EmployeeLFMName` varchar(150) DEFAULT NULL,
  `ListADocTitle1` varchar(30) DEFAULT NULL,
  `ListBDocTitle` varchar(30) DEFAULT NULL,
  `ListCDocTitle` varchar(30) DEFAULT NULL,
  `ListAIssueAuth1` varchar(30) DEFAULT NULL,
  `ListBIssueAuth` varchar(30) DEFAULT NULL,
  `ListCIssueAuth` varchar(30) DEFAULT NULL,
  `ListADocNo1` varchar(30) DEFAULT NULL,
  `ListBDocNo` varchar(30) DEFAULT NULL,
  `ListCDocNo` varchar(30) DEFAULT NULL,
  `ListAExpDate1` varchar(20) DEFAULT NULL,
  `ListBExpDate` varchar(20) DEFAULT NULL,
  `ListCExpDate` varchar(20) DEFAULT NULL,
  `ListADocTitle2` varchar(30) DEFAULT NULL,
  `ListAIssueAuth2` varchar(30) DEFAULT NULL,
  `ListADocNo2` varchar(30) DEFAULT NULL,
  `ListAExpDate2` varchar(20) DEFAULT NULL,
  `ListADocTitle3` varchar(30) DEFAULT NULL,
  `ListAIssueAuth3` varchar(30) DEFAULT NULL,
  `ListADocNo3` varchar(30) DEFAULT NULL,
  `ListAExpDate3` varchar(20) DEFAULT NULL,
  `EmployeeFirstDay` varchar(20) DEFAULT NULL,
  `EmployerSignDate` varchar(20) DEFAULT NULL,
  `EmployerTitle` varchar(33) DEFAULT NULL,
  `EmployerLName` varchar(50) DEFAULT NULL,
  `EmployerFName` varchar(50) DEFAULT NULL,
  `EmployerOrgName` varchar(50) DEFAULT NULL,
  `EmployerOrgAddrs` varchar(200) DEFAULT NULL,
  `EmployerCity` varchar(23) DEFAULT NULL,
  `EmployerState` varchar(5) DEFAULT NULL,
  `EmployerZip` varchar(15) DEFAULT NULL,
  `RehireLName` varchar(50) DEFAULT NULL,
  `RehireFName` varchar(50) DEFAULT NULL,
  `RehireMName` varchar(50) DEFAULT NULL,
  `RehireDate` varchar(20) DEFAULT NULL,
  `RehireDocTitle` varchar(35) DEFAULT NULL,
  `RehireDocNo` varchar(30) DEFAULT NULL,
  `RehireExpDate` varchar(20) DEFAULT NULL,
  `RehireSignDate` varchar(20) DEFAULT NULL,
  `RehireEmployerName` varchar(50) DEFAULT NULL,
  `EmployeeStatus` int(11) DEFAULT '0',
  `EmployerStatus` int(11) DEFAULT '0',
  `CreatedBy` int(11) DEFAULT NULL,
  `UpdatedBy` int(11) DEFAULT NULL,
  `Created` date DEFAULT NULL,
  `EmployeeSign` longtext,
  `UpdatedDt` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `IsActive` smallint(6) DEFAULT '1',
  `HcmoI9RecvDate` varchar(200) DEFAULT NULL,
  `I9Bypass` smallint(6) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_important_news`
--

CREATE TABLE `hcmo_important_news` (
  `HcmoImportantNewsId` int(11) NOT NULL,
  `hcmoclientid` int(11) NOT NULL,
  `HcmoEmployeeId` int(11) DEFAULT NULL,
  `Subject` varchar(255) DEFAULT NULL,
  `Message` text NOT NULL,
  `ShowMessage` tinyint(1) DEFAULT '0',
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_job_title`
--

CREATE TABLE `hcmo_job_title` (
  `HcmoJobTitleId` int(11) NOT NULL,
  `HcmoSalaryGradeId` int(11) NOT NULL,
  `JobTitleName` varchar(60) NOT NULL,
  `JobTitleDesc` text,
  `JobTitleComm` text,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1',
  `hcmoclientid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `hcmo_job_title`
--

INSERT INTO `hcmo_job_title` (`HcmoJobTitleId`, `HcmoSalaryGradeId`, `JobTitleName`, `JobTitleDesc`, `JobTitleComm`, `Created`, `CreatedBy`, `Updated`, `UpdatedBy`, `IsActive`, `hcmoclientid`) VALUES
(1, 1, 'admin', NULL, NULL, '2015-12-28', 1, '2015-12-28 12:19:34', 1, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_leave_approver`
--

CREATE TABLE `hcmo_leave_approver` (
  `HcmoLeaveApproverId` int(11) NOT NULL,
  `HcmoEmployeeId` int(11) NOT NULL,
  `HcmoApprovingEmpId` int(11) DEFAULT NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1',
  `hcmoclientid` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_leave_history`
--

CREATE TABLE `hcmo_leave_history` (
  `HcmoLeaveHistoryId` int(11) NOT NULL,
  `HcmoLeaveApproverId` int(11) DEFAULT NULL,
  `HcmoEmployeeId` int(11) NOT NULL,
  `HcmoLeaveTypeId` int(11) NOT NULL,
  `LeaveRequestId` int(11) DEFAULT NULL,
  `LeaveDate` date DEFAULT NULL,
  `LeaveLengthHours` decimal(19,1) DEFAULT NULL,
  `LeaveLengthDays` decimal(19,1) DEFAULT NULL,
  `Days` decimal(6,2) DEFAULT NULL,
  `Hours` decimal(6,2) DEFAULT NULL,
  `Minutes` decimal(6,2) DEFAULT NULL,
  `LeaveStatus` varchar(20) DEFAULT NULL,
  `LeaveComments` text,
  `ApproveNotes` text,
  `DisApproveNotes` text,
  `StartTime` varchar(20) DEFAULT NULL,
  `EndTime` varchar(20) DEFAULT NULL,
  `LeaveEndDate` date DEFAULT NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1',
  `hcmoclientid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_leave_reqs_approval`
--

CREATE TABLE `hcmo_leave_reqs_approval` (
  `HcmoLeaveReqsApprovalId` int(11) NOT NULL,
  `HcmoApproverId` int(11) DEFAULT NULL,
  `HcmoLeaveTypeId` int(11) NOT NULL,
  `HcmoEmployeeId` int(11) NOT NULL,
  `HcmoLeaveApproverId` int(11) DEFAULT NULL,
  `DateApplied` date NOT NULL,
  `DateApprDisappr` date DEFAULT NULL,
  `Comments` text,
  `LeaveReqStatus` varchar(20) DEFAULT 'For Approval',
  `noOfDays` decimal(6,2) DEFAULT NULL,
  `NoOfHours` decimal(6,2) DEFAULT NULL,
  `NoOfMins` decimal(6,2) DEFAULT NULL,
  `ApproveNotes` text,
  `DisApproveNotes` text,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1',
  `hcmoclientid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_leave_type`
--

CREATE TABLE `hcmo_leave_type` (
  `HcmoLeaveTypeId` int(11) NOT NULL,
  `LeaveTypeName` varchar(20) DEFAULT NULL,
  `hcmoclientid` int(11) DEFAULT NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_location`
--

CREATE TABLE `hcmo_location` (
  `HcmoLocationId` int(11) NOT NULL,
  `HcmoCountryId` int(11) NOT NULL,
  `Name` varchar(60) DEFAULT NULL,
  `Address1` varchar(60) DEFAULT NULL,
  `Address2` varchar(60) DEFAULT NULL,
  `City` varchar(60) DEFAULT NULL,
  `Region` varchar(60) DEFAULT NULL,
  `Phone` varchar(20) DEFAULT NULL,
  `Fax` varchar(20) DEFAULT NULL,
  `Comments` text,
  `ZipCode` varchar(20) DEFAULT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_mail_configuration`
--

CREATE TABLE `hcmo_mail_configuration` (
  `HcmoMailConfigurationId` int(11) NOT NULL,
  `hcmoclientid` int(11) DEFAULT NULL,
  `SmtpHost` varchar(255) DEFAULT NULL,
  `Username` varchar(255) DEFAULT NULL,
  `Password` varchar(255) DEFAULT NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `hcmo_mail_configuration`
--

INSERT INTO `hcmo_mail_configuration` (`HcmoMailConfigurationId`, `hcmoclientid`, `SmtpHost`, `Username`, `Password`, `Created`, `CreatedBy`, `Updated`, `UpdatedBy`, `IsActive`) VALUES
(1, 1, 'smtp.gmail.com', 'aju.saifan@gmail.com', 'we1c@me321', '2016-05-23', 1, '2016-05-23 06:39:06', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_message`
--

CREATE TABLE `hcmo_message` (
  `HcmoMessageId` int(11) NOT NULL,
  `hcmoclientid` int(11) NOT NULL,
  `sender` int(11) DEFAULT NULL,
  `Receiver` varchar(255) DEFAULT NULL,
  `ReceiverEmailId` varchar(255) DEFAULT NULL,
  `Cc` varchar(255) DEFAULT NULL,
  `CcEmailId` text,
  `Bcc` varchar(255) DEFAULT NULL,
  `BccEmailId` text,
  `Subject` varchar(255) DEFAULT NULL,
  `Message` text,
  `Signature` text,
  `Type` varchar(20) DEFAULT NULL,
  `DeletedReceiverId` varchar(255) DEFAULT NULL,
  `DeletedCcId` varchar(255) DEFAULT NULL,
  `DeletedBccId` varchar(255) DEFAULT NULL,
  `SenderDelete` smallint(6) DEFAULT NULL,
  `ReceiverDelete` smallint(6) DEFAULT NULL,
  `IsNewMail` smallint(6) DEFAULT NULL,
  `IsRead` smallint(6) DEFAULT NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_module`
--

CREATE TABLE `hcmo_module` (
  `moduleId` int(2) NOT NULL,
  `moduleType` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `hcmo_module`
--

INSERT INTO `hcmo_module` (`moduleId`, `moduleType`) VALUES
(1, 'HCM'),
(2, 'Recruit'),
(3, 'Both');

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_nationality`
--

CREATE TABLE `hcmo_nationality` (
  `HcmoNationalityId` int(11) NOT NULL,
  `NatName` varchar(120) DEFAULT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1',
  `hcmoclientid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `hcmo_nationality`
--

INSERT INTO `hcmo_nationality` (`HcmoNationalityId`, `NatName`, `IsActive`, `hcmoclientid`) VALUES
(1, 'America', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_org`
--

CREATE TABLE `hcmo_org` (
  `HcmoOrgId` int(11) NOT NULL,
  `HcmoLocationId` int(11) NOT NULL,
  `HcmoOrgTypeId` int(11) NOT NULL,
  `ParentOrgId` int(11) DEFAULT NULL,
  `Name` varchar(60) NOT NULL,
  `Description` text,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1',
  `hcmoclientid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_org_type`
--

CREATE TABLE `hcmo_org_type` (
  `HcmoOrgTypeId` int(11) NOT NULL,
  `hcmoclientid` int(11) NOT NULL,
  `Name` varchar(60) NOT NULL,
  `Description` text,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_paymentinfo`
--

CREATE TABLE `hcmo_paymentinfo` (
  `invoice_id` varchar(45) DEFAULT NULL,
  `contact_id` int(11) DEFAULT NULL,
  `payr_id` varchar(25) DEFAULT NULL,
  `payr_first_name` varchar(150) DEFAULT NULL,
  `payr_last_name` varchar(100) DEFAULT NULL,
  `payr_name` varchar(200) DEFAULT NULL,
  `payr_email` varchar(250) DEFAULT NULL,
  `payr_res_country` varchar(100) DEFAULT NULL,
  `payr_comm_country` varchar(100) DEFAULT NULL,
  `payr_comm_state` varchar(150) DEFAULT NULL,
  `payr_comm_city` varchar(150) DEFAULT NULL,
  `payr_comm_street` varchar(200) DEFAULT NULL,
  `payr_comm_pincode` varchar(20) DEFAULT NULL,
  `payr_comm_status` varchar(25) DEFAULT NULL,
  `payr_country_code` varchar(10) DEFAULT NULL,
  `payr_status` varchar(10) DEFAULT NULL,
  `admin_email` varchar(200) DEFAULT NULL,
  `receiver_id` varchar(20) DEFAULT NULL,
  `rec_email` varchar(200) DEFAULT NULL,
  `trans_id` varchar(50) DEFAULT NULL,
  `trans_sub` varchar(300) DEFAULT NULL,
  `trans_type` varchar(25) DEFAULT NULL,
  `trans_action` varchar(10) DEFAULT NULL,
  `item_id` varchar(11) DEFAULT NULL,
  `item_name` varchar(100) DEFAULT NULL,
  `mc_gross` varchar(10) DEFAULT NULL,
  `mc_currency` varchar(10) DEFAULT NULL,
  `mc_fee` varchar(10) DEFAULT NULL,
  `tot_qty` varchar(11) DEFAULT NULL,
  `pay_amnt` varchar(10) DEFAULT NULL,
  `paymnt_fee` varchar(10) DEFAULT NULL,
  `paymnt_gross` varchar(10) DEFAULT NULL,
  `paymnt_date` varchar(25) DEFAULT NULL,
  `paymnt_type` varchar(25) DEFAULT NULL,
  `paymnt_stat` varchar(25) DEFAULT NULL,
  `shipping_fee` varchar(10) DEFAULT NULL,
  `handling_charge` varchar(10) DEFAULT NULL,
  `tax` varchar(10) DEFAULT NULL,
  `auth_code` varchar(250) DEFAULT NULL,
  `versign_id` varchar(250) DEFAULT NULL,
  `test_IPN` char(10) DEFAULT NULL,
  `prot_eligibility` varchar(20) DEFAULT NULL,
  `url_status` varchar(20) DEFAULT NULL,
  `notify_version` char(5) DEFAULT NULL,
  `charter` varchar(100) DEFAULT NULL,
  `pend_response` varchar(50) DEFAULT NULL,
  `comments` varchar(455) DEFAULT NULL,
  `entry_datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_paystub`
--

CREATE TABLE `hcmo_paystub` (
  `HcmoPayStubId` int(11) NOT NULL,
  `HcmoEmployeeId` int(11) DEFAULT NULL,
  `GrossSalary` double(13,2) DEFAULT NULL,
  `NetSalary` double(13,2) DEFAULT NULL,
  `DeclarationDate` date DEFAULT NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1',
  `hcmoclientid` int(11) DEFAULT NULL,
  `empType` varchar(100) DEFAULT NULL,
  `commission` double(13,2) DEFAULT NULL,
  `bonus` double(13,2) DEFAULT NULL,
  `regularRate` double(13,2) DEFAULT NULL,
  `overtimeRate` double(13,2) DEFAULT NULL,
  `isBenefit` tinyint(1) DEFAULT NULL,
  `role` varchar(100) DEFAULT NULL,
  `paymentType` varchar(100) DEFAULT NULL,
  `firstDay` varchar(100) DEFAULT NULL,
  `payDate` date DEFAULT NULL,
  `payFreq` int(13) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `hcmo_paystub`
--

INSERT INTO `hcmo_paystub` (`HcmoPayStubId`, `HcmoEmployeeId`, `GrossSalary`, `NetSalary`, `DeclarationDate`, `Created`, `CreatedBy`, `Updated`, `UpdatedBy`, `IsActive`, `hcmoclientid`, `empType`, `commission`, `bonus`, `regularRate`, `overtimeRate`, `isBenefit`, `role`, `paymentType`, `firstDay`, `payDate`, `payFreq`) VALUES
(1, 1, 100000.00, 100000.00, NULL, '2016-06-28', 1, '2016-06-28 11:59:35', 1, 1, 1, '0', 2.00, NULL, 2.00, 2.00, 1, NULL, '0', '2', NULL, 0);

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_paystub_archive`
--

CREATE TABLE `hcmo_paystub_archive` (
  `HcmoPayStubArchiveId` int(11) NOT NULL,
  `PayStub_HcmoPayStubId` int(11) DEFAULT NULL,
  `PayStub_HcmoEmployeeId` int(11) DEFAULT NULL,
  `PayStub_GrossSalary` double(13,2) DEFAULT NULL,
  `PayStub_NetSalary` double(13,2) DEFAULT NULL,
  `PayStub_DeclarationDate` date DEFAULT NULL,
  `PayStub_Created` date DEFAULT NULL,
  `PayStub_CreatedBy` int(11) DEFAULT NULL,
  `PayStub_Updated` timestamp NULL DEFAULT NULL,
  `PayStub_UpdatedBy` int(11) DEFAULT NULL,
  `PayStub_IsActive` tinyint(1) DEFAULT '1',
  `PayStubDeduction_Hcmo_PayStub_DeductionsId` int(11) DEFAULT NULL,
  `PayStubDeduction_HcmoPayStubId` int(11) DEFAULT NULL,
  `PayStubDeduction_HcmoDeductionId` int(11) DEFAULT NULL,
  `PayStubDeduction_DeductionAmount` double(13,2) DEFAULT NULL,
  `PayStubDeduction_EffectiveDate` date DEFAULT NULL,
  `PayStubDeduction_Created` date DEFAULT NULL,
  `PayStubDeduction_CreatedBy` int(11) DEFAULT NULL,
  `PayStubDeduction_Updated` timestamp NULL DEFAULT NULL,
  `PayStubDeduction_UpdatedBy` int(11) DEFAULT NULL,
  `PayStubDeduction_IsActive` tinyint(1) DEFAULT '1',
  `Deleted` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `DeletedBy` int(11) NOT NULL,
  `hcmoclientid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_paystub_deductions`
--

CREATE TABLE `hcmo_paystub_deductions` (
  `Hcmo_PayStub_DeductionsId` int(11) NOT NULL,
  `HcmoPayStubId` int(11) NOT NULL,
  `HcmoDeductionId` int(11) NOT NULL,
  `DeductionAmount` double(13,2) DEFAULT NULL,
  `EffectiveDate` date DEFAULT NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1',
  `hcmoclientid` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_plan`
--

CREATE TABLE `hcmo_plan` (
  `HcmoPlanId` int(11) NOT NULL,
  `PlanName` varchar(255) NOT NULL DEFAULT '0',
  `NoOfPerson` int(11) NOT NULL DEFAULT '0',
  `SubscriptionAmount` int(11) NOT NULL DEFAULT '0',
  `hcmoclientid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_plan_subscription`
--

CREATE TABLE `hcmo_plan_subscription` (
  `HcmoPlanClientId` int(11) NOT NULL,
  `hcmoplanid` int(11) NOT NULL DEFAULT '0',
  `hcmoclientid` int(11) NOT NULL DEFAULT '0',
  `subscribedDate` date NOT NULL DEFAULT '0000-00-00',
  `SubscriptionEndDate` date NOT NULL DEFAULT '0000-00-00',
  `RenewedCount` int(11) NOT NULL DEFAULT '0',
  `TrialCountDays` int(11) NOT NULL DEFAULT '0',
  `isActive` int(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_project`
--

CREATE TABLE `hcmo_project` (
  `HcmoProjectId` int(11) NOT NULL,
  `HcmoCustomerId` int(11) NOT NULL,
  `ProjectOwner` int(11) NOT NULL,
  `Name` varchar(120) DEFAULT NULL,
  `Description` text,
  `EstimatedHours` varchar(255) DEFAULT NULL,
  `Deleted` tinyint(1) DEFAULT '0',
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1',
  `hcmoclientid` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `hcmo_project`
--

INSERT INTO `hcmo_project` (`HcmoProjectId`, `HcmoCustomerId`, `ProjectOwner`, `Name`, `Description`, `EstimatedHours`, `Deleted`, `Created`, `CreatedBy`, `Updated`, `UpdatedBy`, `IsActive`, `hcmoclientid`) VALUES
(1, 1, 1, 'VMS', 'TEST IS MUST', '120', 0, '2016-06-24', 1, '2016-06-24 08:49:44', 1, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_project_activity`
--

CREATE TABLE `hcmo_project_activity` (
  `HcmoProjectActivityId` int(11) NOT NULL,
  `HcmoProjectId` int(11) NOT NULL,
  `ActivityName` varchar(120) DEFAULT NULL,
  `ProjectActivityOwner` int(11) DEFAULT NULL,
  `Notes` text,
  `EstimatedHours` varchar(255) DEFAULT NULL,
  `Deleted` tinyint(1) NOT NULL DEFAULT '0',
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1',
  `hcmoclientid` int(11) DEFAULT NULL,
  `HcmoDepartmentId` int(11) NOT NULL,
  `HcmoEmployeeId` int(11) NOT NULL,
  `taskStartDate` date DEFAULT NULL,
  `taskEndDate` date DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `hcmo_project_activity`
--

INSERT INTO `hcmo_project_activity` (`HcmoProjectActivityId`, `HcmoProjectId`, `ActivityName`, `ProjectActivityOwner`, `Notes`, `EstimatedHours`, `Deleted`, `Created`, `CreatedBy`, `Updated`, `UpdatedBy`, `IsActive`, `hcmoclientid`, `HcmoDepartmentId`, `HcmoEmployeeId`, `taskStartDate`, `taskEndDate`, `description`) VALUES
(1, 1, '1', NULL, NULL, '44', 0, '2016-06-27', 1, '2016-06-27 10:58:53', 1, 1, NULL, 1, 18, NULL, NULL, 'termss '),
(2, 1, '0', NULL, NULL, '', 0, '2016-06-27', 1, '2016-06-27 12:29:06', 1, 1, NULL, 1, 18, NULL, NULL, ''),
(3, 1, '0', NULL, NULL, '', 0, '2016-06-27', 1, '2016-06-27 12:32:12', 1, 1, NULL, 1, 18, NULL, NULL, 'safdsfSFs');

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_question`
--

CREATE TABLE `hcmo_question` (
  `HcmoQuestionId` int(11) NOT NULL,
  `HcmoCategoryId` int(11) NOT NULL,
  `HcmoSubCategoryId` int(11) NOT NULL,
  `Question` varchar(100) NOT NULL,
  `QuestionType` varchar(100) NOT NULL,
  `Option1` varchar(100) DEFAULT NULL,
  `Option2` varchar(100) DEFAULT NULL,
  `Option3` varchar(100) DEFAULT NULL,
  `Option4` varchar(100) DEFAULT NULL,
  `Option5` varchar(100) DEFAULT NULL,
  `Option6` varchar(100) DEFAULT NULL,
  `MinRate` varchar(100) DEFAULT NULL,
  `MaxRate` varchar(100) DEFAULT NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1',
  `hcmoclientid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_question_general_info`
--

CREATE TABLE `hcmo_question_general_info` (
  `HcmoQuestionGeneralInfoId` int(11) NOT NULL,
  `HcmoApprovingEmployeeId` int(11) DEFAULT NULL,
  `HcmoAdminId` int(11) DEFAULT NULL,
  `HcmoQuestionGeneralInfoGroupId` int(11) NOT NULL,
  `HcmoQuestionGroupNameId` int(11) DEFAULT NULL,
  `HcmoQuestionGroupNameIdentifiId` int(11) NOT NULL,
  `HcmoDepartmentId` int(11) NOT NULL,
  `HcmoJobTitleId` int(11) NOT NULL,
  `HcmoTeamId` int(11) NOT NULL,
  `ApprovingEmpComments` varchar(100) DEFAULT NULL,
  `AdminComments` varchar(100) DEFAULT NULL,
  `EmployeeType` varchar(100) NOT NULL,
  `PerformanceIndStartDate` varchar(100) NOT NULL,
  `PerformanceIndEndDate` varchar(100) NOT NULL,
  `HcmoEmployeeId` int(11) NOT NULL,
  `PeerEmployeeId` int(11) DEFAULT NULL,
  `Status` varchar(100) DEFAULT NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1',
  `hcmoclientid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_question_general_info_group`
--

CREATE TABLE `hcmo_question_general_info_group` (
  `HcmoQuestionGeneralInfoGroupId` int(11) NOT NULL,
  `Name` varchar(100) NOT NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1',
  `hcmoclientid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_question_group_name`
--

CREATE TABLE `hcmo_question_group_name` (
  `HcmoQuestionGroupNameId` int(11) NOT NULL,
  `HcmoQuestionId` int(11) NOT NULL,
  `HcmoQuestionGroupNameIdentifiId` int(11) NOT NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1',
  `hcmoclientid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_question_group_name_identification`
--

CREATE TABLE `hcmo_question_group_name_identification` (
  `HcmoQuestionGroupNameIdentificationId` int(11) NOT NULL,
  `Name` varchar(100) NOT NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1',
  `hcmoclientid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_recurring_profile`
--

CREATE TABLE `hcmo_recurring_profile` (
  `recurring_prof_id` int(11) NOT NULL,
  `hcmoclientid` int(11) NOT NULL,
  `payment_gateway` varchar(30) DEFAULT NULL,
  `merchant_gateway` varchar(50) DEFAULT NULL COMMENT 'Merchant getway for engine',
  `paymnt_request_type` int(11) DEFAULT NULL COMMENT '1-Premium subscription,2-Job referral',
  `bill_agreemntid` varchar(50) DEFAULT NULL,
  `bill_agreemnt_status` varchar(30) DEFAULT NULL,
  `name` varchar(70) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `currencycode` varchar(10) DEFAULT NULL,
  `country_code` varchar(100) DEFAULT NULL,
  `country_name` varchar(100) DEFAULT NULL,
  `city` varchar(100) DEFAULT NULL,
  `street` varchar(150) DEFAULT NULL,
  `payr_verification_status` varchar(30) DEFAULT NULL,
  `token_id` varchar(50) DEFAULT NULL,
  `tot_qty` int(11) DEFAULT NULL,
  `paymnt_status` varchar(30) DEFAULT NULL,
  `ack_status` varchar(30) DEFAULT NULL,
  `recurring_status` int(11) DEFAULT NULL COMMENT '1-enable,2diable',
  `entry_datetime` datetime DEFAULT NULL,
  `update_datetime` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_region`
--

CREATE TABLE `hcmo_region` (
  `HcmoRegionId` int(11) NOT NULL,
  `HcmoCountryId` int(11) NOT NULL,
  `ZipCode` varchar(50) NOT NULL DEFAULT '',
  `City` varchar(100) NOT NULL DEFAULT '',
  `Name` varchar(100) NOT NULL,
  `RegionCode` varchar(5) NOT NULL,
  `CountyField` varchar(100) DEFAULT NULL,
  `AreaCode` varchar(50) DEFAULT '',
  `TimeZone` varchar(50) DEFAULT '',
  `Latitude` varchar(50) DEFAULT '',
  `Longitude` varchar(50) DEFAULT '',
  `IsActive` tinyint(1) NOT NULL DEFAULT '1',
  `hcmoclientid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_role`
--

CREATE TABLE `hcmo_role` (
  `HcmoRoleId` int(11) NOT NULL,
  `RoleName` varchar(45) DEFAULT NULL,
  `XmlPath` varchar(500) DEFAULT NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1',
  `hcmoclientid` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `hcmo_role`
--

INSERT INTO `hcmo_role` (`HcmoRoleId`, `RoleName`, `XmlPath`, `Created`, `CreatedBy`, `Updated`, `UpdatedBy`, `IsActive`, `hcmoclientid`) VALUES
(1, 'admin', 'Admin.xml', '2015-12-28', 1, '2015-12-28 09:08:00', 1, 1, 1),
(6, 'Communication', 'Communication.xml', '2016-06-14', 1, '2016-06-14 12:15:13', 1, 0, 0),
(7, 'Developement', 'Developement.xml', '2016-06-14', 1, '2016-06-14 12:00:29', 1, 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_saas_contract`
--

CREATE TABLE `hcmo_saas_contract` (
  `HcmoSaasContractId` int(11) NOT NULL,
  `hcmoclientid` int(11) NOT NULL,
  `SaasName` varchar(255) DEFAULT NULL,
  `SaasType` varchar(255) DEFAULT NULL,
  `SaasSize` int(11) DEFAULT '0',
  `CompanyName` varchar(255) DEFAULT NULL,
  `PersonName` varchar(255) DEFAULT NULL,
  `Designation` varchar(255) DEFAULT NULL,
  `SaasSignDate` datetime DEFAULT NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `hcmo_saas_contract`
--

INSERT INTO `hcmo_saas_contract` (`HcmoSaasContractId`, `hcmoclientid`, `SaasName`, `SaasType`, `SaasSize`, `CompanyName`, `PersonName`, `Designation`, `SaasSignDate`, `Created`, `CreatedBy`, `Updated`, `UpdatedBy`, `IsActive`) VALUES
(1, 0, 'Saas_Contract_localhost_2016-05-30.pdf', 'application/pdf', 4921, 'gits', 'bala', 'dir', '2016-05-30 00:00:00', '2016-05-30', 1, '2016-05-30 12:24:25', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_salary`
--

CREATE TABLE `hcmo_salary` (
  `HcmoSalaryId` int(11) NOT NULL,
  `HcmoEmployeeId` int(11) NOT NULL,
  `DeclarationDate` datetime NOT NULL,
  `Salary` double(13,2) NOT NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1',
  `hcmoclientid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_salary_grade`
--

CREATE TABLE `hcmo_salary_grade` (
  `HcmoSalaryGradeId` int(11) NOT NULL,
  `SalGrdName` varchar(60) NOT NULL,
  `hcmoclientid` int(11) NOT NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `hcmo_salary_grade`
--

INSERT INTO `hcmo_salary_grade` (`HcmoSalaryGradeId`, `SalGrdName`, `hcmoclientid`, `Created`, `CreatedBy`, `Updated`, `UpdatedBy`, `IsActive`) VALUES
(1, 'A-Grade', 1, '2015-12-28', 1, '2015-12-28 12:18:51', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_shift`
--

CREATE TABLE `hcmo_shift` (
  `HcmoShiftTypeId` int(11) NOT NULL,
  `shiftType` varchar(100) DEFAULT NULL,
  `created` date DEFAULT NULL,
  `CreatedBy` int(11) DEFAULT NULL,
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) DEFAULT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_signature`
--

CREATE TABLE `hcmo_signature` (
  `HcmoSignatureId` int(11) NOT NULL,
  `HcmoEmployeeId` int(11) NOT NULL,
  `Signature` text,
  `PreferedSignature` tinyint(1) DEFAULT NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1',
  `hcmoclientid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_sub_category`
--

CREATE TABLE `hcmo_sub_category` (
  `HcmoSubCategoryId` int(11) NOT NULL,
  `HcmoCategoryId` int(11) NOT NULL,
  `SubCategoryName` varchar(100) NOT NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1',
  `hcmoclientid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_support`
--

CREATE TABLE `hcmo_support` (
  `HcmoSupportId` int(11) NOT NULL,
  `hcmoclientid` int(11) NOT NULL,
  `SupportPriority` varchar(60) DEFAULT NULL,
  `Subject` varchar(255) NOT NULL,
  `Message` text,
  `AttachFileName` varchar(100) DEFAULT NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_target`
--

CREATE TABLE `hcmo_target` (
  `HcmoTargetId` int(11) NOT NULL,
  `HcmoProjectId` int(11) NOT NULL,
  `HcmoProjectActivityId` int(11) NOT NULL,
  `HcmoTargetTypeId` int(11) NOT NULL,
  `TargetName` varchar(120) NOT NULL,
  `TargetMode` varchar(120) NOT NULL,
  `TargetValue` varchar(120) NOT NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1',
  `hcmoclientid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_target_type`
--

CREATE TABLE `hcmo_target_type` (
  `HcmoTargetTypeId` int(11) NOT NULL,
  `TargetTypeName` varchar(120) NOT NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1',
  `hcmoclientid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_team`
--

CREATE TABLE `hcmo_team` (
  `HcmoTeamId` int(11) NOT NULL,
  `hcmoclientid` int(11) NOT NULL,
  `TeamName` varchar(120) NOT NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `hcmo_team`
--

INSERT INTO `hcmo_team` (`HcmoTeamId`, `hcmoclientid`, `TeamName`, `Created`, `CreatedBy`, `Updated`, `UpdatedBy`, `IsActive`) VALUES
(1, 1, 'Management', '2015-12-28', 1, '2015-12-28 12:37:37', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_timesheetcat`
--

CREATE TABLE `hcmo_timesheetcat` (
  `hcmo_timesheetcat_id` int(11) NOT NULL,
  `hcmoclientid` int(11) NOT NULL,
  `name` varchar(60) DEFAULT NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_timesheet_notes`
--

CREATE TABLE `hcmo_timesheet_notes` (
  `HcmoTsNotesId` int(11) NOT NULL,
  `HcmoEmployeeId` int(11) NOT NULL,
  `Date` date NOT NULL,
  `Notes` text NOT NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1',
  `hcmoclientid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_ts_achieved_target`
--

CREATE TABLE `hcmo_ts_achieved_target` (
  `HcmoTsAchivedTargetId` int(11) NOT NULL,
  `HcmoTsAssignedTargetId` int(11) NOT NULL,
  `Date` date DEFAULT NULL,
  `StartDate` date DEFAULT NULL,
  `EndDate` date DEFAULT NULL,
  `Status` varchar(100) DEFAULT NULL,
  `TargetAchieved` text,
  `TargetNotes` text,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1',
  `hcmoclientid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_ts_approver`
--

CREATE TABLE `hcmo_ts_approver` (
  `HcmoApproverId` int(11) NOT NULL,
  `HcmoEmployeeId` int(11) NOT NULL,
  `HcmoApprovingEmpId` int(11) NOT NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1',
  `hcmoclientid` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `hcmo_ts_approver`
--

INSERT INTO `hcmo_ts_approver` (`HcmoApproverId`, `HcmoEmployeeId`, `HcmoApprovingEmpId`, `Created`, `CreatedBy`, `Updated`, `UpdatedBy`, `IsActive`, `hcmoclientid`) VALUES
(7, 18, 18, '2016-06-27', 1, '2016-06-27 13:01:47', 1, 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_ts_assign_proj_target`
--

CREATE TABLE `hcmo_ts_assign_proj_target` (
  `HcmoTsAssignProjTargetId` int(11) NOT NULL,
  `HcmoTsEmpProjRelId` int(11) NOT NULL,
  `HcmoTargetId` int(11) DEFAULT NULL,
  `HcmoEmployeeId` int(11) NOT NULL,
  `TargetAchieved` text,
  `TargetNotes` text,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1',
  `hcmoclientid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_ts_attachment`
--

CREATE TABLE `hcmo_ts_attachment` (
  `HcmoTsAttachmentId` int(11) NOT NULL,
  `HcmoEmployeeId` int(11) DEFAULT NULL,
  `HcmoProjectId` int(11) DEFAULT NULL,
  `Year` int(4) DEFAULT NULL,
  `Month` varchar(20) DEFAULT NULL,
  `Week` int(1) DEFAULT NULL,
  `FileName` varchar(250) DEFAULT NULL,
  `Location` varchar(1000) DEFAULT NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1',
  `hcmoclientid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_ts_detail`
--

CREATE TABLE `hcmo_ts_detail` (
  `hcmo_ts_detail` int(11) NOT NULL,
  `employee_id` int(11) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1',
  `hcmoclientid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_ts_emp_proj_rel`
--

CREATE TABLE `hcmo_ts_emp_proj_rel` (
  `HcmoTsEmpProjRelId` int(11) NOT NULL,
  `HcmoEmployeeId` int(11) DEFAULT NULL,
  `HcmoProjectId` int(11) DEFAULT NULL,
  `HcmoProjectActivityId` int(11) DEFAULT NULL,
  `ProjTargetName` varchar(120) DEFAULT NULL,
  `ProjTargetType` varchar(120) DEFAULT NULL,
  `ProjTargetMode` varchar(120) DEFAULT NULL,
  `ProjGoalName` text,
  `ProjectStartDate` date DEFAULT NULL,
  `ProjectEndDate` date DEFAULT NULL,
  `AllocatedHours` varchar(255) DEFAULT NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1',
  `hcmoclientid` int(11) NOT NULL,
  `HcmoCustomerId` int(11) NOT NULL,
  `HcmoDepartmentId` int(11) NOT NULL,
  `ProjectOwnerId` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `isBillable` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `hcmo_ts_emp_proj_rel`
--

INSERT INTO `hcmo_ts_emp_proj_rel` (`HcmoTsEmpProjRelId`, `HcmoEmployeeId`, `HcmoProjectId`, `HcmoProjectActivityId`, `ProjTargetName`, `ProjTargetType`, `ProjTargetMode`, `ProjGoalName`, `ProjectStartDate`, `ProjectEndDate`, `AllocatedHours`, `Created`, `CreatedBy`, `Updated`, `UpdatedBy`, `IsActive`, `hcmoclientid`, `HcmoCustomerId`, `HcmoDepartmentId`, `ProjectOwnerId`, `description`, `isBillable`) VALUES
(5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '444', '2016-06-24', 1, '2016-06-24 08:54:40', 1, 0, 0, 1, 1, 1, 'RERWR', 1),
(6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '66', '2016-06-27', 1, '2016-06-27 03:48:12', 1, 0, 0, 1, 1, 1, '', 1),
(7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '888', '2016-06-27', 1, '2016-06-27 09:33:43', 1, 0, 0, 1, 1, 1, 'uiiuiui', 1),
(8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', '2016-06-27', 1, '2016-06-27 10:18:09', 1, 0, 0, 1, 1, 1, 'asdasdasd', 1),
(9, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '999', '2016-06-27', 1, '2016-06-27 10:21:39', 1, 0, 0, 1, 1, 1, '', 1),
(10, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '999', '2016-06-27', 1, '2016-06-27 10:23:34', 1, 0, 1, 1, 1, 1, 'hhhhh', 1),
(12, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '99', '2016-06-27', 1, '2016-06-27 10:26:10', 1, 0, 1, 1, 1, 1, 'I am billing .................', 0),
(13, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '18', '2016-06-27', 1, '2016-06-27 10:53:04', 1, 0, 1, 1, 1, 1, 'my fix works ???', 1),
(14, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '55', '2016-06-27', 1, '2016-06-27 10:58:14', 1, 0, 1, 1, 1, 1, 'm test is wor', 1),
(15, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', '2016-06-27', 1, '2016-06-27 11:28:03', 1, 0, 0, 1, 1, 1, '', 0),
(16, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '80', '2016-06-27', 1, '2016-06-27 11:29:40', 1, 0, 0, 1, 1, 1, 'adfcadfvad', 1),
(17, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', '2016-06-27', 1, '2016-06-27 11:59:48', 1, 0, 0, 1, 1, 1, '', 0),
(18, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', '2016-06-27', 1, '2016-06-27 12:19:41', 1, 0, 0, 1, 1, 1, '', 0),
(19, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', '2016-06-27', 1, '2016-06-27 14:42:30', 1, 0, 0, 1, 1, 1, '', 0),
(20, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', '2016-06-27', 1, '2016-06-27 14:42:33', 1, 0, 0, 1, 1, 1, '', 0),
(21, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', '2016-06-27', 1, '2016-06-27 14:42:44', 1, 0, 0, 1, 1, 1, '', 0),
(22, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', '2016-06-27', 1, '2016-06-27 15:02:38', 1, 0, 0, 1, 1, 1, '', 0),
(23, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', '2016-06-28', 1, '2016-06-27 23:09:15', 1, 0, 0, 1, 1, 1, '', 0),
(24, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', '2016-06-28', 1, '2016-06-27 23:18:54', 1, 0, 0, 1, 1, 1, '', 0),
(25, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', '2016-06-28', 1, '2016-06-27 23:19:42', 1, 0, 0, 1, 1, 1, '', 0),
(26, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '13:00', '2016-06-28', 1, '2016-06-28 00:00:06', 1, 0, 0, 1, 1, 1, '', 0),
(27, NULL, 1, NULL, NULL, NULL, NULL, NULL, '2016-06-28', '2016-06-30', '09:00', '2016-06-28', 1, '2016-06-28 11:27:03', 1, 0, 0, 1, 1, 1, 'zxcxzcxcx', 0),
(28, NULL, 1, NULL, NULL, NULL, NULL, NULL, '2016-06-02', '2016-06-30', '19:00', '2016-06-28', 1, '2016-06-28 11:31:22', 1, 0, 0, 1, 1, 1, 'dcvdcv', 0);

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_ts_timetrack`
--

CREATE TABLE `hcmo_ts_timetrack` (
  `HcmoEmptimesheetId` int(11) NOT NULL,
  `empid` int(11) NOT NULL,
  `CategoryId` int(11) NOT NULL,
  `ProjectId` int(11) DEFAULT NULL,
  `ActivityId` int(11) DEFAULT NULL,
  `Start` datetime DEFAULT NULL,
  `Stop` datetime DEFAULT NULL,
  `Duration` double NOT NULL DEFAULT '0',
  `CheckIn` tinyint(1) NOT NULL,
  `CheckOut` tinyint(1) NOT NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1',
  `hcmoclientid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_user`
--

CREATE TABLE `hcmo_user` (
  `HcmoUserId` int(11) NOT NULL,
  `HcmoEmployeeId` int(11) NOT NULL,
  `UserName` varchar(120) NOT NULL,
  `Password` varchar(16) NOT NULL,
  `CanCreate` tinyint(1) NOT NULL DEFAULT '0',
  `ForgotPassword` tinyint(1) NOT NULL DEFAULT '0',
  `LastSucessfulLogin` datetime DEFAULT NULL,
  `LastFailureLogin` datetime DEFAULT NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1',
  `hcmoclientid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `hcmo_user`
--

INSERT INTO `hcmo_user` (`HcmoUserId`, `HcmoEmployeeId`, `UserName`, `Password`, `CanCreate`, `ForgotPassword`, `LastSucessfulLogin`, `LastFailureLogin`, `Created`, `CreatedBy`, `Updated`, `UpdatedBy`, `IsActive`, `hcmoclientid`) VALUES
(1, 1, 'admin', 'admin', 0, 0, '2015-12-28 14:37:03', '2015-12-28 14:37:04', '2015-12-28', 1, '2015-12-28 09:07:06', 1, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `hcmo_vendor`
--

CREATE TABLE `hcmo_vendor` (
  `HcmoVendorId` int(11) NOT NULL,
  `hcmoclientid` int(11) NOT NULL,
  `HcmoCountryId` int(11) NOT NULL,
  `TaxId` varchar(30) NOT NULL,
  `VendorName` varchar(100) NOT NULL,
  `VendorShName` varchar(100) DEFAULT NULL,
  `ContactPerson1` varchar(100) NOT NULL,
  `EmailAddress1` varchar(100) NOT NULL,
  `ContactPerson2` varchar(100) NOT NULL,
  `EmailAddress2` varchar(100) NOT NULL,
  `UserName` varchar(60) NOT NULL,
  `Password` varchar(16) NOT NULL,
  `CompanyName` varchar(60) NOT NULL,
  `Address1` varchar(100) DEFAULT NULL,
  `Address2` varchar(100) DEFAULT NULL,
  `State` varchar(60) DEFAULT NULL,
  `City` varchar(100) DEFAULT NULL,
  `ZipCode` varchar(20) DEFAULT NULL,
  `Phone` varchar(20) DEFAULT NULL,
  `Extension` varchar(10) DEFAULT NULL,
  `Fax` varchar(20) DEFAULT NULL,
  `Website` varchar(100) DEFAULT NULL,
  `Custom1` varchar(255) DEFAULT NULL,
  `Custom2` varchar(255) DEFAULT NULL,
  `Created` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `Updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `qr_applicants_addl_info`
--

CREATE TABLE `qr_applicants_addl_info` (
  `QrApplicantsAddlId` int(11) NOT NULL,
  `QrApplicantsBaseId` int(11) NOT NULL,
  `QrEthnicRaceId` int(11) NOT NULL,
  `QrNationalityId` int(11) NOT NULL,
  `WorkAuth` varchar(60) DEFAULT NULL,
  `Reloc` varchar(5) DEFAULT NULL,
  `Reloc1` varchar(60) DEFAULT NULL,
  `Reloc2` varchar(60) DEFAULT NULL,
  `Reloc3` varchar(60) DEFAULT NULL,
  `CreatedDt` datetime DEFAULT NULL,
  `CreatedBy` int(11) DEFAULT NULL,
  `UpdatedDt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) DEFAULT NULL,
  `Isactive` tinyint(1) DEFAULT '1',
  `Smoker` bit(1) DEFAULT NULL,
  `BirthDay` date DEFAULT NULL,
  `MaritalStatus` varchar(20) DEFAULT NULL,
  `LicenseNum` varchar(100) DEFAULT NULL,
  `LicenseDate` date DEFAULT NULL,
  `MilitaryService` varchar(100) DEFAULT NULL,
  `ApplicantsPicturePath` text,
  `SSN` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `qr_applicants_attach`
--

CREATE TABLE `qr_applicants_attach` (
  `QrapplicantsAttachId` int(11) NOT NULL,
  `QrApplicantsBaseId` int(11) NOT NULL,
  `ApplicantsAttachName` varchar(255) NOT NULL,
  `ApplicantsAttachType` varchar(255) DEFAULT NULL,
  `ApplicantsAttachSize` int(11) NOT NULL,
  `CreatedDt` datetime DEFAULT NULL,
  `CreatedBy` int(11) DEFAULT NULL,
  `UpdatedDt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) DEFAULT NULL,
  `Isactive` tinyint(1) DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `qr_applicants_base_info`
--

CREATE TABLE `qr_applicants_base_info` (
  `QrApplicantsBaseId` int(11) NOT NULL,
  `QrVendorBaseId` int(11) DEFAULT NULL,
  `Salutation` varchar(20) DEFAULT NULL,
  `ApplicantsFName` varchar(150) NOT NULL,
  `ApplicantsMName` varchar(150) DEFAULT NULL,
  `ApplicantsLName` varchar(150) DEFAULT NULL,
  `ApplicantsDob` date DEFAULT NULL,
  `NickName` varchar(150) DEFAULT NULL,
  `TotYrExp` varchar(150) DEFAULT NULL,
  `Address1` varchar(150) DEFAULT NULL,
  `Address2` varchar(150) DEFAULT NULL,
  `QrCountryId` int(11) NOT NULL,
  `City` varchar(60) DEFAULT NULL,
  `State` varchar(60) DEFAULT NULL,
  `Postalcode` varchar(20) DEFAULT NULL,
  `PhoneWorking` varchar(100) DEFAULT NULL,
  `PhoneWorkingExt` varchar(10) DEFAULT NULL,
  `PhoneWorkAdd` varchar(20) DEFAULT NULL,
  `PhoneHome` varchar(20) DEFAULT NULL,
  `PhoneMobile` varchar(20) DEFAULT NULL,
  `ApplicantsPriEmailId` varchar(200) NOT NULL,
  `ApplicantsSecondaryEmail` varchar(200) DEFAULT NULL,
  `ReferredByName` varchar(60) DEFAULT NULL,
  `Rate` varchar(20) DEFAULT NULL,
  `RateOption` varchar(20) DEFAULT NULL,
  `VendorType` varchar(20) DEFAULT NULL,
  `RatePerHr` double DEFAULT NULL,
  `Isexternal` tinyint(1) DEFAULT '0',
  `PrivilegeType` varchar(100) DEFAULT 'public',
  `TextResume` text,
  `TitleKeyword` varchar(150) DEFAULT NULL,
  `BulkMailPreference` varchar(10) DEFAULT NULL,
  `CreatedDt` datetime DEFAULT NULL,
  `CreatedBy` int(11) DEFAULT NULL,
  `UpdatedDt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) DEFAULT NULL,
  `Isactive` tinyint(1) DEFAULT '1',
  `QrPrivilegeId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `qr_applicants_broadcast`
--

CREATE TABLE `qr_applicants_broadcast` (
  `QrApplicantBroadcastId` int(11) NOT NULL,
  `QrHrUserId` int(11) NOT NULL,
  `QrRequirementId` int(11) NOT NULL,
  `QrApplicantsBaseId` int(11) NOT NULL,
  `CreatedDt` datetime NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `UpdatedDt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `qr_applicants_formatted_attach`
--

CREATE TABLE `qr_applicants_formatted_attach` (
  `QrApplicantsFormatAttachId` int(11) NOT NULL,
  `QrApplicantsBaseId` int(11) NOT NULL,
  `ApplicantsFormatAttachName` varchar(255) NOT NULL,
  `ApplicantsFormatAttachType` varchar(255) DEFAULT NULL,
  `ApplicantsFormatAttachSize` int(11) NOT NULL,
  `CreatedDt` datetime DEFAULT NULL,
  `CreatedBy` int(11) DEFAULT NULL,
  `UpdatedDt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) DEFAULT NULL,
  `Isactive` tinyint(1) DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `qr_applicants_notes`
--

CREATE TABLE `qr_applicants_notes` (
  `QrApplicantsNotesId` int(11) NOT NULL,
  `QrHrUserId` int(11) NOT NULL,
  `QrApplicantsBaseId` int(11) NOT NULL,
  `QrRequirementId` int(11) NOT NULL,
  `Notes` text,
  `NotesType` varchar(50) NOT NULL,
  `CallogTo` text,
  `CallogTime` datetime NOT NULL,
  `IsApplicantVisible` tinyint(1) DEFAULT '0',
  `NotesOwner` tinyint(1) DEFAULT '0',
  `CreatedDt` datetime DEFAULT NULL,
  `CreatedBy` int(11) DEFAULT NULL,
  `UpdatedDt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) DEFAULT NULL,
  `Isactive` tinyint(1) DEFAULT '1',
  `ApplicantsNotes` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `qr_applicants_notes_attach`
--

CREATE TABLE `qr_applicants_notes_attach` (
  `QrApplicantsNotesAttachId` int(11) NOT NULL,
  `QrApplicantsNotesId` int(11) NOT NULL,
  `ApplicantsNotesAttachName` varchar(255) NOT NULL,
  `ApplicantsNotesAttachType` varchar(255) DEFAULT NULL,
  `ApplicantsNotesAttachSize` int(11) NOT NULL,
  `CreatedDt` datetime NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `UpdatedDt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `Isactive` tinyint(1) NOT NULL DEFAULT '1',
  `IsApplicantAttach` smallint(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `qr_applicants_privilege_current`
--

CREATE TABLE `qr_applicants_privilege_current` (
  `QrApplicantsPrivileveCurrentId` int(11) NOT NULL,
  `QrApplicantsBaseId` int(11) NOT NULL,
  `PrivHrUserId` int(11) DEFAULT NULL,
  `PrivSharedHrUserId` varchar(20) DEFAULT NULL,
  `PrivilegeType` varchar(100) DEFAULT NULL,
  `CreatedDt` datetime NOT NULL,
  `CreatedBy` int(11) DEFAULT NULL,
  `UpdatedDt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) DEFAULT NULL,
  `IsActive` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `qr_applicants_privilege_history`
--

CREATE TABLE `qr_applicants_privilege_history` (
  `QrPrivilegeHistoryId` int(11) NOT NULL,
  `QrApplicantsBaseId` int(11) NOT NULL,
  `QrRequirementId` int(11) DEFAULT NULL,
  `QrHrUserId` int(11) DEFAULT NULL,
  `PrivilegeType` varchar(100) DEFAULT NULL,
  `CreatedDt` datetime DEFAULT NULL,
  `CreatedBy` int(11) DEFAULT NULL,
  `UpdatedDt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) DEFAULT NULL,
  `Isactive` tinyint(1) DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `qr_applicants_req_mail`
--

CREATE TABLE `qr_applicants_req_mail` (
  `QrApplicantsReqMailId` int(11) NOT NULL,
  `QrApplicantsBaseId` int(11) NOT NULL,
  `QrRequirementId` int(11) NOT NULL,
  `ApplicantsReqMailSubject` varchar(100) DEFAULT NULL,
  `ApplicantsReqMailMessage` text,
  `MailSendTime` datetime DEFAULT NULL,
  `CreatedDt` datetime NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `UpdatedDt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `qr_applicants_skill_matrix`
--

CREATE TABLE `qr_applicants_skill_matrix` (
  `QrApplicantsSkillMatrixId` int(11) NOT NULL,
  `QrApplicantsBaseId` int(11) NOT NULL,
  `SkillName` varchar(60) NOT NULL,
  `LastUsed` varchar(20) NOT NULL,
  `Proficiency` varchar(20) NOT NULL,
  `CreatedDt` datetime DEFAULT NULL,
  `CreatedBy` int(11) DEFAULT NULL,
  `UpdatedDt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) DEFAULT NULL,
  `Isactive` tinyint(1) DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `qr_applicants_submission`
--

CREATE TABLE `qr_applicants_submission` (
  `QrApplicantsSubmissionId` int(11) NOT NULL,
  `QrApplicantsBaseId` int(11) NOT NULL,
  `QrHrUserId` int(11) NOT NULL,
  `QrApplicantsNotesId` int(11) NOT NULL,
  `QrRequirementId` int(11) NOT NULL,
  `QrVendorBaseId` int(11) NOT NULL,
  `QrApplicationReceivedId` int(11) DEFAULT NULL,
  `SubmittedDate` datetime DEFAULT NULL,
  `ApplicantsApproved` tinyint(1) DEFAULT '0',
  `LeadApproved` tinyint(1) DEFAULT '0',
  `SubmitToLead` tinyint(1) DEFAULT '0',
  `SubmitToClient` tinyint(1) DEFAULT '0',
  `SubmitToLeadIds` varchar(50) DEFAULT NULL,
  `ApprovedLeadId` varchar(10) DEFAULT NULL,
  `DirectSubmitToClient` tinyint(1) NOT NULL DEFAULT '0',
  `CreatedDt` datetime NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `UpdatedDt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `qr_applicants_trai_cert`
--

CREATE TABLE `qr_applicants_trai_cert` (
  `QrApplicantsTraiCertId` int(11) NOT NULL,
  `QrApplicantsBaseId` int(11) NOT NULL,
  `TraiCertName` varchar(60) NOT NULL,
  `TraiCertType` varchar(20) NOT NULL,
  `TraiCertDescription` text,
  `TraiCertLevel` varchar(20) NOT NULL,
  `TraiCertQualifiedDate` date DEFAULT NULL,
  `TraiCertValidUpto` date DEFAULT NULL,
  `CreatedDt` datetime NOT NULL,
  `CreatedBy` int(11) DEFAULT NULL,
  `UpdatedDt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) DEFAULT NULL,
  `Isactive` tinyint(1) DEFAULT '1',
  `JoinedDate` date DEFAULT NULL,
  `TerminateDate` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `qr_application_received`
--

CREATE TABLE `qr_application_received` (
  `QrApplicationReceivedId` int(11) NOT NULL,
  `QrApplicantsBaseId` int(11) NOT NULL,
  `QrRequirementId` int(11) NOT NULL,
  `ApplicationAppliedDate` date DEFAULT NULL,
  `ReqApply` tinyint(1) DEFAULT '0',
  `CreatedDt` datetime NOT NULL,
  `CreatedBy` int(11) DEFAULT NULL,
  `UpdatedDt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) DEFAULT NULL,
  `IsActive` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `qr_assigned_requirement`
--

CREATE TABLE `qr_assigned_requirement` (
  `QrAssignedRequirementId` int(11) NOT NULL,
  `QrRequirementId` int(11) NOT NULL,
  `AssignToId` int(11) NOT NULL,
  `AssignById` int(11) NOT NULL,
  `AssignDate` date NOT NULL,
  `AssignedComments` text,
  `DeAssignFlag` tinyint(1) DEFAULT '1',
  `CreatedDt` datetime NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `UpdatedDt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `Isactive` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `qr_calendar`
--

CREATE TABLE `qr_calendar` (
  `EventID` int(10) NOT NULL,
  `UserID` int(10) DEFAULT NULL,
  `JobId` varchar(20) DEFAULT NULL,
  `QrApplicantName` varchar(150) DEFAULT NULL,
  `QrClientName` varchar(150) NOT NULL,
  `StartDate` datetime DEFAULT NULL,
  `EndDate` datetime DEFAULT NULL,
  `EventName` varchar(30) DEFAULT NULL,
  `Description` varchar(150) DEFAULT NULL,
  `Category` varchar(20) DEFAULT NULL,
  `IsRecEvent` tinyint(1) DEFAULT NULL,
  `Timezone` varchar(150) DEFAULT NULL,
  `Created` datetime DEFAULT NULL,
  `CreatedBy` int(11) DEFAULT NULL,
  `Updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) DEFAULT NULL,
  `IsActive` tinyint(1) DEFAULT '1',
  `QrApplicantsBaseId` int(11) DEFAULT NULL,
  `MailTo` varchar(60) DEFAULT NULL,
  `MailCC` varchar(60) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `qr_city`
--

CREATE TABLE `qr_city` (
  `QrCityId` int(11) NOT NULL,
  `Name` varchar(50) DEFAULT NULL,
  `QrStateId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `qr_client_base_info`
--

CREATE TABLE `qr_client_base_info` (
  `QrClientBaseId` int(11) NOT NULL,
  `QrClientName` varchar(150) NOT NULL,
  `QrClientShName` varchar(150) DEFAULT NULL,
  `Address1` varchar(150) DEFAULT NULL,
  `Address2` varchar(150) DEFAULT NULL,
  `QrCountryId` int(11) NOT NULL,
  `City` varchar(60) DEFAULT NULL,
  `State` varchar(60) DEFAULT NULL,
  `PostalCode` varchar(20) DEFAULT NULL,
  `Fax` varchar(20) DEFAULT NULL,
  `Website` varchar(100) DEFAULT NULL,
  `Industry` varchar(60) DEFAULT NULL,
  `BusinessCategory` varchar(60) DEFAULT NULL,
  `client_contract` tinyint(1) DEFAULT '0',
  `Owner` tinyint(1) NOT NULL DEFAULT '0',
  `CreatedDt` datetime NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `UpdatedDt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `Isactive` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `qr_client_base_info`
--

INSERT INTO `qr_client_base_info` (`QrClientBaseId`, `QrClientName`, `QrClientShName`, `Address1`, `Address2`, `QrCountryId`, `City`, `State`, `PostalCode`, `Fax`, `Website`, `Industry`, `BusinessCategory`, `client_contract`, `Owner`, `CreatedDt`, `CreatedBy`, `UpdatedDt`, `UpdatedBy`, `Isactive`) VALUES
(1, 'localhost', NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, '2016-05-28 00:00:00', 1, '2016-05-28 11:57:29', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `qr_client_broadcast`
--

CREATE TABLE `qr_client_broadcast` (
  `QrDirectClientId` int(11) NOT NULL,
  `QrApplicantsBaseId` int(11) NOT NULL,
  `QrClientBaseId` int(11) NOT NULL,
  `CreatedDt` datetime NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `UpdatedDt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `qr_client_contact_info`
--

CREATE TABLE `qr_client_contact_info` (
  `QrClientContactId` int(11) NOT NULL,
  `QrClientBaseId` int(11) DEFAULT NULL,
  `Salutation` varchar(20) DEFAULT NULL,
  `ContFName` varchar(150) NOT NULL,
  `ContMName` varchar(150) DEFAULT NULL,
  `ContLName` varchar(150) NOT NULL,
  `Phone` varchar(20) DEFAULT NULL,
  `PhoneWorkExt` varchar(10) DEFAULT NULL,
  `PhoneWorkAdd` varchar(20) DEFAULT NULL,
  `PhoneMob` varchar(20) DEFAULT NULL,
  `ClientPriMailId` varchar(100) NOT NULL,
  `ClientSecMailId` varchar(100) DEFAULT NULL,
  `CreatedDt` datetime NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `UpdatedDt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `Isactive` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `qr_client_notes`
--

CREATE TABLE `qr_client_notes` (
  `QrClientNotesId` int(11) NOT NULL,
  `QrClientBaseId` int(11) NOT NULL,
  `QrHrUserId` int(11) NOT NULL,
  `QrClientContactId` int(11) DEFAULT NULL,
  `ClientComments` text NOT NULL,
  `CreatedDt` datetime NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `UpdatedDt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `Isactive` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `qr_client_submission`
--

CREATE TABLE `qr_client_submission` (
  `QrClientSubmissionId` int(11) NOT NULL,
  `QrClientBaseId` int(11) DEFAULT NULL,
  `QrApplicantsBaseId` int(11) DEFAULT NULL,
  `QrHrUserId` int(11) DEFAULT NULL,
  `QrRequirementId` int(11) DEFAULT NULL,
  `QrApplicationReceivedId` int(11) DEFAULT NULL,
  `ClientContactIds` varchar(500) DEFAULT NULL,
  `ClientContactEmailIds` varchar(500) DEFAULT NULL,
  `SubmissionDate` date DEFAULT NULL,
  `SubmissionRate` double DEFAULT NULL,
  `SubmissionCount` int(11) DEFAULT '0',
  `ResendCount` int(11) DEFAULT '0',
  `ForwardCount` int(11) DEFAULT '0',
  `CreatedDt` datetime NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `UpdatedDt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `qr_corp_corp_feed_jobs`
--

CREATE TABLE `qr_corp_corp_feed_jobs` (
  `QrCorpCorpFeedJobsId` int(11) NOT NULL,
  `QrRequirementId` int(11) NOT NULL,
  `QrHrUserId` int(11) NOT NULL,
  `JobFeedDate` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `UpdatedBy` int(11) NOT NULL,
  `CreatedDt` date DEFAULT NULL,
  `UpdatedDt` datetime DEFAULT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `qr_country`
--

CREATE TABLE `qr_country` (
  `QrCountryId` int(11) NOT NULL,
  `Name` varchar(100) NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `qr_country`
--

INSERT INTO `qr_country` (`QrCountryId`, `Name`, `IsActive`) VALUES
(1, 'USA', 1),
(2, 'Canada', 1),
(3, 'India', 1),
(4, 'Albania', 1),
(5, 'Angola', 1),
(6, 'Austria', 1),
(7, 'Belarus', 1),
(8, 'Belgium', 1),
(9, 'Bosnia and Herzegovina', 1),
(10, 'Bulgaria', 1),
(11, 'Croatia', 1),
(12, 'Cyprus', 1),
(13, 'Czeck Republic', 1),
(14, 'Denmark', 1),
(15, 'Estonia', 1),
(16, 'Finland', 1),
(17, 'France', 1),
(18, 'Germany', 1),
(19, 'Greece', 1),
(20, 'Hungary', 1),
(21, 'Iceland', 1),
(22, 'Ireland', 1),
(23, 'Italy', 1),
(24, 'Latvia', 1),
(25, 'Liechtenstein', 1),
(26, 'Lithuania', 1),
(27, 'Luxembourg', 1),
(28, 'Macedonia', 1),
(29, 'Malta', 1),
(30, 'Moldova', 1),
(31, 'Monaco', 1),
(32, 'Netherlands', 1),
(33, 'Norway', 1),
(34, 'Poland', 1),
(35, 'Portugal', 1),
(36, 'Romainia', 1),
(37, 'Russia', 1),
(38, 'San Marino', 1),
(39, 'Slovakia', 1),
(40, 'Slovenia', 1),
(41, 'Spain', 1),
(42, 'Sweden', 1),
(43, 'Switzerland', 1),
(44, 'Turkey', 1),
(45, 'Ukraine', 1),
(46, 'United Kingdom', 1),
(47, 'Vatican City', 1);

-- --------------------------------------------------------

--
-- Table structure for table `qr_educational_qualification`
--

CREATE TABLE `qr_educational_qualification` (
  `QrQualificationId` int(11) NOT NULL,
  `QualificationName` varchar(60) NOT NULL,
  `CreatedDt` datetime NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `UpdatedDt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `Isactive` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `qr_email_reader_configuration`
--

CREATE TABLE `qr_email_reader_configuration` (
  `QrEmailReaderConfigurationId` int(11) NOT NULL,
  `EmailReaderHostName` varchar(255) NOT NULL,
  `EmailReaderHostServerType` varchar(50) NOT NULL,
  `EmailReaderUserId` varchar(255) DEFAULT NULL,
  `EmailReaderPassword` varchar(255) NOT NULL,
  `EmailReaderEmailId` varchar(255) NOT NULL,
  `EmailReaderType` varchar(50) NOT NULL,
  `CreatedDt` datetime NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `UpdatedDt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `Isactive` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `qr_email_send_to_client`
--

CREATE TABLE `qr_email_send_to_client` (
  `QrEmailSendToClientId` int(11) NOT NULL,
  `SubmissionMailedBy` int(11) DEFAULT NULL,
  `SubmissionMailedTo` varchar(500) DEFAULT NULL,
  `SubMailCc` varchar(100) DEFAULT NULL,
  `SubMailBcc` varchar(400) DEFAULT NULL,
  `SubMailSubject` varchar(500) DEFAULT NULL,
  `SubMailMessage` text,
  `MailSignature` varchar(500) DEFAULT NULL,
  `MailAttachName` varchar(60) DEFAULT NULL,
  `SubMailResumeAttached` tinyint(1) NOT NULL,
  `SkillDetails` tinyint(1) NOT NULL,
  `CreatedDt` datetime NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `UpdatedDt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL,
  `QrClientSubmissionId` int(11) DEFAULT NULL,
  `SubmissionType` varchar(50) NOT NULL DEFAULT 'SUBMISSION'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `qr_ethnic_race`
--

CREATE TABLE `qr_ethnic_race` (
  `QrEthnicRaceId` int(11) NOT NULL,
  `EthnicRaceDesc` varchar(255) NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1',
  `CreatedDt` date DEFAULT NULL,
  `UpdatedDt` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `qr_hr_user_info`
--

CREATE TABLE `qr_hr_user_info` (
  `QrHrUserId` int(11) NOT NULL,
  `QrRoleId` int(11) NOT NULL,
  `HrUserFName` varchar(150) NOT NULL,
  `HrUserMName` varchar(150) DEFAULT NULL,
  `HrUserLName` varchar(150) NOT NULL,
  `Salutation` varchar(20) NOT NULL,
  `Address1` varchar(150) NOT NULL,
  `Address2` varchar(150) DEFAULT NULL,
  `QrCountryId` int(11) NOT NULL,
  `City` varchar(60) NOT NULL,
  `State` varchar(60) NOT NULL,
  `PostalCode` varchar(20) NOT NULL,
  `PhoneWork` varchar(20) NOT NULL,
  `PhoneWorkExt` varchar(10) DEFAULT NULL,
  `PhoneWorkAdd` varchar(20) DEFAULT NULL,
  `PhoneMob` varchar(20) DEFAULT NULL,
  `HrUserPriEmail` varchar(100) NOT NULL,
  `HrUserSecEmail` varchar(100) DEFAULT NULL,
  `ReportingTo` varchar(50) DEFAULT NULL,
  `HrUserJobTitle` varchar(60) DEFAULT NULL,
  `JoinedDate` date NOT NULL,
  `EmergencyContactName` varchar(60) DEFAULT NULL,
  `EmergencyContactNum` varchar(50) DEFAULT NULL,
  `TerminateDate` date DEFAULT NULL,
  `TotYearExp` varchar(20) NOT NULL,
  `IsCustomer` tinyint(1) NOT NULL DEFAULT '0',
  `CreatedDt` datetime NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `UpdatedDt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `Isactive` tinyint(1) NOT NULL DEFAULT '1',
  `BccMail` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `qr_hr_user_info`
--

INSERT INTO `qr_hr_user_info` (`QrHrUserId`, `QrRoleId`, `HrUserFName`, `HrUserMName`, `HrUserLName`, `Salutation`, `Address1`, `Address2`, `QrCountryId`, `City`, `State`, `PostalCode`, `PhoneWork`, `PhoneWorkExt`, `PhoneWorkAdd`, `PhoneMob`, `HrUserPriEmail`, `HrUserSecEmail`, `ReportingTo`, `HrUserJobTitle`, `JoinedDate`, `EmergencyContactName`, `EmergencyContactNum`, `TerminateDate`, `TotYearExp`, `IsCustomer`, `CreatedDt`, `CreatedBy`, `UpdatedDt`, `UpdatedBy`, `Isactive`, `BccMail`) VALUES
(1, 2, 'Admin', 'Admin', 'Admin', 'Mr', 'NA', 'NA', 1, 'NA', 'NA', '123456', '1234567890', '', '', '', 'username@domainname.com', '', '1', '', '2012-04-01', '', '', NULL, '0', 0, '2012-01-31 00:00:00', 1, '2012-02-06 20:37:51', 1, 1, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `qr_hr_user_login`
--

CREATE TABLE `qr_hr_user_login` (
  `QrHrUserLoginId` int(11) NOT NULL,
  `QrHrUserId` int(11) NOT NULL,
  `UserName` varchar(60) NOT NULL,
  `Password` varchar(60) NOT NULL,
  `ForgetPassword` tinyint(1) DEFAULT '0',
  `CreatedDt` datetime DEFAULT NULL,
  `CreatedBy` int(11) DEFAULT NULL,
  `UpdatedDt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) DEFAULT NULL,
  `Isactive` tinyint(1) DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `qr_hr_user_login`
--

INSERT INTO `qr_hr_user_login` (`QrHrUserLoginId`, `QrHrUserId`, `UserName`, `Password`, `ForgetPassword`, `CreatedDt`, `CreatedBy`, `UpdatedDt`, `UpdatedBy`, `Isactive`) VALUES
(1, 1, 'admin', 'admin@12', 0, '2011-01-20 12:48:21', 1, '2012-01-13 04:22:45', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `qr_idiligo_config`
--

CREATE TABLE `qr_idiligo_config` (
  `QrIdiligoId` int(11) NOT NULL,
  `IdiligoUserName` varchar(255) DEFAULT NULL,
  `IdiligoPassword` varchar(255) DEFAULT NULL,
  `CreatedDt` date DEFAULT NULL,
  `UpdatedDt` datetime DEFAULT NULL,
  `IsActive` smallint(6) DEFAULT NULL,
  `CreatedBy` int(11) DEFAULT NULL,
  `UpdatedBy` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `qr_import_resumes`
--

CREATE TABLE `qr_import_resumes` (
  `QrImportResumesId` int(11) NOT NULL,
  `QrHrUserId` int(11) DEFAULT NULL,
  `TextResume` text,
  `ImpFolderName` varchar(60) NOT NULL,
  `ResumeName` varchar(50) DEFAULT NULL,
  `CreatedDt` datetime NOT NULL,
  `CreatedBy` int(11) DEFAULT NULL,
  `UpdatedDt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) DEFAULT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `qr_jobcategory_info`
--

CREATE TABLE `qr_jobcategory_info` (
  `QrJobCategoryId` int(11) NOT NULL,
  `JobCategoryName` varchar(100) NOT NULL,
  `Isactive` tinyint(1) DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `qr_job_board_config`
--

CREATE TABLE `qr_job_board_config` (
  `QrJobBoardConfigId` int(11) NOT NULL,
  `QrHrUserId` int(11) NOT NULL,
  `JobBoardUserName` varchar(255) NOT NULL,
  `JobBoardPassword` varchar(255) NOT NULL,
  `JobBoardName` varchar(255) NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `UpdatedBy` int(11) NOT NULL,
  `CreatedDt` datetime NOT NULL,
  `UpdatedDt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `qr_job_board_config_corp_corp`
--

CREATE TABLE `qr_job_board_config_corp_corp` (
  `QrJobBoardConfigCorpCorpId` int(11) NOT NULL,
  `QrHrUserId` int(11) NOT NULL,
  `CorpCorpUserName` varchar(255) NOT NULL,
  `CorpCorpPassword` varchar(255) NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `UpdatedBy` int(11) NOT NULL,
  `CreatedDt` date DEFAULT NULL,
  `UpdatedDt` datetime DEFAULT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `qr_job_board_config_indeed`
--

CREATE TABLE `qr_job_board_config_indeed` (
  `QrJobBoardConfigIndeedId` int(11) NOT NULL,
  `QrHrUserId` int(11) NOT NULL,
  `IndeedUserName` varchar(255) NOT NULL,
  `IndeedPassword` varchar(255) NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `UpdatedBy` int(11) NOT NULL,
  `CreatedDt` date DEFAULT NULL,
  `UpdatedDt` datetime DEFAULT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `qr_job_board_post_jobs`
--

CREATE TABLE `qr_job_board_post_jobs` (
  `QrJobBoardPostJobsId` int(11) NOT NULL,
  `QrHrUserId` int(11) NOT NULL,
  `QrRequirementId` int(11) NOT NULL,
  `JobBoardName` varchar(255) NOT NULL,
  `JobPostDate` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `UpdatedBy` int(11) NOT NULL,
  `CreatedDt` datetime NOT NULL,
  `UpdatedDt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `qr_location`
--

CREATE TABLE `qr_location` (
  `QrLocationId` int(11) NOT NULL,
  `QrCountryId` int(11) NOT NULL,
  `Name` varchar(150) NOT NULL,
  `Address1` varchar(150) NOT NULL,
  `Address2` varchar(150) DEFAULT NULL,
  `City` varchar(60) NOT NULL,
  `Region` varchar(60) NOT NULL,
  `PostalCode` varchar(20) NOT NULL,
  `Phone` varchar(20) NOT NULL,
  `Fax` varchar(20) DEFAULT NULL,
  `Comments` text,
  `CreatedDt` datetime NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `UpdatedDt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `Isactive` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `qr_logo`
--

CREATE TABLE `qr_logo` (
  `QrClientLogoId` int(11) NOT NULL,
  `ClientLogoName` varchar(255) NOT NULL,
  `ClientLogoType` varchar(255) NOT NULL,
  `ClientLogoSize` int(11) NOT NULL,
  `CreatedDt` datetime NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `UpdatedDt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `qr_mail_configuration`
--

CREATE TABLE `qr_mail_configuration` (
  `QrMailConfigurationId` int(11) NOT NULL,
  `QrHrUserId` int(11) NOT NULL,
  `SmtpHost` varchar(255) DEFAULT NULL,
  `UserName` varchar(255) DEFAULT NULL,
  `Password` varchar(255) DEFAULT NULL,
  `FromMailId` varchar(255) DEFAULT NULL,
  `CreatedDt` date NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `UpdatedDt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1',
  `EnableSSL` tinyint(1) NOT NULL DEFAULT '0',
  `Port` int(5) NOT NULL DEFAULT '25'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `qr_nationality`
--

CREATE TABLE `qr_nationality` (
  `QrNationalityId` int(11) NOT NULL,
  `NatName` varchar(120) NOT NULL,
  `CreatedDt` datetime NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `UpdatedDt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `qr_onboarded_attach`
--

CREATE TABLE `qr_onboarded_attach` (
  `QrOnBoardedAttachId` int(11) NOT NULL,
  `OnBoardedApplicantAttachName` varchar(255) DEFAULT NULL,
  `OnBoardedApplicantAttachType` varchar(60) DEFAULT NULL,
  `OnBoardedApplicantAttachSize` bigint(20) DEFAULT NULL,
  `CreatedDt` date DEFAULT NULL,
  `UpdatedDt` datetime DEFAULT NULL,
  `IsActive` smallint(6) DEFAULT NULL,
  `QrApplicantNotesId` int(11) DEFAULT NULL,
  `CreatedBy` int(11) DEFAULT NULL,
  `UpdatedBy` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `qr_on_off_board_applicants`
--

CREATE TABLE `qr_on_off_board_applicants` (
  `QrOnOffBoardApplId` int(11) NOT NULL,
  `DateOnBoard` date DEFAULT NULL,
  `DateOffBoard` date DEFAULT NULL,
  `OnOffStatus` varchar(60) DEFAULT NULL,
  `CreatedDt` date DEFAULT NULL,
  `UpdatedDt` datetime DEFAULT NULL,
  `IsActive` smallint(6) DEFAULT NULL,
  `QrApplicantsBaseId` int(11) DEFAULT NULL,
  `QrRequirementId` int(11) DEFAULT NULL,
  `QrHrUserId` int(11) DEFAULT NULL,
  `CreatedBy` int(11) DEFAULT NULL,
  `UpdatedBy` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `qr_org`
--

CREATE TABLE `qr_org` (
  `QrOrgId` int(11) NOT NULL,
  `ParentOrgId` int(11) DEFAULT NULL,
  `Name` varchar(150) DEFAULT NULL,
  `Description` text,
  `CreatedDt` date DEFAULT NULL,
  `UpdatedDt` datetime DEFAULT NULL,
  `IsActive` smallint(6) DEFAULT NULL,
  `CreatedBy` int(11) DEFAULT NULL,
  `UpdatedBy` int(11) DEFAULT NULL,
  `QrLocationId` int(11) DEFAULT NULL,
  `QrOrgTypeId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `qr_org_type`
--

CREATE TABLE `qr_org_type` (
  `QrOrgTypeId` int(11) NOT NULL,
  `Name` varchar(150) NOT NULL,
  `Description` text,
  `CreatedDt` datetime NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `UpdatedDt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `qr_privilege`
--

CREATE TABLE `qr_privilege` (
  `QrPrivilegeId` int(11) NOT NULL,
  `PrivilegeName` varchar(255) DEFAULT NULL,
  `CreatedDt` date DEFAULT NULL,
  `UpdatedDt` datetime DEFAULT NULL,
  `IsActive` smallint(6) DEFAULT NULL,
  `CreatedBy` int(11) DEFAULT NULL,
  `UpdatedBy` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `qr_requirement`
--

CREATE TABLE `qr_requirement` (
  `QrRequirementId` int(11) NOT NULL,
  `QrClientBaseId` int(11) DEFAULT NULL,
  `ReqDate` date NOT NULL,
  `JobId` varchar(20) DEFAULT NULL,
  `TempJobId` varchar(20) DEFAULT NULL,
  `JobTitle` varchar(60) NOT NULL,
  `QrCountryId` int(11) NOT NULL,
  `City` varchar(60) NOT NULL,
  `State` varchar(60) NOT NULL,
  `PostalCode` varchar(20) NOT NULL,
  `ProjectDuration` varchar(20) NOT NULL,
  `NoOfOpening` varchar(20) NOT NULL,
  `Rate` varchar(20) DEFAULT NULL,
  `RateOption` varchar(20) DEFAULT NULL,
  `RatePerHr` double DEFAULT NULL,
  `Critical` varchar(60) DEFAULT NULL,
  `Experience` varchar(20) DEFAULT NULL,
  `WorkType` varchar(60) NOT NULL DEFAULT '',
  `WorkStatus` varchar(60) NOT NULL DEFAULT '',
  `JobType` varchar(60) NOT NULL DEFAULT '',
  `EmploymentType` varchar(60) NOT NULL DEFAULT '',
  `SubmittedBy` varchar(120) NOT NULL DEFAULT '',
  `Email` varchar(120) NOT NULL DEFAULT '',
  `ContactNo` varchar(60) NOT NULL DEFAULT '',
  `SkillSet` text,
  `OptionalSkills` text,
  `AdditionalNotes` text,
  `ProjectDesc` text,
  `sglassdoor` tinyint(1) DEFAULT '0',
  `CreatedDt` datetime NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `UpdatedDt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `Isactive` tinyint(1) NOT NULL DEFAULT '1',
  `Priority` varchar(60) DEFAULT NULL,
  `Status` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `qr_req_attach`
--

CREATE TABLE `qr_req_attach` (
  `QrReqAttachId` int(11) NOT NULL,
  `QrAttachName` varchar(255) DEFAULT NULL,
  `QrReqAttachType` varchar(255) DEFAULT NULL,
  `QrReqAttachSize` bigint(20) DEFAULT NULL,
  `CreatedDt` date DEFAULT NULL,
  `UpdatedDt` datetime DEFAULT NULL,
  `IsActive` smallint(6) DEFAULT NULL,
  `QrRequirementId` int(11) DEFAULT NULL,
  `CreatedBy` int(11) DEFAULT NULL,
  `UpdatedBy` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `qr_resume_parser`
--

CREATE TABLE `qr_resume_parser` (
  `QrResumeParserId` int(11) NOT NULL,
  `QrImportResumesId` int(11) DEFAULT NULL,
  `FirstName` varchar(150) DEFAULT NULL,
  `Middlename` varchar(150) DEFAULT NULL,
  `LastName` varchar(150) DEFAULT NULL,
  `Email` varchar(100) DEFAULT NULL,
  `Phone` varchar(150) DEFAULT NULL,
  `Address` varchar(150) DEFAULT NULL,
  `City` varchar(60) DEFAULT NULL,
  `State` varchar(60) DEFAULT NULL,
  `ZipCode` varchar(20) DEFAULT NULL,
  `Category` varchar(60) DEFAULT NULL,
  `DateOfBirth` datetime DEFAULT NULL,
  `CurrentEmployer` text,
  `JobProfile` text,
  `WorkedPeriod` varchar(255) DEFAULT NULL,
  `Gender` varchar(60) DEFAULT NULL,
  `FatherName` varchar(60) DEFAULT NULL,
  `MaritalStatus` varchar(60) DEFAULT NULL,
  `PassportNo` varchar(60) DEFAULT NULL,
  `Nationality` varchar(255) DEFAULT NULL,
  `CurrentSalary` mediumtext,
  `ExpectedSalary` mediumtext,
  `Qualification` mediumtext,
  `Skills` mediumtext,
  `Experience` mediumtext,
  `DetailResume` longtext,
  `CreatedDt` datetime DEFAULT NULL,
  `CreatedBy` int(11) DEFAULT NULL,
  `UpdatedDt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) DEFAULT NULL,
  `Isactive` tinyint(1) DEFAULT '1',
  `IsCopied` tinyint(1) DEFAULT '0'
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `qr_role`
--

CREATE TABLE `qr_role` (
  `QrRoleId` int(11) NOT NULL,
  `RoleName` varchar(45) NOT NULL,
  `XmlPath` varchar(500) NOT NULL,
  `CreatedDt` datetime NOT NULL,
  `CreatedBy` int(11) DEFAULT NULL,
  `UpdatedDt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) DEFAULT NULL,
  `Isactive` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `qr_role`
--

INSERT INTO `qr_role` (`QrRoleId`, `RoleName`, `XmlPath`, `CreatedDt`, `CreatedBy`, `UpdatedDt`, `UpdatedBy`, `Isactive`) VALUES
(1, 'default', 'Default.xml', '2011-01-20 12:48:57', 1, '2011-01-20 12:19:00', 1, 0),
(2, 'Admin', 'Admin.xml', '2011-01-20 12:49:02', 1, '2011-12-13 23:30:00', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `qr_saas_contract`
--

CREATE TABLE `qr_saas_contract` (
  `QrSaasContractId` int(11) NOT NULL,
  `SaasName` varchar(255) DEFAULT NULL,
  `SaasType` varchar(255) DEFAULT NULL,
  `SaasSize` int(11) DEFAULT '0',
  `CompanyName` varchar(255) DEFAULT NULL,
  `PersonName` varchar(255) DEFAULT NULL,
  `Designation` varchar(255) DEFAULT NULL,
  `SaasSignDate` datetime DEFAULT NULL,
  `CreatedDt` datetime DEFAULT NULL,
  `CreatedBy` int(11) DEFAULT NULL,
  `UpdatedDt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) DEFAULT NULL,
  `Isactive` tinyint(1) DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `qr_signature`
--

CREATE TABLE `qr_signature` (
  `QrSignatureId` int(11) NOT NULL,
  `QrHrUserId` int(11) DEFAULT NULL,
  `Signature` text,
  `CreatedDt` datetime DEFAULT NULL,
  `CreatedBy` int(11) DEFAULT NULL,
  `UpdatedDt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) DEFAULT NULL,
  `Isactive` tinyint(1) DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `qr_skillset_info`
--

CREATE TABLE `qr_skillset_info` (
  `QrSkillSetId` int(11) NOT NULL,
  `SkillSetName` varchar(100) NOT NULL,
  `Isactive` tinyint(1) DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `qr_social_configuration`
--

CREATE TABLE `qr_social_configuration` (
  `QrSocialId` int(11) NOT NULL,
  `QrHrUserId` int(11) NOT NULL,
  `SocialType` varchar(25) DEFAULT NULL,
  `SocialUrl` varchar(500) DEFAULT NULL,
  `SocialPageId` varchar(250) DEFAULT NULL,
  `CreatedDt` date DEFAULT NULL,
  `CreatedBy` int(11) DEFAULT NULL,
  `UpdatedDt` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) DEFAULT NULL,
  `IsActive` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `qr_state`
--

CREATE TABLE `qr_state` (
  `QrStateId` int(11) NOT NULL,
  `Name` varchar(100) NOT NULL,
  `QrCountryId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `qr_submission_docs`
--

CREATE TABLE `qr_submission_docs` (
  `QrSubmissionDocsId` int(11) NOT NULL,
  `QrRequirementId` int(11) DEFAULT NULL,
  `QrHrUserId` int(11) DEFAULT NULL,
  `QrClientBaseId` int(11) DEFAULT NULL,
  `QrApplicantsBaseId` int(11) DEFAULT NULL,
  `QrClientSubmissionId` int(11) DEFAULT NULL,
  `SubAttachName` varchar(255) NOT NULL,
  `SubAttachType` varchar(255) DEFAULT NULL,
  `SubAttachSize` int(11) NOT NULL,
  `CreatedDt` datetime NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `UpdatedDt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `qr_support`
--

CREATE TABLE `qr_support` (
  `QrSupportId` int(11) NOT NULL,
  `FullName` varchar(150) NOT NULL,
  `EmailId` varchar(150) NOT NULL,
  `Priority` varchar(100) NOT NULL,
  `Subject` varchar(255) NOT NULL,
  `Message` text,
  `CreatedDt` datetime NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `UpdatedDt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `Isactive` tinyint(1) NOT NULL DEFAULT '1',
  `phoneNo` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `qr_support_docs`
--

CREATE TABLE `qr_support_docs` (
  `QrSupportDocsId` int(11) NOT NULL,
  `QrSupportId` int(11) NOT NULL,
  `SupAttachName` varchar(255) NOT NULL,
  `SupAttachType` varchar(255) DEFAULT NULL,
  `SupAttachSize` int(11) DEFAULT NULL,
  `CreatedDt` datetime NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `UpdatedDt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `qr_vendor_base_info`
--

CREATE TABLE `qr_vendor_base_info` (
  `QrVendorBaseId` int(11) NOT NULL,
  `vendor_id` int(11) DEFAULT NULL,
  `VendorName` varchar(150) NOT NULL,
  `VendorRegNo` varchar(60) DEFAULT NULL,
  `VendorShName` varchar(150) DEFAULT NULL,
  `Address1` varchar(150) DEFAULT NULL,
  `Address2` varchar(150) DEFAULT NULL,
  `QrCountryId` int(11) NOT NULL,
  `City` varchar(60) DEFAULT NULL,
  `State` varchar(60) DEFAULT NULL,
  `PostalCode` varchar(20) DEFAULT NULL,
  `Phone` varchar(20) DEFAULT NULL,
  `Fax` varchar(20) DEFAULT NULL,
  `Website` varchar(100) DEFAULT NULL,
  `VendorSkillSet` text,
  `isexternal` tinyint(1) DEFAULT '0',
  `vendor_contract` tinyint(1) DEFAULT '0',
  `CreatedDt` datetime NOT NULL,
  `CreatedBy` int(11) DEFAULT NULL,
  `UpdatedDt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) DEFAULT NULL,
  `Isactive` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `qr_vendor_broadcast`
--

CREATE TABLE `qr_vendor_broadcast` (
  `QrVendorBroadcastId` int(11) NOT NULL,
  `QrRequirementId` int(11) NOT NULL,
  `QrVendorBaseId` int(11) NOT NULL,
  `CreatedDt` datetime NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `UpdatedDt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `qr_vendor_contact_info`
--

CREATE TABLE `qr_vendor_contact_info` (
  `QrVendorContactId` int(11) NOT NULL,
  `QrVendorBaseId` int(11) NOT NULL,
  `Salutation` varchar(20) DEFAULT NULL,
  `VendorFName` varchar(150) NOT NULL,
  `VendorMName` varchar(150) DEFAULT NULL,
  `VendorLName` varchar(150) NOT NULL,
  `PhoneWork` varchar(20) DEFAULT NULL,
  `PhoneWorkExt` varchar(10) DEFAULT NULL,
  `PhoneWorkAdd` varchar(20) DEFAULT NULL,
  `PhoneMob` varchar(20) DEFAULT NULL,
  `VendorPriEmailId` varchar(100) NOT NULL,
  `VendorSecEmail` varchar(100) DEFAULT NULL,
  `IsExternal` tinyint(1) DEFAULT '0',
  `CreatedDt` datetime NOT NULL,
  `CreatedBy` int(11) DEFAULT NULL,
  `UpdatedDt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) DEFAULT NULL,
  `Isactive` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `qr_vendor_notes`
--

CREATE TABLE `qr_vendor_notes` (
  `QrVendorNotesId` int(11) NOT NULL,
  `QrVendorBaseId` int(11) NOT NULL,
  `QrHrUserId` int(11) NOT NULL,
  `VendorComments` varchar(500) NOT NULL,
  `CreatedDt` datetime NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `UpdatedDt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) NOT NULL,
  `Isactive` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `qr_zipcode`
--

CREATE TABLE `qr_zipcode` (
  `QrZipId` int(11) NOT NULL,
  `Name` varchar(10) NOT NULL,
  `QrCityId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `client_history`
--
ALTER TABLE `client_history`
  ADD PRIMARY KEY (`client_history_id`);

--
-- Indexes for table `client_information`
--
ALTER TABLE `client_information`
  ADD PRIMARY KEY (`Client_id`);

--
-- Indexes for table `client_status`
--
ALTER TABLE `client_status`
  ADD PRIMARY KEY (`client_status_id`);

--
-- Indexes for table `hcmo_answer`
--
ALTER TABLE `hcmo_answer`
  ADD PRIMARY KEY (`HcmoAnswerId`),
  ADD KEY `fk_hcmo_answer_updatedby` (`UpdatedBy`),
  ADD KEY `fk_hcmo_answer_createdby` (`CreatedBy`),
  ADD KEY `fk_hcmo_answer_hcmoQuestionGroupNameId` (`HcmoQuestionGroupNameId`),
  ADD KEY `fk_hcmo_answer_appraiseremployeeid` (`AppraiserEmployeeId`),
  ADD KEY `fk_hcmo_answer_hcmoquestiongeneralinfogroupid` (`HcmoQuestionGeneralInfoGroupId`),
  ADD KEY `fk_hcmo_answer_appraisingemployeeid` (`AppraisingEmployeeId`);

--
-- Indexes for table `hcmo_assets`
--
ALTER TABLE `hcmo_assets`
  ADD PRIMARY KEY (`AssetTypeId`),
  ADD KEY `fk_hcmo_assets_updatedby` (`UpdatedBy`),
  ADD KEY `fk_hcmo_assets_createdby` (`CreatedBy`);

--
-- Indexes for table `hcmo_benefits`
--
ALTER TABLE `hcmo_benefits`
  ADD PRIMARY KEY (`HcmoBenefitsId`),
  ADD KEY `fk_hcmo_benefits_updatedby` (`UpdatedBy`),
  ADD KEY `fk_hcmo_benefits_createdby` (`CreatedBy`);

--
-- Indexes for table `hcmo_benefitsplan`
--
ALTER TABLE `hcmo_benefitsplan`
  ADD PRIMARY KEY (`HcmoBenefitsPlanId`),
  ADD KEY `fk_hcmo_benefitsplan_updatedby` (`UpdatedBy`),
  ADD KEY `fk_hcmo_benefitsplan_createdby` (`CreatedBy`);

--
-- Indexes for table `hcmo_benefitstype`
--
ALTER TABLE `hcmo_benefitstype`
  ADD PRIMARY KEY (`HcmoBenefitsTypeId`),
  ADD KEY `fk_hcmo_benefitstype_updatedby` (`UpdatedBy`),
  ADD KEY `fk_hcmo_benefitstype_createdby` (`CreatedBy`);

--
-- Indexes for table `hcmo_billing_info`
--
ALTER TABLE `hcmo_billing_info`
  ADD PRIMARY KEY (`hcmobillingid`);

--
-- Indexes for table `hcmo_category`
--
ALTER TABLE `hcmo_category`
  ADD PRIMARY KEY (`HcmoCategoryId`),
  ADD UNIQUE KEY `Name` (`CategoryName`,`IsActive`),
  ADD KEY `fk_hcmo_category_updatedby` (`UpdatedBy`),
  ADD KEY `fk_hcmo_category_createdby` (`CreatedBy`);

--
-- Indexes for table `hcmo_client`
--
ALTER TABLE `hcmo_client`
  ADD PRIMARY KEY (`HcmoClientId`),
  ADD UNIQUE KEY `CompanyName` (`CompanyName`,`IsActive`),
  ADD UNIQUE KEY `TaxId` (`TaxId`,`IsActive`),
  ADD UNIQUE KEY `ClientPhone` (`Phone`,`IsActive`),
  ADD UNIQUE KEY `ClientFax` (`Fax`,`IsActive`),
  ADD KEY `fk_hcmo_client_updatedby` (`UpdatedBy`),
  ADD KEY `fk_hcmo_client_createdby` (`CreatedBy`),
  ADD KEY `fk_hcmo_client_hcmocountryid` (`HcmoCountryId`);

--
-- Indexes for table `hcmo_clientreg`
--
ALTER TABLE `hcmo_clientreg`
  ADD PRIMARY KEY (`hcmoClientregid`);

--
-- Indexes for table `hcmo_client_reg`
--
ALTER TABLE `hcmo_client_reg`
  ADD PRIMARY KEY (`hcmoclientregid`);

--
-- Indexes for table `hcmo_configuration`
--
ALTER TABLE `hcmo_configuration`
  ADD PRIMARY KEY (`HcmoConfigurationId`),
  ADD KEY `fk_hcmo_configuration_updatedby` (`UpdatedBy`),
  ADD KEY `fk_hcmo_configuration_createdby` (`CreatedBy`);

--
-- Indexes for table `hcmo_country`
--
ALTER TABLE `hcmo_country`
  ADD PRIMARY KEY (`HcmoCountryId`),
  ADD UNIQUE KEY `Name` (`Name`,`IsActive`),
  ADD UNIQUE KEY `CountryCode` (`CountryCode`,`IsActive`);

--
-- Indexes for table `hcmo_currency`
--
ALTER TABLE `hcmo_currency`
  ADD PRIMARY KEY (`HcmoCurrencyId`);

--
-- Indexes for table `hcmo_customer`
--
ALTER TABLE `hcmo_customer`
  ADD PRIMARY KEY (`HcmoCustomerId`),
  ADD UNIQUE KEY `Name` (`Name`,`IsActive`),
  ADD UNIQUE KEY `Email` (`Email`,`IsActive`),
  ADD UNIQUE KEY `CustomerPhone` (`PhoneNumber`,`IsActive`),
  ADD UNIQUE KEY `CustomerFax` (`Fax`,`IsActive`),
  ADD KEY `fk_hcmo_customer_updatedby` (`UpdatedBy`),
  ADD KEY `fk_hcmo_customer_createdby` (`CreatedBy`);

--
-- Indexes for table `hcmo_deductions`
--
ALTER TABLE `hcmo_deductions`
  ADD PRIMARY KEY (`HcmoDeductionId`),
  ADD UNIQUE KEY `DeductionName` (`DeductionName`,`DeductionType`,`DeductionMode`),
  ADD KEY `fk_hcmo_deductions_updatedby` (`UpdatedBy`),
  ADD KEY `fk_hcmo_deductions_createdby` (`CreatedBy`);

--
-- Indexes for table `hcmo_department`
--
ALTER TABLE `hcmo_department`
  ADD PRIMARY KEY (`HcmoDepartmentId`),
  ADD UNIQUE KEY `DeptName` (`DeptName`,`IsActive`),
  ADD KEY `fk_hcmo_department_updatedby` (`UpdatedBy`),
  ADD KEY `fk_hcmo_department_createdby` (`CreatedBy`);

--
-- Indexes for table `hcmo_document`
--
ALTER TABLE `hcmo_document`
  ADD PRIMARY KEY (`HcmoDocumentId`),
  ADD KEY `fk_hcmo_document_updatedby` (`UpdatedBy`),
  ADD KEY `fk_hcmo_document_createdby` (`CreatedBy`),
  ADD KEY `fk_hcmo_document_hcmoemployeeid` (`HcmoEmployeeId`);

--
-- Indexes for table `hcmo_document_template`
--
ALTER TABLE `hcmo_document_template`
  ADD PRIMARY KEY (`HcmoDocumentTemplateId`),
  ADD KEY `fk_hcmo_document_template_updatedby` (`UpdatedBy`),
  ADD KEY `fk_hcmo_document_template_createdby` (`CreatedBy`),
  ADD KEY `fk_hcmo_document_template_hcmodocumentid` (`HcmoDocumentId`),
  ADD KEY `fk_hcmo_document_template_hcmoemployeeid` (`HcmoEmployeeId`);

--
-- Indexes for table `hcmo_employee`
--
ALTER TABLE `hcmo_employee`
  ADD PRIMARY KEY (`HcmoEmployeeId`),
  ADD UNIQUE KEY `EmpWorkEmail` (`EmpWorkEmail`,`IsActive`),
  ADD KEY `fk_hcmo_employee_resumedocumentid` (`resumeDocumentId`);

--
-- Indexes for table `hcmo_employee_expenses`
--
ALTER TABLE `hcmo_employee_expenses`
  ADD PRIMARY KEY (`HcmoExpensesId`),
  ADD KEY `fk_hcmo_employee_expenses_projectid` (`ProjectId`),
  ADD KEY `fk_hcmo_employee_expenses_hcmoaccountantid` (`HcmoAccountantId`),
  ADD KEY `fk_hcmo_employee_expenses_hcmoemployeeid` (`HcmoEmployeeId`),
  ADD KEY `fk_hcmo_employee_expenses_updatedby` (`UpdatedBy`),
  ADD KEY `fk_hcmo_employee_expenses_createdby` (`CreatedBy`);

--
-- Indexes for table `hcmo_employee_shift`
--
ALTER TABLE `hcmo_employee_shift`
  ADD PRIMARY KEY (`HcmoShiftId`),
  ADD KEY `fk_hcmo_employee_shift_updatedby` (`UpdatedBy`),
  ADD KEY `fk_hcmo_employee_shift_createdby` (`CreatedBy`),
  ADD KEY `fk_hcmo_employee_shift_hcmoshifttypeid` (`HcmoShiftTypeId`),
  ADD KEY `fk_hcmo_employee_shift_hcmoemployeeid` (`HcmoEmployeeId`);

--
-- Indexes for table `hcmo_empprotimesheet`
--
ALTER TABLE `hcmo_empprotimesheet`
  ADD PRIMARY KEY (`hcmo_empprotimesheet_id`),
  ADD KEY `fk_hcmo_empprotimesheet_projectid` (`project_id`),
  ADD KEY `fk_hcmo_empprotimesheet_employeeid` (`employee_id`),
  ADD KEY `fk_hcmo_empprotimesheet_projectActivityid` (`projectActivity_id`),
  ADD KEY `fk_hcmo_empprotimesheet_updatedby` (`UpdatedBy`),
  ADD KEY `fk_hcmo_empprotimesheet_createdby` (`CreatedBy`);

--
-- Indexes for table `hcmo_empspace`
--
ALTER TABLE `hcmo_empspace`
  ADD PRIMARY KEY (`HcmoEmpSpaceId`),
  ADD KEY `fk_hcmo_empspace_hcmoemployeeid` (`HcmoEmployeeId`),
  ADD KEY `fk_hcmo_empspace_updatedby` (`UpdatedBy`),
  ADD KEY `fk_hcmo_empspace_createdby` (`CreatedBy`);

--
-- Indexes for table `hcmo_emptimesheet`
--
ALTER TABLE `hcmo_emptimesheet`
  ADD PRIMARY KEY (`hcmo_emptimesheet_id`),
  ADD KEY `fk_hcmo_emptimesheet_categoryid` (`category_id`),
  ADD KEY `fk_hcmo_emptimesheet_employeeid` (`employee_id`),
  ADD KEY `fk_hcmo_emptimesheet_projectid` (`project_id`),
  ADD KEY `fk_hcmo_emptimesheet_updatedby` (`UpdatedBy`),
  ADD KEY `fk_hcmo_emptimesheet_createdby` (`CreatedBy`);

--
-- Indexes for table `hcmo_emp_assets`
--
ALTER TABLE `hcmo_emp_assets`
  ADD PRIMARY KEY (`HcmoAssetId`),
  ADD KEY `fk_hcmo_emp_assets_updatedby` (`UpdatedBy`),
  ADD KEY `fk_hcmo_emp_assets_createdby` (`CreatedBy`),
  ADD KEY `fk_hcmo_emp_assets_assettypeid` (`AssetTypeId`),
  ADD KEY `fk_hcmo_emp_assets_hcmoemployeeid` (`HcmoEmployeeId`);

--
-- Indexes for table `hcmo_emp_attachment`
--
ALTER TABLE `hcmo_emp_attachment`
  ADD KEY `fk_hcmo_emp_attachment_hcmoemployeeid` (`HcmoEmployeeId`),
  ADD KEY `fk_hcmo_emp_attachment_updatedby` (`UpdatedBy`),
  ADD KEY `fk_hcmo_emp_attachment_createdby` (`CreatedBy`);

--
-- Indexes for table `hcmo_emp_children`
--
ALTER TABLE `hcmo_emp_children`
  ADD PRIMARY KEY (`HcmoEmpChildrenId`),
  ADD UNIQUE KEY `EmpChild` (`HcmoEmployeeId`,`ECName`,`IsActive`),
  ADD KEY `fk_hcmo_emp_children_hcmoemployeeid` (`HcmoEmployeeId`),
  ADD KEY `fk_hcmo_emp_children_updatedby` (`UpdatedBy`),
  ADD KEY `fk_hcmo_emp_children_createdby` (`CreatedBy`);

--
-- Indexes for table `hcmo_emp_direct_debit`
--
ALTER TABLE `hcmo_emp_direct_debit`
  ADD PRIMARY KEY (`HcmoEmpDirectDebitId`),
  ADD KEY `fk_hcmo_emp_direct_debit_hcmoemployeeid` (`HcmoEmployeeId`),
  ADD KEY `fk_hcmo_emp_direct_debit_updatedby` (`UpdatedBy`),
  ADD KEY `fk_hcmo_emp_direct_debit_createdby` (`CreatedBy`);

--
-- Indexes for table `hcmo_emp_education`
--
ALTER TABLE `hcmo_emp_education`
  ADD PRIMARY KEY (`HcmoEmpEducationId`),
  ADD UNIQUE KEY `EmpMajorDegree` (`HcmoEmployeeId`,`EduMajor`,`EduDeg`,`IsActive`),
  ADD KEY `fk_hcmo_emp_education_hcmoemployeeid` (`HcmoEmployeeId`),
  ADD KEY `fk_hcmo_emp_education_updatedby` (`UpdatedBy`),
  ADD KEY `fk_hcmo_emp_education_createdby` (`CreatedBy`);

--
-- Indexes for table `hcmo_emp_eeo`
--
ALTER TABLE `hcmo_emp_eeo`
  ADD PRIMARY KEY (`HcmoEEOId`),
  ADD KEY `fk_hcmo_emp_eeo_updatedby` (`UpdatedBy`),
  ADD KEY `fk_hcmo_emp_eeo_createdby` (`CreatedBy`),
  ADD KEY `fk_hcmo_emp_eeo_hcmoethnicraceid` (`HcmoEthnicRaceId`),
  ADD KEY `fk_hcmo_emp_eeo_ethnicdocumentid` (`ethnicDocumentId`),
  ADD KEY `fk_hcmo_emp_eeo_veterandocumentid` (`veteranDocumentId`),
  ADD KEY `fk_hcmo_emp_eeo_militarydocumentid` (`militaryDocumentId`),
  ADD KEY `fk_hcmo_emp_eeo_disabilitydocumentid` (`disabilityDocumentId`),
  ADD KEY `fk_hcmo_emp_eeo_hcmoemployeeid` (`HcmoEmployeeId`);

--
-- Indexes for table `hcmo_emp_leave_quota`
--
ALTER TABLE `hcmo_emp_leave_quota`
  ADD PRIMARY KEY (`HcmoEmpLeaveQuotaId`),
  ADD KEY `fk_hcmo_emp_leave_quota_hcmoemployeeid` (`HcmoEmployeeId`),
  ADD KEY `fk_hcmo_emp_leave_quota_hcmoleavetypeid` (`HcmoLeaveTypeId`),
  ADD KEY `fk_hcmo_emp_leave_quota_updatedby` (`UpdatedBy`),
  ADD KEY `fk_hcmo_emp_leave_quota_createdby` (`CreatedBy`);

--
-- Indexes for table `hcmo_emp_license`
--
ALTER TABLE `hcmo_emp_license`
  ADD PRIMARY KEY (`HcmoEmpLicenseId`),
  ADD UNIQUE KEY `LicenseNumber` (`LicenseNumber`,`IsActive`),
  ADD KEY `fk_hcmo_emp_license_hcmoemployeeid` (`HcmoEmployeeId`),
  ADD KEY `fk_hcmo_emp_license_updatedby` (`UpdatedBy`),
  ADD KEY `fk_hcmo_emp_license_createdby` (`CreatedBy`);

--
-- Indexes for table `hcmo_emp_location_history`
--
ALTER TABLE `hcmo_emp_location_history`
  ADD PRIMARY KEY (`HcmoEmpLocHistoryId`),
  ADD KEY `fk_hcmo_emp_location_history_hcmoemployeeid` (`HcmoEmployeeId`),
  ADD KEY `fk_hcmo_emp_location_history_hcmoclientid` (`HcmoClientId`),
  ADD KEY `fk_hcmo_emp_location_history_hcmolocationid` (`HcmoLocationId`),
  ADD KEY `fk_hcmo_emp_location_history_updatedby` (`UpdatedBy`),
  ADD KEY `fk_hcmo_emp_location_history_createdby` (`CreatedBy`);

--
-- Indexes for table `hcmo_emp_passport`
--
ALTER TABLE `hcmo_emp_passport`
  ADD PRIMARY KEY (`HcmoEmpPassportId`),
  ADD KEY `fk_hcmo_emp_passport_hcmoemployeeid` (`HcmoEmployeeId`),
  ADD KEY `fk_hcmo_emp_passport_hcmocountryid` (`HcmoCountryId`),
  ADD KEY `fk_hcmo_emp_passport_updatedby` (`UpdatedBy`),
  ADD KEY `fk_hcmo_emp_passport_createdby` (`CreatedBy`);

--
-- Indexes for table `hcmo_emp_report_to`
--
ALTER TABLE `hcmo_emp_report_to`
  ADD PRIMARY KEY (`HcmoEmpReportToId`),
  ADD KEY `fk_hcmo_emp_report_to_hcmoemployeeid` (`HcmoEmployeeId`),
  ADD KEY `fk_hcmo_emp_report_to_erepsupempnumber` (`ERepSupEmpNumber`),
  ADD KEY `fk_hcmo_emp_report_to_updatedby` (`UpdatedBy`),
  ADD KEY `fk_hcmo_emp_report_to_createdby` (`CreatedBy`);

--
-- Indexes for table `hcmo_emp_stat`
--
ALTER TABLE `hcmo_emp_stat`
  ADD PRIMARY KEY (`HcmoEmpStatId`),
  ADD UNIQUE KEY `EStatName` (`EStatName`,`IsActive`),
  ADD KEY `fk_hcmo_emp_stat_updatedby` (`UpdatedBy`),
  ADD KEY `fk_hcmo_emp_stat_createdby` (`CreatedBy`);

--
-- Indexes for table `hcmo_emp_target_goal`
--
ALTER TABLE `hcmo_emp_target_goal`
  ADD PRIMARY KEY (`HcmoEmpTgId`),
  ADD KEY `fk_hcmo_emp_target_goal_hcmoemployeeid` (`HcmoEmployeeId`),
  ADD KEY `fk_hcmo_emp_target_goal_hcmoprojectid` (`HcmoProjectId`),
  ADD KEY `fk_hcmo_emp_target_goal_hcmoprojectactivityid` (`HcmoProjectActivityId`),
  ADD KEY `fk_hcmo_emp_target_goal_updatedby` (`UpdatedBy`),
  ADD KEY `fk_hcmo_emp_target_goal_createdby` (`CreatedBy`);

--
-- Indexes for table `hcmo_emp_us_tax`
--
ALTER TABLE `hcmo_emp_us_tax`
  ADD PRIMARY KEY (`HcmoEmpUsTaxId`),
  ADD KEY `fk_hcmo_emp_us_tax_hcmoemployeeid` (`HcmoEmployeeId`),
  ADD KEY `fk_hcmo_emp_us_tax_taxstate` (`TaxState`),
  ADD KEY `fk_hcmo_emp_us_tax_taxunempstate` (`TaxUnempState`),
  ADD KEY `fk_hcmo_emp_us_tax_taxworkstate` (`TaxWorkState`),
  ADD KEY `fk_hcmo_emp_us_tax_updatedby` (`UpdatedBy`),
  ADD KEY `fk_hcmo_emp_us_tax_createdby` (`CreatedBy`);

--
-- Indexes for table `hcmo_emp_work_experience`
--
ALTER TABLE `hcmo_emp_work_experience`
  ADD PRIMARY KEY (`HcmoEmpWorkExperienceId`),
  ADD KEY `fk_hcmo_emp_work_experience_hcmoemployeeid` (`HcmoEmployeeId`),
  ADD KEY `fk_hcmo_emp_work_experience_updatedby` (`UpdatedBy`),
  ADD KEY `fk_hcmo_emp_work_experience_createdby` (`CreatedBy`);

--
-- Indexes for table `hcmo_ethnic_race`
--
ALTER TABLE `hcmo_ethnic_race`
  ADD PRIMARY KEY (`HcmoEthnicRaceId`),
  ADD UNIQUE KEY `EthnicRaceDesc` (`EthnicRaceDesc`,`IsActive`);

--
-- Indexes for table `hcmo_events`
--
ALTER TABLE `hcmo_events`
  ADD PRIMARY KEY (`HcmoEventID`),
  ADD KEY `fk_hcmo_events_updatedby` (`UpdatedBy`),
  ADD KEY `fk_hcmo_events_createdby` (`CreatedBy`);

--
-- Indexes for table `hcmo_expenses_accountant`
--
ALTER TABLE `hcmo_expenses_accountant`
  ADD PRIMARY KEY (`HcmoExpensesAccountantId`),
  ADD UNIQUE KEY `ExpensesAccountantId` (`ExpensesAccountantId`,`IsActive`),
  ADD KEY `fk_hcmo_expenses_accountant_expensesaccountantid` (`ExpensesAccountantId`),
  ADD KEY `fk_hcmo_expenses_accountant_updatedby` (`UpdatedBy`),
  ADD KEY `fk_hcmo_expenses_accountant_createdby` (`CreatedBy`);

--
-- Indexes for table `hcmo_expenses_approver`
--
ALTER TABLE `hcmo_expenses_approver`
  ADD PRIMARY KEY (`ApproverId`),
  ADD KEY `fk_hcmo_expenses_approver_hcmoemployeeid` (`HcmoEmployeeId`),
  ADD KEY `fk_hcmo_expenses_approver_approvingemployeeid` (`ApprovingEmployeeId`),
  ADD KEY `fk_hcmo_expenses_approver_updatedby` (`UpdatedBy`),
  ADD KEY `fk_hcmo_expenses_approver_createdby` (`CreatedBy`);

--
-- Indexes for table `hcmo_expenses_attachment`
--
ALTER TABLE `hcmo_expenses_attachment`
  ADD PRIMARY KEY (`HcmoExpensesAttachId`),
  ADD KEY `fk_hcmo_expenses_attachment_hcmoemployeeid` (`HcmoEmployeeId`),
  ADD KEY `fk_hcmo_expenses_attachment_updatedby` (`UpdatedBy`),
  ADD KEY `fk_hcmo_expenses_attachment_createdby` (`CreatedBy`);

--
-- Indexes for table `hcmo_expenses_details`
--
ALTER TABLE `hcmo_expenses_details`
  ADD PRIMARY KEY (`HcmoExpensesDetailsId`),
  ADD KEY `fk_hcmo_expenses_details_hcmoexpensesid` (`HcmoExpensesId`),
  ADD KEY `fk_hcmo_expenses_details_hcmoexpensestypeid` (`HcmoExpensesTypeId`),
  ADD KEY `fk_hcmo_expenses_details_updatedby` (`UpdatedBy`),
  ADD KEY `fk_hcmo_expenses_details_createdby` (`CreatedBy`);

--
-- Indexes for table `hcmo_expenses_type`
--
ALTER TABLE `hcmo_expenses_type`
  ADD PRIMARY KEY (`HcmoExpensesTypeId`),
  ADD UNIQUE KEY `Name` (`Name`,`IsActive`),
  ADD KEY `fk_hcmo_expenses_type_updatedby` (`UpdatedBy`),
  ADD KEY `fk_hcmo_expenses_type_createdby` (`CreatedBy`);

--
-- Indexes for table `hcmo_expense_status_tracker`
--
ALTER TABLE `hcmo_expense_status_tracker`
  ADD PRIMARY KEY (`HcmoExpensesStatusTrackerId`),
  ADD KEY `fk_hcmo_expense_status_tracker_hcmoexpensesid` (`HcmoExpensesId`),
  ADD KEY `fk_hcmo_expense_status_tracker_accountantid` (`AccountantId`),
  ADD KEY `fk_hcmo_expense_status_tracker_approverid` (`ApproverId`),
  ADD KEY `fk_hcmo_expense_status_tracker_nextlevelid` (`NextLevelId`),
  ADD KEY `fk_hcmo_expense_status_tracker_reviewedid` (`ReviewedId`),
  ADD KEY `fk_hcmo_expense_status_tracker_rejectedid` (`RejectedId`),
  ADD KEY `fk_hcmo_expense_status_tracker_updatedby` (`UpdatedBy`),
  ADD KEY `fk_hcmo_expense_status_tracker_createdby` (`CreatedBy`);

--
-- Indexes for table `hcmo_goal`
--
ALTER TABLE `hcmo_goal`
  ADD PRIMARY KEY (`HcmoGoalId`),
  ADD UNIQUE KEY `GoalName` (`GoalName`,`IsActive`),
  ADD KEY `fk_hcmo_goal_updatedby` (`UpdatedBy`),
  ADD KEY `fk_hcmo_goal_createdby` (`CreatedBy`);

--
-- Indexes for table `hcmo_holidays`
--
ALTER TABLE `hcmo_holidays`
  ADD PRIMARY KEY (`HcmoHolidaysId`),
  ADD UNIQUE KEY `Description` (`Description`,`IsActive`),
  ADD KEY `fk_hcmo_holidays_updatedby` (`UpdatedBy`),
  ADD KEY `fk_hcmo_holidays_createdby` (`CreatedBy`);

--
-- Indexes for table `hcmo_i9`
--
ALTER TABLE `hcmo_i9`
  ADD PRIMARY KEY (`HcmoI9DocumentId`),
  ADD KEY `FKE69A6E081F4362DA` (`UpdatedBy`),
  ADD KEY `FKE69A6E08B71C29C7` (`CreatedBy`),
  ADD KEY `FK8330C6281F4362DA` (`UpdatedBy`),
  ADD KEY `FK8330C628B71C29C7` (`CreatedBy`);

--
-- Indexes for table `hcmo_important_news`
--
ALTER TABLE `hcmo_important_news`
  ADD PRIMARY KEY (`HcmoImportantNewsId`),
  ADD KEY `fk_hcmo_important_news_hcmoemployeeid` (`HcmoEmployeeId`),
  ADD KEY `fk_hcmo_important_news_updatedby` (`UpdatedBy`),
  ADD KEY `fk_hcmo_important_news_createdby` (`CreatedBy`);

--
-- Indexes for table `hcmo_job_title`
--
ALTER TABLE `hcmo_job_title`
  ADD PRIMARY KEY (`HcmoJobTitleId`),
  ADD UNIQUE KEY `JobTitleName` (`JobTitleName`,`IsActive`),
  ADD KEY `fk_hcmo_job_title_hcmosalarygradeid` (`HcmoSalaryGradeId`),
  ADD KEY `fk_hcmo_job_title_updatedby` (`UpdatedBy`),
  ADD KEY `fk_hcmo_job_title_createdby` (`CreatedBy`);

--
-- Indexes for table `hcmo_leave_approver`
--
ALTER TABLE `hcmo_leave_approver`
  ADD PRIMARY KEY (`HcmoLeaveApproverId`),
  ADD KEY `fk_hcmo_leave_approver_hcmoemployeeid` (`HcmoEmployeeId`),
  ADD KEY `fk_hcmo_leave_approver_hcmoapprovingempid` (`HcmoApprovingEmpId`),
  ADD KEY `fk_hcmo_leave_approver_updatedby` (`UpdatedBy`),
  ADD KEY `fk_hcmo_leave_approver_createdby` (`CreatedBy`);

--
-- Indexes for table `hcmo_leave_history`
--
ALTER TABLE `hcmo_leave_history`
  ADD PRIMARY KEY (`HcmoLeaveHistoryId`),
  ADD KEY `fk_hcmo_leave_history_hcmoemployeeid` (`HcmoEmployeeId`),
  ADD KEY `fk_hcmo_leave_history_hcmoleaveapproverid` (`HcmoLeaveApproverId`),
  ADD KEY `fk_hcmo_leave_history_hcmoleavetypeid` (`HcmoLeaveTypeId`),
  ADD KEY `fk_hcmo_leave_history_updatedby` (`UpdatedBy`),
  ADD KEY `fk_hcmo_leave_history_createdby` (`CreatedBy`);

--
-- Indexes for table `hcmo_leave_reqs_approval`
--
ALTER TABLE `hcmo_leave_reqs_approval`
  ADD PRIMARY KEY (`HcmoLeaveReqsApprovalId`),
  ADD KEY `fk_hcmo_leave_reqs_approval_hcmoemployeeid` (`HcmoEmployeeId`),
  ADD KEY `fk_hcmo_leave_reqs_approval_hcmoleavetypeid` (`HcmoLeaveTypeId`),
  ADD KEY `fk_hcmo_leave_reqs_approval_hcmoapproverid` (`HcmoApproverId`),
  ADD KEY `fk_hcmo_leave_reqs_approval_hcmoleaveapproverid` (`HcmoLeaveApproverId`),
  ADD KEY `fk_hcmo_leave_reqs_approval_updatedby` (`UpdatedBy`),
  ADD KEY `fk_hcmo_leave_reqs_approval_createdby` (`CreatedBy`);

--
-- Indexes for table `hcmo_leave_type`
--
ALTER TABLE `hcmo_leave_type`
  ADD PRIMARY KEY (`HcmoLeaveTypeId`),
  ADD UNIQUE KEY `LeaveTypeName` (`LeaveTypeName`,`IsActive`),
  ADD KEY `fk_hcmo_leave_type_updatedby` (`UpdatedBy`),
  ADD KEY `fk_hcmo_leave_type_createdby` (`CreatedBy`);

--
-- Indexes for table `hcmo_location`
--
ALTER TABLE `hcmo_location`
  ADD PRIMARY KEY (`HcmoLocationId`),
  ADD UNIQUE KEY `Name` (`Name`,`IsActive`),
  ADD KEY `fk_hcmo_location_hcmocountryid` (`HcmoCountryId`);

--
-- Indexes for table `hcmo_mail_configuration`
--
ALTER TABLE `hcmo_mail_configuration`
  ADD PRIMARY KEY (`HcmoMailConfigurationId`),
  ADD KEY `fk_hcmo_mail_configuration_updatedby` (`UpdatedBy`),
  ADD KEY `fk_hcmo_mail_configuration_createdby` (`CreatedBy`);

--
-- Indexes for table `hcmo_message`
--
ALTER TABLE `hcmo_message`
  ADD PRIMARY KEY (`HcmoMessageId`),
  ADD KEY `fk_hcmo_message_sender` (`sender`),
  ADD KEY `fk_hcmo_message_updatedby` (`UpdatedBy`),
  ADD KEY `fk_hcmo_message_createdby` (`CreatedBy`);

--
-- Indexes for table `hcmo_module`
--
ALTER TABLE `hcmo_module`
  ADD PRIMARY KEY (`moduleId`);

--
-- Indexes for table `hcmo_nationality`
--
ALTER TABLE `hcmo_nationality`
  ADD PRIMARY KEY (`HcmoNationalityId`),
  ADD UNIQUE KEY `NatName` (`NatName`,`IsActive`);

--
-- Indexes for table `hcmo_org`
--
ALTER TABLE `hcmo_org`
  ADD PRIMARY KEY (`HcmoOrgId`),
  ADD UNIQUE KEY `Name` (`Name`,`IsActive`),
  ADD KEY `fk_hcmo_org_hcmolocationid` (`HcmoLocationId`),
  ADD KEY `fk_hcmo_org_hcmoorgtypeid` (`HcmoOrgTypeId`),
  ADD KEY `fk_hcmo_org_updatedby` (`UpdatedBy`),
  ADD KEY `fk_hcmo_org_createdby` (`CreatedBy`);

--
-- Indexes for table `hcmo_org_type`
--
ALTER TABLE `hcmo_org_type`
  ADD PRIMARY KEY (`HcmoOrgTypeId`),
  ADD UNIQUE KEY `Name` (`Name`,`IsActive`),
  ADD KEY `fk_hcmo_org_type_updatedby` (`UpdatedBy`),
  ADD KEY `fk_hcmo_org_type_createdby` (`CreatedBy`);

--
-- Indexes for table `hcmo_paystub`
--
ALTER TABLE `hcmo_paystub`
  ADD PRIMARY KEY (`HcmoPayStubId`),
  ADD UNIQUE KEY `HcmoEmployeeId` (`HcmoEmployeeId`,`IsActive`),
  ADD KEY `fk_hcmo_paystub_hcmoemployeeid` (`HcmoEmployeeId`),
  ADD KEY `fk_hcmo_paystub_updatedby` (`UpdatedBy`),
  ADD KEY `fk_hcmo_paystub_createdby` (`CreatedBy`);

--
-- Indexes for table `hcmo_paystub_archive`
--
ALTER TABLE `hcmo_paystub_archive`
  ADD PRIMARY KEY (`HcmoPayStubArchiveId`);

--
-- Indexes for table `hcmo_paystub_deductions`
--
ALTER TABLE `hcmo_paystub_deductions`
  ADD PRIMARY KEY (`Hcmo_PayStub_DeductionsId`),
  ADD KEY `fk_hcmo_paystub_deductions_hcmodeductionid` (`HcmoDeductionId`),
  ADD KEY `fk_hcmo_paystub_deductions_hcmopaystubid` (`HcmoPayStubId`),
  ADD KEY `fk_hcmo_paystub_deductions_updatedby` (`UpdatedBy`),
  ADD KEY `fk_hcmo_paystub_deductions_createdby` (`CreatedBy`);

--
-- Indexes for table `hcmo_plan`
--
ALTER TABLE `hcmo_plan`
  ADD PRIMARY KEY (`HcmoPlanId`);

--
-- Indexes for table `hcmo_plan_subscription`
--
ALTER TABLE `hcmo_plan_subscription`
  ADD PRIMARY KEY (`HcmoPlanClientId`);

--
-- Indexes for table `hcmo_project`
--
ALTER TABLE `hcmo_project`
  ADD PRIMARY KEY (`HcmoProjectId`),
  ADD UNIQUE KEY `Name` (`Name`,`IsActive`),
  ADD KEY `fk_hcmo_project_hcmocustomerid` (`HcmoCustomerId`),
  ADD KEY `fk_hcmo_project_projectowner` (`ProjectOwner`),
  ADD KEY `fk_hcmo_project_updatedby` (`UpdatedBy`),
  ADD KEY `fk_hcmo_project_createdby` (`CreatedBy`);

--
-- Indexes for table `hcmo_project_activity`
--
ALTER TABLE `hcmo_project_activity`
  ADD PRIMARY KEY (`HcmoProjectActivityId`),
  ADD KEY `fk_hcmo_project_activity_hcmoprojectid` (`HcmoProjectId`),
  ADD KEY `fk_hcmo_project_activity_projectactivityowner` (`ProjectActivityOwner`),
  ADD KEY `fk_hcmo_project_activity_updatedby` (`UpdatedBy`),
  ADD KEY `fk_hcmo_project_activity_createdby` (`CreatedBy`),
  ADD KEY `fk_hcmo_project_activity_hcmodepartmentid` (`HcmoDepartmentId`),
  ADD KEY `fk_hcmo_project_activity_hcmoemployeeid` (`HcmoEmployeeId`);

--
-- Indexes for table `hcmo_question`
--
ALTER TABLE `hcmo_question`
  ADD PRIMARY KEY (`HcmoQuestionId`),
  ADD KEY `fk_hcmo_question_updatedby` (`UpdatedBy`),
  ADD KEY `fk_hcmo_question_createdby` (`CreatedBy`),
  ADD KEY `fk_hcmo_question_hcmosubcategoryid` (`HcmoSubCategoryId`),
  ADD KEY `fk_hcmo_question_hcmocategoryid` (`HcmoCategoryId`);

--
-- Indexes for table `hcmo_question_general_info`
--
ALTER TABLE `hcmo_question_general_info`
  ADD PRIMARY KEY (`HcmoQuestionGeneralInfoId`),
  ADD KEY `fk_hcmo_question_general_info_updatedby` (`UpdatedBy`),
  ADD KEY `fk_hcmo_question_general_info_createdby` (`CreatedBy`),
  ADD KEY `fk_hcmo_question_general_info_hcmoquestiongroupnameid` (`HcmoQuestionGroupNameId`),
  ADD KEY `fk_hcmo_question_general_info_hcmodepartmentid` (`HcmoDepartmentId`),
  ADD KEY `fk_hcmo_question_general_info_hcmojobtitleid` (`HcmoJobTitleId`),
  ADD KEY `fk_hcmo_question_general_info_hcmoteamid` (`HcmoTeamId`),
  ADD KEY `fk_hcmo_question_general_info_hcmoemployeeid` (`HcmoEmployeeId`),
  ADD KEY `fk_hcmo_question_general_info_peeremployeeid` (`PeerEmployeeId`),
  ADD KEY `fk_hcmo_question_general_info_hcmoApprovingEmployeeId` (`HcmoApprovingEmployeeId`),
  ADD KEY `fk_hcmo_question_general_info_hcmoquestiongroupnameidentifiid` (`HcmoQuestionGroupNameIdentifiId`),
  ADD KEY `fk_hcmo_question_general_info_hcmoquestiongeneralinfogroupid` (`HcmoQuestionGeneralInfoGroupId`);

--
-- Indexes for table `hcmo_question_general_info_group`
--
ALTER TABLE `hcmo_question_general_info_group`
  ADD PRIMARY KEY (`HcmoQuestionGeneralInfoGroupId`),
  ADD KEY `fk_hcmo_employee_assigned_question_group_updatedby` (`UpdatedBy`),
  ADD KEY `fk_hcmo_employee_assigned_question_group_createdby` (`CreatedBy`);

--
-- Indexes for table `hcmo_question_group_name`
--
ALTER TABLE `hcmo_question_group_name`
  ADD PRIMARY KEY (`HcmoQuestionGroupNameId`),
  ADD KEY `fk_hcmo_question_group_name_updatedby` (`UpdatedBy`),
  ADD KEY `fk_hcmo_question_group_name_createdby` (`CreatedBy`),
  ADD KEY `fk_hcmo_question_group_name_hcmoquestionid` (`HcmoQuestionId`),
  ADD KEY `fk_hcmo_question_group_name_hcmoQuestionGroupNameIdentifiId` (`HcmoQuestionGroupNameIdentifiId`);

--
-- Indexes for table `hcmo_question_group_name_identification`
--
ALTER TABLE `hcmo_question_group_name_identification`
  ADD PRIMARY KEY (`HcmoQuestionGroupNameIdentificationId`),
  ADD KEY `fk_hcmo_question_group_name_identification_updatedby` (`UpdatedBy`),
  ADD KEY `fk_hcmo_question_group_name_identification_createdby` (`CreatedBy`);

--
-- Indexes for table `hcmo_recurring_profile`
--
ALTER TABLE `hcmo_recurring_profile`
  ADD PRIMARY KEY (`recurring_prof_id`),
  ADD UNIQUE KEY `bill_agreemntid` (`hcmoclientid`,`bill_agreemntid`);

--
-- Indexes for table `hcmo_region`
--
ALTER TABLE `hcmo_region`
  ADD PRIMARY KEY (`HcmoRegionId`),
  ADD KEY `fk_hcmo_region_hcmocountryid` (`HcmoCountryId`);

--
-- Indexes for table `hcmo_role`
--
ALTER TABLE `hcmo_role`
  ADD PRIMARY KEY (`HcmoRoleId`),
  ADD UNIQUE KEY `RoleName` (`RoleName`,`IsActive`),
  ADD KEY `fk_hcmo_role_updatedby` (`UpdatedBy`),
  ADD KEY `fk_hcmo_role_createdby` (`CreatedBy`);

--
-- Indexes for table `hcmo_saas_contract`
--
ALTER TABLE `hcmo_saas_contract`
  ADD PRIMARY KEY (`HcmoSaasContractId`),
  ADD KEY `hcmo_saas_contract_createdby` (`CreatedBy`),
  ADD KEY `hcmo_saas_contract_updatedby` (`UpdatedBy`);

--
-- Indexes for table `hcmo_salary`
--
ALTER TABLE `hcmo_salary`
  ADD PRIMARY KEY (`HcmoSalaryId`),
  ADD KEY `fk_hcmo_salary_hcmoemployeeid` (`HcmoEmployeeId`),
  ADD KEY `fk_hcmo_salary_updatedby` (`UpdatedBy`),
  ADD KEY `fk_hcmo_salary_createdby` (`CreatedBy`);

--
-- Indexes for table `hcmo_salary_grade`
--
ALTER TABLE `hcmo_salary_grade`
  ADD PRIMARY KEY (`HcmoSalaryGradeId`),
  ADD UNIQUE KEY `SalGrdName` (`SalGrdName`,`IsActive`),
  ADD KEY `fk_hcmo_salary_grade_updatedby` (`UpdatedBy`),
  ADD KEY `fk_hcmo_salary_grade_createdby` (`CreatedBy`);

--
-- Indexes for table `hcmo_shift`
--
ALTER TABLE `hcmo_shift`
  ADD PRIMARY KEY (`HcmoShiftTypeId`),
  ADD KEY `fk_hcmo_shift_updatedby` (`UpdatedBy`),
  ADD KEY `fk_hcmo_shift_createdby` (`CreatedBy`);

--
-- Indexes for table `hcmo_signature`
--
ALTER TABLE `hcmo_signature`
  ADD PRIMARY KEY (`HcmoSignatureId`),
  ADD KEY `fk_hcmo_signature_hcmoemployeeid` (`HcmoEmployeeId`),
  ADD KEY `fk_hcmo_signature_updatedby` (`UpdatedBy`),
  ADD KEY `fk_hcmo_signature_createdby` (`CreatedBy`);

--
-- Indexes for table `hcmo_sub_category`
--
ALTER TABLE `hcmo_sub_category`
  ADD PRIMARY KEY (`HcmoSubCategoryId`),
  ADD KEY `fk_hcmo_sub_category_updatedby` (`UpdatedBy`),
  ADD KEY `fk_hcmo_sub_category_createdby` (`CreatedBy`),
  ADD KEY `fk_hcmo_sub_category_hcmocategoryid` (`HcmoCategoryId`);

--
-- Indexes for table `hcmo_support`
--
ALTER TABLE `hcmo_support`
  ADD PRIMARY KEY (`HcmoSupportId`),
  ADD KEY `fk_hcmo_support_updatedby` (`UpdatedBy`),
  ADD KEY `fk_hcmo_support_createdby` (`CreatedBy`);

--
-- Indexes for table `hcmo_target`
--
ALTER TABLE `hcmo_target`
  ADD PRIMARY KEY (`HcmoTargetId`),
  ADD UNIQUE KEY `TargetName` (`TargetName`,`IsActive`),
  ADD KEY `fk_hcmo_target_projectid` (`HcmoProjectId`),
  ADD KEY `fk_hcmo_target_projectactivityid` (`HcmoProjectActivityId`),
  ADD KEY `fk_hcmo_target_hcmotargettypeid` (`HcmoTargetTypeId`),
  ADD KEY `fk_hcmo_target_updatedby` (`UpdatedBy`),
  ADD KEY `fk_hcmo_target_createdby` (`CreatedBy`);

--
-- Indexes for table `hcmo_target_type`
--
ALTER TABLE `hcmo_target_type`
  ADD PRIMARY KEY (`HcmoTargetTypeId`),
  ADD UNIQUE KEY `TargetTypeName` (`HcmoTargetTypeId`,`IsActive`),
  ADD KEY `fk_hcmo_target_type_createdby` (`CreatedBy`),
  ADD KEY `fk_hcmo_target_type_updatedby` (`UpdatedBy`);

--
-- Indexes for table `hcmo_team`
--
ALTER TABLE `hcmo_team`
  ADD PRIMARY KEY (`HcmoTeamId`),
  ADD UNIQUE KEY `TeamName` (`TeamName`,`IsActive`),
  ADD KEY `fk_hcmo_team_updatedby` (`UpdatedBy`),
  ADD KEY `fk_hcmo_team_createdby` (`CreatedBy`);

--
-- Indexes for table `hcmo_timesheetcat`
--
ALTER TABLE `hcmo_timesheetcat`
  ADD PRIMARY KEY (`hcmo_timesheetcat_id`),
  ADD UNIQUE KEY `name` (`name`),
  ADD KEY `fk_hcmo_timesheetcat_updatedby` (`UpdatedBy`),
  ADD KEY `fk_hcmo_timesheetcat_createdby` (`CreatedBy`);

--
-- Indexes for table `hcmo_timesheet_notes`
--
ALTER TABLE `hcmo_timesheet_notes`
  ADD PRIMARY KEY (`HcmoTsNotesId`),
  ADD UNIQUE KEY `EmpDate` (`HcmoEmployeeId`,`Date`),
  ADD KEY `fk_hcmo_timesheet_notes_updatedby` (`UpdatedBy`),
  ADD KEY `fk_hcmo_timesheet_notes_createdby` (`CreatedBy`);

--
-- Indexes for table `hcmo_ts_achieved_target`
--
ALTER TABLE `hcmo_ts_achieved_target`
  ADD PRIMARY KEY (`HcmoTsAchivedTargetId`),
  ADD KEY `fk_hcmo_ts_achieved_target_hcmotsassignedtargetid` (`HcmoTsAssignedTargetId`),
  ADD KEY `fk_hcmo_ts_achieved_target_updatedby` (`UpdatedBy`),
  ADD KEY `fk_hcmo_ts_achieved_target_createdby` (`CreatedBy`);

--
-- Indexes for table `hcmo_ts_approver`
--
ALTER TABLE `hcmo_ts_approver`
  ADD PRIMARY KEY (`HcmoApproverId`),
  ADD KEY `fk_hcmo_ts_approver_hcmoemployeeid` (`HcmoEmployeeId`),
  ADD KEY `fk_hcmo_ts_approver_hcmoapprovingempid` (`HcmoApprovingEmpId`),
  ADD KEY `fk_hcmo_ts_approver_updatedby` (`UpdatedBy`),
  ADD KEY `fk_hcmo_ts_approver_createdby` (`CreatedBy`);

--
-- Indexes for table `hcmo_ts_assign_proj_target`
--
ALTER TABLE `hcmo_ts_assign_proj_target`
  ADD PRIMARY KEY (`HcmoTsAssignProjTargetId`),
  ADD KEY `fk_hcmo_ts_assign_proj_target_hcmotsempprojrelid` (`HcmoTsEmpProjRelId`),
  ADD KEY `fk_hcmo_ts_assign_proj_target_hcmotargetid` (`HcmoTargetId`),
  ADD KEY `fk_hcmo_ts_assign_proj_target_hcmoemployeeid` (`HcmoEmployeeId`),
  ADD KEY `fk_hcmo_ts_assign_proj_target_updatedby` (`UpdatedBy`),
  ADD KEY `fk_hcmo_ts_assign_proj_target_createdby` (`CreatedBy`);

--
-- Indexes for table `hcmo_ts_attachment`
--
ALTER TABLE `hcmo_ts_attachment`
  ADD PRIMARY KEY (`HcmoTsAttachmentId`),
  ADD KEY `fk_hcmo_ts_attachment_hcmoemployeeid` (`HcmoEmployeeId`),
  ADD KEY `fk_hcmo_ts_attachment_hcmoprojectid` (`HcmoProjectId`),
  ADD KEY `fk_hcmo_ts_attachment_updatedby` (`UpdatedBy`),
  ADD KEY `fk_hcmo_ts_attachment_createdby` (`CreatedBy`);

--
-- Indexes for table `hcmo_ts_detail`
--
ALTER TABLE `hcmo_ts_detail`
  ADD PRIMARY KEY (`hcmo_ts_detail`),
  ADD UNIQUE KEY `employee_id` (`employee_id`,`category_id`),
  ADD KEY `fk_hcmo_ts_detail_updatedby` (`UpdatedBy`),
  ADD KEY `fk_hcmo_ts_detail_createdby` (`CreatedBy`),
  ADD KEY `fk_hcmo_ts_detail_categoryid` (`category_id`),
  ADD KEY `fk_hcmo_ts_detail_employeeid` (`employee_id`);

--
-- Indexes for table `hcmo_ts_emp_proj_rel`
--
ALTER TABLE `hcmo_ts_emp_proj_rel`
  ADD PRIMARY KEY (`HcmoTsEmpProjRelId`),
  ADD KEY `fk_hcmo_ts_emp_proj_rel_hcmoemployeeid` (`HcmoEmployeeId`),
  ADD KEY `fk_hcmo_ts_emp_proj_rel_hcmoprojectid` (`HcmoProjectId`),
  ADD KEY `fk_hcmo_ts_emp_proj_rel_hcmoprojectactivityid` (`HcmoProjectActivityId`),
  ADD KEY `fk_hcmo_ts_emp_proj_rel_updatedby` (`UpdatedBy`),
  ADD KEY `fk_hcmo_ts_emp_proj_rel_createdby` (`CreatedBy`),
  ADD KEY `fk_hcmo_ts_emp_proj_rel_hcmodepartmentid` (`HcmoDepartmentId`),
  ADD KEY `fk_hcmo_ts_emp_proj_rel_hcmocustomerid` (`HcmoCustomerId`),
  ADD KEY `fk_hcmo_ts_emp_proj_rel_projectownerid` (`ProjectOwnerId`);

--
-- Indexes for table `hcmo_ts_timetrack`
--
ALTER TABLE `hcmo_ts_timetrack`
  ADD PRIMARY KEY (`HcmoEmptimesheetId`),
  ADD KEY `fk_hcmo_ts_timetrack_updatedby` (`UpdatedBy`),
  ADD KEY `fk_hcmo_ts_timetrack_createdby` (`CreatedBy`);

--
-- Indexes for table `hcmo_user`
--
ALTER TABLE `hcmo_user`
  ADD PRIMARY KEY (`HcmoUserId`),
  ADD UNIQUE KEY `UserName` (`UserName`,`IsActive`),
  ADD UNIQUE KEY `Password` (`Password`,`IsActive`),
  ADD UNIQUE KEY `EmployeeId` (`HcmoEmployeeId`,`IsActive`),
  ADD KEY `fk_hcmo_user_hcmoemployeeid` (`HcmoEmployeeId`),
  ADD KEY `fk_hcmo_user_updatedby` (`UpdatedBy`),
  ADD KEY `fk_hcmo_user_createdby` (`CreatedBy`);

--
-- Indexes for table `hcmo_vendor`
--
ALTER TABLE `hcmo_vendor`
  ADD PRIMARY KEY (`HcmoVendorId`),
  ADD KEY `fk_hcmo_vendor_hcmocountryid` (`HcmoCountryId`),
  ADD KEY `fk_hcmo_vendor_updatedby` (`UpdatedBy`),
  ADD KEY `fk_hcmo_vendor_createdby` (`CreatedBy`);

--
-- Indexes for table `qr_applicants_addl_info`
--
ALTER TABLE `qr_applicants_addl_info`
  ADD PRIMARY KEY (`QrApplicantsAddlId`),
  ADD KEY `CreatedBy` (`CreatedBy`),
  ADD KEY `UpdatedBy` (`UpdatedBy`),
  ADD KEY `QrApplicantsBaseId` (`QrApplicantsBaseId`),
  ADD KEY `QrEthnicRaceId` (`QrEthnicRaceId`),
  ADD KEY `QrNationalityId` (`QrNationalityId`),
  ADD KEY `FKA0992AD25C0693FE` (`QrNationalityId`),
  ADD KEY `FKA0992AD247BC20AD` (`UpdatedBy`),
  ADD KEY `FKA0992AD23F998942` (`QrEthnicRaceId`),
  ADD KEY `FKA0992AD29816EB55` (`QrApplicantsBaseId`);

--
-- Indexes for table `qr_applicants_attach`
--
ALTER TABLE `qr_applicants_attach`
  ADD PRIMARY KEY (`QrapplicantsAttachId`),
  ADD KEY `CreatedBy` (`CreatedBy`),
  ADD KEY `UpdatedBy` (`UpdatedBy`),
  ADD KEY `QrApplicantsBaseId` (`QrApplicantsBaseId`),
  ADD KEY `FKF68F21F547BC20AD` (`UpdatedBy`),
  ADD KEY `FKF68F21F5DF94E79A` (`CreatedBy`),
  ADD KEY `FKF68F21F59816EB55` (`QrApplicantsBaseId`);

--
-- Indexes for table `qr_applicants_base_info`
--
ALTER TABLE `qr_applicants_base_info`
  ADD PRIMARY KEY (`QrApplicantsBaseId`),
  ADD KEY `UpdatedBy` (`UpdatedBy`),
  ADD KEY `CreatedBy` (`CreatedBy`),
  ADD KEY `QrVendorBaseId` (`QrVendorBaseId`),
  ADD KEY `FKAC59FAC47BC20AD` (`UpdatedBy`),
  ADD KEY `FKAC59FACDF94E79A` (`CreatedBy`),
  ADD KEY `FKAC59FAC3FFA5283` (`QrVendorBaseId`),
  ADD KEY `FKAC59FACBC4D3B2` (`QrCountryId`);

--
-- Indexes for table `qr_applicants_broadcast`
--
ALTER TABLE `qr_applicants_broadcast`
  ADD PRIMARY KEY (`QrApplicantBroadcastId`),
  ADD KEY `FK1690D71126FAA32C` (`QrHrUserId`),
  ADD KEY `FK1690D71147BC20AD` (`UpdatedBy`),
  ADD KEY `FK1690D7116D2BC58C` (`QrRequirementId`),
  ADD KEY `FK1690D711DF94E79A` (`CreatedBy`),
  ADD KEY `FK1690D7119816EB55` (`QrApplicantsBaseId`);

--
-- Indexes for table `qr_applicants_formatted_attach`
--
ALTER TABLE `qr_applicants_formatted_attach`
  ADD PRIMARY KEY (`QrApplicantsFormatAttachId`),
  ADD KEY `CreatedBy` (`CreatedBy`),
  ADD KEY `UpdatedBy` (`UpdatedBy`),
  ADD KEY `QrApplicantsBaseId` (`QrApplicantsBaseId`),
  ADD KEY `FK57D286D847BC20AD` (`UpdatedBy`),
  ADD KEY `FK57D286D8DF94E79A` (`CreatedBy`),
  ADD KEY `FK57D286D89816EB55` (`QrApplicantsBaseId`);

--
-- Indexes for table `qr_applicants_notes`
--
ALTER TABLE `qr_applicants_notes`
  ADD PRIMARY KEY (`QrApplicantsNotesId`),
  ADD KEY `QrHrUserId` (`QrHrUserId`),
  ADD KEY `CreatedBy` (`CreatedBy`),
  ADD KEY `UpdatedBy` (`UpdatedBy`),
  ADD KEY `QrApplicantsBaseId` (`QrApplicantsBaseId`),
  ADD KEY `QrRequirementId` (`QrRequirementId`),
  ADD KEY `FKF824E51126FAA32C` (`QrHrUserId`),
  ADD KEY `FKF824E51147BC20AD` (`UpdatedBy`),
  ADD KEY `FKF824E5116D2BC58C` (`QrRequirementId`),
  ADD KEY `FKF824E511DF94E79A` (`CreatedBy`),
  ADD KEY `FKF824E5119816EB55` (`QrApplicantsBaseId`);

--
-- Indexes for table `qr_applicants_notes_attach`
--
ALTER TABLE `qr_applicants_notes_attach`
  ADD PRIMARY KEY (`QrApplicantsNotesAttachId`),
  ADD KEY `CreatedBy` (`CreatedBy`),
  ADD KEY `UpdatedBy` (`UpdatedBy`),
  ADD KEY `QrApplicantsNotesId` (`QrApplicantsNotesId`),
  ADD KEY `FKF802613347BC20AD` (`UpdatedBy`),
  ADD KEY `FKF8026133DF94E79A` (`CreatedBy`),
  ADD KEY `FKF8026133C02B94C6` (`QrApplicantsNotesId`);

--
-- Indexes for table `qr_applicants_privilege_current`
--
ALTER TABLE `qr_applicants_privilege_current`
  ADD PRIMARY KEY (`QrApplicantsPrivileveCurrentId`),
  ADD KEY `CreatedBy` (`CreatedBy`),
  ADD KEY `UpdatedBy` (`UpdatedBy`),
  ADD KEY `QrApplicantsBaseId` (`QrApplicantsBaseId`),
  ADD KEY `FK9B54B83B47BC20AD` (`UpdatedBy`),
  ADD KEY `FK9B54B83B8150C9BA` (`PrivHrUserId`),
  ADD KEY `FK9B54B83BDF94E79A` (`CreatedBy`),
  ADD KEY `FK9B54B83B9816EB55` (`QrApplicantsBaseId`);

--
-- Indexes for table `qr_applicants_privilege_history`
--
ALTER TABLE `qr_applicants_privilege_history`
  ADD PRIMARY KEY (`QrPrivilegeHistoryId`),
  ADD KEY `qr_applicants_previlage_history_ibfk_1` (`UpdatedBy`),
  ADD KEY `qr_applicants_previlage_history_ibfk_2` (`CreatedBy`),
  ADD KEY `qr_applicants_previlage_history_ibfk_3` (`QrHrUserId`),
  ADD KEY `qr_applicants_previlage_history_ibfk_4` (`QrApplicantsBaseId`),
  ADD KEY `qr_applicants_previlage_history_ibfk_5` (`QrRequirementId`),
  ADD KEY `FK8F68DF5626FAA32C` (`QrHrUserId`),
  ADD KEY `FK8F68DF5647BC20AD` (`UpdatedBy`),
  ADD KEY `FK8F68DF566D2BC58C` (`QrRequirementId`),
  ADD KEY `FK8F68DF56DF94E79A` (`CreatedBy`),
  ADD KEY `FK8F68DF569816EB55` (`QrApplicantsBaseId`);

--
-- Indexes for table `qr_applicants_req_mail`
--
ALTER TABLE `qr_applicants_req_mail`
  ADD PRIMARY KEY (`QrApplicantsReqMailId`),
  ADD KEY `qr_applicants_req_mail_ibfk_1` (`QrApplicantsBaseId`),
  ADD KEY `qr_applicants_req_mail_ibfk_2` (`QrRequirementId`),
  ADD KEY `qr_applicants_req_mail_ibfk_3` (`CreatedBy`),
  ADD KEY `qr_applicants_req_mail_ibfk_4` (`UpdatedBy`),
  ADD KEY `FK568EC06847BC20AD` (`UpdatedBy`),
  ADD KEY `FK568EC0686D2BC58C` (`QrRequirementId`),
  ADD KEY `FK568EC068DF94E79A` (`CreatedBy`),
  ADD KEY `FK568EC0689816EB55` (`QrApplicantsBaseId`);

--
-- Indexes for table `qr_applicants_skill_matrix`
--
ALTER TABLE `qr_applicants_skill_matrix`
  ADD PRIMARY KEY (`QrApplicantsSkillMatrixId`),
  ADD KEY `QrApplicantsBaseId` (`QrApplicantsBaseId`),
  ADD KEY `CreatedBy` (`CreatedBy`),
  ADD KEY `UpdatedBy` (`UpdatedBy`),
  ADD KEY `FK95E56D1F47BC20AD` (`UpdatedBy`),
  ADD KEY `FK95E56D1FDF94E79A` (`CreatedBy`),
  ADD KEY `FK95E56D1F9816EB55` (`QrApplicantsBaseId`);

--
-- Indexes for table `qr_applicants_submission`
--
ALTER TABLE `qr_applicants_submission`
  ADD PRIMARY KEY (`QrApplicantsSubmissionId`),
  ADD KEY `qr_applicants_submission_ibfk_1` (`QrApplicantsBaseId`),
  ADD KEY `qr_applicants_submission_ibfk_2` (`QrHrUserId`),
  ADD KEY `qr_applicants_submission_ibfk_3` (`QrRequirementId`),
  ADD KEY `qr_applicants_submission_ibfk_4` (`QrVendorBaseId`),
  ADD KEY `qr_applicants_submission_ibfk_5` (`CreatedBy`),
  ADD KEY `qr_applicants_submission_ibfk_6` (`UpdatedBy`),
  ADD KEY `qr_applicants_submission_ibfk_7` (`QrApplicantsNotesId`),
  ADD KEY `FKEF0516FC26FAA32C` (`QrHrUserId`),
  ADD KEY `FKEF0516FC47BC20AD` (`UpdatedBy`),
  ADD KEY `FKEF0516FC6D2BC58C` (`QrRequirementId`),
  ADD KEY `FKEF0516FC3FFA5283` (`QrVendorBaseId`),
  ADD KEY `FKEF0516FCDF94E79A` (`CreatedBy`),
  ADD KEY `FKEF0516FC9816EB55` (`QrApplicantsBaseId`),
  ADD KEY `FKEF0516FCC02B94C6` (`QrApplicantsNotesId`),
  ADD KEY `FKEF0516FCE5144E88` (`QrApplicationReceivedId`);

--
-- Indexes for table `qr_applicants_trai_cert`
--
ALTER TABLE `qr_applicants_trai_cert`
  ADD PRIMARY KEY (`QrApplicantsTraiCertId`),
  ADD KEY `QrApplicantsBaseId` (`QrApplicantsBaseId`),
  ADD KEY `CreatedBy` (`CreatedBy`),
  ADD KEY `UpdatedBy` (`UpdatedBy`),
  ADD KEY `FKAC29C5ED47BC20AD` (`UpdatedBy`),
  ADD KEY `FKAC29C5EDDF94E79A` (`CreatedBy`),
  ADD KEY `FKAC29C5ED9816EB55` (`QrApplicantsBaseId`);

--
-- Indexes for table `qr_application_received`
--
ALTER TABLE `qr_application_received`
  ADD PRIMARY KEY (`QrApplicationReceivedId`),
  ADD KEY `qr_application_received_ibfk_1` (`QrApplicantsBaseId`),
  ADD KEY `qr_application_received_ibfk_2` (`QrRequirementId`),
  ADD KEY `FK8B6AEB4E47BC20AD` (`UpdatedBy`),
  ADD KEY `FK8B6AEB4E6D2BC58C` (`QrRequirementId`),
  ADD KEY `FK8B6AEB4EDF94E79A` (`CreatedBy`),
  ADD KEY `FK8B6AEB4E9816EB55` (`QrApplicantsBaseId`);

--
-- Indexes for table `qr_assigned_requirement`
--
ALTER TABLE `qr_assigned_requirement`
  ADD PRIMARY KEY (`QrAssignedRequirementId`),
  ADD KEY `UpdatedBy` (`UpdatedBy`),
  ADD KEY `CreatedBy` (`CreatedBy`),
  ADD KEY `QrRequirementId` (`QrRequirementId`),
  ADD KEY `AssignToId` (`AssignToId`),
  ADD KEY `AssignById` (`AssignById`),
  ADD KEY `FK7581DEB0F911477C` (`AssignById`),
  ADD KEY `FK7581DEB047BC20AD` (`UpdatedBy`),
  ADD KEY `FK7581DEB0F91950A0` (`AssignToId`),
  ADD KEY `FK7581DEB06D2BC58C` (`QrRequirementId`),
  ADD KEY `FK7581DEB0DF94E79A` (`CreatedBy`);

--
-- Indexes for table `qr_calendar`
--
ALTER TABLE `qr_calendar`
  ADD PRIMARY KEY (`EventID`),
  ADD KEY `FK7C6B739C47BC20AD` (`UpdatedBy`),
  ADD KEY `FK7C6B739CDF94E79A` (`CreatedBy`);

--
-- Indexes for table `qr_city`
--
ALTER TABLE `qr_city`
  ADD PRIMARY KEY (`QrCityId`),
  ADD KEY `QrStateId` (`QrStateId`),
  ADD KEY `Name` (`Name`),
  ADD KEY `FK2191F0C9F6874CA8` (`QrStateId`);

--
-- Indexes for table `qr_client_base_info`
--
ALTER TABLE `qr_client_base_info`
  ADD PRIMARY KEY (`QrClientBaseId`),
  ADD KEY `UpdatedBy` (`UpdatedBy`),
  ADD KEY `CreatedBy` (`CreatedBy`),
  ADD KEY `FK22C5D84647BC20AD` (`UpdatedBy`),
  ADD KEY `FK22C5D846DF94E79A` (`CreatedBy`),
  ADD KEY `FK22C5D846BC4D3B2` (`QrCountryId`);

--
-- Indexes for table `qr_client_broadcast`
--
ALTER TABLE `qr_client_broadcast`
  ADD PRIMARY KEY (`QrDirectClientId`),
  ADD KEY `QrApplicantsBaseId` (`QrApplicantsBaseId`),
  ADD KEY `QrClientBaseId` (`QrClientBaseId`),
  ADD KEY `CreatedBy` (`CreatedBy`),
  ADD KEY `UpdatedBy` (`UpdatedBy`),
  ADD KEY `FK2E910FAB44E72C89` (`QrClientBaseId`),
  ADD KEY `FK2E910FAB47BC20AD` (`UpdatedBy`),
  ADD KEY `FK2E910FABDF94E79A` (`CreatedBy`),
  ADD KEY `FK2E910FAB9816EB55` (`QrApplicantsBaseId`);

--
-- Indexes for table `qr_client_contact_info`
--
ALTER TABLE `qr_client_contact_info`
  ADD PRIMARY KEY (`QrClientContactId`),
  ADD UNIQUE KEY `ClientPriMailId` (`ClientPriMailId`,`Isactive`),
  ADD KEY `CreatedBy` (`CreatedBy`),
  ADD KEY `UpdatedBy` (`UpdatedBy`),
  ADD KEY `QrClientBaseId` (`QrClientBaseId`),
  ADD KEY `FK4068064344E72C89` (`QrClientBaseId`),
  ADD KEY `FK4068064347BC20AD` (`UpdatedBy`),
  ADD KEY `FK40680643DF94E79A` (`CreatedBy`);

--
-- Indexes for table `qr_client_notes`
--
ALTER TABLE `qr_client_notes`
  ADD PRIMARY KEY (`QrClientNotesId`),
  ADD KEY `QrClientBaseId` (`QrClientBaseId`),
  ADD KEY `CreatedBy` (`CreatedBy`),
  ADD KEY `UpdatedBy` (`UpdatedBy`),
  ADD KEY `FK7FFD7AAB26FAA32C` (`QrHrUserId`),
  ADD KEY `FK7FFD7AAB44E72C89` (`QrClientBaseId`),
  ADD KEY `FK7FFD7AAB47BC20AD` (`UpdatedBy`),
  ADD KEY `FK7FFD7AABDF94E79A` (`CreatedBy`),
  ADD KEY `FK7FFD7AABF7E6CB50` (`QrClientContactId`);

--
-- Indexes for table `qr_client_submission`
--
ALTER TABLE `qr_client_submission`
  ADD PRIMARY KEY (`QrClientSubmissionId`),
  ADD KEY `qr_client_submission_ibfk_2` (`QrClientBaseId`),
  ADD KEY `qr_client_submission_ibfk_3` (`QrApplicantsBaseId`),
  ADD KEY `qr_client_submission_ibfk_4` (`QrHrUserId`),
  ADD KEY `qr_client_submission_ibfk_5` (`QrRequirementId`),
  ADD KEY `qr_client_submission_ibfk_6` (`CreatedBy`),
  ADD KEY `qr_client_submission_ibfk_7` (`UpdatedBy`),
  ADD KEY `FKD70BF1A226FAA32C` (`QrHrUserId`),
  ADD KEY `FKD70BF1A244E72C89` (`QrClientBaseId`),
  ADD KEY `FKD70BF1A247BC20AD` (`UpdatedBy`),
  ADD KEY `FKD70BF1A26D2BC58C` (`QrRequirementId`),
  ADD KEY `FKD70BF1A2DF94E79A` (`CreatedBy`),
  ADD KEY `FKD70BF1A29816EB55` (`QrApplicantsBaseId`),
  ADD KEY `FKD70BF1A2E5144E88` (`QrApplicationReceivedId`);

--
-- Indexes for table `qr_corp_corp_feed_jobs`
--
ALTER TABLE `qr_corp_corp_feed_jobs`
  ADD PRIMARY KEY (`QrCorpCorpFeedJobsId`),
  ADD KEY `CreatedBy` (`CreatedBy`),
  ADD KEY `UpdatedBy` (`UpdatedBy`),
  ADD KEY `QrHrUserId` (`QrHrUserId`),
  ADD KEY `QrRequirementId` (`QrRequirementId`),
  ADD KEY `FK443B961926FAA32C` (`QrHrUserId`),
  ADD KEY `FK443B961947BC20AD` (`UpdatedBy`),
  ADD KEY `FK443B96196D2BC58C` (`QrRequirementId`),
  ADD KEY `FK443B9619DF94E79A` (`CreatedBy`);

--
-- Indexes for table `qr_country`
--
ALTER TABLE `qr_country`
  ADD PRIMARY KEY (`QrCountryId`);

--
-- Indexes for table `qr_educational_qualification`
--
ALTER TABLE `qr_educational_qualification`
  ADD PRIMARY KEY (`QrQualificationId`),
  ADD KEY `qr_educational_qualification_ibfk_1` (`CreatedBy`),
  ADD KEY `qr_educational_qualification_ibfk_2` (`UpdatedBy`),
  ADD KEY `FKEFCFBE547BC20AD` (`UpdatedBy`),
  ADD KEY `FKEFCFBE5DF94E79A` (`CreatedBy`);

--
-- Indexes for table `qr_email_reader_configuration`
--
ALTER TABLE `qr_email_reader_configuration`
  ADD PRIMARY KEY (`QrEmailReaderConfigurationId`),
  ADD KEY `CreatedBy` (`CreatedBy`),
  ADD KEY `UpdatedBy` (`UpdatedBy`),
  ADD KEY `FKA888541B47BC20AD` (`UpdatedBy`),
  ADD KEY `FKA888541BDF94E79A` (`CreatedBy`);

--
-- Indexes for table `qr_email_send_to_client`
--
ALTER TABLE `qr_email_send_to_client`
  ADD PRIMARY KEY (`QrEmailSendToClientId`),
  ADD KEY `qr_email_send_to_client_ibfk_1` (`SubmissionMailedBy`),
  ADD KEY `qr_email_send_to_client_ibfk_2` (`SubmissionMailedTo`(255)),
  ADD KEY `qr_email_send_to_client_ibfk_3` (`CreatedBy`),
  ADD KEY `qr_email_send_to_client_ibfk_4` (`UpdatedBy`),
  ADD KEY `FK94E9847999A533B0` (`QrClientSubmissionId`),
  ADD KEY `FK94E9847947BC20AD` (`UpdatedBy`),
  ADD KEY `FK94E9847973716514` (`SubmissionMailedBy`),
  ADD KEY `FK94E98479DF94E79A` (`CreatedBy`);

--
-- Indexes for table `qr_ethnic_race`
--
ALTER TABLE `qr_ethnic_race`
  ADD PRIMARY KEY (`QrEthnicRaceId`),
  ADD KEY `CreatedBy` (`CreatedBy`),
  ADD KEY `UpdatedBy` (`UpdatedBy`),
  ADD KEY `FK7309822347BC20AD` (`UpdatedBy`),
  ADD KEY `FK73098223DF94E79A` (`CreatedBy`);

--
-- Indexes for table `qr_hr_user_info`
--
ALTER TABLE `qr_hr_user_info`
  ADD PRIMARY KEY (`QrHrUserId`),
  ADD KEY `CreatedBy` (`CreatedBy`),
  ADD KEY `UpdatedBy` (`UpdatedBy`),
  ADD KEY `FKEAB8B50B47BC20AD` (`UpdatedBy`),
  ADD KEY `FKEAB8B50BDF94E79A` (`CreatedBy`),
  ADD KEY `FKEAB8B50BADC27784` (`ReportingTo`),
  ADD KEY `QrRoleId` (`QrRoleId`),
  ADD KEY `FKEAB8B50B8827D34E` (`QrRoleId`),
  ADD KEY `FKEAB8B50BBC4D3B2` (`QrCountryId`);

--
-- Indexes for table `qr_hr_user_login`
--
ALTER TABLE `qr_hr_user_login`
  ADD PRIMARY KEY (`QrHrUserLoginId`),
  ADD KEY `CreatedBy` (`CreatedBy`),
  ADD KEY `UpdatedBy` (`UpdatedBy`),
  ADD KEY `QrHrUserId` (`QrHrUserId`),
  ADD KEY `FK6C88AAAC26FAA32C` (`QrHrUserId`),
  ADD KEY `FK6C88AAAC47BC20AD` (`UpdatedBy`),
  ADD KEY `FK6C88AAACDF94E79A` (`CreatedBy`);

--
-- Indexes for table `qr_idiligo_config`
--
ALTER TABLE `qr_idiligo_config`
  ADD PRIMARY KEY (`QrIdiligoId`),
  ADD KEY `FK62D4B80C47BC20AD` (`UpdatedBy`),
  ADD KEY `FK62D4B80CDF94E79A` (`CreatedBy`);

--
-- Indexes for table `qr_import_resumes`
--
ALTER TABLE `qr_import_resumes`
  ADD PRIMARY KEY (`QrImportResumesId`),
  ADD KEY `UpdatedBy` (`UpdatedBy`),
  ADD KEY `CreatedBy` (`CreatedBy`),
  ADD KEY `QrHrUserId` (`QrHrUserId`),
  ADD KEY `FK4AB11D2A26FAA32C` (`QrHrUserId`),
  ADD KEY `FK4AB11D2A47BC20AD` (`UpdatedBy`),
  ADD KEY `FK4AB11D2ADF94E79A` (`CreatedBy`);

--
-- Indexes for table `qr_jobcategory_info`
--
ALTER TABLE `qr_jobcategory_info`
  ADD PRIMARY KEY (`QrJobCategoryId`);

--
-- Indexes for table `qr_job_board_config`
--
ALTER TABLE `qr_job_board_config`
  ADD PRIMARY KEY (`QrJobBoardConfigId`),
  ADD KEY `CreatedBy` (`CreatedBy`),
  ADD KEY `UpdatedBy` (`UpdatedBy`),
  ADD KEY `QrHrUserId` (`QrHrUserId`),
  ADD KEY `FK9261E65B26FAA32C` (`QrHrUserId`),
  ADD KEY `FK9261E65B47BC20AD` (`UpdatedBy`),
  ADD KEY `FK9261E65BDF94E79A` (`CreatedBy`);

--
-- Indexes for table `qr_job_board_config_corp_corp`
--
ALTER TABLE `qr_job_board_config_corp_corp`
  ADD PRIMARY KEY (`QrJobBoardConfigCorpCorpId`),
  ADD KEY `CreatedBy` (`CreatedBy`),
  ADD KEY `UpdatedBy` (`UpdatedBy`),
  ADD KEY `QrHrUserId` (`QrHrUserId`),
  ADD KEY `FK8E6EDE3B26FAA32C` (`QrHrUserId`),
  ADD KEY `FK8E6EDE3B47BC20AD` (`UpdatedBy`),
  ADD KEY `FK8E6EDE3BDF94E79A` (`CreatedBy`);

--
-- Indexes for table `qr_job_board_config_indeed`
--
ALTER TABLE `qr_job_board_config_indeed`
  ADD PRIMARY KEY (`QrJobBoardConfigIndeedId`),
  ADD KEY `CreatedBy` (`CreatedBy`),
  ADD KEY `UpdatedBy` (`UpdatedBy`),
  ADD KEY `QrHrUserId` (`QrHrUserId`);

--
-- Indexes for table `qr_job_board_post_jobs`
--
ALTER TABLE `qr_job_board_post_jobs`
  ADD PRIMARY KEY (`QrJobBoardPostJobsId`),
  ADD KEY `CreatedBy` (`CreatedBy`),
  ADD KEY `UpdatedBy` (`UpdatedBy`),
  ADD KEY `QrHrUserId` (`QrHrUserId`),
  ADD KEY `QrRequirementId` (`QrRequirementId`),
  ADD KEY `FK56087ADC26FAA32C` (`QrHrUserId`),
  ADD KEY `FK56087ADC47BC20AD` (`UpdatedBy`),
  ADD KEY `FK56087ADC6D2BC58C` (`QrRequirementId`),
  ADD KEY `FK56087ADCDF94E79A` (`CreatedBy`);

--
-- Indexes for table `qr_location`
--
ALTER TABLE `qr_location`
  ADD PRIMARY KEY (`QrLocationId`),
  ADD KEY `QrCountryId` (`QrCountryId`),
  ADD KEY `CreatedBy` (`CreatedBy`),
  ADD KEY `UpdatedBy` (`UpdatedBy`),
  ADD KEY `FKF85C181347BC20AD` (`UpdatedBy`),
  ADD KEY `FKF85C1813BC4D3B2` (`QrCountryId`),
  ADD KEY `FKF85C1813DF94E79A` (`CreatedBy`);

--
-- Indexes for table `qr_logo`
--
ALTER TABLE `qr_logo`
  ADD PRIMARY KEY (`QrClientLogoId`),
  ADD KEY `CreatedBy` (`CreatedBy`),
  ADD KEY `UpdatedBy` (`UpdatedBy`),
  ADD KEY `FK21961D0947BC20AD` (`UpdatedBy`),
  ADD KEY `FK21961D09DF94E79A` (`CreatedBy`);

--
-- Indexes for table `qr_mail_configuration`
--
ALTER TABLE `qr_mail_configuration`
  ADD PRIMARY KEY (`QrMailConfigurationId`),
  ADD KEY `UpdatedBy` (`UpdatedBy`),
  ADD KEY `CreatedBy` (`CreatedBy`),
  ADD KEY `FKFC309B4C47BC20AD` (`UpdatedBy`),
  ADD KEY `FKFC309B4CDF94E79A` (`CreatedBy`),
  ADD KEY `QrHrUserId` (`QrHrUserId`),
  ADD KEY `FKFC309B4C26FAA32C` (`QrHrUserId`);

--
-- Indexes for table `qr_nationality`
--
ALTER TABLE `qr_nationality`
  ADD PRIMARY KEY (`QrNationalityId`),
  ADD KEY `CreatedBy` (`CreatedBy`),
  ADD KEY `UpdatedBy` (`UpdatedBy`),
  ADD KEY `FKC9B4009E47BC20AD` (`UpdatedBy`),
  ADD KEY `FKC9B4009EDF94E79A` (`CreatedBy`);

--
-- Indexes for table `qr_onboarded_attach`
--
ALTER TABLE `qr_onboarded_attach`
  ADD PRIMARY KEY (`QrOnBoardedAttachId`),
  ADD KEY `FK91CEC7BC889F9E77` (`QrApplicantNotesId`),
  ADD KEY `FK91CEC7BC47BC20AD` (`UpdatedBy`),
  ADD KEY `FK91CEC7BCDF94E79A` (`CreatedBy`);

--
-- Indexes for table `qr_on_off_board_applicants`
--
ALTER TABLE `qr_on_off_board_applicants`
  ADD PRIMARY KEY (`QrOnOffBoardApplId`),
  ADD KEY `FKC2CD1BBC26FAA32C` (`QrHrUserId`),
  ADD KEY `FKC2CD1BBC47BC20AD` (`UpdatedBy`),
  ADD KEY `FKC2CD1BBC6D2BC58C` (`QrRequirementId`),
  ADD KEY `FKC2CD1BBCDF94E79A` (`CreatedBy`),
  ADD KEY `FKC2CD1BBC9816EB55` (`QrApplicantsBaseId`);

--
-- Indexes for table `qr_org`
--
ALTER TABLE `qr_org`
  ADD PRIMARY KEY (`QrOrgId`),
  ADD KEY `FKC746F3C69DECF34C` (`QrLocationId`),
  ADD KEY `FKC746F3C6BD54C3EB` (`QrOrgTypeId`),
  ADD KEY `FKC746F3C647BC20AD` (`UpdatedBy`),
  ADD KEY `FKC746F3C6DF94E79A` (`CreatedBy`);

--
-- Indexes for table `qr_org_type`
--
ALTER TABLE `qr_org_type`
  ADD PRIMARY KEY (`QrOrgTypeId`),
  ADD KEY `UpdatedBy` (`UpdatedBy`),
  ADD KEY `CreatedBy` (`CreatedBy`),
  ADD KEY `FKD569C01347BC20AD` (`UpdatedBy`),
  ADD KEY `FKD569C013DF94E79A` (`CreatedBy`);

--
-- Indexes for table `qr_privilege`
--
ALTER TABLE `qr_privilege`
  ADD PRIMARY KEY (`QrPrivilegeId`),
  ADD KEY `FKFC7D8A1347BC20AD` (`UpdatedBy`),
  ADD KEY `FKFC7D8A13DF94E79A` (`CreatedBy`);

--
-- Indexes for table `qr_requirement`
--
ALTER TABLE `qr_requirement`
  ADD PRIMARY KEY (`QrRequirementId`),
  ADD KEY `CreatedBy` (`CreatedBy`),
  ADD KEY `UpdatedBy` (`UpdatedBy`),
  ADD KEY `qr_requirement_ibfk_3` (`QrClientBaseId`),
  ADD KEY `FKD9D41F2544E72C89` (`QrClientBaseId`),
  ADD KEY `FKD9D41F2547BC20AD` (`UpdatedBy`),
  ADD KEY `FKD9D41F25DF94E79A` (`CreatedBy`),
  ADD KEY `FKD9D41F25BC4D3B2` (`QrCountryId`);

--
-- Indexes for table `qr_req_attach`
--
ALTER TABLE `qr_req_attach`
  ADD PRIMARY KEY (`QrReqAttachId`),
  ADD KEY `FK6577BDE447BC20AD` (`UpdatedBy`),
  ADD KEY `FK6577BDE46D2BC58C` (`QrRequirementId`),
  ADD KEY `FK6577BDE4DF94E79A` (`CreatedBy`);

--
-- Indexes for table `qr_resume_parser`
--
ALTER TABLE `qr_resume_parser`
  ADD PRIMARY KEY (`QrResumeParserId`),
  ADD KEY `QrImportResumesId` (`QrImportResumesId`),
  ADD KEY `CreatedBy` (`CreatedBy`),
  ADD KEY `UpdatedBy` (`UpdatedBy`),
  ADD KEY `FKB13766D3E4DB80D3` (`QrImportResumesId`),
  ADD KEY `FKB13766D347BC20AD` (`UpdatedBy`),
  ADD KEY `FKB13766D3DF94E79A` (`CreatedBy`);

--
-- Indexes for table `qr_role`
--
ALTER TABLE `qr_role`
  ADD PRIMARY KEY (`QrRoleId`),
  ADD KEY `FK2198D7D447BC20AD` (`UpdatedBy`),
  ADD KEY `FK2198D7D4DF94E79A` (`CreatedBy`);

--
-- Indexes for table `qr_saas_contract`
--
ALTER TABLE `qr_saas_contract`
  ADD PRIMARY KEY (`QrSaasContractId`),
  ADD KEY `CreatedBy` (`CreatedBy`),
  ADD KEY `UpdatedBy` (`UpdatedBy`),
  ADD KEY `FK80D175D347BC20AD` (`UpdatedBy`),
  ADD KEY `FK80D175D3DF94E79A` (`CreatedBy`);

--
-- Indexes for table `qr_signature`
--
ALTER TABLE `qr_signature`
  ADD PRIMARY KEY (`QrSignatureId`),
  ADD KEY `UpdatedBy` (`UpdatedBy`),
  ADD KEY `CreatedBy` (`CreatedBy`),
  ADD KEY `QrHrUserId` (`QrHrUserId`),
  ADD KEY `FK9A802C1A26FAA32C` (`QrHrUserId`),
  ADD KEY `FK9A802C1A47BC20AD` (`UpdatedBy`),
  ADD KEY `FK9A802C1ADF94E79A` (`CreatedBy`);

--
-- Indexes for table `qr_skillset_info`
--
ALTER TABLE `qr_skillset_info`
  ADD PRIMARY KEY (`QrSkillSetId`);

--
-- Indexes for table `qr_social_configuration`
--
ALTER TABLE `qr_social_configuration`
  ADD PRIMARY KEY (`QrSocialId`),
  ADD KEY `FK4E36370226FAA32C` (`QrHrUserId`),
  ADD KEY `FK4E36370247BC20AD` (`UpdatedBy`),
  ADD KEY `FK4E363702DF94E79A` (`CreatedBy`);

--
-- Indexes for table `qr_state`
--
ALTER TABLE `qr_state`
  ADD PRIMARY KEY (`QrStateId`),
  ADD KEY `QrCountryId` (`QrCountryId`),
  ADD KEY `FK119258F3BC4D3B2` (`QrCountryId`);

--
-- Indexes for table `qr_submission_docs`
--
ALTER TABLE `qr_submission_docs`
  ADD PRIMARY KEY (`QrSubmissionDocsId`),
  ADD KEY `FK47BBD13026FAA32C` (`QrHrUserId`),
  ADD KEY `FK47BBD13044E72C89` (`QrClientBaseId`),
  ADD KEY `FK47BBD13047BC20AD` (`UpdatedBy`),
  ADD KEY `FK47BBD1306D2BC58C` (`QrRequirementId`),
  ADD KEY `FK47BBD130DF94E79A` (`CreatedBy`),
  ADD KEY `FK47BBD1309816EB55` (`QrApplicantsBaseId`),
  ADD KEY `FK47BBD13099A533B0` (`QrClientSubmissionId`);

--
-- Indexes for table `qr_support`
--
ALTER TABLE `qr_support`
  ADD PRIMARY KEY (`QrSupportId`),
  ADD KEY `UpdatedBy` (`UpdatedBy`),
  ADD KEY `CreatedBy` (`CreatedBy`),
  ADD KEY `FKF8E683B147BC20AD` (`UpdatedBy`),
  ADD KEY `FKF8E683B1DF94E79A` (`CreatedBy`);

--
-- Indexes for table `qr_support_docs`
--
ALTER TABLE `qr_support_docs`
  ADD PRIMARY KEY (`QrSupportDocsId`),
  ADD KEY `QrSupportId` (`QrSupportId`),
  ADD KEY `UpdatedBy` (`UpdatedBy`),
  ADD KEY `CreatedBy` (`CreatedBy`),
  ADD KEY `FK6526FE096828CFA4` (`QrSupportId`),
  ADD KEY `FK6526FE0947BC20AD` (`UpdatedBy`),
  ADD KEY `FK6526FE09DF94E79A` (`CreatedBy`);

--
-- Indexes for table `qr_vendor_base_info`
--
ALTER TABLE `qr_vendor_base_info`
  ADD PRIMARY KEY (`QrVendorBaseId`),
  ADD KEY `UpdatedBy` (`UpdatedBy`),
  ADD KEY `CreatedBy` (`CreatedBy`),
  ADD KEY `FKC453918347BC20AD` (`UpdatedBy`),
  ADD KEY `FKC4539183DF94E79A` (`CreatedBy`),
  ADD KEY `FKC4539183BC4D3B2` (`QrCountryId`);

--
-- Indexes for table `qr_vendor_broadcast`
--
ALTER TABLE `qr_vendor_broadcast`
  ADD PRIMARY KEY (`QrVendorBroadcastId`),
  ADD KEY `QrRequirementId` (`QrRequirementId`),
  ADD KEY `QrVendorBaseId` (`QrVendorBaseId`),
  ADD KEY `CreatedBy` (`CreatedBy`),
  ADD KEY `UpdatedBy` (`UpdatedBy`),
  ADD KEY `FKD01EC8E847BC20AD` (`UpdatedBy`),
  ADD KEY `FKD01EC8E86D2BC58C` (`QrRequirementId`),
  ADD KEY `FKD01EC8E8DF94E79A` (`CreatedBy`),
  ADD KEY `FKD01EC8E83FFA5283` (`QrVendorBaseId`);

--
-- Indexes for table `qr_vendor_contact_info`
--
ALTER TABLE `qr_vendor_contact_info`
  ADD PRIMARY KEY (`QrVendorContactId`),
  ADD KEY `CreatedBy` (`CreatedBy`),
  ADD KEY `UpdatedBy` (`UpdatedBy`),
  ADD KEY `QrVendorBaseId` (`QrVendorBaseId`),
  ADD KEY `FK6BEF67E647BC20AD` (`UpdatedBy`),
  ADD KEY `FK6BEF67E6DF94E79A` (`CreatedBy`),
  ADD KEY `FK6BEF67E63FFA5283` (`QrVendorBaseId`);

--
-- Indexes for table `qr_vendor_notes`
--
ALTER TABLE `qr_vendor_notes`
  ADD PRIMARY KEY (`QrVendorNotesId`),
  ADD KEY `qr_vendor_notes_ibfk1` (`QrVendorBaseId`),
  ADD KEY `qr_vendor_notes_ibfk2` (`QrHrUserId`),
  ADD KEY `qr_vendor_notes_ibfk3` (`CreatedBy`),
  ADD KEY `qr_vendor_notes_ibfk4` (`UpdatedBy`),
  ADD KEY `FK2F2B5A6826FAA32C` (`QrHrUserId`),
  ADD KEY `FK2F2B5A6847BC20AD` (`UpdatedBy`),
  ADD KEY `FK2F2B5A68DF94E79A` (`CreatedBy`),
  ADD KEY `FK2F2B5A683FFA5283` (`QrVendorBaseId`);

--
-- Indexes for table `qr_zipcode`
--
ALTER TABLE `qr_zipcode`
  ADD PRIMARY KEY (`QrZipId`),
  ADD KEY `QrCityId` (`QrCityId`),
  ADD KEY `FK56B21070545532B8` (`QrCityId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `client_history`
--
ALTER TABLE `client_history`
  MODIFY `client_history_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=590;
--
-- AUTO_INCREMENT for table `client_information`
--
ALTER TABLE `client_information`
  MODIFY `Client_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `client_status`
--
ALTER TABLE `client_status`
  MODIFY `client_status_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=156;
--
-- AUTO_INCREMENT for table `hcmo_answer`
--
ALTER TABLE `hcmo_answer`
  MODIFY `HcmoAnswerId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `hcmo_benefits`
--
ALTER TABLE `hcmo_benefits`
  MODIFY `HcmoBenefitsId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `hcmo_benefitsplan`
--
ALTER TABLE `hcmo_benefitsplan`
  MODIFY `HcmoBenefitsPlanId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `hcmo_benefitstype`
--
ALTER TABLE `hcmo_benefitstype`
  MODIFY `HcmoBenefitsTypeId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `hcmo_billing_info`
--
ALTER TABLE `hcmo_billing_info`
  MODIFY `hcmobillingid` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `hcmo_category`
--
ALTER TABLE `hcmo_category`
  MODIFY `HcmoCategoryId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `hcmo_client`
--
ALTER TABLE `hcmo_client`
  MODIFY `HcmoClientId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `hcmo_clientreg`
--
ALTER TABLE `hcmo_clientreg`
  MODIFY `hcmoClientregid` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `hcmo_client_reg`
--
ALTER TABLE `hcmo_client_reg`
  MODIFY `hcmoclientregid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `hcmo_configuration`
--
ALTER TABLE `hcmo_configuration`
  MODIFY `HcmoConfigurationId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `hcmo_country`
--
ALTER TABLE `hcmo_country`
  MODIFY `HcmoCountryId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=256;
--
-- AUTO_INCREMENT for table `hcmo_currency`
--
ALTER TABLE `hcmo_currency`
  MODIFY `HcmoCurrencyId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `hcmo_customer`
--
ALTER TABLE `hcmo_customer`
  MODIFY `HcmoCustomerId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `hcmo_deductions`
--
ALTER TABLE `hcmo_deductions`
  MODIFY `HcmoDeductionId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `hcmo_department`
--
ALTER TABLE `hcmo_department`
  MODIFY `HcmoDepartmentId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `hcmo_document`
--
ALTER TABLE `hcmo_document`
  MODIFY `HcmoDocumentId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `hcmo_document_template`
--
ALTER TABLE `hcmo_document_template`
  MODIFY `HcmoDocumentTemplateId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `hcmo_employee`
--
ALTER TABLE `hcmo_employee`
  MODIFY `HcmoEmployeeId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;
--
-- AUTO_INCREMENT for table `hcmo_employee_expenses`
--
ALTER TABLE `hcmo_employee_expenses`
  MODIFY `HcmoExpensesId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `hcmo_employee_shift`
--
ALTER TABLE `hcmo_employee_shift`
  MODIFY `HcmoShiftId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `hcmo_empprotimesheet`
--
ALTER TABLE `hcmo_empprotimesheet`
  MODIFY `hcmo_empprotimesheet_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `hcmo_empspace`
--
ALTER TABLE `hcmo_empspace`
  MODIFY `HcmoEmpSpaceId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `hcmo_emptimesheet`
--
ALTER TABLE `hcmo_emptimesheet`
  MODIFY `hcmo_emptimesheet_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `hcmo_emp_assets`
--
ALTER TABLE `hcmo_emp_assets`
  MODIFY `HcmoAssetId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `hcmo_emp_children`
--
ALTER TABLE `hcmo_emp_children`
  MODIFY `HcmoEmpChildrenId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `hcmo_emp_direct_debit`
--
ALTER TABLE `hcmo_emp_direct_debit`
  MODIFY `HcmoEmpDirectDebitId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `hcmo_emp_education`
--
ALTER TABLE `hcmo_emp_education`
  MODIFY `HcmoEmpEducationId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `hcmo_emp_eeo`
--
ALTER TABLE `hcmo_emp_eeo`
  MODIFY `HcmoEEOId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT for table `hcmo_emp_leave_quota`
--
ALTER TABLE `hcmo_emp_leave_quota`
  MODIFY `HcmoEmpLeaveQuotaId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `hcmo_emp_license`
--
ALTER TABLE `hcmo_emp_license`
  MODIFY `HcmoEmpLicenseId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `hcmo_emp_location_history`
--
ALTER TABLE `hcmo_emp_location_history`
  MODIFY `HcmoEmpLocHistoryId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `hcmo_emp_passport`
--
ALTER TABLE `hcmo_emp_passport`
  MODIFY `HcmoEmpPassportId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `hcmo_emp_report_to`
--
ALTER TABLE `hcmo_emp_report_to`
  MODIFY `HcmoEmpReportToId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `hcmo_emp_stat`
--
ALTER TABLE `hcmo_emp_stat`
  MODIFY `HcmoEmpStatId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `hcmo_emp_target_goal`
--
ALTER TABLE `hcmo_emp_target_goal`
  MODIFY `HcmoEmpTgId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `hcmo_emp_us_tax`
--
ALTER TABLE `hcmo_emp_us_tax`
  MODIFY `HcmoEmpUsTaxId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `hcmo_emp_work_experience`
--
ALTER TABLE `hcmo_emp_work_experience`
  MODIFY `HcmoEmpWorkExperienceId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `hcmo_ethnic_race`
--
ALTER TABLE `hcmo_ethnic_race`
  MODIFY `HcmoEthnicRaceId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `hcmo_events`
--
ALTER TABLE `hcmo_events`
  MODIFY `HcmoEventID` int(10) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `hcmo_expenses_accountant`
--
ALTER TABLE `hcmo_expenses_accountant`
  MODIFY `HcmoExpensesAccountantId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `hcmo_expenses_approver`
--
ALTER TABLE `hcmo_expenses_approver`
  MODIFY `ApproverId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `hcmo_expenses_attachment`
--
ALTER TABLE `hcmo_expenses_attachment`
  MODIFY `HcmoExpensesAttachId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `hcmo_expenses_details`
--
ALTER TABLE `hcmo_expenses_details`
  MODIFY `HcmoExpensesDetailsId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `hcmo_expenses_type`
--
ALTER TABLE `hcmo_expenses_type`
  MODIFY `HcmoExpensesTypeId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `hcmo_expense_status_tracker`
--
ALTER TABLE `hcmo_expense_status_tracker`
  MODIFY `HcmoExpensesStatusTrackerId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `hcmo_goal`
--
ALTER TABLE `hcmo_goal`
  MODIFY `HcmoGoalId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `hcmo_holidays`
--
ALTER TABLE `hcmo_holidays`
  MODIFY `HcmoHolidaysId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `hcmo_i9`
--
ALTER TABLE `hcmo_i9`
  MODIFY `HcmoI9DocumentId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `hcmo_important_news`
--
ALTER TABLE `hcmo_important_news`
  MODIFY `HcmoImportantNewsId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `hcmo_job_title`
--
ALTER TABLE `hcmo_job_title`
  MODIFY `HcmoJobTitleId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `hcmo_leave_approver`
--
ALTER TABLE `hcmo_leave_approver`
  MODIFY `HcmoLeaveApproverId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `hcmo_leave_history`
--
ALTER TABLE `hcmo_leave_history`
  MODIFY `HcmoLeaveHistoryId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `hcmo_leave_reqs_approval`
--
ALTER TABLE `hcmo_leave_reqs_approval`
  MODIFY `HcmoLeaveReqsApprovalId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `hcmo_leave_type`
--
ALTER TABLE `hcmo_leave_type`
  MODIFY `HcmoLeaveTypeId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `hcmo_location`
--
ALTER TABLE `hcmo_location`
  MODIFY `HcmoLocationId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `hcmo_mail_configuration`
--
ALTER TABLE `hcmo_mail_configuration`
  MODIFY `HcmoMailConfigurationId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `hcmo_message`
--
ALTER TABLE `hcmo_message`
  MODIFY `HcmoMessageId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `hcmo_nationality`
--
ALTER TABLE `hcmo_nationality`
  MODIFY `HcmoNationalityId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `hcmo_org`
--
ALTER TABLE `hcmo_org`
  MODIFY `HcmoOrgId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `hcmo_org_type`
--
ALTER TABLE `hcmo_org_type`
  MODIFY `HcmoOrgTypeId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `hcmo_paystub`
--
ALTER TABLE `hcmo_paystub`
  MODIFY `HcmoPayStubId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `hcmo_paystub_archive`
--
ALTER TABLE `hcmo_paystub_archive`
  MODIFY `HcmoPayStubArchiveId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `hcmo_paystub_deductions`
--
ALTER TABLE `hcmo_paystub_deductions`
  MODIFY `Hcmo_PayStub_DeductionsId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `hcmo_plan`
--
ALTER TABLE `hcmo_plan`
  MODIFY `HcmoPlanId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `hcmo_plan_subscription`
--
ALTER TABLE `hcmo_plan_subscription`
  MODIFY `HcmoPlanClientId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `hcmo_project`
--
ALTER TABLE `hcmo_project`
  MODIFY `HcmoProjectId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `hcmo_project_activity`
--
ALTER TABLE `hcmo_project_activity`
  MODIFY `HcmoProjectActivityId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `hcmo_question`
--
ALTER TABLE `hcmo_question`
  MODIFY `HcmoQuestionId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `hcmo_question_general_info`
--
ALTER TABLE `hcmo_question_general_info`
  MODIFY `HcmoQuestionGeneralInfoId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `hcmo_question_general_info_group`
--
ALTER TABLE `hcmo_question_general_info_group`
  MODIFY `HcmoQuestionGeneralInfoGroupId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `hcmo_question_group_name`
--
ALTER TABLE `hcmo_question_group_name`
  MODIFY `HcmoQuestionGroupNameId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `hcmo_question_group_name_identification`
--
ALTER TABLE `hcmo_question_group_name_identification`
  MODIFY `HcmoQuestionGroupNameIdentificationId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `hcmo_recurring_profile`
--
ALTER TABLE `hcmo_recurring_profile`
  MODIFY `recurring_prof_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `hcmo_region`
--
ALTER TABLE `hcmo_region`
  MODIFY `HcmoRegionId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `hcmo_role`
--
ALTER TABLE `hcmo_role`
  MODIFY `HcmoRoleId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `hcmo_saas_contract`
--
ALTER TABLE `hcmo_saas_contract`
  MODIFY `HcmoSaasContractId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `hcmo_salary`
--
ALTER TABLE `hcmo_salary`
  MODIFY `HcmoSalaryId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `hcmo_salary_grade`
--
ALTER TABLE `hcmo_salary_grade`
  MODIFY `HcmoSalaryGradeId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `hcmo_shift`
--
ALTER TABLE `hcmo_shift`
  MODIFY `HcmoShiftTypeId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `hcmo_signature`
--
ALTER TABLE `hcmo_signature`
  MODIFY `HcmoSignatureId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `hcmo_sub_category`
--
ALTER TABLE `hcmo_sub_category`
  MODIFY `HcmoSubCategoryId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `hcmo_support`
--
ALTER TABLE `hcmo_support`
  MODIFY `HcmoSupportId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `hcmo_target`
--
ALTER TABLE `hcmo_target`
  MODIFY `HcmoTargetId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `hcmo_target_type`
--
ALTER TABLE `hcmo_target_type`
  MODIFY `HcmoTargetTypeId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `hcmo_team`
--
ALTER TABLE `hcmo_team`
  MODIFY `HcmoTeamId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `hcmo_timesheetcat`
--
ALTER TABLE `hcmo_timesheetcat`
  MODIFY `hcmo_timesheetcat_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `hcmo_timesheet_notes`
--
ALTER TABLE `hcmo_timesheet_notes`
  MODIFY `HcmoTsNotesId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `hcmo_ts_achieved_target`
--
ALTER TABLE `hcmo_ts_achieved_target`
  MODIFY `HcmoTsAchivedTargetId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `hcmo_ts_approver`
--
ALTER TABLE `hcmo_ts_approver`
  MODIFY `HcmoApproverId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `hcmo_ts_assign_proj_target`
--
ALTER TABLE `hcmo_ts_assign_proj_target`
  MODIFY `HcmoTsAssignProjTargetId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `hcmo_ts_attachment`
--
ALTER TABLE `hcmo_ts_attachment`
  MODIFY `HcmoTsAttachmentId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `hcmo_ts_detail`
--
ALTER TABLE `hcmo_ts_detail`
  MODIFY `hcmo_ts_detail` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `hcmo_ts_emp_proj_rel`
--
ALTER TABLE `hcmo_ts_emp_proj_rel`
  MODIFY `HcmoTsEmpProjRelId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;
--
-- AUTO_INCREMENT for table `hcmo_ts_timetrack`
--
ALTER TABLE `hcmo_ts_timetrack`
  MODIFY `HcmoEmptimesheetId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `hcmo_user`
--
ALTER TABLE `hcmo_user`
  MODIFY `HcmoUserId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `hcmo_vendor`
--
ALTER TABLE `hcmo_vendor`
  MODIFY `HcmoVendorId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `qr_applicants_addl_info`
--
ALTER TABLE `qr_applicants_addl_info`
  MODIFY `QrApplicantsAddlId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `qr_applicants_attach`
--
ALTER TABLE `qr_applicants_attach`
  MODIFY `QrapplicantsAttachId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `qr_applicants_base_info`
--
ALTER TABLE `qr_applicants_base_info`
  MODIFY `QrApplicantsBaseId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `qr_applicants_broadcast`
--
ALTER TABLE `qr_applicants_broadcast`
  MODIFY `QrApplicantBroadcastId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `qr_applicants_formatted_attach`
--
ALTER TABLE `qr_applicants_formatted_attach`
  MODIFY `QrApplicantsFormatAttachId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `qr_applicants_notes`
--
ALTER TABLE `qr_applicants_notes`
  MODIFY `QrApplicantsNotesId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `qr_applicants_notes_attach`
--
ALTER TABLE `qr_applicants_notes_attach`
  MODIFY `QrApplicantsNotesAttachId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `qr_applicants_privilege_current`
--
ALTER TABLE `qr_applicants_privilege_current`
  MODIFY `QrApplicantsPrivileveCurrentId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `qr_applicants_privilege_history`
--
ALTER TABLE `qr_applicants_privilege_history`
  MODIFY `QrPrivilegeHistoryId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `qr_applicants_req_mail`
--
ALTER TABLE `qr_applicants_req_mail`
  MODIFY `QrApplicantsReqMailId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `qr_applicants_skill_matrix`
--
ALTER TABLE `qr_applicants_skill_matrix`
  MODIFY `QrApplicantsSkillMatrixId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `qr_applicants_submission`
--
ALTER TABLE `qr_applicants_submission`
  MODIFY `QrApplicantsSubmissionId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `qr_applicants_trai_cert`
--
ALTER TABLE `qr_applicants_trai_cert`
  MODIFY `QrApplicantsTraiCertId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `qr_application_received`
--
ALTER TABLE `qr_application_received`
  MODIFY `QrApplicationReceivedId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `qr_assigned_requirement`
--
ALTER TABLE `qr_assigned_requirement`
  MODIFY `QrAssignedRequirementId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `qr_calendar`
--
ALTER TABLE `qr_calendar`
  MODIFY `EventID` int(10) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `qr_city`
--
ALTER TABLE `qr_city`
  MODIFY `QrCityId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `qr_client_base_info`
--
ALTER TABLE `qr_client_base_info`
  MODIFY `QrClientBaseId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `qr_client_broadcast`
--
ALTER TABLE `qr_client_broadcast`
  MODIFY `QrDirectClientId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `qr_client_contact_info`
--
ALTER TABLE `qr_client_contact_info`
  MODIFY `QrClientContactId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `qr_client_notes`
--
ALTER TABLE `qr_client_notes`
  MODIFY `QrClientNotesId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `qr_client_submission`
--
ALTER TABLE `qr_client_submission`
  MODIFY `QrClientSubmissionId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `qr_corp_corp_feed_jobs`
--
ALTER TABLE `qr_corp_corp_feed_jobs`
  MODIFY `QrCorpCorpFeedJobsId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `qr_country`
--
ALTER TABLE `qr_country`
  MODIFY `QrCountryId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=48;
--
-- AUTO_INCREMENT for table `qr_educational_qualification`
--
ALTER TABLE `qr_educational_qualification`
  MODIFY `QrQualificationId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `qr_email_reader_configuration`
--
ALTER TABLE `qr_email_reader_configuration`
  MODIFY `QrEmailReaderConfigurationId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `qr_email_send_to_client`
--
ALTER TABLE `qr_email_send_to_client`
  MODIFY `QrEmailSendToClientId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `qr_ethnic_race`
--
ALTER TABLE `qr_ethnic_race`
  MODIFY `QrEthnicRaceId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `qr_hr_user_info`
--
ALTER TABLE `qr_hr_user_info`
  MODIFY `QrHrUserId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `qr_hr_user_login`
--
ALTER TABLE `qr_hr_user_login`
  MODIFY `QrHrUserLoginId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `qr_idiligo_config`
--
ALTER TABLE `qr_idiligo_config`
  MODIFY `QrIdiligoId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `qr_import_resumes`
--
ALTER TABLE `qr_import_resumes`
  MODIFY `QrImportResumesId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `qr_jobcategory_info`
--
ALTER TABLE `qr_jobcategory_info`
  MODIFY `QrJobCategoryId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `qr_job_board_config`
--
ALTER TABLE `qr_job_board_config`
  MODIFY `QrJobBoardConfigId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `qr_job_board_config_corp_corp`
--
ALTER TABLE `qr_job_board_config_corp_corp`
  MODIFY `QrJobBoardConfigCorpCorpId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `qr_job_board_config_indeed`
--
ALTER TABLE `qr_job_board_config_indeed`
  MODIFY `QrJobBoardConfigIndeedId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `qr_job_board_post_jobs`
--
ALTER TABLE `qr_job_board_post_jobs`
  MODIFY `QrJobBoardPostJobsId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `qr_location`
--
ALTER TABLE `qr_location`
  MODIFY `QrLocationId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `qr_logo`
--
ALTER TABLE `qr_logo`
  MODIFY `QrClientLogoId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `qr_mail_configuration`
--
ALTER TABLE `qr_mail_configuration`
  MODIFY `QrMailConfigurationId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `qr_nationality`
--
ALTER TABLE `qr_nationality`
  MODIFY `QrNationalityId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `qr_onboarded_attach`
--
ALTER TABLE `qr_onboarded_attach`
  MODIFY `QrOnBoardedAttachId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `qr_on_off_board_applicants`
--
ALTER TABLE `qr_on_off_board_applicants`
  MODIFY `QrOnOffBoardApplId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `qr_org`
--
ALTER TABLE `qr_org`
  MODIFY `QrOrgId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `qr_org_type`
--
ALTER TABLE `qr_org_type`
  MODIFY `QrOrgTypeId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `qr_privilege`
--
ALTER TABLE `qr_privilege`
  MODIFY `QrPrivilegeId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `qr_requirement`
--
ALTER TABLE `qr_requirement`
  MODIFY `QrRequirementId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `qr_req_attach`
--
ALTER TABLE `qr_req_attach`
  MODIFY `QrReqAttachId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `qr_resume_parser`
--
ALTER TABLE `qr_resume_parser`
  MODIFY `QrResumeParserId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `qr_role`
--
ALTER TABLE `qr_role`
  MODIFY `QrRoleId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `qr_saas_contract`
--
ALTER TABLE `qr_saas_contract`
  MODIFY `QrSaasContractId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `qr_signature`
--
ALTER TABLE `qr_signature`
  MODIFY `QrSignatureId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `qr_skillset_info`
--
ALTER TABLE `qr_skillset_info`
  MODIFY `QrSkillSetId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `qr_social_configuration`
--
ALTER TABLE `qr_social_configuration`
  MODIFY `QrSocialId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `qr_state`
--
ALTER TABLE `qr_state`
  MODIFY `QrStateId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `qr_submission_docs`
--
ALTER TABLE `qr_submission_docs`
  MODIFY `QrSubmissionDocsId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `qr_support`
--
ALTER TABLE `qr_support`
  MODIFY `QrSupportId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `qr_support_docs`
--
ALTER TABLE `qr_support_docs`
  MODIFY `QrSupportDocsId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `qr_vendor_base_info`
--
ALTER TABLE `qr_vendor_base_info`
  MODIFY `QrVendorBaseId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `qr_vendor_broadcast`
--
ALTER TABLE `qr_vendor_broadcast`
  MODIFY `QrVendorBroadcastId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `qr_vendor_contact_info`
--
ALTER TABLE `qr_vendor_contact_info`
  MODIFY `QrVendorContactId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `qr_vendor_notes`
--
ALTER TABLE `qr_vendor_notes`
  MODIFY `QrVendorNotesId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `qr_zipcode`
--
ALTER TABLE `qr_zipcode`
  MODIFY `QrZipId` int(11) NOT NULL AUTO_INCREMENT;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `hcmo_answer`
--
ALTER TABLE `hcmo_answer`
  ADD CONSTRAINT `fk_hcmo_answer_appraiseremployeeid` FOREIGN KEY (`AppraiserEmployeeId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_answer_appraisingemployeeid` FOREIGN KEY (`AppraisingEmployeeId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_answer_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_answer_hcmoQuestionGroupNameId` FOREIGN KEY (`HcmoQuestionGroupNameId`) REFERENCES `hcmo_question_group_name` (`HcmoQuestionGroupNameId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_answer_hcmoquestiongeneralinfogroupid` FOREIGN KEY (`HcmoQuestionGeneralInfoGroupId`) REFERENCES `hcmo_question_general_info_group` (`HcmoQuestionGeneralInfoGroupId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_answer_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `hcmo_assets`
--
ALTER TABLE `hcmo_assets`
  ADD CONSTRAINT `fk_hcmo_assets_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_assets_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `hcmo_benefits`
--
ALTER TABLE `hcmo_benefits`
  ADD CONSTRAINT `fk_hcmo_benefits_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_benefits_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `hcmo_benefitsplan`
--
ALTER TABLE `hcmo_benefitsplan`
  ADD CONSTRAINT `fk_hcmo_benefitsplan_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_benefitsplan_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `hcmo_benefitstype`
--
ALTER TABLE `hcmo_benefitstype`
  ADD CONSTRAINT `fk_hcmo_benefitstype_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_benefitstype_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `hcmo_category`
--
ALTER TABLE `hcmo_category`
  ADD CONSTRAINT `fk_hcmo_category_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_category_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `hcmo_client`
--
ALTER TABLE `hcmo_client`
  ADD CONSTRAINT `fk_hcmo_client_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_client_hcmocountryid` FOREIGN KEY (`HcmoCountryId`) REFERENCES `hcmo_country` (`HcmoCountryId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_client_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `hcmo_configuration`
--
ALTER TABLE `hcmo_configuration`
  ADD CONSTRAINT `fk_hcmo_configuration_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_configuration_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `hcmo_customer`
--
ALTER TABLE `hcmo_customer`
  ADD CONSTRAINT `fk_hcmo_customer_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_customer_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `hcmo_deductions`
--
ALTER TABLE `hcmo_deductions`
  ADD CONSTRAINT `fk_hcmo_deductions_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_deductions_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `hcmo_department`
--
ALTER TABLE `hcmo_department`
  ADD CONSTRAINT `fk_hcmo_department_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_department_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `hcmo_document`
--
ALTER TABLE `hcmo_document`
  ADD CONSTRAINT `fk_hcmo_document_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_document_hcmoemployeeid` FOREIGN KEY (`HcmoEmployeeId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_document_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `hcmo_document_template`
--
ALTER TABLE `hcmo_document_template`
  ADD CONSTRAINT `fk_hcmo_document_template_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_document_template_hcmodocumentid` FOREIGN KEY (`HcmoDocumentId`) REFERENCES `hcmo_document` (`HcmoDocumentId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_document_template_hcmoemployeeid` FOREIGN KEY (`HcmoEmployeeId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_document_template_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `hcmo_employee`
--
ALTER TABLE `hcmo_employee`
  ADD CONSTRAINT `fk_hcmo_employee_resumedocumentid` FOREIGN KEY (`resumeDocumentId`) REFERENCES `hcmo_document` (`HcmoDocumentId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `hcmo_employee_expenses`
--
ALTER TABLE `hcmo_employee_expenses`
  ADD CONSTRAINT `fk_hcmo_employee_expenses_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_employee_expenses_hcmoaccountantid` FOREIGN KEY (`HcmoAccountantId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_employee_expenses_hcmoemployeeid` FOREIGN KEY (`HcmoEmployeeId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_employee_expenses_projectid` FOREIGN KEY (`ProjectId`) REFERENCES `hcmo_project` (`HcmoProjectId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_employee_expenses_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `hcmo_employee_shift`
--
ALTER TABLE `hcmo_employee_shift`
  ADD CONSTRAINT `fk_hcmo_employee_shift_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_employee_shift_hcmoemployeeid` FOREIGN KEY (`HcmoEmployeeId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_employee_shift_hcmoshifttypeid` FOREIGN KEY (`HcmoShiftTypeId`) REFERENCES `hcmo_shift` (`HcmoShiftTypeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_employee_shift_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `hcmo_empprotimesheet`
--
ALTER TABLE `hcmo_empprotimesheet`
  ADD CONSTRAINT `fk_hcmo_empprotimesheet_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_empprotimesheet_employeeid` FOREIGN KEY (`employee_id`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_empprotimesheet_projectActivityid` FOREIGN KEY (`projectActivity_id`) REFERENCES `hcmo_project_activity` (`HcmoProjectActivityId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_empprotimesheet_projectid` FOREIGN KEY (`project_id`) REFERENCES `hcmo_project` (`HcmoProjectId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_empprotimesheet_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `hcmo_empspace`
--
ALTER TABLE `hcmo_empspace`
  ADD CONSTRAINT `fk_hcmo_empspace_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_empspace_hcmoemployeeid` FOREIGN KEY (`HcmoEmployeeId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_empspace_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `hcmo_emptimesheet`
--
ALTER TABLE `hcmo_emptimesheet`
  ADD CONSTRAINT `fk_hcmo_emptimesheet_categoryid` FOREIGN KEY (`category_id`) REFERENCES `hcmo_timesheetcat` (`hcmo_timesheetcat_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_emptimesheet_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_emptimesheet_employeeid` FOREIGN KEY (`employee_id`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_emptimesheet_projectid` FOREIGN KEY (`project_id`) REFERENCES `hcmo_project` (`HcmoProjectId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_emptimesheet_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `hcmo_emp_assets`
--
ALTER TABLE `hcmo_emp_assets`
  ADD CONSTRAINT `fk_hcmo_emp_assets_assettypeid` FOREIGN KEY (`AssetTypeId`) REFERENCES `hcmo_assets` (`AssetTypeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_emp_assets_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_emp_assets_hcmoemployeeid` FOREIGN KEY (`HcmoEmployeeId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_emp_assets_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `hcmo_emp_attachment`
--
ALTER TABLE `hcmo_emp_attachment`
  ADD CONSTRAINT `fk_hcmo_emp_attachment_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_emp_attachment_hcmoemployeeid` FOREIGN KEY (`HcmoEmployeeId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_emp_attachment_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `hcmo_emp_children`
--
ALTER TABLE `hcmo_emp_children`
  ADD CONSTRAINT `fk_hcmo_emp_children_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_emp_children_hcmoemployeeid` FOREIGN KEY (`HcmoEmployeeId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_emp_children_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `hcmo_emp_direct_debit`
--
ALTER TABLE `hcmo_emp_direct_debit`
  ADD CONSTRAINT `fk_hcmo_emp_direct_debit_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_emp_direct_debit_hcmoemployeeid` FOREIGN KEY (`HcmoEmployeeId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_emp_direct_debit_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `hcmo_emp_education`
--
ALTER TABLE `hcmo_emp_education`
  ADD CONSTRAINT `fk_hcmo_emp_education_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_emp_education_hcmoemployeeid` FOREIGN KEY (`HcmoEmployeeId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_emp_education_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `hcmo_emp_eeo`
--
ALTER TABLE `hcmo_emp_eeo`
  ADD CONSTRAINT `fk_hcmo_emp_eeo_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_emp_eeo_disabilitydocumentid` FOREIGN KEY (`disabilityDocumentId`) REFERENCES `hcmo_document` (`HcmoDocumentId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_emp_eeo_ethnicdocumentid` FOREIGN KEY (`ethnicDocumentId`) REFERENCES `hcmo_document` (`HcmoDocumentId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_emp_eeo_hcmoemployeeid` FOREIGN KEY (`HcmoEmployeeId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_emp_eeo_hcmoethnicraceid` FOREIGN KEY (`HcmoEthnicRaceId`) REFERENCES `hcmo_ethnic_race` (`HcmoEthnicRaceId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_emp_eeo_militarydocumentid` FOREIGN KEY (`militaryDocumentId`) REFERENCES `hcmo_document` (`HcmoDocumentId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_emp_eeo_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_emp_eeo_veterandocumentid` FOREIGN KEY (`veteranDocumentId`) REFERENCES `hcmo_document` (`HcmoDocumentId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `hcmo_emp_leave_quota`
--
ALTER TABLE `hcmo_emp_leave_quota`
  ADD CONSTRAINT `fk_hcmo_emp_leave_quota_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_emp_leave_quota_hcmoemployeeid` FOREIGN KEY (`HcmoEmployeeId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_emp_leave_quota_hcmoleavetypeid` FOREIGN KEY (`HcmoLeaveTypeId`) REFERENCES `hcmo_leave_type` (`HcmoLeaveTypeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_emp_leave_quota_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `hcmo_emp_license`
--
ALTER TABLE `hcmo_emp_license`
  ADD CONSTRAINT `fk_hcmo_emp_license_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_emp_license_hcmoemployeeid` FOREIGN KEY (`HcmoEmployeeId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_emp_license_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `hcmo_emp_location_history`
--
ALTER TABLE `hcmo_emp_location_history`
  ADD CONSTRAINT `fk_hcmo_emp_location_history_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_emp_location_history_hcmoclientid` FOREIGN KEY (`HcmoClientId`) REFERENCES `hcmo_client` (`HcmoClientId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_emp_location_history_hcmoemployeeid` FOREIGN KEY (`HcmoEmployeeId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_emp_location_history_hcmolocationid` FOREIGN KEY (`HcmoLocationId`) REFERENCES `hcmo_location` (`HcmoLocationId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_emp_location_history_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `hcmo_emp_passport`
--
ALTER TABLE `hcmo_emp_passport`
  ADD CONSTRAINT `fk_hcmo_emp_passport_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_emp_passport_hcmocountryid` FOREIGN KEY (`HcmoCountryId`) REFERENCES `hcmo_country` (`HcmoCountryId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_emp_passport_hcmoemployeeid` FOREIGN KEY (`HcmoEmployeeId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_emp_passport_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `hcmo_emp_report_to`
--
ALTER TABLE `hcmo_emp_report_to`
  ADD CONSTRAINT `fk_hcmo_emp_report_to_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_emp_report_to_erepsupempnumber` FOREIGN KEY (`ERepSupEmpNumber`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_emp_report_to_hcmoemployeeid` FOREIGN KEY (`HcmoEmployeeId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_emp_report_to_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `hcmo_emp_stat`
--
ALTER TABLE `hcmo_emp_stat`
  ADD CONSTRAINT `fk_hcmo_emp_stat_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_emp_stat_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `hcmo_emp_target_goal`
--
ALTER TABLE `hcmo_emp_target_goal`
  ADD CONSTRAINT `fk_hcmo_emp_target_goal_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_emp_target_goal_hcmoemployeeid` FOREIGN KEY (`HcmoEmployeeId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_emp_target_goal_hcmoprojectactivityid` FOREIGN KEY (`HcmoProjectActivityId`) REFERENCES `hcmo_project_activity` (`HcmoProjectActivityId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_emp_target_goal_hcmoprojectid` FOREIGN KEY (`HcmoProjectId`) REFERENCES `hcmo_project` (`HcmoProjectId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_emp_target_goal_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `hcmo_emp_us_tax`
--
ALTER TABLE `hcmo_emp_us_tax`
  ADD CONSTRAINT `fk_hcmo_emp_us_tax_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_emp_us_tax_hcmoemployeeid` FOREIGN KEY (`HcmoEmployeeId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_emp_us_tax_taxstate` FOREIGN KEY (`TaxState`) REFERENCES `hcmo_region` (`HcmoRegionId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_emp_us_tax_taxunempstate` FOREIGN KEY (`TaxUnempState`) REFERENCES `hcmo_region` (`HcmoRegionId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_emp_us_tax_taxworkstate` FOREIGN KEY (`TaxWorkState`) REFERENCES `hcmo_region` (`HcmoRegionId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_emp_us_tax_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `hcmo_emp_work_experience`
--
ALTER TABLE `hcmo_emp_work_experience`
  ADD CONSTRAINT `fk_hcmo_emp_work_experience_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_emp_work_experience_hcmoemployeeid` FOREIGN KEY (`HcmoEmployeeId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_emp_work_experience_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `hcmo_events`
--
ALTER TABLE `hcmo_events`
  ADD CONSTRAINT `fk_hcmo_events_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_events_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `hcmo_expenses_accountant`
--
ALTER TABLE `hcmo_expenses_accountant`
  ADD CONSTRAINT `fk_hcmo_expenses_accountant_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_expenses_accountant_expensesaccountantid` FOREIGN KEY (`ExpensesAccountantId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_expenses_accountant_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `hcmo_expenses_approver`
--
ALTER TABLE `hcmo_expenses_approver`
  ADD CONSTRAINT `fk_hcmo_expenses_approver_approvingemployeeid` FOREIGN KEY (`ApprovingEmployeeId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_expenses_approver_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_expenses_approver_hcmoemployeeid` FOREIGN KEY (`HcmoEmployeeId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_expenses_approver_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `hcmo_expenses_attachment`
--
ALTER TABLE `hcmo_expenses_attachment`
  ADD CONSTRAINT `fk_hcmo_expenses_attachment_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_expenses_attachment_hcmoemployeeid` FOREIGN KEY (`HcmoEmployeeId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_expenses_attachment_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `hcmo_expenses_details`
--
ALTER TABLE `hcmo_expenses_details`
  ADD CONSTRAINT `fk_hcmo_expenses_details_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_expenses_details_hcmoexpensesid` FOREIGN KEY (`HcmoExpensesId`) REFERENCES `hcmo_employee_expenses` (`HcmoExpensesId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_expenses_details_hcmoexpensestypeid` FOREIGN KEY (`HcmoExpensesTypeId`) REFERENCES `hcmo_expenses_type` (`HcmoExpensesTypeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_expenses_details_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `hcmo_expenses_type`
--
ALTER TABLE `hcmo_expenses_type`
  ADD CONSTRAINT `fk_hcmo_expenses_type_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_expenses_type_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `hcmo_expense_status_tracker`
--
ALTER TABLE `hcmo_expense_status_tracker`
  ADD CONSTRAINT `fk_hcmo_expense_status_tracker_accountantid` FOREIGN KEY (`AccountantId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_expense_status_tracker_approverid` FOREIGN KEY (`ApproverId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_expense_status_tracker_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_expense_status_tracker_hcmoexpensesid` FOREIGN KEY (`HcmoExpensesId`) REFERENCES `hcmo_employee_expenses` (`HcmoExpensesId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_expense_status_tracker_nextlevelid` FOREIGN KEY (`NextLevelId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_expense_status_tracker_rejectedid` FOREIGN KEY (`RejectedId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_expense_status_tracker_reviewedid` FOREIGN KEY (`ReviewedId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_expense_status_tracker_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `hcmo_goal`
--
ALTER TABLE `hcmo_goal`
  ADD CONSTRAINT `fk_hcmo_goal_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_goal_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `hcmo_holidays`
--
ALTER TABLE `hcmo_holidays`
  ADD CONSTRAINT `fk_hcmo_holidays_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_holidays_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `hcmo_i9`
--
ALTER TABLE `hcmo_i9`
  ADD CONSTRAINT `FK8330C6281F4362DA` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`),
  ADD CONSTRAINT `FK8330C628B71C29C7` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`);

--
-- Constraints for table `hcmo_important_news`
--
ALTER TABLE `hcmo_important_news`
  ADD CONSTRAINT `fk_hcmo_important_news_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_important_news_hcmoemployeeid` FOREIGN KEY (`HcmoEmployeeId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_important_news_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `hcmo_job_title`
--
ALTER TABLE `hcmo_job_title`
  ADD CONSTRAINT `fk_hcmo_job_title_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_job_title_hcmosalarygradeid` FOREIGN KEY (`HcmoSalaryGradeId`) REFERENCES `hcmo_salary_grade` (`HcmoSalaryGradeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_job_title_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `hcmo_leave_approver`
--
ALTER TABLE `hcmo_leave_approver`
  ADD CONSTRAINT `fk_hcmo_leave_approver_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_leave_approver_hcmoapprovingempid` FOREIGN KEY (`HcmoApprovingEmpId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_leave_approver_hcmoemployeeid` FOREIGN KEY (`HcmoEmployeeId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_leave_approver_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `hcmo_leave_history`
--
ALTER TABLE `hcmo_leave_history`
  ADD CONSTRAINT `fk_hcmo_leave_history_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_leave_history_hcmoemployeeid` FOREIGN KEY (`HcmoEmployeeId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_leave_history_hcmoleaveapproverid` FOREIGN KEY (`HcmoLeaveApproverId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_leave_history_hcmoleavetypeid` FOREIGN KEY (`HcmoLeaveApproverId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_leave_history_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `hcmo_leave_reqs_approval`
--
ALTER TABLE `hcmo_leave_reqs_approval`
  ADD CONSTRAINT `fk_hcmo_leave_reqs_approval_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_leave_reqs_approval_hcmoapproverid` FOREIGN KEY (`HcmoApproverId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_leave_reqs_approval_hcmoemployeeid` FOREIGN KEY (`HcmoEmployeeId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_leave_reqs_approval_hcmoleaveapproverid` FOREIGN KEY (`HcmoLeaveApproverId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_leave_reqs_approval_hcmoleavetypeid` FOREIGN KEY (`HcmoLeaveTypeId`) REFERENCES `hcmo_leave_type` (`HcmoLeaveTypeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_leave_reqs_approval_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `hcmo_leave_type`
--
ALTER TABLE `hcmo_leave_type`
  ADD CONSTRAINT `fk_hcmo_leave_type_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_leave_type_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `hcmo_location`
--
ALTER TABLE `hcmo_location`
  ADD CONSTRAINT `fk_hcmo_location_hcmocountryid` FOREIGN KEY (`HcmoCountryId`) REFERENCES `hcmo_country` (`HcmoCountryId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `hcmo_mail_configuration`
--
ALTER TABLE `hcmo_mail_configuration`
  ADD CONSTRAINT `fk_hcmo_mail_configuration_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_mail_configuration_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `hcmo_message`
--
ALTER TABLE `hcmo_message`
  ADD CONSTRAINT `fk_hcmo_message_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_message_sender` FOREIGN KEY (`sender`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_message_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `hcmo_org`
--
ALTER TABLE `hcmo_org`
  ADD CONSTRAINT `fk_hcmo_org_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_org_hcmolocationid` FOREIGN KEY (`HcmoLocationId`) REFERENCES `hcmo_location` (`HcmoLocationId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_org_hcmoorgtypeid` FOREIGN KEY (`HcmoOrgTypeId`) REFERENCES `hcmo_org_type` (`HcmoOrgTypeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_org_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `hcmo_org_type`
--
ALTER TABLE `hcmo_org_type`
  ADD CONSTRAINT `fk_hcmo_org_type_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_org_type_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `hcmo_paystub`
--
ALTER TABLE `hcmo_paystub`
  ADD CONSTRAINT `fk_hcmo_paystub_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_paystub_hcmoemployeeid` FOREIGN KEY (`HcmoEmployeeId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_paystub_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `hcmo_paystub_deductions`
--
ALTER TABLE `hcmo_paystub_deductions`
  ADD CONSTRAINT `fk_hcmo_paystub_deductions_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_paystub_deductions_hcmodeductionid` FOREIGN KEY (`HcmoDeductionId`) REFERENCES `hcmo_deductions` (`HcmoDeductionId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_paystub_deductions_hcmopaystubid` FOREIGN KEY (`HcmoPayStubId`) REFERENCES `hcmo_paystub` (`HcmoPayStubId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_paystub_deductions_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `hcmo_project`
--
ALTER TABLE `hcmo_project`
  ADD CONSTRAINT `fk_hcmo_project_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_project_hcmocustomerid` FOREIGN KEY (`HcmoCustomerId`) REFERENCES `hcmo_customer` (`HcmoCustomerId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_project_projectowner` FOREIGN KEY (`ProjectOwner`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_project_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `hcmo_project_activity`
--
ALTER TABLE `hcmo_project_activity`
  ADD CONSTRAINT `fk_hcmo_project_activity_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_project_activity_hcmodepartmentid` FOREIGN KEY (`HcmoDepartmentId`) REFERENCES `hcmo_department` (`HcmoDepartmentId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_project_activity_hcmoemployeeid` FOREIGN KEY (`HcmoEmployeeId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_project_activity_hcmoprojectid` FOREIGN KEY (`HcmoProjectId`) REFERENCES `hcmo_project` (`HcmoProjectId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_project_activity_projectactivityowner` FOREIGN KEY (`ProjectActivityOwner`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_project_activity_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `hcmo_question`
--
ALTER TABLE `hcmo_question`
  ADD CONSTRAINT `fk_hcmo_question_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_question_hcmocategoryid` FOREIGN KEY (`HcmoCategoryId`) REFERENCES `hcmo_category` (`HcmoCategoryId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_question_hcmosubcategoryid` FOREIGN KEY (`HcmoSubCategoryId`) REFERENCES `hcmo_sub_category` (`HcmoSubCategoryId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_question_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `hcmo_question_general_info`
--
ALTER TABLE `hcmo_question_general_info`
  ADD CONSTRAINT `fk_hcmo_question_general_info_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_question_general_info_hcmoApprovingEmployeeId` FOREIGN KEY (`HcmoApprovingEmployeeId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_question_general_info_hcmodepartmentid` FOREIGN KEY (`HcmoDepartmentId`) REFERENCES `hcmo_department` (`HcmoDepartmentId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_question_general_info_hcmoemployeeid` FOREIGN KEY (`HcmoEmployeeId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_question_general_info_hcmojobtitleid` FOREIGN KEY (`HcmoJobTitleId`) REFERENCES `hcmo_job_title` (`HcmoJobTitleId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_question_general_info_hcmoquestiongeneralinfogroupid` FOREIGN KEY (`HcmoQuestionGeneralInfoGroupId`) REFERENCES `hcmo_question_general_info_group` (`HcmoQuestionGeneralInfoGroupId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_question_general_info_hcmoquestiongroupnameid` FOREIGN KEY (`HcmoQuestionGroupNameId`) REFERENCES `hcmo_question_group_name` (`HcmoQuestionGroupNameId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_question_general_info_hcmoquestiongroupnameidentifiid` FOREIGN KEY (`HcmoQuestionGroupNameIdentifiId`) REFERENCES `hcmo_question_group_name_identification` (`HcmoQuestionGroupNameIdentificationId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_question_general_info_hcmoteamid` FOREIGN KEY (`HcmoTeamId`) REFERENCES `hcmo_team` (`HcmoTeamId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_question_general_info_peeremployeeid` FOREIGN KEY (`PeerEmployeeId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_question_general_info_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `hcmo_question_general_info_group`
--
ALTER TABLE `hcmo_question_general_info_group`
  ADD CONSTRAINT `fk_hcmo_employee_assigned_question_group_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_employee_assigned_question_group_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `hcmo_question_group_name`
--
ALTER TABLE `hcmo_question_group_name`
  ADD CONSTRAINT `fk_hcmo_question_group_name_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_question_group_name_hcmoQuestionGroupNameIdentifiId` FOREIGN KEY (`HcmoQuestionGroupNameIdentifiId`) REFERENCES `hcmo_question_group_name_identification` (`HcmoQuestionGroupNameIdentificationId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_question_group_name_hcmoquestionid` FOREIGN KEY (`HcmoQuestionId`) REFERENCES `hcmo_question` (`HcmoQuestionId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_question_group_name_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `hcmo_question_group_name_identification`
--
ALTER TABLE `hcmo_question_group_name_identification`
  ADD CONSTRAINT `fk_hcmo_question_group_name_identification_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_question_group_name_identification_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `hcmo_region`
--
ALTER TABLE `hcmo_region`
  ADD CONSTRAINT `fk_hcmo_region_hcmocountryid` FOREIGN KEY (`HcmoCountryId`) REFERENCES `hcmo_country` (`HcmoCountryId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `hcmo_role`
--
ALTER TABLE `hcmo_role`
  ADD CONSTRAINT `fk_hcmo_role_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_role_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `hcmo_saas_contract`
--
ALTER TABLE `hcmo_saas_contract`
  ADD CONSTRAINT `hcmo_saas_contract_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `hcmo_saas_contract_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `hcmo_salary`
--
ALTER TABLE `hcmo_salary`
  ADD CONSTRAINT `fk_hcmo_salary_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_salary_hcmoemployeeid` FOREIGN KEY (`HcmoEmployeeId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_salary_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `hcmo_salary_grade`
--
ALTER TABLE `hcmo_salary_grade`
  ADD CONSTRAINT `fk_hcmo_salary_grade_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_salary_grade_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `hcmo_shift`
--
ALTER TABLE `hcmo_shift`
  ADD CONSTRAINT `fk_hcmo_shift_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_shift_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `hcmo_signature`
--
ALTER TABLE `hcmo_signature`
  ADD CONSTRAINT `fk_hcmo_signature_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_signature_hcmoemployeeid` FOREIGN KEY (`HcmoEmployeeId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_signature_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `hcmo_sub_category`
--
ALTER TABLE `hcmo_sub_category`
  ADD CONSTRAINT `fk_hcmo_sub_category_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_sub_category_hcmocategoryid` FOREIGN KEY (`HcmoCategoryId`) REFERENCES `hcmo_category` (`HcmoCategoryId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_sub_category_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `hcmo_support`
--
ALTER TABLE `hcmo_support`
  ADD CONSTRAINT `fk_hcmo_support_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_support_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `hcmo_target`
--
ALTER TABLE `hcmo_target`
  ADD CONSTRAINT `fk_hcmo_target_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_target_hcmotargettypeid` FOREIGN KEY (`HcmoTargetTypeId`) REFERENCES `hcmo_target_type` (`HcmoTargetTypeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_target_projectactivityid` FOREIGN KEY (`HcmoProjectActivityId`) REFERENCES `hcmo_project_activity` (`HcmoProjectActivityId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_target_projectid` FOREIGN KEY (`HcmoProjectId`) REFERENCES `hcmo_project` (`HcmoProjectId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_target_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `hcmo_target_type`
--
ALTER TABLE `hcmo_target_type`
  ADD CONSTRAINT `fk_hcmo_target_type_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_target_type_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `hcmo_team`
--
ALTER TABLE `hcmo_team`
  ADD CONSTRAINT `fk_hcmo_team_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_team_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `hcmo_timesheetcat`
--
ALTER TABLE `hcmo_timesheetcat`
  ADD CONSTRAINT `fk_hcmo_timesheetcat_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_timesheetcat_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `hcmo_timesheet_notes`
--
ALTER TABLE `hcmo_timesheet_notes`
  ADD CONSTRAINT `fk_hcmo_timesheet_hcmoemployeeid` FOREIGN KEY (`HcmoEmployeeId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_timesheet_notes_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_timesheet_notes_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `hcmo_ts_achieved_target`
--
ALTER TABLE `hcmo_ts_achieved_target`
  ADD CONSTRAINT `fk_hcmo_ts_achieved_target_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_ts_achieved_target_hcmotsassignedtargetid` FOREIGN KEY (`HcmoTsAssignedTargetId`) REFERENCES `hcmo_ts_assign_proj_target` (`HcmoTsAssignProjTargetId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_ts_achieved_target_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `hcmo_ts_approver`
--
ALTER TABLE `hcmo_ts_approver`
  ADD CONSTRAINT `fk_hcmo_ts_approver_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_ts_approver_hcmoapprovingempid` FOREIGN KEY (`HcmoApprovingEmpId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_ts_approver_hcmoemployeeid` FOREIGN KEY (`HcmoEmployeeId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_ts_approver_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `hcmo_ts_assign_proj_target`
--
ALTER TABLE `hcmo_ts_assign_proj_target`
  ADD CONSTRAINT `fk_hcmo_ts_assign_proj_target_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_ts_assign_proj_target_hcmoemployeeid` FOREIGN KEY (`HcmoEmployeeId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_ts_assign_proj_target_hcmotargetid` FOREIGN KEY (`HcmoTargetId`) REFERENCES `hcmo_target` (`HcmoTargetId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_ts_assign_proj_target_hcmotsempprojrelid` FOREIGN KEY (`HcmoTsEmpProjRelId`) REFERENCES `hcmo_ts_emp_proj_rel` (`HcmoTsEmpProjRelId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_ts_assign_proj_target_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `hcmo_ts_attachment`
--
ALTER TABLE `hcmo_ts_attachment`
  ADD CONSTRAINT `fk_hcmo_ts_attachment_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_ts_attachment_hcmoemployeeid` FOREIGN KEY (`HcmoEmployeeId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_ts_attachment_hcmoprojectid` FOREIGN KEY (`HcmoProjectId`) REFERENCES `hcmo_project` (`HcmoProjectId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_ts_attachment_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `hcmo_ts_detail`
--
ALTER TABLE `hcmo_ts_detail`
  ADD CONSTRAINT `fk_hcmo_ts_detail_categoryid` FOREIGN KEY (`category_id`) REFERENCES `hcmo_timesheetcat` (`hcmo_timesheetcat_id`),
  ADD CONSTRAINT `fk_hcmo_ts_detail_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`),
  ADD CONSTRAINT `fk_hcmo_ts_detail_employeeid` FOREIGN KEY (`employee_id`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`),
  ADD CONSTRAINT `fk_hcmo_ts_detail_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`);

--
-- Constraints for table `hcmo_ts_emp_proj_rel`
--
ALTER TABLE `hcmo_ts_emp_proj_rel`
  ADD CONSTRAINT `fk_hcmo_ts_emp_proj_rel_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_ts_emp_proj_rel_hcmocustomerid` FOREIGN KEY (`HcmoCustomerId`) REFERENCES `hcmo_customer` (`HcmoCustomerId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_ts_emp_proj_rel_hcmodepartmentid` FOREIGN KEY (`HcmoDepartmentId`) REFERENCES `hcmo_department` (`HcmoDepartmentId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_ts_emp_proj_rel_hcmoemployeeid` FOREIGN KEY (`HcmoEmployeeId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_ts_emp_proj_rel_hcmoprojectactivityid` FOREIGN KEY (`HcmoProjectActivityId`) REFERENCES `hcmo_project_activity` (`HcmoProjectActivityId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_ts_emp_proj_rel_hcmoprojectid` FOREIGN KEY (`HcmoProjectId`) REFERENCES `hcmo_project` (`HcmoProjectId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_ts_emp_proj_rel_projectownerid` FOREIGN KEY (`ProjectOwnerId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_ts_emp_proj_rel_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `hcmo_ts_timetrack`
--
ALTER TABLE `hcmo_ts_timetrack`
  ADD CONSTRAINT `fk_hcmo_ts_timetrack_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_ts_timetrack_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `hcmo_user`
--
ALTER TABLE `hcmo_user`
  ADD CONSTRAINT `fk_hcmo_user_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_user_hcmoemployeeid` FOREIGN KEY (`HcmoEmployeeId`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_user_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `hcmo_vendor`
--
ALTER TABLE `hcmo_vendor`
  ADD CONSTRAINT `fk_hcmo_vendor_createdby` FOREIGN KEY (`CreatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_vendor_hcmocountryid` FOREIGN KEY (`HcmoCountryId`) REFERENCES `hcmo_country` (`HcmoCountryId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_hcmo_vendor_updatedby` FOREIGN KEY (`UpdatedBy`) REFERENCES `hcmo_employee` (`HcmoEmployeeId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `qr_applicants_addl_info`
--
ALTER TABLE `qr_applicants_addl_info`
  ADD CONSTRAINT `FKA0992AD23F998942` FOREIGN KEY (`QrEthnicRaceId`) REFERENCES `qr_ethnic_race` (`QrEthnicRaceId`),
  ADD CONSTRAINT `FKA0992AD247BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `FKA0992AD25C0693FE` FOREIGN KEY (`QrNationalityId`) REFERENCES `qr_nationality` (`QrNationalityId`),
  ADD CONSTRAINT `FKA0992AD29816EB55` FOREIGN KEY (`QrApplicantsBaseId`) REFERENCES `qr_applicants_base_info` (`QrApplicantsBaseId`),
  ADD CONSTRAINT `FKA0992AD2DF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_applicants_addl_info_ibfk_1` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_applicants_addl_info_ibfk_2` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_applicants_addl_info_ibfk_3` FOREIGN KEY (`QrApplicantsBaseId`) REFERENCES `qr_applicants_base_info` (`QrApplicantsBaseId`),
  ADD CONSTRAINT `qr_applicants_addl_info_ibfk_4` FOREIGN KEY (`QrEthnicRaceId`) REFERENCES `qr_ethnic_race` (`QrEthnicRaceId`),
  ADD CONSTRAINT `qr_applicants_addl_info_ibfk_6` FOREIGN KEY (`QrNationalityId`) REFERENCES `qr_nationality` (`QrNationalityId`);

--
-- Constraints for table `qr_applicants_attach`
--
ALTER TABLE `qr_applicants_attach`
  ADD CONSTRAINT `FKF68F21F547BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `FKF68F21F59816EB55` FOREIGN KEY (`QrApplicantsBaseId`) REFERENCES `qr_applicants_base_info` (`QrApplicantsBaseId`),
  ADD CONSTRAINT `FKF68F21F5DF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_applicants_attach_ibfk_1` FOREIGN KEY (`QrApplicantsBaseId`) REFERENCES `qr_applicants_base_info` (`QrApplicantsBaseId`),
  ADD CONSTRAINT `qr_applicants_attach_ibfk_2` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_applicants_attach_ibfk_3` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`);

--
-- Constraints for table `qr_applicants_base_info`
--
ALTER TABLE `qr_applicants_base_info`
  ADD CONSTRAINT `FKAC59FAC3FFA5283` FOREIGN KEY (`QrVendorBaseId`) REFERENCES `qr_vendor_base_info` (`QrVendorBaseId`),
  ADD CONSTRAINT `FKAC59FAC47BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `FKAC59FACBC4D3B2` FOREIGN KEY (`QrCountryId`) REFERENCES `qr_country` (`QrCountryId`),
  ADD CONSTRAINT `FKAC59FACDF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_applicants_base_info_ibfk_1` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_applicants_base_info_ibfk_2` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_applicants_base_info_ibfk_3` FOREIGN KEY (`QrVendorBaseId`) REFERENCES `qr_vendor_base_info` (`QrVendorBaseId`),
  ADD CONSTRAINT `qr_applicants_base_info_ibfk_4` FOREIGN KEY (`QrCountryId`) REFERENCES `qr_country` (`QrCountryId`);

--
-- Constraints for table `qr_applicants_broadcast`
--
ALTER TABLE `qr_applicants_broadcast`
  ADD CONSTRAINT `FK1690D71126FAA32C` FOREIGN KEY (`QrHrUserId`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `FK1690D71147BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `FK1690D7116D2BC58C` FOREIGN KEY (`QrRequirementId`) REFERENCES `qr_requirement` (`QrRequirementId`),
  ADD CONSTRAINT `FK1690D7119816EB55` FOREIGN KEY (`QrApplicantsBaseId`) REFERENCES `qr_applicants_base_info` (`QrApplicantsBaseId`),
  ADD CONSTRAINT `FK1690D711DF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_applicants_broadcast_ibfk_1` FOREIGN KEY (`QrHrUserId`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_applicants_broadcast_ibfk_2` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_applicants_broadcast_ibfk_3` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_applicants_broadcast_ibfk_4` FOREIGN KEY (`QrRequirementId`) REFERENCES `qr_requirement` (`QrRequirementId`),
  ADD CONSTRAINT `qr_applicants_broadcast_ibfk_5` FOREIGN KEY (`QrApplicantsBaseId`) REFERENCES `qr_applicants_base_info` (`QrApplicantsBaseId`);

--
-- Constraints for table `qr_applicants_formatted_attach`
--
ALTER TABLE `qr_applicants_formatted_attach`
  ADD CONSTRAINT `FK57D286D847BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `FK57D286D89816EB55` FOREIGN KEY (`QrApplicantsBaseId`) REFERENCES `qr_applicants_base_info` (`QrApplicantsBaseId`),
  ADD CONSTRAINT `FK57D286D8DF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_applicants_formatted_attach_ibfk_1` FOREIGN KEY (`QrApplicantsBaseId`) REFERENCES `qr_applicants_base_info` (`QrApplicantsBaseId`),
  ADD CONSTRAINT `qr_applicants_formatted_attach_ibfk_2` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_applicants_formatted_attach_ibfk_3` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`);

--
-- Constraints for table `qr_applicants_notes`
--
ALTER TABLE `qr_applicants_notes`
  ADD CONSTRAINT `FKF824E51126FAA32C` FOREIGN KEY (`QrHrUserId`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `FKF824E51147BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `FKF824E5116D2BC58C` FOREIGN KEY (`QrRequirementId`) REFERENCES `qr_requirement` (`QrRequirementId`),
  ADD CONSTRAINT `FKF824E5119816EB55` FOREIGN KEY (`QrApplicantsBaseId`) REFERENCES `qr_applicants_base_info` (`QrApplicantsBaseId`),
  ADD CONSTRAINT `FKF824E511DF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_applicants_notes_ibfk_1` FOREIGN KEY (`QrHrUserId`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_applicants_notes_ibfk_2` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_applicants_notes_ibfk_3` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_applicants_notes_ibfk_4` FOREIGN KEY (`QrApplicantsBaseId`) REFERENCES `qr_applicants_base_info` (`QrApplicantsBaseId`),
  ADD CONSTRAINT `qr_applicants_notes_ibfk_5` FOREIGN KEY (`QrRequirementId`) REFERENCES `qr_requirement` (`QrRequirementId`);

--
-- Constraints for table `qr_applicants_notes_attach`
--
ALTER TABLE `qr_applicants_notes_attach`
  ADD CONSTRAINT `FKF802613347BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `FKF8026133C02B94C6` FOREIGN KEY (`QrApplicantsNotesId`) REFERENCES `qr_applicants_notes` (`QrApplicantsNotesId`),
  ADD CONSTRAINT `FKF8026133DF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_applicants_notes_attach_ibfk_1` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_applicants_notes_attach_ibfk_2` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_applicants_notes_attach_ibfk_3` FOREIGN KEY (`QrApplicantsNotesId`) REFERENCES `qr_applicants_notes` (`QrApplicantsNotesId`);

--
-- Constraints for table `qr_applicants_privilege_current`
--
ALTER TABLE `qr_applicants_privilege_current`
  ADD CONSTRAINT `FK9B54B83B47BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `FK9B54B83B8150C9BA` FOREIGN KEY (`PrivHrUserId`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `FK9B54B83B9816EB55` FOREIGN KEY (`QrApplicantsBaseId`) REFERENCES `qr_applicants_base_info` (`QrApplicantsBaseId`),
  ADD CONSTRAINT `FK9B54B83BDF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_applicants_privilege_current_ibfk_1` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_applicants_privilege_current_ibfk_2` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_applicants_privilege_current_ibfk_3` FOREIGN KEY (`QrApplicantsBaseId`) REFERENCES `qr_applicants_base_info` (`QrApplicantsBaseId`);

--
-- Constraints for table `qr_applicants_privilege_history`
--
ALTER TABLE `qr_applicants_privilege_history`
  ADD CONSTRAINT `FK8F68DF5626FAA32C` FOREIGN KEY (`QrHrUserId`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `FK8F68DF5647BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `FK8F68DF566D2BC58C` FOREIGN KEY (`QrRequirementId`) REFERENCES `qr_requirement` (`QrRequirementId`),
  ADD CONSTRAINT `FK8F68DF569816EB55` FOREIGN KEY (`QrApplicantsBaseId`) REFERENCES `qr_applicants_base_info` (`QrApplicantsBaseId`),
  ADD CONSTRAINT `FK8F68DF56DF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_applicants_previlage_history_ibfk_1` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_applicants_previlage_history_ibfk_2` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_applicants_previlage_history_ibfk_3` FOREIGN KEY (`QrHrUserId`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_applicants_previlage_history_ibfk_4` FOREIGN KEY (`QrApplicantsBaseId`) REFERENCES `qr_applicants_base_info` (`QrApplicantsBaseId`),
  ADD CONSTRAINT `qr_applicants_previlage_history_ibfk_5` FOREIGN KEY (`QrRequirementId`) REFERENCES `qr_requirement` (`QrRequirementId`);

--
-- Constraints for table `qr_applicants_req_mail`
--
ALTER TABLE `qr_applicants_req_mail`
  ADD CONSTRAINT `FK568EC06847BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `FK568EC0686D2BC58C` FOREIGN KEY (`QrRequirementId`) REFERENCES `qr_requirement` (`QrRequirementId`),
  ADD CONSTRAINT `FK568EC0689816EB55` FOREIGN KEY (`QrApplicantsBaseId`) REFERENCES `qr_applicants_base_info` (`QrApplicantsBaseId`),
  ADD CONSTRAINT `FK568EC068DF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_applicants_req_mail_ibfk_1` FOREIGN KEY (`QrApplicantsBaseId`) REFERENCES `qr_applicants_base_info` (`QrApplicantsBaseId`),
  ADD CONSTRAINT `qr_applicants_req_mail_ibfk_2` FOREIGN KEY (`QrRequirementId`) REFERENCES `qr_requirement` (`QrRequirementId`),
  ADD CONSTRAINT `qr_applicants_req_mail_ibfk_3` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_applicants_req_mail_ibfk_4` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`);

--
-- Constraints for table `qr_applicants_skill_matrix`
--
ALTER TABLE `qr_applicants_skill_matrix`
  ADD CONSTRAINT `FK95E56D1F47BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `FK95E56D1F9816EB55` FOREIGN KEY (`QrApplicantsBaseId`) REFERENCES `qr_applicants_base_info` (`QrApplicantsBaseId`),
  ADD CONSTRAINT `FK95E56D1FDF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_applicants_skill_matrixFK1` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_applicants_skill_matrixFK2` FOREIGN KEY (`QrApplicantsBaseId`) REFERENCES `qr_applicants_base_info` (`QrApplicantsBaseId`),
  ADD CONSTRAINT `qr_applicants_skill_matrixFK3` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`);

--
-- Constraints for table `qr_applicants_submission`
--
ALTER TABLE `qr_applicants_submission`
  ADD CONSTRAINT `FKEF0516FC26FAA32C` FOREIGN KEY (`QrHrUserId`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `FKEF0516FC3FFA5283` FOREIGN KEY (`QrVendorBaseId`) REFERENCES `qr_vendor_base_info` (`QrVendorBaseId`),
  ADD CONSTRAINT `FKEF0516FC47BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `FKEF0516FC6D2BC58C` FOREIGN KEY (`QrRequirementId`) REFERENCES `qr_requirement` (`QrRequirementId`),
  ADD CONSTRAINT `FKEF0516FC9816EB55` FOREIGN KEY (`QrApplicantsBaseId`) REFERENCES `qr_applicants_base_info` (`QrApplicantsBaseId`),
  ADD CONSTRAINT `FKEF0516FCC02B94C6` FOREIGN KEY (`QrApplicantsNotesId`) REFERENCES `qr_applicants_notes` (`QrApplicantsNotesId`),
  ADD CONSTRAINT `FKEF0516FCDF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `FKEF0516FCE5144E88` FOREIGN KEY (`QrApplicationReceivedId`) REFERENCES `qr_application_received` (`QrApplicationReceivedId`),
  ADD CONSTRAINT `qr_applicants_submission_ibfk_1` FOREIGN KEY (`QrApplicantsBaseId`) REFERENCES `qr_applicants_base_info` (`QrApplicantsBaseId`),
  ADD CONSTRAINT `qr_applicants_submission_ibfk_2` FOREIGN KEY (`QrHrUserId`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_applicants_submission_ibfk_3` FOREIGN KEY (`QrRequirementId`) REFERENCES `qr_requirement` (`QrRequirementId`),
  ADD CONSTRAINT `qr_applicants_submission_ibfk_4` FOREIGN KEY (`QrVendorBaseId`) REFERENCES `qr_vendor_base_info` (`QrVendorBaseId`),
  ADD CONSTRAINT `qr_applicants_submission_ibfk_5` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_applicants_submission_ibfk_6` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_applicants_submission_ibfk_7` FOREIGN KEY (`QrApplicantsNotesId`) REFERENCES `qr_applicants_notes` (`QrApplicantsNotesId`),
  ADD CONSTRAINT `qr_applicants_submission_ibfk_8` FOREIGN KEY (`QrApplicationReceivedId`) REFERENCES `qr_application_received` (`QrApplicationReceivedId`);

--
-- Constraints for table `qr_applicants_trai_cert`
--
ALTER TABLE `qr_applicants_trai_cert`
  ADD CONSTRAINT `FKAC29C5ED47BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `FKAC29C5ED9816EB55` FOREIGN KEY (`QrApplicantsBaseId`) REFERENCES `qr_applicants_base_info` (`QrApplicantsBaseId`),
  ADD CONSTRAINT `FKAC29C5EDDF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_applicants_trai_cert_ibfk_1` FOREIGN KEY (`QrApplicantsBaseId`) REFERENCES `qr_applicants_base_info` (`QrApplicantsBaseId`),
  ADD CONSTRAINT `qr_applicants_trai_cert_ibfk_2` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_applicants_trai_cert_ibfk_3` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`);

--
-- Constraints for table `qr_application_received`
--
ALTER TABLE `qr_application_received`
  ADD CONSTRAINT `FK8B6AEB4E47BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `FK8B6AEB4E6D2BC58C` FOREIGN KEY (`QrRequirementId`) REFERENCES `qr_requirement` (`QrRequirementId`),
  ADD CONSTRAINT `FK8B6AEB4E9816EB55` FOREIGN KEY (`QrApplicantsBaseId`) REFERENCES `qr_applicants_base_info` (`QrApplicantsBaseId`),
  ADD CONSTRAINT `FK8B6AEB4EDF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_application_received_ibfk_1` FOREIGN KEY (`QrApplicantsBaseId`) REFERENCES `qr_applicants_base_info` (`QrApplicantsBaseId`),
  ADD CONSTRAINT `qr_application_received_ibfk_2` FOREIGN KEY (`QrRequirementId`) REFERENCES `qr_requirement` (`QrRequirementId`);

--
-- Constraints for table `qr_assigned_requirement`
--
ALTER TABLE `qr_assigned_requirement`
  ADD CONSTRAINT `FK7581DEB047BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `FK7581DEB06D2BC58C` FOREIGN KEY (`QrRequirementId`) REFERENCES `qr_requirement` (`QrRequirementId`),
  ADD CONSTRAINT `FK7581DEB0DF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `FK7581DEB0F911477C` FOREIGN KEY (`AssignById`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `FK7581DEB0F91950A0` FOREIGN KEY (`AssignToId`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_assigned_requirement_ibfk_1` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_assigned_requirement_ibfk_2` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_assigned_requirement_ibfk_3` FOREIGN KEY (`QrRequirementId`) REFERENCES `qr_requirement` (`QrRequirementId`),
  ADD CONSTRAINT `qr_assigned_requirement_ibfk_4` FOREIGN KEY (`AssignToId`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_assigned_requirement_ibfk_5` FOREIGN KEY (`AssignById`) REFERENCES `qr_hr_user_info` (`QrHrUserId`);

--
-- Constraints for table `qr_city`
--
ALTER TABLE `qr_city`
  ADD CONSTRAINT `FK2191F0C9F6874CA8` FOREIGN KEY (`QrStateId`) REFERENCES `qr_state` (`QrStateId`),
  ADD CONSTRAINT `qr_city_ibfk_1` FOREIGN KEY (`QrStateId`) REFERENCES `qr_state` (`QrStateId`);

--
-- Constraints for table `qr_client_base_info`
--
ALTER TABLE `qr_client_base_info`
  ADD CONSTRAINT `FK22C5D84647BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `FK22C5D846BC4D3B2` FOREIGN KEY (`QrCountryId`) REFERENCES `qr_country` (`QrCountryId`),
  ADD CONSTRAINT `FK22C5D846DF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_client_base_info_ibfk_1` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_client_base_info_ibfk_2` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_client_base_info_ibfk_3` FOREIGN KEY (`QrCountryId`) REFERENCES `qr_country` (`QrCountryId`);

--
-- Constraints for table `qr_client_broadcast`
--
ALTER TABLE `qr_client_broadcast`
  ADD CONSTRAINT `FK2E910FAB44E72C89` FOREIGN KEY (`QrClientBaseId`) REFERENCES `qr_client_base_info` (`QrClientBaseId`),
  ADD CONSTRAINT `FK2E910FAB47BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `FK2E910FAB9816EB55` FOREIGN KEY (`QrApplicantsBaseId`) REFERENCES `qr_applicants_base_info` (`QrApplicantsBaseId`),
  ADD CONSTRAINT `FK2E910FABDF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_direct_client_ibfk_1` FOREIGN KEY (`QrApplicantsBaseId`) REFERENCES `qr_applicants_base_info` (`QrApplicantsBaseId`),
  ADD CONSTRAINT `qr_direct_client_ibfk_3` FOREIGN KEY (`QrClientBaseId`) REFERENCES `qr_client_base_info` (`QrClientBaseId`),
  ADD CONSTRAINT `qr_direct_client_ibfk_4` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_direct_client_ibfk_5` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`);

--
-- Constraints for table `qr_client_contact_info`
--
ALTER TABLE `qr_client_contact_info`
  ADD CONSTRAINT `FK4068064344E72C89` FOREIGN KEY (`QrClientBaseId`) REFERENCES `qr_client_base_info` (`QrClientBaseId`),
  ADD CONSTRAINT `FK4068064347BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `FK40680643DF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_client_contact_info_ibfk_1` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_client_contact_info_ibfk_2` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_client_contact_info_ibfk_3` FOREIGN KEY (`QrClientBaseId`) REFERENCES `qr_client_base_info` (`QrClientBaseId`);

--
-- Constraints for table `qr_client_notes`
--
ALTER TABLE `qr_client_notes`
  ADD CONSTRAINT `FK7FFD7AAB26FAA32C` FOREIGN KEY (`QrHrUserId`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `FK7FFD7AAB44E72C89` FOREIGN KEY (`QrClientBaseId`) REFERENCES `qr_client_base_info` (`QrClientBaseId`),
  ADD CONSTRAINT `FK7FFD7AAB47BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `FK7FFD7AABDF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `FK7FFD7AABF7E6CB50` FOREIGN KEY (`QrClientContactId`) REFERENCES `qr_client_contact_info` (`QrClientContactId`),
  ADD CONSTRAINT `qr_client_notes_ibfk_1` FOREIGN KEY (`QrClientBaseId`) REFERENCES `qr_client_base_info` (`QrClientBaseId`),
  ADD CONSTRAINT `qr_client_notes_ibfk_2` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_client_notes_ibfk_3` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_client_notes_ibfk_4` FOREIGN KEY (`QrClientContactId`) REFERENCES `qr_client_contact_info` (`QrClientContactId`);

--
-- Constraints for table `qr_client_submission`
--
ALTER TABLE `qr_client_submission`
  ADD CONSTRAINT `FKD70BF1A226FAA32C` FOREIGN KEY (`QrHrUserId`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `FKD70BF1A244E72C89` FOREIGN KEY (`QrClientBaseId`) REFERENCES `qr_client_base_info` (`QrClientBaseId`),
  ADD CONSTRAINT `FKD70BF1A247BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `FKD70BF1A26D2BC58C` FOREIGN KEY (`QrRequirementId`) REFERENCES `qr_requirement` (`QrRequirementId`),
  ADD CONSTRAINT `FKD70BF1A29816EB55` FOREIGN KEY (`QrApplicantsBaseId`) REFERENCES `qr_applicants_base_info` (`QrApplicantsBaseId`),
  ADD CONSTRAINT `FKD70BF1A2DF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `FKD70BF1A2E5144E88` FOREIGN KEY (`QrApplicationReceivedId`) REFERENCES `qr_application_received` (`QrApplicationReceivedId`),
  ADD CONSTRAINT `qr_client_submission_ibfk_2` FOREIGN KEY (`QrClientBaseId`) REFERENCES `qr_client_base_info` (`QrClientBaseId`),
  ADD CONSTRAINT `qr_client_submission_ibfk_4` FOREIGN KEY (`QrHrUserId`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_client_submission_ibfk_5` FOREIGN KEY (`QrRequirementId`) REFERENCES `qr_requirement` (`QrRequirementId`),
  ADD CONSTRAINT `qr_client_submission_ibfk_6` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_client_submission_ibfk_7` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_client_submission_ibfk_8` FOREIGN KEY (`QrApplicantsBaseId`) REFERENCES `qr_applicants_base_info` (`QrApplicantsBaseId`),
  ADD CONSTRAINT `qr_client_submission_ibfk_9` FOREIGN KEY (`QrApplicationReceivedId`) REFERENCES `qr_application_received` (`QrApplicationReceivedId`);

--
-- Constraints for table `qr_corp_corp_feed_jobs`
--
ALTER TABLE `qr_corp_corp_feed_jobs`
  ADD CONSTRAINT `FK443B961926FAA32C` FOREIGN KEY (`QrHrUserId`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `FK443B961947BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `FK443B96196D2BC58C` FOREIGN KEY (`QrRequirementId`) REFERENCES `qr_requirement` (`QrRequirementId`),
  ADD CONSTRAINT `FK443B9619DF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_corp_corp_feed_jobs_ibfk_1` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_corp_corp_feed_jobs_ibfk_2` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_corp_corp_feed_jobs_ibfk_3` FOREIGN KEY (`QrHrUserId`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_corp_corp_feed_jobs_ibfk_4` FOREIGN KEY (`QrRequirementId`) REFERENCES `qr_requirement` (`QrRequirementId`);

--
-- Constraints for table `qr_educational_qualification`
--
ALTER TABLE `qr_educational_qualification`
  ADD CONSTRAINT `FKEFCFBE547BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `FKEFCFBE5DF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_educational_qualification_ibfk_1` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_educational_qualification_ibfk_2` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`);

--
-- Constraints for table `qr_email_send_to_client`
--
ALTER TABLE `qr_email_send_to_client`
  ADD CONSTRAINT `FK94E9847947BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `FK94E9847973716514` FOREIGN KEY (`SubmissionMailedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `FK94E98479DF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_email_send_to_client_ibfk_1` FOREIGN KEY (`SubmissionMailedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_email_send_to_client_ibfk_3` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_email_send_to_client_ibfk_4` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`);

--
-- Constraints for table `qr_ethnic_race`
--
ALTER TABLE `qr_ethnic_race`
  ADD CONSTRAINT `FK7309822347BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `FK73098223DF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_ethnic_race_ibfk_1` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_ethnic_race_ibfk_2` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`);

--
-- Constraints for table `qr_hr_user_info`
--
ALTER TABLE `qr_hr_user_info`
  ADD CONSTRAINT `FKEAB8B50B47BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `FKEAB8B50BBC4D3B2` FOREIGN KEY (`QrCountryId`) REFERENCES `qr_country` (`QrCountryId`),
  ADD CONSTRAINT `FKEAB8B50BDF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_hr_user_info_ibfk_1` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_hr_user_info_ibfk_2` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_hr_user_info_ibfk_3` FOREIGN KEY (`QrRoleId`) REFERENCES `qr_role` (`QrRoleId`),
  ADD CONSTRAINT `qr_hr_user_info_ibfk_4` FOREIGN KEY (`QrCountryId`) REFERENCES `qr_country` (`QrCountryId`);

--
-- Constraints for table `qr_hr_user_login`
--
ALTER TABLE `qr_hr_user_login`
  ADD CONSTRAINT `FK6C88AAAC26FAA32C` FOREIGN KEY (`QrHrUserId`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `FK6C88AAAC47BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `FK6C88AAACDF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_hr_user_login_ibfk_1` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_hr_user_login_ibfk_2` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_hr_user_login_ibfk_3` FOREIGN KEY (`QrHrUserId`) REFERENCES `qr_hr_user_info` (`QrHrUserId`);

--
-- Constraints for table `qr_idiligo_config`
--
ALTER TABLE `qr_idiligo_config`
  ADD CONSTRAINT `FK62D4B80C47BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `FK62D4B80CDF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`);

--
-- Constraints for table `qr_import_resumes`
--
ALTER TABLE `qr_import_resumes`
  ADD CONSTRAINT `FK4AB11D2A26FAA32C` FOREIGN KEY (`QrHrUserId`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `FK4AB11D2A47BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `FK4AB11D2ADF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_import_resumes_ibfk_1` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_import_resumes_ibfk_2` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_import_resumes_ibfk_3` FOREIGN KEY (`QrHrUserId`) REFERENCES `qr_hr_user_info` (`QrHrUserId`);

--
-- Constraints for table `qr_job_board_config`
--
ALTER TABLE `qr_job_board_config`
  ADD CONSTRAINT `FK9261E65B26FAA32C` FOREIGN KEY (`QrHrUserId`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `FK9261E65B47BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `FK9261E65BDF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_job_board_config_ibfk_1` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_job_board_config_ibfk_2` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_job_board_config_ibfk_3` FOREIGN KEY (`QrHrUserId`) REFERENCES `qr_hr_user_info` (`QrHrUserId`);

--
-- Constraints for table `qr_job_board_config_corp_corp`
--
ALTER TABLE `qr_job_board_config_corp_corp`
  ADD CONSTRAINT `FK8E6EDE3B26FAA32C` FOREIGN KEY (`QrHrUserId`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `FK8E6EDE3B47BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `FK8E6EDE3BDF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_job_board_config_corp_corp_ibfk_1` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_job_board_config_corp_corp_ibfk_2` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_job_board_config_corp_corp_ibfk_3` FOREIGN KEY (`QrHrUserId`) REFERENCES `qr_hr_user_info` (`QrHrUserId`);

--
-- Constraints for table `qr_job_board_config_indeed`
--
ALTER TABLE `qr_job_board_config_indeed`
  ADD CONSTRAINT `qr_job_board_config_indeed_ibfk_1` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_job_board_config_indeed_ibfk_2` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_job_board_config_indeed_ibfk_3` FOREIGN KEY (`QrHrUserId`) REFERENCES `qr_hr_user_info` (`QrHrUserId`);

--
-- Constraints for table `qr_job_board_post_jobs`
--
ALTER TABLE `qr_job_board_post_jobs`
  ADD CONSTRAINT `FK56087ADC26FAA32C` FOREIGN KEY (`QrHrUserId`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `FK56087ADC47BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `FK56087ADC6D2BC58C` FOREIGN KEY (`QrRequirementId`) REFERENCES `qr_requirement` (`QrRequirementId`),
  ADD CONSTRAINT `FK56087ADCDF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_corp_corp_post_jobs_ibfk_4` FOREIGN KEY (`QrRequirementId`) REFERENCES `qr_requirement` (`QrRequirementId`),
  ADD CONSTRAINT `qr_job_board_post_jobs_ibfk_1` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_job_board_post_jobs_ibfk_2` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_job_board_post_jobs_ibfk_3` FOREIGN KEY (`QrHrUserId`) REFERENCES `qr_hr_user_info` (`QrHrUserId`);

--
-- Constraints for table `qr_location`
--
ALTER TABLE `qr_location`
  ADD CONSTRAINT `FKF85C181347BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `FKF85C1813BC4D3B2` FOREIGN KEY (`QrCountryId`) REFERENCES `qr_country` (`QrCountryId`),
  ADD CONSTRAINT `FKF85C1813DF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_location_ibfk_1` FOREIGN KEY (`QrCountryId`) REFERENCES `qr_country` (`QrCountryId`),
  ADD CONSTRAINT `qr_location_ibfk_2` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_location_ibfk_3` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`);

--
-- Constraints for table `qr_logo`
--
ALTER TABLE `qr_logo`
  ADD CONSTRAINT `FK21961D0947BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `FK21961D09DF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_logo_ibfk_1` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_logo_ibfk_2` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`);

--
-- Constraints for table `qr_mail_configuration`
--
ALTER TABLE `qr_mail_configuration`
  ADD CONSTRAINT `FKFC309B4C26FAA32C` FOREIGN KEY (`QrHrUserId`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `FKFC309B4C47BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `FKFC309B4CDF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `hcmo_mail_configuration_ibfk_1` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `hcmo_mail_configuration_ibfk_2` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `rlite_mail_configuration_ibfk_3` FOREIGN KEY (`QrHrUserId`) REFERENCES `qr_hr_user_info` (`QrHrUserId`);

--
-- Constraints for table `qr_nationality`
--
ALTER TABLE `qr_nationality`
  ADD CONSTRAINT `FKC9B4009E47BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `FKC9B4009EDF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_nationality_ibfk_1` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_nationality_ibfk_2` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`);

--
-- Constraints for table `qr_on_off_board_applicants`
--
ALTER TABLE `qr_on_off_board_applicants`
  ADD CONSTRAINT `FKC2CD1BBC26FAA32C` FOREIGN KEY (`QrHrUserId`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `FKC2CD1BBC47BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `FKC2CD1BBC6D2BC58C` FOREIGN KEY (`QrRequirementId`) REFERENCES `qr_requirement` (`QrRequirementId`),
  ADD CONSTRAINT `FKC2CD1BBC9816EB55` FOREIGN KEY (`QrApplicantsBaseId`) REFERENCES `qr_applicants_base_info` (`QrApplicantsBaseId`),
  ADD CONSTRAINT `FKC2CD1BBCDF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`);

--
-- Constraints for table `qr_org`
--
ALTER TABLE `qr_org`
  ADD CONSTRAINT `FKC746F3C647BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `FKC746F3C69DECF34C` FOREIGN KEY (`QrLocationId`) REFERENCES `qr_location` (`QrLocationId`),
  ADD CONSTRAINT `FKC746F3C6BD54C3EB` FOREIGN KEY (`QrOrgTypeId`) REFERENCES `qr_org_type` (`QrOrgTypeId`),
  ADD CONSTRAINT `FKC746F3C6DF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`);

--
-- Constraints for table `qr_org_type`
--
ALTER TABLE `qr_org_type`
  ADD CONSTRAINT `FKD569C01347BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `FKD569C013DF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_org_type_ibfk_1` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_org_type_ibfk_2` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`);

--
-- Constraints for table `qr_privilege`
--
ALTER TABLE `qr_privilege`
  ADD CONSTRAINT `FKFC7D8A1347BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `FKFC7D8A13DF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`);

--
-- Constraints for table `qr_requirement`
--
ALTER TABLE `qr_requirement`
  ADD CONSTRAINT `FKD9D41F2544E72C89` FOREIGN KEY (`QrClientBaseId`) REFERENCES `qr_client_base_info` (`QrClientBaseId`),
  ADD CONSTRAINT `FKD9D41F2547BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `FKD9D41F25BC4D3B2` FOREIGN KEY (`QrCountryId`) REFERENCES `qr_country` (`QrCountryId`),
  ADD CONSTRAINT `FKD9D41F25DF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_requirement_ibfk_1` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_requirement_ibfk_2` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_requirement_ibfk_3` FOREIGN KEY (`QrClientBaseId`) REFERENCES `qr_client_base_info` (`QrClientBaseId`),
  ADD CONSTRAINT `qr_requirement_ibfk_4` FOREIGN KEY (`QrCountryId`) REFERENCES `qr_country` (`QrCountryId`);

--
-- Constraints for table `qr_req_attach`
--
ALTER TABLE `qr_req_attach`
  ADD CONSTRAINT `FK6577BDE447BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `FK6577BDE46D2BC58C` FOREIGN KEY (`QrRequirementId`) REFERENCES `qr_requirement` (`QrRequirementId`),
  ADD CONSTRAINT `FK6577BDE4DF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`);

--
-- Constraints for table `qr_role`
--
ALTER TABLE `qr_role`
  ADD CONSTRAINT `FK2198D7D447BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `FK2198D7D4DF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`);

--
-- Constraints for table `qr_saas_contract`
--
ALTER TABLE `qr_saas_contract`
  ADD CONSTRAINT `FK80D175D347BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `FK80D175D3DF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_saas_contract_ibfk_1` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_saas_contract_ibfk_2` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`);

--
-- Constraints for table `qr_signature`
--
ALTER TABLE `qr_signature`
  ADD CONSTRAINT `FK9A802C1A26FAA32C` FOREIGN KEY (`QrHrUserId`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `FK9A802C1A47BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `FK9A802C1ADF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_signature_info_ibfk_1` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_signature_info_ibfk_2` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_signature_info_ibfk_3` FOREIGN KEY (`QrHrUserId`) REFERENCES `qr_hr_user_info` (`QrHrUserId`);

--
-- Constraints for table `qr_state`
--
ALTER TABLE `qr_state`
  ADD CONSTRAINT `FK119258F3BC4D3B2` FOREIGN KEY (`QrCountryId`) REFERENCES `qr_country` (`QrCountryId`),
  ADD CONSTRAINT `qr_state_ibfk_1` FOREIGN KEY (`QrCountryId`) REFERENCES `qr_country` (`QrCountryId`);

--
-- Constraints for table `qr_submission_docs`
--
ALTER TABLE `qr_submission_docs`
  ADD CONSTRAINT `FK47BBD13026FAA32C` FOREIGN KEY (`QrHrUserId`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `FK47BBD13044E72C89` FOREIGN KEY (`QrClientBaseId`) REFERENCES `qr_client_base_info` (`QrClientBaseId`),
  ADD CONSTRAINT `FK47BBD13047BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `FK47BBD1306D2BC58C` FOREIGN KEY (`QrRequirementId`) REFERENCES `qr_requirement` (`QrRequirementId`),
  ADD CONSTRAINT `FK47BBD1309816EB55` FOREIGN KEY (`QrApplicantsBaseId`) REFERENCES `qr_applicants_base_info` (`QrApplicantsBaseId`),
  ADD CONSTRAINT `FK47BBD13099A533B0` FOREIGN KEY (`QrClientSubmissionId`) REFERENCES `qr_client_submission` (`QrClientSubmissionId`),
  ADD CONSTRAINT `FK47BBD130DF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_submission_docs_ibfk_1` FOREIGN KEY (`QrApplicantsBaseId`) REFERENCES `qr_applicants_base_info` (`QrApplicantsBaseId`),
  ADD CONSTRAINT `qr_submission_docs_ibfk_2` FOREIGN KEY (`QrRequirementId`) REFERENCES `qr_requirement` (`QrRequirementId`),
  ADD CONSTRAINT `qr_submission_docs_ibfk_3` FOREIGN KEY (`QrClientBaseId`) REFERENCES `qr_client_base_info` (`QrClientBaseId`),
  ADD CONSTRAINT `qr_submission_docs_ibfk_4` FOREIGN KEY (`QrHrUserId`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_submission_docs_ibfk_5` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_submission_docs_ibfk_6` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_submission_docs_ibfk_8` FOREIGN KEY (`QrClientSubmissionId`) REFERENCES `qr_client_submission` (`QrClientSubmissionId`);

--
-- Constraints for table `qr_support`
--
ALTER TABLE `qr_support`
  ADD CONSTRAINT `FKF8E683B147BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `FKF8E683B1DF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_support_ibfk_1` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_support_ibfk_2` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`);

--
-- Constraints for table `qr_support_docs`
--
ALTER TABLE `qr_support_docs`
  ADD CONSTRAINT `FK6526FE0947BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `FK6526FE096828CFA4` FOREIGN KEY (`QrSupportId`) REFERENCES `qr_support` (`QrSupportId`),
  ADD CONSTRAINT `FK6526FE09DF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_support_docs_1` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_support_docs_2` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_support_docs_3` FOREIGN KEY (`QrSupportId`) REFERENCES `qr_support` (`QrSupportId`);

--
-- Constraints for table `qr_vendor_base_info`
--
ALTER TABLE `qr_vendor_base_info`
  ADD CONSTRAINT `FKC453918347BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `FKC4539183BC4D3B2` FOREIGN KEY (`QrCountryId`) REFERENCES `qr_country` (`QrCountryId`),
  ADD CONSTRAINT `FKC4539183DF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_vendor_base_info_ibfk_1` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_vendor_base_info_ibfk_2` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_vendor_base_info_ibfk_3` FOREIGN KEY (`QrCountryId`) REFERENCES `qr_country` (`QrCountryId`);

--
-- Constraints for table `qr_vendor_broadcast`
--
ALTER TABLE `qr_vendor_broadcast`
  ADD CONSTRAINT `FKD01EC8E83FFA5283` FOREIGN KEY (`QrVendorBaseId`) REFERENCES `qr_vendor_base_info` (`QrVendorBaseId`),
  ADD CONSTRAINT `FKD01EC8E847BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `FKD01EC8E86D2BC58C` FOREIGN KEY (`QrRequirementId`) REFERENCES `qr_requirement` (`QrRequirementId`),
  ADD CONSTRAINT `FKD01EC8E8DF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_vendor_broadcast_ibfk_1` FOREIGN KEY (`QrRequirementId`) REFERENCES `qr_requirement` (`QrRequirementId`),
  ADD CONSTRAINT `qr_vendor_broadcast_ibfk_3` FOREIGN KEY (`QrVendorBaseId`) REFERENCES `qr_vendor_base_info` (`QrVendorBaseId`),
  ADD CONSTRAINT `qr_vendor_broadcast_ibfk_4` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_vendor_broadcast_ibfk_5` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`);

--
-- Constraints for table `qr_vendor_contact_info`
--
ALTER TABLE `qr_vendor_contact_info`
  ADD CONSTRAINT `FK6BEF67E63FFA5283` FOREIGN KEY (`QrVendorBaseId`) REFERENCES `qr_vendor_base_info` (`QrVendorBaseId`),
  ADD CONSTRAINT `FK6BEF67E647BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `FK6BEF67E6DF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_vendor_contact_info_ibfk_1` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_vendor_contact_info_ibfk_2` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_vendor_contact_info_ibfk_3` FOREIGN KEY (`QrVendorBaseId`) REFERENCES `qr_vendor_base_info` (`QrVendorBaseId`);

--
-- Constraints for table `qr_vendor_notes`
--
ALTER TABLE `qr_vendor_notes`
  ADD CONSTRAINT `FK2F2B5A6826FAA32C` FOREIGN KEY (`QrHrUserId`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `FK2F2B5A683FFA5283` FOREIGN KEY (`QrVendorBaseId`) REFERENCES `qr_vendor_base_info` (`QrVendorBaseId`),
  ADD CONSTRAINT `FK2F2B5A6847BC20AD` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `FK2F2B5A68DF94E79A` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_vendor_notes_ibfk1` FOREIGN KEY (`QrVendorBaseId`) REFERENCES `qr_vendor_base_info` (`QrVendorBaseId`),
  ADD CONSTRAINT `qr_vendor_notes_ibfk2` FOREIGN KEY (`QrHrUserId`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_vendor_notes_ibfk3` FOREIGN KEY (`CreatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`),
  ADD CONSTRAINT `qr_vendor_notes_ibfk4` FOREIGN KEY (`UpdatedBy`) REFERENCES `qr_hr_user_info` (`QrHrUserId`);

--
-- Constraints for table `qr_zipcode`
--
ALTER TABLE `qr_zipcode`
  ADD CONSTRAINT `FK56B21070545532B8` FOREIGN KEY (`QrCityId`) REFERENCES `qr_city` (`QrCityId`),
  ADD CONSTRAINT `qr_zipcode_ibfk_1` FOREIGN KEY (`QrCityId`) REFERENCES `qr_city` (`QrCityId`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
