/*
SQLyog Ultimate v10.00 Beta1
MySQL - 5.6.26-log : Database - network
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`network` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `network`;

/*Table structure for table `databasechangelog` */

DROP TABLE IF EXISTS `databasechangelog`;

CREATE TABLE `databasechangelog` (
  `ID` varchar(255) NOT NULL,
  `AUTHOR` varchar(255) NOT NULL,
  `FILENAME` varchar(255) NOT NULL,
  `DATEEXECUTED` datetime NOT NULL,
  `ORDEREXECUTED` int(11) NOT NULL,
  `EXECTYPE` varchar(10) NOT NULL,
  `MD5SUM` varchar(35) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `COMMENTS` varchar(255) DEFAULT NULL,
  `TAG` varchar(255) DEFAULT NULL,
  `LIQUIBASE` varchar(20) DEFAULT NULL,
  `CONTEXTS` varchar(255) DEFAULT NULL,
  `LABELS` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `databasechangelog` */

insert  into `databasechangelog`(`ID`,`AUTHOR`,`FILENAME`,`DATEEXECUTED`,`ORDEREXECUTED`,`EXECTYPE`,`MD5SUM`,`DESCRIPTION`,`COMMENTS`,`TAG`,`LIQUIBASE`,`CONTEXTS`,`LABELS`) values ('initial','levon','classpath:liquibase/dbChangeLog.xml','2017-11-22 15:03:07',1,'EXECUTED','7:d25d3df1d91b09fcf78999f4e06deb35','sqlFile','',NULL,'3.4.1',NULL,NULL),('update-2017-11-23','levon','classpath:liquibase/dbChangeLog.xml','2017-11-23 17:01:31',2,'EXECUTED','7:c0f6c66069f9ad71080670a5d8271e14','sqlFile','',NULL,'3.4.1',NULL,NULL);

/*Table structure for table `databasechangeloglock` */

DROP TABLE IF EXISTS `databasechangeloglock`;

CREATE TABLE `databasechangeloglock` (
  `ID` int(11) NOT NULL,
  `LOCKED` bit(1) NOT NULL,
  `LOCKGRANTED` datetime DEFAULT NULL,
  `LOCKEDBY` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `databasechangeloglock` */

insert  into `databasechangeloglock`(`ID`,`LOCKED`,`LOCKGRANTED`,`LOCKEDBY`) values (1,'\0',NULL,NULL);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `mobile_number` varchar(255) DEFAULT NULL,
  `user_status_id` int(10) DEFAULT NULL,
  `user_type_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_type` (`user_type_id`),
  KEY `fk_user_status` (`user_status_id`),
  CONSTRAINT `fk_user_status` FOREIGN KEY (`user_status_id`) REFERENCES `user_status_lcp` (`id`),
  CONSTRAINT `fk_user_type` FOREIGN KEY (`user_type_id`) REFERENCES `user_type_lcp` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`first_name`,`last_name`,`mobile_number`,`user_status_id`,`user_type_id`) values (6,'asdf','sasdf','0055321654',2,1);

/*Table structure for table `user_status_lcp` */

DROP TABLE IF EXISTS `user_status_lcp`;

CREATE TABLE `user_status_lcp` (
  `id` int(10) NOT NULL,
  `status` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user_status_lcp` */

insert  into `user_status_lcp`(`id`,`status`) values (1,'ACTIVE'),(2,'NOT_ACTIVATED'),(3,'BLOCKED'),(4,'DELETED');

/*Table structure for table `user_type_lcp` */

DROP TABLE IF EXISTS `user_type_lcp`;

CREATE TABLE `user_type_lcp` (
  `id` int(10) NOT NULL,
  `type` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user_type_lcp` */

insert  into `user_type_lcp`(`id`,`type`) values (1,'REGULAR'),(2,'PREMIUM');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
