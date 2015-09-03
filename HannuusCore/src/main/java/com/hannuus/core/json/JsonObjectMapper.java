package com.hannuus.core.json;

import java.io.IOException;  

import com.fasterxml.jackson.core.JsonGenerator;  
import com.fasterxml.jackson.core.JsonGenerator.Feature;
import com.fasterxml.jackson.core.JsonProcessingException;  
import com.fasterxml.jackson.databind.JsonSerializer;  
import com.fasterxml.jackson.databind.ObjectMapper;  
//import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializerProvider;  
  
/** 
 * @description: JSON格式转换
 */  
public class JsonObjectMapper extends ObjectMapper {  
    private static final long serialVersionUID = 1L;
  
    public JsonObjectMapper() {  
        super();
        this.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {  
            @Override  
            public void serialize(Object value, JsonGenerator jg, SerializerProvider sp) throws IOException, JsonProcessingException {  
                jg.writeString("");  
            }
        });
        
        this.configure(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        this.configure(Feature.WRITE_NUMBERS_AS_STRINGS, true);
        this.configure(Feature.QUOTE_NON_NUMERIC_NUMBERS, true);
        this.configure(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
    }
}  
