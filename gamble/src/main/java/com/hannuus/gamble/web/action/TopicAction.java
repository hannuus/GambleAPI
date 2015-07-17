package com.hannuus.gamble.web.action;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hannuus.gamble.bean.Topic;
import com.hannuus.gamble.vo.JsonVo;
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
	@RequestMapping(value = "/category/{categoryId}", method = {RequestMethod.GET, RequestMethod.POST})
	public JsonVo<List<Topic>> created(@PathVariable int categoryId) {
		JsonVo<List<Topic>> json = new JsonVo<List<Topic>>();
		try {
			List<Topic> list = topicService.findByCategoryID(categoryId);
			if (CollectionUtils.isNotEmpty(list)) {
				json.setT(list);
			}
		} catch (Exception e) {
			logger.error(e);
			json.setResult(false);
			json.setMsg(e.getMessage());
		}
		return json;
	}
}
