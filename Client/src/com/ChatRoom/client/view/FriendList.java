/**
 * �ҵĺ����б�,(Ҳ����İ���ˣ�������)
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
	//�����һ�ſ�Ƭ.

	JPanel jphy1, jphy2;
	JButton com_chat;
	JScrollPane jsp1;
	String owner;
	//��ϵ�˱�ǩ��ʽ
	Font font = new Font("΢���ź�", Font.BOLD, 20);
	JLabel[] jb1s;
	public static List<String> onLineFriendList = new ArrayList<>();
	public static String onLineFriend[]= {""};
	public static boolean isOpen;//�����ж�Ⱥ�Ĵ���״̬
	//�������ߵĺ������
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
		//�����һ�ſ�Ƭ(��ʾ�����б�)
		com_chat = new JButton("����Ⱥ��");
		com_chat.addActionListener(this);
		jphy1 = new JPanel(new BorderLayout());
		//�ٶ���50������
		jphy2 = new JPanel(new GridLayout(50, 1, 4, 4));

		//��jphy2����ʼ��20����.
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


		//��jphy1,��ʼ��
		jphy1.add(com_chat, "North");
		jphy1.add(jsp1, "Center");
		this.add(jphy1);
//�����˵�Ⱥ�ļ��������
		int flag = 0;
		ChatFrame com_chatFrame = new ChatFrame(ownerId, flag);
//		com_chatFrame.setVisible(false);
		ManageChat.addChat(ownerId,com_chatFrame);
		//�ڴ�����ʾ�Լ��ı��.
		setTitle(ownerId + "����ϵ���б�");
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
				JOptionPane.showMessageDialog(null, "���������Ѵ���!", "ϵͳ��ʾ", JOptionPane.WARNING_MESSAGE);
			}

		}
	}

	public void mouseClicked(MouseEvent arg0) {
		//��Ӧ�û�˫�����¼������õ����ѵı��.
		if (arg0.getClickCount() == 2) {
			//�õ��ú��ѵı��
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