package com.hannuus.gamble.comm;

public enum GambleAPIErrorCode {
	
	ArgumentsIncorrect("10001", "参数不正确"),
	Timeout("10002", "API调用超时");
	
	private String code;
	private String reasoning;
	
	private GambleAPIErrorCode(String code, String reasoning) {
		this.code = code;
		this.reasoning = reasoning;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the reasoning
	 */
	public String getReasoning() {
		return reasoning;
	}

	/**
	 * @param reasoning the reasoning to set
	 */
	public void setReasoning(String reasoning) {
		this.reasoning = reasoning;
	}
}
