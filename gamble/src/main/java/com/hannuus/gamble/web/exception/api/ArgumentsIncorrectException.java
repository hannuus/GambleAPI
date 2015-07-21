package com.hannuus.gamble.web.exception.api;

import com.hannuus.gamble.comm.GambleAPIErrorCode;
import com.hannuus.gamble.web.exception.GambleException;

public class ArgumentsIncorrectException extends GambleException {

	private static final long serialVersionUID = -698281893764433657L;

	public ArgumentsIncorrectException() {
		super(GambleAPIErrorCode.ArgumentsIncorrect.getCode(), GambleAPIErrorCode.ArgumentsIncorrect.getReasoning());
	}
}
