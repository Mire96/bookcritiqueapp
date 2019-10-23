/*
SQLyog Community v13.1.1 (64 bit)
MySQL - 10.1.26-MariaDB : Database - projekat_baza
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`projekat_baza` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `projekat_baza`;

/*Table structure for table `autor` */

DROP TABLE IF EXISTS `autor`;

CREATE TABLE `autor` (
  `autorID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ime` varchar(40) NOT NULL,
  `prezime` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`autorID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `autor` */

insert  into `autor`(`autorID`,`ime`,`prezime`) values 
(1,'Ivo','Andri?'),
(2,'Vuk','Karadži?'),
(3,'Džordž','Orvel'),
(4,'Nik','Miler'),
(5,'Agata','Kristi'),
(6,'Oliver','Twist');

/*Table structure for table `autordelo` */

DROP TABLE IF EXISTS `autordelo`;

CREATE TABLE `autordelo` (
  `deloID` int(10) unsigned NOT NULL,
  `autorID` int(10) unsigned NOT NULL,
  PRIMARY KEY (`deloID`,`autorID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `autordelo` */

insert  into `autordelo`(`deloID`,`autorID`) values 
(1,1),
(1,4),
(1,5),
(2,1),
(2,2),
(3,1),
(3,4);

/*Table structure for table `delo` */

DROP TABLE IF EXISTS `delo`;

CREATE TABLE `delo` (
  `deloID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `naslov` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `godina` int(10) NOT NULL,
  `opis` varchar(2000) COLLATE utf8_unicode_ci NOT NULL,
  `jezik` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `korisnikID` int(10) unsigned NOT NULL,
  PRIMARY KEY (`deloID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `delo` */

insert  into `delo`(`deloID`,`naslov`,`godina`,`opis`,`jezik`,`korisnikID`) values 
(1,'A brave new world',2005,'Neki test opis cisto da vidimo','srpski',3),
(2,'Na drini cuprija',1961,'Neki opis samo za test','srpski',3),
(3,'Mali princ',1980,'Knjiga o malom decaku koji je princ svoje sopstvene planete','srpski',9);

/*Table structure for table `korisnik` */

DROP TABLE IF EXISTS `korisnik`;

CREATE TABLE `korisnik` (
  `korisnikID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ime` varchar(40) CHARACTER SET latin1 NOT NULL,
  `prezime` varchar(40) CHARACTER SET latin1 NOT NULL,
  `username` varchar(40) CHARACTER SET latin1 NOT NULL,
  `password` varchar(40) CHARACTER SET latin1 NOT NULL,
  `ulogaKorisnika` tinyint(1) NOT NULL,
  PRIMARY KEY (`korisnikID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `korisnik` */

insert  into `korisnik`(`korisnikID`,`ime`,`prezime`,`username`,`password`,`ulogaKorisnika`) values 
(3,'Milos','Mirkovic','mire','mire',1),
(4,'Ivan','Marojevic','ica','ica',2),
(9,'Pera','Peric','pera','pera',2);

/*Table structure for table `kritika` */

DROP TABLE IF EXISTS `kritika`;

CREATE TABLE `kritika` (
  `kritikaID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `deloID` int(10) unsigned NOT NULL,
  `sadrzaj` varchar(1000) CHARACTER SET latin1 NOT NULL,
  `statusKritike` tinyint(3) unsigned NOT NULL,
  `korisnikID` int(10) unsigned NOT NULL,
  PRIMARY KEY (`kritikaID`,`deloID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `kritika` */

insert  into `kritika`(`kritikaID`,`deloID`,`sadrzaj`,`statusKritike`,`korisnikID`) values 
(1,2,'Ovo je jedno divno delo',1,3),
(2,1,'Neki primeri',1,3),
(3,2,'Odlicno delo 10/10',1,9),
(4,3,'Bas mi se svidela knjiga',1,9);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
