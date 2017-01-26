package org.tsui.process;

import java.sql.SQLException;

import org.tsui.entity.Article;
import org.tsui.util.DaoHelper;

/**
 * @author TsuiXh
 *	������ӹؼ��ʶ�Ӧ�ظ�����
 */
public class ConfigKeywordProcess {
	
	/**
	 * ��ͼ�ĺ͹ؼ��ʽ�������
	 * @param article			ͼ��
	 * @param keywords	�ؼ��ֳ���
	 * @return					����Ƿ�ɹ�
	 * @throws SQLException 
	 */
	public boolean addArticleReplyProcess(Article article, String keywords) throws SQLException {
		boolean isSuccess = false;
		/*
		 * ���ͼ�Ĳ�������ؼ����γɹ���
		 */
		//1.����ؼ����ַ���
		String[] tempKeywords = processKeywords(keywords);
		
		//2.������²���ȡ����������id
		int articleID = DaoHelper.addArticle(article);
		//3.���ݻ�õ�����id����ؼ���
		isSuccess = DaoHelper.addKeywords(articleID, tempKeywords, "article");
		
		return isSuccess;
	}
	
	/**
	 * ����ؼ��ʳ���
	 * @param keywords
	 * @return	�ؼ�������
	 */
	private String[] processKeywords(String keywords) {
		return keywords.split("��");
	}
}
