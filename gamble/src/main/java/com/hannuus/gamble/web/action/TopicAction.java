package com.hannuus.gamble.web.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hannuus.gamble.bean.Topic;
import com.hannuus.gamble.comm.SystemConstants;
import com.hannuus.gamble.comm.TopicState;
import com.hannuus.gamble.vo.JsonResultStatus;
import com.hannuus.gamble.vo.JsonVo;
import com.hannuus.gamble.web.exception.GambleException;
import com.hannuus.gamble.web.exception.api.ArgumentsIncorrectException;
import com.hannuus.gamble.web.service.ITopicService;

/**
 * 
 * @author aelns
 *
 */
@Controller
@RequestMapping("/topic")
public class TopicAction extends BaseAction {
	
	@Autowired
	ITopicService topicService;
	
	/**
	 * 获取板块下的topic
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/listByCategoryId.json", method = {RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.POST})
	public JsonVo<List<Topic>> listByCategory(HttpServletRequest request, HttpServletResponse response) {
		JsonVo<List<Topic>> json = new JsonVo<List<Topic>>();
		setCrossOrigin(response);
		try {
			validateRequest(request);
			Long categoryId = getLongReqParam("categoryId", 0L);
			int pageNumber = getIntegerReqParam("pageNumber", 1);
			int pageSize = getIntegerReqParam("pageSize", SystemConstants.DEFAULT_PAGE_SIZE);
			
			int total = topicService.countTopicsByCategoryId(categoryId);
			json.setTotal(total);
			if (total > 0) {
				List<Topic> list = topicService.findCategoryTopicsByPage(categoryId, pageNumber, pageSize);
				json.setResult(list);
				json.setStatus(JsonResultStatus.Success.getValue());
			} else {
				json.setStatus(JsonResultStatus.EmptyResult.getValue());
			}
		} catch (GambleException e) {
			logErrorMessages(json, e);
		}
		return json;
	}
	
	/**
	 * 发帖
	 * @param Topic
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/add", method = {RequestMethod.POST, RequestMethod.OPTIONS})
	public JsonVo<List<Topic>> create(ModelMap modelMap, Topic topic, HttpServletRequest request, HttpServletResponse response) {
		JsonVo<List<Topic>> json = new JsonVo<List<Topic>>();
		setCrossOrigin(response);
		try {
			validateRequest(request);
			initTopic(topic);
			validateAddTopicArguments(topic);
			if(topicService.addTopic(topic)) {
				json.setStatus(JsonResultStatus.Success.getValue());
			} else {
				json.setStatus(JsonResultStatus.Failed.getValue());
			}
		} catch (GambleException e) {
			logErrorMessages(json, e);
		}
		return json;
	}
	
	/**
	 * 验证参数是否正确
	 * @param topic
	 * @return
	 */
	private void validateAddTopicArguments(Topic topic) throws ArgumentsIncorrectException {
		if (null == topic.getCategoryId() || topic.getCategoryId() < 0) {
			throw new ArgumentsIncorrectException("categoryId不能为空");
		}
		if (null == topic.getUserId() || topic.getUserId() < 0) {
			throw new ArgumentsIncorrectException("userId不能为空");
		}
		if (StringUtils.isBlank(topic.getTitle())) {
			throw new ArgumentsIncorrectException("title不能为空");
		}
		if (StringUtils.isBlank(topic.getContent())) {
			throw new ArgumentsIncorrectException("content不能为空");
		}
	}

	private void initTopic(Topic topic) throws GambleException {
		topic.setCreatedOn(new Date());
		topic.setEnabled(true);// 默认启用
		topic.setFollowCount(0L);
		topic.setHits(0L);
		topic.setReplyCount(0L);
		topic.setState(TopicState.Normal.value());
	}
}
