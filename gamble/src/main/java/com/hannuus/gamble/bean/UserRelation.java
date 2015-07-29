package com.hannuus.gamble.bean;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.hannuus.gamble.vo.serializer.DateSerializer;
import com.hannuus.gamble.vo.serializer.StringFormatSerializer;

public class UserRelation {
	
	@JsonSerialize(using=StringFormatSerializer.class)
    private Long id;
	
	@JsonSerialize(using=DateSerializer.class)
    private Date createTime;
    
    @JsonSerialize(using=StringFormatSerializer.class)
    private Integer relationType;
    
    @JsonSerialize(using=StringFormatSerializer.class)
    private Long fromId;
    
    @JsonSerialize(using=StringFormatSerializer.class)
    private Long toId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getRelationType() {
        return relationType;
    }

    public void setRelationType(Integer relationType) {
        this.relationType = relationType;
    }

    public Long getFromId() {
        return fromId;
    }

    public void setFromId(Long fromId) {
        this.fromId = fromId;
    }

    public Long getToId() {
        return toId;
    }

    public void setToId(Long toId) {
        this.toId = toId;
    }
}