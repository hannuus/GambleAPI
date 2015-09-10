package com.hannuus.gamble.web.validate;

/**
 * 校验参数
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
