package com.hannuus.gamble.vo;

public enum JsonResultStatus {
	Success("0"),
	Failed("1"),
	EmptyResult("2");
	
	private String value;
	
	JsonResultStatus(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
