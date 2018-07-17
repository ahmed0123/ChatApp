package com.example.hendawy.chatapp.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class CallModel {

    private static CallModel sCallModel;
    private Context mContext;

    public static CallModel get(Context context){
        if (sCallModel == null){
            sCallModel = new CallModel(context);

        }

        return sCallModel;
    }

    public CallModel (Context context){
        mContext = context;
    }

    public List<Call> getCallsList(){

        List<Call> calls = new ArrayList<>();
        Call call = new Call("hendawy@xmpp.jp",System.currentTimeMillis(), Call.CallType.IN_COMING);
        calls.add(call);
        Call call1 = new Call("hendawy@xmpp.jp",System.currentTimeMillis(), Call.CallType.IN_COMING);
        calls.add(call1);
        Call call2 = new Call("hendawy@xmpp.jp",System.currentTimeMillis(), Call.CallType.IN_COMING);
        calls.add(call2);
        Call call3 = new Call("hendawy@xmpp.jp",System.currentTimeMillis(), Call.CallType.IN_COMING);
        calls.add(call3);
        Call call4 = new Call("hendawy@xmpp.jp",System.currentTimeMillis(), Call.CallType.IN_COMING);
        calls.add(call4);
        Call call5 = new Call("hendawy@xmpp.jp",System.currentTimeMillis(), Call.CallType.IN_COMING);
        calls.add(call5);
        Call call6 = new Call("hendawy@xmpp.jp",System.currentTimeMillis(), Call.CallType.IN_COMING);
        calls.add(call6);

        return calls;
    }
}
