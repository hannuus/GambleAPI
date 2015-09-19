package com.hannuus.gamble.web.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hannuus.gamble.comm.R;
import com.hannuus.gamble.dao.UserMapper;
import com.hannuus.gamble.domain.page.PageDTO;
import com.hannuus.gamble.domain.page.PageParams;
import com.hannuus.gamble.model.User;
import com.hannuus.gamble.model.UserExample;
import com.hannuus.gamble.utils.VirtualUtils;
import com.hannuus.gamble.web.service.VirtualService;

/**
 * @author cuesky
 * @date 2015年9月18日下午5:19:39
 */
@Service
public class VirtualServiceImpl implements VirtualService {

	@Autowired
	UserMapper userMapper;

	@Override
	public PageDTO<User> findUserPage(PageParams params) {
		int total = userMapper.countByExample(null);
		UserExample example = new UserExample();
		example.createCriteria().andFlagEqualTo(R.user.virtual_type);// 只查询虚拟用户
		example.setLimitStart(params.getStart());
		example.setLimitEnd(params.getPageSize());
		example.setOrderByClause("created_date asc");// 默认按创建日期排升序
		List<User> list = userMapper.selectByExample(example);
		return new PageDTO<User>(total, list);
	}

	@Override
	public boolean isSeedAvailable(String seed) {
		UserExample example = new UserExample();
		example.createCriteria().andUserNameLike(seed);
		List<User> list = userMapper.selectByExample(example);
		if (CollectionUtils.isEmpty(list)) {
			return true;
		}
		return false;
	}

	@Override
	public void batchAddeUsers(String seed, int num) {
		String[] userNames = VirtualUtils.generateUserNames(seed, num);
		for (String userName : userNames) {
			User user = new User();
			user.setUserName(userName);
			user.setState(1);// 正常状态
			user.setCreatedDate(new Date());
			user.setFlag(R.user.virtual_type);// 虚拟用户
			userMapper.insertSelective(user);
		}
	}

}
