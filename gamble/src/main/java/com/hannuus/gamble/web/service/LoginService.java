package com.hannuus.gamble.web.service;

import com.hannuus.domain.gamble.AccessToken;
import com.hannuus.model.gamble.User;


public interface LoginService {
	
	/**
	 * 根据accessToken获取用户信息
	 * @param accessToken
	 * @return
	 * @throws Exception
	 */
	User getUserByAccessToken(String accessToken) throws Exception;
	
	/**
	 * 用户登录, 需要确认client传递时是否是加密过的参数, 如果是, 则需要进行相应的解密
	 * 登录成功返回一个accessToken
	 * @param userName
	 * @param password
	 * @return AccessToken
	 * @throws Exception 
	 */
	AccessToken userLogin(String userName, String password) throws Exception;
}
