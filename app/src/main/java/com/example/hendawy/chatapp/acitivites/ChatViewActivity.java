package com.example.hendawy.chatapp.acitivites;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.hendawy.chatapp.R;
import com.example.hendawy.chatapp.Xmpp.RoosterConnectionService;
import com.example.hendawy.chatapp.adapter.ChatMessagesAdapter;
import com.example.hendawy.chatapp.model.ChatMessage;
import com.example.hendawy.chatapp.model.ChatMessagesModel;
import com.example.hendawy.chatapp.utils.Constants;
import com.example.hendawy.chatapp.views.KeyboardUtil;

public class ChatViewActivity extends AppCompatActivity implements ChatMessagesAdapter.OnInformRecyclerViewToScrollDownListener,KeyboardUtil.KeyboardVisibilityListener {

    RecyclerView chatMessagesRecyclerView ;
    private EditText textSendEditText;
    private ImageButton sendMessageButton;
    ChatMessagesAdapter adapter;
    private String counterpartJid;
    private BroadcastReceiver mReceiveMessageBroadcastReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_view);

        //Get the counterpart Jid
        Intent intent = getIntent();
        counterpartJid = intent.getStringExtra("contact_jid");
        setTitle(counterpartJid);

        chatMessagesRecyclerView = (RecyclerView) findViewById(R.id.chatMessagesRecyclerView);
        chatMessagesRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));

        adapter = new ChatMessagesAdapter(getApplicationContext(),"user@server.com");
        adapter.setmOnInformRecyclerViewToScrollDownListener(this);
        chatMessagesRecyclerView.setAdapter(adapter);

        textSendEditText = (EditText) findViewById(R.id.textinput);
        sendMessageButton = (ImageButton) findViewById(R.id.textSendButton);
        sendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RoosterConnectionService.getConnection().sendMessage(textSendEditText.getText().toString(),counterpartJid);
                adapter.onMessageAdd();
                textSendEditText.getText().clear();


            }
        });

        KeyboardUtil.setKeyboardVisibilityListener(this,this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_chat_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if ( item.getItemId() == R.id.receive_message)
        {
            ChatMessagesModel.get(getApplicationContext()).addMessage(new ChatMessage("This is a message you received from a stranger",System.currentTimeMillis(), ChatMessage.Type.RECEIVED,"user@server.com"));


        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mReceiveMessageBroadcastReceiver);
    }

    @Override
    protected void onResume() {
        super.onResume();

        adapter.informRecyclerViewToScrollDown();

        mReceiveMessageBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                switch (action)
                {
                    case Constants.BroadCastMessages.UI_NEW_MESSAGE_FLAG:
                        adapter.onMessageAdd();
                        return;
                }

            }
        };

        IntentFilter filter = new IntentFilter(Constants.BroadCastMessages.UI_NEW_MESSAGE_FLAG);
        registerReceiver(mReceiveMessageBroadcastReceiver,filter);
    }

    @Override
    public void onInformRecyclerViewToScrollDown(int size) {
        chatMessagesRecyclerView.scrollToPosition(size-1);

    }

    @Override
    public void onKeyboardVisibilityChanged(boolean keyboardVisible) {
        adapter.informRecyclerViewToScrollDown();
    }
}
