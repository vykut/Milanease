package com.example.milanease.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Insert;
import androidx.room.PrimaryKey;

@Entity(tableName = "contracts", foreignKeys = {
        @ForeignKey(entity = Provider.class,
        parentColumns = "id",
        childColumns = "provider_id")
})
public class Contract implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "provider_id")
    private long providerID;
    @ColumnInfo(name = "client_id")
    private String clientID;
    @ColumnInfo(name = "client_name")
    private String clientName;

    public Contract(long id, String name, long providerID, String clientID, String clientName) {
        this.id = id;
        this.name = name;
        this.providerID = providerID;
        this.clientID = clientID;
        this.clientName = clientName;
    }

    @Ignore
    public Contract(String name, long providerID, String clientID, String clientName) {
        this.name = name;
        this.providerID = providerID;
        this.clientID = clientID;
        this.clientName = clientName;
    }

    @Ignore
    public Contract(Parcel in) {
        id = in.readLong();
        name = in.readString();
        providerID = in.readLong();
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
        dest.writeLong(id);
        dest.writeString(name);
        dest.writeLong(providerID);
        dest.writeString(clientID);
        dest.writeString(clientName);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getProviderID() {
        return providerID;
    }

    public void setProviderID(long providerID) {
        this.providerID = providerID;
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
}
