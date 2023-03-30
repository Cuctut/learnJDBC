/*
 Navicat Premium Data Transfer

 Source Server         : 本机
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : localhost:3306
 Source Schema         : learnjdbc

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 30/03/2023 13:50:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for students
-- ----------------------------
DROP TABLE IF EXISTS `students`;
CREATE TABLE `students`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `gender` tinyint(1) NOT NULL,
  `grade` int(11) NOT NULL,
  `score` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of students
-- ----------------------------
INSERT INTO `students` VALUES (1, '小明', 1, 1, 88);
INSERT INTO `students` VALUES (2, '小红', 1, 1, 95);
INSERT INTO `students` VALUES (3, '小军', 0, 1, 93);
INSERT INTO `students` VALUES (4, '小白', 0, 1, 100);
INSERT INTO `students` VALUES (5, '小牛', 1, 2, 96);
INSERT INTO `students` VALUES (6, '小兵', 1, 2, 99);
INSERT INTO `students` VALUES (7, '小强', 0, 2, 86);
INSERT INTO `students` VALUES (8, '小乔', 0, 2, 79);
INSERT INTO `students` VALUES (9, '小青', 1, 3, 85);
INSERT INTO `students` VALUES (10, '小王', 1, 3, 90);
INSERT INTO `students` VALUES (11, '小林', 0, 3, 91);
INSERT INTO `students` VALUES (12, '小贝', 0, 3, 77);

SET FOREIGN_KEY_CHECKS = 1;
