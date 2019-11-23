package com.example.milanease.core.ui.providers;

import android.os.Parcel;
import android.os.Parcelable;

public class Contract implements Parcelable {

    private String name;

    public Contract() {
        name = "Enel - Family Plan";
    }

    public Contract(Parcel in) {
        name = in.readString();
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
    }
}
