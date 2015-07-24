package com.hannuus.gamble.web.service;

import java.util.List;

import com.hannuus.gamble.bean.Reply;

public interface IReplyService {
	
	/**
	 * 添加一条评论
	 * @param comment
	 * @return
	 */
	boolean addReply(Reply reply);
	
	/**
	 * 删除一条评论, 不物理删除, 修改评论的状态为-1
	 * @param replyId
	 * @return
	 */
	boolean deleteReply(Long replyId);
	
	/**
	 * 查询一个主题的所有评论
	 * @param topicId
	 * @return
	 */
	List<Reply> findByTopicId(Long topicId);
	
	/**
	 * 分页查询一个主题的评论
	 * @param topicId
	 * @param pageNUmber
	 * @param pageSize
	 * @return
	 */
	List<Reply> findReplysByPage(Long topicId, int pageNUmber, int pageSize);
	
	/***
	 * 获取回复, 即获取子评论
	 * @param replyId
	 * @return
	 */
	List<Reply> findChildReply(Long replyId);
	
	/**
	 * 分页获取子评论
	 * @param replyId
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	List<Reply> findChildReplyByPage(Long replyId, int pageNumber, int pageSize);
}
