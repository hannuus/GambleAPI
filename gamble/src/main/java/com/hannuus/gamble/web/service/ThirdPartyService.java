package com.hannuus.gamble.web.service;

import java.util.List;

import com.hannuus.gamble.model.GlobalParams;

/**
 * 第三方服务API，提供如下功能：<br>
 * 1.自身维护(CRUD，其中D仍保留在GlobalParamsService)<br>
 * 2.检测第三方API是否可用
 * 
 * @author cuesky
 * @date 2015年9月3日 下午9:01:45
 * @see com.hannuus.gamble.web.service.GlobalParamsService
 */
public interface ThirdPartyService {

	// self-maintenance=================================================

	/**
	 * 查询第三方API列表
	 * 
	 * @return
	 */
	public List<GlobalParams> findAllThirdParty();

	/**
	 * 根据GlobalParams Id查询一条记录
	 * 
	 * @param id
	 *            GlobalParams Id
	 * @return
	 */
	public GlobalParams findThirdParty(Long id);

	/**
	 * 更新第三方API设置
	 * 
	 * @param party
	 */
	public void updateThirdParty(GlobalParams party);

	// for other client=================================================
	/**
	 * 查询某第三方API是否可用
	 * 
	 * @param key
	 *            第三方API的key名
	 * @return
	 * @see com.hannuus.gamble.comm.R.global
	 */
	public boolean isThirdPartyApiEnabled(String key);

}
