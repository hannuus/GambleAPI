package com.hannuus.gamble.web.service;

import java.util.List;

import com.hannuus.gamble.bean.User;
import com.hannuus.gamble.bean.UserRelation;

public interface IUserRelationService {
	
	/**
	 * 查找好友列表, 彼此互相关注的人
	 * @param userId
	 * @return
	 */
	List<User> findFriendsListByPage(Long userId, int pageNumber, int pageSize);
	
	/**
	 * 查找关注列表, 我关注的人
	 * @param userId
	 * @return
	 */
	List<User> findFollowListByPage(Long userId, int pageNumber, int pageSize);
	
	/**
	 * 查找粉丝, 关注我的人
	 * @param userId
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	List<User> findFansListByPage(Long userId, int pageNumber, int pageSize);
	
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
	 * 更新用户关系, 应该不需要此接口
	 * @param userRelation
	 * @return
	 */
	boolean updateRelation(UserRelation userRelation);
	
	/**
	 * 根据ID查找
	 * @param relationId
	 * @return
	 */
	UserRelation findRelationById(Long relationId);
	
	/**
	 * 添加关注
	 * @param fromId
	 * @param toid
	 * @return
	 */
	boolean addFollow(Long fromId, Long toId);
	
	/**
	 * 批量添加关注
	 * @param fromId
	 * @param toIds
	 * @return
	 */
	boolean addFollows(Long fromId, List<Long> toIds);
	
	/**
	 * 取消关注
	 * @param fromId
	 * @param toId
	 * @return
	 */
	boolean cancelFollow(Long fromId, Long toId);
	
	/**
	 * 批量取消关注
	 * @param fromId
	 * @param toIds
	 * @return
	 */
	boolean cancelFollows(Long fromId, List<Long> toIds);
	
	
}
