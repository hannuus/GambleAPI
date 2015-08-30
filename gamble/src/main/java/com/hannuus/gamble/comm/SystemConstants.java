package com.hannuus.gamble.comm;

import com.hannuus.gamble.utils.GambleUtils;

/**
 * System constant
 * 
 */
public class SystemConstants {

	/**
	 * deployment root
	 */
	public static String ESQUIFY_WEB_ROOT = GambleUtils.Property.getRoot();

	/**
	 * upload folder
	 */
	public static String UPLOAD_FOLDER = "upload/";

	/**
	 * user logo folder
	 */
	public static String UPLOAD_FOLDER_USER_LOGO = "upload/logo";
	/**
	 * upload admin folder
	 */
	public static String UPLOAD_ADMIN_FOLDER = "upload/admin/";

	/**
	 * backup folder
	 */
	public static String BACKUP_FOLDER = "/WEB-INF/backup";

	/**
	 * 
	 */
	public static final String LANGUAGE = "language";

	/**
	 * 用户登录session key
	 */
	public static final String USER_KEY = "user_key";

	public static final String ADMIN_USER_KEY = "admin_user_key";

	public static final int DEFAULT_PAGE_SIZE = 12;

}
