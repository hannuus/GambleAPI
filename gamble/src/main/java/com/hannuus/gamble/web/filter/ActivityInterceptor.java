package com.hannuus.gamble.web.filter;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.hannuus.gamble.comm.R;
import com.hannuus.gamble.model.Activity;
import com.hannuus.gamble.model.UserToken;
import com.hannuus.gamble.web.service.ActivityService;

/**
 * 活动拦截器
 * 
 * @author cuesky
 * @date 2015年9月12日 上午10:38:37
 */
@Component
public class ActivityInterceptor implements HandlerInterceptor {

	private Logger logger = Logger.getLogger(getClass());

	@Autowired
	ActivityService activityService;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String path = request.getRequestURI();
		switch (path) {
		case R.activity.logout_uri:
			logger.debug("detect logout...");
			logActivity(request, R.activity.logout_type, null, null);
			break;
		default:
			break;
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		String path = request.getRequestURI();
		switch (path) {
		case R.activity.login_uri: {
			logger.debug("detect login...");
			logActivity(request, R.activity.login_type, null, null);
			break;
		}
		case R.activity.post_uri: {
			logger.debug("detect post...");
			Long topicId = null;// 主题贴id
			logActivity(request, R.activity.post_type, topicId, null);
			break;
		}
		case R.activity.reply_uri: {
			logger.debug("detect reply...");
			Long topicId = null;// 主题贴id
			logActivity(request, R.activity.reply_type, topicId, null);
			break;
		}
		case R.activity.mark_uri: {
			logger.debug("detect mark...");
			Long userId = null;// 被关注人id
			logActivity(request, R.activity.mark_type, userId, null);
			break;
		}
		case R.activity.collect_uri: {
			logger.debug("detect collect...");
			Long topicId = null;// 收藏贴id
			logActivity(request, R.activity.collect_type, topicId, null);
			break;
		}
		case R.activity.email_uri:
			logger.debug("detect email...");
			Long userId = null;// 收件人id
			logActivity(request, R.activity.email_type, userId, null);
			break;
		case R.activity.search_uri:
			logger.debug("detect search...");
			String key = null;// 搜索的关键字
			logActivity(request, R.activity.search_type, null, key);
			break;
		default:
			break;
		}
	}

	private void logActivity(HttpServletRequest request, Integer typeId,
			Long targetId, String activityValue) {
		Object user = request.getSession().getAttribute(R.session.user);
		if (user != null) {
			UserToken userToken = (UserToken) user;
			Long userId = userToken.getUserId();
			Activity activity = new Activity();
			activity.setUserId(userId);
			activity.setTypeId(typeId);
			activity.setTargetId(targetId);
			activity.setActivityValue(activityValue);
			activity.setCreatedDate(new Date());
			activityService.logActivity(activity);
		} else {
			logger.debug("匿名用户，未记录日志");
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
