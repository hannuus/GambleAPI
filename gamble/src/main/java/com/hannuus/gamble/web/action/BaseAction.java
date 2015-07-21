package com.hannuus.gamble.web.action;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.hannuus.gamble.utils.HttpUtils;
import com.hannuus.gamble.web.exception.GambleException;


public class BaseAction {
	
	
	/**
	 *  validate the API path is invalid</br>
	 *  validate access token</br>
	 *  validate user permission</br>
	 *  validate sign</br>
	 *  validate timestamp, if server (timestamp - client timestamp) > {timeout-times}</br> 
	 *  like 10 minutes, then return the timeout response</br>
	 *  
	 * @param
	 * @param HttpServletRequest request
	 * @throws GambleException
	 */
	protected void validate(HttpServletRequest request) throws GambleException {
		// TODO validate the API is valid
		// TODO validate access token
		// TODO validate user permission
		// TODO validate sign
		// TODO validate timestamp, if server (timestamp - client timestamp) > {timeout-times, like 10 minutes} return the timeout error code
	}
	
	protected String getReqParam(String key) {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		return request.getParameter(key);
	}
	
	protected void setSession(String key, Object value) {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		request.getSession().setAttribute(key, value);
	}
	
	protected Object getSession(String key) {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		return request.getSession().getAttribute(key);
	}
	
	protected String getLocalDiskPath() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		return request.getSession().getServletContext().getRealPath("/") + File.separator;
	}
	
	protected String  getBasePath() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		return HttpUtils.getBasePath(request);
	}
	
	protected String getBasePathNotPort() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		return HttpUtils.getBasePathNotPort(request);
	}
}
