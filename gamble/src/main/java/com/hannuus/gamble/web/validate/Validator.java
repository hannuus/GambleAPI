package com.hannuus.gamble.web.validate;

import org.apache.log4j.Logger;

/**
 * 校验规则基类
 * 
 * @author cuesky
 * @date 2015年9月10日 下午9:50:44
 */
public abstract class Validator implements Comparable<Validator> {

	protected Logger logger = Logger.getLogger(getClass());

	/** 规则所处命名空间 */
	public final static String NS = "com.hannuus.gamble.web.validate.validator";

	/**
	 * 校验入口方法
	 * 
	 * @param params
	 * @return
	 */
	public abstract ValidateResult validate(ValidateParams params);

	/**
	 * 提供规则序号
	 * 
	 * @return
	 */
	public abstract int getOrder();

	@Override
	public int compareTo(Validator o) {
		int x = getOrder();
		int y = o.getOrder();
		return (x < y) ? -1 : ((x == y) ? 0 : 1);
	}

}
