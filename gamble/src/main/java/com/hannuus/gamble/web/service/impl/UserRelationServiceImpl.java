package com.hannuus.gamble.web.service.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.hannuus.gamble.comm.UserRelationType;
import com.hannuus.gamble.dao.UserRelationMapper;
import com.hannuus.gamble.model.User;
import com.hannuus.gamble.model.UserRelation;
import com.hannuus.gamble.model.UserRelationExample;
import com.hannuus.gamble.model.UserRelationExample.Criteria;
import com.hannuus.gamble.web.service.UserRelationService;
import com.hannuus.gamble.web.service.UserService;

@Service
public class UserRelationServiceImpl implements UserRelationService {
	
	@Autowired
	UserRelationMapper userRelationMapper;
	
	@Autowired
	UserService userService;
	
	@Override
	public List<User> findFriendsListByPage(Long userId, int pageNumber,
			int pageSize) {
		Collection<Long> friendsIds = getFriendsIds(userId);
		return userService.findUsersByPage(Lists.newArrayList(friendsIds), pageNumber, pageSize);
	}

	@Override
	public List<User> findFollowListByPage(Long userId, int pageNumber,
			int pageSize) {
		UserRelationExample userRelationexample = new UserRelationExample();
		Criteria criteria = userRelationexample.createCriteria();
		criteria.andFromIdEqualTo(userId);
		criteria.andRelationTypeEqualTo(UserRelationType.Follow.value());
		// set pagination info
		int pageIndex = 0;
		pageIndex = (pageNumber - 1) * pageSize;
		userRelationexample.setLimitStart(pageIndex);
		userRelationexample.setLimitEnd(pageSize);
		// find my follow relationship
		List<UserRelation> myFollowList = userRelationMapper.selectByExample(userRelationexample);
		if (CollectionUtils.isNotEmpty(myFollowList)) {
			List<Long> userIds = Lists.newArrayList();
			for (UserRelation userRelation : myFollowList) {
				userIds.add(userRelation.getToId());
			}
			// find follow list user detail
			return userService.findUserByIds(userIds);
		}
		return Collections.emptyList();
	}

	@Override
	public List<User> findFansListByPage(Long userId, int pageNumber,
			int pageSize) {
		UserRelationExample userRelationexample = new UserRelationExample();
		Criteria criteria = userRelationexample.createCriteria();
		criteria.andToIdEqualTo(userId);
		criteria.andRelationTypeEqualTo(UserRelationType.Follow.value());
		// set pagination info
		int pageIndex = 0;
		pageIndex = (pageNumber - 1) * pageSize;
		userRelationexample.setLimitStart(pageIndex);
		userRelationexample.setLimitEnd(pageSize);
		// find my fans relationship
		List<UserRelation> myFansList = userRelationMapper.selectByExample(userRelationexample);
		if (CollectionUtils.isNotEmpty(myFansList)) {
			List<Long> userIds = Lists.newArrayList();
			for (UserRelation userRelation : myFansList) {
				userIds.add(userRelation.getToId());
			}
			// find fans user detail
			return userService.findUserByIds(userIds);
		}
		return Collections.emptyList();
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
		relation.setRelationType(UserRelationType.Follow.value());
		return userRelationMapper.insert(relation) > 0;
	}

	@Override
	public boolean cancelFollow(Long fromId, Long toId) {
		UserRelationExample example = new UserRelationExample();
		example.createCriteria().andFromIdEqualTo(fromId).andToIdEqualTo(toId)
				.andRelationTypeEqualTo(UserRelationType.Follow.value());
		return userRelationMapper.deleteByExample(example) > 0;
	}

	@Override
	public boolean cancelFollows(Long fromId, List<Long> toIds) {
		UserRelationExample example = new UserRelationExample();
		example.createCriteria().andFromIdEqualTo(fromId).andToIdIn(toIds)
				.andRelationTypeEqualTo(UserRelationType.Follow.value());
		return userRelationMapper.deleteByExample(example) > 0;
	}

	@Override
	public boolean addFollows(Long fromId, List<Long> toIds) {
		for (Long toId : toIds) {
			addFollow(fromId, toId);
		}
		return true;
	}

	@Override
	public int countFriends(Long userId) {
		Collection<Long> friendsIds = getFriendsIds(userId);
		return friendsIds.size();
	}

	private Collection<Long> getFriendsIds(Long userId) {
		UserRelationExample followRelationexample = new UserRelationExample();
		Criteria followCriteria = followRelationexample.createCriteria();
		followCriteria.andFromIdEqualTo(userId);
		followCriteria.andRelationTypeEqualTo(UserRelationType.Follow.value());
		List<UserRelation> myFollowList = userRelationMapper.selectByExample(followRelationexample);
		List<Long> followIds = Lists.newArrayList();
		if (CollectionUtils.isNotEmpty(myFollowList)) {
			for (UserRelation userRelation : myFollowList) {
				followIds.add(userRelation.getToId());
			}
		}
		// find fans Ids
		UserRelationExample fansRelationexample = new UserRelationExample();
		Criteria fansCriteria = fansRelationexample.createCriteria();
		fansCriteria.andToIdEqualTo(userId);
		fansCriteria.andRelationTypeEqualTo(UserRelationType.Follow.value());
		List<UserRelation> myFansList = userRelationMapper.selectByExample(fansRelationexample);
		List<Long> fansIds = Lists.newArrayList();
		if (CollectionUtils.isNotEmpty(myFansList)) {
			for (UserRelation userRelation : myFansList) {
				fansIds.add(userRelation.getToId());
			}
		}
		Collection<Long> friendsIds = getFriendsIds(userId);
		return friendsIds;
	}

	@Override
	public int countFollows(Long userId) {
		UserRelationExample userRelationexample = new UserRelationExample();
		Criteria criteria = userRelationexample.createCriteria();
		criteria.andFromIdEqualTo(userId);
		criteria.andRelationTypeEqualTo(UserRelationType.Follow.value());
		return userRelationMapper.countByExample(userRelationexample);
	}

	@Override
	public int countFans(Long userId) {
		UserRelationExample userRelationexample = new UserRelationExample();
		Criteria criteria = userRelationexample.createCriteria();
		criteria.andToIdEqualTo(userId);
		criteria.andRelationTypeEqualTo(UserRelationType.Follow.value());
		return userRelationMapper.countByExample(userRelationexample);
	}
}
