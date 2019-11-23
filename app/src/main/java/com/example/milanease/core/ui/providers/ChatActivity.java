package com.example.milanease.core.ui.providers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.milanease.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
                    if (message.charAt(message.length() - 1) == '\n')
                        message = message.substring(0, message.length() - 1);
                    messages.add(new Message(message, Calendar.getInstance(), MessageState.sent));
                    messageAdapter.notifyDataSetChanged();
                    recyclerView.scrollToPosition(messages.size() - 1);
                }
                editText.setText("");
            }
        });

        setActionBarImage();
        setActionBarTitle();
    }

    private void setActionBarImage() {
//        getSupportActionBar().setIcon(provider.getLogo());
        getSupportActionBar().setLogo(provider.getLogo());
    }

    private void setActionBarTitle() {
        getSupportActionBar().setTitle(provider.getName());
    }
}
