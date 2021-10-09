/**
 * 这是客户端连接服务器的后台
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

	//发送第一次请求
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
			//这里就是验证用户登录的地方
			if(ms.getMesType().equals(MessageType.message_succeed))
			{
				//就创建一个该账号和服务器端保持通讯连接得线程
				ClientConServerThread ccst=new ClientConServerThread(socket);
				//启动该通讯线程
				ccst.start();
				ManageClientConServerThread.addClientConServerThread
						(user.getUserId(), ccst);
				flag=true;
			}else{
				//关闭Scoket
				socket.close();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
		}
		return flag;
	}



}
