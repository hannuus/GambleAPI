package com.hannuus.gamble.web.constant;

import com.hannuus.gamble.utils.PropertyUtils;

/**
 * System constant
 * 
 */
public class SystemConstant {

	/**
	 * deployment root
	 */
	public static String ESQUIFY_WEB_ROOT = PropertyUtils.getRoot();

	/**
	 * upload folder
	 */
	public static String UPLOAD_FOLDER = "upload/";
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

}
