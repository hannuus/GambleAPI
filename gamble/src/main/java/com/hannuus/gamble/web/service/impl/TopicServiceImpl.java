package com.hannuus.gamble.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hannuus.gamble.comm.TopicState;
import com.hannuus.gamble.dao.TopicMapper;
import com.hannuus.gamble.model.Topic;
import com.hannuus.gamble.model.TopicExample;
import com.hannuus.gamble.web.dto.SearchTopicParamDTO;
import com.hannuus.gamble.web.service.TopicService;
import com.hannuus.gamble.web.service.UserService;

@Service
public class TopicServiceImpl implements TopicService {

	@Autowired
	TopicMapper topicMapper;

	@Autowired
	UserService userService;

	@Override
	public boolean addTopic(Topic topic) {
		boolean added = topicMapper.insert(topic) > 0;
		if (added) {
			userService.updateUserTopicCount(topic.getUserId(), 1);
		}
		return added;
	}

	@Override
	public boolean updateTopic(Topic topic) {
		return topicMapper.updateByPrimaryKeySelective(topic) > 0;
	}

	@Override
	public boolean deleteTopic(Long topicId) {
		boolean deleted = updateTopicState(topicId,
				TopicState.Willdelete.value());
		if (deleted) {
			Topic topic = findTopicsById(topicId);
			userService.updateUserTopicCount(topic.getUserId(), -1);
		}
		return deleted;
	}

	@Override
	public boolean updateTopicState(Long topicId, int state) {
		Topic topic = new Topic();
		topic.setId(topicId);
		topic.setState(state);
		return topicMapper.updateByPrimaryKeySelective(topic) > 0;
	}

	@Override
	public Topic findTopicsById(Long topicId) {
		return topicMapper.selectByPrimaryKey(topicId);
	}

	@Override
	public List<Topic> findTopicsByCategoryId(Long categoryId) {
		TopicExample example = new TopicExample();
		example.createCriteria().andCategoryIdEqualTo(categoryId);
		return topicMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public List<Topic> findTopicsBySpeciaId(Long specialId) {
		TopicExample example = new TopicExample();
		example.createCriteria().andSpecialIdEqualTo(specialId);
		return topicMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public List<Topic> findCategoryTopicsByPage(Long categoryId,
			int pageNumber, int pageSize) {
		TopicExample example = new TopicExample();
		int pageIndex = 0;
		pageIndex = (pageNumber - 1) * pageSize;
		example.setLimitStart(pageIndex);
		example.setLimitEnd(pageSize);
		example.createCriteria().andCategoryIdEqualTo(categoryId);
		return topicMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public List<Topic> findSpeciaTopicsByPage(Long specialId, int pageNumber,
			int pageSize) {
		TopicExample example = new TopicExample();
		int pageIndex = 0;
		pageIndex = (pageNumber - 1) * pageSize;
		example.setLimitStart(pageIndex);
		example.setLimitEnd(pageSize);
		example.createCriteria().andSpecialIdEqualTo(specialId);
		return topicMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public List<Topic> searchByPage(SearchTopicParamDTO dto, int pageNumber,
			int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countTopicsByCategoryId(Long categoryId) {
		TopicExample example = new TopicExample();
		example.createCriteria().andCategoryIdEqualTo(categoryId);
		return topicMapper.countByExample(example);
	}

	@Override
	public int countTopicsBySpeciaId(Long specialId) {
		TopicExample example = new TopicExample();
		example.createCriteria().andSpecialIdEqualTo(specialId);
		return topicMapper.countByExample(example);
	}

}
