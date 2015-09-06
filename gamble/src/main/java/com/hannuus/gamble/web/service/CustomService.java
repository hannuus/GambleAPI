package com.hannuus.gamble.web.service;

import java.util.List;

import com.hannuus.gamble.model.BlackList;
import com.hannuus.gamble.model.User;
import com.hannuus.gamble.model.UserToken;

/**
 * 用户设置接口
 * 
 * @author cuesky
 * @date 2015年9月4日 下午3:47:31
 */
public interface CustomService {

	// 登录、注册与注销================================================================

	/**
	 * 根据userName或email查询用户是否存在
	 * 
	 * @param userToken
	 * @return
	 */
	public UserToken findUserTokenByUserNameOrEmail(UserToken userToken);

	/**
	 * 注册新用户<br>
	 * client同时使用返回的userToken做登录处理
	 * 
	 * @param userToken
	 * @return
	 */
	public UserToken regist(UserToken userToken);

	/**
	 * 登录
	 * 
	 * @param userToken
	 * @return
	 */
	public UserToken checkLogin(UserToken userToken);

	/**
	 * 使用realm登录
	 * 
	 * @param userName
	 * @param password
	 * @return
	 */
	public UserToken loginByRealm(String userName, String password);

	/**
	 * 注销
	 */
	public void logout();

	// 帐号密码设置===================================================================

	/**
	 * 根据userName查询userToken
	 * 
	 * @param userToken
	 * @return
	 */
	public UserToken findUserTokenByUserName(UserToken userToken);

	/**
	 * 使用Email发送校验回执号码
	 * 
	 * @param userToken
	 * @return
	 */
	public void sendReceipt(UserToken userToken);

	/**
	 * 修改email、手机号码、密码
	 * 
	 * @param userToken
	 * @return
	 */
	public UserToken modifyUserToken(UserToken userToken);

	/**
	 * 绑定账户(webchat/QQ/sina)
	 * 
	 * @param userToken
	 * @return
	 */
	public UserToken bandAccount(UserToken userToken);

	// 黑名单管理=====================================================================

	/**
	 * 根据userId查询该用户所有黑名单<br>
	 * 暂不使用分页查询
	 * 
	 * @param userId
	 * @return
	 */
	public List<User> findBlackList(Long userId);

	/**
	 * 添加一条黑名单记录
	 * 
	 * @param blackList
	 */
	public int addBlack(BlackList blackList);

	/**
	 * 根据id删除一条黑名单记录
	 * 
	 * @param id
	 */
	public int deleteBlack(Long id);

	/**
	 * 判断是否是黑名单<br>
	 * userId是blackUserId操作的目标<br>
	 * 
	 * @param blackList
	 * @return
	 */
	public boolean isBack(BlackList blackList);

	// 推送消息设置====================================================================

	/**
	 * client设置是否接受消息推送
	 * 
	 * @param flag
	 * @return
	 */
	public int enablePush(String flag);

	/**
	 * client是否接受消息推送
	 * 
	 * @return
	 */
	public boolean isPushEnable();

	// 无图模式=======================================================================

	/**
	 * client设置是否接受图片
	 * 
	 * @param flag
	 * @return
	 */
	public int enableImage(String flag);

	/**
	 * client是否接受图片
	 * 
	 * @return
	 */
	public boolean isImageEnable();

}
