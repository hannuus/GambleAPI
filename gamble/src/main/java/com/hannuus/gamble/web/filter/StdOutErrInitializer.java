package com.hannuus.gamble.web.filter;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.hannuus.gamble.utils.GambleUtils;

public class StdOutErrInitializer implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		GambleUtils.StdOutErrRedirect.redirectSystemOutAndErrToLog();
	}

}