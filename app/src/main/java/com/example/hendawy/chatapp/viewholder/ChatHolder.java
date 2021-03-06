package com.example.hendawy.chatapp.viewholder;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hendawy.chatapp.R;
import com.example.hendawy.chatapp.Xmpp.RoosterConnection;
import com.example.hendawy.chatapp.Xmpp.RoosterConnectionService;
import com.example.hendawy.chatapp.adapter.ChatListAdapter;
import com.example.hendawy.chatapp.model.Chat;
import com.example.hendawy.chatapp.utils.Utilities;

public class ChatHolder extends RecyclerView.ViewHolder {

    private static final String LOGTAG = "ChatHolder";
    private TextView contactTextView;
    private TextView messageAbstractTextView;
    private TextView timestampTextView;
    private ImageView profileImage;
    private Chat mChat;
    private ChatListAdapter mChatListAdapter;


    public ChatHolder(final View itemView, ChatListAdapter adapter) {
        super(itemView);

        contactTextView = (TextView) itemView.findViewById(R.id.contact_jid);
        messageAbstractTextView = (TextView) itemView.findViewById(R.id.message_abstract);
        timestampTextView = (TextView) itemView.findViewById(R.id.text_message_timestamp);
        profileImage = (ImageView) itemView.findViewById(R.id.profile);
        mChatListAdapter = adapter;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ChatListAdapter.OnItemClickListener listener = mChatListAdapter.getmOnItemClickListener();

                if (listener != null) {
                    listener.onItemClick(contactTextView.getText().toString(), mChat.getContactType());

                }

                Log.d(LOGTAG, "Clicked on the item in the recyclerView");

            }
        });

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ChatListAdapter.OnItemLongClickListener listener = mChatListAdapter.getOnItemLongClickListener();
                if (listener != null) {
                    listener.onItemLongClick(mChat.getJid(), mChat.getPersistID(), itemView);
                    return true;
                }
                return false;
            }
        });
    }

    public void bindChat(Chat chat) {
        mChat = chat;
        contactTextView.setText(chat.getJid());
        messageAbstractTextView.setText(chat.getLastMessage());
        timestampTextView.setText(Utilities.getFormattedTime(mChat.getLastMessageTimeStamp()));

        profileImage.setImageResource(R.drawable.ic_profile);

        RoosterConnection rc = RoosterConnectionService.getConnection();
        if (rc != null) {
            String imageAbsPath = rc.getProfileImageAbsolutePath(mChat.getJid());
            if (imageAbsPath != null) {
                Drawable d = Drawable.createFromPath(imageAbsPath);
                profileImage.setImageDrawable(d);
            }
        }
    }


}
