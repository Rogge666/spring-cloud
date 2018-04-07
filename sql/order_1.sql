DROP SCHEMA IF EXISTS order_1;
CREATE SCHEMA IF NOT EXISTS order_1;
USE order_1;
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `t_order_0`;
CREATE TABLE `t_order_0` (
  `order_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `product_id` bigint(20) NOT NULL,
  `product_name` varchar(30) NOT NULL,
  `order_user_name` varchar(20) NOT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='订单';

DROP TABLE IF EXISTS `t_order_1`;
CREATE TABLE `t_order_1` (
  `order_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `product_id` bigint(20) NOT NULL,
  `product_name` varchar(30) NOT NULL,
  `order_user_name` varchar(20) NOT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='订单';

INSERT INTO `t_order_1` VALUES (1, 1, 1, 'MYSQL 高可用', '吴雷');
INSERT INTO `t_order_1` VALUES (3, 1, 1, 'MYSQL 高可用', '吴雷');
INSERT INTO `t_order_1` VALUES (9, 3, 3, 'Thinking In Java', '吴雷');
INSERT INTO `t_order_1` VALUES (11, 3, 3, 'Thinking In Java', '吴雷');
INSERT INTO `t_order_1` VALUES (17, 5, 5, 'JavaScript', '吴雷');
INSERT INTO `t_order_1` VALUES (19, 5, 5, 'JavaScript', '吴雷');
