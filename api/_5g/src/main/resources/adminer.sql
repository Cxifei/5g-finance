/*
Navicat MySQL Data Transfer

Source Server         : 47.106.241.195_3306
Source Server Version : 80015
Source Host           : 47.106.241.195:3306
Source Database       : financial

Target Server Type    : MYSQL
Target Server Version : 80015
File Encoding         : 65001

Date: 2019-06-21 14:32:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for adminer
-- ----------------------------
DROP TABLE IF EXISTS `adminer`;
CREATE TABLE `adminer` (
  `a_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '管理员编号',
  `account` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '管理员账号',
  `psw` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '管理员密码',
  PRIMARY KEY (`a_id`),
  UNIQUE KEY `adminer_account_uindex` (`account`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='管理员表';

-- ----------------------------
-- Records of adminer
-- ----------------------------
INSERT INTO `adminer` VALUES ('1', 'jaychou', '123');
INSERT INTO `adminer` VALUES ('2', 'root', 'root');
