package com.ChatRoom.client.model;

import com.ChatRoom.client.tools.ManageClientConServerThread;
import com.ChatRoom.common.Message;
import com.ChatRoom.common.MessageType;

import java.io.IOException;
import java.io.ObjectOutputStream;

public class SendRes_Mes {
    ObjectOutputStream oos;
    public SendRes_Mes(Message m) {
        Message message = new Message();
        message.setGetter(m.getSender());
        message.setSender(m.getGetter());
        message.setMesType(MessageType.message_invite_refuse);
        try {
            oos = new ObjectOutputStream(ManageClientConServerThread.getClientConServerThread(message.getSender()).getS().getOutputStream());

            oos.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
