package com.hannuus.gamble.web.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.hannuus.gamble.utils.GambleUtils;

/**
 * 
 */
@Component
public class GlobalInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if (null == modelAndView) {
			return;
		}
		// 系统配置参数
		String basePath = GambleUtils.Http.getBasePath(request);
		if (!modelAndView.getModelMap().containsKey("BASE_PATH")) {
			modelAndView.addObject("BASE_PATH", basePath);
			modelAndView.addObject("UPLOAD_BASE_PATH", basePath + "/upload");
			modelAndView.addObject("STATIC_BASE_PATH", basePath + "/static");
			modelAndView.addObject("ADMIN_STATIC_BASE_PATH", basePath
					+ "/adminsystem");
			modelAndView.addObject("ADMIN_BASE_PATH", basePath + "/admin");
		}
		MDC.put("ip", GambleUtils.Http.getIp(request));
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
