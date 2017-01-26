package org.tsui.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.tsui.entity.Article;

/**
 * @author TsuiXh
 *	�������ùؼ��ʻظ�
 */
@WebServlet("/ConfigServlet")
public class ConfigServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ�ظ�����
		String reply_type = request.getParameter("reply_type");
		
		if ("article".equals(reply_type)) {
			//���ͼ�Ļظ�
			String title = request.getParameter("title");
			String description = request.getParameter("description");
			String picUrl = request.getParameter("pic_url");
			String url = request.getParameter("url");
			String keyword = request.getParameter("keyword");
			Article article = new Article(title, description, picUrl, url);
			
		} else if ("text".equals(reply_type)) {
			//������ֻظ�
		} else if ("default".equals(reply_type)) {
			//����Ĭ�ϻظ�
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
