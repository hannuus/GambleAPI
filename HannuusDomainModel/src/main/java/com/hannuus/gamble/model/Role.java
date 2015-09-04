package com.hannuus.gamble.model;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hannuus.core.json.DateSerializer;

public class Role {
	private Long id;

	private String name;

	private String roleValue;

	private String description;

	private String resourceIds;

	@JsonSerialize(using = DateSerializer.class)
	private Date createdDate;

	private Integer available;

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

	public String getRoleValue() {
		return roleValue;
	}

	public void setRoleValue(String roleValue) {
		this.roleValue = roleValue == null ? null : roleValue.trim();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}

	public String getResourceIds() {
		return resourceIds;
	}

	public void setResourceIds(String resourceIds) {
		this.resourceIds = resourceIds == null ? null : resourceIds.trim();
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getAvailable() {
		return available;
	}

	public void setAvailable(Integer available) {
		this.available = available;
	}
}