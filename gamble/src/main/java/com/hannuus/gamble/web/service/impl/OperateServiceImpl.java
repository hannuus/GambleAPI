package com.hannuus.gamble.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hannuus.gamble.comm.R;
import com.hannuus.gamble.dao.UserMapper;
import com.hannuus.gamble.domain.page.PageDTO;
import com.hannuus.gamble.domain.page.PageParams;
import com.hannuus.gamble.model.User;
import com.hannuus.gamble.model.UserExample;
import com.hannuus.gamble.web.service.OperateService;

/**
 * @author cuesky
 * @date 2015年9月21日下午4:27:50
 */
@Service
class OperateServiceImpl implements OperateService {

	@Autowired
	UserMapper userMapper;

	@Override
	public PageDTO<User> findOperPage(PageParams params) {
		int total = userMapper.countByRole(R.role.oper);
		List<User> list = userMapper.selectByRole(R.role.oper,
				params.getStart(), params.getPageSize());
		return new PageDTO<User>(total, list);
	}

	@Override
	public User findOperById(Long id) {
		return userMapper.selectByPrimaryKey(id);
	}

	@Override
	public void saveOrUpdateOper(User user) {
		Long id = user.getId();
		if (id == null) {
			userMapper.insertSelective(user);
			userMapper.insertUserRole(user.getId(), R.role.oper);
		} else {
			UserExample example = new UserExample();
			userMapper.updateByExampleSelective(user, example);
		}
	}

	@Override
	public void deleteOper(Long id) {
		userMapper.deleteByPrimaryKey(id);
		// 未作topic级联处理
		// TODO 备份或者FK置为null
	}

}
