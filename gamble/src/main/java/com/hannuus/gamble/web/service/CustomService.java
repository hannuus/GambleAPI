package com.hannuus.gamble.web.service;

import java.util.List;

import com.hannuus.gamble.model.BlackList;
import com.hannuus.gamble.model.User;

/**
 * 用户设置接口
 * 
 * @author cuesky
 * @date 2015年9月4日 下午3:47:31
 */
public interface CustomService {

	// 帐号密码设置===================================================================

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

	// 无图模式=======================================================================

}
