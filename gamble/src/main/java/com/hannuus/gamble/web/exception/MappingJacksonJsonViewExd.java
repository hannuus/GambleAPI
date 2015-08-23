package com.hannuus.gamble.web.exception;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;
 
public class MappingJacksonJsonViewExd extends MappingJacksonJsonView {
	
	private Logger logger = Logger.getLogger(MappingJacksonJsonViewExd.class);
 
    @Override
    protected Object filterModel(Map<String, Object> model) {
        Map<?, ?> result = (Map<?, ?>) super.filterModel(model);
        Object obj = result.values().iterator().next();
        if (logger.isDebugEnabled()) {
        	logger.debug(obj);
        }
        return obj;
    }
}