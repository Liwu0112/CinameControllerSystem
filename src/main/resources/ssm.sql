/*
 Navicat Premium Data Transfer

 Source Server         : ssm
 Source Server Type    : MySQL
 Source Server Version : 80032
 Source Host           : localhost:3306
 Source Schema         : ssm1

 Target Server Type    : MySQL
 Target Server Version : 80032
 File Encoding         : 65001

 Date: 16/05/2024 19:21:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin_informations
-- ----------------------------
DROP TABLE IF EXISTS `admin_informations`;
CREATE TABLE `admin_informations`  (
  `admin_id` int NOT NULL AUTO_INCREMENT,
  `admin_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `admin_password` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`admin_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1530 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of admin_informations
-- ----------------------------
INSERT INTO `admin_informations` VALUES (1530, 'zeng', '202CB962AC59075B964B07152D234B70');

-- ----------------------------
-- Table structure for ciname_informations
-- ----------------------------
DROP TABLE IF EXISTS `ciname_informations`;
CREATE TABLE `ciname_informations`  (
  `ciname_id` int NOT NULL AUTO_INCREMENT,
  `ciname_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `begin_date` datetime NULL DEFAULT NULL,
  `over_date` datetime NULL DEFAULT NULL,
  `ciname_place` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `ciname_price` int NULL DEFAULT NULL,
  `is_delete` int NULL DEFAULT NULL,
  PRIMARY KEY (`ciname_id`) USING BTREE,
  INDEX `cinemaId`(`ciname_id` ASC) USING BTREE,
  INDEX `cinema_name`(`ciname_name` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ciname_informations
-- ----------------------------
INSERT INTO `ciname_informations` VALUES (1, '小黄人', '2023-11-17 21:13:04', '2023-11-17 23:13:06', '01', 35, 0);
INSERT INTO `ciname_informations` VALUES (2, '前任四', '2023-11-17 21:13:04', '2023-11-17 23:13:06', '02', 45, 1);
INSERT INTO `ciname_informations` VALUES (3, '复仇者联盟', '2023-11-17 21:13:04', '2023-11-17 23:13:06', '03', 50, 0);
INSERT INTO `ciname_informations` VALUES (5, '大黄蜂', '2023-11-19 16:48:00', '2023-11-19 19:00:00', '05', 52, 1);
INSERT INTO `ciname_informations` VALUES (6, '变形金刚', '2023-11-30 12:00:00', '2023-11-30 14:00:00', '06', 65, 0);
INSERT INTO `ciname_informations` VALUES (7, '猪猪侠', '2023-02-01 06:00:00', '2023-02-03 08:00:00', '03', 10, 0);
INSERT INTO `ciname_informations` VALUES (8, '蜘蛛侠', '2024-01-01 00:00:00', '2024-01-01 02:02:00', '01', 23, 1);
INSERT INTO `ciname_informations` VALUES (9, '前任三', '2023-12-11 12:00:00', '2023-12-11 14:00:00', '02', 100, 0);
INSERT INTO `ciname_informations` VALUES (10, '煎饼侠', '2023-11-23 11:15:00', '2023-11-23 15:00:00', '06', 15, 0);
INSERT INTO `ciname_informations` VALUES (11, '美国队长', '2024-11-02 01:02:00', '2024-11-02 04:00:00', '01', 60, 0);

-- ----------------------------
-- Table structure for retail_informations
-- ----------------------------
DROP TABLE IF EXISTS `retail_informations`;
CREATE TABLE `retail_informations`  (
  `retail_id` int NOT NULL AUTO_INCREMENT,
  `retail_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `retail_price` int NULL DEFAULT NULL,
  `retail_introduce` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `retail_count` int NULL DEFAULT NULL,
  `is_delete` int NULL DEFAULT NULL,
  PRIMARY KEY (`retail_id`) USING BTREE,
  INDEX `retailId`(`retail_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of retail_informations
-- ----------------------------
INSERT INTO `retail_informations` VALUES (1, '娃娃机', 2, '抓娃娃', 44, 0);
INSERT INTO `retail_informations` VALUES (2, '按摩椅', 15, '享受按摩', 29, 1);
INSERT INTO `retail_informations` VALUES (3, '爆米花', 20, '现烤爆米花', 9, 0);
INSERT INTO `retail_informations` VALUES (4, '可乐', 15, '可口可乐', 27, 0);
INSERT INTO `retail_informations` VALUES (5, '阿根达斯', 53, '冰淇淋中的王者', 20, 1);

-- ----------------------------
-- Table structure for sys_user_login_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_login_log`;
CREATE TABLE `sys_user_login_log`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NULL DEFAULT NULL,
  `login_ip` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `login_time` datetime NULL DEFAULT NULL,
  `login_token` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `channel` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '渠道',
  `equipment` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '设备信息',
  `is_Delete` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_login_log
-- ----------------------------

-- ----------------------------
-- Table structure for user_buyretsil
-- ----------------------------
DROP TABLE IF EXISTS `user_buyretsil`;
CREATE TABLE `user_buyretsil`  (
  `orders_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `retail_id` int NULL DEFAULT NULL,
  `retail_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `pay_price` int NULL DEFAULT NULL,
  PRIMARY KEY (`orders_id`) USING BTREE,
  INDEX `userId`(`user_id` ASC) USING BTREE,
  INDEX `retailId`(`retail_id` ASC) USING BTREE,
  CONSTRAINT `user_buyretsil_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_informations` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `user_buyretsil_ibfk_2` FOREIGN KEY (`retail_id`) REFERENCES `retail_informations` (`retail_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_buyretsil
-- ----------------------------
INSERT INTO `user_buyretsil` VALUES (1, 4, 4, '可乐', 15);
INSERT INTO `user_buyretsil` VALUES (2, 7, 4, '可乐', 15);
INSERT INTO `user_buyretsil` VALUES (3, 7, 1, '娃娃机', 2);
INSERT INTO `user_buyretsil` VALUES (4, 7, 1, '娃娃机', 2);
INSERT INTO `user_buyretsil` VALUES (5, 7, 1, '娃娃机', 2);
INSERT INTO `user_buyretsil` VALUES (6, 7, 5, '阿根达斯', 53);
INSERT INTO `user_buyretsil` VALUES (7, 7, 1, '娃娃机', 2);
INSERT INTO `user_buyretsil` VALUES (8, 4, 2, '按摩椅', 15);
INSERT INTO `user_buyretsil` VALUES (9, 7, 1, '娃娃机', 2);
INSERT INTO `user_buyretsil` VALUES (10, 4, 4, '可乐', 15);
INSERT INTO `user_buyretsil` VALUES (11, 4, 1, '娃娃机', 2);
INSERT INTO `user_buyretsil` VALUES (12, 4, 3, '爆米花', 20);

-- ----------------------------
-- Table structure for user_informations
-- ----------------------------
DROP TABLE IF EXISTS `user_informations`;
CREATE TABLE `user_informations`  (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `user_phone` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `user_password` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `user_balance` int NULL DEFAULT NULL,
  `is_delete` int NULL DEFAULT NULL,
  `tickets_count` int NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_informations
-- ----------------------------
INSERT INTO `user_informations` VALUES (1, 'zhao', '15112345671', '202CB962AC59075B964B07152D234B70', 5000, 0, 0);
INSERT INTO `user_informations` VALUES (2, 'qian', '15112345672', '202CB962AC59075B964B07152D234B70', 5000, 0, 0);
INSERT INTO `user_informations` VALUES (3, 'sun', '15112345673', '202CB962AC59075B964B07152D234B70', 5000, 0, 0);
INSERT INTO `user_informations` VALUES (4, 'li', '15112345674', '202CB962AC59075B964B07152D234B70', 4853, 0, 2);
INSERT INTO `user_informations` VALUES (5, 'aa', '15112345675', '202CB962AC59075B964B07152D234B70', 5000, 0, 0);
INSERT INTO `user_informations` VALUES (6, 'bb', '15112345676', '202CB962AC59075B964B07152D234B70', 4790, 0, 4);
INSERT INTO `user_informations` VALUES (7, 'cc', '15112345677', '202CB962AC59075B964B07152D234B70', 4697, 0, 5);
INSERT INTO `user_informations` VALUES (10, 'ling', '', '202CB962AC59075B964B07152D234B70', 5000, 0, 0);

-- ----------------------------
-- Table structure for user_select_mytickets
-- ----------------------------
DROP TABLE IF EXISTS `user_select_mytickets`;
CREATE TABLE `user_select_mytickets`  (
  `ticket_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `ciname_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `begin_data` datetime NULL DEFAULT NULL,
  `over_data` datetime NULL DEFAULT NULL,
  `ciname_place` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ticket_id`) USING BTREE,
  INDEX `cinema_name`(`ciname_name` ASC) USING BTREE,
  INDEX `user_id`(`user_id` ASC) USING BTREE,
  CONSTRAINT `user_select_mytickets_ibfk_2` FOREIGN KEY (`ciname_name`) REFERENCES `ciname_informations` (`ciname_name`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_select_mytickets_ibfk_3` FOREIGN KEY (`user_id`) REFERENCES `user_informations` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 45 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_select_mytickets
-- ----------------------------
INSERT INTO `user_select_mytickets` VALUES (28, 6, '复仇者联盟', '2023-11-17 21:13:04', '2023-11-17 23:13:06', '03');
INSERT INTO `user_select_mytickets` VALUES (29, 6, '变形金刚', '2023-11-30 12:00:00', '2023-11-30 14:00:00', '06');
INSERT INTO `user_select_mytickets` VALUES (36, 7, '变形金刚', '2023-11-30 12:00:00', '2023-11-30 14:00:00', '06');
INSERT INTO `user_select_mytickets` VALUES (40, 7, '前任三', '2023-12-11 12:00:00', '2023-12-11 14:00:00', '02');
INSERT INTO `user_select_mytickets` VALUES (41, 7, '小黄人', '2023-11-17 21:13:04', '2023-11-17 23:13:06', '01');
INSERT INTO `user_select_mytickets` VALUES (42, 7, '煎饼侠', '2023-11-23 11:15:00', '2023-11-23 15:00:00', '06');
INSERT INTO `user_select_mytickets` VALUES (43, 7, '猪猪侠', '2023-02-01 06:00:00', '2023-02-03 08:00:00', '03');
INSERT INTO `user_select_mytickets` VALUES (45, 4, '小黄人', '2023-11-17 21:13:04', '2023-11-17 23:13:06', '01');

-- ----------------------------
-- Table structure for worker_application_information
-- ----------------------------
DROP TABLE IF EXISTS `worker_application_information`;
CREATE TABLE `worker_application_information`  (
  `application_id` int NOT NULL AUTO_INCREMENT,
  `worker_id` int NOT NULL,
  `worker_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `worker_application` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `application_date` datetime NULL DEFAULT NULL,
  `application_result` int NULL DEFAULT NULL,
  PRIMARY KEY (`application_id`) USING BTREE,
  INDEX `workerapplicationinformation_ibfk_1`(`worker_id` ASC) USING BTREE,
  CONSTRAINT `worker_application_information_ibfk_1` FOREIGN KEY (`worker_id`) REFERENCES `worker_informations` (`worker_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of worker_application_information
-- ----------------------------
INSERT INTO `worker_application_information` VALUES (3, 1002, 'li', '休假', '2023-11-23 16:52:00', 1);
INSERT INTO `worker_application_information` VALUES (5, 1003, 'chen', '休假', '2023-11-23 19:52:00', 2);
INSERT INTO `worker_application_information` VALUES (6, 1003, 'chen', '辞职', '2023-11-23 19:52:00', 2);
INSERT INTO `worker_application_information` VALUES (7, 1002, 'li', '休假', '2023-11-23 20:31:00', 2);
INSERT INTO `worker_application_information` VALUES (9, 1002, 'li', '休假', '2023-11-23 21:06:00', 1);
INSERT INTO `worker_application_information` VALUES (10, 1002, 'li', '休假', '2023-11-23 21:06:00', 1);
INSERT INTO `worker_application_information` VALUES (11, 1013, 'fu', '休假', '2023-11-24 10:16:00', 1);
INSERT INTO `worker_application_information` VALUES (12, 1008, 'hang', '休假', '2023-11-24 10:24:00', 0);
INSERT INTO `worker_application_information` VALUES (13, 1002, 'li', '复工', '2023-11-25 14:50:00', 1);
INSERT INTO `worker_application_information` VALUES (14, 1002, 'li', '复工', '2023-11-25 15:17:00', 1);
INSERT INTO `worker_application_information` VALUES (15, 1002, 'li', '复工', '2023-11-25 15:24:00', 1);
INSERT INTO `worker_application_information` VALUES (16, 1012, 'xiao', '复工', '2023-11-25 15:28:00', 1);
INSERT INTO `worker_application_information` VALUES (17, 1012, 'xiao', '复工', '2023-11-25 15:37:00', 1);
INSERT INTO `worker_application_information` VALUES (18, 1012, 'xiao', '复工', '2023-11-25 15:37:00', 1);
INSERT INTO `worker_application_information` VALUES (19, 1002, 'li', '复工', '2023-11-25 15:38:00', 1);
INSERT INTO `worker_application_information` VALUES (20, 1013, 'fu', '复工', '2023-11-28 15:40:00', 1);
INSERT INTO `worker_application_information` VALUES (21, 1013, 'fu', '辞职', '2023-11-28 15:41:00', 1);
INSERT INTO `worker_application_information` VALUES (22, 1012, 'xiao', '休假', '2023-11-28 16:12:00', 0);

-- ----------------------------
-- Table structure for worker_informations
-- ----------------------------
DROP TABLE IF EXISTS `worker_informations`;
CREATE TABLE `worker_informations`  (
  `worker_id` int NOT NULL AUTO_INCREMENT,
  `worker_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `worker_age` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `worker_sex` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `worker_intodate` datetime NULL DEFAULT NULL,
  `worker_post` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `worker_phone` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `worker_password` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `is_delete` int NULL DEFAULT NULL,
  `is_relex` int NULL DEFAULT NULL,
  PRIMARY KEY (`worker_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1015 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of worker_informations
-- ----------------------------
INSERT INTO `worker_informations` VALUES (1001, 'zhang', '21', '男', '2023-11-17 21:08:09', '零售员', '15112345661', '202CB962AC59075B964B07152D234B70', 1, 0);
INSERT INTO `worker_informations` VALUES (1002, 'li', '22', '男', '2023-11-17 21:09:01', '售票员', '151****5662', '202CB962AC59075B964B07152D234B70', 0, 1);
INSERT INTO `worker_informations` VALUES (1003, 'chen', '23', '男', '2023-11-17 21:09:42', '售票员', '15112345663', '202CB962AC59075B964B07152D234B70', 1, 0);
INSERT INTO `worker_informations` VALUES (1004, 'yang', '23', '男', '2023-11-20 21:05:00', '零售员', '15112345664', '202CB962AC59075B964B07152D234B70', 0, 0);
INSERT INTO `worker_informations` VALUES (1008, 'hang', '23', '男', '2023-11-23 20:50:45', '售票员', '15112345665', '202CB962AC59075B964B07152D234B70', 0, 0);
INSERT INTO `worker_informations` VALUES (1009, 'guo', '32', '女', '2023-11-23 21:21:00', '售票员', '15112345666', '202CB962AC59075B964B07152D234B70', 0, 0);
INSERT INTO `worker_informations` VALUES (1011, 'wen', '28', '男', '2023-11-23 22:30:00', '零售员', '15112345667', '202CB962AC59075B964B07152D234B70', 0, 0);
INSERT INTO `worker_informations` VALUES (1012, 'xiao', '18', '男', '2023-11-23 22:20:00', '售票员', '15112345668', '202CB962AC59075B964B07152D234B70', 0, 0);
INSERT INTO `worker_informations` VALUES (1013, 'fu', '36', '女', '2023-11-24 12:00:00', '零售员', '15112345669', '202CB962AC59075B964B07152D234B70', 0, 0);

SET FOREIGN_KEY_CHECKS = 1;
