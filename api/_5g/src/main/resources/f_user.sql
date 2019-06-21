/*
Navicat MySQL Data Transfer

Source Server         : 47.106.241.195_3306
Source Server Version : 80015
Source Host           : 47.106.241.195:3306
Source Database       : financial

Target Server Type    : MYSQL
Target Server Version : 80015
File Encoding         : 65001

Date: 2019-06-21 14:32:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for f_user
-- ----------------------------
DROP TABLE IF EXISTS `f_user`;
CREATE TABLE `f_user` (
  `u_id` int(11) NOT NULL AUTO_INCREMENT,
  `u_pwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `u_phone` bigint(13) NOT NULL,
  `u_address` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `u_idcard` varchar(18) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '0',
  `u_company` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '公司名称',
  `u_msg` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `u_sex` varchar(2) COLLATE utf8_bin NOT NULL,
  `u_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `u_head` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '/group1/M00/00/00/rBExuF0LM0KATBpBAAD375yOzjU440.png',
  `u_validation` int(2) NOT NULL DEFAULT '0',
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
