package com.ChatRoom.client.view;////import com.ChatRoom.client.tools.ManageChat;

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
import java.io.ObjectOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InforCard extends JFrame {

    private  ChatFrame chatFrame;
    private JPanel contentPane;
    private JTextField currentUserTextField;
    private JTextField userNameTextField;
    private JTextField userPhoneTextField;
    private JTextField userAddressTextField;
    private JTextField textField;
    private JButton offJb;
    private JButton onJb;
    private String ownId, friendId;
    /**
     * Create the frame.
     */
    public InforCard(String ownId, String friendId) {
        this.ownId = ownId;
        this.friendId = friendId;
        setResizable(false);
        setTitle(friendId+"����Ƭ");
        setBounds(100, 100, 589, 522);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel label = new JLabel("\u5F53\u524D\u7528\u6237\uFF1A");
        label.setFont(new Font("������κ", Font.PLAIN, 23));

        currentUserTextField = new JTextField(friendId);
        currentUserTextField.setFont(new Font("������κ", Font.PLAIN, 23));
        currentUserTextField.setColumns(10);
        currentUserTextField.setEditable(false);

        JLabel label_1 = new JLabel("\u7528 \u6237 \u540D \uFF1A");
        label_1.setFont(new Font("������κ", Font.PLAIN, 20));

        userNameTextField = new JTextField(ownId);
        userNameTextField.setFont(new Font("΢���ź�", Font.PLAIN, 18));
        userNameTextField.setColumns(10);
        userNameTextField.setEditable(false);

        JLabel label_2 = new JLabel("\u8054\u7CFB\u7535\u8BDD\uFF1A");
        label_2.setFont(new Font("������κ", Font.PLAIN, 20));

        userPhoneTextField = new JTextField("88888888888");
        userPhoneTextField.setFont(new Font("������κ", Font.PLAIN, 18));
        userPhoneTextField.setColumns(10);
        userPhoneTextField.setEditable(false);

        JLabel label_3 = new JLabel("  \u4F4F  \u5740 \uFF1A");
        label_3.setFont(new Font("������κ", Font.PLAIN, 20));

        userAddressTextField = new JTextField("����ũҵ��ѧ�������ѧԺ");
        userAddressTextField.setFont(new Font("΢���ź�", Font.PLAIN, 18));
        userAddressTextField.setColumns(10);
        userAddressTextField.setEditable(false);

        JLabel label_4 = new JLabel("\u4E2A\u6027\u7B7E\u540D\uFF1A");
        label_4.setFont(new Font("������κ", Font.PLAIN, 20));

        textField = new JTextField("��Ҫ������,��ֻ�Ǹ���˵");
        textField.setFont(new Font("΢���ź�", Font.PLAIN, 18));
        textField.setColumns(10);
        textField.setEditable(false);
        //�����ݿ��л�ȡ�û�������Ϣ
        this.getInfo(ownId);

        offJb = new JButton("��������");
        offJb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //��������

                if (ManageChat.isExistChat(ownId + "off" + friendId) & ManageChat.isSelfChat1(ownId+"off", friendId)) {
                    String flag=" ";
                    //�����������뵽������
                    chatFrame = new ChatFrame(ownId,friendId,flag);
                    ManageChat.addChat(ownId + "off" + friendId, chatFrame);
                }
                else if(ManageChat.isExistChat(ownId+"off"+friendId)==false){
                    JOptionPane.showMessageDialog(null, "���������Լ��������죡", "ϵͳ��ʾ",JOptionPane.WARNING_MESSAGE);
                }
                else if (ownId.equals(friendId)){
                    JOptionPane.showMessageDialog(null, "�����ܸ��Լ����ԣ�", "ϵͳ��ʾ",JOptionPane.WARNING_MESSAGE);

                }


            }
        });
        offJb.setBackground(Color.ORANGE);
        offJb.setFont(new Font("������κ", Font.PLAIN, 20));

        onJb = new JButton("��������");
        onJb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //��������
                if (ManageChat.isExistChat(ownId + " " + friendId) & ManageChat.isSelfChat2(ownId, friendId)) {
                    chatFrame = new ChatFrame(ownId,friendId);
                    ManageChat.addChat(ownId+" "+friendId,chatFrame);
                    Message message = new Message();
                    message.setSender(ownId);
                    message.setGetter(friendId);
                    message.setMesType(MessageType.message_invite_dialog);
                    try {
                        ObjectOutputStream oos=new ObjectOutputStream
                                (ManageClientConServerThread.getClientConServerThread(ownId).getS().getOutputStream());
                        oos.writeObject(message);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
                else if(ManageChat.isExistChat(ownId+" "+friendId)==false){
                    JOptionPane.showMessageDialog(null, "���������Ѵ���!", "ϵͳ��ʾ",JOptionPane.WARNING_MESSAGE);
                }
                else if (ownId.equals(friendId)){
                    JOptionPane.showMessageDialog(null, "���������Լ��������죡", "ϵͳ��ʾ",JOptionPane.WARNING_MESSAGE);

                }

            }
        });
        onJb.setBackground(Color.ORANGE);
        onJb.setFont(new Font("������κ", Font.PLAIN, 20));
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGap(94)
                                                .addComponent(label)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(currentUserTextField, GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE))
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                                .addGap(78)
                                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                                                        .addComponent(label_1)
                                                                        .addComponent(label_2)
                                                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                                                                        .addComponent(label_4)
                                                                                        .addComponent(label_3))
                                                                                .addGap(15))))
                                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                                .addGap(90)
                                                                .addComponent(offJb)))
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                                        .addComponent(userPhoneTextField, Alignment.LEADING, 233, 233, 233)
                                                        .addComponent(userAddressTextField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
                                                        .addComponent(userNameTextField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
                                                        .addComponent(textField, GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
                                                        .addComponent(onJb))))
                                .addGap(115))
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGap(59)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(label)
                                        .addComponent(currentUserTextField, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
                                .addGap(47)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(label_1)
                                        .addComponent(userNameTextField, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
                                .addGap(35)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(label_2)
                                        .addComponent(userPhoneTextField, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
                                .addGap(33)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(label_3)
                                        .addComponent(userAddressTextField, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
                                .addGap(32)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(textField, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label_4))
                                .addGap(23)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(onJb)
                                        .addComponent(offJb))
                                .addContainerGap())
        );
        contentPane.setLayout(gl_contentPane);
        setVisible(true);
    }
    public void getInfo(String ownId) {
        String phone_num=null;
        String name = null;
        String sql1 = "select phone_num from client where id=" + this.friendId;
        String sql2 = "select name from client where id=" + this.friendId;
        try {
            UserDao.pstmt2 = DBUtil.getPstmt(sql1);
            ResultSet rs1 = UserDao.pstmt2.executeQuery();
            while (rs1.next()) {
                phone_num = rs1.getString("phone_num");
            }
            userPhoneTextField.setText(phone_num);
            UserDao.pstmt2 = DBUtil.getPstmt(sql2);
            ResultSet rs2 = UserDao.pstmt2.executeQuery();
            while (rs2.next()) {
                name = rs2.getString("name");
            }
            userNameTextField.setText(name);


        } catch (
                SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}
