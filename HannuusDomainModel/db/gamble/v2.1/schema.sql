CREATE DATABASE  IF NOT EXISTS `gamble_bbs` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `gamble_bbs`;
-- MySQL dump 10.13  Distrib 5.6.24, for Win64 (x86_64)
--
-- Host: localhost    Database: gamble_bbs
-- ------------------------------------------------------
-- Server version	5.6.26-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `global_params`
--

DROP TABLE IF EXISTS `global_params`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `global_params` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `type_name` varchar(100) DEFAULT NULL COMMENT '类型名称',
  `type_value` varchar(100) NOT NULL COMMENT '类型逻辑值',
  `key` varchar(100) DEFAULT NULL COMMENT 'param名称',
  `value` varchar(100) DEFAULT NULL COMMENT 'param逻辑值',
  `description` varchar(300) DEFAULT NULL COMMENT '描述',
  `created_date` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `permission`
--

DROP TABLE IF EXISTS `permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `name` varchar(100) NOT NULL COMMENT '权限名称',
  `permission_value` varchar(100) NOT NULL COMMENT '逻辑值 */create/update/delete/view',
  `description` varchar(200) DEFAULT NULL COMMENT '描述',
  `created_date` datetime DEFAULT NULL COMMENT '创建时间',
  `available` int(11) NOT NULL COMMENT '0-不可用 1-可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `reply`
--

DROP TABLE IF EXISTS `reply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reply` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `topic_id` bigint(20) NOT NULL COMMENT 'FK 主题ID',
  `title` varchar(300) DEFAULT NULL COMMENT '标题',
  `content` text COMMENT '回复内容',
  `user_id` bigint(20) DEFAULT NULL COMMENT 'FK 回复用户ID',
  `modified_date` datetime DEFAULT NULL COMMENT '最后编辑时间',
  `created_date` datetime DEFAULT NULL COMMENT '创建时间',
  `parent_id` bigint(20) NOT NULL COMMENT 'FK 父级回复ID',
  `state` int(11) DEFAULT NULL COMMENT '状态 -1-将要删除',
  `up_count` bigint(20) DEFAULT NULL COMMENT '获赞总计',
  `down_count` bigint(20) DEFAULT NULL COMMENT '被踩总计',
  `collection_count` bigint(20) DEFAULT NULL COMMENT '被收藏总计',
  `reply_count` bigint(20) DEFAULT NULL COMMENT '被评论总计',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='论坛帖子回复表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `resource`
--

DROP TABLE IF EXISTS `resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `name` varchar(100) NOT NULL COMMENT '资源名称',
  `resource_value` varchar(100) NOT NULL COMMENT '逻辑值',
  `description` varchar(200) DEFAULT NULL COMMENT '描述',
  `type` varchar(100) NOT NULL COMMENT '资源类型 model/menu/button',
  `priority` int(11) DEFAULT NULL COMMENT '优先级、顺序',
  `parent_id` bigint(20) DEFAULT NULL COMMENT 'FK 父编号，主要用于menu类型的资源',
  `parent_ids` varchar(100) DEFAULT NULL COMMENT '父编号列表（以“/”间隔）',
  `url` varchar(200) DEFAULT NULL COMMENT '访问路径，主要用于menu类型资源',
  `created_date` datetime DEFAULT NULL COMMENT '创建时间',
  `available` int(11) NOT NULL COMMENT '0-不可用 1-可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='资源表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL COMMENT 'PK',
  `name` varchar(100) NOT NULL COMMENT '角色名',
  `role_value` varchar(100) DEFAULT NULL COMMENT '逻辑值',
  `description` varchar(200) DEFAULT NULL COMMENT '描述',
  `resource_ids` varchar(100) DEFAULT NULL COMMENT '引用的多个资源ID字符串（以“,”间隔）',
  `created_date` datetime DEFAULT NULL COMMENT '创建时间',
  `available` int(11) NOT NULL COMMENT '0-不可用 1-可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `role_resource_permission`
--

DROP TABLE IF EXISTS `role_resource_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_resource_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `role_id` bigint(20) NOT NULL COMMENT 'FK 角色ID',
  `resource_id` bigint(20) NOT NULL COMMENT 'FK 资源ID',
  `permission_ids` varchar(200) NOT NULL COMMENT '权限ID列表（以“,”间隔）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='角色、资源、权限关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `topic`
--

DROP TABLE IF EXISTS `topic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `topic` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `category_id` bigint(20) DEFAULT NULL COMMENT 'FK 版块分类ID',
  `special_id` bigint(20) DEFAULT NULL COMMENT 'FK 主题所属专题ID',
  `title` varchar(300) NOT NULL COMMENT '标题',
  `content` text COMMENT '内容',
  `user_id` bigint(20) DEFAULT NULL COMMENT 'FK 用户ID',
  `enabled` int(11) DEFAULT NULL COMMENT '是否启用 0-不启用 1-启用',
  `hits` bigint(20) DEFAULT NULL COMMENT '访问总量',
  `reply_count` bigint(20) DEFAULT NULL COMMENT '回复总计',
  `modified_by` bigint(20) DEFAULT NULL COMMENT 'FK 最后编辑用户ID',
  `modified_date` datetime DEFAULT NULL COMMENT '最后编辑时间',
  `replied_by` bigint(20) DEFAULT NULL COMMENT 'FK 最后回复用户ID',
  `replied_date` datetime DEFAULT NULL COMMENT '最后回复时间',
  `icon_id` bigint(20) DEFAULT NULL COMMENT 'FK 主题图标ID',
  `state` int(11) DEFAULT NULL COMMENT '状态 -1-关闭贴',
  `image_url` varchar(400) DEFAULT NULL COMMENT '帖子展示图片',
  `created_date` datetime DEFAULT NULL COMMENT '创建时间',
  `follow_count` bigint(20) DEFAULT NULL COMMENT '关注总计',
  `tag_id` bigint(20) DEFAULT NULL COMMENT 'FK 标签ID',
  `up_count` bigint(20) DEFAULT NULL COMMENT '获赞总计',
  `down_count` bigint(20) DEFAULT NULL COMMENT '被踩总计',
  `collection_count` bigint(20) DEFAULT NULL COMMENT '被收藏总计',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='论坛帖子表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `user_name` varchar(100) NOT NULL COMMENT '域名名称，唯一值[用户名]',
  `nick_name` varchar(100) DEFAULT NULL COMMENT '用于显示的昵称',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `salt` varchar(100) DEFAULT NULL COMMENT '盐，用于加密',
  `email` varchar(100) DEFAULT NULL COMMENT 'Email',
  `head_url` varchar(800) DEFAULT NULL COMMENT '用户头像',
  `introduction` varchar(800) DEFAULT NULL COMMENT '自我介绍',
  `signature` varchar(4000) DEFAULT NULL COMMENT '个性签名',
  `topic_count` int(11) DEFAULT NULL COMMENT '发贴数',
  `reply_count` int(11) DEFAULT NULL COMMENT '帖子回复数',
  `best_topic_count` int(11) DEFAULT NULL COMMENT '精华贴子数',
  `last_topic_id` bigint(20) DEFAULT NULL COMMENT '最后发贴ID',
  `last_reply_id` bigint(20) DEFAULT NULL COMMENT '最后回复贴ID',
  `amount` int(11) DEFAULT NULL COMMENT '积分',
  `state` int(11) DEFAULT NULL COMMENT '用户状态',
  `created_date` datetime DEFAULT NULL COMMENT '创建时间',
  `fans_count` int(11) DEFAULT NULL COMMENT '粉丝数量',
  `follow_count` int(11) DEFAULT NULL COMMENT '关注对象数量',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号码',
  `flag` int(11) DEFAULT NULL COMMENT '用户标记 1-正常用户  2-虚拟用户',
  `accept_image` int(11) DEFAULT NULL COMMENT 'client接收图片 0-关闭 1-开启 ',
  `accept_push_message` int(11) DEFAULT NULL COMMENT 'client接收推送消息 0-不接收 1-接收',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_channel`
--

DROP TABLE IF EXISTS `user_channel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_channel` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `user_id` bigint(20) NOT NULL COMMENT 'FK 用户ID',
  `channel_id` varchar(100) NOT NULL COMMENT '通道ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户通道表（用户消息推送）';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_group`
--

DROP TABLE IF EXISTS `user_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `name` varchar(100) NOT NULL COMMENT '组名',
  `group_value` varchar(100) DEFAULT NULL COMMENT '逻辑值',
  `description` varchar(200) DEFAULT NULL COMMENT '描述',
  `created_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户组';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_group_relation`
--

DROP TABLE IF EXISTS `user_group_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_group_relation` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `user_id` bigint(20) DEFAULT NULL COMMENT 'FK 用户ID',
  `group_id` bigint(20) DEFAULT NULL COMMENT 'FK 用户组ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户、用户组关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_relation`
--

DROP TABLE IF EXISTS `user_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `created_date` datetime DEFAULT NULL COMMENT '创建时间',
  `type` int(11) DEFAULT NULL COMMENT '关系类型 0-关注',
  `from_id` bigint(20) NOT NULL COMMENT 'FK 用户ID',
  `to_id` bigint(20) NOT NULL COMMENT 'FK 被关联用户ID',
  `description` varchar(100) DEFAULT NULL COMMENT '给关系人的备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `user_id` bigint(20) NOT NULL COMMENT 'FK 用户ID',
  `role_id` bigint(20) NOT NULL COMMENT 'FK 角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='用户、角色关联表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-09-03 16:44:09
