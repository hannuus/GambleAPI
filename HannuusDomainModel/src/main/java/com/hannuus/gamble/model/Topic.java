package com.hannuus.gamble.model;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hannuus.core.json.DateSerializer;

public class Topic {
	private Long id;

	private Long categoryId;

	private Long specialId;

	private Long tagId;

	private Integer topicType;

	private Long userId;

	private String title;

	private Integer imgFlag;

	private Long modifiedBy;

	@JsonSerialize(using = DateSerializer.class)
	private Date modifiedDate;

	private Long repliedBy;

	@JsonSerialize(using = DateSerializer.class)
	private Date repliedDate;

	private Long hits;

	private Long replyCount;

	private Long followCount;

	private Long collectionCount;

	private Long upCount;

	private Long downCount;

	private Integer essence;

	private Integer stickie;

	private Integer enabled;

	private Integer state;

	@JsonSerialize(using = DateSerializer.class)
	private Date createdDate;

	private String content;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Long getSpecialId() {
		return specialId;
	}

	public void setSpecialId(Long specialId) {
		this.specialId = specialId;
	}

	public Long getTagId() {
		return tagId;
	}

	public void setTagId(Long tagId) {
		this.tagId = tagId;
	}

	public Integer getTopicType() {
		return topicType;
	}

	public void setTopicType(Integer topicType) {
		this.topicType = topicType;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}

	public Integer getImgFlag() {
		return imgFlag;
	}

	public void setImgFlag(Integer imgFlag) {
		this.imgFlag = imgFlag;
	}

	public Long getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Long getRepliedBy() {
		return repliedBy;
	}

	public void setRepliedBy(Long repliedBy) {
		this.repliedBy = repliedBy;
	}

	public Date getRepliedDate() {
		return repliedDate;
	}

	public void setRepliedDate(Date repliedDate) {
		this.repliedDate = repliedDate;
	}

	public Long getHits() {
		return hits;
	}

	public void setHits(Long hits) {
		this.hits = hits;
	}

	public Long getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(Long replyCount) {
		this.replyCount = replyCount;
	}

	public Long getFollowCount() {
		return followCount;
	}

	public void setFollowCount(Long followCount) {
		this.followCount = followCount;
	}

	public Long getCollectionCount() {
		return collectionCount;
	}

	public void setCollectionCount(Long collectionCount) {
		this.collectionCount = collectionCount;
	}

	public Long getUpCount() {
		return upCount;
	}

	public void setUpCount(Long upCount) {
		this.upCount = upCount;
	}

	public Long getDownCount() {
		return downCount;
	}

	public void setDownCount(Long downCount) {
		this.downCount = downCount;
	}

	public Integer getEssence() {
		return essence;
	}

	public void setEssence(Integer essence) {
		this.essence = essence;
	}

	public Integer getStickie() {
		return stickie;
	}

	public void setStickie(Integer stickie) {
		this.stickie = stickie;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}
}