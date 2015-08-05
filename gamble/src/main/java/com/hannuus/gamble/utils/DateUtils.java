package com.hannuus.gamble.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	/**
	 * yyyy-MM-dd
	 */
	public static String YYYYMMDD = "yyyy-MM-dd";
	
	public static String format(Date date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}
}
