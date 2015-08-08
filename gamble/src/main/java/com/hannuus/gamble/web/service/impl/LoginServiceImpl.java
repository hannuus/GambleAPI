package com.hannuus.gamble.web.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.hannuus.core.AESHelper;
import com.hannuus.gamble.web.service.LoginService;
import com.hannuus.gamble.web.service.UserService;
import com.hannuus.model.gamble.AccessToken;
import com.hannuus.model.gamble.User;

@Service
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	UserService userService;
	
	@Override
	public User getUserByAccessToken(String accessToken) throws Exception {
		Long userId = getUserIdByAccessToken(accessToken);
		return userService.findUserById(userId);
	}

	private Long getUserIdByAccessToken(String accessToken) throws Exception {
		AccessToken token = new Gson().fromJson(AESHelper.decrypt(accessToken), AccessToken.class);
		if (null != token) {
			return Long.valueOf(token.getId());
		}
		return null;
	}

	@Override
	public AccessToken userLogin(String userName, String password) throws Exception {
		User user = login(userName, password);
		if (null != user) {
			return getAccessTokenByUser(user);
		}
		return null;
	}

	private User login(String userName, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	private AccessToken getAccessTokenByUser(User user) throws Exception {
		AccessToken token = new AccessToken();
		token.setId(user.getId().toString());
		token.setTimestamp(String.valueOf(new Date().getTime()));
		return token;
	}

}
