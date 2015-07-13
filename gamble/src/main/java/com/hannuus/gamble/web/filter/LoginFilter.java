package com.hannuus.gamble.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import com.hannuus.gamble.web.constant.SystemConstant;

public class LoginFilter implements Filter {

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain arg2) throws IOException, ServletException {
//		HttpServletRequest request = (HttpServletRequest)req;
//		if (request.getRequestURI().endsWith(".htm")) {
//			if (request.getRequestURI().contains("/admin/")) {
//				if (request.getRequestURI().endsWith("login.htm")) {
//					
//				} else {
//					Object res = request.getSession().getAttribute(SystemConstant.ADMIN_USER_KEY);
//					if (res == null) {
//						((HttpServletResponse)resp).sendRedirect(request.getContextPath() + "/admin/login.htm");
//						return;
//					}
//				}
//			} else if (request.getRequestURI().endsWith("login.htm")
//				|| request.getRequestURI().endsWith("404.htm") 
//				|| request.getRequestURI().endsWith("500.htm") 
//				) {
//				// to do something... 
//			} else {
//				Object res = request.getSession().getAttribute(SystemConstant.USER_KEY);
//				if (res == null) {
//					((HttpServletResponse)resp).sendRedirect(request.getContextPath() + "/login.htm");
//					return;
//				}
//			}
//		}
		arg2.doFilter(req, resp);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
