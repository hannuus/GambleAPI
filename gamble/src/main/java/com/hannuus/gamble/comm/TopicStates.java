package com.hannuus.gamble.comm;

public enum TopicStates {
	Willdelete(-1),
	Normal(1);
	
	private int value;

	private TopicStates(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}
