package com.example.milanease.data;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.example.milanease.core.ui.Bills.Bill;
import com.example.milanease.core.ui.dashboard.Utility;
import com.example.milanease.data.model.Contract;
import com.example.milanease.data.model.Message;
import com.example.milanease.data.model.Provider;

import java.util.ArrayList;
import java.util.List;

public class RepositoryManager {
    private static final RepositoryManager ourInstance = new RepositoryManager();

    public static RepositoryManager getInstance() {
        return ourInstance;
    }

    private MutableLiveData<List<Provider>> mProviders = new MutableLiveData<>();
    private MutableLiveData<List<Bill>> mBills = new MutableLiveData<>();
    private MutableLiveData<List<Message>> mMessages = new MutableLiveData<>();

    private RepositoryManager() {
        initProviders();
        initBills();
        initMessages();
    }

    public LiveData<List<Bill>> getBills() {
        return mBills;
    }

    public LiveData<List<Bill>> getSegmentedBills(final Utility utility) {
        return Transformations.map(mBills, new Function<List<Bill>, List<Bill>>() {
            @Override
            public List<Bill> apply(List<Bill> input) {
                List<Bill> bills = new ArrayList<>();
                for (Bill bill : input) {
                    if (bill.getUtility().equals(utility))
                        bills.add(bill);
                }
                return bills;
            }
        });
    }

    public LiveData<List<Provider>> getProviders() {
        return mProviders;
    }

    public LiveData<List<Message>> getMessages() {
        return mMessages;
    }

    public void addMessages(List<Message> messages) {
        List<Message> currentMessages = mMessages.getValue();
        currentMessages.addAll(messages);

        mMessages.setValue(currentMessages);
    }

    public void addMessage(Message message) {
        List<Message> currentMessages = mMessages.getValue();
        currentMessages.add(message);
        mMessages.setValue(currentMessages);
    }

    public void addProvider(Provider provider) {
        List<Provider> providers = mProviders.getValue();
        providers.add(provider);
        mProviders.setValue(providers);
    }

    public void addBill(Bill bill) {
        List<Bill> bills = mBills.getValue();
        bills.add(bill);
        mBills.postValue(bills);
    }

    public void addContract(Provider provider, Contract contract) {

    }

    private void initBills() {
        List<Bill> bills = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            bills.add(new Bill(Utility.water));
        }

        bills.add(new Bill(Utility.electricity));
        bills.add(new Bill(Utility.electricity));
        bills.add(new Bill(Utility.gas));
        bills.add(new Bill(Utility.gas));

        mBills.setValue(bills);
    }

    private void initProviders() {
        List<Provider> providers = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            if (i < 2)
                providers.add(new Provider(true));
            else
                providers.add(new Provider(false));
        }

        mProviders.setValue(providers);
    }

    private void initMessages() {
        List<Message> messages = new ArrayList<>();
        mMessages.setValue(messages);
    }
}
