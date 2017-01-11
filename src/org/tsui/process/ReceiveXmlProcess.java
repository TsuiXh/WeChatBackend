package org.tsui.process;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.tsui.entity.ReceiveXmlEntity;

/**
 * ����΢��xml��Ϣ��������Ϣ����
 * @author TsuiXh
 */
public class ReceiveXmlProcess {
	/**
	 * ����xml��Ϣ
	 * @param strXml
	 * @return
	 */
	public ReceiveXmlEntity getMsgEntity(String strXml) {
		ReceiveXmlEntity msg = null;
		
		if (strXml.length() <= 0 || strXml == null) {
			return null;
		} 
		
		//���ַ���ת��Ϊxml�ĵ�����
		Document document;
		try {
			document = DocumentHelper.parseText(strXml);
			//��ȡ�ĵ��ĸ��ڵ�
			Element root = document.getRootElement();
			//�������ڵ��µ������ӽڵ�
			Iterator<?> iterator = root.elementIterator();
			
			//�������нڵ�
			msg = new ReceiveXmlEntity();
			//���÷�����ƣ�����set����
			//��ȡ��ʵ���Ԫ����
			Class<?> c = Class.forName("org.tsui.entity.ReceiveXmlEntity");
			//����ʵ�����
			msg = (ReceiveXmlEntity)c.newInstance();
			
			while (iterator.hasNext()) {
				Element element = (Element) iterator.next();
				//��ȡset�����еĲ����ֶΣ�ʵ��������ԣ�
				Field field = c.getDeclaredField(element.getName());
				//��ȡset������field.getType()��ȡ���Ĳ�����������
				Method method = c.getDeclaredMethod("set" + element.getName(), field.getType());
				//����set����
				method.invoke(msg, element.getText());
			}
			
		} catch (Exception e) {
			System.out.println("xml��ʽ�쳣" + strXml);
			e.printStackTrace();
		}
		return msg;
	}
}
