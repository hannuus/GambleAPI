package com.hannuus.gamble.web.service;

import java.util.List;

import com.hannuus.gamble.bean.Topic;

public interface ITopicService {
	
	/**
	 * create a new topic
	 * @param topic
	 * @return created or not
	 */
	boolean create(Topic topic);
	
	/**
	 * update the topic
	 * @param topic
	 * @return updated or not
	 */
	boolean update(Topic topic);
	
	/**
	 * delete topic by topic ID
	 * @param topicId
	 * @return deleted or not
	 */
	boolean delete(Long topicId);
	
	/**
	 * 
	 * @param topicId
	 * @param state
	 * @return updated or not
	 */
	boolean updateTopicState(Long topicId, int state);
	
	/**
	 * find topic by topic ID
	 * @param topicId
	 * @return The Topic
	 */
	Topic findById(Long topicId);
	
	/**
	 *  find topic by category ID
	 * @param categoryId
	 * @return Topic list
	 */
	List<Topic> findByCategoryID(Long categoryId);
	
	/**
	 * find topic by specia ID
	 * @param speciaId
	 * @return Topic list
	 */
	List<Topic> findBySpeciaId(Long speciaId);
}
