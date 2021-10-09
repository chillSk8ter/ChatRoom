package com.ChatRoom.client.view;

import com.ChatRoom.server.model.Server;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyServerFrame extends JFrame {

	public static  JPanel contentPane;
	 public static  JTextField ipAddressTextField;
	 public static  JLabel label;
	 public static  JTextField portNumbertextField;
	 public static  JLabel label_1;
	public static  JTextField userInitNumberTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyServerFrame frame = new MyServerFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();

				}
			}
		});
	}

	public static String getIpAdress()
	{
		String ipAdress;
		ipAdress = MyServerFrame.ipAddressTextField.getText().trim();
		return ipAdress;
	}
	public static String getPort()
	{
		String port;
		port = MyServerFrame.portNumbertextField.getText().trim();
		return port;
	}

	/**
	 * Create the frame.
	 */
	public MyServerFrame() {
		setFont(new Font("微软雅黑", Font.BOLD, 14));
		setIconImage(Toolkit.getDefaultToolkit().getImage(MyServerFrame.class.getResource("/imgs/Home.png")));
		setResizable(false);
		setTitle("\u804A\u5929\u5BA4\u670D\u52A1\u5668");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 686, 493);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		
		JLabel lblIp = new JLabel("IP \u5730 \u5740\uFF1A");

		lblIp.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(FriendList.class.getResource("/imgs/IP.png"))));

		lblIp.setFont(new Font("华文新魏", Font.PLAIN, 22));
		
		ipAddressTextField = new JTextField();
		ipAddressTextField.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		ipAddressTextField.setColumns(10);
		label = new JLabel("\u7AEF\u53E3\u53F7\uFF1A");
		label.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(FriendList.class.getResource("/imgs/Port.png"))));

		label.setFont(new Font("华文新魏", Font.PLAIN, 22));
		
		portNumbertextField = new JTextField();
		portNumbertextField.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		portNumbertextField.setColumns(10);
		
		label_1 = new JLabel("\u9ED8\u8BA4\u7528\u6237\u6570\u91CF\uFF1A");
		label_1.setFont(new Font("华文新魏", Font.PLAIN, 22));
		
		userInitNumberTextField = new JTextField();
		userInitNumberTextField.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		userInitNumberTextField.setColumns(10);
		
		JButton startServerButton = new JButton("\u5F00\u542F\u670D\u52A1");
		startServerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//开启服务
				if(e.getSource()==startServerButton)
				{
					new Server();
				}
				
			}
		});
		startServerButton.setBackground(Color.ORANGE);
		startServerButton.setFont(new Font("华文新魏", Font.PLAIN, 23));
		
		JButton stopServerButton = new JButton("\u5173\u95ED\u670D\u52A1");
		stopServerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//关闭服务
				dispose();
			}
		});
		stopServerButton.setBackground(Color.ORANGE);
		stopServerButton.setFont(new Font("华文新魏", Font.PLAIN, 23));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(102)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(startServerButton)
						.addComponent(label_1)
						.addComponent(label)
						.addComponent(lblIp))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(111)
							.addComponent(stopServerButton))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(userInitNumberTextField)
								.addComponent(portNumbertextField)
								.addComponent(ipAddressTextField, GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE))))
					.addContainerGap(144, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(67)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblIp)
						.addComponent(ipAddressTextField, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
					.addGap(64)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(portNumbertextField, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
					.addGap(72)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(userInitNumberTextField, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(stopServerButton)
						.addComponent(startServerButton))
					.addGap(30))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
