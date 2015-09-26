package com.hannuus.validation.validator.login;

import org.springframework.stereotype.Component;

import com.hannuus.validation.ValidateParams;
import com.hannuus.validation.ValidateResult;
import com.hannuus.validation.Validator;

@Component
public class Validator2 extends Validator {

	private int order = 2;

	@Override
	public ValidateResult validate(ValidateParams params) {
		logger.debug("execute validator2...");
		return new ValidateResult(ValidateResult.SUCCESS);
	}

	@Override
	public int getOrder() {
		return order;
	}

}
