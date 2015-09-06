package com.hannuus.gamble.web.service;

import com.hannuus.gamble.domain.AccessToken;
import com.hannuus.gamble.model.User;

/**
 * 测试完毕后再移植回来
 * 
 * @author aelns
 * @date 2015年9月6日 下午2:35:23
 * @see com.hannuus.gamble.web.service.CustomService
 */
@Deprecated
public interface LoginService {

	/**
	 * 根据accessToken获取用户信息
	 * 
	 * @param accessToken
	 * @return
	 * @throws Exception
	 */
	User getUserByAccessToken(String accessToken);

	/**
	 * 用户登录, 需要确认client传递时是否是加密过的参数, 如果是, 则需要进行相应的解密 登录成功返回一个accessToken
	 * 
	 * @param userName
	 * @param password
	 * @return AccessToken
	 * @throws Exception
	 */
	AccessToken userLogin(String userName, String password);
}
