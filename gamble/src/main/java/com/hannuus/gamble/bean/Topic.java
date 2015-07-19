package com.hannuus.gamble.bean;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.hannuus.gamble.vo.serializer.BooleanSerializer;
import com.hannuus.gamble.vo.serializer.DateSerializer;
import com.hannuus.gamble.vo.serializer.IntegerSerializer;

public class Topic {
	@JsonSerialize(using=IntegerSerializer.class)
    private Integer id;
	
	@JsonSerialize(using=IntegerSerializer.class)
    private Integer categoryId;
	
	@JsonSerialize(using=IntegerSerializer.class)
    private Integer speciaId;
	
	@JsonSerialize(using=IntegerSerializer.class)
    private Integer userId;
	
	@JsonSerialize(using=BooleanSerializer.class)
    private Boolean enabled;
	
	@JsonSerialize(using=IntegerSerializer.class)
    private Integer hits;

	@JsonSerialize(using=IntegerSerializer.class)
    private Integer replyCount;
	
	@JsonSerialize(using=IntegerSerializer.class)
    private Integer modifiedBy;
	
	@JsonSerialize(using=DateSerializer.class)
    private Date modifiedOn;
	
	@JsonSerialize(using=IntegerSerializer.class)
    private Integer repliedBy;
	
	@JsonSerialize(using=IntegerSerializer.class)
    private Integer repliedOn;
	
	@JsonSerialize(using=IntegerSerializer.class)
    private Integer iconId;
	
	@JsonSerialize(using=IntegerSerializer.class)
    private Integer state;

    private String imgUrl;
    
    @JsonSerialize(using=DateSerializer.class)
    private Date createdOn;

    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getSpeciaId() {
        return speciaId;
    }

    public void setSpeciaId(Integer speciaId) {
        this.speciaId = speciaId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Integer getHits() {
        return hits;
    }

    public void setHits(Integer hits) {
        this.hits = hits;
    }

    public Integer getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(Integer replyCount) {
        this.replyCount = replyCount;
    }

    public Integer getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Integer modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(Date modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public Integer getRepliedBy() {
        return repliedBy;
    }

    public void setRepliedBy(Integer repliedBy) {
        this.repliedBy = repliedBy;
    }

    public Integer getRepliedOn() {
        return repliedOn;
    }

    public void setRepliedOn(Integer repliedOn) {
        this.repliedOn = repliedOn;
    }

    public Integer getIconId() {
        return iconId;
    }

    public void setIconId(Integer iconId) {
        this.iconId = iconId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}