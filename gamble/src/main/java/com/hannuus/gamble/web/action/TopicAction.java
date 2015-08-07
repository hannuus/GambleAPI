package com.hannuus.gamble.web.action;

import java.util.Date;
import java.util.List;

import javax.management.openmbean.InvalidOpenTypeException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
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
 * TODO need more test 
 *
 */
@Controller
@RequestMapping("/topic")
public class TopicAction extends BaseAction {
	
	@Autowired
	ITopicService topicService;
	
	/**
	 * 获取主题详情
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/detail.json", method = {RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.POST})
	public JsonVo<Topic> detail(HttpServletRequest request, HttpServletResponse response) {
		JsonVo<Topic> json = new JsonVo<Topic>();
		setCrossOrigin(response);
		try {
			Long topicId = getLongReqParam("id", 0L);
			json.setTotal(0);
			Topic topic = topicService.findTopicsById(topicId);
			if (null != topic) {
				json.setTotal(1);
				json.setResult(topic);
				json.setStatus(JsonResultStatus.Success.getValue());
			} else {
				json.setStatus(JsonResultStatus.EmptyResult.getValue());
			}
		} catch (Exception e) {
			logUnknowErrorMessages(json, e);
		}
		return json;
	}
	
	/**
	 * 获取某个板块下的topic
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/listByCategoryId.json", method = {RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.POST})
	public JsonVo<List<Topic>> listByCategoryId(HttpServletRequest request, HttpServletResponse response) {
		JsonVo<List<Topic>> json = new JsonVo<List<Topic>>();
		setCrossOrigin(response);
		try {
			Long categoryId = getLongReqParam("id", 0L);
			int pageNumber = getIntegerReqParam("pageNumber", 1);
			int pageSize = getIntegerReqParam("pageSize", SystemConstants.DEFAULT_PAGE_SIZE);
			int total = topicService.countTopicsByCategoryId(categoryId);
			json.setTotal(total);
			List<Topic> list = topicService.findCategoryTopicsByPage(categoryId, pageNumber, pageSize);
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
	 * 获取板块下的topic
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/listBySpeciaId.json", method = {RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.POST})
	public JsonVo<List<Topic>> listBySpeciaId(HttpServletRequest request, HttpServletResponse response) {
		JsonVo<List<Topic>> json = new JsonVo<List<Topic>>();
		setCrossOrigin(response);
		try {
			Long speciaId = getLongReqParam("speciaId", 0L);
			int pageNumber = getIntegerReqParam("pageNumber", 1);
			int pageSize = getIntegerReqParam("pageSize", SystemConstants.DEFAULT_PAGE_SIZE);
			int total = topicService.countTopicsBySpeciaId(speciaId);
			json.setTotal(total);
			List<Topic> list = topicService.findSpeciaTopicsByPage(speciaId, pageNumber, pageSize);
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
		} catch (Exception e) {
			logUnknowErrorMessages(json, e);
		}
		return json;
	}
	
	/**
	 * 验证发帖参数是否正确
	 * @param topic
	 * @return
	 */
	private void validateAddTopicArguments(Topic topic) throws ArgumentsIncorrectException {
		if (null == topic.getCategoryId() || topic.getCategoryId() < 0) {
			throw new ArgumentsIncorrectException("categoryId无效");
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
	
	/**
	 * 发帖
	 * @param Topic
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/update", method = {RequestMethod.POST, RequestMethod.OPTIONS})
	public JsonVo<List<Topic>> update(ModelMap modelMap, Long id, String title,
			String content, HttpServletRequest request, HttpServletResponse response) {
		JsonVo<List<Topic>> json = new JsonVo<List<Topic>>();
		setCrossOrigin(response);
		try {
			validateRequest(request);
			validateUpdateTopicArguments(id, title, content);
			Topic topic = new Topic();
			topic.setId(id);
			topic.setTitle(title);
			topic.setContent(content);
			topic.setModifiedBy(getLoginUserId());
			topic.setModifiedOn(new Date());
			if(topicService.updateTopic(topic)) {
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
	 * 验证更新贴子的参数是否正确
	 * @param id
	 * @param title
	 * @param content
	 * @throws ArgumentsIncorrectException
	 */
	private void validateUpdateTopicArguments(Long topicId, String title,
			String content) throws ArgumentsIncorrectException {
		if (null == topicId || topicId < 0L) {
			throw new ArgumentsIncorrectException("topicId无效");
		}
		if (null == topicService.findTopicsById(topicId)) {
			throw new ArgumentsIncorrectException("帖子不存在");
		}
		if (StringUtils.isBlank(title)) {
			throw new ArgumentsIncorrectException("title不能为空");
		}
		if (StringUtils.isBlank(content)) {
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
	
	/**
	 * 发帖
	 * @param Topic
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/delete", method = {RequestMethod.POST, RequestMethod.OPTIONS})
	public JsonVo<List<Topic>> delete(ModelMap modelMap, Long id, HttpServletRequest request, HttpServletResponse response) {
		JsonVo<List<Topic>> json = new JsonVo<List<Topic>>();
		setCrossOrigin(response);
		try {
			validateRequest(request);
			validateDelete(request, id);
			if(topicService.deleteTopic(id)) {
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

	private void validateDelete(HttpServletRequest request, Long id) throws Exception {
		Topic topic = topicService.findTopicsById(id);
		if (null == topic) {
			throw new ArgumentsIncorrectException("帖子不存在");
		}
		Long userId = topic.getUserId();
		// 只能删除自己的帖子
		if (getLoginUserId() != userId) {
			throw new InvalidOpenTypeException();
		}
	}
}
