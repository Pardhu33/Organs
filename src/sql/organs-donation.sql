/*
SQLyog Trial v13.1.8 (64 bit)
MySQL - 10.11.0-MariaDB : Database - organ_donation
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`organ_donation` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `organ_donation`;

/*Table structure for table `address` */

DROP TABLE IF EXISTS `address`;

CREATE TABLE `address` (
  `id` int(11) NOT NULL,
  `donor_id` int(11) DEFAULT NULL,
  `house_no` varchar(100) DEFAULT NULL,
  `street` varchar(100) DEFAULT NULL,
  `district` varchar(100) DEFAULT NULL,
  `state` varchar(100) DEFAULT NULL,
  `zip` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `donor_id` (`donor_id`),
  CONSTRAINT `fk_donor` FOREIGN KEY (`donor_id`) REFERENCES `donors` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `address` */

/*Table structure for table `donors` */

DROP TABLE IF EXISTS `donors`;

CREATE TABLE `donors` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `date_of_birth` date NOT NULL,
  `gender` enum('Male','Female','Other') NOT NULL,
  `aadhar_number` varchar(12) NOT NULL,
  `age` int(11) NOT NULL,
  `blood_group` varchar(5) NOT NULL,
  `organs` text NOT NULL,
  `contact` varchar(20) NOT NULL,
  `email` varchar(100) NOT NULL,
  `address` text DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `aadhar_number` (`aadhar_number`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;

/*Data for the table `donors` */

insert  into `donors`(`id`,`first_name`,`last_name`,`date_of_birth`,`gender`,`aadhar_number`,`age`,`blood_group`,`organs`,`contact`,`email`,`address`) values 
(13,'dugyala','srinivas','1990-03-12','Male','8888222888',35,'O+','kidneys','9885695354','dsrao33@gmail.com','venkatapur parkal'),
(14,'varun','reddy','2000-02-29','Male','586443635420',25,'AB+','kidneys','9868454960','varun@gmail.com','hyderabad'),
(16,'jaffer','syed','2004-01-09','Male','8888222887',20,'A+','lungs','9876543214','jaffer@gmail.com','hyderabad'),
(17,'vraj','rao','2005-12-04','Male','586443635421',20,'B+','tissues','6281828212','vraj@gmail.com','Langar House'),
(18,'kiran','raj','2000-01-01','Male','586443635423',25,'AB-','pancreas','9885675354','kiran@gmail.com','chiltalguda,Hyderabad'),
(19,'Rakesh','shigh','2001-02-02','Male','8888222867',24,'O+','intestines','9868454962','rakesh@gmail.com','Vizag , Andhra pradesh'),
(20,'Sanjani','sudula','2005-05-21','Female','586443635428',20,'O-','corneas','9868454964','sanjani@gmail.com','Shaikpet'),
(21,'Pavani','Naga','1990-03-18','Female','586443635429',35,'AB+','kidneys','9888888549','pavani@gmail.com','Nagaland'),
(22,'anil','kumar','1980-09-09','Male','586443635443',45,'A+','intestines','9868454967','anil@gmail.com','Kondapur'),
(23,'Udbhav','tumala','1970-05-21','Male','586443635433',55,'AB-','heart','9868454979','udbhav@gmail.com','Warangal'),
(24,'Venkat','Krishna','1960-06-22','Male','586443635456',65,'B+','heart','986845477','Venkat@gmail.com','Goa'),
(25,'venky','kudumula','2000-09-21','Male','586443635523',25,'AB+','liver','9868454978','venky@gmail.com','chennai');

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

/*Data for the table `users` */

insert  into `users`(`id`,`username`,`password`) values 
(9,'ds','abcd'),
(10,'admin','admin123');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
