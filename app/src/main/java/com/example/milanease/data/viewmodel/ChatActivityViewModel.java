package com.example.milanease.data.viewmodel;

import android.text.TextUtils;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.milanease.data.model.Bill;
import com.example.milanease.core.ui.dashboard.Utility;
import com.example.milanease.core.ui.providers.MessageState;
import com.example.milanease.data.ChatBot;
import com.example.milanease.data.RepositoryManager;
import com.example.milanease.data.model.Message;
import com.example.milanease.data.model.Provider;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ChatActivityViewModel extends ViewModel {

    private RepositoryManager repositoryManager = RepositoryManager.getInstance();
    private MutableLiveData<List<Message>> mMessages;
    private List<Bill> bills;
    private Provider provider;

    public void init(Provider provider) {
        this.provider = provider;
        initMessages();
        initBills();

        ChatBot.getInstance().setViewModel(this);

        if (mMessages.getValue().isEmpty())
        addMessage(ChatBot.getInstance().getHelp());
    }

    public Provider getProvider() {
        return provider;
    }

    public LiveData<List<Message>> getMessages() { return mMessages; }

    public void addMessages(List<Message> messages) {
        List<Message> currentMessages = mMessages.getValue();
        currentMessages.addAll(messages);
        mMessages.setValue(currentMessages);
        repositoryManager.addMessages(messages);
    }

    public void addMessage(Message message) {
        List<Message> currentMessages = mMessages.getValue();
        currentMessages.add(message);
        mMessages.setValue(currentMessages);
        repositoryManager.addMessage(message);
    }

    public List<Bill> getBills() {
        return bills;
    }

    public void sendMessage(String text) {
        if (TextUtils.isGraphic(text)) {
            while (text.charAt(text.length() - 1) == '\n')
                text = text.substring(0, text.length() - 1);
            Message message = new Message(provider.getId(), text, Calendar.getInstance(), MessageState.sent);
            addMessage(message);
            addMessages(ChatBot.getInstance().makeReply(message));
        }
    }

    private void initMessages() {
        List<Message> allMessages = repositoryManager.getMessages().getValue();
        mMessages = new MutableLiveData<>();
        List<Message> messages = new ArrayList<>();

        for (Message message : allMessages)
            if (message.getProviderID().equals(provider.getId()))
                messages.add(message);

        mMessages.setValue(messages);
    }

    private void initBills() {
        //should implement live data
        List<Bill> allBills = repositoryManager.getBills().getValue();
        bills = new ArrayList<>();

        for (Utility utility : provider.getUtilities())
            for (Bill bill : allBills)
                if (bill.getProviderID().equals(provider.getId()) && bill.getUtility().equals(utility)) {
                    bills.add(bill);
                    break;
                }

    }
}
