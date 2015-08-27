package com.hannuus.gamble.model;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hannuus.core.json.DateSerializer;

public class User {
	private Long id;

	private String userName;

	private String nickName;

	private String password;

	private String salt;

	private String email;

	private String headUrl;

	private String introduction;

	private String signature;

	private Integer topicCount;

	private Integer replyCount;

	private Integer bestTopicCount;

	private Long lastTopicId;

	private Long lastReplyId;

	private Integer amount;

	private Integer state;

	@JsonSerialize(using = DateSerializer.class)
	private Date createdDate;

	private Integer fansCount;

	private Integer followCount;

	private String mobile;

	private Integer flag;

	private Integer acceptImage;

	private Integer acceptPushMessage;

	private String confirmPassword;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName == null ? null : userName.trim();
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName == null ? null : nickName.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt == null ? null : salt.trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	public String getHeadUrl() {
		return headUrl;
	}

	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl == null ? null : headUrl.trim();
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction == null ? null : introduction.trim();
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature == null ? null : signature.trim();
	}

	public Integer getTopicCount() {
		return topicCount;
	}

	public void setTopicCount(Integer topicCount) {
		this.topicCount = topicCount;
	}

	public Integer getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(Integer replyCount) {
		this.replyCount = replyCount;
	}

	public Integer getBestTopicCount() {
		return bestTopicCount;
	}

	public void setBestTopicCount(Integer bestTopicCount) {
		this.bestTopicCount = bestTopicCount;
	}

	public Long getLastTopicId() {
		return lastTopicId;
	}

	public void setLastTopicId(Long lastTopicId) {
		this.lastTopicId = lastTopicId;
	}

	public Long getLastReplyId() {
		return lastReplyId;
	}

	public void setLastReplyId(Long lastReplyId) {
		this.lastReplyId = lastReplyId;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getFansCount() {
		return fansCount;
	}

	public void setFansCount(Integer fansCount) {
		this.fansCount = fansCount;
	}

	public Integer getFollowCount() {
		return followCount;
	}

	public void setFollowCount(Integer followCount) {
		this.followCount = followCount;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile == null ? null : mobile.trim();
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Integer getAcceptImage() {
		return acceptImage;
	}

	public void setAcceptImage(Integer acceptImage) {
		this.acceptImage = acceptImage;
	}

	public Integer getAcceptPushMessage() {
		return acceptPushMessage;
	}

	public void setAcceptPushMessage(Integer acceptPushMessage) {
		this.acceptPushMessage = acceptPushMessage;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

}