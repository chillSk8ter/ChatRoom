/**
 * ���ǿͻ������ӷ������ĺ�̨
 */
package com.ChatRoom.server.model;

import com.ChatRoom.client.tools.ClientConServerThread;
import com.ChatRoom.client.tools.ManageClientConServerThread;
import com.ChatRoom.common.Message;
import com.ChatRoom.common.MessageType;
import com.ChatRoom.common.User;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientConServer {


	public Socket socket;

	public ClientConServer() {

	}

	//���͵�һ������
	public  boolean sendLoginInfoToServer(User user,String ip,String port)
	{
		int portInt = Integer.valueOf(port);
		boolean flag=false;
		try {
			socket =new Socket(ip,portInt);
			ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(user);
			ObjectInputStream ois=new ObjectInputStream(socket.getInputStream());
			Message ms=(Message)ois.readObject();
			//���������֤�û���¼�ĵط�
			if(ms.getMesType().equals(MessageType.message_succeed))
			{
				//�ʹ���һ�����˺źͷ������˱���ͨѶ���ӵ��߳�
				ClientConServerThread ccst=new ClientConServerThread(socket);
				//������ͨѶ�߳�
				ccst.start();
				ManageClientConServerThread.addClientConServerThread
						(user.getUserId(), ccst);
				flag=true;
			}else{
				//�ر�Scoket
				socket.close();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
		}
		return flag;
	}



}
