package com.hannuus.gamble.web.exception.api;

import com.hannuus.gamble.comm.GambleAPIErrorCode;
import com.hannuus.gamble.web.exception.GambleException;

public class InvalidAccessTokenException extends GambleException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7117287618118746659L;

	public InvalidAccessTokenException() {
		super(GambleAPIErrorCode.InvaliAccessToken.getCode(), GambleAPIErrorCode.InvaliAccessToken.getReasoning());
	}
}
