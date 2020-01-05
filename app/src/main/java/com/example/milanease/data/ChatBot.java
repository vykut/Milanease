package com.example.milanease.data;

import android.os.AsyncTask;

import androidx.arch.core.util.Function;
import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;

import com.example.milanease.core.ui.dashboard.Utility;
import com.example.milanease.data.model.Bill;
import com.example.milanease.data.viewmodel.ChatActivityViewModel;
import com.example.milanease.core.ui.providers.MessageState;
import com.example.milanease.data.model.Provider;
import com.example.milanease.data.model.Message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChatBot {
    private static volatile ChatBot ourInstance = new ChatBot();

    public static ChatBot getInstance() {
        return ourInstance;
    }

    // THE STRINGS HERE HAVE NOT BEEN SAVED IN STRINGS.XML ON PURPOSE.
    // THE STRINGS HERE HAVE NOT BEEN SAVED IN STRINGS.XML ON PURPOSE.
    // THE STRINGS HERE HAVE NOT BEEN SAVED IN STRINGS.XML ON PURPOSE.
    // THE STRINGS HERE HAVE NOT BEEN SAVED IN STRINGS.XML ON PURPOSE.
    // THE STRINGS HERE HAVE NOT BEEN SAVED IN STRINGS.XML ON PURPOSE.
    // THE STRINGS HERE HAVE NOT BEEN SAVED IN STRINGS.XML ON PURPOSE.
    // THE STRINGS HERE HAVE NOT BEEN SAVED IN STRINGS.XML ON PURPOSE.

    private final static String LAST_BILL = "last bill";
    private final static String BILL_TOTAL = "bill total";
    private final static String CONTRACT_NEW = "new contract";
    private final static String CHANGE_CONTRACT_TERMS = "change contract terms";
    private final static String CONTRACT_STOP = "stop contract";
    private final static String ASSISTANCE = "assist";
    private final static String PHONE_NUMBER = "phone";
    private final static String EMAIL = "email";
    private final static String HELP = "help";
    private final static String YES = "yes";
    private final static String NO = "no";

    private ChatActivityViewModel chatActivityViewModel;
    private List<Bill> myBills;
    private Provider provider;

    private ChatBot() {
    }

    public void setViewModel(ChatActivityViewModel chatActivityViewModel) {
        this.chatActivityViewModel = chatActivityViewModel;
        chatActivityViewModel.getBills().removeObserver(observer);
        chatActivityViewModel.getBills().observeForever(observer);
        this.provider = chatActivityViewModel.getProvider();
    }

    private Observer<List<Bill>> observer = new Observer<List<Bill>>() {
        @Override
        public void onChanged(List<Bill> bills) {
            myBills = bills;
        }
    };

    public List<Message> makeReply(Message message) {
        final List<Message> messages = new ArrayList<>();

        if (message.getMessage().contains(LAST_BILL))
            if (provider.getContract() != null)
                messages.add(new Message(provider.getId(), getLastBillTotal(), MessageState.received.toString()));
            else //to add reponse from new contract
                messages.add(new Message(provider.getId(), "You don't have a contract with us.", MessageState.received.toString()));

        if (message.getMessage().contains(BILL_TOTAL))
            if (provider.getContract() != null)
                messages.add(new Message(provider.getId(), getBillTotal(), MessageState.received.toString()));

            else
                messages.add(new Message(provider.getId(), "You don't have a contract with us.", MessageState.received.toString()));

        if (message.getMessage().contains(CONTRACT_NEW)) {
            if (provider.getContract() != null)
                messages.add(new Message(provider.getId(), "You already have a contract.", MessageState.received.toString()));
            else {
                chatActivityViewModel.createContract(provider);
                messages.add(new Message(provider.getId(), "Your contract has been created. You can now start using our services", MessageState.received.toString()));
            }
//                messages.add(new Message(provider.getId(), "If you'd like to make a new contract, please go back to the previous screen and press the + button.", MessageState.received));
        }

        if (message.getMessage().contains(CHANGE_CONTRACT_TERMS))
            if (provider.getContract() != null)
                messages.add(new Message(provider.getId(), String.format("If you have any inquiries about your current contract or would like to add new options, please call us at the following phone number: %s.", provider.getPhone()), MessageState.received.toString()));
            else {
                messages.add(new Message(provider.getId(), "You don't have a contract with us.", MessageState.received.toString()));
                messages.add(new Message(provider.getId(), "If you'd like to make a new contract, please go back to the previous screen and press the + button.", MessageState.received.toString()));
            }

        if (message.getMessage().contains(CONTRACT_STOP))
            if (provider.getContract() != null) {
                chatActivityViewModel.deleteContract(provider);
                messages.add(new Message(provider.getId(), "Your contract has been terminated with immediate effect", MessageState.received.toString()));
            }
            else
                messages.add(new Message(provider.getId(), "You don't have a contract with us.", MessageState.received.toString()));

        if (message.getMessage().contains(ASSISTANCE))
            messages.add(new Message(provider.getId(), "Dispatching a team to your location. They should arrive shortly.", MessageState.received.toString()));

        if (message.getMessage().contains(PHONE_NUMBER))
            messages.add(new Message(provider.getId(), String.format("You can call us at %s, available 24/7.", provider.getPhone()), MessageState.received.toString()));

        if (message.getMessage().contains(EMAIL))
            messages.add(new Message(provider.getId(), String.format("You can write us at %s.", provider.getEmail()), MessageState.received.toString()));

        if (message.getMessage().contains(HELP)) {
            messages.add(getHelp());
            return messages;
        }

        if (message.getMessage().contains(NO)) {
            messages.add(new Message(provider.getId(), "Have a good day!", MessageState.received.toString()));
            return messages;
        }
        if (message.getMessage().contains(YES)) {
            messages.add(getHelp());
            return messages;
        }

        if (messages.isEmpty()) {
            messages.add(new Message(provider.getId(), "Sorry, I didn't understand your request.", MessageState.received.toString()));
            messages.add(getHelp());
        }
        else
            messages.add(new Message(provider.getId(), "Is there anything else we can assist you with?", MessageState.received.toString()));

        messages.add(message);

        return messages;
    }

    public Message getHelp() {
        return new Message(provider.getId(),
                String.format("%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s","Some things you can ask me:", LAST_BILL, BILL_TOTAL, CONTRACT_NEW, CHANGE_CONTRACT_TERMS, CONTRACT_STOP, ASSISTANCE, PHONE_NUMBER, EMAIL), MessageState.received.toString());
    }

    private String getLastBillTotal() {
        StringBuilder billTotal = new StringBuilder();
        //for each utility display latest bill
        for (Utility utility: provider.getUtilities()) {
            for (Bill bill: myBills) {
                if (bill.getUtility().equals(utility)) {
                    billTotal.append(String.format("%s: %s, due in %s\n", bill.getUtility().toString(), bill.displayPrice(), bill.getDueDate()));
                    break;
                }
            }
        }

        return billTotal.substring(0, billTotal.length() - 1);
    }

    private String getBillTotal() {
        StringBuilder stringBuilder = new StringBuilder();

        HashMap<Utility, Double> hashMap = new HashMap<>();
        for (Bill bill :
                myBills) {
            if (hashMap.containsKey(bill.getUtility()) && provider.getUtilities().contains(bill.getUtility()))
                hashMap.put(bill.getUtility(), hashMap.get(bill.getUtility()) + bill.getPrice());
            else
                hashMap.put(bill.getUtility(), bill.getPrice());
        }

        for (Utility utility :
                provider.getUtilities()) {
            stringBuilder.append(String.format("Total for %s: %5.2f€\n", utility.toString(), hashMap.get(utility)));
        }

        return stringBuilder.toString();
    }
}
