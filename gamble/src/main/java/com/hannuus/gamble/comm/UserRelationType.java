package com.hannuus.gamble.comm;

import java.util.Map;

import com.google.common.collect.Maps;

public enum UserRelationType {
	Invalid,
	Follow,
	Collect;
	
	private static Map<UserRelationType, Integer> valueMap = Maps.newConcurrentMap();
	private static Map<Integer, UserRelationType> instanceMap = Maps.newConcurrentMap();
	
	static {
		valueMap.put(UserRelationType.Invalid, -1);
		valueMap.put(UserRelationType.Follow, 1);
		valueMap.put(UserRelationType.Collect, 2);
		
		for (UserRelationType state : valueMap.keySet()) {
			instanceMap.put(valueMap.get(state), state);
		}
	}
	
	public int value() {
		return valueMap.get(this);
	}
	
	public static UserRelationType parse(int value) {
        if (!instanceMap.containsKey(value)) {
             throw new IllegalArgumentException(value + " is not a valid UserRelationType");
        }
        return instanceMap.get(value);
    }
}
