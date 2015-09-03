package com.hannuus.gamble.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hannuus.gamble.comm.R;
import com.hannuus.gamble.model.GlobalParams;
import com.hannuus.gamble.web.service.GlobalParamsService;
import com.hannuus.gamble.web.service.ThirdPartyService;

/**
 * @author cuesky
 * @date 2015年9月3日 下午9:54:02
 */
@Service
public class ThirdPartyServiceImpl implements ThirdPartyService {

	@Autowired
	GlobalParamsService globalParamsService;

	@Override
	public List<GlobalParams> findAllThirdParty() {
		List<GlobalParams> list = globalParamsService
				.findParamsByType(R.global.third_party_api);
		return list;
	}

	@Override
	public GlobalParams findThirdParty(Long id) {
		GlobalParams params = globalParamsService.findGlobalParams(id);
		return params;
	}

	@Override
	public void updateThirdParty(GlobalParams params) {
		globalParamsService.updateGlobalParams(params);
	}

	@Override
	public boolean isThirdPartyApiEnabled(String key) {
		GlobalParams party = globalParamsService.findParamsByTypeAndKey(
				R.global.third_party_api, key);
		if (party == null) {
			return false;
		}
		// 0-false 1-true
		if ("0".equals(party.getValue())) {
			return false;
		}
		return true;
	}

}
