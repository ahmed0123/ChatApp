package com.example.hendawy.chatapp.acitivites;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.hendawy.chatapp.R;
import com.example.hendawy.chatapp.Xmpp.RoosterConnectionService;
import com.example.hendawy.chatapp.adapter.ChatListAdapter;
import com.example.hendawy.chatapp.utils.Utilities;
import com.example.hendawy.chatapp.views.DividerDecoration;

public class ChatListActivity extends AppCompatActivity implements ChatListAdapter.OnItemClickListener {

    private RecyclerView chatsRecyclerView;
    private FloatingActionButton newConversationButton;
    private final static String LOGTAG = "ChatList";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);

        boolean logged_in_state = PreferenceManager.getDefaultSharedPreferences(getApplicationContext())
                .getBoolean("xmpp_logged_in",false);
        if(!logged_in_state)
        {
            Log.d(LOGTAG,"Logged in state :"+ logged_in_state );
            Intent i = new Intent(ChatListActivity.this,MainActivity.class);
            startActivity(i);
            finish();
        }else
        {
            if(!Utilities.isServiceRunning(RoosterConnectionService.class,getApplicationContext()))
            {
                Log.d(LOGTAG,"Service not running, starting it ...");
                //Start the service
                Intent i1 = new Intent(this,RoosterConnectionService.class);
                startService(i1);

            }else
            {
                Log.d(LOGTAG ,"The service is already running.");
            }

        }

        chatsRecyclerView = (RecyclerView) findViewById(R.id.chatsRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        chatsRecyclerView.setLayoutManager(linearLayoutManager);

        chatsRecyclerView.setHasFixedSize(true);
        chatsRecyclerView.addItemDecoration(new DividerDecoration(this.getResources().getDrawable(R.drawable.chat_list_item_decorator)));
        chatsRecyclerView.setItemAnimator(new DefaultItemAnimator());

        ChatListAdapter mAdapter = new ChatListAdapter(getApplicationContext());
        mAdapter.setmOnItemClickListener(this);
        chatsRecyclerView.setAdapter(mAdapter);

        newConversationButton = (FloatingActionButton) findViewById(R.id.new_conversation_floating_button);
        newConversationButton.setBackgroundTintList(getResources().getColorStateList(R.color.colorPrimary));
        newConversationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(ChatListActivity.this,ContactListActivity.class);
                startActivity(i);

            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_me_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if( item.getItemId()  == R.id.me)
        {
            Intent i = new Intent(ChatListActivity.this,MeActivity.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(String contactJid) {

        Intent i = new Intent(ChatListActivity.this,ChatViewActivity.class);
        i.putExtra("contact_jid",contactJid);
        startActivity(i);
    }
}