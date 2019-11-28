package com.example.milanease.data;

import com.example.milanease.core.ui.bills.Bill;
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

    private final static String BILL_TOTAL_MESSAGE = "bill total";
    private final static String CONTRACT_NEW_MESSAGE = "new contract";
    private final static String CONTRACT_RENEW_MESSAGE = "renew contract";
    private final static String CONTRACT_STOP_MESSAGE = "stop contract";
    private final static String ASSISTANCE = "assist";
    private final static String PHONE_NUMBER = "phone";
    private final static String EMAIL = "email";
    private final static String HELP = "help";
    private final static String YES = "yes";
    private final static String NO = "no";

    private ChatActivityViewModel chatActivityViewModel;
    private Provider provider;

    private ChatBot() {}

    public void setViewModel(ChatActivityViewModel chatActivityViewModel) {
        this.chatActivityViewModel = chatActivityViewModel;
        this.provider = chatActivityViewModel.getProvider();
    }

    public List<Message> makeReply(Message message) {
        List<Message> messages = new ArrayList<>();

        if (message.getMessage().contains(BILL_TOTAL_MESSAGE))
            if (provider.getContract() != null)
                messages.add(new Message(provider.getID(), getBillTotal(), MessageState.received));
            else //to add reponse from new contract
                messages.add(new Message(provider.getID(), "You don't have a contract with us.", MessageState.received));

        if (message.getMessage().contains(CONTRACT_NEW_MESSAGE)) {
            if (provider.getContract() != null)
                messages.add(new Message(provider.getID(), "You already have a contract.", MessageState.received));
            else
                messages.add(new Message(provider.getID(), "If you'd like to make a new contract, please go back to the previous screen and press the + button.", MessageState.received));
        }

        if (message.getMessage().contains(CONTRACT_RENEW_MESSAGE))
            messages.add(new Message(provider.getID(), String.format("If you have any inquiries about your current contract or would like to add new options, please call us at the following phone number: %s.", provider.getPhone()), MessageState.received));

        if (message.getMessage().contains(CONTRACT_STOP_MESSAGE))
            messages.add(new Message(provider.getID(), "One of our employees will get in touch with you soon. We are sorry to see you go!", MessageState.received));

        if (message.getMessage().contains(ASSISTANCE))
            messages.add(new Message(provider.getID(), "Dispatching a team to your location. They should arrive shortly.", MessageState.received));

        if (message.getMessage().contains(PHONE_NUMBER))
            messages.add(new Message(provider.getID(), String.format("You can call us at %s, available 24/7.", provider.getPhone()), MessageState.received));

        if (message.getMessage().contains(EMAIL))
            messages.add(new Message(provider.getID(), String.format("You can write us at %s.", provider.getEmail()), MessageState.received));

        if (message.getMessage().contains(HELP)) {
            messages.add(getHelp());
            return messages;
        }

        if (message.getMessage().contains(NO)) {
            messages.add(new Message(provider.getID(), "Have a good day!", MessageState.received));
            return messages;
        }
        if (message.getMessage().contains(YES)) {
            messages.add(getHelp());
            return messages;
        }

        if (messages.isEmpty()) {
            messages.add(new Message(provider.getID(), "Sorry, I didn't understand your request.", MessageState.received));
            messages.add(getHelp());
        }
        else
            messages.add(new Message(provider.getID(), "Is there anything else we can assist you with?", MessageState.received));
        return messages;
    }

    public Message getHelp() {
        return new Message(provider.getID(), "Some things you can ask me:\nbill total\nnew contract\nrenew contract\nstop contract\nassistance\nphone\nemail", MessageState.received);
    }

    private String getBillTotal() {
        String billTotal = "";
        //for each utility display latest bill
        List<Bill> bills = chatActivityViewModel.getBills();
        for (Bill bill : bills) {
            billTotal = String.format("%s%s: %s, due in %s", billTotal, bill.getUtility().toString(), bill.displayPrice(), bill.getDueDate());
        }
        return billTotal;
    }
}
