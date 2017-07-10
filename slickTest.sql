/*
 Navicat Premium Data Transfer

 Source Server         : myMacMysql
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost
 Source Database       : slickTest

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : utf-8

 Date: 07/10/2017 11:17:17 AM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `course`
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `cid` bigint(20) NOT NULL,
  `cname` varchar(255) NOT NULL,
  `tid` bigint(20) NOT NULL,
  `created` bigint(20) NOT NULL COMMENT '创建时间',
  `createdBy` bigint(20) NOT NULL COMMENT '创建人',
  `updated` bigint(20) NOT NULL COMMENT '修改时间',
  `updatedBy` bigint(20) NOT NULL COMMENT '修改人',
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sc`
-- ----------------------------
DROP TABLE IF EXISTS `sc`;
CREATE TABLE `sc` (
  `cid` bigint(20) NOT NULL,
  `sid` bigint(20) NOT NULL,
  `score` tinyint(4) NOT NULL,
  `created` bigint(20) NOT NULL COMMENT '创建时间',
  `createdBy` bigint(20) NOT NULL COMMENT '创建人',
  `updated` bigint(20) NOT NULL COMMENT '修改时间',
  `updatedBy` bigint(20) NOT NULL COMMENT '修改人',
  PRIMARY KEY (`cid`,`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `student`
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `sid` bigint(20) NOT NULL,
  `sname` varchar(255) NOT NULL,
  `sage` tinyint(3) NOT NULL,
  `ssex` varchar(5) NOT NULL,
  `address` varchar(1024) NOT NULL,
  `created` bigint(20) NOT NULL COMMENT '创建时间',
  `createdBy` bigint(20) NOT NULL COMMENT '创建人',
  `updated` bigint(20) NOT NULL COMMENT '修改时间',
  `updatedBy` bigint(20) NOT NULL COMMENT '修改人',
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `teacher`
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `tid` bigint(20) NOT NULL,
  `tname` varchar(255) NOT NULL,
  `created` bigint(20) NOT NULL COMMENT '创建时间',
  `createdBy` bigint(20) NOT NULL COMMENT '创建人',
  `updated` bigint(20) NOT NULL COMMENT '修改时间',
  `updatedBy` bigint(20) NOT NULL COMMENT '修改人',
  PRIMARY KEY (`tid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
