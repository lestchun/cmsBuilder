/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.6.10 : Database - cmscreate
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`cmscreate` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `cmscreate`;

/*Table structure for table `column` */

DROP TABLE IF EXISTS `column`;

CREATE TABLE `column` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '列编号',
  `cid` int(11) DEFAULT NULL COMMENT '组件编号',
  `ispre` int(11) DEFAULT NULL COMMENT '是否为主键',
  `showName` varchar(100) DEFAULT NULL COMMENT '显示中文名称',
  `ename` varchar(400) DEFAULT NULL COMMENT '显示在前台名称',
  `oname` varchar(400) DEFAULT NULL COMMENT '在类中名称',
  `defaultValue` varchar(100) DEFAULT NULL COMMENT '如果为空 则没有默认值',
  `formatter` text COMMENT '如果为空则显示原来的值',
  `width` varchar(100) DEFAULT NULL COMMENT '宽度',
  `editMethod` int(11) DEFAULT NULL COMMENT '如果为空则不能编辑',
  `search` varchar(100) DEFAULT NULL COMMENT '如果为空则不能被搜索',
  PRIMARY KEY (`id`),
  KEY `FKAF3ED3563EE34B29` (`cid`),
  CONSTRAINT `FKAF3ED3563EE34B29` FOREIGN KEY (`cid`) REFERENCES `component` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `column` */

/*Table structure for table `component` */

DROP TABLE IF EXISTS `component`;

CREATE TABLE `component` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '组件编号',
  `name` varchar(100) DEFAULT NULL COMMENT '组件名称',
  `position` int(11) DEFAULT NULL COMMENT '组件位置',
  `mainClass` varchar(400) DEFAULT NULL COMMENT '主要处理的类',
  `mid` int(11) DEFAULT NULL COMMENT '模块编号',
  `config` text COMMENT '组件其他配置',
  PRIMARY KEY (`id`),
  KEY `FKAC8F1CFDF3E437CF` (`mid`),
  CONSTRAINT `FKAC8F1CFDF3E437CF` FOREIGN KEY (`mid`) REFERENCES `modul` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `component` */

insert  into `component`(`id`,`name`,`position`,`mainClass`,`mid`,`config`) values (1,NULL,NULL,'test',NULL,NULL);

/*Table structure for table `modul` */

DROP TABLE IF EXISTS `modul`;

CREATE TABLE `modul` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '模块编号',
  `pid` int(11) DEFAULT NULL COMMENT '项目编号',
  `name` varchar(100) DEFAULT NULL COMMENT '模块名称',
  `comment` varchar(400) DEFAULT NULL COMMENT '模块说明',
  `mainClass` varchar(400) DEFAULT NULL COMMENT '操作的表',
  PRIMARY KEY (`id`),
  KEY `FK633FD1929ED2D92` (`pid`),
  CONSTRAINT `FK633FD1929ED2D92` FOREIGN KEY (`pid`) REFERENCES `project` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `modul` */

insert  into `modul`(`id`,`pid`,`name`,`comment`,`mainClass`) values (1,1,'财务管理','这是财务管理',NULL),(2,NULL,'哈哈','测试','test');

/*Table structure for table `project` */

DROP TABLE IF EXISTS `project`;

CREATE TABLE `project` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '项目编号',
  `name` varchar(100) DEFAULT NULL COMMENT '项目名称',
  `descript` text COMMENT '项目介绍',
  `logo` varchar(400) DEFAULT NULL COMMENT '项目logo',
  `addr` varchar(400) DEFAULT NULL COMMENT '项目访问地址',
  `databaseType` varchar(100) DEFAULT NULL COMMENT '项目数据库类型',
  `databseName` varchar(400) DEFAULT NULL COMMENT '项目数据库名称',
  `username` varchar(100) DEFAULT NULL COMMENT '项目数据库用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '项目数据库密码',
  `driverClass` varchar(400) DEFAULT NULL COMMENT '项目数据库驱动',
  `dialect` varchar(255) DEFAULT NULL COMMENT '数据库方言',
  `beans` varchar(400) DEFAULT NULL COMMENT 'bean存放路径',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `project` */

insert  into `project`(`id`,`name`,`descript`,`logo`,`addr`,`databaseType`,`databseName`,`username`,`password`,`driverClass`,`dialect`,`beans`) values (1,'cc管理系统','你猜啊','terminal%2Fimg%2Fsmall%2Fc5bad1d12db5638bac257a2229a42480.png','aa',NULL,'wqe','qweq','qwe',NULL,NULL,'%2Fbeans%2Fjar%2F1e87e8477b5ed44173b4b1bbc028a4b7.jar');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
