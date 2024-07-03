/*
 Navicat Premium Data Transfer

 Source Server         : LocalHost
 Source Server Type    : MariaDB
 Source Server Version : 100339
 Source Host           : 127.0.0.1:3366
 Source Schema         : studentdata

 Target Server Type    : MariaDB
 Target Server Version : 100339
 File Encoding         : 65001

 Date: 02/07/2024 19:11:37
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `course` varchar(30) DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL,
  `degree` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- ----------------------------
-- Records of course
-- ----------------------------
BEGIN;
INSERT INTO `course` VALUES (1, 'BSCS', 'COMPUTER SCIENCE', 'BACHELOR OF SCIENCE IN COMPUTER SCIENCE');
INSERT INTO `course` VALUES (2, 'BSCE', 'COMPUTER ENGINEERING', 'BACHELOR OF SCIENCE IN COMPUTER SCIENCE');
COMMIT;

-- ----------------------------
-- Table structure for grade
-- ----------------------------
DROP TABLE IF EXISTS `grade`;
CREATE TABLE `grade` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `studentNum` int(11) DEFAULT NULL,
  `year` varchar(10) DEFAULT NULL,
  `course` varchar(50) DEFAULT NULL,
  `first` double(11,1) DEFAULT NULL,
  `second` double(11,1) DEFAULT NULL,
  `finale` double(11,1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- ----------------------------
-- Records of grade
-- ----------------------------
BEGIN;
INSERT INTO `grade` VALUES (1, 3000, 'First', 'BSCS', 10.0, 9.0, 9.5);
COMMIT;

-- ----------------------------
-- Table structure for perfil
-- ----------------------------
DROP TABLE IF EXISTS `perfil`;
CREATE TABLE `perfil` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `perfil` varchar(100) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of perfil
-- ----------------------------
BEGIN;
INSERT INTO `perfil` VALUES (1, 'ADMINISTRATOR');
INSERT INTO `perfil` VALUES (2, 'USER');
COMMIT;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `studentNum` int(11) DEFAULT NULL,
  `year` varchar(10) DEFAULT NULL,
  `course` varchar(50) DEFAULT NULL,
  `firstName` varchar(50) DEFAULT NULL,
  `lastName` varchar(50) DEFAULT NULL,
  `gender` varchar(6) DEFAULT NULL,
  `birth` date DEFAULT NULL,
  `status` varchar(15) DEFAULT NULL,
  `image` varchar(500) DEFAULT NULL,
  `dateReg` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- ----------------------------
-- Records of student
-- ----------------------------
BEGIN;
INSERT INTO `student` VALUES (1, 7453, 'First', 'BSCS', 'John', 'Doe', 'Male', '1980-07-18', 'Enrolled', 'duke-java.png', '2024-07-01');
INSERT INTO `student` VALUES (6, 1000, 'Third', 'BSCS', 'xxxx', 'xxxx', 'Female', '2024-06-26', 'Enrolled', 'helloween.jpg', '2024-07-01');
INSERT INTO `student` VALUES (7, 2000, 'Second', 'BSCE', 'wwww', 'wwww', 'Male', '2024-06-26', 'Not Enrolled', '', '2024-07-02');
INSERT INTO `student` VALUES (9, 3000, 'First', 'BSCS', 'ertertert', 'rtretetttrert', 'Female', '2024-06-20', 'Enrolled', '', '2024-07-02');
COMMIT;

-- ----------------------------
-- Table structure for userperfil
-- ----------------------------
DROP TABLE IF EXISTS `userperfil`;
CREATE TABLE `userperfil` (
  `iduser` int(11) NOT NULL,
  `idperfil` int(11) NOT NULL,
  UNIQUE KEY `Usuario_Perfil_UNIQUE` (`iduser`,`idperfil`) USING BTREE,
  KEY `fk_Usuarios1_idx` (`iduser`) USING BTREE,
  KEY `fk_Perfiles1_idx` (`idperfil`) USING BTREE,
  CONSTRAINT `userperfil_ibfk_1` FOREIGN KEY (`idperfil`) REFERENCES `perfil` (`id`),
  CONSTRAINT `userperfil_ibfk_2` FOREIGN KEY (`iduser`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of userperfil
-- ----------------------------
BEGIN;
INSERT INTO `userperfil` VALUES (1, 1);
COMMIT;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- ----------------------------
-- Records of users
-- ----------------------------
BEGIN;
INSERT INTO `users` VALUES (1, 'fedex', '*EAA1F3CBAD88D9E7F0E8F777C0AB9E213F334860');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
