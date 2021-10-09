package com.ChatRoom.client.tools;

import java.util.*;
public class ManageClientConServerThread {

	private static HashMap hm=new HashMap<String, ClientConServerThread>();
	
	public static void addClientConServerThread(String owner,ClientConServerThread ccst)
	{
		hm.put(owner, ccst);
	}
	
	public static ClientConServerThread getClientConServerThread(String owner)
	{
		return (ClientConServerThread)hm.get(owner);
	}
}
