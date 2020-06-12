/*
 Navicat Premium Data Transfer

 Source Server         : 我的电脑ftsh
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost:3306
 Source Schema         : mybatisplus

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 12/06/2020 19:24:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '姓名',
  `age` int(11) NULL DEFAULT NULL COMMENT '年龄',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT 1 COMMENT '乐观琐',
  `deleted` int(11) NULL DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1265467269564325900 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'yyyyy', 18, 'test1@baomidou.com', '2020-05-19 11:24:03', '2020-05-19 11:24:03', 6, 1);
INSERT INTO `user` VALUES (2, 'Jack', 20, 'test2@baomidou.com', '2020-05-19 11:24:03', '2020-05-19 11:24:03', 1, 1);
INSERT INTO `user` VALUES (3, 'Tom', 28, 'test3@baomidou.com', '2020-05-19 11:24:03', '2020-05-19 11:24:03', 1, 1);
INSERT INTO `user` VALUES (4, 'Sandy', 21, 'test4@baomidou.com', '2020-05-19 11:24:03', '2020-05-19 11:24:03', 1, 0);
INSERT INTO `user` VALUES (5, 'Billie', 24, 'test5@baomidou.com', '2020-05-19 11:24:03', '2020-05-19 11:24:03', 1, 0);
INSERT INTO `user` VALUES (6, 'ls', 5, '111@qq.com', '2020-05-19 11:54:55', '2020-05-19 11:54:55', 1, 0);
INSERT INTO `user` VALUES (7, 'zzzzzzzzzzzzz', 5, '111@qq.com', '2020-05-19 11:55:45', '2020-05-19 12:03:45', 1, 0);
INSERT INTO `user` VALUES (8, 'ls', 5, '111@qq.com', '2020-05-19 12:02:04', '2020-05-19 12:02:04', 1, 0);
INSERT INTO `user` VALUES (9, 'ls', 5, '111@qq.com', '2020-05-19 12:19:03', '2020-05-19 12:19:03', 1, 0);
INSERT INTO `user` VALUES (10, '属于阿', 13, NULL, '2020-05-20 18:18:39', '2020-05-20 18:18:39', 1, 0);
INSERT INTO `user` VALUES (1265467269564325893, '测试返回主键', 5, '111@qq.com', '2020-05-27 10:21:23', '2020-05-27 10:21:23', 1, 0);
INSERT INTO `user` VALUES (1265467269564325894, '测试返回主键', 5, '111@qq.com', '2020-05-28 12:17:02', '2020-05-28 12:17:02', 1, 0);
INSERT INTO `user` VALUES (1265467269564325895, '测试返回主键', 5, '111@qq.com', '2020-05-28 14:33:25', '2020-05-28 14:33:25', 1, 0);
INSERT INTO `user` VALUES (1265467269564325896, '测试返回主键', 5, '111@qq.com', '2020-05-28 14:59:54', '2020-05-28 14:59:54', 1, 0);
INSERT INTO `user` VALUES (1265467269564325897, 'ddddd', 3, NULL, '2020-06-01 10:01:18', '2020-06-01 10:01:18', 1, 0);
INSERT INTO `user` VALUES (1265467269564325898, 'ddddd', 3, NULL, '2020-06-01 10:02:36', '2020-06-01 10:02:36', 1, 0);
INSERT INTO `user` VALUES (1265467269564325899, '阿斯顿的萨', 3, NULL, '2020-06-01 10:12:55', '2020-06-01 10:12:55', 1, 0);

-- ----------------------------
-- Table structure for user_security
-- ----------------------------
DROP TABLE IF EXISTS `user_security`;
CREATE TABLE `user_security`  (
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `authority` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `credentials_salt` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_security
-- ----------------------------
INSERT INTO `user_security` VALUES ('123', 'ac59075b964b0715', 'VIP3', NULL);
INSERT INTO `user_security` VALUES ('123456', 'e10adc3949ba59abbe56e057f20f883e', 'VIP1', NULL);
INSERT INTO `user_security` VALUES ('2', '2', 'VIP2', NULL);
INSERT INTO `user_security` VALUES ('3', '3', 'VIP3', NULL);
INSERT INTO `user_security` VALUES ('321', 'f30a9893f08a93c8e683cac5a6432ce9', 'VIP1', '766319321');

SET FOREIGN_KEY_CHECKS = 1;
