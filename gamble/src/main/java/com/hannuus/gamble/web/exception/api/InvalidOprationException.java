package com.hannuus.gamble.web.exception.api;

import com.hannuus.gamble.comm.GambleAPIErrorCode;
import com.hannuus.gamble.web.exception.GambleException;

public class InvalidOprationException extends GambleException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1914193421273983448L;

	public InvalidOprationException() {
		super(GambleAPIErrorCode.InvalidOpration.getCode(), GambleAPIErrorCode.InvalidOpration.getReasoning());
	}
	
}
