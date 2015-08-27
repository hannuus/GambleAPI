package com.hannuus.gamble.model;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hannuus.core.json.DateSerializer;

public class Topic {
	private Long id;

	private Long categoryId;

	private Long specialId;

	private String title;

	private Long userId;

	private Integer enabled;

	private Long hits;

	private Long replyCount;

	private Long modifiedBy;

	@JsonSerialize(using = DateSerializer.class)
	private Date modifiedDate;

	private Long repliedBy;

	@JsonSerialize(using = DateSerializer.class)
	private Date repliedDate;

	private Long iconId;

	private Integer state;

	private String imageUrl;

	@JsonSerialize(using = DateSerializer.class)
	private Date createdDate;

	private Long followCount;

	private Long tagId;

	private Long upCount;

	private Long downCount;

	private Long collectionCount;

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
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

	public Long getIconId() {
		return iconId;
	}

	public void setIconId(Long iconId) {
		this.iconId = iconId;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl == null ? null : imageUrl.trim();
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Long getFollowCount() {
		return followCount;
	}

	public void setFollowCount(Long followCount) {
		this.followCount = followCount;
	}

	public Long getTagId() {
		return tagId;
	}

	public void setTagId(Long tagId) {
		this.tagId = tagId;
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

	public Long getCollectionCount() {
		return collectionCount;
	}

	public void setCollectionCount(Long collectionCount) {
		this.collectionCount = collectionCount;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}
}