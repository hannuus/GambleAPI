package com.hannuus.gamble.model;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hannuus.core.json.DateSerializer;

public class Resource {
	private Long id;

	private String name;

	private String resourceValue;

	private String description;

	private String type;

	private Integer priority;

	private Long parentId;

	private String parentIds;

	private String url;

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

	public String getResourceValue() {
		return resourceValue;
	}

	public void setResourceValue(String resourceValue) {
		this.resourceValue = resourceValue == null ? null : resourceValue
				.trim();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type == null ? null : type.trim();
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds == null ? null : parentIds.trim();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url == null ? null : url.trim();
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