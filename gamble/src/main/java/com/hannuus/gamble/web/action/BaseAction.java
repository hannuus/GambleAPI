package com.hannuus.gamble.web.action;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.hannuus.gamble.utils.HttpUtils;
import com.hannuus.gamble.web.exception.GambleException;
import com.hannuus.gamble.web.exception.api.CanNotAccessException;
import com.hannuus.gamble.web.exception.api.InvalidAccessTokenException;
import com.hannuus.gamble.web.exception.api.InvalidRequestURLException;
import com.hannuus.gamble.web.exception.api.InvalidSignException;
import com.hannuus.gamble.web.exception.api.TimeoutCallingException;


public class BaseAction {
	
	private Logger logger = Logger.getLogger(getClass());
	
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
	protected void validateRequest(HttpServletRequest request) throws GambleException {
		if (requestPathIsInvalid(request)) {
			throw new InvalidRequestURLException(request.getRequestURL() + " is invalid");
		}
		if (accessTokenIsInvalid(request)) {
			throw new InvalidAccessTokenException();
		}
		if (userNotHavePermission(request)) {
			throw new CanNotAccessException();
		}
		if (isInvalidSign(request)) {
			throw new InvalidSignException();
		}
		if (isTimeoutCall(request)) {
			throw new TimeoutCallingException();
		}
	}
	/**
	 * validate timestamp, if server (timestamp - client timestamp) > {timeout-times, like 10 minutes} return the timeout error code
	 * @param request
	 * @return
	 */
	private boolean isTimeoutCall(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * validate sign
	 * @param request
	 * @return
	 */
	private boolean isInvalidSign(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * validate user permission
	 * @param request
	 * @return
	 */
	private boolean userNotHavePermission(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * validate the access token
	 * @param request
	 * @return
	 */
	private boolean accessTokenIsInvalid(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * validate the API is valid
	 * @param request
	 * @return
	 */
	private boolean requestPathIsInvalid(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * get a String request parameter
	 * @param key
	 * @return
	 */
	protected String getStringReqParam(String key) {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		return request.getParameter(key);
	}
	
	/**
	 * get a long parameter from request
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	protected Long getLongReqParam(String key, Long defaultValue) {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		try {
			return Long.valueOf(request.getParameter(key));
		} catch (Exception e) {
			logger.error("get " + key + " error: " + e);
			return defaultValue;
		}
	}
	
	/**
	 * get a integer parameter from request
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	protected Integer getIntegerReqParam(String key, Integer defaultValue) {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		try {
			return Integer.valueOf(request.getParameter(key));
		} catch (Exception e) {
			logger.error("get " + key + " error: " + e);
			return defaultValue;
		}
	}
	
	/**
	 * get a double parameter from request
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	protected Double getDoubleReqParam(String key, Double defaultValue) {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		try {
			return Double.valueOf(request.getParameter(key));
		} catch (Exception e) {
			logger.error("get " + key + " error: " + e);
			return defaultValue;
		}
	}
	
	/**
	 * set session
	 * @param key
	 * @param value
	 */
	protected void setSession(String key, Object value) {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		request.getSession().setAttribute(key, value);
	}
	/**
	 * get sessin
	 * @param key
	 * @return
	 */
	protected Object getSession(String key) {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		return request.getSession().getAttribute(key);
	}
	/**
	 * get local disk path
	 * @return
	 */
	protected String getLocalDiskPath() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		return request.getSession().getServletContext().getRealPath("/") + File.separator;
	}
	/**
	 * get base path
	 * @return
	 */
	protected String  getBasePath() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		return HttpUtils.getBasePath(request);
	}
	
	protected String getBasePathNotPort() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		return HttpUtils.getBasePathNotPort(request);
	}
	
	/**
	 * set allow origin domain request with response header properties
	 * @param response
	 */
	protected void setCrossOrigin(HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
		response.addHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With"); 
		response.addHeader("Access-Control-Max-Age", "30");
	}
}
