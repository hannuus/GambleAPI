package com.hannuus.gamble.web.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hannuus.gamble.comm.R;
import com.hannuus.gamble.dao.BlackListMapper;
import com.hannuus.gamble.dao.GlobalParamsMapper;
import com.hannuus.gamble.model.BlackList;
import com.hannuus.gamble.model.BlackListExample;
import com.hannuus.gamble.model.GlobalParams;
import com.hannuus.gamble.model.GlobalParamsExample;
import com.hannuus.gamble.model.User;
import com.hannuus.gamble.web.service.CustomService;

/**
 * @author cuesky
 * @date 2015年9月4日 下午4:37:47
 */
@Service
public class CustomServiceImpl implements CustomService {

	// 帐号密码设置===================================================================

	// 黑名单管理=====================================================================

	@Autowired
	BlackListMapper blackListMapper;
	@Autowired
	GlobalParamsMapper globalParamsMapper;

	@Override
	public List<User> findBlackList(Long userId) {
		List<User> list = blackListMapper.findBlackList(userId);
		return list;
	}

	@Override
	public int addBlack(BlackList blackList) {
		return blackListMapper.insertSelective(blackList);
	}

	@Override
	public int deleteBlack(Long id) {
		return blackListMapper.deleteByPrimaryKey(id);
	}

	@Override
	public boolean isBack(BlackList blackList) {
		BlackListExample example = new BlackListExample();
		example.createCriteria().andUserIdEqualTo(blackList.getUserId())
				.andBlackUserIdEqualTo(blackList.getBlackUserId());
		List<BlackList> list = blackListMapper.selectByExample(example);
		return list.size() > 0;
	}

	// 推送消息设置====================================================================

	@Override
	public int enablePush(String flag) {
		GlobalParams record = new GlobalParams();
		GlobalParamsExample example = new GlobalParamsExample();
		example.createCriteria().andTypeValueEqualTo(R.global.client_setting)
				.andKeyEqualTo(R.global.push_key);
		if (StringUtils.isNotEmpty(flag) && R.global.enable.equals(flag.trim())) {
			record.setValue(R.global.enable);
		} else {
			record.setValue(R.global.disable);
		}
		return globalParamsMapper.updateByExampleSelective(record, example);
	}

	@Override
	public boolean isPushEnable() {
		boolean flag = true;// 默认接受
		GlobalParamsExample example = new GlobalParamsExample();
		example.createCriteria().andTypeValueEqualTo(R.global.client_setting)
				.andKeyEqualTo(R.global.push_key);
		List<GlobalParams> list = globalParamsMapper.selectByExample(example);
		if (CollectionUtils.isNotEmpty(list)
				&& list.get(0).getValue().equals(R.global.disable)) {
			flag = false;
		}
		return flag;
	}

	// 无图模式=======================================================================

	@Override
	public int enableImage(String flag) {
		GlobalParams record = new GlobalParams();
		GlobalParamsExample example = new GlobalParamsExample();
		example.createCriteria().andTypeValueEqualTo(R.global.client_setting)
				.andKeyEqualTo(R.global.image_key);
		if (!StringUtils.isEmpty(flag) && R.global.enable.equals(flag.trim())) {
			record.setValue(R.global.enable);
		} else {
			record.setValue(R.global.disable);
		}
		return globalParamsMapper.updateByExampleSelective(record, example);
	}

	@Override
	public boolean isImageEnable() {
		boolean flag = true;// 默认接受
		GlobalParamsExample example = new GlobalParamsExample();
		example.createCriteria().andTypeValueEqualTo(R.global.client_setting)
				.andKeyEqualTo(R.global.image_key);
		List<GlobalParams> list = globalParamsMapper.selectByExample(example);
		if (CollectionUtils.isNotEmpty(list)
				&& list.get(0).getValue().equals(R.global.disable)) {
			flag = false;
		}
		return flag;
	}

}
