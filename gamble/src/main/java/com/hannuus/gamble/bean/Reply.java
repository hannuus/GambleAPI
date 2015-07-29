package com.hannuus.gamble.bean;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.hannuus.gamble.vo.serializer.DateSerializer;
import com.hannuus.gamble.vo.serializer.StringFormatSerializer;

public class Reply {
	
	@JsonSerialize(using=StringFormatSerializer.class)
    private Long id;
	
	@JsonSerialize(using=StringFormatSerializer.class)
    private Long topicId;

    private String title;

    @JsonSerialize(using=StringFormatSerializer.class)
    private Long userId;

    @JsonSerialize(using=DateSerializer.class)
    private Date modifiedOn;

    private Date createdOn;

    @JsonSerialize(using=StringFormatSerializer.class)
    private Long parentId;

    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
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

    public Date getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(Date modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}