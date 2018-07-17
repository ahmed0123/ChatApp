package com.example.hendawy.chatapp.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hendawy.chatapp.R;
import com.example.hendawy.chatapp.model.Call;

public class CallHolder extends RecyclerView.ViewHolder {


    public ImageView userImage;
    public TextView userName , lastDateCall;
    private Call mCall;

    public CallHolder(View itemView) {
        super(itemView);

        userImage = itemView.findViewById(R.id.contact_Image);
        userName = itemView.findViewById(R.id.contact_name);
        lastDateCall = itemView.findViewById(R.id.call_Lat_Time);

    }

    public void bindCall(Call call){
        mCall = call;
        userImage.setImageResource(R.drawable.ic_profile);
        userName.setText(call.getJid());
        lastDateCall.setText(""+call.getLastCallTimeStamp());
    }
}
