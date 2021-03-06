package com.hannuus.gamble.web.action;

import java.io.File;
import java.text.MessageFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.hannuus.gamble.comm.JsonVo;
import com.hannuus.gamble.comm.R;
import com.hannuus.gamble.model.User;
import com.hannuus.gamble.model.UserToken;
import com.hannuus.gamble.utils.GambleUtils;
import com.hannuus.gamble.web.exception.GambleException;
import com.hannuus.gamble.web.exception.MappingJacksonJsonViewExd;
import com.hannuus.gamble.web.exception.api.InvalidAccessTokenException;
import com.hannuus.gamble.web.exception.api.InvalidRequestURLException;
import com.hannuus.gamble.web.service.LoginService;
import com.hannuus.gamble.web.service.PermissionService;
import com.hannuus.pagination.PageDTO;
import com.hannuus.pagination.PageQueryCallback;
import com.hannuus.pagination.PageQueryTemplate;
import com.hannuus.pagination.PageWrapper;
import com.hannuus.validation.ValidateEngine;
import com.hannuus.validation.ValidateParams;
import com.hannuus.validation.ValidateResult;

/**
 * Action基类
 * 
 * @author aelns
 * @author cuesky
 * @date 2015年8月27日 下午8:56:45
 */
@SuppressWarnings("deprecation")
public class BaseAction {

	private Logger logger = Logger.getLogger(getClass());

	@Autowired
	LoginService loginService;

	@Autowired
	PageQueryTemplate pageQueryTemplate;

	@Autowired
	PermissionService permissionService;

	@Autowired
	ValidateEngine validateEngine;

	private MappingJacksonJsonViewExd jsonView = new MappingJacksonJsonViewExd();

	@ExceptionHandler(Exception.class)
	public ModelAndView handleAnyException(Exception ex) {
		if (ex instanceof UnauthorizedException) {
			return new ModelAndView("/unauthorized");
		}
		return new ModelAndView(jsonView, "error", new JsonVo<String>(ex));
	}

	protected Long getLoginUserId() {
		String accessToken = getStringReqParam(R.request.access_token);
		User user = loginService.getUserByAccessToken(accessToken);
		return null == user ? null : user.getId();
	}

	/**
	 * validate access token<br>
	 * validate user permission<br>
	 * 
	 * @param
	 * @param HttpServletRequest
	 *            request
	 * @throws GambleException
	 */
	protected void validateRequest(HttpServletRequest request)
			throws GambleException {
		if (!isAccessTokenValid(request)) {
			throw new InvalidAccessTokenException();
		}
		// 因采用shiro，故废弃
		// if (!isPermissionDefined(request)) {
		// throw new CanNotAccessException();
		// }
		if (!isRequestPathValid(request)) {
			throw new InvalidRequestURLException();
		}
	}

	// /**
	// * 验证用户是否有权限访问此API
	// *
	// * @param request
	// * @return
	// */
	// private boolean isPermissionDefined(HttpServletRequest request) {
	// // boolean flag = false;
	// Long loginUserId = getLoginUserId();
	// if (loginUserId == null) {
	// return false;
	// }
	// // String permissionPath = getPermissionPath(request);
	// // flag = permissionService.isPermissionDefined(permissionPath,
	// // loginUserId);
	// return true;
	// }

	// /**
	// * @param request
	// * @return 获取到方法级别的路径。例如:"/user/add"
	// */
	// private String getPermissionPath(HttpServletRequest request) {
	// String permissionPath = request.getRequestURI();
	// return permissionPath;
	// }

	/**
	 * 验证client提交的accessToken是否有效
	 * 
	 * @param request
	 * @return
	 */
	private boolean isAccessTokenValid(HttpServletRequest request) {
		String token = getStringReqParam(R.request.access_token);
		// if (StringUtils.isEmpty(token)) {
		// return false;
		// }
		String savedToken = getStringInSession(R.session.access_token, null);
		if (StringUtils.isEmpty(savedToken)) {
			return false;
		}
		return token.equals(savedToken);
	}

	/**
	 * 验证请求的路径是否有效, 比如请求的路径可能已经无效, 或者是后台设置了暂时禁止访问, 目前默认返回true, 后期可能实现
	 * 
	 * FIXME
	 * 
	 * @param request
	 * @return
	 */
	private boolean isRequestPathValid(HttpServletRequest request) {
		return true;
	}

	/**
	 * get a String request parameter
	 * 
	 * @param key
	 * @return
	 */
	protected String getStringReqParam(String key) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		return request.getParameter(key);
	}

	/**
	 * get a long parameter from request
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	protected Long getLongReqParam(String key, Long defaultValue) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		try {
			return Long.valueOf(request.getParameter(key));
		} catch (Exception e) {
			logger.warn(MessageFormat
					.format("get {0} error, use default value: {1}, exception details: {2}",
							key, defaultValue, e));
			return defaultValue;
		}
	}

	/**
	 * get a integer parameter from request
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	protected Integer getIntegerReqParam(String key, Integer defaultValue) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		try {
			return Integer.valueOf(request.getParameter(key));
		} catch (Exception e) {
			logger.warn(MessageFormat
					.format("get {0} error, use default value: {1}, exception details: {2}",
							key, defaultValue, e));
			return defaultValue;
		}
	}

	/**
	 * get a double parameter from request
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	protected Double getDoubleReqParam(String key, Double defaultValue) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		try {
			return Double.valueOf(request.getParameter(key));
		} catch (Exception e) {
			logger.warn(MessageFormat
					.format("get {0} error, use default value: {1}, exception details: {2}",
							key, defaultValue, e));
			return defaultValue;
		}
	}

	/**
	 * Set an attribute in session.
	 * 
	 * @param key
	 * @param value
	 */
	protected void setSessionAttribute(String key, Object value) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		request.getSession().setAttribute(key, value);
	}

	/**
	 * Get an <code>Object</code> attribute from session
	 * 
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected <T> T getSessionAttribute(String key) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		return (T) request.getSession().getAttribute(key);
	}

	/**
	 * 获取当前用户ID
	 * 
	 * @return
	 */
	protected Long getCurrentUserId() {
		UserToken userToken = getSessionAttribute(R.session.user);
		return userToken.getId();
	}

	/**
	 * Get a <code>String</code> attribute from session.
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	protected String getStringInSession(String key, String defaultValue) {
		HttpSession session = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest().getSession();
		try {
			return (String) session.getAttribute(key);
		} catch (Exception e) {
			logger.warn(MessageFormat
					.format("get {0} error, use default value: {1}, exception details: {2}",
							key, defaultValue, e));
			return defaultValue;
		}
	}

	/**
	 * get local disk path
	 * 
	 * @return
	 */
	protected String getLocalDiskPath() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		return request.getSession().getServletContext().getRealPath("/")
				+ File.separator;
	}

	/**
	 * get base path
	 * 
	 * @return
	 */
	protected String getBasePath() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		return GambleUtils.Http.getBasePath(request);
	}

	protected String getBasePathNotPort() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		return GambleUtils.Http.getBasePathNotPort(request);
	}

	/**
	 * set allow origin domain request with response header properties
	 * 
	 * @param response
	 */
	protected void setCrossOrigin(HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
		response.addHeader(
				"Access-Control-Allow-Headers",
				"Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With");
		response.addHeader("Access-Control-Max-Age", "30");
	}

	/**
	 * 分页查询
	 * 
	 * @param pageNum
	 *            页码
	 * @param pageSize
	 *            页面大小
	 * @param map
	 *            域模型
	 * @param callback
	 *            分页回调
	 * @return
	 */
	protected <T> PageDTO<T> pageQuery(int pageNum, int pageSize, ModelMap map,
			PageQueryCallback<T> callback) {
		PageDTO<T> page = pageQueryTemplate.execute(pageNum, pageSize, map,
				callback);
		return page;
	}

	/**
	 * 分页查询
	 * 
	 * @param pageNum
	 *            页码
	 * @param pageSize
	 *            页面大小
	 * @param map
	 *            域模型
	 * @param callback
	 *            分页回调
	 * @param pageWrapper
	 *            分页包装器
	 */
	protected <T> void pageQuery(int pageNum, int pageSize, ModelMap map,
			PageQueryCallback<T> callback, PageWrapper pageWrapper) {
		pageQueryTemplate
				.execute(pageNum, pageSize, map, callback, pageWrapper);
	}

	/**
	 * 进行业务规则校验
	 * 
	 * @param params
	 *            校验参数
	 * @return
	 */
	public ValidateResult validate(ValidateParams params) {
		return validateEngine.executeValidate(params);
	}

}
