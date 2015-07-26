package com.hannuus.gamble.web.service;

import java.util.List;

import com.hannuus.gamble.bean.UserRelation;
import com.hannuus.gamble.web.dto.UserInfoDTO;

public interface IUserRelationService {
	
	/**
	 * 查找好友列表, 彼此互相关注的人
	 * @param userId
	 * @return
	 */
	List<UserInfoDTO> findFriendsListByPage(Long userId, int pageNumber, int pageSize);
	
	/**
	 * 查找关注列表, 我关注的人
	 * @param userId
	 * @return
	 */
	List<UserInfoDTO> findFollowListByPage(Long userId, int pageNumber, int pageSize);
	
	/**
	 * 查找粉丝, 关注我的人
	 * @param userId
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	List<UserInfoDTO> findFansListByPage(Long userId, int pageNumber, int pageSize);
	
	/**
	 * 添加用户关系
	 * @param userRelation
	 * @return
	 */
	boolean addUserRelation(UserRelation userRelation);
	
	/**
	 * 删除用户关系, 不物理删除, 将状态置为-1
	 * @param relationId
	 * @return
	 */
	boolean deleteUserRelation(Long relationId);
	
	/**
	 * 更新用户关系
	 * @param userRelation
	 * @return
	 */
	boolean updateRelation(UserRelation userRelation);
	
	/**
	 * 根据ID查找
	 * @param relationId
	 * @return
	 */
	UserRelation findById(Long relationId);
}
