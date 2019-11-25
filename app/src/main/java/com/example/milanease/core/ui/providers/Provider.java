package com.example.milanease.core.ui.providers;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Nullable;

import com.example.milanease.R;
import com.example.milanease.core.ui.dashboard.Utility;

import java.util.ArrayList;
import java.util.List;

public class Provider implements Comparable<Provider>, Parcelable {

    private String name;
    private String id;
    private List<Utility> utilities;
    private int logoLarge;
    private int logoSmall;
    private String phone;
    private String description;
    private String email;
    @Nullable private Contract contract;

    public Provider(String id, String name, List<Utility> utilities, int logoLarge, int logoSmall, String phone, String description, String email) {
        this.id = id;
        this.name = name;
        this.utilities = utilities;
        this.logoLarge = logoLarge;
        this.logoSmall = logoSmall;
        this.phone = phone;
        this.description = description;
        this.email = email;
        contract = null;
    }

    public Provider(String id, String name, List<Utility> utilities, int logoLarge, int logoSmall, String phone, String description, String email, @Nullable Contract contract) {
        this.id = id;
        this.name = name;
        this.utilities = utilities;
        this.logoLarge = logoLarge;
        this.logoSmall = logoSmall;
        this.phone = phone;
        this.email = email;
        this.description = description;
        this.contract = contract;
    }

    public Provider(boolean contract) {
        this.id = "0001";
        this.name = "Enel";

        List<Utility> utilities = new ArrayList<>();
        utilities.add(Utility.electricity);

        this.utilities = utilities;
        this.logoLarge = R.drawable.ic_enel_logo_large;
        this.logoSmall = R.drawable.ic_enel_logo_small;
        this.phone = "0800070809";
        this.email = "www.enel.ro";
        this.description = "În România, Grupul Enel deserveşte 2,8 milioane de clienţi prin reţeaua sa de furnizare şi distribuţie, iar Enel Green Power deţine şi operează centrale de producţie a energiei din surse regenerabile.";
        if(contract) {
            this.contract = new Contract();
        } else {
            this.contract = null;
        }
    }

    public Provider() {
        id = "";
        name = "";
        utilities = null;
        logoLarge = 0;
        logoSmall = 0;
        phone = "";
        description = "";
        email = "";
        contract = null;
    }

    public Provider(Parcel in) {
        id = in.readString();
        name = in.readString();
        utilities = in.readArrayList(Utility.class.getClassLoader());
        logoLarge = in.readInt();
        logoSmall = in.readInt();
        phone = in.readString();
        email = in.readString();
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

    public int getLogoLarge() {
        return logoLarge;
    }

    public int getLogoSmall() { return logoSmall; }

    public String getPhone() {
        return phone;
    }

    public String getDescription() {
        return description;
    }

    public String getEmail() { return email; }

    public String getID() {
        return id;
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
                ", id='" + id + '\'' +
                ", utilities=" + utilities +
                ", logoLarge=" + logoLarge +
                ", logoSmall=" + logoSmall +
                ", phone='" + phone + '\'' +
                ", description='" + description + '\'' +
                ", email='" + email + '\'' +
                ", contract=" + contract +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeList(utilities);
        dest.writeInt(logoLarge);
        dest.writeInt(logoSmall);
        dest.writeString(phone);
        dest.writeString(email);
        dest.writeString(description);
        dest.writeParcelable(contract, 0);
    }
}