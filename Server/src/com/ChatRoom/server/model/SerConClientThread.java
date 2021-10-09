package com.ChatRoom.server.model;

import com.ChatRoom.common.Message;
import com.ChatRoom.common.MessageType;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
public class SerConClientThread  extends Thread{

	Socket s;
	
	public SerConClientThread(Socket s)
	{
		this.s=s;
	}
	
	public void notifyOther(String iam)
	{
		HashMap hm=ManageClientThread.hm;
		Iterator it=hm.keySet().iterator();
		//得到所有在线的人的线程
		while(it.hasNext())
		{
			Message m=new Message();
			m.setText(iam);
			m.setMesType(MessageType.message_ret_onLineFriend);
			//取出在线人的id
			String onLineUserId=it.next().toString();
			try {
				ObjectOutputStream oos=new ObjectOutputStream(ManageClientThread.getClientThread(onLineUserId).s.getOutputStream());
				m.setGetter(onLineUserId);
				oos.writeObject(m);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
	//这里该线程就可以接收客户端的信息.
	//对从客户端取得的消息进行类型判断，然后做相应的处理

	public void run()
	{

		while(true)
		{			try {
			ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
			Message message=(Message)ois.readObject();
			SerConClientThread sc=ManageClientThread.getClientThread(message.getGetter());

//			//接收到来自收件人的文本类型的信息
			if(message.getMesType().equals(MessageType.message_comm_mes))
			{
				///取得接收人的通信线程
				//一会完成转发.
				ObjectOutputStream oos=new ObjectOutputStream(sc.s.getOutputStream());
				oos.writeObject(message);
			}else if(message.getMesType().equals(MessageType.message_get_onLineFriend))
			{
				//把在服务器的好友给该客户端返回
				String res=ManageClientThread.getAllOnLineUserid();
				Message m2=new Message();
				m2.setMesType(MessageType.message_ret_onLineFriend);
				m2.setText(res);
				m2.setGetter(message.getSender());
				ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
				oos.writeObject(m2);
			}
			//接收到来自收件人的邀请类型的信息
			else if ( (message.getMesType().equals(MessageType.message_invite_dialog))){

				ObjectOutputStream oos=new ObjectOutputStream(sc.s.getOutputStream());
				oos.writeObject(message);
			}
			//接收到来自收件人的拒绝类型的信息
			else if(message.getMesType().equals(MessageType.message_invite_refuse))
			{
				ObjectOutputStream oos = new ObjectOutputStream(sc.s.getOutputStream());
				oos.writeObject(message);
			}
			//接收到来自收件人的同意类型的信息,将jtf设置为true
			else if(message.getMesType().equals(MessageType.message_jtf_true))
			{
				ObjectOutputStream oos = new ObjectOutputStream(sc.s.getOutputStream());
				oos.writeObject(message);
			}
			else if(message.getMesType().equals(MessageType.message_com_chat))
			{
				ObjectOutputStream oos = new ObjectOutputStream(sc.s.getOutputStream());
				oos.writeObject(message);
			}
			else if(message.getMesType().equals(MessageType.message_disFrame))
			{
				ObjectOutputStream oos = new ObjectOutputStream(sc.s.getOutputStream());
				oos.writeObject(message);
			}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
		}
		
		
	}
}
