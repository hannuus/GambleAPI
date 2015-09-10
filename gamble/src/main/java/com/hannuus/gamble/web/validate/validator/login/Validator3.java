package com.hannuus.gamble.web.validate.validator.login;

import org.springframework.stereotype.Component;

import com.hannuus.gamble.web.validate.ValidateParams;
import com.hannuus.gamble.web.validate.ValidateResult;
import com.hannuus.gamble.web.validate.Validator;

@Component
public class Validator3 extends Validator {

	private int order = -1;

	@Override
	public ValidateResult validate(ValidateParams params) {
		logger.debug("execute validator3...");
		return new ValidateResult(ValidateResult.SUCCESS);
	}

	@Override
	public int getOrder() {
		return order;
	}

}
