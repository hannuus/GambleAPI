package com.hannuus.gamble.comm;

import java.util.Map;
import com.google.common.collect.Maps;

public enum UserState {
	Willdelete,
	Locked,
	Normal;
	
	private static Map<UserState, Integer> valueMap = Maps.newConcurrentMap();
	private static Map<Integer, UserState> instanceMap = Maps.newConcurrentMap();
	
	static {
		valueMap.put(UserState.Willdelete, -1);
		valueMap.put(UserState.Locked, -1);
		valueMap.put(UserState.Normal, 1);
		
		for (UserState state : valueMap.keySet()) {
			instanceMap.put(valueMap.get(state), state);
		}
	}
	
	public int value() {
		return valueMap.get(this);
	}
	
	public static UserState parse(int value) {
        if (!instanceMap.containsKey(value)) {
             throw new IllegalArgumentException(value + " is not a valid TopicState");
        }
        return instanceMap.get(value);
    }
}
