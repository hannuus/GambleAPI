/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.5.20 : Database - gamble_bbs
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`gamble_bbs` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `gamble_bbs`;

/*Table structure for table `bbs_topic` */

DROP TABLE IF EXISTS `bbs_topic`;

CREATE TABLE `bbs_topic` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID标识',
  `category_id` int(11) NOT NULL COMMENT '版块分类ID',
  `specia_id` int(11) DEFAULT NULL COMMENT '主题所属专题ID',
  `content` text COMMENT '内容',
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `enabled` bit(1) DEFAULT NULL COMMENT '是否启用',
  `hits` int(11) DEFAULT NULL COMMENT '访问总量',
  `reply_count` int(11) DEFAULT NULL COMMENT '回复总计',
  `modified_by` int(11) DEFAULT NULL COMMENT '最后编辑用户ID',
  `modified_on` datetime DEFAULT NULL COMMENT '最后编辑时间',
  `replied_by` int(11) DEFAULT NULL COMMENT '最后回复用户ID',
  `replied_on` int(11) DEFAULT NULL COMMENT '最后回复时间',
  `icon_id` int(11) DEFAULT NULL COMMENT '主题图标ID',
  `state` int(11) DEFAULT NULL COMMENT '状态[关闭贴不给回复] -1',
  `img_url` varchar(400) DEFAULT NULL COMMENT '帖子展示图片.',
  `created_on` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `bbs_topic` */

insert  into `bbs_topic`(`ID`,`category_id`,`specia_id`,`content`,`user_id`,`enabled`,`hits`,`reply_count`,`modified_by`,`modified_on`,`replied_by`,`replied_on`,`icon_id`,`state`,`img_url`,`created_on`) values (1,1,2,NULL,1,'',1,1,1,'0000-00-00 00:00:00',1,0,NULL,NULL,NULL,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
