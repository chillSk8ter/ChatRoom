package com.ChatRoom.client.view;

import Dao.DBUtil;
import Dao.UserDao;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class SignUp extends JFrame {

    private JPanel contentPane;
    private JTextField userNameTextField;
    private JPasswordField passwordField;
    private JPasswordField certainPasswordField;

    public SignUp() {


        setIconImage(Toolkit.getDefaultToolkit().getImage(SignUp.class.getResource("/imgs/register.png")));
        setTitle("\u6CE8\u518C");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 609, 487);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel label = new JLabel("\u804A\u5929\u5BA4\u6CE8\u518C");
        label.setForeground(new Color(250, 128, 114));
        label.setFont(new Font("华文新魏", Font.PLAIN, 29));

        JLabel label_1 = new JLabel("\u7528\u6237\u540D\uFF1A");
        label_1.setFont(new Font("华文新魏", Font.PLAIN, 23));

        userNameTextField = new JTextField();
        userNameTextField.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        userNameTextField.setColumns(10);

        JLabel label_2 = new JLabel("\u5BC6   \u7801\uFF1A");
        label_2.setFont(new Font("华文新魏", Font.PLAIN, 23));

        JLabel label_3 = new JLabel("\u786E\u8BA4\u5BC6\u7801\uFF1A");
        label_3.setFont(new Font("华文新魏", Font.PLAIN, 23));

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("微软雅黑", Font.PLAIN, 18));

        certainPasswordField = new JPasswordField();
        certainPasswordField.setFont(new Font("微软雅黑", Font.PLAIN, 18));

        JButton btnNewButton = new JButton("\u7ACB\u5373\u6CE8\u518C");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //注册
                String ownId = null,password = null,phone_num=null,name=null;
                ownId = userNameTextField.getText();
                password = passwordField.getText();
                phone_num = "12345678911";
                name = "花开富贵";
                String sql1 = "insert into client values (?,?,?,?)";
                String sql2 = "alter table message add talk"+ownId+" VARCHAR(500) NOT NULL";
                String sql3= "insert into message values (?,'','','','','','')";
                try {
                    UserDao.pstmt2 = DBUtil.getPstmt(sql1);
                    UserDao.pstmt2.setString(1, ownId);
                    UserDao.pstmt2.setString(2, password);
                    UserDao.pstmt2.setString(3, phone_num);
                    UserDao.pstmt2.setString(4, name);
                    UserDao.pstmt2.executeUpdate();
                    UserDao.pstmt2 = DBUtil.getPstmt(sql2);
                    UserDao.pstmt2.executeUpdate();
                    UserDao.pstmt2 = DBUtil.getPstmt(sql3);
                    UserDao.pstmt2.setString(1, ownId);
                    UserDao.pstmt2.executeUpdate();


                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                JOptionPane.showMessageDialog(null, "注册成功！", "聊天室",JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
        });
        btnNewButton.setBackground(Color.ORANGE);
        btnNewButton.setFont(new Font("华文新魏", Font.PLAIN, 25));
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGap(189)
                                                .addComponent(label))
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGap(93)
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                                .addComponent(label_2)
                                                                .addGap(5))
                                                        .addComponent(label_1)
                                                        .addComponent(label_3))
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                                .addComponent(certainPasswordField, GroupLayout.PREFERRED_SIZE, 238, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                                .addComponent(userNameTextField, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                                .addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 238, GroupLayout.PREFERRED_SIZE))))
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGap(139)
                                                .addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 283, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(82, Short.MAX_VALUE))
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGap(29)
                                .addComponent(label)
                                .addGap(50)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(label_1)
                                        .addComponent(userNameTextField, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
                                .addGap(50)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                        .addComponent(label_2)
                                        .addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
                                .addGap(46)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(certainPasswordField, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label_3, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                                .addGap(38)
                                .addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                                .addContainerGap())
        );
        contentPane.setLayout(gl_contentPane);
        setVisible(true);
    }
}
