package com.hannuus.gamble.model;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hannuus.core.json.DateSerializer;

public class UserGroup {
	private Long id;

	private String name;

	private String groupValue;

	private String description;

	@JsonSerialize(using = DateSerializer.class)
	private Date createdDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getGroupValue() {
		return groupValue;
	}

	public void setGroupValue(String groupValue) {
		this.groupValue = groupValue == null ? null : groupValue.trim();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
}