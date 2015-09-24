package com.hannuus.gamble.utils;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import com.hannuus.gamble.comm.SystemConstants;
import com.hannuus.gamble.web.exception.PropertyIsNullException;

/**
 * Gamble Utilities
 * 
 * @author cuesky
 * @date 2015年8月28日 下午12:28:02
 */
public final class GambleUtils {

	/**
	 * @author cuesky
	 * @date 2015年8月29日 下午7:55:42
	 */
	public static final class Security {

		/**
		 * 返回显式角色名称，取roleValue的第一个"_"前面的单词
		 * 
		 * @param roleValue
		 *            显式角色名称，用于@RequiresRoles<br>
		 *            隐式角色名称为完整的roleValue
		 * @param index
		 * @return
		 */
		public static String findRoleValue(String roleValue, int index) {
			String[] arr = roleValue.split("_");
			String role;
			try {
				role = arr[index];
				return role;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}

		/**
		 * 从permissionIds中擦除permissionId字符串
		 * 
		 * @param permissionIds
		 * @param permissionId
		 * @return
		 */
		public static String removePermissionId(String permissionIds,
				Long permissionId) {
			String[] arr = permissionIds.split(",");
			List<String> list = new ArrayList<String>(Arrays.asList(arr));
			ListIterator<String> iterator = list.listIterator();
			String target = String.valueOf(permissionId);
			while (iterator.hasNext()) {
				if (target.equals(iterator.next())) {
					iterator.remove();
				}
			}
			return Array.toString(list);
		}

	}

	/**
	 * @author aelns
	 * @date 2015年8月29日 下午7:55:32
	 */
	public static final class Http {

		/**
		 * Get the request IP
		 * 
		 * @param request
		 * @return
		 */
		public static String getIp(HttpServletRequest request) {
			String ip = request.getHeader("X-Real-IP");
			if (StringUtils.isBlank(ip)) {
				ip = request.getHeader("Host");
			}
			if (StringUtils.isBlank(ip)) {
				ip = request.getHeader("X-Forwarded-For");
			}
			if (StringUtils.isBlank(ip)) {
				ip = "0.0.0.0";
			}
			return ip;
		}

		/**
		 * Get the base path
		 * 
		 * @param request
		 * @return
		 */
		public static String getBasePath(HttpServletRequest request) {
			String path = request.getContextPath();
			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path;
			return basePath;
		}

		/**
		 * Get the base path haven't port
		 * 
		 * @param request
		 * @return
		 */
		public static String getBasePathNotPort(HttpServletRequest request) {
			String path = request.getContextPath();
			String basePath = request.getScheme() + "://"
					+ request.getServerName() + path;
			return basePath;
		}

		/**
		 * Get the context path
		 * 
		 * @param request
		 * @return
		 */
		public static String getContextPath(HttpServletRequest request) {
			String path = request.getContextPath();
			return path;
		}

		/**
		 * @return
		 */
		public static String getRealPath() {
			return SystemConstants.ESQUIFY_WEB_ROOT;
		}
	}

	/**
	 * @author aelns
	 * @date 2015年8月29日 下午7:55:52
	 */
	public static final class Property extends PropertyPlaceholderConfigurer {

		public static final Logger logger = Logger.getLogger(Property.class);

		private static Map<String, String> propertyMap;

		@Override
		protected void processProperties(
				ConfigurableListableBeanFactory beanFactoryToProcess,
				Properties props) throws BeansException {
			super.processProperties(beanFactoryToProcess, props);
			propertyMap = new HashMap<String, String>();
			for (Object key : props.keySet()) {
				String keyStr = key.toString();
				String value = props.getProperty(keyStr);
				propertyMap.put(keyStr, value);
			}
		}

		public static String getValue(String name)
				throws PropertyIsNullException {
			String value = propertyMap.get(name);
			if (StringUtils.isBlank(value)) {
				String error = "Property [" + name + "] is null";
				logger.fatal(error);
				throw new PropertyIsNullException(error);
			} else {
				return value;
			}
		}

		public static String getRoot() {
			String rootKey = "com.hannuus.gamble.root";
			String cmsRoot = System.getProperty(rootKey);
			Enumeration<?> enu = System.getProperties().propertyNames();
			while (enu.hasMoreElements()) {
				Object key = enu.nextElement();
				if (key.toString().startsWith(rootKey)) {
					cmsRoot = System.getProperty(key.toString());
					break;
				}
			}
			logger.info(cmsRoot);
			return cmsRoot;
		}
	}

	/**
	 * @author aelns
	 * @date 2015年8月29日 下午7:59:25
	 */
	public static final class StdOutErrRedirect {

		private final static Log logger = LogFactory
				.getLog(StdOutErrRedirect.class);

		public static void redirectSystemOutAndErrToLog() {
			PrintStream printStreamForOut = createLoggingWrapper(System.out,
					false);
			PrintStream printStreamForErr = createLoggingWrapper(System.out,
					true);
			System.setOut(printStreamForOut);
			System.setErr(printStreamForErr);
		}

		public static PrintStream createLoggingWrapper(
				final PrintStream printStream, final boolean isErr) {
			return new PrintStream(printStream) {
				@Override
				public void print(final String string) {
					if (!isErr) {
						logger.debug(string);
					} else {
						logger.error(string);
					}
				}

				@Override
				public void print(boolean b) {
					if (!isErr) {
						logger.debug(Boolean.valueOf(b));
					} else {
						logger.error(Boolean.valueOf(b));
					}
				}

				@Override
				public void print(char c) {
					if (!isErr) {
						logger.debug(Character.valueOf(c));
					} else {
						logger.error(Character.valueOf(c));
					}
				}

				@Override
				public void print(int i) {
					if (!isErr) {
						logger.debug(String.valueOf(i));
					} else {
						logger.error(String.valueOf(i));
					}
				}

				@Override
				public void print(long l) {
					if (!isErr) {
						logger.debug(String.valueOf(l));
					} else {
						logger.error(String.valueOf(l));
					}
				}

				@Override
				public void print(float f) {
					if (!isErr) {
						logger.debug(String.valueOf(f));
					} else {
						logger.error(String.valueOf(f));
					}
				}

				@Override
				public void print(double d) {
					if (!isErr) {
						logger.debug(String.valueOf(d));
					} else {
						logger.error(String.valueOf(d));
					}
				}

				@Override
				public void print(char[] x) {
					if (!isErr) {
						logger.debug(x == null ? null : new String(x));
					} else {
						logger.error(x == null ? null : new String(x));
					}
				}

				@Override
				public void print(Object obj) {
					if (!isErr) {
						logger.debug(obj);
					} else {
						logger.error(obj);
					}
				}
			};
		}
	}

	public static final class Array {

		public static Long[] toLongArray(String value, String split) {
			String[] arr = value.split(split);
			Long[] array = new Long[arr.length];
			for (int i = 0; i < arr.length; i++) {
				array[i] = Long.valueOf(arr[i].trim());
			}
			return array;
		}

		/**
		 * 将Long数组转换成"1,2,3..."的字符串格式
		 * 
		 * @param ids
		 * @return
		 */
		public static String toString(Long[] ids) {
			StringBuilder b = new StringBuilder();
			for (int i = 0; i < ids.length; i++) {
				b.append(ids[i]);
				b.append(",");
			}
			b.deleteCharAt(b.lastIndexOf(","));
			return b.toString();
		}

		/**
		 * 将List转换成"1,2,3..."的字符串格式
		 * 
		 * @param ids
		 * @return
		 */
		public static String toString(List<String> list) {
			StringBuilder b = new StringBuilder();
			for (int i = 0; i < list.size(); i++) {
				b.append(list.get(i));
				b.append(",");
			}
			b.deleteCharAt(b.lastIndexOf(","));
			return b.toString();
		}

	}

	public static final class File {

		public static String appendTimestamp(String fileName) {
			Calendar cal = Calendar.getInstance();
			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH) + 1;
			int date = cal.get(Calendar.DATE);
			int hour = cal.get(Calendar.HOUR_OF_DAY);
			int minute = cal.get(Calendar.MINUTE);
			int second = cal.get(Calendar.SECOND);
			return fileName + year + month + date + hour + minute + second;
		}
	}

}
