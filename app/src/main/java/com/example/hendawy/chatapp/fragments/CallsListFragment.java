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
import com.example.hendawy.chatapp.adapter.CallListAdapter;
import com.example.hendawy.chatapp.model.CallModel;
import com.example.hendawy.chatapp.views.DividerDecoration;

/**
 * A simple {@link Fragment} subclass.
 */
public class CallsListFragment extends Fragment {

    private RecyclerView callsRecyclerView;
    CallListAdapter callListAdapter;


    public CallsListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calls_list, container, false);
        callsRecyclerView = (RecyclerView) view.findViewById(R.id.callsRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        callsRecyclerView.setLayoutManager(linearLayoutManager);

        callsRecyclerView.setHasFixedSize(true);
        callsRecyclerView.addItemDecoration(new DividerDecoration(this.getResources().getDrawable(R.drawable.chat_list_item_decorator)));
        callsRecyclerView.setItemAnimator(new DefaultItemAnimator());

        callListAdapter = new CallListAdapter(getActivity(), CallModel.get(getActivity()).getCallsList());

        callsRecyclerView.setAdapter(callListAdapter);

        return view;
    }


}
