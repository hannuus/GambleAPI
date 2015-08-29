package com.hannuus.gamble.web.filter;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author cuesky
 * @date 2015年8月29日 下午6:05:40
 */
@ControllerAdvice
public class DefaultExceptionHandler {

	// TODO aelns
	// 结合MappingJacksonJsonViewExd让这个处理类生效

	@ExceptionHandler(UnauthorizedException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public ModelAndView processUnauthorizedException(UnauthorizedException e) {
		ModelAndView mv = new ModelAndView("/unauthorized", "exception", e);
		return mv;
	}
}
