package com.example.milanease.core.ui.providers;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.milanease.core.ui.Bills.Bill;
import com.example.milanease.data.RepositoryManager;

import java.util.List;

public class ChatActivityViewModel extends ViewModel {

    private RepositoryManager repositoryManager = RepositoryManager.getInstance();
    private MutableLiveData<List<Message>> mMessages;
    private MutableLiveData<List<Bill>> mBills;
    private MutableLiveData<Provider> mProvider;

    public void init(Provider provider) {
        mProvider = new MutableLiveData<>();
        mProvider.setValue(provider);
        mMessages = repositoryManager.getMessages(mProvider.getValue());
        mBills = repositoryManager.getBills(mProvider.getValue());
    }

    public LiveData<Provider> getProvider() {
        return mProvider;
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

    public LiveData<List<Bill>> getBills() {
        return mBills;
    }
}
