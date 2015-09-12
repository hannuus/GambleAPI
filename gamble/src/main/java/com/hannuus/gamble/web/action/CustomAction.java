package com.hannuus.gamble.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.hannuus.core.json.JsonResultStatus;
import com.hannuus.gamble.comm.JsonVo;
import com.hannuus.gamble.comm.R;
import com.hannuus.gamble.model.BlackList;
import com.hannuus.gamble.model.User;
import com.hannuus.gamble.model.UserToken;
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

	// 登录、注册与注销================================================================

	/**
	 * 根据userName和email查询用户是否存在
	 * 
	 * @param userToken
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/isUserTokenExists.json")
	public JsonVo<Integer> isUserTokenExists(UserToken userToken) {
		JsonVo<Integer> json = new JsonVo<Integer>();
		userToken = customService.findUserTokenByUserNameOrEmail(userToken);
		if (userToken == null) {
			json.setResult(0);// 不存在
		} else {
			json.setResult(1);// 存在
		}
		return json;
	}

	/**
	 * 注册新用户
	 * 
	 * @param userToken
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/regist.json")
	public JsonVo<UserToken> regist(UserToken userToken) {
		JsonVo<UserToken> json = new JsonVo<UserToken>();
		userToken = customService.regist(userToken);
		json.setResult(userToken);
		return json;
	}

	/**
	 * 用户登录
	 * 
	 * @param userToken
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/login.json")
	public JsonVo<UserToken> login(UserToken userToken) {
		JsonVo<UserToken> json = new JsonVo<UserToken>();
		userToken = customService.checkLogin(userToken);
		if (userToken == null) {
			json.setStatus(JsonResultStatus.Failed.getValue());
		} else {
			// 将userToken存入session
			setSessionAttribute(R.session.user, userToken);
			json.setStatus(JsonResultStatus.Success.getValue());
			json.setResult(userToken);
		}
		return json;
	}

	/**
	 * 用户注销
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/logout.json")
	public JsonVo<Integer> logout() {
		JsonVo<Integer> json = new JsonVo<Integer>();
		customService.logout();
		// TODO cuesky 移除session
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		request.getSession().invalidate();
		json.setStatus(JsonResultStatus.Success.getValue());
		return json;
	}

	// 帐号密码设置===================================================================

	/**
	 * 使用Email发送校验回执号码<br>
	 * 使用Email回执方式还是本地密码(login时已回传至客户端)校验方式进行信息修改，由客户端根据是否设置过密码决定<br>
	 * 注：修改Email必须通过Email回执方式校验
	 * 
	 * @param userToken
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/sendReceipt.json")
	public JsonVo<UserToken> sendReceipt(UserToken userToken) {
		JsonVo<UserToken> json = new JsonVo<UserToken>();
		// 生成回执号码，通过userToken回传
		customService.sendReceipt(userToken);
		json.setResult(userToken);
		return json;
	}

	/**
	 * 修改email、手机号码、密码
	 * 
	 * @param userToken
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/modifyUserToken.json")
	public JsonVo<UserToken> modifyUserToken(UserToken userToken) {
		JsonVo<UserToken> json = new JsonVo<UserToken>();
		userToken = customService.modifyUserToken(userToken);
		json.setResult(userToken);
		return json;
	}

	/**
	 * 绑定账户(webchat/QQ/sina)
	 * 
	 * @param userToken
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/bandAccount.json")
	public JsonVo<UserToken> bandAccount(UserToken userToken) {
		JsonVo<UserToken> json = new JsonVo<UserToken>();
		userToken = customService.bandAccount(userToken);
		json.setResult(userToken);
		return json;
	}

	// 黑名单管理=====================================================================

	/**
	 * 根据userId查询该用户所有黑名单
	 * 
	 * @param userId
	 * @return
	 */
	@ResponseBody
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

	/**
	 * 添加一条黑名单记录
	 * 
	 * @param blackList
	 * @return
	 */
	@ResponseBody
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

	/**
	 * 根据id删除一条黑名单记录
	 * 
	 * @param id
	 * @return
	 */
	@ResponseBody
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

	/**
	 * client设置是否接受消息推送
	 * 
	 * @param flag
	 * @return
	 */
	@ResponseBody
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

	/**
	 * client设置是否接受图片
	 * 
	 * @param flag
	 * @return
	 */
	@ResponseBody
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

	// @RequestMapping("/queryCustom.json")
	// public JsonVo<List<GlobalParams>> queryCustomSettings() {
	// return null;
	// }

}
