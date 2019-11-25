package com.example.milanease.core.ui.providers;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.milanease.data.RepositoryManager;

import java.util.List;

public class ChatActivityViewModel extends ViewModel {

    private RepositoryManager repositoryManager = RepositoryManager.getInstance();
    private MutableLiveData<List<Message>> mMessages;
    private MutableLiveData<Integer> mPosition;

    public void init() {
        mMessages = new MutableLiveData<>();
        mPosition = new MutableLiveData<>();
        mPosition.setValue(0);
    }

    public void setPosition(int position) {
        mPosition.setValue(position);
    }

    public LiveData<Provider> getProvider() {
        return repositoryManager.getProvider(mPosition.getValue());
    }

    public LiveData<Integer> getPosition() {
        return mPosition;
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

}
