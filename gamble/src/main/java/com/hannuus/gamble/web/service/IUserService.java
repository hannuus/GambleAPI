package com.hannuus.gamble.web.service;

import java.util.List;

import com.hannuus.gamble.bean.User;
import com.hannuus.gamble.web.exception.api.NotFoundAnyDataException;
import com.hannuus.gamble.web.exception.api.UserPointsNotEnoughException;

public interface IUserService {
	
	/**
	 * 新增用户
	 * @param user
	 * @return
	 */
	boolean addUser(User user);
	
	/**
	 * 更新用户的信息
	 * @param user
	 * @return
	 */
	boolean updateUser(User user);
	
	/**
	 * 删除一个用户, 不物理删除, 将用户的状态设置为-1
	 * @param userId
	 * @return
	 */
	boolean deleteUser(Long userId);
	
	/**
	 * 根据用户ID查询
	 * @param userId
	 * @return
	 */
	User findUserById(Long userId);
	
	/**
	 * 分页查询用户信息
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	List<User> findUserByPage(int pageNumber, int pageSize);
	
	/**
	 * 按照一定的条件分页查询用户信息
	 * @param conditions
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	List<User> findUserByPageWithConditions(User conditions, int pageNumber, int pageSize);
	
	/**
	 * 修改昵称
	 * @param userId
	 * @param newNickName
	 * @return
	 */
	boolean updateNickName(Long userId, String newNickName);
	
	/**
	 * 更新用户头像
	 * @param userId
	 * @param imageURL
	 * @return
	 */
	boolean updateHeadURL(Long userId, String imageURL);
	
	/**
	 * 更新个性签名, 备注说明?
	 * @param userId
	 * @param introduction
	 * @return
	 */
	boolean updateIntroduction(Long userId, String introduction);
	
	/**
	 * 修改用户密码
	 * @param userId
	 * @param oldPassword
	 * @param newPassword
	 * @return
	 */
	boolean updatePassword(Long userId, String oldPassword, String newPassword);
	
	/**
	 * 绑定邮箱
	 * @param userId
	 * @param email
	 * @param code 验证码
	 * @return
	 */
	boolean bindEmail(Long userId, String email, String code);
	
	/**
	 * 绑定手机
	 * @param userId
	 * @param mobile
	 * @param code 验证码
	 * @return
	 */
	boolean bindMobile(Long userId, Long mobile, String code);
	
	/**
	 * 更新积分, 如果amount为负数, 即为扣除积分
	 * @param userId
	 * @param point
	 * @return
	 */
	boolean updateUserAmount(Long userId, int amount)  throws UserPointsNotEnoughException;
	
	/**
	 * 锁定用户, 修改用户的状态, 用户将不能进行任何操作
	 * @param userId
	 * @return
	 */
	boolean lockUser(Long userId);
	
	/**
	 * 查询用户是否被锁定
	 * @param userId
	 * @return
	 */
	boolean isLock(Long userId) throws NotFoundAnyDataException;
	
	/**
	 * 更新用户状态
	 * @param userId
	 * @param state
	 * @return
	 */
	boolean updateUserState(Long userId, int state);
}
