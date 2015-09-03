package com.hannuus.gamble.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hannuus.gamble.comm.R;
import com.hannuus.gamble.dao.GlobalParamsMapper;
import com.hannuus.gamble.model.GlobalParams;
import com.hannuus.gamble.model.GlobalParamsExample;
import com.hannuus.gamble.web.service.ThirdPartyService;

@Service
public class ThirdPartyServiceImpl implements ThirdPartyService {

	@Autowired
	GlobalParamsMapper globalParamsMapper;

	@Override
	public boolean isThirdPartyApiEnabled(String key) {
		GlobalParamsExample example = new GlobalParamsExample();
		example.createCriteria().andTypeNameEqualTo(R.global.third_party_api)
				.andKeyEqualTo(key);
		List<GlobalParams> list = globalParamsMapper.selectByExample(example);
		if (list == null || list.size() == 0) {
			return false;
		}
		GlobalParams globalParams = list.get(0);
		// "0"-false "1"-true
		String value = globalParams.getValue();
		if ("0".equals(value)) {
			return false;
		}
		return true;
	}

}
