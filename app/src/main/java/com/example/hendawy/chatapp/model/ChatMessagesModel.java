package com.example.hendawy.chatapp.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;



public class ChatMessagesModel {


    private static ChatMessagesModel sChatMessagesModel;
    private Context context;
    List<ChatMessage> messages;



    public static ChatMessagesModel get(Context context)
    {
        if( sChatMessagesModel == null)
        {
            sChatMessagesModel = new ChatMessagesModel(context);
        }

        return sChatMessagesModel;
    }

    private ChatMessagesModel(Context context)
    {
        this.context = context;
        ChatMessage message1 = new ChatMessage("Hey there",System.currentTimeMillis(), ChatMessage.Type.SENT,"user@server.com");


        ChatMessage message2 = new ChatMessage("How are you doing",System.currentTimeMillis(), ChatMessage.Type.RECEIVED,"user@server.com");

        messages = new ArrayList<>();
        messages.add(message1);
        messages.add(message2);
        messages.add(message1);
        messages.add(message2);
        messages.add(message1);
        messages.add(message2);
    }

    public List<ChatMessage> getMessages()
    {
        return  messages;
    }

    public void addMessage(ChatMessage message)
    {
        messages.add(message);
    }


}
