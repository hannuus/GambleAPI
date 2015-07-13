package com.hannuus.gamble.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hannuus.gamble.bean.Province;
import com.hannuus.gamble.bean.ProvinceExample;
import com.hannuus.gamble.dao.ProvinceMapper;
import com.hannuus.gamble.web.service.IBaseInfoService;

@Service
public class BaseInfoServiceImpl implements IBaseInfoService {

	@Autowired
	ProvinceMapper provinceMapper;
	
	@Override
	public List<Province> getAllProvinceList() {
		ProvinceExample ex = new ProvinceExample();
		ex.createCriteria();
		return provinceMapper.selectByExample(ex);
	}
}
