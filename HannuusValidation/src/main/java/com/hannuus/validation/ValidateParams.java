package com.hannuus.validation;

/**
 * 校验参数<br>
 * ruleId：用来标识某个业务场景所使用的所有校验规则所处的命名空间<br>
 * 如ruleId为abc，则相关的校验规则实现类必须集中放置在<br>
 * com.hannuus.validation.validator.abc下
 * 
 * @author cuesky
 * @date 2015年9月10日 下午9:52:23
 */
public class ValidateParams {

	/** 规则ID，唯一 */
	private String ruleId;
	/** 被校验目标 */
	private Object target;

	public String getRuleId() {
		return ruleId;
	}

	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}

	public Object getTarget() {
		return target;
	}

	public void setTarget(Object target) {
		this.target = target;
	}

}
