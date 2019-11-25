package com.example.milanease.data;

import androidx.lifecycle.MutableLiveData;

import com.example.milanease.core.ui.Bills.Bill;
import com.example.milanease.core.ui.dashboard.Utility;
import com.example.milanease.core.ui.providers.ChatBot;
import com.example.milanease.core.ui.providers.Message;
import com.example.milanease.core.ui.providers.Provider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RepositoryManager {
    private static final RepositoryManager ourInstance = new RepositoryManager();

    public static RepositoryManager getInstance() {
        return ourInstance;
    }

    private List<Provider> providers;
    private List<Bill> bills;
    private List<Message> messages;

    private RepositoryManager() {
        messages = new ArrayList<>();
        initProviders();
        initBills();
        initMessages();
    }

    public MutableLiveData<List<Bill>> getBills() {
        MutableLiveData<List<Bill>> mBills = new MutableLiveData<>();
        mBills.setValue(bills);
        return mBills;
    }

    public MutableLiveData<List<Provider>> getProviders() {


        MutableLiveData<List<Provider>> mProviders = new MutableLiveData<>();
        mProviders.setValue(providers);
        return mProviders;
    }

    public MutableLiveData<Provider> getProvider(int position) {
        if (providers == null) {
            initProviders();
        }

        MutableLiveData<Provider> mProvider = new MutableLiveData<>();
        mProvider.setValue(providers.get(position));
        return mProvider;
    }

    public MutableLiveData<List<Message>> getMessages() {
        MutableLiveData<List<Message>> mMessages = new MutableLiveData<>();
        mMessages.setValue(messages);
        return mMessages;

    }

    public MutableLiveData<List<Message>> getMessages(Provider provider) {
        MutableLiveData<List<Message>> mMessages = new MutableLiveData<>();
        List<Message> messages = new ArrayList<>();

        for (Message message : this.messages) {
            if (message.getProviderID().equals(provider.getID()))
                messages.add(message);
        }
        mMessages.setValue(messages);
        return mMessages;
    }
    
    public MutableLiveData<List<Bill>> getBills(Provider provider) {
        MutableLiveData<List<Bill>> mBills = new MutableLiveData<>();
        List<Bill> bills = new ArrayList<>();
        for (Bill bill: this.bills) {
            if (bill.getProviderID().equals(provider.getID()))
                bills.add(bill);
        }
        mBills.setValue(bills);
        return mBills;
    }

    private void initBills() {
        bills = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            bills.add(new Bill(Utility.electricity));
        }

        bills.add(new Bill(Utility.water));
        bills.add(new Bill(Utility.water));
        bills.add(new Bill(Utility.gas));
        bills.add(new Bill(Utility.gas));
    }

    private void initProviders() {
        providers = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            if (i < 2)
                providers.add(new Provider(true));
            else
                providers.add(new Provider(false));
        }
    }

    private void initMessages() {
        messages = new ArrayList<>();
    }
}
