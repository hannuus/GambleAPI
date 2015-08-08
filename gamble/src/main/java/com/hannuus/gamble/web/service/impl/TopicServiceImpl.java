package com.hannuus.gamble.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hannuus.gamble.comm.TopicState;
import com.hannuus.gamble.dao.TopicMapper;
import com.hannuus.gamble.web.dto.SearchTopicParamDTO;
import com.hannuus.gamble.web.service.TopicService;
import com.hannuus.model.gamble.Topic;
import com.hannuus.model.gamble.TopicExample;

@Service
public class TopicServiceImpl implements TopicService {
	
	@Autowired
	TopicMapper topicMapper;
	
	@Override
	public boolean addTopic(Topic topic) {
		return topicMapper.insert(topic) > 0;
	}

	@Override
	public boolean updateTopic(Topic topic) {
		return topicMapper.updateByPrimaryKeySelective(topic) > 0;
	}

	@Override
	public boolean deleteTopic(Long topicId) {
		return updateTopicState(topicId, TopicState.Willdelete.value());
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
	public List<Topic> findTopicsBySpeciaId(Long speciaId) {
		TopicExample example = new TopicExample();
		example.createCriteria().andSpeciaIdEqualTo(speciaId);
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
	public List<Topic> findSpeciaTopicsByPage(Long speciaId, int pageNumber,
			int pageSize) {
		TopicExample example = new TopicExample();
		int pageIndex = 0;
		pageIndex = (pageNumber - 1) * pageSize;
		example.setLimitStart(pageIndex);
		example.setLimitEnd(pageSize);
		example.createCriteria().andSpeciaIdEqualTo(speciaId);
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
	public int countTopicsBySpeciaId(Long speciaId) {
		TopicExample example = new TopicExample();
		example.createCriteria().andSpeciaIdEqualTo(speciaId);
		return topicMapper.countByExample(example);
	}

}
