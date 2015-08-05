package com.hannuus.gamble.web.dto;

/**
 * 搜索参数的DTO, 热门词汇、tags；据作者、标题、内容搜索etc.
 * @author aelns
 *
 */
public class SearchTopicParamDTO {
	
	/**
	 * 作者
	 */
	private String author;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 内容
	 */
	private String content;
	
	/**
	 * 热门词汇
	 */
	private String hotWords;
	
	/**
	 * 标签
	 */
	private String tags;
	
	public String getHotWords() {
		return hotWords;
	}
	public void setHotWords(String hotWords) {
		this.hotWords = hotWords;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
