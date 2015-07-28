package com.hannuus.gamble.web.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;
import com.hannuus.gamble.comm.GambleAPIErrorCode;
import com.hannuus.gamble.vo.JsonResultStatus;
import com.hannuus.gamble.vo.JsonVo;
import com.hannuus.gamble.web.exception.GambleException;

@Controller
@RequestMapping("/docs")
public class DocAction extends BaseAction {
	
	private static final Logger logger = Logger.getLogger(TopicAction.class);
	
	@ResponseBody
	@RequestMapping(value = "/errorCodes", method = {RequestMethod.GET, RequestMethod.POST})
	public JsonVo<Map<String, String>> listByCategory(HttpServletRequest request, HttpServletResponse response) {
		JsonVo<Map<String, String>> json = new JsonVo<Map<String, String>>();
		try {
			Map<String, String> map = Maps.newHashMap();
			for (GambleAPIErrorCode errorCode : GambleAPIErrorCode.values()) {
				map.put(errorCode.getCode(), errorCode.getReasoning());
			}
			json.setResult(map);
			json.setStatus(JsonResultStatus.Success.getValue());
		} catch (GambleException e) {
			logger.error(e);
			json.setErrcode(e.getCode());
			json.setErrmsg(e.getMessage());
			json.setStatus(JsonResultStatus.Failed.getValue());
		}
		return json;
	}
}
