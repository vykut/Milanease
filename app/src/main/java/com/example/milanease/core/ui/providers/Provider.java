package com.example.milanease.core.ui.providers;

import android.media.Image;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.milanease.R;
import com.example.milanease.core.ui.dashboard.Utility;

import java.util.ArrayList;
import java.util.List;

public class Provider implements Comparable<Provider>, Parcelable {

    private String name;
    private List<Utility> utilities;
    private int logoResID;
    private String phone;
    private String description;
    @Nullable private Contract contract;

    public Provider(String name, List<Utility> utilities, int logoResID, String phone, String description) {
        this.name = name;
        this.utilities = utilities;
        this.logoResID = logoResID;
        this.phone = phone;
        this.description = description;
    }

    public Provider(String name, List<Utility> utilities, int logoResID, String phone, String description, @Nullable Contract contract) {
        this.name = name;
        this.utilities = utilities;
        this.logoResID = logoResID;
        this.phone = phone;
        this.description = description;
        this.contract = contract;
    }

    public Provider(boolean contract) {
        this.name = "Enel";

        List<Utility> utilities = new ArrayList<>();
        utilities.add(Utility.electricity);

        this.utilities = utilities;
        this.logoResID = R.drawable.ic_enel_logo;
        this.phone = "0800070809";
        this.description = "În România, Grupul Enel deserveşte 2,8 milioane de clienţi prin reţeaua sa de furnizare şi distribuţie, iar Enel Green Power deţine şi operează centrale de producţie a energiei din surse regenerabile.";
        if(contract) {
            this.contract = new Contract();
        } else {
            this.contract = null;
        }

    }

    public Provider() {
        name = "";
        utilities = null;
        logoResID = 0;
        phone = "";
        description = "";
        contract = null;
    }

    public Provider(Parcel in) {
        name = in.readString();
        utilities = in.readArrayList(Utility.class.getClassLoader());
        logoResID = in.readInt();
        phone = in.readString();
        description = in.readString();
        contract = in.readParcelable(Contract.class.getClassLoader());
    }

    public static final Creator<Provider> CREATOR = new Creator<Provider>() {
        @Override
        public Provider createFromParcel(Parcel in) {
            return new Provider(in);
        }

        @Override
        public Provider[] newArray(int size) {
            return new Provider[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Utility> getUtilities() {
        return utilities;
    }

    public int getLogo() {
        return logoResID;
    }

    public String getPhone() {
        return phone;
    }

    public String getDescription() {
        return description;
    }

    @Nullable
    public Contract getContract() {
        return contract;
    }

    @Override
    public int compareTo(Provider o) {
        if (this.contract == null)
            return 1;
        return -1;

    }

//    @NonNull
//    @Override
//    public String toString() {
//        if (contract == null) {
//            return "null contract";
//        }
//        return "non-null contract";
//    }

    @Override
    public String toString() {
        return "Provider{" +
                "name='" + name + '\'' +
                ", utilities=" + utilities +
                ", logoResID=" + logoResID +
                ", phone='" + phone + '\'' +
                ", description='" + description + '\'' +
                ", contract=" + contract +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeList(utilities);
        dest.writeInt(logoResID);
        dest.writeString(phone);
        dest.writeString(description);
        dest.writeParcelable(contract, 0);
    }
}