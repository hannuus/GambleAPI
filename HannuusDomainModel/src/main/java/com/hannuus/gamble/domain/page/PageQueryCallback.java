package com.hannuus.gamble.domain.page;

/**
 * 分页回调<br>
 * 用于执行service查询接口
 * 
 * @author cuesky
 * @date 2015年9月1日 下午2:23:31
 * @param <T>
 *            Domain Class
 */
public interface PageQueryCallback<T> {

	/**
	 * 分页查询
	 * 
	 * @param params
	 *            分页参数
	 * @return 分页数据项
	 */
	PageDTO<T> query(PageParams params);

}
