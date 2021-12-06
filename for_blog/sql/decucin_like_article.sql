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

 Date: 24/10/2021 15:22:08
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for decucin_like_article
-- ----------------------------
DROP TABLE IF EXISTS `decucin_like_article`;
CREATE TABLE `decucin_like_article`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` bigint NULL DEFAULT NULL COMMENT '用户id',
  `article_id` bigint NULL DEFAULT NULL COMMENT '文章id',
  `if_like` bit(1) NULL DEFAULT NULL COMMENT '是否点赞',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `userid`(`user_id`) USING BTREE,
  INDEX `articleid`(`article_id`) USING BTREE,
  CONSTRAINT `articleid` FOREIGN KEY (`article_id`) REFERENCES `decucin_article` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `userid` FOREIGN KEY (`user_id`) REFERENCES `decucin_sys_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
