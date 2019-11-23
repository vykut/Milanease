package com.example.milanease.core.ui.providers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.milanease.R;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static class MessageHolder extends RecyclerView.ViewHolder {

        private LinearLayout linearLayout;
        private TextView message;
        private TextView time;

        public MessageHolder(@NonNull View itemView) {
            super(itemView);

            linearLayout = itemView.findViewById(R.id.chat_bubble_linear_layout);
            message = itemView.findViewById(R.id.chat_bubble_message);
            time = itemView.findViewById(R.id.chat_bubble_time);
        }

        @SuppressLint("DefaultLocale")
        public void setMessage(Message message) {
            if (message.getState() == MessageState.received)
                linearLayout.setGravity(Gravity.START);
            else
                linearLayout.setGravity(Gravity.END);

            this.message.setText(message.getMessage());
            this.time.setText(String.format("%d:%d %s %d %s", message.getDate().get(Calendar.HOUR_OF_DAY), message.getDate().get(Calendar.MINUTE),
                    message.getDate().getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.getDefault()),
                    message.getDate().get(Calendar.DAY_OF_MONTH), message.getDate().getDisplayName(Calendar.MONTH, Calendar.SHORT,
                            Locale.getDefault())));
        }
    }

    private List<Message> messages;
    private Context context;

    public MessageAdapter(List<Message> messages, Context context) {
        this.messages = messages;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.chat_bubble, parent, false);

        return new MessageHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((MessageHolder) holder).setMessage(messages.get(position));
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }
}