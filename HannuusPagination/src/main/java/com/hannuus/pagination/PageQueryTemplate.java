package com.hannuus.pagination;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import com.hannuus.gamble.comm.R;

/**
 * 分页查询模板
 * 
 * @author cuesky
 * @date 2015年9月1日 下午1:29:06
 */
@Component
public class PageQueryTemplate {

	private Logger logger = Logger.getLogger(getClass());

	/**
	 * 执行分页查询
	 * 
	 * @param pageNum
	 *            页码
	 * @param pageSize
	 *            页面大小
	 * @param model
	 *            域模型
	 * @param callback
	 *            查询回调
	 */
	public <T> PageDTO<T> execute(int pageNum, int pageSize, Object model,
			PageQueryCallback<T> callback) {
		PageParams params = preHandle(pageNum, pageSize);
		PageDTO<T> page = callback.query(params);
		postHandle(model, page);
		return page;
	}

	/**
	 * 执行分页查询
	 * 
	 * @param pageNum
	 *            页码
	 * @param pageSize
	 *            页面大小
	 * @param model
	 *            域模型
	 * @param callback
	 *            查询回调
	 * @param pageWrapper
	 *            自定义分页包装器
	 */
	public <T> void execute(int pageNum, int pageSize, Object model,
			PageQueryCallback<T> callback, PageWrapper pageWrapper) {
		defaultPageWrapper = pageWrapper;
		execute(pageNum, pageSize, model, callback);
	}

	/**
	 * 分页参数预处理
	 * 
	 * @param pageNum
	 *            页码
	 * @param pageSize
	 *            页面大小
	 * @return 分页参数
	 */
	private PageParams preHandle(int pageNum, int pageSize) {
		PageParams params = defaultPageWrapper
				.wrapPageParams(pageNum, pageSize);
		return params;
	}

	/**
	 * 分页后的数据封送
	 * 
	 * @param model
	 *            域模型
	 * @param page
	 *            分页数据项
	 */
	private <T> void postHandle(Object model, PageDTO<T> page) {
		defaultPageWrapper.wrapPageDTO(model, page);
	}

	/** 默认分页包装器 */
	private PageWrapper defaultPageWrapper = new PageWrapper() {

		public PageParams wrapPageParams(int pageNum, int pageSize) {
			PageParams params = new PageParams(pageNum, pageSize);
			logger.debug(params);
			return params;
		}

		public <T> void wrapPageDTO(Object model, PageDTO<T> page) {
			if (model != null) {
				ModelMap map = (ModelMap) model;
				map.put(R.page.attr_key, page);
				logger.debug(page);
			}
		}

	};

}
