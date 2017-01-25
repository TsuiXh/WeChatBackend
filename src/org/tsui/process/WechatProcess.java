package org.tsui.process;

import org.tsui.entity.ReceiveXmlEntity;

/**
 * ΢��xml��Ϣ���������߼�
 * @author TsuiXh
 */
public class WechatProcess {
	public String processWechatMsg(String xml) {
		
		//����xml����
		//TODO:����ReceiveXmlEntity���Լ�ReceiveXmlProcess��
		ReceiveXmlEntity xmlEntity = new ReceiveXmlProcess().getMsgEntity(xml);
		
		/*
		 * ���ݷ���������Ϣ����ȡ��Ҫ�ظ�������
		 * �����Զ��ظ������˽ӿ�
		 * TODO�������Զ��ظ���������RobotProcess
		 */
		String result = "";
		
		//��Ӧ������Ϣ
		if ("text".endsWith(xmlEntity.getMsgType())) {
			result = new RobotProcess().getReplyMsg(xmlEntity.getContent(), xmlEntity.getFromUserName(), xmlEntity.getToUserName());
		}
		//��Ӧ΢���¼�
		if ("event".endsWith(xmlEntity.getMsgType())) {
			result = new RobotProcess().processEvent(xmlEntity.getEvent(), xmlEntity.getFromUserName(), xmlEntity.getToUserName());
		}
		
		return result;
	}
}
