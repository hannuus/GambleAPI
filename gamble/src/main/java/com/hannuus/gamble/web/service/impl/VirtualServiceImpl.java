package com.hannuus.gamble.web.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hannuus.gamble.comm.R;
import com.hannuus.gamble.dao.GlobalParamsMapper;
import com.hannuus.gamble.dao.ReplyMapper;
import com.hannuus.gamble.dao.TopicMapper;
import com.hannuus.gamble.dao.UserMapper;
import com.hannuus.gamble.model.GlobalParams;
import com.hannuus.gamble.model.GlobalParamsExample;
import com.hannuus.gamble.model.Reply;
import com.hannuus.gamble.model.ReplyExample;
import com.hannuus.gamble.model.Topic;
import com.hannuus.gamble.model.User;
import com.hannuus.gamble.model.UserExample;
import com.hannuus.gamble.utils.VirtualUtils;
import com.hannuus.gamble.web.service.VirtualService;
import com.hannuus.pagination.PageDTO;
import com.hannuus.pagination.PageParams;

/**
 * @author cuesky
 * @date 2015年9月18日下午5:19:39
 */
@Service
public class VirtualServiceImpl implements VirtualService {

	@Autowired
	UserMapper userMapper;
	@Autowired
	ReplyMapper replyMapper;
	@Autowired
	GlobalParamsMapper globalParamsMapper;
	@Autowired
	TopicMapper topicMapper;

	@Override
	public PageDTO<User> findUserPage(PageParams params) {
		int total = userMapper.countByExample(null);
		UserExample example = new UserExample();
		example.createCriteria().andUserTypeEqualTo(R.user.virtual_type);// 只查询虚拟用户
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
			// user.setUserType(R.user.virtual_type);// 虚拟用户
			userMapper.insertSelective(user);
		}
	}

	@Override
	public PageDTO<Reply> findReplyPage(PageParams params) {
		int total = replyMapper.countByExample(null);
		ReplyExample example = new ReplyExample();
		// 只查询虚拟回复
		example.createCriteria().andReplyTypeEqualTo(R.reply.virtual_type);
		// 按照日期排降序
		example.setOrderByClause("created_date desc");
		List<Reply> list = replyMapper.selectByExample(example);
		return new PageDTO<Reply>(total, list);
	}

	@Override
	public void batchAddReplys() {
		List<Reply> list = generateVirtualReplysRecords();
		for (Reply reply : list) {
			replyMapper.insertSelective(reply);
		}
	}

	/**
	 * 生成思路：<br>
	 * 1、首先根据采样天数(sampleDay)，查询出近几天所有的主题<br>
	 * 2、在这些主题中根据虚拟回复因子(replyFactor)，随机抽取出需要被虚拟回复的主题<br>
	 * 3、同理，根据虚拟回复因子，从所有的虚拟用户中随机抽取出用来回复的虚拟用户<br>
	 * 4、从GlobalParams中查询出虚拟回复内容<br>
	 * 5、封装虚拟回复记录<br>
	 * <br>
	 * 公式：<br>
	 * A.虚拟回复总数(num) = 原始主题样本总数 / 虚拟回复因子(replyFactor)<br>
	 * B.虚拟回复内容 = subject + verb + content<br>
	 * 
	 * @return 虚拟回复记录
	 */
	private List<Reply> generateVirtualReplysRecords() {
		List<Reply> replys = new ArrayList<Reply>();

		// 采样天数
		Integer sampleDay = querySampleDay();

		// 被回复主题(原始样本)
		List<Topic> topicSamples = topicMapper.findSampleTopics(sampleDay);

		// 必须采样天数以内有topic才生成虚拟回复
		if (CollectionUtils.isNotEmpty(topicSamples)) {
			// 虚拟回复因子
			Integer replyFactor = queryReplyFactor();
			// 虚拟回复数
			int num = topicSamples.size() / replyFactor;

			// 被回复主题
			List<Topic> topics = VirtualUtils.generateRandomSamples(
					topicSamples, num);
			// 虚拟用户
			List<User> users = queryVirtualUsers(num);

			List<String> contents = generateContents(num);

			wrapReply(replys, num, topics, users, contents);
		}
		return replys;
	}

	/**
	 * @param num
	 *            虚拟回复数
	 * @return 虚拟回复
	 */
	private List<String> generateContents(int num) {
		List<String> list = new ArrayList<String>();
		// 主语
		GlobalParamsExample example = new GlobalParamsExample();
		example.createCriteria().andKeyEqualTo(R.global.virtual_subject_key);
		List<GlobalParams> subjects = globalParamsMapper
				.selectByExample(example);
		int len1 = subjects.size();
		// 谓语
		example = new GlobalParamsExample();
		example.createCriteria().andKeyEqualTo(R.global.virtual_verb_key);
		List<GlobalParams> verbs = globalParamsMapper.selectByExample(example);
		int len2 = verbs.size();
		// 内容
		example = new GlobalParamsExample();
		example.createCriteria().andKeyEqualTo(R.global.virtual_content_key);
		List<GlobalParams> contents = globalParamsMapper
				.selectByExample(example);
		int len3 = contents.size();

		Random random = new Random();
		for (int i = 0; i < num; i++) {
			String subject = subjects.get(random.nextInt(len1)).getValue();
			String verb = verbs.get(random.nextInt(len2)).getValue();
			String con = contents.get(random.nextInt(len3)).getValue();
			String content = subject + verb + "," + con;
			list.add(content);
		}
		return list;
	}

	/**
	 * @param num
	 *            虚拟回复数
	 * @return 用来回复主题的虚拟用户
	 */
	private List<User> queryVirtualUsers(int num) {
		UserExample ex = new UserExample();
		ex.createCriteria().andUserTypeEqualTo(R.user.virtual_type);// 只查询虚拟用户
		// 虚拟用户(原始样本)
		List<User> userSamples = userMapper.selectByExample(ex);
		// 虚拟用户
		List<User> users = VirtualUtils.generateRandomSamples(userSamples, num);
		return users;
	}

	/**
	 * @return 虚拟回复因子
	 */
	private Integer queryReplyFactor() {
		GlobalParamsExample example;
		example = new GlobalParamsExample();
		example.createCriteria().andKeyEqualTo(
				R.global.virtual_reply_factor_key);
		Integer replyFactor = Integer.valueOf(globalParamsMapper
				.selectByExample(example).get(0).getValue());
		return replyFactor;
	}

	/**
	 * @return 虚拟回复采样天数
	 */
	private Integer querySampleDay() {
		GlobalParamsExample example = new GlobalParamsExample();
		example.createCriteria().andKeyEqualTo(R.global.virtual_sample_day_key);
		Integer sampleDay = Integer.valueOf(globalParamsMapper
				.selectByExample(example).get(0).getValue());
		return sampleDay;
	}

	/**
	 * @param replys
	 *            虚拟回复记录
	 * @param num
	 *            虚拟回复数
	 * @param topics
	 *            回复主题
	 * @param users
	 *            虚拟用户
	 * @param contents
	 *            虚拟回复
	 */
	private void wrapReply(List<Reply> replys, int num, List<Topic> topics,
			List<User> users, List<String> contents) {
		for (int i = 0; i < num; i++) {
			Reply reply = new Reply();

			Topic topic = topics.get(i);
			reply.setTopicId(topic.getId());// 主题ID

			String title = "Re:" + topic.getTitle();
			reply.setTitle(title);// 回复标题

			reply.setContent(contents.get(i));// 回复内容

			User user = users.get(i);
			reply.setUserId(user.getId());// 回复用户ID

			reply.setCreatedDate(new Date());// 系统时间

			reply.setReplyType(R.reply.virtual_type);// 回复类型

			replys.add(reply);
		}
	}

}
