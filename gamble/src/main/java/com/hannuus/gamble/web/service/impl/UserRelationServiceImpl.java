package com.hannuus.gamble.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hannuus.gamble.bean.UserRelation;
import com.hannuus.gamble.bean.UserRelationExample;
import com.hannuus.gamble.comm.UserRelationTypes;
import com.hannuus.gamble.dao.UserRelationMapper;
import com.hannuus.gamble.web.dto.UserInfoDTO;
import com.hannuus.gamble.web.service.IUserRelationService;

public class UserRelationServiceImpl implements IUserRelationService {
	
	@Autowired
	UserRelationMapper userRelationMapper;
	
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
		return userRelationMapper.insert(userRelation) > 0;
	}

	@Override
	public boolean deleteUserRelation(Long relationId) {
		return userRelationMapper.deleteByPrimaryKey(relationId) > 0;
	}

	@Override
	public boolean updateRelation(UserRelation userRelation) {
		return userRelationMapper.updateByPrimaryKeySelective(userRelation) > 0;
	}

	@Override
	public UserRelation findRelationById(Long relationId) {
		return userRelationMapper.selectByPrimaryKey(relationId);
	}

	@Override
	public boolean addFollow(Long fromId, Long toId) {
		UserRelation relation = new UserRelation();
		relation.setFromId(fromId);
		relation.setToId(toId);
		relation.setRelationType(UserRelationTypes.Follow.getValue());
		return userRelationMapper.insert(relation) > 0;
	}

	@Override
	public boolean cancelFollow(Long fromId, Long toId) {
		UserRelationExample example = new UserRelationExample();
		example.createCriteria().andFromIdEqualTo(fromId).andToIdEqualTo(toId)
				.andRelationTypeEqualTo(UserRelationTypes.Follow.getValue());
		return userRelationMapper.deleteByExample(example) > 0;
	}

	@Override
	public boolean cancelFollows(Long fromId, List<Long> toIds) {
		UserRelationExample example = new UserRelationExample();
		example.createCriteria().andFromIdEqualTo(fromId).andToIdIn(toIds)
				.andRelationTypeEqualTo(UserRelationTypes.Follow.getValue());
		return userRelationMapper.deleteByExample(example) > 0;
	}

	@Override
	public boolean addFollows(Long fromId, List<Long> toIds) {
		for (Long toId : toIds) {
			addFollow(fromId, toId);
		}
		return true;
	}
}
