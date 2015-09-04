package com.hannuus.gamble.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hannuus.gamble.dao.BlackListMapper;
import com.hannuus.gamble.model.BlackList;
import com.hannuus.gamble.model.BlackListExample;
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

	// 无图模式=======================================================================

}
