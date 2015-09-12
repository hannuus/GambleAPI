package com.hannuus.gamble.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hannuus.gamble.dao.ActivityMapper;
import com.hannuus.gamble.model.Activity;
import com.hannuus.gamble.web.service.ActivityService;

/**
 * @author cuesky
 * @date 2015年9月12日 上午12:29:07
 */
@Service
public class ActivityServiceImpl implements ActivityService {

	@Autowired
	ActivityMapper activityMapper;

	@Override
	public void logActivity(Activity activity) {
		activityMapper.insertSelective(activity);
	}

}
