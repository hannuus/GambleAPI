package com.hannuus.gamble.web.service;

import java.io.InputStream;

import com.hannuus.gamble.domain.page.PageDTO;
import com.hannuus.gamble.domain.page.PageParams;
import com.hannuus.gamble.model.Topic;
import com.hannuus.gamble.model.User;

/**
 * 运营API
 * 
 * @author cuesky
 * @date 2015年9月21日下午4:27:19
 */
public interface OperateService {

	/**
	 * 分页查询所有运营人员信息
	 * 
	 * @param params
	 *            分页参数
	 * @return
	 */
	PageDTO<User> findOperPage(PageParams params);

	/**
	 * 根据ID查询一条运营人员记录
	 * 
	 * @param id
	 * @return
	 */
	User findOperById(Long id);

	/**
	 * 新增或更新运营人员资料
	 * 
	 * @param user
	 */
	void saveOrUpdateOper(User user);

	/**
	 * 根据ID删除一条运营人员记录
	 * 
	 * @param id
	 */
	void deleteOper(Long id);

	/**
	 * 分页查询虚拟帖子
	 * 
	 * @param params
	 * @return
	 */
	PageDTO<Topic> findTopicPage(PageParams params);

	/**
	 * 批量新增主题
	 * 
	 * @param in
	 * @return
	 */
	void batchAddTopics(InputStream in) throws Exception;

}
