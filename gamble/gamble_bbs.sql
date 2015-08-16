CREATE DATABASE /*!32312 IF NOT EXISTS*/`gamble_bbs` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `gamble_bbs`;

/*Table structure for table `bbs_reply` */

DROP TABLE IF EXISTS `bbs_reply`;

CREATE TABLE `bbs_reply` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID标识',
  `topic_id` bigint(20) DEFAULT NULL COMMENT '主题ID',
  `title` varchar(300) DEFAULT NULL COMMENT '标题',
  `content` text COMMENT '回复内容',
  `user_id` bigint(20) DEFAULT NULL COMMENT '回复用户ID',
  `modified_on` datetime DEFAULT NULL COMMENT '最后编辑时间',
  `created_on` datetime DEFAULT NULL COMMENT '创建时间',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父级回复ID',
  `up_count` bigint(20) DEFAULT NULL COMMENT '获赞总计',
  `down_count` bigint(20) DEFAULT NULL COMMENT '被踩总计',
  `collection_count` bigint(20) DEFAULT NULL COMMENT '被收藏总计',
  `reply_count` bigint(20) DEFAULT NULL COMMENT '被评论总计',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `bbs_reply` */

/*Table structure for table `bbs_role_group` */

DROP TABLE IF EXISTS `bbs_role_group`;

CREATE TABLE `bbs_role_group` (
  `id` bigint(20) NOT NULL,
  `name` varchar(300) DEFAULT NULL COMMENT '管理组名称',
  `key` varchar(100) DEFAULT NULL COMMENT '组key,唯一值，等效于ID',
  `created_on` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `bbs_role_group` */

/*Table structure for table `bbs_role_group_right` */

DROP TABLE IF EXISTS `bbs_role_group_right`;

CREATE TABLE `bbs_role_group_right` (
  `id` bigint(20) NOT NULL,
  `group_id` bigint(20) DEFAULT NULL COMMENT '管理组ID',
  `manage_id` bigint(20) DEFAULT NULL COMMENT '单个管理权限单元ID',
  `created_on` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `bbs_role_group_right` */

/*Table structure for table `bbs_role_manage` */

DROP TABLE IF EXISTS `bbs_role_manage`;

CREATE TABLE `bbs_role_manage` (
  `id` bigint(20) NOT NULL,
  `name` varchar(300) DEFAULT NULL COMMENT '管理权限名称',
  `key` varchar(300) DEFAULT NULL COMMENT '唯一管理权限标识符，用与区分此单个管理权限单元',
  `created_on` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `bbs_role_manage` */

/*Table structure for table `bbs_topic` */

DROP TABLE IF EXISTS `bbs_topic`;

CREATE TABLE `bbs_topic` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID标识',
  `category_id` bigint(20) DEFAULT NULL COMMENT '版块分类ID',
  `specia_id` bigint(20) DEFAULT NULL COMMENT '主题所属专题ID',
  `content` text COMMENT '内容',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `enabled` bit(1) DEFAULT NULL COMMENT '是否启用',
  `hits` bigint(20) DEFAULT NULL COMMENT '访问总量',
  `reply_count` bigint(20) DEFAULT NULL COMMENT '回复总计',
  `modified_by` bigint(20) DEFAULT NULL COMMENT '最后编辑用户ID',
  `modified_on` datetime DEFAULT NULL COMMENT '最后编辑时间',
  `replied_by` bigint(20) DEFAULT NULL COMMENT '最后回复用户ID',
  `replied_on` datetime DEFAULT NULL COMMENT '最后回复时间',
  `icon_id` bigint(20) DEFAULT NULL COMMENT '主题图标ID',
  `state` int(11) DEFAULT NULL COMMENT '状态[关闭贴不给回复] -1',
  `img_url` varchar(400) DEFAULT NULL COMMENT '帖子展示图片.',
  `created_on` datetime DEFAULT NULL COMMENT '创建时间',
  `title` varchar(300) DEFAULT NULL COMMENT '标题',
  `tag_id` bigint(20) DEFAULT NULL COMMENT '标签ID',
  `follow_count` bigint(20) DEFAULT NULL COMMENT '关注总计',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `bbs_topic` */

insert  into `bbs_topic`(`ID`,`category_id`,`specia_id`,`content`,`user_id`,`enabled`,`hits`,`reply_count`,`modified_by`,`modified_on`,`replied_by`,`replied_on`,`icon_id`,`state`,`img_url`,`created_on`,`title`,`tag_id`,`follow_count`) values (3,1,2,'原问题还包括香料除了超市调料架上的种种我觉得还可以再推广到更广义些的概念。比如，大块柠檬煮小龙虾的柠檬，陈皮，用来调味的中药像甘草等等。各位答题的大大请务必不要拘谨~',2,'',0,0,NULL,NULL,NULL,NULL,2,1,'http://pic3.zhimg.com/8529aceef19be4425814c1764853c222_b.jpg','2015-07-31 01:41:59','食物该如何搭配香料呢？',1,0);

/*Table structure for table `bbs_user` */

DROP TABLE IF EXISTS `bbs_user`;

CREATE TABLE `bbs_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID标识',
  `user_name` varchar(100) DEFAULT NULL COMMENT '域名名称,唯一值[用户名]',
  `nick_name` varchar(100) DEFAULT NULL COMMENT '昵称',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `email` varchar(100) DEFAULT NULL COMMENT 'Email',
  `head_url` varchar(100) DEFAULT NULL COMMENT '用户头像',
  `introduction` varchar(800) DEFAULT NULL COMMENT '自我介绍',
  `signature` varchar(4000) DEFAULT NULL COMMENT '个性签名',
  `topic_count` int(11) DEFAULT NULL COMMENT '发贴数',
  `reply_count` int(11) DEFAULT NULL COMMENT '帖子回复数',
  `best_topic_count` int(11) DEFAULT NULL COMMENT '精华贴子数',
  `last_topic_id` bigint(20) DEFAULT NULL COMMENT '最后发贴ID',
  `last_reply_id` bigint(20) DEFAULT NULL COMMENT '最后回复贴ID',
  `amount` bigint(20) DEFAULT NULL COMMENT '积分',
  `state` int(1) DEFAULT NULL COMMENT '用户状态',
  `created_on` datetime DEFAULT NULL COMMENT '创建时间',
  `fans_count` int(11) DEFAULT NULL COMMENT '粉丝数量',
  `follow_count` int(11) DEFAULT NULL COMMENT '关注对象数量',
  `mobile` bigint(20) DEFAULT NULL COMMENT '手机号码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `bbs_user` */

/*Table structure for table `bbs_user_channel` */

DROP TABLE IF EXISTS `bbs_user_channel`;

CREATE TABLE `bbs_user_channel` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `channel_id` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `bbs_user_channel` */

/*Table structure for table `bbs_user_relation` */

DROP TABLE IF EXISTS `bbs_user_relation`;

CREATE TABLE `bbs_user_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户关系ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `relation_type` int(11) DEFAULT NULL COMMENT '关系类型, 0关注, 1收藏',
  `from_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `to_id` bigint(20) DEFAULT NULL COMMENT '被关联用户ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `bbs_user_relation` */

/*Table structure for table `bbs_user_right` */

DROP TABLE IF EXISTS `bbs_user_right`;

CREATE TABLE `bbs_user_right` (
  `id` bigint(20) NOT NULL,
  `category_id` bigint(20) DEFAULT NULL COMMENT '所对应的版块分类ID',
  `group_id` bigint(20) DEFAULT NULL COMMENT '社区版块管理组ID',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `enabled` tinyint(1) DEFAULT NULL COMMENT '是否启用',
  `created_on` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;