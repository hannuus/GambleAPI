package com.hannuus.gamble.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hannuus.core.json.JsonResultStatus;
import com.hannuus.core.json.JsonVo;
import com.hannuus.gamble.domain.AccessToken;
import com.hannuus.gamble.web.service.LoginService;

@Controller
@RequestMapping("/")
public class RootAction extends BaseAction {

	@Autowired
	LoginService loginService;

	@RequestMapping(value = "/index.htm", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String index(ModelMap modelMap) {
		modelMap.put("title", "首页");
		return "/index";
	}

	@RequestMapping(value = "/unauthorized.htm", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String unauthorized(ModelMap modelMap) {
		modelMap.put("title", "未授权");
		return "/unauthorized";
	}

	@ResponseBody
	@RequestMapping(value = "/login.json", method = { RequestMethod.POST })
	public JsonVo<String> isLock(ModelMap modelMap, String userName,
			String password, HttpServletRequest request,
			HttpServletResponse response) {
		JsonVo<String> json = new JsonVo<String>();
		json.setStatus(JsonResultStatus.Failed.getValue());
		AccessToken accessToken = loginService.userLogin(userName, password);
		if (null != accessToken) {
			json.setResult(accessToken.toString());
		}
		return json;
	}
}
