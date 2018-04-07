DROP SCHEMA IF EXISTS order_0;
CREATE SCHEMA IF NOT EXISTS order_0;
USE order_0;
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

-- ----------------------------
-- Records of order
-- ----------------------------
-- INSERT INTO `order` VALUES ('1', '10', '1', '雪地靴', '吴雷');
-- INSERT INTO `order` VALUES ('2', '11', '2', '篮球', '吴雷');
-- INSERT INTO `order` VALUES ('3', '12', '3', '帽子', '李四');
-- INSERT INTO `order` VALUES ('4', '13', '4', '足球', '吴雷');
-- INSERT INTO `order` VALUES ('5', '14', '1', '雪地靴', '张三');
-- INSERT INTO `order` VALUES ('6', '15', '5', '羽毛球', '张三');
