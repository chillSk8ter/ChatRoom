package com.ChatRoom.client.model;

import com.ChatRoom.common.User;
import com.ChatRoom.server.model.ClientConServer;

public class CheckLogin {
	/**
	 * git test
	 *
	 * @param user
	 * @param ip
	 *
	 * @param port
	 * @return
	 */
	private int test = 1;
	public boolean checkUser(User user,String ip,String port)
	{
		return new ClientConServer().sendLoginInfoToServer(user,ip,port);
	}
	
}
