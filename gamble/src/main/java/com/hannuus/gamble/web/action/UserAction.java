package com.hannuus.gamble.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hannuus.gamble.bean.Topic;
import com.hannuus.gamble.bean.User;
import com.hannuus.gamble.comm.SystemConstants;
import com.hannuus.gamble.vo.JsonResultStatus;
import com.hannuus.gamble.vo.JsonVo;
import com.hannuus.gamble.web.exception.GambleException;
import com.hannuus.gamble.web.service.IUserService;

/**
 * 
 * @author aelns
 * 
 * TODO need more test
 *
 */
@Controller
@RequestMapping("/user")
public class UserAction extends BaseAction {
	
	@Autowired
	IUserService userService;
	
	/**
	 * 新增用户
	 * @param modelMap
	 * @param user
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/add", method = {RequestMethod.POST, RequestMethod.OPTIONS})
	public JsonVo<List<User>> create(ModelMap modelMap, User user, HttpServletRequest request, HttpServletResponse response) {
		JsonVo<List<User>> json = new JsonVo<List<User>>();
		setCrossOrigin(response);
		try {
			validateRequest(request);
			initUser(user);
			validateAddUserArguments(user);
			if(userService.addUser(user)) {
				json.setStatus(JsonResultStatus.Success.getValue());
			} else {
				json.setStatus(JsonResultStatus.Failed.getValue());
			}
		} catch (GambleException e) {
			logErrorMessages(json, e);
		} catch (Exception e) {
			logUnknowErrorMessages(json, e);
		}
		return json;
	}
	
	private void validateAddUserArguments(User user) throws GambleException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 删除一个用户	
	 * @param modelMap
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/delete", method = {RequestMethod.POST, RequestMethod.OPTIONS})
	public JsonVo<List<User>> delete(ModelMap modelMap, Long id, HttpServletRequest request, HttpServletResponse response) {
		JsonVo<List<User>> json = new JsonVo<List<User>>();
		setCrossOrigin(response);
		try {
			validateRequest(request);
			validateDelete(request, id);
			if(userService.deleteUser(id)) {
				json.setStatus(JsonResultStatus.Success.getValue());
			} else {
				json.setStatus(JsonResultStatus.Failed.getValue());
			}
		} catch (GambleException e) {
			logErrorMessages(json, e);
		} catch (Exception e) {
			logUnknowErrorMessages(json, e);
		}
		return json;
	}
	
	private void validateDelete(HttpServletRequest request, Long id) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * 查询用户详情
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/detail.json", method = {RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.POST})
	public JsonVo<User> detail(HttpServletRequest request, HttpServletResponse response) {
		JsonVo<User> json = new JsonVo<User>();
		setCrossOrigin(response);
		try {
			validateRequest(request);
			Long userId = getLongReqParam("id", 0L);
			json.setTotal(0);
			User user = userService.findUserById(userId);
			if (null != user) {
				json.setTotal(1);
				json.setResult(user);
				json.setStatus(JsonResultStatus.Success.getValue());
			} else {
				json.setStatus(JsonResultStatus.EmptyResult.getValue());
			}
		} catch (GambleException e) {
			logErrorMessages(json, e);
		} catch (Exception e) {
			logUnknowErrorMessages(json, e);
		}
		return json;
	}
	
	/**
	 * 分页查询用户
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/listUsers.json", method = {RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.POST})
	public JsonVo<List<User>> listByCategory(User user, HttpServletRequest request, HttpServletResponse response) {
		JsonVo<List<User>> json = new JsonVo<List<User>>();
		setCrossOrigin(response);
		try {
			validateRequest(request);
			int pageNumber = getIntegerReqParam("pageNumber", 1);
			int pageSize = getIntegerReqParam("pageSize", SystemConstants.DEFAULT_PAGE_SIZE);
			int total = userService.countUsers();
			json.setTotal(total);
			List<User> list = userService.findUserByPageWithConditions(user, pageNumber, pageSize);
			json.setResult(list);
			json.setStatus(JsonResultStatus.Success.getValue());
			if (CollectionUtils.isEmpty(list)) {
				json.setStatus(JsonResultStatus.EmptyResult.getValue());
			}
		} catch (GambleException e) {
			logErrorMessages(json, e);
		} catch (Exception e) {
			logUnknowErrorMessages(json, e);
		}
		return json;
	}
	
	/**
	 * 更新用户昵称
	 * @param modelMap
	 * @param id
	 * @param nickName
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updateNickName", method = {RequestMethod.POST, RequestMethod.OPTIONS})
	public JsonVo<List<Topic>> updateNickName(ModelMap modelMap, Long id, String nickName, HttpServletRequest request, HttpServletResponse response) {
		JsonVo<List<Topic>> json = new JsonVo<List<Topic>>();
		setCrossOrigin(response);
		try {
			validateRequest(request);
			validateUpdateNickNameArguments(id, nickName);
			User user = new User();
			user.setId(id);
			user.setNickName(nickName);
			if(userService.updateNickName(id, nickName)) {
				json.setStatus(JsonResultStatus.Success.getValue());
			} else {
				json.setStatus(JsonResultStatus.Failed.getValue());
			}
		} catch (GambleException e) {
			logErrorMessages(json, e);
		} catch (Exception e) {
			logUnknowErrorMessages(json, e);
		}
		return json;
	}
	
	private void validateUpdateNickNameArguments(Long id, String nickName) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 更新用户头像, 在调用此方法之前, client应当调用上传图片的接口获得一个imageURL	
	 * @param modelMap
	 * @param id
	 * @param imageURL
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updateHeadImage", method = {RequestMethod.POST, RequestMethod.OPTIONS})
	public JsonVo<List<Topic>> updateHeadImage(ModelMap modelMap, Long id, String imageURL, HttpServletRequest request, HttpServletResponse response) {
		JsonVo<List<Topic>> json = new JsonVo<List<Topic>>();
		setCrossOrigin(response);
		try {
			validateRequest(request);
			validateUpdateHeadImageArguments(id, imageURL);
			User user = new User();
			user.setId(id);
			user.setHeadUrl(imageURL);
			if(userService.updateHeadURL(id, imageURL)) {
				json.setStatus(JsonResultStatus.Success.getValue());
			} else {
				json.setStatus(JsonResultStatus.Failed.getValue());
			}
		} catch (GambleException e) {
			logErrorMessages(json, e);
		} catch (Exception e) {
			logUnknowErrorMessages(json, e);
		}
		return json;
	}
	private void validateUpdateHeadImageArguments(Long id, String headImage) throws GambleException {
		if (!headImageUploaed(headImage)) {
			
		}
	}
	
	/**
	 * 判断用户头像是否已经上传至服务器
	 * @param headImage
	 * @return
	 */
    private boolean headImageUploaed(String headImage) {
		// TODO Auto-generated method stub
		return false;
	}

	//	更新个性签名
//	绑定邮箱
//	绑定手机
//	更新用户积分
//	锁定用户
//	查询用户是否被锁定
//	查找好友列表, 彼此互相关注的人
//	查找关注列表, 我关注的人
//	查找粉丝, 关注我的人
//	添加用户关系
//	删除用户关系
//	添加关注
//	批量添加关注
//	取消关注
//	批量取消关注
	private void initUser(User user) {
		// TODO Auto-generated method stub
		
	}

}
