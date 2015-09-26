package com.hannuus.validation.validator.other;

import org.springframework.stereotype.Component;

import com.hannuus.validation.ValidateParams;
import com.hannuus.validation.ValidateResult;
import com.hannuus.validation.Validator;

@Component
public class Validator4 extends Validator {

	private int order = 1;

	@Override
	public ValidateResult validate(ValidateParams params) {
		System.out.println("validator4...");
		return new ValidateResult(ValidateResult.SUCCESS, "");
	}

	@Override
	public int getOrder() {
		return order;
	}

}
