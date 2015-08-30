package com.hannuus.gamble.web.action;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestAction extends BaseAction {

	private Logger logger = Logger.getLogger(getClass());

	@RequestMapping("/login")
	public String login(String userName, String password) {
		logger.debug("login...");
		UsernamePasswordToken token = new UsernamePasswordToken(userName,
				password);
		SecurityUtils.getSubject().login(token);
		logger.debug("login done...");
		return "/success";
	}

	@RequestMapping("/logout")
	public String logout(String userName, String password) {
		logger.debug("logout in...");
		SecurityUtils.getSubject().logout();
		logger.debug("logout done...");
		return "/index";
	}

	@RequiresUser
	@RequestMapping("/success")
	public String success() {
		logger.debug("success in...");
		logger.debug("success out...");
		return "/success";
	}

	@RequiresRoles(value = { "admin" })
	@RequestMapping("/adminTest")
	public String onlyAdminCanExecute(String userName, String password) {
		logger.debug("onlyAdminCanExecute...");
		return "/success";
	}

	@RequiresPermissions(value = { "topic:create,delete" })
	@RequestMapping("/operTopic")
	public String operTopic() {
		logger.debug("operTopic...");
		return "/success";
	}

}
