/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : rogge

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-11-04 19:07:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for command_content
-- ----------------------------
DROP TABLE IF EXISTS `command_content`;
CREATE TABLE `command_content` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `CONTENT` varchar(255) DEFAULT NULL COMMENT '指令内容',
  `COMMAND_ID` int(11) NOT NULL COMMENT '指令主键',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of command_content
-- ----------------------------
INSERT INTO `command_content` VALUES ('1', '查看1', '1');
INSERT INTO `command_content` VALUES ('2', '查看2', '1');
INSERT INTO `command_content` VALUES ('3', '查看3', '1');
INSERT INTO `command_content` VALUES ('4', '查看4', '1');
INSERT INTO `command_content` VALUES ('5', '查看5', '1');
INSERT INTO `command_content` VALUES ('6', '查看6', '1');
INSERT INTO `command_content` VALUES ('7', '段子1', '2');
INSERT INTO `command_content` VALUES ('8', '段子2', '2');
INSERT INTO `command_content` VALUES ('9', '段子3', '2');
INSERT INTO `command_content` VALUES ('10', '段子4', '2');
INSERT INTO `command_content` VALUES ('11', '段子5', '2');
INSERT INTO `command_content` VALUES ('12', '段子6', '2');
INSERT INTO `command_content` VALUES ('13', '批量插入0', '4');
INSERT INTO `command_content` VALUES ('14', '批量插入1', '4');
INSERT INTO `command_content` VALUES ('15', '批量插入2', '4');
INSERT INTO `command_content` VALUES ('16', '批量插入3', '4');
INSERT INTO `command_content` VALUES ('17', '批量插入4', '4');
