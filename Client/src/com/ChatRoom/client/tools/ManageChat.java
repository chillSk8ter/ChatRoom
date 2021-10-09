/**
 * ����һ�������û�����������
 */
package com.ChatRoom.client.tools;

import com.ChatRoom.client.view.ChatFrame;

import java.util.HashMap;
public class ManageChat {

	private static HashMap hm=new HashMap<String, ChatFrame>();
	
	//增加聊天对话框
	public static void addChat(String chatName, ChatFrame chatFrame)
	{
		hm.put(chatName, chatFrame);
	}
	//获取聊天对话框
	public static ChatFrame getChat(String loginIdAnFriendId )
	{
		return (ChatFrame)hm.get(loginIdAnFriendId);
	}
	public static void removeChat(String chatName)
	{
		hm.remove(chatName);
	}
	public static Boolean isExistChat(String loginIdAnFriendId)
	{
		boolean flag=true;
		for(Object name:hm.keySet())
		{
			if(name.equals(loginIdAnFriendId))
			{
				flag=false;
				break;
			}
		}
		return flag;
	}
	public static Boolean isSelfChat1(String own,String fakeFriend)
	{
		boolean flag = true;
		if(own.equals(fakeFriend+"off"))
		{
			flag = false;
		}
		return flag;
	}
	public static Boolean isSelfChat2(String own,String fakeFriend)
	{
		boolean flag = true;
		if(own.equals(fakeFriend))
		{
			flag = false;
		}
		return flag;
	}


}
