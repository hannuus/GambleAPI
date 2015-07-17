package com.hannuus.gamble.web.action;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class RootAction {
	
	@RequestMapping(value = "/index.htm", method = {RequestMethod.GET, RequestMethod.POST})
	public String index(ModelMap modelMap) {
		modelMap.put("title", "首页");
		return "/index";
	}
}
