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

import com.hannuus.gamble.bean.Reply;
import com.hannuus.gamble.bean.Topic;
import com.hannuus.gamble.comm.SystemConstants;
import com.hannuus.gamble.vo.JsonResultStatus;
import com.hannuus.gamble.vo.JsonVo;
import com.hannuus.gamble.web.exception.GambleException;
import com.hannuus.gamble.web.exception.api.ArgumentsIncorrectException;
import com.hannuus.gamble.web.service.IReplyService;

/**
 * 
 * @author aelns
 * 
 * TODO need more test
 *
 */
@Controller
@RequestMapping("/reply")
public class ReplyAction extends BaseAction {
	
	@Autowired
	IReplyService replyService;
	
	@ResponseBody
	@RequestMapping(value = "/detail.json", method = {RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.POST})
	public JsonVo<Reply> detail(HttpServletRequest request, HttpServletResponse response) {
		JsonVo<Reply> json = new JsonVo<Reply>();
		setCrossOrigin(response);
		try {
			Long replyId = getLongReqParam("id", 0L);
			json.setTotal(0);
			Reply topic = replyService.findReplyById(replyId);
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
	 * 添加一条评论
	 * @param Topic
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/add", method = {RequestMethod.POST, RequestMethod.OPTIONS})
	public JsonVo<List<Reply>> create(ModelMap modelMap, Reply reply, HttpServletRequest request, HttpServletResponse response) {
		JsonVo<List<Reply>> json = new JsonVo<List<Reply>>();
		setCrossOrigin(response);
		try {
			validateRequest(request);
			initReply(reply);
			validateAddReplyArguments(reply);
			if(replyService.addReply(reply)) {
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
	 * 删除评论
	 * @param modelMap
	 * @param id
	 * @param request
	 * @param response
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
			if(replyService.deleteReply(id)) {
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
	 * 编辑评论
	 * @param modelMap
	 * @param id
	 * @param content
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/update", method = {RequestMethod.POST, RequestMethod.OPTIONS})
	public JsonVo<List<Topic>> update(ModelMap modelMap, Long id, String content, HttpServletRequest request, HttpServletResponse response) {
		JsonVo<List<Topic>> json = new JsonVo<List<Topic>>();
		setCrossOrigin(response);
		try {
			validateRequest(request);
			validateUpdateReplyArguments(id, content);
			Reply reply = new Reply();
			reply.setId(id);
			reply.setContent(content);
			reply.setModifiedOn(new Date());
			if(replyService.updateReply(reply)) {
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
	 * 分页查询一个主题的评论
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/listByTopicId.json", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
	public JsonVo<List<Reply>> listByTopicId(HttpServletRequest request, HttpServletResponse response) {
		JsonVo<List<Reply>> json = new JsonVo<List<Reply>>();
		setCrossOrigin(response);
		try {
			Long topicId = getLongReqParam("topicId", 0L);
			int pageNumber = getIntegerReqParam("pageNumber", 1);
			int pageSize = getIntegerReqParam("pageSize", SystemConstants.DEFAULT_PAGE_SIZE);
			int total = replyService.countByToticId(topicId);
			json.setTotal(total);
			List<Reply> list = replyService.findReplysByPage(topicId, pageNumber, pageSize);
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
	 * 分页获取回复, 即获取子评论
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/listChilds.json", method = { RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS })
	public JsonVo<List<Reply>> listChilds(HttpServletRequest request, HttpServletResponse response) {
		JsonVo<List<Reply>> json = new JsonVo<List<Reply>>();
		setCrossOrigin(response);
		try {
			Long parentId = getLongReqParam("parentId", 0L);
			int pageNumber = getIntegerReqParam("pageNumber", 1);
			int pageSize = getIntegerReqParam("pageSize", SystemConstants.DEFAULT_PAGE_SIZE);
			int total = replyService.countChildReplys(parentId);
			json.setTotal(total);
			List<Reply> list = replyService.findChildReplyByPage(parentId, pageNumber, pageSize);
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
	 * 验证更新的参数是否正确
	 * @param id
	 * @param content
	 */
	private void validateUpdateReplyArguments(Long repLyId, String content)  throws ArgumentsIncorrectException {
		if (null == repLyId || repLyId < 0L) {
			throw new ArgumentsIncorrectException("ID无效");
		}
		if (null == replyService.findReplyById(repLyId)) {
			throw new ArgumentsIncorrectException("评论不存在");
		}
		if (StringUtils.isBlank(content)) {
			throw new ArgumentsIncorrectException("content不能为空");
		}
	}
	
	/**
	 * 验证删除
	 * @param request
	 * @param replyId
	 * @throws Exception 
	 */
	private void validateDelete(HttpServletRequest request, Long replyId)  throws Exception  {
		Reply reply = replyService.findReplyById(replyId);
		if (null == reply) {
			throw new ArgumentsIncorrectException("评论不存在");
		}
		Long userId = reply.getUserId();
		// 只能删除自己的评论
		if (getLoginUserId() != userId) {
			throw new InvalidOpenTypeException();
		}
	}
	/**
	 * 初始化reply
	 * @param reply
	 */
	private void initReply(Reply reply) {
		reply.setCreatedOn(new Date());
		reply.setReplyCount(0L);
		reply.setUpCount(0L);
	}
	/**
	 * 验证添加评论参数
	 * @param reply
	 */
	private void validateAddReplyArguments(Reply reply) throws GambleException {
		if (null == reply.getTopicId() || reply.getTopicId() < 0) {
			throw new ArgumentsIncorrectException("topicId无效");
		}
		if (null == reply.getUserId() || reply.getUserId() < 0) {
			throw new ArgumentsIncorrectException("userId不能为空");
		}
		if (StringUtils.isBlank(reply.getContent())) {
			throw new ArgumentsIncorrectException("content不能为空");
		}
	}
}
