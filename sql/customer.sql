/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : jeefw

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-10-19 13:43:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `customer_name` varchar(40) NOT NULL,
  `sex` smallint(6) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `email` varchar(30) NOT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `status` bit(1) NOT NULL,
  `remark` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
