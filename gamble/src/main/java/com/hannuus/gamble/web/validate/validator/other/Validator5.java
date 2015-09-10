package com.hannuus.gamble.web.validate.validator.other;

import org.springframework.stereotype.Component;

import com.hannuus.gamble.web.validate.ValidateParams;
import com.hannuus.gamble.web.validate.ValidateResult;
import com.hannuus.gamble.web.validate.Validator;

@Component
public class Validator5 extends Validator {

	private int order = 2;

	@Override
	public ValidateResult validate(ValidateParams params) {
		System.out.println("validator5...");
		return new ValidateResult(ValidateResult.SUCCESS, "");
	}

	@Override
	public int getOrder() {
		return order;
	}

}
