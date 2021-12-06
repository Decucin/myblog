/*
 Navicat Premium Data Transfer

 Source Server         : for_blog
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : localhost:3306
 Source Schema         : blog

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 23/10/2021 19:44:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for decucin_comment
-- ----------------------------
DROP TABLE IF EXISTS `decucin_comment`;
CREATE TABLE `decucin_comment`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `comment_body` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评论内容',
  `from_id` bigint NULL DEFAULT NULL COMMENT '评论者id',
  `to_id` bigint NULL DEFAULT NULL COMMENT '被评论者id',
  `level` bit(1) NULL DEFAULT NULL COMMENT '评论层级',
  `create_time` datetime NULL DEFAULT NULL COMMENT '评论时间',
  `like_count` int NULL DEFAULT NULL COMMENT '点赞数',
  `article_id` bigint NULL DEFAULT NULL COMMENT '哪篇文章的评论',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `from`(`from_id`) USING BTREE,
  INDEX `to`(`to_id`) USING BTREE,
  INDEX `to_article`(`article_id`) USING BTREE,
  CONSTRAINT `from` FOREIGN KEY (`from_id`) REFERENCES `decucin_sys_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `to` FOREIGN KEY (`to_id`) REFERENCES `decucin_sys_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `to_article` FOREIGN KEY (`article_id`) REFERENCES `decucin_article` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
