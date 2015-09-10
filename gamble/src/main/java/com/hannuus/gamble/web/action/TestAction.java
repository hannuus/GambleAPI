package com.hannuus.gamble.web.action;

import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hannuus.gamble.model.UserToken;
import com.hannuus.gamble.web.service.CustomService;
import com.hannuus.gamble.web.validate.ValidateParams;
import com.hannuus.gamble.web.validate.ValidateResult;

@Controller
@RequestMapping("/test")
public class TestAction extends BaseAction {

	private Logger logger = Logger.getLogger(getClass());

	@Autowired
	CustomService customService;

	@RequestMapping("/login")
	public String login(UserToken userToken) {
		logger.debug("login...");
		// UsernamePasswordToken token = new UsernamePasswordToken(userName,
		// password);
		// SecurityUtils.getSubject().login(token);
		UserToken token = customService.checkLogin(userToken);
		if (token == null) {
			throw new AuthenticationException();
		}
		logger.debug("login done...");
		return "/success";
	}

	@RequestMapping("/logout")
	public String logout(String userName, String password) {
		logger.debug("logout in...");
		// SecurityUtils.getSubject().logout();
		customService.logout();
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

	@RequiresUser
	@RequestMapping("/validate")
	public String testValidate() {
		logger.debug("testValidate in...");
		ValidateParams params = new ValidateParams();
		params.setRuleId("login");
		ValidateResult result = validate(params);
		if (result.getCode() == ValidateResult.FAILURE) {
			// handle the result
			return "/index";
		}
		logger.debug("testValidate out...");
		return "/success";
	}

}
