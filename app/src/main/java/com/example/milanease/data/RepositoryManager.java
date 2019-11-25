package com.example.milanease.data;

import androidx.lifecycle.MutableLiveData;

import com.example.milanease.core.ui.Bills.Bill;
import com.example.milanease.core.ui.providers.Message;
import com.example.milanease.core.ui.providers.Provider;

import java.util.ArrayList;
import java.util.List;

public class RepositoryManager {
    private static final RepositoryManager ourInstance = new RepositoryManager();

    public static RepositoryManager getInstance() {
        return ourInstance;
    }

    private List<Provider> providers;
    private List<Bill> bills;
    private List<Message> messages;

    private RepositoryManager() {}

    public MutableLiveData<List<Bill>> getBills() {
        initBills();

        MutableLiveData<List<Bill>> mBills = new MutableLiveData<>();
        mBills.setValue(bills);
        return mBills;
    }

    public MutableLiveData<List<Provider>> getProviders() {
        initProviders();

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

    private void initBills() {
        bills = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            bills.add(new Bill());
        }
    }

    private void initProviders() {
        providers = new ArrayList<>();

        providers.add(new Provider(true));
        providers.add(new Provider(true));
        providers.add(new Provider(false));
        providers.add(new Provider(false));
        providers.add(new Provider(false));
        providers.add(new Provider(false));
    }


}
