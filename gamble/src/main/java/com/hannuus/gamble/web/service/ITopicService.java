package com.hannuus.gamble.web.service;

import java.util.List;

import com.hannuus.gamble.bean.Topic;

public interface ITopicService {
	
	/**
	 * 发帖
	 * @param topic
	 * @return created or not
	 */
	boolean create(Topic topic);
	
	/**
	 * 更新帖子
	 * @param topic
	 * @return updated or not
	 */
	boolean update(Topic topic);
	
	/**
	 * 删除帖子, 不物理删除, 将状态设置为-1(will delete)
	 * @param topicId
	 * @return deleted or not
	 */
	boolean delete(Long topicId);
	
	/**
	 * 更新帖子的状态
	 * @param topicId
	 * @param state
	 * @return updated or not
	 */
	boolean updateTopicState(Long topicId, int state);
	
	/**
	 * 查询根据ID帖子
	 * @param topicId
	 * @return The Topic
	 */
	Topic findById(Long topicId);
	
	/**
	 *  根据分类ID查询帖子
	 * @param categoryId
	 * @return Topic list
	 */
	List<Topic> findByCategoryID(Long categoryId);
	
	/**
	 * 分页查询分类下的帖子
	 * @param categoryId
	 * @param startIndex
	 * @param pageIndex
	 * @return
	 */
	List<Topic> findCategoryTopicsByPage(Long categoryId, int startIndex, int pageSize);
	
	/**
	 * 根据专题ID查询
	 * @param speciaId
	 * @return Topic list
	 */
	List<Topic> findBySpeciaId(Long speciaId);
	
	/**
	 * 分页查询专题下的帖子
	 * @param speciaId
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	List<Topic> findSpeciaTopicsByPage(Long speciaId, int startIndex, int pageSize);
}
