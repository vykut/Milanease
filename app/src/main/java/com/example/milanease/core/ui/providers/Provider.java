package com.example.milanease.core.ui.providers;

import android.media.Image;

import androidx.annotation.Nullable;

import com.example.milanease.R;
import com.example.milanease.core.ui.dashboard.Utility;

import java.util.ArrayList;
import java.util.List;

public class Provider implements Comparable<Provider> {

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
        this.logoResID = R.drawable.enel;
        this.phone = "0800070809";
        this.description = "În România, Grupul Enel deserveşte 2,8 milioane de clienţi prin reţeaua sa de furnizare şi distribuţie, iar Enel Green Power deţine şi operează centrale de producţie a energiei din surse regenerabile.";
        if(contract) {
            this.contract = new Contract();
        } else {
            this.contract = null;
        }

    }

    public String getName() {
        return name;
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
            return -1;
        return 1;

    }
}