package com.hannuus.gamble.web.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hannuus.gamble.dao.HonorMapper;
import com.hannuus.gamble.dao.ReplyMapper;
import com.hannuus.gamble.dao.TopicMapper;
import com.hannuus.gamble.dao.VoteUserMapper;
import com.hannuus.gamble.model.Honor;
import com.hannuus.gamble.model.HonorExample;
import com.hannuus.gamble.model.Topic;
import com.hannuus.gamble.model.TopicExample;
import com.hannuus.gamble.model.VoteUser;
import com.hannuus.gamble.model.VoteUserExample;
import com.hannuus.gamble.web.service.InteractiveService;
import com.hannuus.pagination.PageDTO;
import com.hannuus.pagination.PageParams;

/**
 * @author cuesky
 * @date 2015年9月8日 下午11:23:36
 */
@Service
public class InteractiveServiceImpl implements InteractiveService {

	@Autowired
	TopicMapper topicMapper;
	@Autowired
	ReplyMapper replyMapper;
	@Autowired
	HonorMapper honorMapper;
	@Autowired
	VoteUserMapper voteUserMapper;

	@Override
	public PageDTO<Topic> findSelfTopicPageByCategoryId(Long userId,
			Long categoryId, PageParams params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int stickieTopic(Long id, Long userId, Integer stickie) {
		Topic record = new Topic();
		record.setStickie(stickie);
		TopicExample example = new TopicExample();
		example.createCriteria().andIdEqualTo(id).andUserIdEqualTo(userId);
		return topicMapper.updateByExample(record, example);
	}

	@Override
	public int essenceTopic(Long id, Integer essence) {
		Topic record = new Topic();
		record.setId(id);
		record.setEssence(essence);
		return topicMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int honor(Long userId, Long topicId, Long replyId, Integer honorValue) {
		if (topicId != null && topicId != 0) {
			return honorTopic(userId, topicId, honorValue);
		}
		if (replyId != null && replyId != 0) {
			return honorReply(userId, replyId, honorValue);
		}
		return 0;
	}

	private int honorTopic(Long userId, Long topicId, Integer honorValue) {
		HonorExample example = new HonorExample();
		example.createCriteria().andUserIdEqualTo(userId)
				.andTopicIdEqualTo(topicId);
		List<Honor> list = honorMapper.selectByExample(example);
		if (CollectionUtils.isEmpty(list)) {// 第一次操作
			Honor honor = new Honor();
			honor.setUserId(userId);
			honor.setTopicId(topicId);
			honor.setHonorValue(honorValue);
			honor.setCreatedDate(new Date());// 默认系统时间
			honorMapper.insertSelective(honor);
			// 更新count
			if (honorValue == 1) {
				topicMapper.increaseUpCountByPrimaryKey(topicId);
			} else {
				topicMapper.increaseDownCountByPrimaryKey(topicId);
			}
			return 1;// 有效操作
		}
		return 0;// 无效操作
	}

	private int honorReply(Long userId, Long replyId, Integer honorValue) {
		HonorExample example = new HonorExample();
		example.createCriteria().andUserIdEqualTo(userId)
				.andReplyIdEqualTo(replyId);
		List<Honor> list = honorMapper.selectByExample(example);
		if (CollectionUtils.isEmpty(list)) {// 第一次操作
			Honor honor = new Honor();
			honor.setUserId(userId);
			honor.setReplyId(replyId);
			honor.setHonorValue(honorValue);
			honor.setCreatedDate(new Date());// 默认系统时间
			honorMapper.insertSelective(honor);

			// 更新count
			if (honorValue == 1) {
				replyMapper.increaseUpCountByPrimaryKey(replyId);
			} else {
				replyMapper.increaseDownCountByPrimaryKey(replyId);
			}
			return 1;// 有效操作
		}
		return 0;// 无效操作
	}

	@Override
	public int vote(Long userId, Long voteId, String voteItemIds) {
		// check voteItemIds?
		VoteUserExample example = new VoteUserExample();
		example.createCriteria().andUserIdEqualTo(userId)
				.andVoteIdEqualTo(voteId);
		List<VoteUser> list = voteUserMapper.selectByExample(example);
		if (CollectionUtils.isEmpty(list)) {
			VoteUser voteUser = new VoteUser();
			voteUser.setUserId(userId);
			voteUser.setVoteId(voteId);
			voteUser.setVoteItemIds(voteItemIds);
			voteUser.setCreatedDate(new Date());// 默认系统时间
			voteUserMapper.insertSelective(voteUser);
			return 1;// 有效操作
		}
		return 0;// 无效操作
	}

}
