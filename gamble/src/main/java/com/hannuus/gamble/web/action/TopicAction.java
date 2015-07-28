package com.hannuus.gamble.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hannuus.gamble.bean.Topic;
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
	
	private static final Logger logger = Logger.getLogger(TopicAction.class);

	@ResponseBody
	@RequestMapping(value = "/listByCategoryId.json", method = {RequestMethod.GET})
	public JsonVo<List<Topic>> listByCategory(HttpServletRequest request, HttpServletResponse response) {
		JsonVo<List<Topic>> json = new JsonVo<List<Topic>>();
		try {
			validate(request);
			// TODO validate arguments
			// TODO do the service logic
			List<Topic> list = topicService.findTopicsByCategoryId(getLongReqParam("categoryId", 0L));
			if (CollectionUtils.isNotEmpty(list)) {
				json.setResult(list);
				json.setStatus(JsonResultStatus.Success.getValue());
			} else {
				json.setStatus(JsonResultStatus.EmptyResult.getValue());
			}
		} catch (GambleException e) {
			logger.error(e);
			json.setErrcode(e.getCode());
			json.setErrmsg(e.getMessage());
			json.setStatus(JsonResultStatus.Failed.getValue());
		}
		return json;
	}
	
	/**
	 * create a new topic
	 * @param Topic
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/create", method = {RequestMethod.POST})
	public JsonVo<List<Topic>> create(HttpServletRequest request, HttpServletResponse response) {
		JsonVo<List<Topic>> json = new JsonVo<List<Topic>>();
		try {
			validate(request);
			Topic topic = new Topic();
			if (!validateCreateArguments(topic)) {
				throw new ArgumentsIncorrectException();
			}
			// TODO do the service logic
			if(topicService.addTopic(topic)) {
				json.setStatus(JsonResultStatus.Success.getValue());
			} else {
				json.setStatus(JsonResultStatus.Failed.getValue());
			}
		} catch (GambleException e) {
			logger.error(e);
			json.setErrcode(e.getCode());
			json.setErrmsg(e.getMessage());
			json.setStatus(JsonResultStatus.Failed.getValue());
		}
		return json;
	}
	
	/**
	 * 
	 * @param topic
	 * @return
	 */
	private boolean validateCreateArguments(Topic topic) {
		// TODO validate arguments
		return false;
	}
}
