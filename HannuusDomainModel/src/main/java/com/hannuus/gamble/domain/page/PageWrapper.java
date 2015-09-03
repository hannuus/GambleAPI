package com.hannuus.gamble.domain.page;

/**
 * Pagination封送接口
 * 
 * @author cuesky
 * @date 2015年9月1日 上午11:06:43
 */
public interface PageWrapper {

	/**
	 * @param pageNum
	 *            页码
	 * @param pageSize
	 *            页面大小
	 * @return
	 */
	PageParams wrapPageParams(int pageNum, int pageSize);

	/**
	 * @param model
	 *            域模型，可为HttpServletRequest/ModelMap等
	 * @param dto
	 *            页面数据项
	 */
	<T> void wrapPageDTO(Object model, PageDTO<T> page);

}
