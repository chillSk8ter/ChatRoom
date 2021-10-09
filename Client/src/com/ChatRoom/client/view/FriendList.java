/**
 * 我的好友列表,(也包括陌生人，黑名单)
 */
package com.ChatRoom.client.view;

import com.ChatRoom.client.tools.ManageChat;
import com.ChatRoom.common.Message;
import com.ChatRoom.common.MessageType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
public class FriendList extends JFrame implements ActionListener,MouseListener {
	//处理第一张卡片.

	JPanel jphy1, jphy2;
	JButton com_chat;
	JScrollPane jsp1;
	String owner;
	//联系人标签样式
	Font font = new Font("微软雅黑", Font.BOLD, 20);
	JLabel[] jb1s;
	public static List<String> onLineFriendList = new ArrayList<>();
	public static String onLineFriend[]= {""};
	public static boolean isOpen;//用于判断群聊窗口状态
	//更新在线的好友情况
	public void upateFriend(Message message) {
		FriendList.onLineFriend= message.getText().split(" ");
		for (int i=0; i < FriendList.onLineFriend.length; i++) {
			FriendList.onLineFriendList.add(FriendList.onLineFriend[i]);
		}
		for (int i = 0; i < FriendList.onLineFriend.length; i++) {
//			System.out.println(FriendList.onLineFriend[i]);
			jb1s[Integer.parseInt(onLineFriend[i]) - 1].setEnabled(true);
		}
	}

	public FriendList(String ownerId) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/imgs/Home.png")));
		FriendList.isOpen = false;
		this.owner = ownerId;
		//处理第一张卡片(显示好友列表)
		com_chat = new JButton("进入群聊");
		com_chat.addActionListener(this);
		jphy1 = new JPanel(new BorderLayout());
		//假定有50个好友
		jphy2 = new JPanel(new GridLayout(50, 1, 4, 4));

		//给jphy2，初始化20好友.
		jb1s = new JLabel[20];

		for (int i = 0; i < jb1s.length; i++) {
//			setIconImage(Toolkit.getDefaultToolkit().getImage(AboutUs.class.getResource("/imgs/people.png")));
			jb1s[i] = new JLabel(i + 1 + "", new ImageIcon(Toolkit.getDefaultToolkit().getImage(FriendList.class.getResource("/imgs/people.png"))), JLabel.LEFT);
			jb1s[i].setFont(font);
			jb1s[i].setEnabled(false);
			if (jb1s[i].getText().equals(ownerId)) {
				jb1s[i].setEnabled(true);
			}
			jb1s[i].addMouseListener(this);
			jphy2.add(jb1s[i]);


		}

		jsp1 = new JScrollPane(jphy2);


		//对jphy1,初始化
		jphy1.add(com_chat, "North");
		jphy1.add(jsp1, "Center");
		this.add(jphy1);
//将个人的群聊加入管理类
		int flag = 0;
		ChatFrame com_chatFrame = new ChatFrame(ownerId, flag);
//		com_chatFrame.setVisible(false);
		ManageChat.addChat(ownerId,com_chatFrame);
		//在窗口显示自己的编号.
		setTitle(ownerId + "的联系人列表");
		setSize(350, 500);
		setLocation(600, 350);
		setResizable(false);
		setVisible(true);


	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == com_chat) {
			if (FriendList.isOpen == false) {
				ChatFrame com_chatFrame = ManageChat.getChat(owner);
				com_chatFrame.setVisible(true);
//			if(ManageChat.isExistChat(owner)) {

//				ManageChat.addChat(owner, com_chatFrame);
				Message message = new Message();
				message.setMesType(MessageType.message_com_chat);
				FriendList.isOpen = true;
			} else {
				JOptionPane.showMessageDialog(null, "该聊天室已存在!", "系统提示", JOptionPane.WARNING_MESSAGE);
			}

		}
	}

	public void mouseClicked(MouseEvent arg0) {
		//响应用户双击的事件，并得到好友的编号.
		if (arg0.getClickCount() == 2) {
			//得到该好友的编号
			String friendNo = ((JLabel) arg0.getSource()).getText();
			new InforCard(this.owner, friendNo);

		}

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		JLabel jl = (JLabel) e.getSource();
		jl.setForeground(Color.red);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		JLabel jl = (JLabel) e.getSource();
		jl.setForeground(Color.black);

	}
}