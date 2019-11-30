package com.example.milanease.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Contract implements Parcelable {

    private String name;
    private String providerID;
    private String clientID;
    private String clientName;

    public Contract(String name, String providerID, String clientID, String clientName) {
        this.name = name;
        this.providerID = providerID;
        this.clientID = clientID;
        this.clientName = clientName;
    }

    public Contract(Parcel in) {
        name = in.readString();
        providerID = in.readString();
        clientID = in.readString();
        clientName = in.readString();
    }

    public static final Creator<Contract> CREATOR = new Creator<Contract>() {
        @Override
        public Contract createFromParcel(Parcel in) {
            return new Contract(in);
        }

        @Override
        public Contract[] newArray(int size) {
            return new Contract[size];
        }
    };

    public String getName() {
        return name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(providerID);
        dest.writeString(clientID);
        dest.writeString(clientName);
    }
}
