/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50725
Source Host           : localhost:3306
Source Database       : db_item

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2019-04-26 16:45:38
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
INSERT INTO `t_admin` VALUES ('1', 'admin', '111111', 'Lero', '男', '123');

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
INSERT INTO `t_developer` VALUES ('1', 'developer1', '123', '1', '喜子', '女', '10010');
INSERT INTO `t_developer` VALUES ('2', 'developer2', '123', '2', '小茶', '男', '12580');
INSERT INTO `t_developer` VALUES ('3', 'developer3', '123', '3', '悠哉', '男', '1008611');

-- ----------------------------
-- Table structure for t_developerrecord
-- ----------------------------
DROP TABLE IF EXISTS `t_developerrecord`;
CREATE TABLE `t_developerrecord` (
  `developerId` int(11) NOT NULL AUTO_INCREMENT,
  `itemTypeId` int(11) DEFAULT NULL,
  `developerName` varchar(20) DEFAULT NULL,
  `developerSex` varchar(20) DEFAULT NULL,
  `developerNumber` int(11) DEFAULT NULL,
  `developerTel` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`developerId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_developerrecord
-- ----------------------------
INSERT INTO `t_developerrecord` VALUES ('1', '1', '220', '男', '6', '110');

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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_itemmanager
-- ----------------------------
INSERT INTO `t_itemmanager` VALUES ('2', 'manager2', '123', '4', '小张', '男', '123');
INSERT INTO `t_itemmanager` VALUES ('3', 'manager3', '123', '1', '小李', '女', '123');
INSERT INTO `t_itemmanager` VALUES ('4', 'manager4', '123', '5', '小陈', '男', '123');
INSERT INTO `t_itemmanager` VALUES ('5', 'manager5', '123', '1', '小宋', '男', '123');
INSERT INTO `t_itemmanager` VALUES ('7', 'manager6', '123', '0', '呵呵 ', '女', '123');
INSERT INTO `t_itemmanager` VALUES ('8', 'manager1', '123', '6', '小白', '男', '123');
INSERT INTO `t_itemmanager` VALUES ('9', 'man66', '123', '9', '大黑', '男', '123456');
INSERT INTO `t_itemmanager` VALUES ('10', 'xiaoxin', '123', null, '小新', '男', '1008611');

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
INSERT INTO `t_itemtype` VALUES ('1', '华商教务系统', '这是一华商教务系统。。');
INSERT INTO `t_itemtype` VALUES ('4', '华商OA系统', '这是华商OA系统。。。。');
INSERT INTO `t_itemtype` VALUES ('5', '华商E家', '这是华商E家。。。。');
INSERT INTO `t_itemtype` VALUES ('6', '华商宿舍管理', '这是。。。。');
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
INSERT INTO `t_record` VALUES ('3', '007', '测试1', '1', '221', '2014-08-11', '123', null);
INSERT INTO `t_record` VALUES ('9', '010', '公告', '4', '222', '2019-02-22', 'dfdfs', null);
INSERT INTO `t_record` VALUES ('10', '005', '请假', '4', '220', '2019-02-25', '123', null);
INSERT INTO `t_record` VALUES ('12', '005', '请假', '4', '220', '2019-02-25', 'fasdf', null);

-- ----------------------------
-- Table structure for t_subproject
-- ----------------------------
DROP TABLE IF EXISTS `t_subproject`;
CREATE TABLE `t_subproject` (
  `subprojectId` int(11) NOT NULL AUTO_INCREMENT,
  `subNum` varchar(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `itemTypeId` int(11) DEFAULT NULL,
  `developerName` varchar(11) DEFAULT NULL,
  `state` varchar(10) DEFAULT NULL,
  `tel` varchar(15) DEFAULT NULL,
  `developerId` int(11) DEFAULT NULL,
  PRIMARY KEY (`subprojectId`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_subproject
-- ----------------------------
INSERT INTO `t_subproject` VALUES ('2', '002', '考勤', '123', '4', '方法撒旦', '未完成', '32', '1');
INSERT INTO `t_subproject` VALUES ('3', '003', '外卖', '123', '5', '手动阀', '未完成', '2', '1');
INSERT INTO `t_subproject` VALUES ('4', '004', '成绩查询', '123', '6', '220', '未完成', '1', '1');
INSERT INTO `t_subproject` VALUES ('5', '005', '请假', '123', '4', '220', '完成', '123', '1');
INSERT INTO `t_subproject` VALUES ('6', '006', '调休', '123', '4', '111', '完成', '111', '2');
INSERT INTO `t_subproject` VALUES ('9', '007', '测试1', '123', '1', '221', '未完成', '123', '2');
INSERT INTO `t_subproject` VALUES ('28', '001', '测试1', '123', '1', '111', '完成', '123', '2');
INSERT INTO `t_subproject` VALUES ('29', '008', '测试3', '123', '6', '123', '完成', '123', '2');
INSERT INTO `t_subproject` VALUES ('31', '010', '公告', '123', '4', '222', '未完成', '111', '3');
INSERT INTO `t_subproject` VALUES ('32', '011', '011', '123', '5', '222', '完成', '111', '3');
INSERT INTO `t_subproject` VALUES ('33', '022', '123', 'test111', '4', '懒得想1号', '未完成', '10086', '3');
INSERT INTO `t_subproject` VALUES ('34', '033', '123', 'test22', '1', '小李子', '未完成', '12580', '3');
