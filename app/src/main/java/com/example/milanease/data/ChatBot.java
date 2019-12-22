package com.example.milanease.data;

import com.example.milanease.data.model.Bill;
import com.example.milanease.data.viewmodel.ChatActivityViewModel;
import com.example.milanease.core.ui.providers.MessageState;
import com.example.milanease.data.model.Provider;
import com.example.milanease.data.model.Message;

import java.util.ArrayList;
import java.util.List;

public class ChatBot {
    private static final ChatBot ourInstance = new ChatBot();

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
    private Provider provider;

    private ChatBot() {
    }

    public void setViewModel(ChatActivityViewModel chatActivityViewModel) {
        this.chatActivityViewModel = chatActivityViewModel;
        this.provider = chatActivityViewModel.getProvider();
    }

    public List<Message> makeReply(Message message) {
        List<Message> messages = new ArrayList<>();

        if (message.getMessage().contains(BILL_TOTAL))
            if (provider.getContract() != null)
                messages.add(new Message(provider.getId(), getBillTotal(), MessageState.received));
            else //to add reponse from new contract
                messages.add(new Message(provider.getId(), "You don't have a contract with us.", MessageState.received));

        if (message.getMessage().contains(CONTRACT_NEW)) {
            if (provider.getContract() != null)
                messages.add(new Message(provider.getId(), "You already have a contract.", MessageState.received));
            else
                messages.add(new Message(provider.getId(), "If you'd like to make a new contract, please go back to the previous screen and press the + button.", MessageState.received));
        }

        if (message.getMessage().contains(CHANGE_CONTRACT_TERMS))
            if (provider.getContract() != null)
                messages.add(new Message(provider.getId(), String.format("If you have any inquiries about your current contract or would like to add new options, please call us at the following phone number: %s.", provider.getPhone()), MessageState.received));
            else {
                messages.add(new Message(provider.getId(), "You don't have a contract with us.", MessageState.received));
                messages.add(new Message(provider.getId(), "If you'd like to make a new contract, please go back to the previous screen and press the + button.", MessageState.received));
            }

        if (message.getMessage().contains(CONTRACT_STOP))
            messages.add(new Message(provider.getId(), "One of our employees will get in touch with you soon. We are sorry to see you go!", MessageState.received));

        if (message.getMessage().contains(ASSISTANCE))
            messages.add(new Message(provider.getId(), "Dispatching a team to your location. They should arrive shortly.", MessageState.received));

        if (message.getMessage().contains(PHONE_NUMBER))
            messages.add(new Message(provider.getId(), String.format("You can call us at %s, available 24/7.", provider.getPhone()), MessageState.received));

        if (message.getMessage().contains(EMAIL))
            messages.add(new Message(provider.getId(), String.format("You can write us at %s.", provider.getEmail()), MessageState.received));

        if (message.getMessage().contains(HELP)) {
            messages.add(getHelp());
            return messages;
        }

        if (message.getMessage().contains(NO)) {
            messages.add(new Message(provider.getId(), "Have a good day!", MessageState.received));
            return messages;
        }
        if (message.getMessage().contains(YES)) {
            messages.add(getHelp());
            return messages;
        }

        if (messages.isEmpty()) {
            messages.add(new Message(provider.getId(), "Sorry, I didn't understand your request.", MessageState.received));
            messages.add(getHelp());
        }
        else
            messages.add(new Message(provider.getId(), "Is there anything else we can assist you with?", MessageState.received));
        return messages;
    }

    public Message getHelp() {
        return new Message(provider.getId(),
                String.format("%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s","Some things you can ask me:", BILL_TOTAL, CONTRACT_NEW, CHANGE_CONTRACT_TERMS, CONTRACT_STOP, ASSISTANCE, PHONE_NUMBER, EMAIL), MessageState.received);
    }

    private String getBillTotal() {
        String billTotal = "";
        //for each utility display latest bill
        List<Bill> bills = chatActivityViewModel.getBills();
        for (Bill bill : bills) {
            billTotal = String.format("%s%s: %s, due in %s\n", billTotal, bill.getUtility().toString(), bill.displayPrice(), bill.getDueDate());
        }
        return billTotal.substring(0, billTotal.length() - 1);
    }
}
