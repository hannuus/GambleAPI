package com.hannuus.gamble.web.exception.api;

import com.hannuus.gamble.comm.GambleAPIErrorCode;
import com.hannuus.gamble.web.exception.GambleException;

public class TimeoutCallingException extends GambleException {

	private static final long serialVersionUID = 2426099428515832936L;

	public TimeoutCallingException() {
		super(GambleAPIErrorCode.TimeoutCall.getCode(), GambleAPIErrorCode.TimeoutCall.getReasoning());
	}
}
