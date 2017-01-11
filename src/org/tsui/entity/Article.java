package org.tsui.entity;

/**
 * ͼ����Ϣ�е�Article�ṹ
 * @author TsuiXh
 *
 */
public class Article {
	private String Title;
	private String Description;
	private String PicUrl;
	private String Url;
	
	/**
	 * @param title	ͼ�ı���
	 * @param description	ͼ������
	 * @param picUrl		����ͼƬ��ַ
	 * @param url		���µ�ַ
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
