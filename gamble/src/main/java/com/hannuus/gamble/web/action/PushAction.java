package com.hannuus.gamble.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baidu.yun.push.exception.PushClientException;
import com.baidu.yun.push.exception.PushServerException;
import com.hannuus.core.json.JsonResultStatus;
import com.hannuus.core.json.JsonVo;
import com.hannuus.gamble.comm.GambleAPIErrorCode;
import com.hannuus.gamble.domain.PushMessage;
import com.hannuus.gamble.web.service.PushService;

/**
 * @author cuesky
 * @date 2015年8月15日 下午8:47:48
 * 
 * @see com.hannuus.gamble.domain.PushMessage
 */
@Controller
@RequestMapping("/push")
public class PushAction extends BaseAction {
	
	private Logger logger = Logger.getLogger(PushAction.class);
	
	@Autowired
	PushService pushService;

	// TODO cuesky
	// 根据fromId和toId构造title或者description或者customContent以简化PushMessage封装

	/**
	 * 评论
	 * 
	 * @param modelMap
	 * @param message
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/comment.json", method = { RequestMethod.POST })
	public JsonVo<String> comment(ModelMap modelMap, PushMessage message,
			Long fromId, Long toId, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO cuesky
		return null;
	}

	/**
	 * 站内私信
	 * 
	 * @param modelMap
	 * @param message
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/personal.json", method = { RequestMethod.POST })
	public JsonVo<String> personal(ModelMap modelMap, PushMessage message,
			Long fromId, Long toId, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO cuesky
		JsonVo<String> json = new JsonVo<String>();
		try {
			pushService.pushMsgToAll(message);
		} catch (PushClientException e) {
			logger.error(e);
			json.setErrcode(GambleAPIErrorCode.PushClient.getCode());
			json.setErrmsg(GambleAPIErrorCode.PushClient.getReasoning() + " 详细:" + e.getMessage());
		} catch (PushServerException e) {
			logger.error(e);
			json.setErrcode(GambleAPIErrorCode.PushServer.getCode());
			json.setErrmsg(GambleAPIErrorCode.PushServer.getReasoning() + " 详细:" + e.getMessage());
		}
		return json;
	}

	/**
	 * 关注
	 * 
	 * @param modelMap
	 * @param message
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/concern.json", method = { RequestMethod.POST })
	public JsonVo<String> concern(ModelMap modelMap, PushMessage message,
			Long fromId, Long toId, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO cuesky
		return null;
	}

	/**
	 * 广告
	 * 
	 * @param modelMap
	 * @param message
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/advertising.json", method = { RequestMethod.POST })
	public JsonVo<String> advertising(ModelMap modelMap, PushMessage message,
			HttpServletRequest request, HttpServletResponse response) {
		JsonVo<String> json = new JsonVo<String>();
		try {
			pushService.pushMsgToAll(message);
			json.setStatus(JsonResultStatus.Success.getValue());
		} catch (Exception e) {
			logUnknowErrorMessages(json, e);
		}
		return json;
	}

	/**
	 * 推荐
	 * 
	 * @param modelMap
	 * @param message
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/recommend.json", method = { RequestMethod.POST })
	public JsonVo<String> recommend(ModelMap modelMap, PushMessage message,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO aelns 根据用户关注topic和搜索关键字统计信息，定时推送
		return null;
	}

	/**
	 * 系统私信
	 * 
	 * @param modelMap
	 * @param message
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/system.json", method = { RequestMethod.POST })
	public JsonVo<String> system(ModelMap modelMap, PushMessage message,
			Long userId, HttpServletRequest request,
			HttpServletResponse response) {
		JsonVo<String> json = new JsonVo<String>();
		try {
			pushService.pushMsgToSingleUser(message, userId);
			json.setStatus(JsonResultStatus.Success.getValue());
		} catch (Exception e) {
			logUnknowErrorMessages(json, e);
		}
		return json;
	}

}
