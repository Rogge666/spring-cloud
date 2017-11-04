/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : rogge

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-11-04 19:09:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for command
-- ----------------------------
DROP TABLE IF EXISTS `command`;
CREATE TABLE `command` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `NAME` varchar(16) DEFAULT NULL COMMENT '指令名称',
  `DESCRIPTION` varchar(32) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of command
-- ----------------------------
INSERT INTO `command` VALUES ('1', '查看', '精彩内容');
INSERT INTO `command` VALUES ('2', '段子', '精彩段子');
INSERT INTO `command` VALUES ('3', '新闻', '今日头条');
INSERT INTO `command` VALUES ('4', '娱乐', '娱乐新闻');
INSERT INTO `command` VALUES ('5', '电影', '近日上映大片');
INSERT INTO `command` VALUES ('6', '彩票', '中奖号码');
