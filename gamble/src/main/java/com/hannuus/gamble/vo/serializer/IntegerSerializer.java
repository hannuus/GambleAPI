package com.hannuus.gamble.vo.serializer;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

public class IntegerSerializer extends JsonSerializer<Integer> {

	@Override
	public void serialize(Integer value, JsonGenerator jg,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
		jg.writeString(value.toString());
	}
}
