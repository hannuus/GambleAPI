package com.hannuus.gamble.model;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hannuus.core.json.DateSerializer;

public class Permission {
	private Long id;

	private String name;

	private String permissionValue;

	private String description;

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

	public String getPermissionValue() {
		return permissionValue;
	}

	public void setPermissionValue(String permissionValue) {
		this.permissionValue = permissionValue == null ? null : permissionValue
				.trim();
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

	public Integer getAvailable() {
		return available;
	}

	public void setAvailable(Integer available) {
		this.available = available;
	}
}