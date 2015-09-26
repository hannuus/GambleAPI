package com.hannuus.validation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 校验规则引擎
 * 
 * @author cuesky
 * @date 2015年9月10日 下午9:49:41
 * @version 1.0
 */
@Component
public class ValidateEngine {

	@Autowired
	RulesContainer rulesContainer;

	public ValidateResult executeValidate(ValidateParams params) {
		List<Validator> rules = rulesContainer.getRules(params);
		for (Validator validator : rules) {
			ValidateResult result = validator.validate(params);
			if (result.getCode() == ValidateResult.FAILURE) {
				return result;
			}
		}
		return new ValidateResult(ValidateResult.SUCCESS);
	}

}
