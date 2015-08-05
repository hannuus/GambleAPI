package com.hannuus.gamble.web.exception.api;

import com.hannuus.gamble.comm.GambleAPIErrorCode;
import com.hannuus.gamble.web.exception.GambleException;

public class InvalidSignException extends GambleException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 112412478287309738L;

	public InvalidSignException() {
		super(GambleAPIErrorCode.InvalidSign.getCode(), GambleAPIErrorCode.InvalidSign.getReasoning());
	}
	
}
