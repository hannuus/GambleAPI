package com.hannuus.gamble.comm;

public enum UserStates {
	Willdelete(-1),
	Locked(0),
	Normal(1);
	
	private int value;

	private UserStates(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}
