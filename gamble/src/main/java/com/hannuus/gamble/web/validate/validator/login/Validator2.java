package com.hannuus.gamble.web.validate.validator.login;

import org.springframework.stereotype.Component;

import com.hannuus.gamble.web.validate.ValidateParams;
import com.hannuus.gamble.web.validate.ValidateResult;
import com.hannuus.gamble.web.validate.Validator;

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
