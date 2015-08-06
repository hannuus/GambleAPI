package com.hannuus.gamble.bean;

import com.google.gson.Gson;
import com.hannuus.gamble.utils.AESHelper;

public class AccessToken {
	
	private String id; // user id
	
	private String timestamp;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
