package com.hannuus.gamble.web.service;

import java.util.List;

import com.hannuus.gamble.bean.Topic;
import com.hannuus.gamble.web.dto.SearchTopicParamDTO;

public interface TopicService {
	
	/**
	 * 发帖
	 * @param topic
	 * @return created or not
	 */
	boolean addTopic(Topic topic);
	
	/**
	 * 更新帖子
	 * @param topic
	 * @return updated or not
	 */
	boolean updateTopic(Topic topic);
	
	/**
	 * 删除帖子, 不物理删除, 将状态设置为-1(will delete)
	 * @param topicId
	 * @return deleted or not
	 */
	boolean deleteTopic(Long topicId);
	
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
	Topic findTopicsById(Long topicId);
	
	/**
	 *  根据分类ID查询帖子
	 * @param categoryId
	 * @return Topic list
	 */
	List<Topic> findTopicsByCategoryId(Long categoryId);
	
	/**
	 * 分页查询分类下的帖子
	 * @param categoryId
	 * @param startIndex
	 * @param pageIndex
	 * @return
	 */
	List<Topic> findCategoryTopicsByPage(Long categoryId, int pageNumber, int pageSize);
	
	/**
	 * 统计Category下的主题数量
	 * @param categoryId
	 * @return
	 */
	int countTopicsByCategoryId(Long categoryId);
	/**
	 * 根据专题ID查询
	 * @param speciaId
	 * @return Topic list
	 */
	List<Topic> findTopicsBySpeciaId(Long speciaId);
	
	/**
	 * 分页查询专题下的帖子
	 * @param speciaId
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	List<Topic> findSpeciaTopicsByPage(Long speciaId, int pageNumber, int pageSize);
	
	/**
	 * 搜索（查询热门词汇、tags；据作者、标题、内容搜索）
	 * @param dto
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	List<Topic> searchByPage(SearchTopicParamDTO dto, int pageNumber, int pageSize);
	
	/**
	 * 统计主题下中的帖子数量
	 * @param speciaId
	 * @return
	 */
	int countTopicsBySpeciaId(Long speciaId);
}
