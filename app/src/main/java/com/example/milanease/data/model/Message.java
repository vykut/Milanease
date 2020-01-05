package com.example.milanease.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.milanease.core.ui.providers.MessageState;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;


public class Message implements Parcelable, Comparable<Message> {

    private String message;
    private Date date;
    private String state;
    private long providerID;

    public Message(long providerID, String message, Date date, String state) {
        this.providerID = providerID;
        this.message = message;
        this.date = date;
        this.state = state;
    }

    public Message(long providerID, String message, String state) {
        this.providerID = providerID;
        this.message = message;
        this.state = state;
        date = new Date();
    }

    public Message(Parcel in) {
        providerID = in.readLong();
        message = in.readString();
        date = new Date(in.readLong());
        state = in.readString();
    }

    public Message() {
    }

    public String getMessage() {
        return message;
    }

    public Date getDate() {
        return date;
    }

    public String getState() {
        return state;
    }

    public Long getProviderID() {
        return providerID;
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
        dest.writeLong(providerID);
        dest.writeString(message);
        dest.writeLong(date.getTime());
        dest.writeString(state);
    }

    @Override
    public int compareTo(Message o) {
        return getDate().compareTo(o.getDate());
    }
}
