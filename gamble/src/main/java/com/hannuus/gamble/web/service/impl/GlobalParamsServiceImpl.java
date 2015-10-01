package com.hannuus.gamble.web.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hannuus.gamble.dao.GlobalParamsMapper;
import com.hannuus.gamble.model.GlobalParams;
import com.hannuus.gamble.model.GlobalParamsExample;
import com.hannuus.gamble.web.service.GlobalParamsService;
import com.hannuus.pagination.PageDTO;
import com.hannuus.pagination.PageParams;

/**
 * @author cuesky
 * @date 2015年9月3日 上午11:36:57
 */
@Service
public class GlobalParamsServiceImpl implements GlobalParamsService {

	@Autowired
	GlobalParamsMapper globalParamsMapper;

	@Override
	public PageDTO<GlobalParams> findGlobalParamsPage(PageParams params) {
		int total = globalParamsMapper.countByExample(null);
		GlobalParamsExample example = new GlobalParamsExample();
		example.setLimitStart(params.getStart());
		example.setLimitEnd(params.getPageSize());
		example.setOrderByClause("type_value asc");
		List<GlobalParams> list = globalParamsMapper.selectByExample(example);
		return new PageDTO<GlobalParams>(total, list);
	}

	@Override
	public List<GlobalParams> findParamsByType(String typeValue) {
		GlobalParamsExample example = new GlobalParamsExample();
		example.createCriteria().andTypeValueEqualTo(typeValue).andKeyIsNull()
				.andValueIsNull();
		List<GlobalParams> list = globalParamsMapper.selectByExample(example);
		return list;
	}

	@Override
	public GlobalParams findParamsByTypeAndKey(String typeValue, String key) {
		GlobalParamsExample example = new GlobalParamsExample();
		example.createCriteria().andTypeValueEqualTo(typeValue)
				.andKeyEqualTo(key);
		List<GlobalParams> list = globalParamsMapper.selectByExample(example);
		if (list == null || list.size() == 0) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public List<GlobalParams> findParamsTypes() {
		GlobalParamsExample example = new GlobalParamsExample();
		example.createCriteria().andKeyIsNull().andValueIsNull();
		List<GlobalParams> list = globalParamsMapper.selectByExample(example);
		return list;
	}

	@Override
	public boolean isTypeExists(GlobalParams params) {
		GlobalParamsExample example = new GlobalParamsExample();
		// typeName和typeValue只要有一个重复都不可
		example.or().andTypeNameEqualTo(params.getTypeName());
		example.or().andTypeValueEqualTo(params.getTypeValue());
		List<GlobalParams> list = globalParamsMapper.selectByExample(example);
		boolean flag = false;
		if (list != null && list.size() >= 1) {
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean isParamsExists(GlobalParams params) {
		GlobalParamsExample example = new GlobalParamsExample();
		// typeValue与key不能同时重复
		example.createCriteria().andTypeValueEqualTo(params.getTypeValue())
				.andKeyEqualTo(params.getValue());
		List<GlobalParams> list = globalParamsMapper.selectByExample(example);
		boolean flag = false;
		if (list != null && list.size() >= 1) {
			flag = true;
		}
		return flag;
	}

	@Override
	public void addGlobalParams(GlobalParams params) {
		params.setCreatedDate(new Date());
		globalParamsMapper.insertSelective(params);
	}

	@Override
	public GlobalParams findGlobalParams(Long id) {
		return globalParamsMapper.selectByPrimaryKey(id);
	}

	@Override
	public void updateGlobalParamsType(String oldType_name,
			String oldTypeValue, GlobalParams params) {
		// 更新类型
		globalParamsMapper.updateByPrimaryKey(params);
		// 更新类型下所有值
		GlobalParams record = new GlobalParams();
		record.setTypeName(params.getTypeName());
		record.setTypeValue(params.getTypeValue());
		GlobalParamsExample example = new GlobalParamsExample();
		example.createCriteria().andTypeNameEqualTo(oldType_name)
				.andTypeValueEqualTo(oldTypeValue);
		globalParamsMapper.updateByExampleSelective(record, example);
	}

	@Override
	public void updateGlobalParams(GlobalParams params) {
		globalParamsMapper.updateByPrimaryKeySelective(params);
	}

	@Override
	public void deleteGlobalParamsType(GlobalParams params) {
		GlobalParamsExample example = new GlobalParamsExample();
		example.createCriteria().andTypeNameEqualTo(params.getTypeName())
				.andTypeValueEqualTo(params.getTypeValue());
		globalParamsMapper.deleteByExample(example);
	}

	@Override
	public void deleteGlobalParams(Long id) {
		globalParamsMapper.deleteByPrimaryKey(id);
	}

}
