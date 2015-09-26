package com.hannuus.validation;

import org.apache.log4j.Logger;

/**
 * 校验规则基类<br>
 * 该类通过getOrder()来维持多个校验规则之间的执行顺序(按照从小到大的顺序执行每条校验规则)<br>
 * order无须从0开始，也无须保持连续，但是多个规则之间的order不可重复，并且负数将被忽略
 * 
 * @author cuesky
 * @date 2015年9月10日 下午9:50:44
 */
public abstract class Validator implements Comparable<Validator> {

	protected Logger logger = Logger.getLogger(getClass());

	/** 规则所处命名空间 */
	public final static String NS = "com.hannuus.validation.validator";

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
