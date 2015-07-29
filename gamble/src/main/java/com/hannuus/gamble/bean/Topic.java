package com.hannuus.gamble.bean;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.hannuus.gamble.vo.serializer.DateSerializer;
import com.hannuus.gamble.vo.serializer.StringFormatSerializer;

public class Topic {
	@JsonSerialize(using=StringFormatSerializer.class)
    private Long id;
	
	@JsonSerialize(using=StringFormatSerializer.class)
    private Long categoryId;

	@JsonSerialize(using=StringFormatSerializer.class)
    private Long speciaId;
	
	@JsonSerialize(using=StringFormatSerializer.class)
    private Long userId;
	
	@JsonSerialize(using=StringFormatSerializer.class)
    private Boolean enabled;

	@JsonSerialize(using=StringFormatSerializer.class)
    private Long hits;
	
	@JsonSerialize(using=StringFormatSerializer.class)
    private Long replyCount;
	
	@JsonSerialize(using=StringFormatSerializer.class)
    private Long modifiedBy;

	@JsonSerialize(using=StringFormatSerializer.class)
    private Date modifiedOn;

	@JsonSerialize(using=StringFormatSerializer.class)
    private Long repliedBy;

	@JsonSerialize(using=StringFormatSerializer.class)
    private Long repliedOn;

	@JsonSerialize(using=StringFormatSerializer.class)
    private Long iconId;

	@JsonSerialize(using=StringFormatSerializer.class)
    private Integer state;

    private String imgUrl;
    
    @JsonSerialize(using=DateSerializer.class)
    private Date createdOn;

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

    public Long getSpeciaId() {
        return speciaId;
    }

    public void setSpeciaId(Long speciaId) {
        this.speciaId = speciaId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
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

    public Date getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(Date modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public Long getRepliedBy() {
        return repliedBy;
    }

    public void setRepliedBy(Long repliedBy) {
        this.repliedBy = repliedBy;
    }

    public Long getRepliedOn() {
        return repliedOn;
    }

    public void setRepliedOn(Long repliedOn) {
        this.repliedOn = repliedOn;
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