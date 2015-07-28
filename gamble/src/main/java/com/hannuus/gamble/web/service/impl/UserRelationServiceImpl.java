package com.hannuus.gamble.web.service.impl;

import java.util.List;

import com.hannuus.gamble.bean.UserRelation;
import com.hannuus.gamble.web.dto.UserInfoDTO;
import com.hannuus.gamble.web.service.IUserRelationService;

public class UserRelationServiceImpl implements IUserRelationService {

	@Override
	public List<UserInfoDTO> findFriendsListByPage(Long userId, int pageNumber,
			int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserInfoDTO> findFollowListByPage(Long userId, int pageNumber,
			int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserInfoDTO> findFansListByPage(Long userId, int pageNumber,
			int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addUserRelation(UserRelation userRelation) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteUserRelation(Long relationId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateRelation(UserRelation userRelation) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public UserRelation findRelationById(Long relationId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addFollow(Long fromId, Long toId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean cancelFollow(Long fromId, Long toId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean cancelFollows(Long fromId, List<Long> toIds) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addFollows(Long fromId, List<Long> toIds) {
		// TODO Auto-generated method stub
		return false;
	}

}
