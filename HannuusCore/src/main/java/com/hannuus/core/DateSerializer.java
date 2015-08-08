package com.hannuus.core;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

public class DateSerializer extends JsonSerializer<Date> {
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:MM:ss");
	@Override
	public void serialize(Date value, JsonGenerator jg,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
		jg.writeString(sdf.format(value));
	}
}
