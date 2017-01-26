package org.tsui.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.tsui.entity.Article;
import org.tsui.process.ConfigKeywordProcess;

/**
 * @author TsuiXh
 *	�������ùؼ��ʻظ�
 */
@WebServlet("/ConfigServlet")
public class ConfigServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		//��ȡ�ظ�����
		String reply_type = request.getParameter("reply_type");
		boolean isSuccess = false;
		
		
		if ("article".equals(reply_type)) {
			//���ͼ�Ļظ�
			String title = request.getParameter("title");
			String description = request.getParameter("description");
			String picUrl = request.getParameter("pic_url");
			String url = request.getParameter("url");
			String keyword = request.getParameter("keyword");
			Article article = new Article(title, description, picUrl, url);
			try {
				isSuccess = new ConfigKeywordProcess().addArticleReplyProcess(article, keyword);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if ("text".equals(reply_type)) {
			//������ֻظ�
			
		} else if ("default".equals(reply_type)) {
			//����Ĭ�ϻظ�
		}
		
		if (isSuccess) {
			//��ӳɹ���������ҳ
			request.setAttribute("isSuccess", true);
			request.getRequestDispatcher("itech.jsp").forward(request, response);
		} else {
			//���ʧ�ܵĻ��򷵻ط���������
			response.sendError(500);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
