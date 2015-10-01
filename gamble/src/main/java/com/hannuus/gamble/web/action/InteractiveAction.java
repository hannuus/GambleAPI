package com.hannuus.gamble.web.action;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hannuus.core.json.JsonResultStatus;
import com.hannuus.gamble.comm.JsonVo;
import com.hannuus.gamble.model.Topic;
import com.hannuus.gamble.web.service.InteractiveService;
import com.hannuus.pagination.PageDTO;
import com.hannuus.pagination.PageParams;
import com.hannuus.pagination.PageQueryCallback;

/**
 * 论坛交互API
 * 
 * @author cuesky
 * @date 2015年9月8日 上午11:46:26
 */
@Controller
@RequestMapping("/interactive")
public class InteractiveAction extends BaseAction {

	@Autowired
	InteractiveService interactiveService;

	/**
	 * 根据categoryId展示自己发布的所有帖子
	 * 
	 * @param categoryId
	 *            版块ID
	 * @param pageNum
	 *            页码
	 * @param pageSize
	 *            页面大小
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/listTopicByCategoryId.json")
	public JsonVo<PageDTO<Topic>> listTopicByCategoryId(final Long categoryId,
			int pageNum, int pageSize) {
		JsonVo<PageDTO<Topic>> json = new JsonVo<PageDTO<Topic>>();
		final Long userId = getCurrentUserId();
		PageDTO<Topic> page = pageQuery(pageNum, pageSize, null,
				new PageQueryCallback<Topic>() {
					@Override
					public PageDTO<Topic> query(PageParams params) {
						return interactiveService
								.findSelfTopicPageByCategoryId(userId,
										categoryId, params);
					}
				});
		json.setResult(page);
		return json;
	}

	/**
	 * 设置Topic是否置顶
	 * 
	 * @param id
	 *            帖子ID
	 * @param stickie
	 *            0-取消置顶 1-置顶
	 * @return 仅返回成功或失败标识，由客户端决定下一个页面跳转至哪里
	 */
	@ResponseBody
	@RequestMapping("/stickieTopic.json")
	public JsonVo<Integer> stickieTopic(Long id, Integer stickie) {
		JsonVo<Integer> json = new JsonVo<Integer>();
		Long userId = getCurrentUserId();
		int count = interactiveService.stickieTopic(id, userId, stickie);
		if (count == 1) {
			json.setStatus(JsonResultStatus.Success.getValue());
		} else {
			json.setStatus(JsonResultStatus.Failed.getValue());
		}
		return json;
	}

	/**
	 * 设置Topic是否精华
	 * 
	 * @param id
	 *            帖子ID
	 * @param essence
	 *            0-非精华 1-精华
	 * @return
	 */
	@RequiresRoles(value = { "admin" })
	@ResponseBody
	@RequestMapping("/essenceTopic.json")
	public JsonVo<Integer> essenceTopic(Long id, Integer essence) {
		JsonVo<Integer> json = new JsonVo<Integer>();
		int count = interactiveService.essenceTopic(id, essence);
		if (count == 1) {
			json.setStatus(JsonResultStatus.Success.getValue());
		} else {
			json.setStatus(JsonResultStatus.Failed.getValue());
		}
		return json;
	}

	/**
	 * 点赞
	 * 
	 * @param topicId
	 *            帖子ID
	 * @param replyId
	 *            回复ID
	 * @param honorValue
	 *            0-踩 1-赞
	 * @return 0-无效操作 1-有效操作
	 */
	@ResponseBody
	@RequestMapping("/honor.json")
	public JsonVo<Integer> honor(Long topicId, Long replyId, Integer honorValue) {
		JsonVo<Integer> json = new JsonVo<Integer>();
		Long userId = getCurrentUserId();
		int count = interactiveService.honor(userId, topicId, replyId,
				honorValue);
		if (count == 1) {
			json.setStatus(JsonResultStatus.Success.getValue());
		} else {
			json.setStatus(JsonResultStatus.Failed.getValue());
		}
		return json;
	}

	/**
	 * 投票
	 * 
	 * @param voteId
	 *            投票ID
	 * @param voteItemIds
	 *            投票选项
	 * @return 0-无效操作 1-有效操作
	 */
	@ResponseBody
	@RequestMapping("/vote.json")
	public JsonVo<Integer> vote(Long voteId, String voteItemIds) {
		JsonVo<Integer> json = new JsonVo<Integer>();
		Long userId = getCurrentUserId();
		int count = interactiveService.vote(userId, voteId, voteItemIds);
		if (count == 1) {
			json.setStatus(JsonResultStatus.Success.getValue());
		} else {
			json.setStatus(JsonResultStatus.Failed.getValue());
		}
		return json;
	}

}
