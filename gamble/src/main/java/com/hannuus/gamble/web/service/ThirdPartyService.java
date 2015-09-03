package com.hannuus.gamble.web.service;

/**
 * 第三方服务API
 * 
 * @author cuesky
 * @date 2015年9月3日 上午10:57:18
 */
public interface ThirdPartyService {

	/**
	 * 查询某第三方API是否可用
	 * 
	 * @param key
	 *            param名称
	 * @return
	 * @see com.hannuus.gamble.comm.R.global
	 */
	public boolean isThirdPartyApiEnabled(String key);

}
