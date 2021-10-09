package com.ChatRoom.client.view;

import com.ChatRoom.client.model.CheckLogin;
import com.ChatRoom.client.tools.ManageClientConServerThread;
import com.ChatRoom.client.tools.ManageFriendList;
import com.ChatRoom.common.Message;
import com.ChatRoom.common.MessageType;
import com.ChatRoom.common.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectOutputStream;

/**
 * @author peijunZhong
 * @Date 2021/5/8
 */


public class Login extends JFrame implements ActionListener {

    private JPanel contentPane;
    private JTextField loginNameTextField;
    private JPasswordField loginPasswordField;

    /**
     * Create the frame.
     */
    public Login(String ip, String port) {
        setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/imgs/Home.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("小小聊天室");
        setBounds(100, 100, 611, 473);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel label = new JLabel("小小聊天室");
        label.setForeground(Color.ORANGE);
        label.setBackground(Color.ORANGE);
        label.setFont(new Font("华文新魏", Font.PLAIN, 28));

        JLabel label_1 = new JLabel("用户名");
        label_1.setFont(new Font("华文新魏", Font.PLAIN, 21));
        label_1.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/imgs/Client.png"))));
        loginNameTextField = new JTextField();
        loginNameTextField.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        loginNameTextField.setColumns(10);

        JLabel label_2 = new JLabel("密码");
        label_2.setFont(new Font("华文新魏", Font.PLAIN, 21));
        label_2.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/imgs/Password.png"))));
        loginPasswordField = new JPasswordField();
        loginPasswordField.setColumns(10);
        JButton loginJb = new JButton("立即登录");
        loginJb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //立即登录
                User user = new User();
                //获得id和密码并出去其中开头和结尾的空格
                user.setUserId(loginNameTextField.getText().trim());
                user.setPassword(loginPasswordField.getText());
                //认证id和password
                if (new CheckLogin().checkUser(user,ip,port)) {
                    try {
//					new AddChatFrame();
                        FriendList friendList = new FriendList(user.getUserId());
                        ManageFriendList.addFriendList(user.getUserId(), friendList);
                        //发送一个要求返回在线好友的请求包.
                        ObjectOutputStream oos = new ObjectOutputStream
                                (ManageClientConServerThread.getClientConServerThread(user.getUserId()).getS().getOutputStream());

                        //做一个Message
                        Message m = new Message();
                        m.setMesType(MessageType.message_get_onLineFriend);
                        //指明我要的是这个qq号的好友情况.
                        m.setSender(user.getUserId());
                        oos.writeObject(m);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }


                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "您输入的id或密码有误!", "提示", JOptionPane.ERROR_MESSAGE);
                    loginNameTextField.setText("");
                    loginPasswordField.setText("");
                }
            }
        });


//			}
//		});
        loginJb.setBackground(Color.ORANGE);
        loginJb.setFont(new Font("华文新魏", Font.PLAIN, 22));

        JButton signJb = new JButton("立即注册");
        signJb.setContentAreaFilled(false);
        signJb.setBorder(null);
        signJb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //注册
                new SignUp().setVisible(true);
            }
        });
        signJb.setForeground(Color.ORANGE);
        signJb.setFont(new Font("华文新魏", Font.PLAIN, 19));

        JButton forgetJb = new JButton("忘记密码");
        forgetJb.setContentAreaFilled(false);
        forgetJb.setBorder(null);
        forgetJb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //忘记密码

            }
        });
        forgetJb.setForeground(new Color(30, 144, 255));
        forgetJb.setFont(new Font("华文新魏", Font.PLAIN, 19));

        JButton infoJb = new JButton("关于我们");
        infoJb.setContentAreaFilled(false);
        infoJb.setBorder(null);
        infoJb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //关于我们
                new AboutUs();
            }
        });
        infoJb.setForeground(new Color(30, 144, 255));
        infoJb.setFont(new Font("华文新魏", Font.PLAIN, 19));
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, gl_contentPane.createSequentialGroup()
                                .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(signJb))
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGap(128)
                                                .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                        .addComponent(loginJb, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
                                                        .addComponent(label_2, GroupLayout.Alignment.LEADING)
                                                        .addGroup(GroupLayout.Alignment.LEADING, gl_contentPane.createSequentialGroup()
                                                                .addComponent(label_1)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                        .addComponent(label)
                                                                        .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                                                .addComponent(loginPasswordField, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                                                                                .addComponent(loginNameTextField, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 228, GroupLayout.PREFERRED_SIZE)))))))
                                .addGap(130))
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGap(120)
                                .addComponent(forgetJb)
                                .addGap(80)
                                .addComponent(infoJb)
                                .addContainerGap(161, Short.MAX_VALUE))
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGap(37)
                                .addComponent(label)
                                .addGap(45)
                                .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(label_1)
                                        .addComponent(loginNameTextField, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
                                .addGap(53)
                                .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(label_2)
                                        .addComponent(loginPasswordField, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
                                .addGap(37)
                                .addComponent(loginJb)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(signJb)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(forgetJb)
                                        .addComponent(infoJb))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        contentPane.setLayout(gl_contentPane);
        this.setLocationRelativeTo(null);//窗体居中显示
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}






