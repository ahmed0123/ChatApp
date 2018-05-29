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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.hendawy.chatapp.R;
import com.example.hendawy.chatapp.Xmpp.RoosterConnectionService;
import com.example.hendawy.chatapp.acitivites.ChatListActivity;
import com.example.hendawy.chatapp.acitivites.ChatViewActivity;
import com.example.hendawy.chatapp.acitivites.ContactListActivity;
import com.example.hendawy.chatapp.acitivites.MainActivity;
import com.example.hendawy.chatapp.acitivites.MeActivity;
import com.example.hendawy.chatapp.adapter.ChatListAdapter;
import com.example.hendawy.chatapp.utils.Utilities;
import com.example.hendawy.chatapp.views.DividerDecoration;

/**
 * A simple {@link Fragment} subclass.
 */
public class CallsListFragment extends Fragment {


    public CallsListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calls_list, container, false);


        return view;
    }


}
