package com.ChatRoom.client.view;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConnectFrame extends JFrame {

	private JPanel contentPane;
	private JTextField userIpTextField;
	private JTextField portIDTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConnectFrame frame = new ConnectFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ConnectFrame() {
		setTitle("连接服务器");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ConnectFrame.class.getResource("/imgs/Home.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 701, 525);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblid = new JLabel("IP");
		lblid.setFont(new Font("华文新魏", Font.PLAIN, 24));
		lblid.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(FriendList.class.getResource("/imgs/IP.png"))));

		userIpTextField = new JTextField();
		userIpTextField.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		userIpTextField.setColumns(10);

		JLabel label = new JLabel("端口号");
		label.setFont(new Font("华文新魏", Font.PLAIN, 24));
//		label.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(FriendList.class.getResource("/imgs/Port.png"))));
		portIDTextField = new JTextField();
		portIDTextField.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		portIDTextField.setColumns(100);

		JButton button = new JButton("\u8FDE   \u63A5");
		button.setBackground(Color.ORANGE);
		button.setFont(new Font("华文新魏", Font.PLAIN, 22));

		button.setBackground(Color.ORANGE);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//连接
				dispose();
				JOptionPane.showMessageDialog(null, "连接成功!", "小小聊天室",JOptionPane.INFORMATION_MESSAGE);
				new Login(userIpTextField.getText(),portIDTextField.getText()).setVisible(true);
			}
		});
		button.setFont(new Font("华文新魏", Font.PLAIN, 28));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(154)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(label, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(portIDTextField, GroupLayout.PREFERRED_SIZE, 242, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblid)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(userIpTextField, GroupLayout.PREFERRED_SIZE, 242, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(199)
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 258, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(185, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(107)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblid)
						.addComponent(userIpTextField, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(82)
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(74)
							.addComponent(portIDTextField, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
					.addComponent(button)
					.addGap(75))
		);
		contentPane.setLayout(gl_contentPane);
		setLocationRelativeTo(null);//窗体居中显示
	}
}
