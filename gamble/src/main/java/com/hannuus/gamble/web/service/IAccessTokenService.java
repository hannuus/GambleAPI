package com.hannuus.gamble.web.service;

import com.hannuus.gamble.bean.User;

public interface IAccessTokenService {
	
	User getUserByAccessToken(String accessToken);
}
