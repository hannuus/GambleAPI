package com.hannuus.gamble.web.action;

import java.util.List;

import javax.management.openmbean.InvalidOpenTypeException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.google.common.collect.Lists;
import com.hannuus.gamble.comm.SystemConstants;
import com.hannuus.gamble.comm.UserRelationType;
import com.hannuus.core.json.JsonResultStatus;
import com.hannuus.core.json.JsonVo;
import com.hannuus.gamble.web.exception.GambleException;
import com.hannuus.gamble.web.exception.api.ArgumentsIncorrectException;
import com.hannuus.gamble.web.service.UserRelationService;
import com.hannuus.model.gamble.User;
import com.hannuus.model.gamble.UserRelation;

@Controller
@RequestMapping("relation")
public class RelationAction extends BaseAction {
	
	
	@Autowired
	UserRelationService userRelationService;

    /**
     * 查找好友列表, 彼此互相关注的人
     * @param modelMap
     * @param id
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
	@RequestMapping(value = "/friends.json", method = {RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.POST})
	public JsonVo<List<User>> friends(ModelMap modelMap, Long id, HttpServletRequest request, HttpServletResponse response) {
		JsonVo<List<User>> json = new JsonVo<List<User>>();
		try {
			int pageNumber = getIntegerReqParam("pageNumber", 1);
			int pageSize = getIntegerReqParam("pageSize", SystemConstants.DEFAULT_PAGE_SIZE);
			int total = userRelationService.countFriends(id);
			json.setTotal(total);
			List<User> list = userRelationService.findFriendsListByPage(id, pageNumber, pageSize);
			json.setResult(list);
			json.setStatus(JsonResultStatus.Success.getValue());
			if (CollectionUtils.isEmpty(list)) {
				json.setStatus(JsonResultStatus.EmptyResult.getValue());
			}
		} catch (Exception e) {
			logUnknowErrorMessages(json, e);
		}
		return json;
	}
    
    /**
     * 查找关注列表, 我关注的人
     * @param modelMap
     * @param id
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
	@RequestMapping(value = "/follows.json", method = {RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.POST})
	public JsonVo<List<User>> follows(ModelMap modelMap, Long id, HttpServletRequest request, HttpServletResponse response) {
		JsonVo<List<User>> json = new JsonVo<List<User>>();
		try {
			int pageNumber = getIntegerReqParam("pageNumber", 1);
			int pageSize = getIntegerReqParam("pageSize", SystemConstants.DEFAULT_PAGE_SIZE);
			int total = userRelationService.countFollows(id);
			json.setTotal(total);
			List<User> list = userRelationService.findFollowListByPage(id, pageNumber, pageSize);
			json.setResult(list);
			json.setStatus(JsonResultStatus.Success.getValue());
			if (CollectionUtils.isEmpty(list)) {
				json.setStatus(JsonResultStatus.EmptyResult.getValue());
			}
		} catch (Exception e) {
			logUnknowErrorMessages(json, e);
		}
		return json;
	}
    /**
     * 查找粉丝, 关注我的人
     * @param modelMap
     * @param id
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
	@RequestMapping(value = "/fans.json", method = {RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.POST})
	public JsonVo<List<User>> fans(ModelMap modelMap, Long id, HttpServletRequest request, HttpServletResponse response) {
		JsonVo<List<User>> json = new JsonVo<List<User>>();
		try {
			int pageNumber = getIntegerReqParam("pageNumber", 1);
			int pageSize = getIntegerReqParam("pageSize", SystemConstants.DEFAULT_PAGE_SIZE);
			int total = userRelationService.countFans(id);
			json.setTotal(total);
			List<User> list = userRelationService.findFansListByPage(id, pageNumber, pageSize);
			json.setResult(list);
			json.setStatus(JsonResultStatus.Success.getValue());
			if (CollectionUtils.isEmpty(list)) {
				json.setStatus(JsonResultStatus.EmptyResult.getValue());
			}
		} catch (Exception e) {
			logUnknowErrorMessages(json, e);
		}
		return json;
	}
    
    /**
     * 添加关注
     * @param modelMap
     * @param relation
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
	@RequestMapping(value = "/addFollows.json", method = {RequestMethod.POST, RequestMethod.OPTIONS})
	public JsonVo<List<UserRelation>> addFollows(ModelMap modelMap, Long fromId, String toIds, HttpServletRequest request, HttpServletResponse response) {
		JsonVo<List<UserRelation>> json = new JsonVo<List<UserRelation>>();
		try {
			validateRequest(request);
			validateAddFollowsArguments(fromId, toIds);
			UserRelation relation = initUserRelation(fromId, toIds, UserRelationType.Follow);
			if(userRelationService.addUserRelation(relation)) {
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
    
    /**
     * 取消关注
     * @param modelMap
     * @param fromId
     * @param toIds
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
   	@RequestMapping(value = "/canselFollows.json", method = {RequestMethod.POST, RequestMethod.OPTIONS})
   	public JsonVo<List<UserRelation>> canselFollows(ModelMap modelMap, Long fromId, String toIds, HttpServletRequest request, HttpServletResponse response) {
   		JsonVo<List<UserRelation>> json = new JsonVo<List<UserRelation>>();
   		try {
   			validateRequest(request);
   			List<Long> toIdsList = convertToIds(toIds);
   			validateCanselFollowsArguments(fromId, toIdsList);
   			if(userRelationService.cancelFollows(fromId, toIdsList)) {
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
    
    /**
     * 验证取消关注参数
     * @param fromId
     * @param toIdsList
     * @throws Exception 
     */
	private void validateCanselFollowsArguments(Long fromId, List<Long> toIdsList) throws Exception {
		if (null == fromId) {
			throw new ArgumentsIncorrectException("fromId参数无效");
		}
		if (CollectionUtils.isEmpty(toIdsList)) {
			throw new ArgumentsIncorrectException("toIds参数无效");
		}
		Long userId = getLoginUserId();
		if (fromId != userId) {
			throw new InvalidOpenTypeException("无效的操作");
		}
	}
	
	private List<Long> convertToIds(String toIds) throws GambleException {
		List<Long> toIdsList = Lists.newArrayList();
		String[] tmpArray = toIds.split(",");
		try {
			for (String id : tmpArray) {
				toIdsList.add(Long.valueOf(id));
			}
		} catch (Exception e) {
			throw new ArgumentsIncorrectException("toIds无效");
		}
		return toIdsList;
	}
	/**
	 * 
	 * @param fromId
	 * @param toId
	 */
	private void validateAddFollowsArguments(Long fromId, String toIdsString) throws GambleException {
		if (null == fromId || fromId < 0L) {
			throw new ArgumentsIncorrectException("fromId无效");
		}
		// TODO 判断是否已经关注
	}

	private UserRelation initUserRelation(Long fromId, String toIds, UserRelationType relationType) {
		// TODO Auto-generated method stub
		// 分解toIds
		return null;
	}

}
