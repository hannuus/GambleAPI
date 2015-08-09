package com.hannuus.gamble.domain;

import com.google.gson.Gson;
import com.hannuus.core.utils.AESHelper;

/**
 * 用作与mobile client保持session。<br>
 * 用户登录后采集各属性封装成JSON，做AES加密留存server，并发送至client做后续提交使用。
 * 
 * @author aelns
 * @author cuesky
 * @date 2015年8月7日 上午1:40:24
 * @see com.hannuus.core.utils.AESHelper
 * @see com.hannuus.gamble.web.action.BaseAction#isAccessTokenValid()
 */
public class AccessToken {

	/** user id */
	private String id;
	private String sessionId;
	private String timestamp;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		String json = new Gson().toJson(this);
		return AESHelper.encrypt(json);
	}

}
