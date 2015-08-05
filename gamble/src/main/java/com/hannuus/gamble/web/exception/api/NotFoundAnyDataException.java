package com.hannuus.gamble.web.exception.api;

import com.hannuus.gamble.comm.GambleAPIErrorCode;
import com.hannuus.gamble.web.exception.GambleException;

public class NotFoundAnyDataException extends GambleException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2830025252331466928L;

	public NotFoundAnyDataException() {
		super(GambleAPIErrorCode.NotFoundAnyData.getCode(), GambleAPIErrorCode.NotFoundAnyData.getReasoning());
	}
}
