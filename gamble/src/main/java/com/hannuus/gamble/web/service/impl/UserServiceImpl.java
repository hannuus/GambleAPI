package com.hannuus.gamble.web.service.impl;

import java.util.List;

import com.hannuus.gamble.bean.User;
import com.hannuus.gamble.web.service.IUserService;

public class UserServiceImpl implements IUserService {

	@Override
	public boolean addUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteUser(Long userId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User findUserById(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findUserByPage(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findUserByPageWithConditions(User conditions,
			int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateNickName(Long userId, String newNickName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateHeadURL(Long userId, String imageURL) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateIntroduction(Long userId, String introduction) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updatePassword(Long userId, String oldPassword,
			String newPassword) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean bindEmail(Long userId, String emai) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean bindMobile(Long userId, Long mobile) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateUserAmount(Long userId, int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean lockUser(Long userId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isLock(Long userId) {
		// TODO Auto-generated method stub
		return false;
	}

}
