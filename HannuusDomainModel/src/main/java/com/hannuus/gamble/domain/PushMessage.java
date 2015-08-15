package com.hannuus.gamble.domain;

import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

/**
 * 用于消息推送
 * 
 * @author cuesky
 * @date 2015年8月15日 下午1:53:50
 */
public class PushMessage {

	private String title;

	private String description;

	@SerializedName("notification_builder_id")
	private Integer notificationBuilderId;

	@SerializedName("notification_basic_style")
	private Integer notificationBasicStyle;

	@SerializedName("open_type")
	private Integer openType;

	private String url;

	@SerializedName("custom_content")
	private Map<String, String> customContent;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getNotificationBuilderId() {
		return notificationBuilderId;
	}

	public void setNotificationBuilderId(Integer notificationBuilderId) {
		this.notificationBuilderId = notificationBuilderId;
	}

	public Integer getNotificationBasicStyle() {
		return notificationBasicStyle;
	}

	public void setNotificationBasicStyle(Integer notificationBasicStyle) {
		this.notificationBasicStyle = notificationBasicStyle;
	}

	public Integer getOpenType() {
		return openType;
	}

	public void setOpenType(Integer openType) {
		this.openType = openType;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Map<String, String> getCustomContent() {
		return customContent;
	}

	public void setCustomContent(Map<String, String> customContent) {
		this.customContent = customContent;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}

}
