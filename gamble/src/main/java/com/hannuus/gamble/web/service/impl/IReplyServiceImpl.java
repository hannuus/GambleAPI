package com.hannuus.gamble.web.service.impl;

import java.util.List;

import com.hannuus.gamble.bean.Reply;
import com.hannuus.gamble.web.service.IReplyService;

public class IReplyServiceImpl implements IReplyService{

	@Override
	public boolean AddReply(Reply reply) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteReply(Long replyId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Reply> findByTopicId(Long topicId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reply> findReplysByPage(Long topicId, int startIndex,
			int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reply> findChildReply(Long replyId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reply> findChildReplyByPage(Long replyId, int startIndex,
			int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

}
