package com.example.hendawy.chatapp.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hendawy.chatapp.R;
import com.example.hendawy.chatapp.Xmpp.RoosterConnectionService;
import com.example.hendawy.chatapp.acitivites.ChatViewActivity;
import com.example.hendawy.chatapp.acitivites.ContactListActivity;
import com.example.hendawy.chatapp.acitivites.MainActivity;
import com.example.hendawy.chatapp.adapter.ChatListAdapter;
import com.example.hendawy.chatapp.utils.Utilities;
import com.example.hendawy.chatapp.views.DividerDecoration;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChatListFragment extends Fragment implements ChatListAdapter.OnItemClickListener {

    private final static String LOGTAG = "ChatList";
    private RecyclerView chatsRecyclerView;
    private FloatingActionButton newConversationButton;

    public ChatListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chat_list, container, false);
        boolean logged_in_state = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext())
                .getBoolean("xmpp_logged_in", false);
        if (!logged_in_state) {
            Log.d(LOGTAG, "Logged in state :" + logged_in_state);
            Intent i = new Intent(getActivity(), MainActivity.class);
            startActivity(i);
            getActivity().finish();
        } else {
            if (!Utilities.isServiceRunning(RoosterConnectionService.class, getActivity().getApplicationContext())) {
                Log.d(LOGTAG, "Service not running, starting it ...");
                //Start the service
                Intent i1 = new Intent(getActivity(), RoosterConnectionService.class);
                getActivity().startService(i1);

            } else {
                Log.d(LOGTAG, "The service is already running.");
            }

        }

        chatsRecyclerView = (RecyclerView) view.findViewById(R.id.chatsRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        chatsRecyclerView.setLayoutManager(linearLayoutManager);

        chatsRecyclerView.setHasFixedSize(true);
        chatsRecyclerView.addItemDecoration(new DividerDecoration(this.getResources().getDrawable(R.drawable.chat_list_item_decorator)));
        chatsRecyclerView.setItemAnimator(new DefaultItemAnimator());

        ChatListAdapter mAdapter = new ChatListAdapter(getActivity().getApplicationContext());
        mAdapter.setmOnItemClickListener(this);
        chatsRecyclerView.setAdapter(mAdapter);

        newConversationButton = (FloatingActionButton) view.findViewById(R.id.new_conversation_floating_button);
        newConversationButton.setBackgroundTintList(getResources().getColorStateList(R.color.colorPrimary));
        newConversationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getActivity(), ContactListActivity.class);
                startActivity(i);

            }
        });


        return view;
    }

    @Override
    public void onItemClick(String contactJid) {

        Intent i = new Intent(getActivity(), ChatViewActivity.class);
        i.putExtra("contact_jid", contactJid);
        startActivity(i);
    }
}
