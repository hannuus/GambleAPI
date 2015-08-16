package com.hannuus.gamble.comm;

/**
 * all error code message shows in http://localhost:8080/gamble/api/docs/errorCodes
 * @author aelns
 *
 */
public enum GambleAPIErrorCode {
	
	ArgumentsIncorrect("10001", "参数不正确"),
	TimeoutCall("10002", "API调用超时"),
	EmptyUploadFile("10003", "没有要上传的文件"),
	UnSupportFileSize("10004", "文件大小错误, 不能超过2M"),
	UnSupoortFileType("10005", "文件类型错误, 只支持jpg, jpe, gif, png格式照片"),
	ImageUploadFailed("10006", "文件上传失败"),
	UserPointsNotEnough("10007", "用户积分余额不足"),
	NotFoundAnyData("10008", "没有找到相关的数据"),
	InvalidRequestPath("10009", "无效的请求路径"), 
	InvaliAccessToken("10010", "无效的AccessToken"),
	CanNotAccessResource("10011", "无权访问此资源"),
	InvalidSign("10012", "sign验证失败"), 
	InvalidOpration("10013", "无效的操作"),
	UnKnowException("10014", "未知的异常"), 
	PushClient("10015", "推送Client异常"), 
	PushServer("10016", "推送Server异常"),
	;
	
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
