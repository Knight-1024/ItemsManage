/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50725
Source Host           : localhost:3306
Source Database       : db_item

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2019-04-28 17:09:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_admin
-- ----------------------------
DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin` (
  `adminId` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `sex` varchar(10) DEFAULT NULL,
  `tel` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`adminId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_admin
-- ----------------------------
INSERT INTO `t_admin` VALUES ('1', 'admin', '111', 'Lero', '男', '123');

-- ----------------------------
-- Table structure for t_developer
-- ----------------------------
DROP TABLE IF EXISTS `t_developer`;
CREATE TABLE `t_developer` (
  `developerId` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `itemManaerId` int(11) DEFAULT NULL,
  `name` varchar(30) DEFAULT NULL,
  `sex` varchar(30) DEFAULT NULL,
  `tel` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`developerId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_developer
-- ----------------------------
INSERT INTO `t_developer` VALUES ('1', 'user1', '123', '1', '喜子', '女', '18918900011');
INSERT INTO `t_developer` VALUES ('2', 'developer2', '123', '2', '小茶', '男', '13527129531');
INSERT INTO `t_developer` VALUES ('3', 'developer3', '123', '3', '悠哉', '男', '13527129554');

-- ----------------------------
-- Table structure for t_itemmanager
-- ----------------------------
DROP TABLE IF EXISTS `t_itemmanager`;
CREATE TABLE `t_itemmanager` (
  `itemManId` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `itemTypeId` int(11) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `sex` varchar(20) DEFAULT NULL,
  `tel` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`itemManId`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_itemmanager
-- ----------------------------
INSERT INTO `t_itemmanager` VALUES ('1', 'name3', '123', null, '悠哉', '男', '18918900014');
INSERT INTO `t_itemmanager` VALUES ('2', 'name2', '123', '4', '小张', '男', '13527129554');
INSERT INTO `t_itemmanager` VALUES ('3', 'name3', '123', '1', '小李', '女', '18918900074');
INSERT INTO `t_itemmanager` VALUES ('4', 'xiaochen', '123', '5', '小陈', '男', '13527129524');
INSERT INTO `t_itemmanager` VALUES ('5', 'xiaosong', '123', '1', '小宋', '男', '18918900014');
INSERT INTO `t_itemmanager` VALUES ('6', 'feifei', '123', '2', '菲菲', '女', '13527129558');
INSERT INTO `t_itemmanager` VALUES ('7', 'hehe', '123', '0', '呵呵 ', '女', '18918900064');
INSERT INTO `t_itemmanager` VALUES ('8', 'xiaobai', '123', '6', '小白', '男', '13527129515');
INSERT INTO `t_itemmanager` VALUES ('9', 'dahei', '123', '9', '大黑', '男', '13030969198');
INSERT INTO `t_itemmanager` VALUES ('10', 'xiaoxin', '123', null, '小新', '男', '13631249657');
INSERT INTO `t_itemmanager` VALUES ('11', 'xiaolin', '123', null, '小林', '女', '19919900014');

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
INSERT INTO `t_itemtype` VALUES ('4', '华商OA系统', '这是华商OA系统。');
INSERT INTO `t_itemtype` VALUES ('5', '华商E家', '这是华商E家。');
INSERT INTO `t_itemtype` VALUES ('6', '华商宿舍管理', '这是。。。');
INSERT INTO `t_itemtype` VALUES ('9', '华商考勤系统', '');

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
  `date` date DEFAULT NULL,
  `detail` varchar(50) DEFAULT NULL,
  `developerId` int(11) DEFAULT NULL,
  PRIMARY KEY (`recordId`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_record
-- ----------------------------
INSERT INTO `t_record` VALUES ('3', '007', '测试1', '1', '221', '2014-08-11', '基本完成', '1');
INSERT INTO `t_record` VALUES ('9', '010', '公告', '4', '222', '2019-02-22', '全部完成并测试成功', '2');
INSERT INTO `t_record` VALUES ('10', '005', '请假', '4', '220', '2019-02-25', '基本完成', null);
INSERT INTO `t_record` VALUES ('12', '005', '请假', '4', '220', '2019-02-25', '请假功能基本实现', null);

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
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_subproject
-- ----------------------------
INSERT INTO `t_subproject` VALUES ('2', '002', '考勤', '4', '喜子', '0', '18918900011', '1');
INSERT INTO `t_subproject` VALUES ('3', '003', '外卖', '5', '喜子', '0', '18918900011', '1');
INSERT INTO `t_subproject` VALUES ('4', '004', '成绩查询', '6', '喜子', '0', '18918900011', '1');
INSERT INTO `t_subproject` VALUES ('5', '005', '请假', '4', '喜子', '1', '18918900011', '1');
INSERT INTO `t_subproject` VALUES ('6', '006', '调休', '4', '小茶', '1', '13527129531', '2');
INSERT INTO `t_subproject` VALUES ('9', '007', '测试1', '1', '小茶', '0', '13527129531', '2');
INSERT INTO `t_subproject` VALUES ('28', '001', '测试1', '1', '小茶', '1', '13527129531', '2');
INSERT INTO `t_subproject` VALUES ('29', '008', '测试3', '6', '小茶', '1', '13527129531', '2');
INSERT INTO `t_subproject` VALUES ('31', '010', '公告', '4', '悠哉', '1', '13527129554', '3');
INSERT INTO `t_subproject` VALUES ('32', '011', '出差', '5', '悠哉', '1', '13527129554', '3');
INSERT INTO `t_subproject` VALUES ('33', '022', '报修', '4', '悠哉', '0', '13527129554', '3');
INSERT INTO `t_subproject` VALUES ('34', '033', '楼栋查询', '1', '悠哉', '1', '13527129554', '3');
INSERT INTO `t_subproject` VALUES ('35', '033', '空教室查询', '5', '', '0', '', null);
INSERT INTO `t_subproject` VALUES ('36', '033', '活动', '4', '', '1', '', null);
INSERT INTO `t_subproject` VALUES ('37', '033', '社团', '5', '', '1', '', null);
