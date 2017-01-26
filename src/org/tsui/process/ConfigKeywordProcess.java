package org.tsui.process;

import java.sql.SQLException;

import org.tsui.entity.Article;
import org.tsui.entity.Keyword;
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

	/**
	 * ��������Ϣ��ؼ��ʽ��й���
	 * @param content		��������
	 * @param keyword	�ؼ��ʴ�
	 * @return	true if success
	 * @throws SQLException 
	 */
	public boolean addTextReplyProcess(String content, String keyword) throws SQLException {
		
		boolean isSuccess = false;
		
		if ("default".equals(keyword)) {
			//1.��ѯ���ݿ����Ƿ���"default"��¼
			Keyword key = DaoHelper.queryByKeyword("default");
			
			if (key != null) {
				//�������Ĭ�ϻظ���������޸�
				
				//1.��ȡ�ؼ��ʶ�Ӧ�����ֻظ���text_id
				Keyword defaultKey = DaoHelper.queryByKeyword("default");
				int textID = defaultKey.getText_id();
				
				//2.�޸�text_id����Ӧ������
				isSuccess = DaoHelper.updateTextReply(content, textID);
			} else {
				//�����������������Ĭ�����ֻظ�
				
				//1.����ؼ��ʴ�
				String[] tempKeywords = new String[]{"default"};
				
				//2.���Ĭ�����ֻظ�����ȡ������id
				int textID = DaoHelper.addTextReply(content);
				
				//3.���ݻ�ȡ��������id����ؼ���
				isSuccess = DaoHelper.addKeywords(textID, tempKeywords, "text");
			}
		} else {
			//1.����ؼ��ʴ�
			String[] tempKeywords = processKeywords(keyword);
			
			//2.������ֻظ�����ȡ������id
			int textID = DaoHelper.addTextReply(content);
			
			//3.���ݻ�ȡ��������id����ؼ���
			isSuccess = DaoHelper.addKeywords(textID, tempKeywords, "text");
		}
		
		return isSuccess;
	}
}
