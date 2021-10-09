/**
 * ���ǿͻ��˺ͷ������˱���ͨѶ���߳�.
 */
package com.ChatRoom.client.tools;

import com.ChatRoom.client.model.SendRes_Mes;
import com.ChatRoom.client.view.ChatFrame;
import com.ChatRoom.client.view.FriendList;
import com.ChatRoom.common.Message;
import com.ChatRoom.common.MessageType;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientConServerThread extends Thread {

	private Socket s;
//	static ChatFrame chatFrame=null;
	//���캯��
	public ClientConServerThread(Socket s)
	{
		this.s=s;
	}

	public void run()
	{
		while(true) {
			//��ͣ�Ķ�ȡ�ӷ������˷�������Ϣ

			ObjectInputStream ois = null;
			ObjectOutputStream oos = null;
			try {
				ois = new ObjectInputStream(s.getInputStream());
				Message m = (Message) ois.readObject();
				if (m.getMesType().equals(MessageType.message_comm_mes)) {

					//�Ѵӷ����������Ϣ����ʾ������ʾ���������
					ChatFrame chatFrame = ManageChat.getChat(m.getGetter() + " " + m.getSender());
					//��ʾ��Ϣ
					chatFrame.showMessage(m);
				} else if (m.getMesType().equals(MessageType.message_ret_onLineFriend)) {
					System.out.println("�ͻ��˽��յ�" + m.getText());
					String getter = m.getGetter();
					System.out.println("getter=" + getter);
					//�޸���Ӧ�ĺ����б�.
					FriendList friendList = ManageFriendList.getFriendList(getter);
					//�������ߺ���.
					if (friendList != null) {
						friendList.upateFriend(m);
					}
				} else if ((m.getMesType().equals(MessageType.message_invite_dialog)))
				{
						String invite = m.getSender() + "�������������";
						Object[] options = {"����", "�ܾ�"};
//
//						//����������ʾ
						int op = JOptionPane.showOptionDialog(null, invite, "������", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
//						//��������ܡ�
						if (op == JOptionPane.YES_OPTION) {
							 ChatFrame chatFrame = new ChatFrame(m.getGetter(), m.getSender());
							chatFrame.chatSendTextField.setEnabled(true);
							ManageChat.addChat(m.getGetter() + " " + m.getSender(), chatFrame);
							Message message = new Message();
							message.setSender(m.getGetter());
							message.setGetter(m.getSender());
							message.setMesType(MessageType.message_jtf_true);
							try {
								oos = new ObjectOutputStream(ManageClientConServerThread.getClientConServerThread(message.getSender()).getS().getOutputStream());
								oos.writeObject(message);
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
						//����ܾ�
						else if (op == JOptionPane.NO_OPTION) {

							new SendRes_Mes(m);


					}
				}
				else if(m.getMesType().equals(MessageType.message_invite_refuse))
				{
					JOptionPane.showMessageDialog(null, "�Է��ܾ��������룡", "������",JOptionPane.WARNING_MESSAGE);
					ManageChat.removeChat(m.getGetter()+" "+m.getSender());
					ManageChat.getChat(m.getGetter() + " " + m.getSender()).dispose();
					ManageChat.removeChat(m.getGetter() + " " + m.getSender());
				}
				else if (m.getMesType().equals(MessageType.message_jtf_true))
				{
					ManageChat.getChat(m.getGetter()+" "+m.getSender()).chatSendTextField.setEnabled(true);
				}
				else if(m.getMesType().equals(MessageType.message_com_chat))
				{
					ChatFrame chatFrame = ManageChat.getChat(m.getGetter());
					chatFrame.show_Com_Message(m);

				}
				else if (m.getMesType().equals(MessageType.message_disFrame))
				{
					JOptionPane.showMessageDialog(null, m.getSender()+"�˳���������!", "������",JOptionPane.WARNING_MESSAGE);
//					ClientConServerThread.chatFrame.dispose();
					ManageChat.getChat(m.getGetter()+" "+m.getSender()).dispose();
					ManageChat.removeChat(m.getGetter()+" "+m.getSender());
				}
			} catch (Exception e) {
					e.printStackTrace();
				}

			}

	}


	public Socket getS() {
		return s;
	}

	public void setS(Socket s) {
		this.s = s;
	}
	
}
