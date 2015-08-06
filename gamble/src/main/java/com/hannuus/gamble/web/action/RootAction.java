package com.hannuus.gamble.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hannuus.gamble.bean.AccessToken;
import com.hannuus.gamble.vo.JsonResultStatus;
import com.hannuus.gamble.vo.JsonVo;
import com.hannuus.gamble.web.exception.GambleException;
import com.hannuus.gamble.web.service.ILoginService;

@Controller
@RequestMapping("/")
public class RootAction extends BaseAction {
	
	@Autowired
	ILoginService loginService;
	
	@RequestMapping(value = "/index.htm", method = {RequestMethod.GET, RequestMethod.POST})
	public String index(ModelMap modelMap) {
		modelMap.put("title", "首页");
		return "/index";
	}
	
	@ResponseBody
	@RequestMapping(value = "/login", method = {RequestMethod.POST, RequestMethod.OPTIONS})
	public JsonVo<String> isLock(ModelMap modelMap, String userName, String password, HttpServletRequest request, HttpServletResponse response) {
		JsonVo<String> json = new JsonVo<String>();
		json.setStatus(JsonResultStatus.Failed.getValue());
		try {
			AccessToken accessToken = loginService.userLogin(userName, password);
			if (null != accessToken) {
				json.setResult(accessToken.toString());
				json.setStatus(JsonResultStatus.Success.getValue());
			}
		} catch (GambleException e) {
			logErrorMessages(json, e);
		} catch (Exception e) {
			logUnknowErrorMessages(json, e);
		}
		return json;
	}
}
