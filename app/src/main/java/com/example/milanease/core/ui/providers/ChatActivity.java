package com.example.milanease.core.ui.providers;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
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

    private RecyclerView recyclerView;
    private MessageAdapter messageAdapter;
    private Button send;
    private EditText editText;
    private ChatActivityViewModel chatActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        Provider provider = getIntent().getParcelableExtra(ProvidersFragment.PROVIDER);

        chatActivityViewModel = ViewModelProviders.of(this).get(ChatActivityViewModel.class);
        chatActivityViewModel.init(provider);

        chatActivityViewModel.getMessages().observe(this, new Observer<List<Message>>() {
            @Override
            public void onChanged(List<Message> messages) {
                messageAdapter.notifyDataSetChanged();
                recyclerView.scrollToPosition(messages.size() - 1);
            }
        });

        initComponents(chatActivityViewModel.getProvider().getValue(), chatActivityViewModel.getMessages().getValue());
        initChatBot();
    }

    private void initComponents(final Provider provider, List<Message> messages) {
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
                    String text = editText.getText().toString();
                    while (text.charAt(text.length() - 1) == '\n')
                        text = text.substring(0, text.length() - 1);
                    Message message = new Message(provider.getID(), text, Calendar.getInstance(), MessageState.sent);
                    chatActivityViewModel.addMessage(message);
                    ChatBot.getInstance().reply(message);
                }
                editText.setText("");
            }
        });

        initActionBar(provider);
    }

    private void initActionBar(Provider provider) {
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

    private void initChatBot() {
        ChatBot.getInstance().setViewModel(chatActivityViewModel);
        chatActivityViewModel.addMessage(ChatBot.getInstance().getHelp());
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
}
