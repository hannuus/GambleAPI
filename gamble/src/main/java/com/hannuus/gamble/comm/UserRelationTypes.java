package com.hannuus.gamble.comm;

public enum UserRelationTypes {
	Follow(0),
	Collect(1);
	
	private int value;

	private UserRelationTypes(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}
