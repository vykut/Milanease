package com.example.milanease.core.ui.providers;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Calendar;
import java.util.Date;

public class Message implements Parcelable, Comparable<Message> {

    private String message;
    private Calendar date;
    private MessageState state;

    public Message(String message, Calendar date, MessageState state) {
        this.message = message;
        this.date = date;
        this.state = state;
    }

    public Message(String message, MessageState state) {
        this.message = message;
        this.state = state;
        date = Calendar.getInstance();
    }

    public Message(MessageState state) {
        this.state = state;
        message = "Ciao";
        date = Calendar.getInstance();
    }

    public Message(Parcel in) {
        message = in.readString();
        date = Calendar.getInstance();
        date.setTime(new Date(in.readLong()));
        state = MessageState.valueOf(in.readString());
    }

    public String getMessage() {
        return message;
    }

    public Calendar getDate() {
        return date;
    }

    public MessageState getState() {
        return state;
    }

    public static final Creator<Message> CREATOR = new Creator<Message>() {
        @Override
        public Message createFromParcel(Parcel in) {
            return new Message(in);
        }

        @Override
        public Message[] newArray(int size) {
            return new Message[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(message);
        dest.writeLong(date.getTimeInMillis());
        dest.writeString(state.name());
    }


    @Override
    public int compareTo(Message o) {
        return getDate().compareTo(o.getDate());
    }
}
