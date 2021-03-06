package com.example.hendawy.chatapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hendawy.chatapp.R;
import com.example.hendawy.chatapp.model.Contact;
import com.example.hendawy.chatapp.model.ContactModel;
import com.example.hendawy.chatapp.viewholder.ContactHolder;

import java.util.List;


public class ContactListAdapter extends RecyclerView.Adapter<ContactHolder> {

    private OnItemLongClickListener mOnItemLongClickListener;

    public OnItemLongClickListener getmOnItemLongClickListener() {
        return mOnItemLongClickListener;
    }

    private List<Contact> mContacts;
    private Context mContext;
    private static final String LOGTAG = "ContactListAdapter";
    private OnItemClickListener mOnItemClickListener;

    public void setmOnItemLongClickListener(OnItemLongClickListener mOnItemLongClickListener) {
        this.mOnItemLongClickListener = mOnItemLongClickListener;
    }

    public ContactListAdapter(Context context)
    {
        mContacts = ContactModel.get(context).getContacts();
        mContext = context;
        Log.d(LOGTAG,"Constructor of ChatListAdapter , the size of the backing list is :" +mContacts.size());
    }

    public OnItemClickListener getmOnItemClickListener() {
        return mOnItemClickListener;
    }

    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void onContactCountChange() {
        mContacts = ContactModel.get(mContext).getContacts();
        notifyDataSetChanged();
        Log.d(LOGTAG, "ContactListAdapter knows of the change in messages");
    }

    public interface OnItemClickListener {
        public void onItemClick(String contactJid);
    }

    @Override
    public ContactHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater
                .inflate(R.layout.contact_list_item, parent,
                        false);
        return new ContactHolder(view,this);
    }

    @Override
    public void onBindViewHolder(ContactHolder holder, int position) {
        Contact contact = mContacts.get(position);
        holder.bindContact(contact);

    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }

    public interface OnItemLongClickListener {
        public void onItemLongClick(int uniqueId, String contactJid, View anchor);
    }
}
