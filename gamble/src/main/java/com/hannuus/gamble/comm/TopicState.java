package com.hannuus.gamble.comm;

import java.util.Map;

import com.google.common.collect.Maps;

public enum TopicState {
	Willdelete,
	Normal;
	
	private static Map<TopicState, Integer> valueMap = Maps.newConcurrentMap();
	private static Map<Integer, TopicState> instanceMap = Maps.newConcurrentMap();
	
	static {
		valueMap.put(TopicState.Normal, 0);
		valueMap.put(TopicState.Willdelete, -1);
		
		for (TopicState state : valueMap.keySet()) {
			instanceMap.put(valueMap.get(state), state);
		}
	}
	
	public int value() {
		return valueMap.get(this);
	}
	
	public static TopicState parse(int value) {
        if (!instanceMap.containsKey(value)) {
             throw new IllegalArgumentException(value + " is not a valid TopicState");
        }
        return instanceMap.get(value);
    }
}
