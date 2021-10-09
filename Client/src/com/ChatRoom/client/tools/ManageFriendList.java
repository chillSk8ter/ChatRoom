package com.ChatRoom.client.tools;

import com.ChatRoom.client.view.FriendList;

import java.util.HashMap;
public class ManageFriendList {

	private static HashMap friendListHashMap =new HashMap<String, FriendList>();
	
	public static void addFriendList(String owner, FriendList friendList){
		
		friendListHashMap.put(owner, friendList);
	}
	
	public static FriendList getFriendList(String owner)
	{
		return (FriendList) friendListHashMap.get(owner);
	}
}
