package com.hannuus.gamble.web.service;

import com.hannuus.gamble.model.Topic;
import com.hannuus.pagination.PageDTO;
import com.hannuus.pagination.PageParams;

/**
 * 论坛交互API
 * 
 * @author cuesky
 * @date 2015年9月8日 上午11:55:51
 */
public interface InteractiveService {

	/**
	 * 根据categoryId分页查询userId发布的所有帖子
	 * 
	 * @param userId
	 *            用户ID
	 * @param categoryId
	 *            版块ID
	 * @param params
	 *            分页参数
	 * @return
	 */
	public PageDTO<Topic> findSelfTopicPageByCategoryId(Long userId,
			Long categoryId, PageParams params);

	/**
	 * 根据id和userId设置帖子是否置顶
	 * 
	 * @param id
	 *            帖子ID
	 * @param userId
	 *            用户ID
	 * @param stickie
	 *            0-非置顶 1-置顶
	 * @return
	 */
	public int stickieTopic(Long id, Long userId, Integer stickie);

	/**
	 * 根据id设置帖子是否精华
	 * 
	 * @param id
	 *            帖子ID
	 * @param essence
	 *            0-非精华 1-精华
	 * @return
	 */
	public int essenceTopic(Long id, Integer essence);

	/**
	 * 点赞
	 * 
	 * @param userId
	 *            用户ID
	 * @param topicId
	 *            帖子ID
	 * @param replyId
	 *            回复ID
	 * @param honorValue
	 *            0-踩 1-赞
	 * @return 0-无效操作 1-有效操作
	 */
	public int honor(Long userId, Long topicId, Long replyId, Integer honorValue);

	/**
	 * 投票
	 * 
	 * @param userId
	 *            用户ID
	 * @param voteId
	 *            投票ID
	 * @param voteItemIds
	 *            投票选项ID字符串
	 * @return 0-无效操作 1-有效操作
	 */
	public int vote(Long userId, Long voteId, String voteItemIds);

}
