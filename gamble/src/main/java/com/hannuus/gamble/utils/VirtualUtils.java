package com.hannuus.gamble.utils;

import java.util.Calendar;
import java.util.Random;

import com.hannuus.gamble.comm.R;

/**
 * 创建虚拟用户内容相关
 * 
 * @author cuesky
 * @date 2015年9月19日上午9:40:43
 */
public class VirtualUtils {

	private static final String dict = "abcdefghijklmnopqrstuvwxyz";

	private static final Random ran = new Random();

	/**
	 * 生成随机userName
	 * 
	 * @param seed
	 *            用于生成虚拟用户名的种子字符串
	 * @param num
	 *            生成虚拟用户名的数量
	 * @return seed+randonString+YYYY
	 */
	public static String[] generateUserNames(String seed, int num) {
		String[] userNames = new String[num];
		// 获取当前年份
		int year = Calendar.getInstance().get(Calendar.YEAR);
		// 随机字符串可用长度
		int len = R.user.username_limit - 4 - seed.length();
		if (len < 1) {
			len = 1;
			// 保证有1位随机字符串，可能与现有userName冲突，几率较小
			seed = seed.substring(0, R.user.username_limit - 5);// 保证有1位随机字符串
		}
		for (int i = 0; i < num; i++) {
			StringBuilder buff = new StringBuilder();
			buff.append(seed);
			for (int j = 0; j < len; j++) {
				buff.append(dict.charAt(ran.nextInt(dict.length() + 1)));
			}
			buff.append(year - i);
			userNames[i] = buff.toString();
		}
		return userNames;
	}

}
