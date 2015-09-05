package com.hannuus.gamble.web.action;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hannuus.core.json.JsonResultStatus;
import com.hannuus.gamble.comm.JsonVo;
import com.hannuus.gamble.model.BlackList;
import com.hannuus.gamble.model.GlobalParams;
import com.hannuus.gamble.model.User;
import com.hannuus.gamble.web.service.CustomService;

/**
 * 用户设置Web API
 * 
 * @author cuesky
 * @date 2015年9月4日 下午3:33:02
 */
@Controller
@RequestMapping("/custom")
public class CustomAction extends BaseAction {

	@Autowired
	CustomService customService;

	// 帐号密码设置===================================================================

	// 黑名单管理=====================================================================

	@RequestMapping("/listBlackList.json")
	public JsonVo<List<User>> listBlackList(Long userId) {
		JsonVo<List<User>> json = new JsonVo<List<User>>();
		List<User> list = customService.findBlackList(userId);
		if (CollectionUtils.isEmpty(list)) {
			json.setStatus(JsonResultStatus.EmptyResult.getValue());
		} else {
			json.setTotal(list.size());
			json.setResult(list);
		}
		return json;
	}

	@RequestMapping("/addBlack.json")
	public JsonVo<Integer> addBlack(BlackList blackList) {
		JsonVo<Integer> json = new JsonVo<Integer>();
		int count = customService.addBlack(blackList);
		if (count != 1) {
			json.setStatus(JsonResultStatus.Failed.getValue());
		} else {
			json.setStatus(JsonResultStatus.Success.getValue());
		}
		return json;
	}

	@RequestMapping("/deleteBlack.json")
	public JsonVo<Integer> deleteBlack(Long id) {
		JsonVo<Integer> json = new JsonVo<Integer>();
		int count = customService.deleteBlack(id);
		if (count != 1) {
			json.setStatus(JsonResultStatus.Failed.getValue());
		} else {
			json.setStatus(JsonResultStatus.Success.getValue());
		}
		return json;
	}

	// 推送消息设置====================================================================

	@RequestMapping("/enablePush.json")
	public JsonVo<Integer> enablePush(String flag) {
		JsonVo<Integer> json = new JsonVo<Integer>();
		int count = customService.enablePush(flag);
		if (count != 1) {
			json.setStatus(JsonResultStatus.Failed.getValue());
		} else {
			json.setStatus(JsonResultStatus.Success.getValue());
		}
		return json;
	}

	// 无图模式=======================================================================

	@RequestMapping("/enableImage.json")
	public JsonVo<Integer> enableImage(String flag) {
		JsonVo<Integer> json = new JsonVo<Integer>();
		int count = customService.enableImage(flag);
		if (count != 1) {
			json.setStatus(JsonResultStatus.Failed.getValue());
		} else {
			json.setStatus(JsonResultStatus.Success.getValue());
		}
		return json;
	}

	// 客户端查询接口====================================================================

	@RequestMapping("/queryCustom.json")
	public JsonVo<List<GlobalParams>> queryCustomSettings() {
		// TODO cuesky
		return null;
	}

}
