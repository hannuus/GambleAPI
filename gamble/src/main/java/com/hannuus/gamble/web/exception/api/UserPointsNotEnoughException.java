package com.hannuus.gamble.web.exception.api;

import com.hannuus.gamble.comm.GambleAPIErrorCode;
import com.hannuus.gamble.web.exception.GambleException;

public class UserPointsNotEnoughException extends GambleException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2772408066486659864L;
	
	public UserPointsNotEnoughException() {
		super(GambleAPIErrorCode.UserPointsNotEnough.getCode(), GambleAPIErrorCode.UserPointsNotEnough.getReasoning());
	}
}
