package org.tsui.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.tsui.process.WechatProcess;

/**
 * ΢�ŷ�����շ���Ϣ�ӿ�
 * 
 * @author TsuiXh
 *	MailAddress:	smartcyh@hotmail.com
 */
@WebServlet(description = "΢�ŷ�����շ���Ϣ�ӿ�", urlPatterns = { "/WechatServlet" })
public class WechatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		/*
		 *��ȡ���յ���xml��Ϣ
		 */
		StringBuffer stringBuffer = new StringBuffer();
		InputStream is = request.getInputStream();
		InputStreamReader isr = new InputStreamReader(is, "UTF-8");
		BufferedReader br = new BufferedReader(isr);
		String s = "";
		while ((s = br.readLine()) != null) {
			stringBuffer.append(s);
		}
		//��΢�ŷ��������յ���xml����
		String xml = stringBuffer.toString();
		
		//debug
		System.err.println("���յ�΢�ŷ���������Ϣ" + xml);
		
		String result = "";
		/**
		 * �ж��Ƿ���Ϊ΢�Ž��뼤����֤��ֻ���״���֤ʱ�Ż��ܵ�echostr��������ʱ��Ҫ����ֱ�ӷ���
		 */
		String echostr = request.getParameter("echostr");
		if (echostr != null && echostr.length() > 1) {
			result = echostr;
		} else {
			//����΢���Զ��ظ����߼�
			//WechatProcess���Լ��Զ��ظ��߼�
			result = new WechatProcess().processWechatMsg(xml);
			
			//debug
			System.err.println("���͵Ļظ���Ϣ" + result);
		}
		
		/*
		 * ���ظ�΢�ŷ�������������
		 */
		
		OutputStream os = response.getOutputStream();
		os.write(result.getBytes("UTF-8"));
		os.flush();
		os.close();
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
