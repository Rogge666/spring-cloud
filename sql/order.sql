/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : rogge

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-11-01 23:32:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `order_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) NOT NULL,
  `product_name` varchar(30) NOT NULL,
  `order_user_name` varchar(20) NOT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='订单';

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES ('1', '1', '雪地靴', '吴雷');
INSERT INTO `order` VALUES ('2', '2', '篮球', '吴雷');
INSERT INTO `order` VALUES ('3', '3', '帽子', '李四');
INSERT INTO `order` VALUES ('4', '4', '足球', '吴雷');
INSERT INTO `order` VALUES ('5', '1', '雪地靴', '张三');
INSERT INTO `order` VALUES ('6', '5', '羽毛球', '张三');
