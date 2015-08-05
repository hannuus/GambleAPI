package com.hannuus.gamble.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hannuus.gamble.bean.Reply;
import com.hannuus.gamble.bean.ReplyExample;
import com.hannuus.gamble.dao.ReplyMapper;
import com.hannuus.gamble.web.service.IReplyService;

public class ReplyServiceImpl implements IReplyService{
	
	@Autowired
	ReplyMapper replyMapper;
	
	@Override
	public boolean addReply(Reply reply) {
		return replyMapper.insert(reply) > 0;
	}

	@Override
	public boolean deleteReply(Long replyId) {
		return replyMapper.deleteByPrimaryKey(replyId) > 0;
	}

	@Override
	public List<Reply> findReplysByTopicId(Long topicId) {
		ReplyExample example = new ReplyExample();
		example.createCriteria().andTopicIdEqualTo(topicId);
		return replyMapper.selectByExample(example);
	}

	@Override
	public List<Reply> findReplysByPage(Long topicId, int pageNumber,
			int pageSize) {
		ReplyExample example = new ReplyExample();
		example.createCriteria().andTopicIdEqualTo(topicId);
		int pageIndex = 0;
		pageIndex = (pageNumber - 1) * pageSize;
		example.setLimitStart(pageIndex);
		example.setLimitEnd(pageSize);
		return replyMapper.selectByExample(example);
	}

	@Override
	public List<Reply> findChildReply(Long replyId) {
		ReplyExample example = new ReplyExample();
		example.createCriteria().andParentIdEqualTo(replyId);
		return replyMapper.selectByExample(example);
	}

	@Override
	public List<Reply> findChildReplyByPage(Long parentId, int pageNumber,
			int pageSize) {
		ReplyExample example = new ReplyExample();
		example.createCriteria().andParentIdEqualTo(parentId);
		int pageIndex = 0;
		pageIndex = (pageNumber - 1) * pageSize;
		example.setLimitStart(pageIndex);
		example.setLimitEnd(pageSize);
		return replyMapper.selectByExample(example);
	}

	@Override
	public int countByToticId(Long topicId) {
		ReplyExample example = new ReplyExample();
		example.createCriteria().andTopicIdEqualTo(topicId);
		return replyMapper.countByExample(example);
	}

	@Override
	public boolean updateReply(Reply reply) {
		return replyMapper.updateByPrimaryKeySelective(reply) > 0;
	}

	@Override
	public int countChildReplys(Long parentId) {
		ReplyExample example = new ReplyExample();
		example.createCriteria().andParentIdEqualTo(parentId);
		return replyMapper.countByExample(example);
	}

	@Override
	public Reply findReplyById(Long replyId) {
		return replyMapper.selectByPrimaryKey(replyId);
	}
}
