package com.example.milanease.core.ui.providers;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.milanease.R;
import com.example.milanease.data.model.Message;
import com.example.milanease.data.model.Provider;
import com.example.milanease.data.viewmodel.ChatActivityViewModel;

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
        initComponents();

        Provider provider = getIntent().getParcelableExtra(ProvidersFragment.PROVIDER);

        chatActivityViewModel = ViewModelProviders.of(this).get(ChatActivityViewModel.class);
        chatActivityViewModel.init(provider);

        chatActivityViewModel.getMessages().observe(this, new Observer<List<Message>>() {
            @Override
            public void onChanged(List<Message> messages) {
                initAdaper(messages);
            }
        });

        initUI(chatActivityViewModel.getProvider());
    }

    private void initComponents() {
        recyclerView = findViewById(R.id.activity_chat_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        editText = findViewById(R.id.activity_chat_edit_text);
        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    sendMessage();
                    return true;
                }
                return false;
            }
        });
        send = findViewById(R.id.activity_chat_btn_send);
    }

    private void initAdaper(final List<Message> messages) {
        messageAdapter = new MessageAdapter(messages, getApplicationContext());
        recyclerView.setAdapter(messageAdapter);
        recyclerView.scrollToPosition(messages.size() - 1 > 0 ? messages.size() - 1 : 0);
//        recyclerView.smoothScrollToPosition(messages.size() - 1 > 0 ? messages.size() - 1 : 0);
    }

    private void initUI(final Provider provider) {
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });

        initActionBar(provider);
    }

    private void sendMessage() {
        chatActivityViewModel.sendMessage(editText.getText().toString());
        editText.getText().clear();
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
            ColorDrawable colorDrawable = new ColorDrawable(getResources().getColor(provider.getUtilities().get(0).getColor()));
            getSupportActionBar().setBackgroundDrawable(colorDrawable);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                getWindow().setStatusBarColor(getResources().getColor(provider.getUtilities().get(0).getColor()));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

            if(item.getItemId() == android.R.id.home) {
                Intent intent = NavUtils.getParentActivityIntent(this);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                NavUtils.navigateUpTo(this, intent);
                return true;
            }
        return super.onOptionsItemSelected(item);
    }
}