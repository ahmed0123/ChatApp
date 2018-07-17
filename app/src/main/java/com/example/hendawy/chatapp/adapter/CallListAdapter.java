package com.example.hendawy.chatapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hendawy.chatapp.R;
import com.example.hendawy.chatapp.model.Call;
import com.example.hendawy.chatapp.viewholder.CallHolder;
import com.example.hendawy.chatapp.viewholder.ChatHolder;

import java.util.List;

public class CallListAdapter extends RecyclerView.Adapter<CallHolder> {

    private Context context;
    private List<Call> callList;

    public CallListAdapter(Context context, List<Call> callList) {
        this.context = context;
        this.callList = callList;
    }


    @NonNull
    @Override
    public CallHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater
                .inflate(R.layout.call_list_item, parent,
                        false);
        return new CallHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CallHolder holder, int position) {
        Call call = callList.get(position);
        holder.bindCall(call);

    }

    @Override
    public int getItemCount() {
        return callList.size();
    }
}
