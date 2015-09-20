package com.hannuus.gamble.comm;

import java.util.Calendar;

/**
 * 该类设置为保存所有常量<br>
 * 为便于阅读，内部类类名使用小写（尽量使用一个单词，如有两个或以上则连写），常量使用snake命名法。
 * 
 * @author cuesky
 * @date 2015年8月5日 下午8:15:33
 */
public final class R {

	/** Request作用域范围各种key */
	public static final class request {

		public static final String timestamp = "timestamp";
		public static final String access_token = "accessToken";

	}

	/** Request作用域范围各种key */
	public static final class session {

		public static final String user = "userInSession";
		public static final String access_token = "accessToken";

	}

	/** 常规Client与Server端的约定 */
	public static final class rules {

		/** 超时计时单位 */
		public static final int timeout_unit = Calendar.MINUTE;
		/** 超时阀值 */
		public static final int timeout_interval = 10;

	}

	/**
	 * 百度推送
	 * 
	 * @author cuesky
	 * @date 2015年9月3日 上午9:22:58
	 */
	public static final class baidupush {

		public static final String api_key = "eMl56e2c5izUBNLwywhUCSiK";

		public static final String secret_key = "4lSzEPDLPCgvyK3q49ekzp38TNaBKgYl";

		/** 有效时间，单位second */
		public static final Integer msg_expires = 3600;

		/** 定时推送时间，必需超过当前时间一分钟，单位second */
		public static final Long msg_send_time = System.currentTimeMillis() / 1000 + 1 * 60;

		/** 透传消息(不支持IOS) */
		public static final Integer msg_type_message = 0;

		/** 通知 */
		public static final Integer msg_type_notification = 1;

		public static final Integer device_type_android = 3;
		public static final Integer device_type_ios = 4;

	}

	/**
	 * 分页
	 * 
	 * @author cuesky
	 * @date 2015年9月3日 上午9:23:13
	 */
	public static final class page {

		public static final int default_page_size = 12;

		public static final String attr_key = "page";

	}

	/**
	 * 全局参数配置
	 * 
	 * @author cuesky
	 * @date 2015年9月3日 上午9:33:22
	 */
	public static final class global {

		public static final String enable = "1";

		public static final String disable = "0";

		// 第三方API相关

		public static final String third_party_api = "thirdPartyApi";

		public static final String tencent_key = "tencent";

		public static final String webchat_key = "webchat";

		public static final String baidu_key = "baidu";

		public static final String weibo_key = "weibo";

		// 客户端设置相关

		public static final String client_setting = "clientSetting";

		public static final String push_key = "push";

		public static final String image_key = "image";

		// 虚拟内容相关

		public static final String virtual = "virtual";

		public static final String virtual_subject_key = "virtualSubject";

		public static final String virtual_verb_key = "virtualVerb";

		public static final String virtual_content_key = "virtualContent";

		/** 采样天数(距离今天最近N天) */
		public static final String virtual_sample_day_key = "virtualSampleDay";// int

		/** 虚拟回复因子：虚拟回复数=采样数/因子(整数) */
		public static final String virtual_reply_factor_key = "virtualReplyFactor";// int
	}

	/**
	 * 活动相关
	 * 
	 * @author cuesky
	 * @date 2015年9月11日 下午11:21:24
	 */
	public static final class activity {

		// TODO cuesky
		public static final String login_uri = "/gamble/api/custom/login.json";
		public static final Integer login_type = 1;

		public static final String logout_uri = "/gamble/api/custom/logout.json";
		public static final Integer logout_type = 2;

		public static final String post_uri = "post";
		public static final Integer post_type = 3;

		public static final String reply_uri = "reply";
		public static final Integer reply_type = 4;

		public static final String mark_uri = "mark";
		public static final Integer mark_type = 5;

		public static final String collect_uri = "collect";
		public static final Integer collect_type = 6;

		public static final String email_uri = "email";
		public static final Integer email_type = 7;

		public static final String search_uri = "search";
		public static final Integer search_type = 8;

	}

	/**
	 * 用户相关
	 * 
	 * @author cuesky
	 * @date 2015年9月18日下午5:38:56
	 */
	public static final class user {

		/** 用户名最大长度 */
		public static final Integer username_limit = 12;

		/** 真实用户 */
		public static final Integer reality_type = 1;
		/** 虚拟用户 */
		public static final Integer virtual_type = 2;

	}

	public static final class reply {

		/** 真实回复 */
		public static final Integer reality_type = 1;
		/** 虚拟虚拟回复 */
		public static final Integer virtual_type = 2;

	}

}
