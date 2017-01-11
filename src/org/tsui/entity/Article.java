package org.tsui.entity;

/**
 * 图文消息中的Article结构
 * @author TsuiXh
 *
 */
public class Article {
	private String Title;
	private String Description;
	private String PicUrl;
	private String Url;
	
	/**
	 * @param title	图文标题
	 * @param description	图文描述
	 * @param picUrl		封面图片地址
	 * @param url		文章地址
	 */
	public Article(String title, String description, String picUrl, String url) {
		this.Title = title;
		this.Description = description;
		this.PicUrl = picUrl;
		this.Url = url;
	}
	
	public Article(){
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}
	
	
}
