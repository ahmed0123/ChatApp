package com.example.hendawy.chatapp.viewholder;

import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hendawy.chatapp.R;
import com.example.hendawy.chatapp.Xmpp.RoosterConnection;
import com.example.hendawy.chatapp.Xmpp.RoosterConnectionService;
import com.example.hendawy.chatapp.adapter.ChatMessagesAdapter;
import com.example.hendawy.chatapp.model.ChatMessage;
import com.example.hendawy.chatapp.utils.Utilities;

public class ChatMessageViewHolder extends RecyclerView.ViewHolder{

    private static final String LOGTAG = "ChatMessageViewHolder";
    private TextView mMessageBody, mMessageTimestamp;
    private ImageView profileImage;
    private ChatMessage mChatMessage;
    private ChatMessagesAdapter mAdapter;

    public ChatMessageViewHolder(final View itemView, final ChatMessagesAdapter mAdapter) {
        super(itemView);
        mMessageBody = (TextView) itemView.findViewById(R.id.text_message_body);
        mMessageTimestamp = (TextView) itemView.findViewById(R.id.text_message_timestamp);
        profileImage = (ImageView) itemView.findViewById(R.id.profile);

        this.mAdapter = mAdapter;

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ChatMessagesAdapter.OnItemLongClickListener listener = mAdapter.getOnItemLongClickListener();
                if (listener != null) {
                    listener.onItemLongClick(mChatMessage.getPersistID(), itemView);
                }
                return false;
            }
        });
    }

    public void bindChat(ChatMessage chatMessage)
    {
        mChatMessage = chatMessage;
        mMessageBody.setText(chatMessage.getMessage());
        mMessageTimestamp.setText(Utilities.getFormattedTime(chatMessage.getTimestamp()));
        profileImage.setImageResource(R.drawable.ic_profile);

        ChatMessage.Type type = mChatMessage.getType();


        if (type == ChatMessage.Type.RECEIVED) {
            RoosterConnection rc = RoosterConnectionService.getConnection();
            if (rc != null) {
                String imageAbsPath = rc.getProfileImageAbsolutePath(mChatMessage.getContactJid());
                if (imageAbsPath != null) {
                    Drawable d = Drawable.createFromPath(imageAbsPath);
                    profileImage.setImageDrawable(d);
                }

            }
        }

        if (type == ChatMessage.Type.SENT) {
            RoosterConnection rc = RoosterConnectionService.getConnection();
            if (rc != null) {
                String selfJid = PreferenceManager.getDefaultSharedPreferences(mAdapter.getContext())
                        .getString("xmpp_jid", null);

                if (selfJid != null) {
                    Log.d(LOGTAG, "God a valid self jid : " + selfJid);
                    String imageAbsPath = rc.getProfileImageAbsolutePath(selfJid);
                    if (imageAbsPath != null) {
                        Drawable d = Drawable.createFromPath(imageAbsPath);
                        profileImage.setImageDrawable(d);
                    }
                } else {
                    Log.d(LOGTAG, "Could not get a valid self jid ");
                }
            }
        }
    }
}
