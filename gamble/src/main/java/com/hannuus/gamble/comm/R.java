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

}
