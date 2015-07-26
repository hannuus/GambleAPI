package com.hannuus.gamble.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * 
 * Ftl filter, for security
 * 
 */
public class FtlFilter implements Filter {

	protected final Logger logger = Logger.getLogger(this.getClass());

	public void init(FilterConfig filterConfig) throws ServletException {

	}

	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		response.sendRedirect(com.hannuus.gamble.utils.HttpUtils.getBasePath(request) + "/404.htm");
	}

	public void destroy() {

	}

}
