package com.example.hendawy.chatapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hendawy.chatapp.R;
import com.example.hendawy.chatapp.model.Chat;
import com.example.hendawy.chatapp.model.ChatModel;
import com.example.hendawy.chatapp.viewholder.ChatHolder;

import java.util.List;

public class ChatListAdapter extends RecyclerView.Adapter<ChatHolder> {

    public interface OnItemClickListener {
        public void onItemClick(String contactJid);
    }


    List<Chat> chatList;
    private OnItemClickListener mOnItemClickListener;

    public ChatListAdapter(Context context) {
        this.chatList = ChatModel.get(context).getChats();
    }

    public OnItemClickListener getmOnItemClickListener() {
        return mOnItemClickListener;
    }

    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
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
}

