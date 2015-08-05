package com.hannuus.gamble.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.hannuus.gamble.bean.User;
import com.hannuus.gamble.web.service.IAccessTokenService;
import com.hannuus.gamble.web.service.IUserService;

public class AccessTokenServiceImpl implements IAccessTokenService {
	
	@Autowired
	IUserService userService;
	
	@Override
	public User getUserByAccessToken(String accessToken) {
		Long userId = getUserIdByAccessToken(accessToken);
		return userService.findUserById(userId);
	}

	private Long getUserIdByAccessToken(String accessToken) {
		// TODO cuesky
		return null;
	}

}
