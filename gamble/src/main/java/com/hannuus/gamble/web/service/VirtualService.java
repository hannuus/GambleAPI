package com.hannuus.gamble.web.service;

import com.hannuus.gamble.domain.page.PageDTO;
import com.hannuus.gamble.domain.page.PageParams;
import com.hannuus.gamble.model.User;

/**
 * 虚拟服务接口
 * 
 * @author cuesky
 * @date 2015年9月18日下午5:10:36
 */
public interface VirtualService {

	/**
	 * 分页查询所有虚拟用户数据
	 * 
	 * @param params
	 *            分页参数
	 * @return
	 */
	PageDTO<User> findUserPage(PageParams params);

	/**
	 * 根据seed模糊查询userName是否存在
	 * 
	 * @param seed
	 * @return
	 */
	boolean isSeedAvailable(String seed);

	/**
	 * 批量新增虚拟用户
	 * 
	 * @param seed
	 *            用户名种子
	 * @param num
	 *            新增数量
	 */
	void batchAddeUsers(String seed, int num);

}
