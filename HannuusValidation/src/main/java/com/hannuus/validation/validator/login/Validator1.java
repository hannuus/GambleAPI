package com.hannuus.validation.validator.login;

import org.springframework.stereotype.Component;

import com.hannuus.validation.ValidateParams;
import com.hannuus.validation.ValidateResult;
import com.hannuus.validation.Validator;

@Component
public class Validator1 extends Validator {

	private int order = 1;

	@Override
	public ValidateResult validate(ValidateParams params) {
		logger.debug("execute validator1...");
		return new ValidateResult(ValidateResult.SUCCESS, "");
	}

	@Override
	public int getOrder() {
		return order;
	}

}
