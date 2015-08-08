package com.hannuus.core.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	/** yyyy-MM-dd */
	public static String YYYYMMDD = "yyyy-MM-dd";

	public static String format(Date date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	/**
	 * 基于偏移量的日期比较
	 * 
	 * @param millis1
	 * @param millis2
	 * @param field
	 *            时间单位
	 * @param offset
	 *            偏移量
	 * @return 等于0 - millis1 equalTo millis2<br>
	 *         小于0 - millis1 before millis2<br>
	 *         大于0 - millis1 after millis2<br>
	 */
	public static int compareBaseOnOffset(long millis1, long millis2,
			int field, int offset) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTimeInMillis(millis1);
		cal1.add(field, offset);

		Calendar cal2 = Calendar.getInstance();
		cal2.setTimeInMillis(millis2);

		return cal1.compareTo(cal2);
	}

	/**
	 * 基于偏移量的日期比较
	 * 
	 * @param date1
	 * @param date2
	 * @param field
	 *            时间单位
	 * @param offset
	 *            偏移量
	 * @return 等于0 - date1 equalTo date2<br>
	 *         小于0 - date1 before date2<br>
	 *         大于0 - date1 after date2<br>
	 */
	public static int compareBaseOnOffset(Date date1, Date date2, int field,
			int offset) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		cal1.add(field, offset);

		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);

		return cal1.compareTo(cal2);
	}

	/**
	 * 以mid为中心，判断date是否落在以正负offset为偏移量的范围<br>
	 * 注：不含边界
	 * 
	 * @param date
	 * @param mid
	 *            中心时间点
	 * @param field
	 *            时间单位
	 * @param offset
	 *            偏移量
	 * @return
	 */
	public static boolean around(long date, long mid, int field, int offset) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTimeInMillis(mid);
		cal1.add(field, -offset);

		Calendar cal2 = Calendar.getInstance();
		cal2.setTimeInMillis(mid);
		cal2.add(field, offset);

		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(date);

		return cal.after(cal1) && cal.before(cal2);
	}

	/**
	 * 以mid为中心，判断date是否落在以正负offset为偏移量的范围<br>
	 * 注：不含边界
	 * 
	 * @param date
	 * @param mid
	 *            中心时间点
	 * @param field
	 *            时间单位
	 * @param offset
	 *            偏移量
	 * @return
	 */
	public static boolean around(Date date, Date mid, int field, int offset) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(mid);
		cal1.add(field, -offset);

		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(mid);
		cal2.add(field, offset);

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		return cal.after(cal1) && cal.before(cal2);
	}

}
