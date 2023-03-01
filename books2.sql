/*
 Navicat Premium Data Transfer

 Source Server         : MySql
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : localhost:3306
 Source Schema         : books2

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 01/03/2023 22:30:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `aid` int(0) NOT NULL AUTO_INCREMENT,
  `username` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` char(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` char(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `times` int(0) NULL DEFAULT 0,
  `status` int(0) NULL DEFAULT 1,
  `lend_num` int(0) NULL DEFAULT NULL,
  `max_num` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`aid`, `username`) USING BTREE,
  INDEX `username`(`username`) USING BTREE,
  INDEX `name`(`name`) USING BTREE,
  INDEX `status`(`status`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10032 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (10000, 'root', '管理员', 'root', '972014123@qq.com', '13389447000', 0, 2, NULL, NULL);
INSERT INTO `admin` VALUES (10001, '1816270150', '王一', '123456', '642492123@qq.com', '13639349000', 4, 1, 30, 5);
INSERT INTO `admin` VALUES (10002, '1507550140', '吴一', 'wq5701211', '1667942357@qq.com', '17361643257', 2, 1, 30, 10);
INSERT INTO `admin` VALUES (10003, '181627015', '杨一', 'yy123456', '1068457627@qq.com', '17294286187', 2, 1, 30, 5);
INSERT INTO `admin` VALUES (10004, '1816270152', '高一', 'gjx123456', '6485113975@qq.com', '17294286188', 1, 1, 30, 5);
INSERT INTO `admin` VALUES (10005, 'admin', '管理员一号', 'admin', '1068457627@qq.com', '13625694675', 0, 2, NULL, NULL);
INSERT INTO `admin` VALUES (10006, '1816270153', '张一', 'zll123456', '1068457628@qq.com', '13625694681', 0, 1, 30, 5);
INSERT INTO `admin` VALUES (10007, '1816270154', '辛一', 'xy123456', '1068457629@qq.com', '13625694682', 0, 1, 30, 5);
INSERT INTO `admin` VALUES (10008, '8888', '测试1', '123456', 'test@qq.com', '13677778888', 14, 1, 30, 5);

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
  `bid` int(0) NOT NULL AUTO_INCREMENT,
  `name` char(205) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `card` char(205) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `autho` char(205) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `num` int(0) NOT NULL,
  `press` char(205) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` char(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `times` int(0) NULL DEFAULT 0,
  PRIMARY KEY (`bid`) USING BTREE,
  INDEX `card`(`card`) USING BTREE,
  INDEX `name`(`name`) USING BTREE,
  INDEX `type`(`type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2000030 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES (2000001, 'C语言程序设计', '3001001', '李建秋', 5, '中国矿业大学出版社', '002教学参考资料', 8);
INSERT INTO `book` VALUES (2000002, '盗墓笔记', '3003001', '南派三叔', 0, '上海文化出版社', '003现代国内文学', 5);
INSERT INTO `book` VALUES (2000003, 'java从入门到精通', '3002001', '软件开发中心', 14, '人民邮电出版社', '002教学参考资料', 4);
INSERT INTO `book` VALUES (2000004, '白夜行', '3004001', '东野圭吾', 10, '南海出版社', '004国外经典著作', 2);
INSERT INTO `book` VALUES (2000005, '解忧杂货店', '3004002', '东野圭吾', 10, '南海出版社', '004国外经典著作', 2);
INSERT INTO `book` VALUES (2000006, 'headfirst设计模式', '3001002', '弗里曼', 7, '中国电力出版社', '001教材', 2);
INSERT INTO `book` VALUES (2000029, '数据结构', '3005001', '王道论坛', 12, '电子工业出版社', '001教材', 0);
INSERT INTO `book` VALUES (2000030, '数据结构', '3005001', '王道论坛', 12, '电子工业出版社', '001教材', 0);

-- ----------------------------
-- Table structure for booktype
-- ----------------------------
DROP TABLE IF EXISTS `booktype`;
CREATE TABLE `booktype`  (
  `tid` int(0) NOT NULL AUTO_INCREMENT,
  `name` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`tid`) USING BTREE,
  INDEX `name`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3004 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of booktype
-- ----------------------------
INSERT INTO `booktype` VALUES (3001, '001教材');
INSERT INTO `booktype` VALUES (3002, '002教学参考资料');
INSERT INTO `booktype` VALUES (3003, '003现代国内文学');
INSERT INTO `booktype` VALUES (3004, '004国外经典著作');

-- ----------------------------
-- Table structure for history
-- ----------------------------
DROP TABLE IF EXISTS `history`;
CREATE TABLE `history`  (
  `hid` int(0) NOT NULL AUTO_INCREMENT,
  `aid` int(0) NULL DEFAULT NULL,
  `bid` int(0) NULL DEFAULT NULL,
  `card` int(0) NULL DEFAULT NULL,
  `bookname` char(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `adminname` char(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `username` char(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `begintime` char(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `endtime` char(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`hid`) USING BTREE,
  INDEX `status`(`status`) USING BTREE,
  INDEX `aid`(`aid`) USING BTREE,
  INDEX `bid`(`bid`) USING BTREE,
  INDEX `bookname`(`bookname`) USING BTREE,
  INDEX `adminname`(`adminname`) USING BTREE,
  INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 400023 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of history
-- ----------------------------
INSERT INTO `history` VALUES (400001, 10001, 2000001, 3001001, 'C语言程序设计', '1816270150', '王一', '2022-4-25', '2022-4-27', 0);
INSERT INTO `history` VALUES (400002, 10002, 2000002, 3003001, '盗墓笔记', '1507550139', '吴一', '2022-4-26', '2022-4-28', 0);
INSERT INTO `history` VALUES (400003, 10003, 2000003, 3002001, 'java从入门到精通', '1816270151', '杨一', '2022-4-27', '2022-5-4', 0);
INSERT INTO `history` VALUES (400004, 10001, 2000002, 3003001, '盗墓笔记', '1816270150', '王一', '2022-4-27', '2022-5-27', 1);
INSERT INTO `history` VALUES (400010, 10008, 2000001, 3001001, 'C语言程序设计', '8888', '测试1', '2022-5-12', '2022-6-12', 1);
INSERT INTO `history` VALUES (400011, 10008, 2000002, 3003001, '盗墓笔记', '8888', '测试1', '2022-5-12', '2022-5-12', 0);
INSERT INTO `history` VALUES (400012, 10008, 2000001, 3001001, 'C语言程序设计', '8888', '测试1', '2022-5-12', '2022-5-12', 0);
INSERT INTO `history` VALUES (400013, 10008, 2000001, 3001001, 'C语言程序设计', '8888', '测试1', '2022-5-12', '2022-5-12', 0);
INSERT INTO `history` VALUES (400014, 10008, 2000005, 3004002, '解忧杂货店', '8888', '测试1', '2022-5-12', '2022-5-21', 0);
INSERT INTO `history` VALUES (400015, 10008, 2000003, 3002001, 'java从入门到精通', '8888', '测试1', '2022-5-12', '2022-6-12', 1);
INSERT INTO `history` VALUES (400016, 10008, 2000006, 3001002, 'headfirst设计模式', '8888', '测试1', '2022-5-12', '2022-5-21', 0);
INSERT INTO `history` VALUES (400017, 10008, 2000002, 3003001, '盗墓笔记', '8888', '测试1', '2022-5-12', '2022-5-12', 0);
INSERT INTO `history` VALUES (400018, 10008, 2000001, 3001001, 'C语言程序设计', '8888', '测试1', '2022-5-12', '2022-5-12', 0);
INSERT INTO `history` VALUES (400019, 10008, 2000002, 3003001, '盗墓笔记', '8888', '测试1', '2022-4-12', '2022-5-12', 0);
INSERT INTO `history` VALUES (400020, 10008, 2000001, 3001001, 'C语言程序设计', '8888', '测试1', '2022-5-21', '2022-6-21', 1);
INSERT INTO `history` VALUES (400021, 10008, 2000001, 3001001, 'C语言程序设计', '8888', '测试1', '2022-5-21', '2022-5-23', 2);
INSERT INTO `history` VALUES (400022, 10008, 2000003, 3002001, 'java从入门到精通', '测试1', '8888', '2022-5-23', '2022-6-23', 1);
INSERT INTO `history` VALUES (400023, 10008, 2000001, 3001001, 'C语言程序设计', '测试1', '8888', '2022-5-24', '2022-6-24', 1);

-- ----------------------------
-- Table structure for problem
-- ----------------------------
DROP TABLE IF EXISTS `problem`;
CREATE TABLE `problem`  (
  `pid` int(0) NOT NULL AUTO_INCREMENT,
  `aid` int(0) NULL DEFAULT NULL,
  `name` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `page` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `body` char(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` char(5) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '未解决',
  PRIMARY KEY (`pid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5000003 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of problem
-- ----------------------------
INSERT INTO `problem` VALUES (5000001, 10001, '验证码问题', 'login', '登录页面的验证码实在是看不清楚，登录好多次才能成功', '123123', '未解决');
INSERT INTO `problem` VALUES (5000003, 10008, '少书', '123', '少一本书', '123', '解决');

SET FOREIGN_KEY_CHECKS = 1;
