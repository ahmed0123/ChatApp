package com.example.hendawy.chatapp.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hendawy.chatapp.R;
import com.example.hendawy.chatapp.model.ChatMessage;

public class ChatMessageViewHolder extends RecyclerView.ViewHolder{

    private TextView mMessageBody, mMessageTimestamp;
    private ImageView profileImage;

    public ChatMessageViewHolder(View itemView) {
        super(itemView);
        mMessageBody = (TextView) itemView.findViewById(R.id.text_message_body);
        mMessageTimestamp = (TextView) itemView.findViewById(R.id.text_message_timestamp);
        profileImage = (ImageView) itemView.findViewById(R.id.profile);
    }

    public void bindChat(ChatMessage chatMessage)
    {
        mMessageBody.setText(chatMessage.getMessage());
        mMessageTimestamp.setText(chatMessage.getFormattedTime());
        profileImage.setImageResource(R.drawable.ic_profile);
    }
}
