package com.hannuus.core.json;

import java.io.IOException;  

import com.fasterxml.jackson.core.JsonGenerator;  
import com.fasterxml.jackson.core.JsonGenerator.Feature;
import com.fasterxml.jackson.core.JsonProcessingException;  
import com.fasterxml.jackson.databind.JsonSerializer;  
import com.fasterxml.jackson.databind.ObjectMapper;  
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializerProvider;  
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
  
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
        
        this.setPropertyNamingStrategy(new MyNaming());
        this.configure(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        this.configure(Feature.WRITE_NUMBERS_AS_STRINGS, true);
        this.configure(Feature.QUOTE_NON_NUMERIC_NUMBERS, true);
        this.configure(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
    }
    
    class MyNaming extends PropertyNamingStrategy {
		private static final long serialVersionUID = -6130959716402255046L;

		@Override
        public String nameForGetterMethod(MapperConfig<?> config,
             AnnotatedMethod method, String defaultName)
        {
          return convertName(defaultName);
        }

		private String convertName(String defaultName) {
			StringBuilder bu = new StringBuilder();
			bu.append("\"").append(defaultName).append("\"");
			return bu.toString();
		}
     }
}  
