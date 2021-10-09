/**
 * 这是qq服务器，它在监听，等待某个qq客户端，来连接
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
			System.out.println("服务器已开启,在"+portString+"号端口监听");
			//将字符串类型的端口号转化为整形的端口号
			portInt = Integer.valueOf(portString);
			ServerSocket ss=new ServerSocket(portInt);
			//阻塞,等待连接
			while(true)
			{
				Socket s=ss.accept();

				//接收客户端发来的信息.

				ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
				User user =(User)ois.readObject();
				System.out.println("服务器接收到用户id:"+ user.getUserId()+"  密码:"+ user.getPassword());
				Message m=new Message();
				ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
				if(UserDao.findUser(user))
				{
					//返回一个成功登陆的信息包

					m.setMesType(MessageType.message_succeed);
					oos.writeObject(m);

					//这里就单开一个线程，让该线程与该客户端保持通讯.
					SerConClientThread scct=new SerConClientThread(s);
					ManageClientThread.addClientThread(user.getUserId(), scct);
					//启动与该客户端通信的线程.
					scct.start();
					//并通知其它在线用户.
					scct.notifyOther(user.getUserId());
				}else{
					m.setMesType(MessageType.message_login_fail);
					oos.writeObject(m);
					//关闭Socket
					s.close();

				}


			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{

		}

	}


}
