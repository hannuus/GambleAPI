package com.hannuus.gamble.web.service.impl;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hannuus.gamble.comm.R;
import com.hannuus.gamble.dao.TopicMapper;
import com.hannuus.gamble.dao.UserMapper;
import com.hannuus.gamble.domain.page.PageDTO;
import com.hannuus.gamble.domain.page.PageParams;
import com.hannuus.gamble.model.Topic;
import com.hannuus.gamble.model.TopicExample;
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
	@Autowired
	TopicMapper topicMapper;

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

	@Override
	public PageDTO<Topic> findTopicPage(PageParams params) {
		TopicExample example = new TopicExample();
		example.createCriteria().andTopicTypeEqualTo(R.topic.virtual);
		example.setOrderByClause("created_date desc");
		int total = topicMapper.countByExample(example);

		example.setLimitStart(params.getStart());
		example.setLimitEnd(params.getPageSize());
		List<Topic> list = topicMapper.selectByExample(example);
		return new PageDTO<Topic>(total, list);
	}

	@Override
	public void batchAddTopics(InputStream in) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Workbook book = Workbook.getWorkbook(in);
		Sheet sheet = book.getSheet(0);
		int rows = sheet.getRows();
		List<Topic> list = new ArrayList<Topic>();

		for (int i = 1; i < rows; i++) {
			Cell c1 = sheet.getCell(0, i);
			Cell c2 = sheet.getCell(1, i);
			Cell c3 = sheet.getCell(2, i);
			Cell c4 = sheet.getCell(3, i);

			String title = c1.getContents();
			String content = c2.getContents();
			Long userId = Long.valueOf(c3.getContents());
			Date createdDate = sdf.parse(c4.getContents());

			Topic topic = new Topic();
			topic.setTitle(title);
			topic.setContent(content);
			topic.setTopicType(R.topic.virtual);
			topic.setUserId(userId);
			topic.setCreatedDate(createdDate);

			list.add(topic);
		}

		validateUserIds(list);

		for (Topic topic : list) {
			topicMapper.insertSelective(topic);
		}
	}

	private void validateUserIds(List<Topic> list) {
		List<Long> ids = new ArrayList<Long>();
		for (Topic topic : list) {
			ids.add(topic.getUserId());
		}
		List<Long> all = userMapper.selectIdsByRole(R.user.virtual_type);
		boolean flag = all.containsAll(ids);
		if (!flag) {
			// TODO 先作为异常处理
			throw new RuntimeException();
		}
	}
}
