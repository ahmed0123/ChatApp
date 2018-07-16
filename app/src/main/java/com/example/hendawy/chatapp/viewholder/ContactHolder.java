package com.example.hendawy.chatapp.viewholder;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hendawy.chatapp.R;
import com.example.hendawy.chatapp.Xmpp.RoosterConnection;
import com.example.hendawy.chatapp.Xmpp.RoosterConnectionService;
import com.example.hendawy.chatapp.adapter.ContactListAdapter;
import com.example.hendawy.chatapp.model.Contact;

public class ContactHolder extends RecyclerView.ViewHolder {
    private TextView jidTexView;
    private TextView subscriptionTypeTextView;
    private Contact mContact;
    private ImageView profile_image;
    private ContactListAdapter mAdapter;
    private static final String LOGTAG = "ContactHolder";

    public ContactHolder(final View itemView, ContactListAdapter adapter) {
        super(itemView);
        mAdapter = adapter;
        jidTexView = (TextView) itemView.findViewById(R.id.contact_jid_string);
        subscriptionTypeTextView = (TextView) itemView.findViewById(R.id.suscription_type);
        profile_image = (ImageView) itemView.findViewById(R.id.profile_contact);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(LOGTAG,"User clicked on Contact Item");
                ContactListAdapter.OnItemClickListener listener = mAdapter.getmOnItemClickListener();
                if ( listener != null)
                {
                    Log.d(LOGTAG,"Calling the listener method");

                    listener.onItemClick(jidTexView.getText().toString());
                }
            }
        });


        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ContactListAdapter.OnItemLongClickListener listener = mAdapter.getmOnItemLongClickListener();
                if (listener != null) {
                    mAdapter.getmOnItemLongClickListener().onItemLongClick(mContact.getPersistID(), mContact.getJid(), itemView);
                    return true;
                }
                return false;
            }
        });
    }


    public void bindContact(Contact c)
    {
        mContact = c;
        if ( mContact == null)
        {
            return;
        }
        jidTexView.setText(mContact.getJid());
        subscriptionTypeTextView.setText(mContact.getTypeStringValue(mContact.getSubscriptionType()));
        profile_image.setImageResource(R.drawable.ic_profile);

        RoosterConnection rc = RoosterConnectionService.getConnection();
        if (rc != null) {
            String imageAbsPath = rc.getProfileImageAbsolutePath(mContact.getJid());
            if (imageAbsPath != null) {
                Drawable d = Drawable.createFromPath(imageAbsPath);
                profile_image.setImageDrawable(d);
            }
        }

    }
}

