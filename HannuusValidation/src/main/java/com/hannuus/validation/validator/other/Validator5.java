package com.hannuus.validation.validator.other;

import org.springframework.stereotype.Component;

import com.hannuus.validation.ValidateParams;
import com.hannuus.validation.ValidateResult;
import com.hannuus.validation.Validator;

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
