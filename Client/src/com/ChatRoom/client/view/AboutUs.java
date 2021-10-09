package com.ChatRoom.client.view;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AboutUs extends JFrame {

	private JPanel contentPane;

	public AboutUs() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(AboutUs.class.getResource("/imgs/introduce.png")));
		setTitle("\u9879\u76EE\u4ECB\u7ECD");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 950, 700);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel label = new JLabel("\u5C0F\u5C0F\u804A\u5929\u5BA4");
		label.setForeground(new Color(250, 128, 114));
		label.setFont(new Font("华文新魏", Font.PLAIN, 28));

		JLabel label_1 = new JLabel("\u9879\u76EE\u4ECB\u7ECD\uFF1A");
		label_1.setFont(new Font("华文新魏", Font.PLAIN, 23));
		JLabel lbljavasejavaswingmysql = new JLabel("\u672C\u804A\u5929\u5BA4\u9879\u76EE\u57FA\u4E8E\u722A\u54C7(JavaSE)\uFF0C\u7FC5\u8180(JavaSwing)没压岁钱了(MySQL\uFF09\u5B9E\u73B0\u5C40\u57DF\u7F51\u7684\u6587\u5B57\u4FE1\u606F\u7684\u4F20\u9012\u3002");

		lbljavasejavaswingmysql.setFont(new Font("宋体", Font.PLAIN, 19));

		JSeparator separator = new JSeparator();

		JLabel label_2 = new JLabel("\u4E3B\u8981\u529F\u80FD\uFF1A");
		label_2.setFont(new Font("华文新魏", Font.PLAIN, 23));

		JLabel label_3 = new JLabel("1.\u767B\u5F55\u8D26\u6237");
		label_3.setFont(new Font("宋体", Font.PLAIN, 20));

		JLabel label_4 = new JLabel("2.\u6CE8\u518C\u8D26\u6237");
		label_4.setFont(new Font("宋体", Font.PLAIN, 20));

		JLabel label_5 = new JLabel("3.\u597D\u53CB\u8D44\u6599\u5361\u7247");
		label_5.setFont(new Font("宋体", Font.PLAIN, 20));

		JLabel label_6 = new JLabel("4.\u5373\u65F6\u804A\u5929");
		label_6.setFont(new Font("宋体", Font.PLAIN, 20));

		JLabel label_7 = new JLabel("5.\u7528\u6237\u7FA4\u804A");
		label_7.setFont(new Font("宋体", Font.PLAIN, 20));

		JSeparator separator_1 = new JSeparator();

		JLabel label_8 = new JLabel("\u9879\u76EE\u6210\u5458\uFF1A");
		label_8.setFont(new Font("华文新魏", Font.PLAIN, 23));

		JLabel label_9 = new JLabel("1.\u8F6F\u4EF62013\u73ED\u949F\u4F69\u96BD");
		label_9.setFont(new Font("宋体", Font.PLAIN, 20));

		JLabel label_10 = new JLabel("2.\u8F6F\u4EF62013\u73ED\u9AD8\u63A5\u6587");
		label_10.setFont(new Font("宋体", Font.PLAIN, 20));

		JLabel label_11 = new JLabel("3.\u8F6F\u4EF62013\u73ED\u9EC4\u6D9B");
		label_11.setFont(new Font("宋体", Font.PLAIN, 20));

		JLabel label_12 = new JLabel("4.\u8F6F\u4EF62013\u73ED\u738B\u5FD7\u660E");
		label_12.setFont(new Font("宋体", Font.PLAIN, 20));

		JLabel label_13 = new JLabel("\u5982\u6709\u96F7\u540C\uFF0C\u7EAF\u5C5E\u5DE7\u5408");
		label_13.setForeground(new Color(85, 107, 47));
		label_13.setBackground(new Color(255, 255, 255));
		label_13.setFont(new Font("华文新魏", Font.PLAIN, 24));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
												.addGap(348)
												.addComponent(label))
										.addGroup(gl_contentPane.createSequentialGroup()
												.addGap(48)
												.addComponent(label_1)))
								.addContainerGap(440, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
								.addContainerGap(36, Short.MAX_VALUE)
								.addComponent(lbljavasejavaswingmysql)
								.addGap(25))
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
								.addGap(142)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(label_3)
										.addComponent(label_7, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
										.addComponent(label_6, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
										.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
										.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED, 249, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(label_9)
										.addComponent(label_10, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
										.addComponent(label_11, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
										.addComponent(label_12, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE))
								.addContainerGap(187, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
								.addGap(43)
								.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
								.addGap(288)
								.addComponent(label_8, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(367, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
								.addGap(336)
								.addComponent(label_13)
								.addContainerGap(376, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
								.addGap(1)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(separator_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 917, Short.MAX_VALUE)
										.addComponent(separator, GroupLayout.DEFAULT_SIZE, 927, Short.MAX_VALUE)))
		);
		gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(36)
								.addComponent(label)
								.addGap(27)
								.addComponent(label_1)
								.addGap(18)
								.addComponent(lbljavasejavaswingmysql)
								.addGap(28)
								.addComponent(separator, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
														.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
														.addComponent(label_8, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(label_3)
												.addGap(29)
												.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
														.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
														.addComponent(label_10, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
												.addGap(33)
												.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
														.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
														.addComponent(label_11, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)))
										.addGroup(gl_contentPane.createSequentialGroup()
												.addGap(43)
												.addComponent(label_9, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)))
								.addGap(33)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(label_6, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
										.addComponent(label_12, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
								.addGap(32)
								.addComponent(label_7, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
								.addGap(40)
								.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGap(44)
								.addComponent(label_13)
								.addContainerGap(112, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		setVisible(true);
		setLocationRelativeTo(null);//窗体居中显示
	}

}
