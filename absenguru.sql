# Host: localhost  (Version 5.5.5-10.4.13-MariaDB)
# Date: 2020-07-22 10:25:12
# Generator: MySQL-Front 6.0  (Build 2.20)


#
# Structure for table "absenkeluar"
#

DROP TABLE IF EXISTS `absenkeluar`;
CREATE TABLE `absenkeluar` (
  `idabsenkeluar` int(11) NOT NULL AUTO_INCREMENT,
  `tanggalkeluar` date DEFAULT NULL,
  `waktukeluar` time DEFAULT NULL,
  `idguru` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`idabsenkeluar`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;

#
# Data for table "absenkeluar"
#

INSERT INTO `absenkeluar` VALUES (1,'2020-07-22','09:44:41',1712501244),(25,'2020-07-22','09:48:56',1712501344),(26,'2020-07-22','10:15:24',1712501444);

#
# Structure for table "absenmasuk"
#

DROP TABLE IF EXISTS `absenmasuk`;
CREATE TABLE `absenmasuk` (
  `idabsenmasuk` int(11) NOT NULL AUTO_INCREMENT,
  `tanggalmasuk` date DEFAULT NULL,
  `waktumasuk` time DEFAULT NULL,
  `idguru` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`idabsenmasuk`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;

#
# Data for table "absenmasuk"
#

INSERT INTO `absenmasuk` VALUES (1,'2020-07-22','09:44:37',1712501244),(31,'2020-07-22','09:50:07',1712501344),(32,'2020-07-22','10:15:21',1712501444);

#
# Structure for table "guru"
#

DROP TABLE IF EXISTS `guru`;
CREATE TABLE `guru` (
  `idguru` int(11) NOT NULL AUTO_INCREMENT,
  `nama` varchar(50) NOT NULL DEFAULT '',
  `jenkel` char(1) NOT NULL DEFAULT '',
  `jabatan` varchar(50) NOT NULL DEFAULT '',
  `katasandi` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`idguru`)
) ENGINE=InnoDB AUTO_INCREMENT=1712501345 DEFAULT CHARSET=utf8mb4;

#
# Data for table "guru"
#

INSERT INTO `guru` VALUES (1712501244,'arby','L','kepala sekolah','852456555'),(1712501344,'arby ganteng','L','wakil kepala sekolah','852456555'),(1712501444,'arby sudewa','L','punya sekolah','852456555');
