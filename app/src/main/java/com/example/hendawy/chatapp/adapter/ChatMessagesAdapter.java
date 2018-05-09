package com.example.hendawy.chatapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hendawy.chatapp.R;
import com.example.hendawy.chatapp.model.ChatMessage;
import com.example.hendawy.chatapp.model.ChatMessagesModel;
import com.example.hendawy.chatapp.viewholder.ChatMessageViewHolder;

import java.util.List;

public class ChatMessagesAdapter extends RecyclerView.Adapter<ChatMessageViewHolder> {

    /* Interface to let the view recycler view know that an item has been added so it
     * can scroll down. */
    public interface OnInformRecyclerViewToScrollDownListener {
        public void onInformRecyclerViewToScrollDown( int size);
    }
    private static final int SENT = 1;
    private static final int RECEIVED = 2;
    private static final String LOGTAG ="ChatMessageAdapter" ;

    private List<ChatMessage> mChatMessageList;
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private String contactJid;
    private OnInformRecyclerViewToScrollDownListener mOnInformRecyclerViewToScrollDownListener;


    public void setmOnInformRecyclerViewToScrollDownListener(OnInformRecyclerViewToScrollDownListener mOnInformRecyclerViewToScrollDownListener) {
        this.mOnInformRecyclerViewToScrollDownListener = mOnInformRecyclerViewToScrollDownListener;
    }

    public ChatMessagesAdapter(Context context, String contactJid)
    {
        this.mLayoutInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.contactJid = contactJid;

        mChatMessageList = ChatMessagesModel.get(context).getMessages();


    }

    public void informRecyclerViewToScrollDown()
    {
        mOnInformRecyclerViewToScrollDownListener.onInformRecyclerViewToScrollDown(mChatMessageList.size());
    }

    @Override
    public ChatMessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        switch (viewType)
        {
            case  SENT :
                itemView = mLayoutInflater.inflate(R.layout.chat_message_sent,parent,false);
                return new ChatMessageViewHolder(itemView);
            case RECEIVED:
                itemView = mLayoutInflater.inflate(R.layout.chat_message_received,parent,false);
                return new ChatMessageViewHolder(itemView);
            default:
                itemView = mLayoutInflater.inflate(R.layout.chat_message_sent,parent,false);
                return new ChatMessageViewHolder(itemView);

        }
    }

    @Override
    public void onBindViewHolder(ChatMessageViewHolder holder, int position) {
        ChatMessage chatMessage =mChatMessageList.get(position);
        holder.bindChat(chatMessage);

    }

    @Override
    public int getItemCount() {
        return mChatMessageList.size();
    }

    @Override
    public int getItemViewType(int position) {
        ChatMessage.Type messageType = mChatMessageList.get(position).getType();
        if ( messageType == ChatMessage.Type.SENT)
        {
            return SENT;
        }else{
            return RECEIVED;
        }
    }

    public void onMessageAdd() {
        mChatMessageList = ChatMessagesModel.get(mContext).getMessages();
        notifyDataSetChanged();
        informRecyclerViewToScrollDown();

    }
}
