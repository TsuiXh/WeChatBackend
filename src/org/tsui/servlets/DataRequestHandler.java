package org.tsui.servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.tsui.entity.Keyword;
import org.tsui.entity.PageAttr;
import org.tsui.util.DaoHelper;
import org.tsui.util.DaoHelper.CallBack;

import com.google.gson.Gson;

/**
 * �������������Լ��첽�����
 */
@WebServlet("/RequestHandler")
public class DataRequestHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ��������
		String type = request.getParameter("type");
		
		//������
		if ("keyword".equals(type)) {
			int currentPage = Integer.parseInt(request.getParameter("page"));
			int pageSize = Integer.parseInt(request.getParameter("pagesize"));
			
			//���÷�ҳ��ѯ����
			PageAttr pa = new PageAttr();
			pa.setColumns(" key_id,keyword,reply_type");
			pa.setTableName("keyword");
			pa.setPageSize(pageSize);
			pa.setCurrentPage(currentPage);
			
			//ִ�з�ҳ��ѯ��ȡlist����
			try {
				@SuppressWarnings("unchecked")
				List<Keyword> keywordList = DaoHelper.findByPage(pa, new CallBack() {
					
					@Override
					public List<Keyword> getData(ResultSet rs) throws SQLException {
						List<Keyword> tempData = new ArrayList<>();
						while (rs.next()) {
							int key_id = rs.getInt("key_id");
							String keyword = rs.getString("keyword");
							String reply_type = rs.getString("reply_type");
							tempData.add(new Keyword(key_id, keyword, reply_type));
						}
						return tempData;
					}
				}).getData();
				
				//����json���ݷ���
				Gson gson = new Gson();
				String responseText = gson.toJson(keywordList);
				response.getWriter().write(responseText);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		} else if ("article".equals(type)) {
			//TODO:��ѯ���·�ҳ
			
		}
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
