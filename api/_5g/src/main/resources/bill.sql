/*
Navicat MySQL Data Transfer

Source Server         : 47.106.241.195_3306
Source Server Version : 80015
Source Host           : 47.106.241.195:3306
Source Database       : financial

Target Server Type    : MYSQL
Target Server Version : 80015
File Encoding         : 65001

Date: 2019-06-21 14:32:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for bill
-- ----------------------------
DROP TABLE IF EXISTS `bill`;
CREATE TABLE `bill` (
  `f_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '订单编号',
  `u_id` bigint(20) NOT NULL COMMENT '发单人id',
  `f_type` enum('房地产','票据','摆账','国企','上市公司','企贷','个贷','过桥') NOT NULL COMMENT '贷款类型',
  `f_address` varchar(255) NOT NULL COMMENT '地址',
  `f_amount` bigint(20) NOT NULL COMMENT '贷款金额',
  `f_instructions` varchar(255) DEFAULT NULL COMMENT '补充说明',
  `f_valuation` varchar(255) DEFAULT NULL COMMENT '估值/票面/房地产取得方式',
  `f_rate` varchar(6) NOT NULL COMMENT '利率',
  `f_levorprofit` varchar(6) DEFAULT NULL COMMENT '公司负债率或房地产的利润率',
  `f_cycle` char(255) NOT NULL COMMENT '贷款周期',
  `f_used` varchar(255) DEFAULT NULL COMMENT '用途',
  `f_editor` date DEFAULT NULL COMMENT '到期时间',
  `f_area` double DEFAULT NULL COMMENT '房地产面积',
  `f_plot` varchar(6) DEFAULT NULL COMMENT '房地产容积率',
  `f_floorprice` double DEFAULT NULL COMMENT '楼面价',
  `f_houseprice` double DEFAULT NULL COMMENT '房价',
  `f_creditplus` varchar(255) NOT NULL COMMENT '征信/票据状态/房地产取得方式',
  `f_mortqaqed` varchar(255) DEFAULT NULL COMMENT '抵押物',
  `f_prototype` varchar(255) NOT NULL COMMENT '出款/类型/批复',
  `f_risk` varchar(255) NOT NULL COMMENT '风控/利息',
  `f_commossion` varchar(255) NOT NULL COMMENT '佣金',
  `j_id` bigint(20) DEFAULT '0' COMMENT '接单人id',
  `u_uconfirm` int(11) NOT NULL DEFAULT '0' COMMENT '发单人确认交易',
  `j_jconfirm` int(11) NOT NULL DEFAULT '0' COMMENT '接单人确认交易',
  `f_createtime` date NOT NULL COMMENT '发单时间',
  `f_accepttime` date DEFAULT NULL COMMENT '接单时间',
  `f_status` int(11) DEFAULT '0' COMMENT '订单状态，0表示后台还未审核，1表示后台审核未通过，2表示后台审核通过',
  PRIMARY KEY (`f_id`)
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8;
