package com.example.hendawy.chatapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hendawy.chatapp.R;
import com.example.hendawy.chatapp.model.Chat;
import com.example.hendawy.chatapp.model.ChatModel;
import com.example.hendawy.chatapp.viewholder.ChatHolder;

import java.util.List;

public class ChatListAdapter extends RecyclerView.Adapter<ChatHolder> {

    private static final String LOGTAG = "ChatListAdapter";
    private OnItemLongClickListener onItemLongClickListener;
    private Context mContext;



    List<Chat> chatList;
    private OnItemClickListener mOnItemClickListener;

    public ChatListAdapter(Context context) {
        this.chatList = ChatModel.get(context).getChats();
        this.mContext = context;
    }

    public OnItemLongClickListener getOnItemLongClickListener() {
        return onItemLongClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public OnItemClickListener getmOnItemClickListener() {
        return mOnItemClickListener;
    }

    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void onChatCountChange() {
        chatList = ChatModel.get(mContext).getChats();
        notifyDataSetChanged();
        Log.d(LOGTAG, "ChatListAdapter knows of the change in messages");
    }

    public interface OnItemClickListener {
        public void onItemClick(String contactJid, Chat.ContactType chatType);
    }

    @Override
    public ChatHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater
                .inflate(R.layout.chat_list_item, parent,
                        false);
        return new ChatHolder(view,this);

    }

    @Override
    public void onBindViewHolder(ChatHolder holder, int position) {
        Chat chat = chatList.get(position);
        holder.bindChat(chat);

    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }

    public interface OnItemLongClickListener {
        public void onItemLongClick(String contactJid, int chatUniqueId, View anchor);
    }
}
