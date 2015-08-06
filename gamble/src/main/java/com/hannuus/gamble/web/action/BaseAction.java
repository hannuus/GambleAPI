package com.hannuus.gamble.web.action;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.hannuus.gamble.bean.User;
import com.hannuus.gamble.comm.GambleAPIErrorCode;
import com.hannuus.gamble.comm.R;
import com.hannuus.gamble.utils.HttpUtils;
import com.hannuus.gamble.vo.JsonResultStatus;
import com.hannuus.gamble.vo.JsonVo;
import com.hannuus.gamble.web.exception.GambleException;
import com.hannuus.gamble.web.exception.api.CanNotAccessException;
import com.hannuus.gamble.web.exception.api.InvalidAccessTokenException;
import com.hannuus.gamble.web.exception.api.InvalidRequestURLException;
import com.hannuus.gamble.web.service.ILoginService;


public class BaseAction {
	
	private Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	ILoginService accessTokenService;
	
	protected Long getLoginUserId() throws Exception {
		String accessToken = getStringReqParam(R.request.access_token);
		User user = accessTokenService.getUserByAccessToken(accessToken);
		return null == user? null : user.getId();
	}

	
	/**
	 * log error messages
	 * @param json
	 * @param e
	 */
	protected void logErrorMessages(JsonVo<?> json, GambleException e) {
		logger.error(e);
		json.setErrcode(e.getCode());
		json.setErrmsg(e.getReasoning());
		json.setStatus(JsonResultStatus.Failed.getValue());
	}
	
	/**
	 * 
	 * @param json
	 * @param e
	 */
	protected void logUnknowErrorMessages(JsonVo<?> json, Exception e) {
		logger.error(e);
		json.setErrcode(GambleAPIErrorCode.UnKnowException.getCode());
		json.setErrmsg(e.getMessage());
		json.setStatus(JsonResultStatus.Failed.getValue());
	}
	
	/**
	 *  validate access token</br>
	 *  validate user permission</br>
	 *  
	 * @param
	 * @param HttpServletRequest request
	 * @throws GambleException
	 */
	protected void validateRequest(HttpServletRequest request) throws GambleException {
		if (accessTokenInvalid(request)) {
			throw new InvalidAccessTokenException();
		}
		if (permissionNotDefined(request)) {
			throw new CanNotAccessException();
		}
		if (requestPathInvalid(request)) {
			throw new InvalidRequestURLException();
		}
	}
	/**
	 * 验证用户是否有权限访问此API
	 * @param request
	 * @return
	 */
	private boolean permissionNotDefined(HttpServletRequest request) {
		// TODO cuesky
		return false;
	}
	/**
	 * 验证client的accessToken是否有效
	 * @param request
	 * @return
	 */
	private boolean accessTokenInvalid(HttpServletRequest request) {
		// TODO cuesky
		return false;
	}
	/**
	 * 验证请求的路径是否有效, 
	 * 比如请求的路径可能已经无效, 或者是后台设置了暂时禁止访问, 
	 * 目前默认返回true, 后期可能实现
	 * 
	 * FIXME
	 * 
	 * @param request
	 * @return
	 */
	private boolean requestPathInvalid(HttpServletRequest request) {
		return true;
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
