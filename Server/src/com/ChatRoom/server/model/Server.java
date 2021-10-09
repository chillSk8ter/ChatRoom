/**
 * ����qq�����������ڼ������ȴ�ĳ��qq�ͻ��ˣ�������
 */
package com.ChatRoom.server.model;

import Dao.UserDao;
import com.ChatRoom.common.Message;
import com.ChatRoom.common.MessageType;
import com.ChatRoom.common.User;
import com.ChatRoom.client.view.MyServerFrame;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public Server()
	{
		String portString;
		int portInt;
		try {
			portString= MyServerFrame.getPort();
			System.out.println("�������ѿ���,��"+portString+"�Ŷ˿ڼ���");
			//���ַ������͵Ķ˿ں�ת��Ϊ���εĶ˿ں�
			portInt = Integer.valueOf(portString);
			ServerSocket ss=new ServerSocket(portInt);
			//����,�ȴ�����
			while(true)
			{
				Socket s=ss.accept();

				//���տͻ��˷�������Ϣ.

				ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
				User user =(User)ois.readObject();
				System.out.println("���������յ��û�id:"+ user.getUserId()+"  ����:"+ user.getPassword());
				Message m=new Message();
				ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
				if(UserDao.findUser(user))
				{
					//����һ���ɹ���½����Ϣ��

					m.setMesType(MessageType.message_succeed);
					oos.writeObject(m);

					//����͵���һ���̣߳��ø��߳���ÿͻ��˱���ͨѶ.
					SerConClientThread scct=new SerConClientThread(s);
					ManageClientThread.addClientThread(user.getUserId(), scct);
					//������ÿͻ���ͨ�ŵ��߳�.
					scct.start();
					//��֪ͨ���������û�.
					scct.notifyOther(user.getUserId());
				}else{
					m.setMesType(MessageType.message_login_fail);
					oos.writeObject(m);
					//�ر�Socket
					s.close();

				}


			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{

		}

	}


}
