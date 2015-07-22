package com.hannuus.gamble.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hannuus.gamble.bean.Topic;
import com.hannuus.gamble.bean.TopicExample;
import com.hannuus.gamble.dao.TopicMapper;
import com.hannuus.gamble.web.service.ITopicService;

@Service
public class TopicServiceImpl implements ITopicService {
	
	@Autowired
	TopicMapper topicMapper;
	
	@Override
	public boolean create(Topic topic) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Topic topic) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Long topicId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateTopicState(Long topicId, int state) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Topic findById(Long topicId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Topic> findByCategoryID(Long categoryId) {
		TopicExample example = new TopicExample();
		example.createCriteria().andCategoryIdEqualTo(categoryId);
		return topicMapper.selectByExample(example);
	}

	@Override
	public List<Topic> findBySpeciaId(Long speciaId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Topic> findCategoryTopicsByPage(Long categoryId,
			int startIndex, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Topic> findSpeciaTopicsByPage(Long speciaId, int startIndex,
			int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

}
