package com.hannuus.gamble.vo.serializer;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

public class DoubleSerializer extends JsonSerializer<Double> {

	@Override
	public void serialize(Double value, JsonGenerator jg,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
		jg.writeString(value.toString());
	}
}
