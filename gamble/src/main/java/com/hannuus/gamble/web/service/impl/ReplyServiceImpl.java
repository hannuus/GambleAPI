package com.hannuus.gamble.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hannuus.gamble.dao.ReplyMapper;
import com.hannuus.gamble.web.service.ReplyService;
import com.hannuus.gamble.web.service.UserService;
import com.hannuus.model.gamble.Reply;
import com.hannuus.model.gamble.ReplyExample;

@Service
public class ReplyServiceImpl implements ReplyService{
	
	@Autowired
	ReplyMapper replyMapper;
	
	@Autowired
	UserService userService;
	
	@Override
	public boolean addReply(Reply reply) {
		boolean added = replyMapper.insert(reply) > 0;
		if (added) {
			// 用户的帖子统计数量加1
			userService.updateUserReplyCount(reply.getUserId(), 1);
		}
		return added;
	}

	@Override
	public boolean deleteReply(Long replyId) {
		boolean deleted = replyMapper.deleteByPrimaryKey(replyId) > 0;
		if (deleted) {
			// 用户的帖子统计数量减1
			Reply reply = findReplyById(replyId);
			userService.updateUserReplyCount(reply.getUserId(), -1);
		}
		return deleted;
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
