package com.example.milanease.core.ui.providers;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.milanease.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private Provider provider;
    private List<Message> messages = new ArrayList<>();
    private RecyclerView recyclerView;
    private MessageAdapter messageAdapter;
    private Button send;
    private EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        provider = getIntent().getParcelableExtra(ProvidersFragment.PROVIDER);

        initComponents();
    }

    private void initComponents() {
        recyclerView = findViewById(R.id.activity_chat_recycler_view);
        messageAdapter = new MessageAdapter(messages, getApplicationContext());
        recyclerView.setAdapter(messageAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


        editText = findViewById(R.id.activity_chat_edit_text);
        send = findViewById(R.id.activity_chat_btn_send);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isGraphic(editText.getText())) {
                    String message = editText.getText().toString();
                    while (message.charAt(message.length() - 1) == '\n')
                        message = message.substring(0, message.length() - 1);
                    messages.add(new Message(message, Calendar.getInstance(), MessageState.sent));
                    messageAdapter.notifyDataSetChanged();
                    recyclerView.scrollToPosition(messages.size() - 1);
                }
                editText.setText("");
            }
        });

        initActionBar();
    }

    private void initActionBar() {
        if (getSupportActionBar() != null) {
            ActionBar actionBar = getSupportActionBar();
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setCustomView(R.layout.chat_action_bar);
            ImageView imageView = findViewById(R.id.chat_action_bar_image);
            imageView.setImageResource(provider.getLogoSmall());
            TextView textView = findViewById(R.id.chat_action_bar_title);
            textView.setText(provider.getName());
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = NavUtils.getParentActivityIntent(this);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                NavUtils.navigateUpTo(this, intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setActionBarImage() {


//        ImageView imageView = new ImageView(getApplicationContext());
//        imageView.setImageResource(provider.getLogoLarge());
//        getSupportActionBar().setDisplayShowCustomEnabled(true);
//        getSupportActionBar().setDisplayOptions(getSupportActionBar().getDisplayOptions() | ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_USE_LOGO);
//        getSupportActionBar().setCustomView(imageView);

//        getSupportActionBar().setDisplayUseLogoEnabled(true);
//        getSupportActionBar().setIcon(provider.getLogoLarge());
//        getSupportActionBar().setLogo(provider.getLogoLarge());
//
//        getActionBar().setDisplayUseLogoEnabled(true);
//        getActionBar().setLogo(provider.getLogoLarge());
////        getActionBar().setIcon(provider.getLogoLarge());
    }

    private void setActionBarTitle() {
        getSupportActionBar().setTitle(provider.getName());
    }
}
