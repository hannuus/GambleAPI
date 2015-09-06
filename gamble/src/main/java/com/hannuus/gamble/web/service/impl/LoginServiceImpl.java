package com.hannuus.gamble.web.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.hannuus.core.utils.AESHelper;
import com.hannuus.gamble.dao.UserMapper;
import com.hannuus.gamble.domain.AccessToken;
import com.hannuus.gamble.model.User;
import com.hannuus.gamble.web.service.LoginService;
import com.hannuus.gamble.web.service.UserService;

/**
 * 测试完毕后再移植回来
 * 
 * @author aelns
 * @date 2015年9月6日 下午2:34:12
 * @see com.hannuus.gamble.web.service.impl.CustomServiceImpl
 */
@Service
@Deprecated
public class LoginServiceImpl implements LoginService {

	@Autowired
	UserService userService;
	// TEST
	@Autowired
	UserMapper userMapper;

	@Override
	public User getUserByAccessToken(String accessToken) {
		Long userId = getUserIdByAccessToken(accessToken);
		return userService.findUserById(userId);
	}

	private Long getUserIdByAccessToken(String accessToken) {
		AccessToken token = new Gson().fromJson(AESHelper.decrypt(accessToken),
				AccessToken.class);
		if (null != token) {
			return Long.valueOf(token.getId());
		}
		return null;
	}

	@Override
	public AccessToken userLogin(String userName, String password) {
		User user = login(userName, password);
		if (null != user) {
			return getAccessTokenByUser(user);
		}
		return null;
	}

	private User login(String userName, String password) {
		// TODO 比如你有两个手机， 但是你是一个用户， 推送消息时需要两台设备都要推送， userId和channelID是一对多的关系

		// shiro test case
		User user = userMapper.checkLogin(userName, password);

		return user;
	}

	private AccessToken getAccessTokenByUser(User user) {
		AccessToken token = new AccessToken();
		token.setId(user.getId().toString());
		token.setTimestamp(String.valueOf(new Date().getTime()));
		return token;
	}

}
