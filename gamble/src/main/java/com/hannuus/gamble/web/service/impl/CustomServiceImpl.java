package com.hannuus.gamble.web.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hannuus.gamble.comm.R;
import com.hannuus.gamble.dao.BlackListMapper;
import com.hannuus.gamble.dao.GlobalParamsMapper;
import com.hannuus.gamble.dao.UserMapper;
import com.hannuus.gamble.dao.UserTokenMapper;
import com.hannuus.gamble.model.BlackList;
import com.hannuus.gamble.model.BlackListExample;
import com.hannuus.gamble.model.GlobalParams;
import com.hannuus.gamble.model.GlobalParamsExample;
import com.hannuus.gamble.model.User;
import com.hannuus.gamble.model.UserToken;
import com.hannuus.gamble.model.UserTokenExample;
import com.hannuus.gamble.web.service.CustomService;

/**
 * @author cuesky
 * @date 2015年9月4日 下午4:37:47
 */
@Service
public class CustomServiceImpl implements CustomService {

	private Logger logger = Logger.getLogger(getClass());

	@Autowired
	BlackListMapper blackListMapper;
	@Autowired
	GlobalParamsMapper globalParamsMapper;
	@Autowired
	UserTokenMapper userTokenMapper;
	@Autowired
	UserMapper userMapper;

	// 登录、注册与注销================================================================

	@Override
	public UserToken findUserTokenByUserNameOrEmail(UserToken userToken) {
		UserTokenExample example = new UserTokenExample();
		example.or().andUserNameEqualTo(userToken.getUserName());
		example.or().andEmailEqualTo(userToken.getEmail());
		List<UserToken> list = userTokenMapper.selectByExample(example);
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public UserToken regist(UserToken userToken) {
		generateToken(userToken);
		// 同时保存user
		User user = new User();
		user.setUserName(userToken.getUserName());
		user.setEmail(userToken.getEmail());
		user.setMobile(userToken.getMobile());
		user.setState(1);// 默认可用
		user.setUserType(R.user.reality_type);// 默认正常用户
		user.setCreatedDate(new Date());// 默认系统时间
		userMapper.insertSelective(user);
		// 新增token
		userToken.setUserId(user.getId());
		userTokenMapper.insertSelective(userToken);
		return userToken;
	}

	/**
	 * 创建token值
	 * 
	 * @param userToken
	 * @return
	 */
	private void generateToken(UserToken userToken) {
		logger.debug("generateToken...");
		// TODO cuesky
		// FIXME need argue with huangrong and aelns
		// String token = null;
		// userToken.setToken(token);
		// AccessToken accessToken = new AccessToken();
		// userToken.setAccessToken(accessToken);
	}

	@Override
	public UserToken checkLogin(UserToken userToken) {
		logger.debug("checkLogin in...");
		// TODO 可完善为多种登录方式
		// 在userRealm中完成登录
		UsernamePasswordToken token = new UsernamePasswordToken(
				userToken.getUserName(), userToken.getPassword());
		UserToken ut = null;
		try {
			SecurityUtils.getSubject().login(token);
			ut = findUserTokenByUserName(userToken);
			generateToken(ut);
		} catch (AuthenticationException e) {
			// e.printStackTrace();
			logger.error(e.getMessage());
		}
		logger.debug("checkLogin out...");
		return ut;
	}

	@Override
	public UserToken qqLogin(UserToken userToken) {
		UserTokenExample example = new UserTokenExample();
		example.createCriteria().andQqEqualTo(userToken.getQq());
		List<UserToken> list = userTokenMapper.selectByExample(example);
		if (CollectionUtils.isNotEmpty(list)) {// 第2+次登录
			userToken = list.get(0);
		} else {// 第1次使用QQ进行登录，同时进行注册
			userToken.setUserName(userToken.getQq() + "@qq.com");
			userToken = regist(userToken);
		}
		return userToken;
	}

	@Override
	public UserToken sinaLogin(UserToken userToken) {
		UserTokenExample example = new UserTokenExample();
		example.createCriteria().andSinaEqualTo(userToken.getSina());
		List<UserToken> list = userTokenMapper.selectByExample(example);
		if (CollectionUtils.isNotEmpty(list)) {// 第2+次登录
			userToken = list.get(0);
		} else {// 第1次使用Sina进行登录，同时进行注册
			userToken.setUserName(userToken.getSina() + "@sina.com");
			userToken = regist(userToken);
		}
		return userToken;
	}

	public UserToken loginByRealm(String userName, String password) {
		logger.debug("loginByRealm in...");
		UserToken userToken = null;
		UserTokenExample example = new UserTokenExample();
		example.createCriteria().andUserNameEqualTo(userName)
				.andPasswordEqualTo(password);
		List<UserToken> list = userTokenMapper.selectByExample(example);
		if (CollectionUtils.isNotEmpty(list)) {
			userToken = list.get(0);
		}
		logger.debug("loginByRealm out...");
		return userToken;
	}

	@Override
	public void logout() {
		try {
			SecurityUtils.getSubject().logout();
		} catch (Exception e) {
			// e.printStackTrace();
			logger.error(e.getMessage());
		}
	}

	// 帐号密码设置===================================================================

	@Override
	public UserToken findUserTokenByUserName(UserToken userToken) {
		UserTokenExample example = new UserTokenExample();
		example.createCriteria().andUserNameEqualTo(userToken.getUserName());
		List<UserToken> list = userTokenMapper.selectByExample(example);
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public void sendReceipt(UserToken userToken) {
		userToken = findUserTokenByUserName(userToken);
		generateReceiptNum(userToken);
		emailReceipt(userToken);
	}

	/**
	 * Email回执号码
	 * 
	 * @param userToken
	 */
	private void emailReceipt(UserToken userToken) {
		// TODO cuesky
	}

	/**
	 * 生成回执号码设置给userToken
	 * 
	 * @param userToken
	 */
	private void generateReceiptNum(UserToken userToken) {
		// TODO cuesky
	}

	@Override
	public UserToken modifyUserToken(UserToken userToken) {
		UserTokenExample example = new UserTokenExample();
		example.createCriteria().andUserNameEqualTo(userToken.getUserName());
		UserToken record = new UserToken();
		record.setEmail(userToken.getEmail());
		record.setMobile(userToken.getEmail());
		record.setPassword(userToken.getPassword());
		userTokenMapper.updateByExample(record, example);
		userToken = findUserTokenByUserName(userToken);
		return userToken;
	}

	@Override
	public UserToken bandAccount(UserToken userToken) {
		UserTokenExample example = new UserTokenExample();
		example.createCriteria().andUserNameEqualTo(userToken.getUserName());
		UserToken record = new UserToken();
		record.setWebchat(userToken.getWebchat());
		record.setQq(userToken.getQq());
		record.setSina(userToken.getSina());
		userTokenMapper.updateByExampleSelective(record, example);
		userToken = findUserTokenByUserName(userToken);
		return userToken;
	}

	// 黑名单管理=====================================================================

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
