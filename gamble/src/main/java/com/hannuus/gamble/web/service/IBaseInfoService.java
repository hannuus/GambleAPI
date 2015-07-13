package com.hannuus.gamble.web.service;

import java.util.List;

import com.hannuus.gamble.bean.Province;


public interface IBaseInfoService {
	
	/**
	 * get all province info
	 * @return
	 */
	List<Province> getAllProvinceList();
}
