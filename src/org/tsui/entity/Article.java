package org.tsui.entity;

/**
 * ͼ����Ϣ�е�Article�ṹ
 * @author TsuiXh
 *
 */
public class Article {
	private int article_id;
	public int getArticle_id() {
		return article_id;
	}

	public void setArticle_id(int article_id) {
		this.article_id = article_id;
	}

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

	@Override
	public String toString() {
		return "Article [article_id=" + article_id + ", Title=" + Title + ", Description=" + Description + ", PicUrl="
				+ PicUrl + ", Url=" + Url + "]";
	}
	
}
