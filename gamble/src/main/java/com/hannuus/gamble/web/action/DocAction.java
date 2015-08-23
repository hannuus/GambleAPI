package com.hannuus.gamble.web.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;
import com.hannuus.gamble.comm.GambleAPIErrorCode;
import com.hannuus.core.json.JsonVo;

@Controller
@RequestMapping("/docs")
public class DocAction extends BaseAction {
	
	@ResponseBody
	@RequestMapping(value = "/errorCodes.json", method = {RequestMethod.GET, RequestMethod.POST})
	public JsonVo<Map<String, String>> errorCodes(HttpServletRequest request, HttpServletResponse response) {
		JsonVo<Map<String, String>> json = new JsonVo<Map<String, String>>();
		Map<String, String> map = Maps.newHashMap();
		for (GambleAPIErrorCode errorCode : GambleAPIErrorCode.values()) {
			map.put(errorCode.getCode(), errorCode.getReasoning());
		}
		json.setResult(map);
		return json;
	}
}
