package com.ChatRoom.client.view;///**
import Dao.DBUtil;
import Dao.UserDao;
import com.ChatRoom.client.tools.ManageChat;
import com.ChatRoom.client.tools.ManageClientConServerThread;
import com.ChatRoom.common.Message;
import com.ChatRoom.common.MessageType;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class ChatFrame extends JFrame {

	private JPanel contentPane;
	public JTextField chatSendTextField;
	private String ownId, friendId;
	public JTextArea chatTextArea;
	private JScrollPane jScrollPane;
	/**
	 * Create the frame.
	 */
	//	离线留言窗口初始化
	public ChatFrame(String ownId, String friendId, String flag) {
		this.ownId = ownId;
		this.friendId = friendId;
		flag = " ";
		this.setTitle(ownId + " 正在给 " + friendId + " 留言");
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 611, 426);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		chatSendTextField = new JTextField();
		chatSendTextField.setColumns(10);
		chatTextArea = new JTextArea();
		chatTextArea.setEditable(false);
		chatTextArea.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		jScrollPane = new JScrollPane(chatTextArea);
		JButton sendJb = new JButton("\u53D1  \u9001");
		sendJb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//发送
				certainActionOffline();

			}
			private void certainActionOffline() {
				String offMessage=chatSendTextField.getText().trim();
				String selfMessage = "你对" + friendId + "说: " + offMessage + "【离线消息】\n";
				chatSendTextField.setText("");
				chatTextArea.append(selfMessage);
				String text = selfMessage.replace("\n", "##");
				String sql = "update message set " + "talk" + friendId + "=CONCAT(talk" + friendId + ",?) where id =?";
				try {
					UserDao.pstmt2 = DBUtil.getPstmt(sql);
					UserDao.pstmt2.setString(1, text);
					UserDao.pstmt2.setString(2, ownId);
					UserDao.pstmt2.executeUpdate();

				} catch (SQLException throwables) {
					throwables.printStackTrace();
				}
				try {
					String text2 = ownId + "对你说：" + offMessage + "【离线消息】\n";
					String text3 = text2.replace("\n", "##");
					String sql2 = "update message set " + "talk" + ownId + "=CONCAT(talk" + ownId + ",?) where id =?";
					UserDao.pstmt2 = DBUtil.getPstmt(sql2);
					UserDao.pstmt2.setString(1, text3);
					UserDao.pstmt2.setString(2, friendId);
					UserDao.pstmt2.executeUpdate();
				}catch (SQLException throwables)
				{
					throwables.printStackTrace();
				}


			}

		});
		sendJb.setBackground(Color.ORANGE);
		sendJb.setFont(new Font("华文新魏", Font.PLAIN, 23));


		JButton disJb = new JButton("\u5173\u95ED\u7A97\u53E3");
		disJb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//关闭窗口
				disNremove(ownId+"off"+friendId);


			}
			private void disNremove(String chatName)
			{
				dispose();
				ManageChat.removeChat(chatName);
			}

		});
		disJb.setBackground(Color.ORANGE);
		disJb.setFont(new Font("华文新魏", Font.PLAIN, 23));

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
								.addContainerGap()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(chatTextArea, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 553, Short.MAX_VALUE)
										.addGroup(gl_contentPane.createSequentialGroup()
												.addComponent(disJb)
												.addGap(18)
												.addComponent(chatSendTextField, GroupLayout.PREFERRED_SIZE, 276, GroupLayout.PREFERRED_SIZE)
												.addGap(18)
												.addComponent(sendJb, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)))
								.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
								.addContainerGap()
								.addComponent(chatTextArea, GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(disJb, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addGroup(Alignment.TRAILING, gl_contentPane.createParallelGroup(Alignment.BASELINE)
												.addComponent(sendJb, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
												.addComponent(chatSendTextField, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)))
								.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
		setVisible(true);
		ShowHistroyMessage(ownId, friendId);
	}
	//	初始化在线聊天窗口
	public ChatFrame(String ownId,String friendId)
	{
		this.ownId = ownId;
		this.friendId = friendId;
		setTitle("你 正在和 " + friendId + " 聊天");
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 611, 426);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		chatSendTextField = new JTextField();
		chatSendTextField.setColumns(10);
		chatSendTextField.setEnabled(false);
		chatTextArea = new JTextArea();
		chatTextArea.setEditable(false);
		chatTextArea.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		JScrollPane jsp = new JScrollPane(chatTextArea);
		jsp.setBounds(100,100,100,100);
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		JButton sendJb = new JButton("发送");
		sendJb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				certainAction();
			}
						private void certainAction() {
				//如果用户点击了，发送按钮
				com.ChatRoom.common.Message message = new Message();
				message.setMesType(MessageType.message_comm_mes);
				message.setSender(ownId);
				message.setGetter(friendId);
				message.setText(chatSendTextField.getText());
				message.setSendTime(new Date().toString());
				//
				String selfMessage = "你对" + message.getGetter() + "说: " + message.getText() + "\n";
				chatSendTextField.setText("");
				chatTextArea.append(selfMessage);
				String text = selfMessage.replace("\n", "##");
				String sql = "update message set " + "talk" + message.getGetter() + "=CONCAT(talk" + message.getGetter() + ",?) where id =?";
				try {
					UserDao.pstmt2 = DBUtil.getPstmt(sql);
					UserDao.pstmt2.setString(1, text);
					UserDao.pstmt2.setString(2, message.getSender());
					UserDao.pstmt2.executeUpdate();
				} catch (SQLException throwables) {
					throwables.printStackTrace();
				}

				//发送给服务器.
				try {
					ObjectOutputStream oos = new ObjectOutputStream
							(ManageClientConServerThread.getClientConServerThread(ownId).getS().getOutputStream());
					oos.writeObject(message);
				} catch (Exception e) {
					e.printStackTrace();
				}


			}



		});
		JButton disJb = new JButton("退出聊天");
		disJb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//关闭窗口
				disNremove(ownId+" "+friendId);
			}
			private void disNremove(String chatName)
			{
				dispose();
				try {
					ManageChat.removeChat(chatName);
					ObjectOutputStream oos = new ObjectOutputStream(ManageClientConServerThread.getClientConServerThread(ownId).getS().getOutputStream());
					Message message = new Message();
					message.setSender(ownId);
					message.setGetter(friendId);
					message.setMesType(MessageType.message_disFrame);
					oos.writeObject(message);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		disJb.setBackground(Color.ORANGE);
		disJb.setFont(new Font("华文新魏", Font.PLAIN, 23));

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
								.addContainerGap()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(jsp, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 553, Short.MAX_VALUE)
										.addGroup(gl_contentPane.createSequentialGroup()
												.addComponent(disJb)
												.addGap(18)
												.addComponent(chatSendTextField, GroupLayout.PREFERRED_SIZE, 276, GroupLayout.PREFERRED_SIZE)
												.addGap(18)
												.addComponent(sendJb, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)))
								.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
								.addContainerGap()
								.addComponent(jsp, GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(disJb, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addGroup(Alignment.TRAILING, gl_contentPane.createParallelGroup(Alignment.BASELINE)
												.addComponent(sendJb, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
												.addComponent(chatSendTextField, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)))
								.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
		setVisible(true);
		ShowHistroyMessage(ownId, friendId);
	}
	//	初始化群聊窗口
	public ChatFrame(String ownId,int flag)
	{
		setTitle(ownId+"的群聊");
		setVisible(true);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 611, 426);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		chatSendTextField = new JTextField();
		chatSendTextField.setColumns(10);
		chatTextArea = new JTextArea();
		chatTextArea.setEditable(false);
		chatTextArea.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		JScrollPane jsp = new JScrollPane(chatTextArea);
		jsp.setBounds(100,100,100,100);
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		JButton sendJb = new JButton("\u53D1  \u9001");
		sendJb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				certainAction();
			}

			private void certainAction() {
				//如果用户点击了，发送按钮
				Message message = new Message();
				message.setMesType(MessageType.message_com_chat);
				message.setSender(ownId);
				message.setText(chatSendTextField.getText());
				message.setSendTime(new Date().toString());
				String selfMessage = "我 说: " + message.getText() + "\n";
				chatSendTextField.setText("");
				chatTextArea.append(selfMessage);
				for (int i = 0; i < FriendList.onLineFriendList.size(); i++)
				{
					System.out.println(FriendList.onLineFriendList.size());
					if(!FriendList.onLineFriendList.get(i).equals(ownId)) {
						message.setGetter(FriendList.onLineFriendList.get(i));
						//
						String text = selfMessage.replace("\n", "##");
						String sql = "update message set community = CONCAT(community,?) where id =?";
						try {
							UserDao.pstmt2 = DBUtil.getPstmt(sql);
							UserDao.pstmt2.setString(1, text);
							UserDao.pstmt2.setString(2, message.getSender());
							UserDao.pstmt2.executeUpdate();
						} catch (SQLException throwables) {
							throwables.printStackTrace();
						}

						//发送给服务器.
						try {
							ObjectOutputStream oos = new ObjectOutputStream
									(ManageClientConServerThread.getClientConServerThread(ownId).getS().getOutputStream());
							oos.writeObject(message);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

			} }

		});
		JButton disJb = new JButton("\u5173\u95ED\u7A97\u53E3");
		disJb.setBackground(Color.WHITE);
		disJb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//关闭窗口
				dis(ownId);
			}
			//该方法用于将群聊窗口隐藏,实际并未关闭窗口进程
			private void dis(String chatName)
			{
				setVisible(false);
				FriendList.isOpen = false;
			}
		});
		disJb.setBackground(Color.ORANGE);
		disJb.setFont(new Font("华文新魏", Font.PLAIN, 23));

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
								.addContainerGap()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(jsp, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 553, Short.MAX_VALUE)
										.addGroup(gl_contentPane.createSequentialGroup()
												.addComponent(disJb)
												.addGap(18)
												.addComponent(chatSendTextField, GroupLayout.PREFERRED_SIZE, 276, GroupLayout.PREFERRED_SIZE)
												.addGap(18)
												.addComponent(sendJb, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)))
								.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
								.addContainerGap()
								.addComponent(jsp, GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(disJb, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addGroup(Alignment.TRAILING, gl_contentPane.createParallelGroup(Alignment.BASELINE)
												.addComponent(sendJb, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
												.addComponent(chatSendTextField, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)))
								.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
		setVisible(false);
		Show_com_HistroyMessage(ownId);

	}

	public void showMessage(com.ChatRoom.common.Message message) {

		String text = message.getSender() + "说: " + message.getText() + "##";
		String sql = "update message set " + "community=CONCAT(community,?) where id =?";
		try {
			UserDao.pstmt2 = DBUtil.getPstmt(sql);
			UserDao.pstmt2.setString(1, text);
			UserDao.pstmt2.setString(2, message.getGetter());
			UserDao.pstmt2.executeUpdate();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		text=text.substring(0,text.length()-2);
		this.chatTextArea.append(text+"\n");
	}
	public void ShowHistroyMessage(String ownerId, String friend)
	{
		String historyTexts = null;//历史聊天记录(未换行)
		String historyText;//(换行后)
		String sql="select talk"+friend+" from message where id="+ownerId;
		try {
//					UserDao.pstmt2.setString(1, ownerId);
			UserDao.pstmt2 = DBUtil.getPstmt(sql);
			ResultSet rs = UserDao.pstmt2.executeQuery();
			while (rs.next())
			{
				historyTexts = rs.getString("talk"+friend);
			}
			historyText = historyTexts.replace("##", "\n");
			this.chatTextArea.append(historyText);
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}
	public void Show_com_HistroyMessage(String ownerId)
	{
		String historyTexts = null;//历史聊天记录(未换行)
		String historyText = null;//(换行后)
		String sql="select community from message where id="+ownerId;
		try {
			UserDao.pstmt2 = DBUtil.getPstmt(sql);
			ResultSet rs = UserDao.pstmt2.executeQuery();
			while (rs.next())
			{
				historyTexts = rs.getString("community");
			}
			if(historyText!=null) {
				historyText = historyTexts.replace("##", "\n");
				this.chatTextArea.append(historyText);
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}


	public void show_Com_Message(com.ChatRoom.common.Message message) {
		if (!message.getSender().equals(ownId)) {
			String text = message.getSender() + " 说: " + message.getText() + "##";
			String sql = "update message set community = CONCAT(community,?) where id =?";
			try {
				UserDao.pstmt2 = DBUtil.getPstmt(sql);
				UserDao.pstmt2.setString(1, text);
				UserDao.pstmt2.setString(2, message.getGetter());
				UserDao.pstmt2.executeUpdate();
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
			text = text.substring(0, text.length() - 2);
			this.chatTextArea.append(text + "\n");
		}
	}
}
