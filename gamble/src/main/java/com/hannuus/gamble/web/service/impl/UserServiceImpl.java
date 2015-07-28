package com.hannuus.gamble.web.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.hannuus.gamble.bean.User;
import com.hannuus.gamble.bean.UserExample;
import com.hannuus.gamble.comm.SystemConstants;
import com.hannuus.gamble.comm.UserStates;
import com.hannuus.gamble.dao.UserMapper;
import com.hannuus.gamble.web.exception.api.NotFoundAnyDataException;
import com.hannuus.gamble.web.exception.api.UserPointsNotEnoughException;
import com.hannuus.gamble.web.service.IUserService;

public class UserServiceImpl implements IUserService {
	
	@Autowired
	UserMapper userMapper;
	
	@Override
	public boolean addUser(User user) {
		return userMapper.insert(user) > 0;
	}

	@Override
	public boolean updateUser(User user) {
		return userMapper.updateByPrimaryKeySelective(user) > 0;
	}

	@Override
	public boolean deleteUser(Long userId) {
		return updateUserState(userId, UserStates.Willdelete.getValue());
	}

	@Override
	public User findUserById(Long userId) {
		return userMapper.selectByPrimaryKey(userId);
	}

	@Override
	public List<User> findUserByPage(int pageNumber, int pageSize) {
		UserExample example = new UserExample();
		int pageIndex = 0;
		pageIndex = (pageNumber - 1) * SystemConstants.DEFAULT_PAGE_SIZE;
		example.setLimitStart(pageIndex);
		example.setLimitEnd(pageSize);
		return userMapper.selectByExample(example);
	}

	@Override
	public List<User> findUserByPageWithConditions(User conditions,
			int pageNumber, int pageSize) {
		UserExample example = new UserExample();
		if (StringUtils.isNotBlank(conditions.getNickName())) {
			example.createCriteria().andNickNameLike(conditions.getNickName());
		}
		// TODO more conditions ?
		return userMapper.selectByExample(example);
	}

	@Override
	public boolean updateNickName(Long userId, String newNickName) {
		User user = new User();
		user.setId(userId);
		user.setNickName(newNickName);
		return userMapper.updateByPrimaryKeySelective(user) > 0;
	}

	@Override
	public boolean updateHeadURL(Long userId, String imageURL) {
		User user = new User();
		user.setId(userId);
		user.setHeadUrl(imageURL);
		return userMapper.updateByPrimaryKeySelective(user) > 0;
	}

	@Override
	public boolean updateIntroduction(Long userId, String introduction) {
		User user = new User();
		user.setId(userId);
		user.setIntroduction(introduction);
		return userMapper.updateByPrimaryKeySelective(user) > 0;
	}

	@Override
	public boolean updatePassword(Long userId, String oldPassword,
			String newPassword) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateUserAmount(Long userId, int amount) throws UserPointsNotEnoughException {
		User user = findUserById(userId);
		long off = user.getAmount() + amount;
		if (off < 0) {
			throw new UserPointsNotEnoughException();
		}
		user.setAmount(off);
		return userMapper.updateByPrimaryKeySelective(user) > 0;
	}

	@Override
	public boolean lockUser(Long userId) {
		User user = new User();
		user.setState(UserStates.Locked.getValue());
		return userMapper.updateByPrimaryKeySelective(user) > 0;
	}

	@Override
	public boolean isLock(Long userId) throws NotFoundAnyDataException {
		User user = findUserById(userId);
		if (null == user) {
			throw new NotFoundAnyDataException();
		}
		return user.getState() != UserStates.Locked.getValue();
	}

	@Override
	public boolean bindEmail(Long userId, String email, String code) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean bindMobile(Long userId, Long mobile, String code) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateUserState(Long userId, int state) {
		User user = new User();
		user.setId(userId);
		user.setState(state);
		return userMapper.updateByPrimaryKeySelective(user) > 0;
	}

}
