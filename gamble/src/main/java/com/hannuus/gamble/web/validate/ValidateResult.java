package com.hannuus.gamble.web.validate;

/**
 * 校验结果
 * 
 * @author cuesky
 * @date 2015年9月10日 下午8:39:51
 */
public class ValidateResult {

	public final static int FAILURE = 0;
	public final static int SUCCESS = 1;

	/** 状态码 */
	private int code;
	/** 返回的校验结果消息 */
	private String message;

	public ValidateResult() {
	}

	public ValidateResult(int code) {
		this.code = code;
	}

	public ValidateResult(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
