/**
 * �����������
 */
package com.ChatRoom.common;

public interface MessageType {

	String message_succeed="1";//登录成功
	String message_login_fail="2";//登录失败
	String message_comm_mes="3";//普遍信息文本
	String message_get_onLineFriend="4";////要求在线好友
	String message_ret_onLineFriend="5";////返回在线好友数量
	String message_invite_dialog ="6";
	String message_invite_refuse="7";//发送拒绝邀请信息
	String message_jtf_true="8";//将邀请者的文本框设为可用
	String message_com_chat="9";//发送群聊请求
	String message_disFrame = "10";//发送关闭聊天窗口信息
}
