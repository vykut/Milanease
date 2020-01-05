package com.example.milanease.data.viewmodel;

import android.text.TextUtils;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.milanease.data.model.Bill;
import com.example.milanease.core.ui.dashboard.Utility;
import com.example.milanease.core.ui.providers.MessageState;
import com.example.milanease.data.ChatBot;
import com.example.milanease.data.RepositoryManager;
import com.example.milanease.data.model.Contract;
import com.example.milanease.data.model.Message;
import com.example.milanease.data.model.Provider;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class ChatActivityViewModel extends ViewModel {

    private RepositoryManager repositoryManager = RepositoryManager.getInstance();
    private LiveData<List<Message>> mMessages;
    private LiveData<List<Bill>> bills = repositoryManager.getBills();
    private Provider provider;

    public void init(Provider provider) {
        this.provider = provider;
        ChatBot.getInstance().setViewModel(this);

        initMessages();
    }

    public Provider getProvider() {
        return provider;
    }

    public LiveData<List<Message>> getMessages() { return mMessages; }

    public void addMessages(List<Message> messages) {
        repositoryManager.addMessages(messages);
    }

    public void addMessage(Message message) {
        repositoryManager.addMessage(message);
    }

    public LiveData<List<Bill>> getBills() {
        return bills;
    }

    public void sendMessage(String text) {
        if (TextUtils.isGraphic(text)) {
            while (text.charAt(text.length() - 1) == '\n')
                text = text.substring(0, text.length() - 1);
            Message message = new Message(provider.getId(), text, new Timestamp(new Date().getTime()), MessageState.sent.toString());
            addMessages(ChatBot.getInstance().makeReply(message));
        }
    }

    private void initMessages() {
        mMessages = Transformations.map(repositoryManager.getMessages(), new Function<List<Message>, List<Message>>() {
            @Override
            public List<Message> apply(List<Message> input) {
                List<Message> messages = new ArrayList<>();

                for (Message message : input)
                    if (message.getProviderID().equals(provider.getId()))
                        messages.add(message);
                    if (messages.isEmpty())
                        addMessage(ChatBot.getInstance().getHelp());
//                Collections.sort(messages);
                return messages;
            }
        });
    }
    public Double getBillTotal(Utility utility) {
        return repositoryManager.getBillTotal(utility);
    }

    public void deleteContract(Provider provider) {
        repositoryManager.deleteContract(provider.getContract());
        provider.setContract(null);
    }

    public void deleteContract(Contract contract) {
        repositoryManager.deleteContract(contract);
    }

    public void createContract(Provider provider) {
        repositoryManager.addContract(provider);
    }
}
