package org.tsui.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.tsui.entity.Article;
import org.tsui.entity.Keyword;


/**
 * ���ݷ��ʲ㣬��ѯ��װ
 * @author TsuiXh
 */
public class DaoHelper {
	
	private static Connection conn;
	/**
	 * ͨ���ؼ��ʲ�ѯ���ظ������Լ��ظ���Ϣ�ľ�����Ϣ
	 * @param key		�ؼ���
	 * @return			ͨ���ؼ��ʲ�ѯ���Ľ������
	 * @throws SQLException
	 */
	public static Keyword queryByKeyword(String key) throws SQLException {
		Keyword keyword = null;
		if (conn == null) {
			conn = DatabaseUtil.getConn();
		}
		PreparedStatement ps = conn.prepareStatement("SELECT reply_type,article_id,text_id FROM KEYWORD WHERE KEYWORD = ?");
		ps.setString(1, key);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			String reply_type = rs.getString("reply_type");
			if ("article".endsWith(reply_type)) {
				int article_id = rs.getInt("article_id");
				keyword = new Keyword();
				keyword.setReply_type(reply_type);
				keyword.setArticle_id(article_id);
			} else if ("text".endsWith(reply_type)) {
				int text_id = rs.getInt("text_id");
				keyword = new Keyword();
				keyword.setReply_type(reply_type);
				keyword.setText_id(text_id);
			}
		}
		return keyword;
	}

	/**
	 * ͨ��id��ѯ����
	 * @param article_id
	 * @return
	 * @throws SQLException 
	 */
	public static Article queryArticleById(int article_id) throws SQLException {
		Article article = null;
		if (conn == null) {
			conn = DatabaseUtil.getConn();
		}
		PreparedStatement ps = conn.prepareStatement("SELECT title,description,pic_url,ad_url FROM article WHERE article_id = ?");
		ps.setInt(1, article_id);
		ResultSet rs = ps.executeQuery();
		if (rs != null) {
			String title = rs.getString("title");
			String description = rs.getString("description");
			String pic_url = rs.getString("pic_url");
			String url = rs.getString("url");
			article = new Article(title, description, pic_url, url);
		}
		return article;
	}
	
	/**
	 * ��ѯ���
	 * @return
	 * @throws SQLException
	 */
	public static Article queryCurrentAdvertisement() throws SQLException {
		Article article = null;
		if (conn == null) {
			conn = DatabaseUtil.getConn();
		}
		Statement s = conn.createStatement();
		ResultSet rs = s.executeQuery("SELECT title,description,pic_url,ad_url FROM ad order by ad_id desc limit 1");
		
		String title = rs.getString("title");
		String description = rs.getString("description");
		String pic_url = rs.getString("pic_url");
		String ad_url = rs.getString("ad_url");
		//���
		article = new Article(title, description, pic_url, ad_url);
		
		return article;
	}
	
	/**
	 * ͨ��id��ѯ���ֻظ���Ϣ
	 * @param id	���ֻظ�id
	 * @return		������Ϣ
	 * @throws SQLException
	 */
	public static String queryTextReplyById(int id) throws SQLException {
		String reply_text = "";
		if (conn == null) {
			conn = DatabaseUtil.getConn();
		}
		Statement s = conn.createStatement();
		ResultSet rs = s.executeQuery("SELECT content FROM text_reply WHERE text_id = " + String.valueOf(id));
		reply_text = rs.getString("content");
		return reply_text;
	}

	/**
	 * ��ȡ��ע�¼��ظ���ͼ��
	 * @return ArrayList
	 * @throws SQLException 
	 */
	public static ArrayList<Article> queryArticles4Subscrible() throws SQLException {
		ArrayList<Article> articles = new ArrayList<>();
		if (conn == null) {
			conn = DatabaseUtil.getConn();
		}
		Statement s = conn.createStatement();
		ResultSet rs = s.executeQuery("SELECT title,description,pic_url,ad_url FROM article WHERE is4_sub = 1");
		if (rs != null) {
			while (rs.next()) {
				String title = rs.getString("title");
				String description = rs.getString("description");
				String pic_url = rs.getString("pic_url");
				String url = rs.getString("url");
				Article article = new Article(title, description, pic_url, url);
				articles.add(article);
			}
		}
		return articles;
	}
	
	/**
	 * ��ѯ�˺�����
	 * @param account
	 * @param password
	 * @return	״̬��	��½�ɹ���1���˺Ų����ڻ��������0��
	 * @throws SQLException 
	 */
	public static int qureyAdminister(String account, String password) throws SQLException {
		int stateCode = 0;
		if (conn == null) {
			conn = DatabaseUtil.getConn();
		}
		PreparedStatement ps = conn.prepareStatement("SELECT id FROM admin WHERE account = ? AND password = ?");
		ps.setString(1, account);
		ps.setString(2, password);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			stateCode = 1;
		} else {
			stateCode = 0;
		}
		return stateCode;
	}
	
	/**
	 * ������²���������������id
	 * @param article		���¶���
	 * @return				������º��������id
	 * @throws SQLException 
	 */
	public static int addArticle(Article article) throws SQLException {
		int auto_incrementID = 0;
		//��ȡ����
		if (conn == null) {
			conn = DatabaseUtil.getConn();
		}
		PreparedStatement ps = conn.prepareStatement("insert into article (title,description,pic_url,url,is4_sub) values (?,?,?,?,?)");
		ps.setString(1, article.getTitle());
		ps.setString(2, article.getDescription());
		ps.setString(3, article.getPicUrl());
		ps.setString(4, article.getUrl());
		ps.setInt(5, 0);
		//��ȡ�����Ƿ�ɹ�
		int effectedRow = ps.executeUpdate();
		if (effectedRow > 0) {
			//��ѯ������id
			ps = conn.prepareStatement("select LAST_INSERT_ID()");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				auto_incrementID = rs.getInt(1);
			}
		}
		return auto_incrementID;
	}

	/**
	 * ��ӹؼ��ʣ����ݻظ����͵Ĳ�ͬ�����γɶ��ձ�
	 * @param id	�ظ���Դid��articleID|textID��
	 * @param tempKeywords	�ؼ�������
	 * @param replyType	��"article"|"text"���ظ�����
	 * @return	��ӳɹ����
	 * @throws SQLException 
	 */
	public static boolean addKeywords(int id, String[] tempKeywords, String replyType) throws SQLException {
		boolean isSuccess = false;
		//��ȡ����
		if (conn == null) {
			conn = DatabaseUtil.getConn();
		}
		PreparedStatement ps = null;
		int effectedRows = 0;
		//���ݻظ����͵Ĳ�ͬ����ͬ����
		if ("article".equals(replyType)) {
			//�ظ�����Ϊ����
			ps = conn.prepareStatement("insert into keyword (keyword,reply_type,article_id) values (?,?,?)");
			for (int i = 0; i < tempKeywords.length; ++i) {
				ps.setString(1, tempKeywords[i]);
				ps.setString(2, replyType);
				ps.setInt(3, id);
				effectedRows += ps.executeUpdate();
			}
		} else if ("text".equals(replyType)) {
			//�ظ�����Ϊ����
			ps = conn.prepareStatement("insert into keyword (keyword,reply_type,text_id) values (?,?,?)");
			for (int i = 0; i < tempKeywords.length; ++i) {
				ps.setString(1, tempKeywords[i]);
				ps.setString(2, replyType);
				ps.setInt(3, id);
				effectedRows += ps.executeUpdate();
			}
		}
		
		//�ж��Ƿ���ӳɹ�
		if (effectedRows == tempKeywords.length) {
			isSuccess = true;
		}
		return isSuccess;
	}
	
	/**
	 * ��ӹ��
	 * @param advertisement	���¶���
	 * @return	true if success 
	 * @throws SQLException 
	 */
	public static boolean addAdvertisement(Article advertisement) throws SQLException {
		
		if (conn == null) {
			conn = DatabaseUtil.getConn();
		}
		
		PreparedStatement ps = conn.prepareStatement("insert into ad (title,description,pic_url,ad_url) values (?,?,?,?)");
		ps.setString(1, advertisement.getTitle());
		ps.setString(2, advertisement.getDescription());
		ps.setString(3, advertisement.getPicUrl());
		ps.setString(4, advertisement.getUrl());
		
		int effectedRow = ps.executeUpdate();
		
		return effectedRow > 0? true : false;
	}

	/**
	 * ������ֻظ�����������id
	 * @param content	������Ϣ����
	 * @return	������id
	 * @throws SQLException 
	 */
	public static int addTextReply(String content) throws SQLException {
		int auto_incrementID = 0;
		
		if (conn == null) {
			conn = DatabaseUtil.getConn();
		}
		
		PreparedStatement ps = conn.prepareStatement("insert into text_reply (content) values (?)");
		ps.setString(1, content);
		int effectedRow = ps.executeUpdate();
		
		if (effectedRow > 0) {
			//��ѯ������id
			ps = conn.prepareStatement("select LAST_INSERT_ID()");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				auto_incrementID = rs.getInt(1);
			}
		}
		
		return auto_incrementID;
	}

	/**
	 * �������ֻظ�����
	 * @param content	���ֻظ�����
	 * @param textID	���ֻظ���Ӧ��id
	 * @return	true if success
	 * @throws SQLException 
	 */
	public static boolean updateTextReply(String content, int textID) throws SQLException {
		
		if (conn == null) {
			conn = DatabaseUtil.getConn();
		}
		
		PreparedStatement ps = conn.prepareStatement("update text_reply set content = ? where text_id = ?");
		ps.setString(1, content);
		ps.setInt(2, textID);
		
		int effectedRow = ps.executeUpdate();
		return effectedRow > 0? true : false;
	}
	
	
}
