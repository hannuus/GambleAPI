package com.hannuus.gamble.model;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hannuus.core.json.DateSerializer;

public class VoteUser {
	private Long id;

	private Long userId;

	private Long voteId;

	private String voteItemIds;

	@JsonSerialize(using = DateSerializer.class)
	private Date createdDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getVoteId() {
		return voteId;
	}

	public void setVoteId(Long voteId) {
		this.voteId = voteId;
	}

	public String getVoteItemIds() {
		return voteItemIds;
	}

	public void setVoteItemIds(String voteItemIds) {
		this.voteItemIds = voteItemIds == null ? null : voteItemIds.trim();
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
}