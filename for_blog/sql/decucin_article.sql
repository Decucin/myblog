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

 Date: 17/10/2021 19:56:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for decucin_article
-- ----------------------------
DROP TABLE IF EXISTS `decucin_article`;
CREATE TABLE `decucin_article`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `comment_count` int NULL DEFAULT NULL COMMENT '评论数量',
  `create_date` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `summary` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '简介',
  `title` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `view_count` int NULL DEFAULT NULL COMMENT '浏览数量',
  `weight` int NULL DEFAULT NULL COMMENT '是否置顶',
  `author_id` bigint NULL DEFAULT NULL COMMENT '作者id',
  `body_id` bigint NULL DEFAULT NULL COMMENT '内容id',
  `category_id` bigint NULL DEFAULT NULL COMMENT '类别id',
  `like_count` int NULL DEFAULT NULL COMMENT '点赞数',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `author`(`author_id`) USING BTREE,
  INDEX `body`(`body_id`) USING BTREE,
  INDEX `category`(`category_id`) USING BTREE,
  CONSTRAINT `author` FOREIGN KEY (`author_id`) REFERENCES `decucin_sys_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `body` FOREIGN KEY (`body_id`) REFERENCES `decucin_body` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `category` FOREIGN KEY (`category_id`) REFERENCES `decucin_category` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;
