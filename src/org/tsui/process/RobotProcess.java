package org.tsui.process;

import java.sql.SQLException;
import java.util.ArrayList;

import org.tsui.entity.Article;
import org.tsui.entity.Keyword;
import org.tsui.util.DaoHelper;

/**
 * �Զ��ظ��ӿ�
 * @author TsuiXh
 */
public class RobotProcess {
	
	/**
	 * �Զ��ظ���Ϣ
	 * @param content
	 * @param to
	 * @param from
	 * @return
	 */
	public String getReplyMsg(String content, String to, String from) {

		String result = "";
		try {
			//��ѯ�ؼ���
			Keyword keyword = DaoHelper.queryByKeyword(content);
			
			//���û�ж�Ӧ�ؼ���
			if (keyword == null) {
				//�ظ�Ĭ����Ϣ��ֻ����һ��Ĭ����Ϣ��
				content = "default";
				//�����ڹؼ��ʱ������Ĭ�ϻظ�
				keyword = DaoHelper.queryByKeyword(content);
				result = getReplyByKeyword(keyword, to, from);
			}else if (keyword != null) {
				//���ݹؼ��ʻ�ȡ�ظ����ظ�ͼ�Ļ���������Ϣ
				result = getReplyByKeyword(keyword, to, from);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * ����΢���¼������û���ע���ظ�ͼ����Ϣ��
	 * @param event
	 * @param to	���͵�
	 * @param from	���ͷ�
	 * @return
	 */
	public String processEvent(String event, String to, String from) {
		String result = "";
		ArrayList<Article> articles = null;
		if ("subscribe".endsWith(event)) {
			try {
				articles = DaoHelper.queryArticles4Subscrible();
				//debug
				System.out.println("��ѯ������������" + articles.size());
				//FormatXml
				if (articles.size() > 0) {
					result = new FormatXmlProcess().formatArticleAnswer(to, from, articles);
					//debug
					System.out.println("׼�����͵���Ϣ��"+ result);
				} else {
					//������ݿ���û������ͼ�Ļظ�����ظ�Ĭ��������Ϣ
					String content = "��ӭ��������������ظ���Ŀ�鿴������Ϣ";
					result = new FormatXmlProcess().formatTextAnswer(to, from, content);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if ("unsubscribe".equals(event)) {
			//TODO���պ�ͳ������
			System.out.println("һλ�û�ȡ���˹�ע");
		}
		
		
		return result;
	}
	
	/**
	 * �������ݹؼ��������ɻظ���xml��ʽ����Ϣ��ͼ�ģ����֣�
	 * @param keyword	�ؼ��ʶ���
	 * @param to				���͵�
	 * @param from			���ͷ�
	 * @return					��װ��xml��ʽ�Ļظ���Ϣ��ͼ�ģ����֣�
	 * @throws SQLException 
	 */
	String getReplyByKeyword(Keyword keyword, String to, String from) throws SQLException {
		String result  = "";
		String reply_type = keyword.getReply_type();
		//�ظ�ͼ��
		if ("article".equals(reply_type)) {
			//��ȡͼ�Ļظ�
			Article article = DaoHelper.queryArticleById(keyword.getArticle_id());
			//��ȡ���
			Article advertisement = DaoHelper.queryCurrentAdvertisement();
			//��װͼ��
			ArrayList<Article> articles = new ArrayList<>();
			articles.add(article);
			if (advertisement != null) {
				articles.add(advertisement);
			}
			//��װxml��Ϣ
			result = new FormatXmlProcess().formatArticleAnswer(to, from, articles);
		} else if ("text".equals(reply_type)) {
			//��ȡ���ֻظ�
			int text_id = keyword.getText_id();
			String reply_content = DaoHelper.queryTextReplyById(text_id);
			
			//����ظ���Ϣ�к��л��еĻ����Ի����ַ����д���
			if (reply_content.contains("\\n")) {
				reply_content = processEnter(reply_content);
			}
			
			result = new FormatXmlProcess().formatTextAnswer(to, from, reply_content);
		}
		
		return result;
	}

	/**
	 * �滻�ַ����е�\nΪ����
	 * @param reply_content	��Ҫ������ַ���
	 * @return	�滻����ַ���
	 */
	private String processEnter(String reply_content) {
		return reply_content.replaceAll("\\\\n", "\n");
	}
}
