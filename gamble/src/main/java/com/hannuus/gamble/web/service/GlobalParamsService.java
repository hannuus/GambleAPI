package com.hannuus.gamble.web.service;

import java.util.List;

import com.hannuus.gamble.model.GlobalParams;
import com.hannuus.pagination.PageDTO;
import com.hannuus.pagination.PageParams;

/**
 * 全局参数API
 * 
 * @author cuesky
 * @date 2015年9月3日 上午11:15:11
 */
public interface GlobalParamsService {

	/**
	 * 查询所有全局参数
	 * 
	 * @param params
	 *            分页参数
	 * @return
	 */
	public PageDTO<GlobalParams> findGlobalParamsPage(PageParams params);

	/**
	 * 根据typeValue查询所有params
	 * 
	 * @param typeValue
	 * @return
	 */
	public List<GlobalParams> findParamsByType(String typeValue);

	/**
	 * 根据typeValue和key查询一条GlobalParams
	 * 
	 * @param typeValue
	 * @param key
	 * @return
	 */
	public GlobalParams findParamsByTypeAndKey(String typeValue, String key);

	/**
	 * 查询所有参数类型
	 * 
	 * @return
	 */
	public List<GlobalParams> findParamsTypes();

	/**
	 * 判断该参数类型是否存在
	 * 
	 * @param params
	 * @return
	 */
	public boolean isTypeExists(GlobalParams params);

	/**
	 * 添加一个全局参数
	 * 
	 * @param params
	 */
	public void addGlobalParams(GlobalParams params);

	/**
	 * 判断该参数是否存在
	 * 
	 * @param params
	 * @return
	 */
	public boolean isParamsExists(GlobalParams params);

	/**
	 * 根据ID查询
	 * 
	 * @param id
	 * @return
	 */
	public GlobalParams findGlobalParams(Long id);

	/**
	 * 更新全局参数类型<br>
	 * 根据params.id更新类型<br>
	 * 根据old更新类型下所有的值
	 * 
	 * @param oldTypeValue
	 * @param params
	 */
	public void updateGlobalParamsType(String oldType_name,
			String oldTypeValue, GlobalParams params);

	/**
	 * 更新全局参数
	 * 
	 * @param params
	 */
	public void updateGlobalParams(GlobalParams params);

	/**
	 * 根据typeName和typeValue进行清理
	 * 
	 * @param params
	 */
	public void deleteGlobalParamsType(GlobalParams params);

	/**
	 * 根据ID删除一条GlobalParams
	 * 
	 * @param id
	 */
	public void deleteGlobalParams(Long id);

}
