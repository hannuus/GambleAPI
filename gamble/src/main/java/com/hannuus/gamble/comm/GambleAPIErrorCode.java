package com.hannuus.gamble.comm;

public enum GambleAPIErrorCode {
	
	ArgumentsIncorrect("10001", "参数不正确"),
	Timeout("10002", "API调用超时"),
	EmptyFile("10003", "没有要上传的文件"),
	UnSupportFileSize("10004", "文件大小错误, 不能超过2M"),
	UnSupoortFileType("10005", "文件类型错误, 只支持jpg, jpe, gif, png格式照片"),
	ImageUploadFailed("10006", "文件上传失败");
	
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
