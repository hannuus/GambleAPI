package com.hannuus.gamble.web.task;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service("testFlushTask")
public class TestFlushTask {
	private Logger logger = LogManager.getLogger(TestFlushTask.class); 
	
	public void flush() {
		logger.info("Test flush.");
	}
}
