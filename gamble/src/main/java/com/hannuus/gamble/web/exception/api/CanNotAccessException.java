package com.hannuus.gamble.web.exception.api;

import com.hannuus.gamble.comm.GambleAPIErrorCode;
import com.hannuus.gamble.web.exception.GambleException;

public class CanNotAccessException extends GambleException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3404607636122554946L;

	public CanNotAccessException() {
		super(GambleAPIErrorCode.CanNotAccessResource.getCode(), GambleAPIErrorCode.CanNotAccessResource.getReasoning());
	}
}
