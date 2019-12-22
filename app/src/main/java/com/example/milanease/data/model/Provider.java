package com.example.milanease.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.milanease.core.ui.dashboard.Utility;

import java.util.List;

@Entity(tableName = "providers")
public class Provider implements Comparable<Provider>, Parcelable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "utilities")
    private List<Utility> utilities;
    @ColumnInfo(name = "logo_large")
    private int logoLarge;
    @ColumnInfo(name = "logo_small")
    private int logoSmall;
    @ColumnInfo(name = "phone")
    private String phone;
    @ColumnInfo(name = "description")
    private String description;
    @ColumnInfo(name = "email")
    private String email;
    @Ignore
    @Nullable private Contract contract;

    @Ignore
    public Provider(long id, String name, List<Utility> utilities, int logoLarge, int logoSmall, String phone, String description, String email, @Nullable Contract contract) {
        this.name = name;
        this.id = id;
        this.utilities = utilities;
        this.logoLarge = logoLarge;
        this.logoSmall = logoSmall;
        this.phone = phone;
        this.description = description;
        this.email = email;
        this.contract = contract;
    }

    public Provider(long id, String name, List<Utility> utilities, int logoLarge, int logoSmall, String phone, String description, String email) {
        this.id = id;
        this.name = name;
        this.utilities = utilities;
        this.logoLarge = logoLarge;
        this.logoSmall = logoSmall;
        this.phone = phone;
        this.description = description;
        this.email = email;
    }

    @Ignore
    public Provider() {
        id = 0;
        name = "";
        utilities = null;
        logoLarge = 0;
        logoSmall = 0;
        phone = "";
        description = "";
        email = "";
        contract = null;
    }

    @Ignore
    public Provider(Parcel in) {
        id = in.readLong();
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

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Utility> getUtilities() {
        return utilities;
    }

    public void setUtilities(List<Utility> utilities) {
        this.utilities = utilities;
    }

    public int getLogoLarge() {
        return logoLarge;
    }

    public void setLogoLarge(int logoLarge) {
        this.logoLarge = logoLarge;
    }

    public int getLogoSmall() {
        return logoSmall;
    }

    public void setLogoSmall(int logoSmall) {
        this.logoSmall = logoSmall;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setContract(@Nullable Contract contract) {
        this.contract = contract;
    }

    @Nullable
    public Contract getContract() {
        return contract;
    }

    public void addContract(Contract contract) {
        if (this.contract == null)
        this.contract = contract;
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
        dest.writeLong(id);
        dest.writeString(name);
        dest.writeList(utilities);
        dest.writeInt(logoLarge);
        dest.writeInt(logoSmall);
        dest.writeString(phone);
        dest.writeString(email);
        dest.writeString(description);
        dest.writeParcelable(contract, 0);
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof Provider)
            if (((Provider) obj).getId().equals(id))
                return true;
            return false;
    }
}