/*
Navicat MySQL Data Transfer

Source Server         : MySQL01
Source Server Version : 50552
Source Host           : localhost:3306
Source Database       : db_item

Target Server Type    : MYSQL
Target Server Version : 50552
File Encoding         : 65001

Date: 2019-05-06 23:49:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_admin
-- ----------------------------
DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin` (
  `adminId` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(20) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `sex` varchar(10) DEFAULT NULL,
  `tel` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`adminId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_admin
-- ----------------------------
INSERT INTO `t_admin` VALUES ('1', 'admin', 'aY1RoZ2KEhzlgUmde3AWaA==', 'Lero', '男', '13450921034');

-- ----------------------------
-- Table structure for t_developer
-- ----------------------------
DROP TABLE IF EXISTS `t_developer`;
CREATE TABLE `t_developer` (
  `developerId` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(30) NOT NULL,
  `password` varchar(50) NOT NULL,
  `itemManaerId` int(11) DEFAULT NULL,
  `name` varchar(30) DEFAULT NULL,
  `sex` varchar(30) DEFAULT NULL,
  `tel` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`developerId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_developer
-- ----------------------------
INSERT INTO `t_developer` VALUES ('1', 'user1', 'aY1RoZ2KEhzlgUmde3AWaA==', '1', '喜子', '女', '18918900012');
INSERT INTO `t_developer` VALUES ('2', 'developer2', 'aY1RoZ2KEhzlgUmde3AWaA==', '2', '小茶', '男', '13420337810');
INSERT INTO `t_developer` VALUES ('3', 'developer3', 'aY1RoZ2KEhzlgUmde3AWaA==', '3', '悠哉', '男', '13527129554');
INSERT INTO `t_developer` VALUES ('4', 'user2', 'aY1RoZ2KEhzlgUmde3AWaA==', '2', '开发者铅笔', '男', '13523092201');

-- ----------------------------
-- Table structure for t_itemmanager
-- ----------------------------
DROP TABLE IF EXISTS `t_itemmanager`;
CREATE TABLE `t_itemmanager` (
  `itemManId` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(20) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `itemTypeId` int(11) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `sex` varchar(20) DEFAULT NULL,
  `tel` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`itemManId`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_itemmanager
-- ----------------------------
INSERT INTO `t_itemmanager` VALUES ('1', 'name1', 'aY1RoZ2KEhzlgUmde3AWaA==', '3', '小刘', '男', '1234567890');
INSERT INTO `t_itemmanager` VALUES ('2', 'name2', 'aY1RoZ2KEhzlgUmde3AWaA==', '4', '小张', '男', '13527129554');
INSERT INTO `t_itemmanager` VALUES ('3', 'name3', 'aY1RoZ2KEhzlgUmde3AWaA==', '2', '小李', '女', '18918900074');
INSERT INTO `t_itemmanager` VALUES ('4', 'name6', 'aY1RoZ2KEhzlgUmde3AWaA==', '2', '小艾', '男', '1234567890');
INSERT INTO `t_itemmanager` VALUES ('5', 'name7', 'aY1RoZ2KEhzlgUmde3AWaA==', null, '刘总', '男', '1234567897');
INSERT INTO `t_itemmanager` VALUES ('20', 'name8', 'aY1RoZ2KEhzlgUmde3AWaA==', null, '何以', '女', '13423445656');
INSERT INTO `t_itemmanager` VALUES ('21', 'name9', 'aY1RoZ2KEhzlgUmde3AWaA==', null, '小肥', '男', '13565096018');
INSERT INTO `t_itemmanager` VALUES ('22', 'name10', 'aY1RoZ2KEhzlgUmde3AWaA==', null, '陈某', '男', '13523092201');
INSERT INTO `t_itemmanager` VALUES ('24', 'name11', 'aY1RoZ2KEhzlgUmde3AWaA==', null, '毕业设计2', '男', '18918900012');

-- ----------------------------
-- Table structure for t_itemtype
-- ----------------------------
DROP TABLE IF EXISTS `t_itemtype`;
CREATE TABLE `t_itemtype` (
  `itemTypeId` int(11) NOT NULL AUTO_INCREMENT,
  `itemTypeName` varchar(20) DEFAULT NULL,
  `itemTypeDetail` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`itemTypeId`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_itemtype
-- ----------------------------
INSERT INTO `t_itemtype` VALUES ('1', '华商教务系统', '这是一华商教务系统。');
INSERT INTO `t_itemtype` VALUES ('2', '华商宿舍管理', '这是。。。');
INSERT INTO `t_itemtype` VALUES ('3', '华商考勤系统', '');
INSERT INTO `t_itemtype` VALUES ('4', '华商OA系统', '这是华商OA系统。');
INSERT INTO `t_itemtype` VALUES ('5', '华商E家', '这是华商E家。');

-- ----------------------------
-- Table structure for t_record
-- ----------------------------
DROP TABLE IF EXISTS `t_record`;
CREATE TABLE `t_record` (
  `recordId` int(11) NOT NULL AUTO_INCREMENT,
  `subprojectNumber` varchar(20) DEFAULT NULL,
  `subprojectName` varchar(30) DEFAULT NULL,
  `itemTypeId` int(11) DEFAULT NULL,
  `developerName` varchar(11) DEFAULT NULL,
  `date` varchar(20) DEFAULT NULL,
  `detail` varchar(50) DEFAULT NULL,
  `developerId` int(11) DEFAULT NULL,
  PRIMARY KEY (`recordId`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_record
-- ----------------------------
INSERT INTO `t_record` VALUES ('3', '007', '测试1', '1', '喜子', '2014-08-11', '基本完成', '1');
INSERT INTO `t_record` VALUES ('9', '010', '公告', '4', '小茶', '2019-02-22', '全部完成并测试成功', '2');
INSERT INTO `t_record` VALUES ('10', '005', '请假', '4', '悠哉', '2019-02-25', '基本完成', '3');
INSERT INTO `t_record` VALUES ('12', '005', '请假', '4', '喜子', '2019-02-25', '请假功能基本实现', null);
INSERT INTO `t_record` VALUES ('16', '022', '报修', '4', '悠哉', '2019-05-06', '做得不错', null);
INSERT INTO `t_record` VALUES ('18', '008', '测试3', '3', '小茶', '2019-05-06', '做得不错', null);

-- ----------------------------
-- Table structure for t_subproject
-- ----------------------------
DROP TABLE IF EXISTS `t_subproject`;
CREATE TABLE `t_subproject` (
  `subprojectId` int(11) NOT NULL AUTO_INCREMENT,
  `subNum` varchar(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `itemTypeId` int(11) DEFAULT NULL,
  `developerName` varchar(11) DEFAULT NULL,
  `state` varchar(10) DEFAULT NULL,
  `tel` varchar(15) DEFAULT NULL,
  `developerId` int(11) DEFAULT NULL,
  PRIMARY KEY (`subprojectId`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_subproject
-- ----------------------------
INSERT INTO `t_subproject` VALUES ('2', '002', '考勤', '4', '喜子', '0', '18918900011', '1');
INSERT INTO `t_subproject` VALUES ('3', '003', '外卖', '5', '喜子', '1', '18918900011', '1');
INSERT INTO `t_subproject` VALUES ('4', '004', '成绩查询', '5', '喜子', '0', '18918900011', '1');
INSERT INTO `t_subproject` VALUES ('5', '005', '请假', '4', '喜子', '0', '18918900011', '1');
INSERT INTO `t_subproject` VALUES ('6', '006', '调休', '4', '小茶', '1', '13420337810', '2');
INSERT INTO `t_subproject` VALUES ('9', '007', '测试1', '1', '小茶', '0', '13420337810', '2');
INSERT INTO `t_subproject` VALUES ('28', '001', '测试1', '3', '小茶', '1', '13420337810', '2');
INSERT INTO `t_subproject` VALUES ('29', '008', '测试3', '3', '小茶', '0', '13420337810', '2');
INSERT INTO `t_subproject` VALUES ('31', '010', '公告', '4', '悠哉', '1', '13527129554', '3');
INSERT INTO `t_subproject` VALUES ('32', '011', '出差', '3', '悠哉', '1', '13527129554', '3');
INSERT INTO `t_subproject` VALUES ('33', '012', '报修', '4', '悠哉', '0', '13527129554', '3');
INSERT INTO `t_subproject` VALUES ('34', '013', '楼栋查询', '4', '悠哉', '0', '13527129554', '3');
