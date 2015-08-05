package com.hannuus.gamble.web.exception.api;

import com.hannuus.gamble.comm.GambleAPIErrorCode;
import com.hannuus.gamble.web.exception.GambleException;

public class InvalidRequestURLException extends GambleException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5779511351451638862L;

	public InvalidRequestURLException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InvalidRequestURLException(String reasoning) {
		super(GambleAPIErrorCode.InvalidRequestPath.getCode(), reasoning);
	}
}
