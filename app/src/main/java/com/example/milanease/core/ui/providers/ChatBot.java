package com.example.milanease.core.ui.providers;

import com.example.milanease.core.ui.dashboard.Utility;

import java.util.ArrayList;
import java.util.Collections;
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

    private ChatBot() {}


    public void setViewModel(ChatActivityViewModel chatActivityViewModel) {
        this.chatActivityViewModel = chatActivityViewModel;
    }

    public void reply(Message message) {
        List<Message> messages = new ArrayList<>();

        if (message.getMessage().contains(BILL_TOTAL_MESSAGE))
            messages.add(new Message(chatActivityViewModel.getProvider().getValue().getID(), getBillTotal(), MessageState.received));

        if (message.getMessage().contains(CONTRACT_NEW_MESSAGE)) {
            if (chatActivityViewModel.getProvider().getValue().getContract() != null)
                messages.add(new Message(chatActivityViewModel.getProvider().getValue().getID(), "You already have a contract.", MessageState.received));
            else
                messages.add(new Message(chatActivityViewModel.getProvider().getValue().getID(), "If you'd like to make a new contract, please go back to the previous screen and press the + button.", MessageState.received));
        }

        if (message.getMessage().contains(CONTRACT_RENEW_MESSAGE))
            messages.add(new Message(chatActivityViewModel.getProvider().getValue().getID(), String.format("If you have any inquiries about your current contract or would like to add new options, please call us at the following phone number: %s.", chatActivityViewModel.getProvider().getValue().getPhone()), MessageState.received));

        if (message.getMessage().contains(CONTRACT_STOP_MESSAGE))
            messages.add(new Message(chatActivityViewModel.getProvider().getValue().getID(), "One of our employees will get in touch with you soon. We are sorry to see you go!", MessageState.received));

        if (message.getMessage().contains(ASSISTANCE))
            messages.add(new Message(chatActivityViewModel.getProvider().getValue().getID(), "Dispatching a team to your location. They should arrive shortly.", MessageState.received));

        if (message.getMessage().contains(PHONE_NUMBER))
            messages.add(new Message(chatActivityViewModel.getProvider().getValue().getID(), String.format("You can call us at %s, available 24/7.", chatActivityViewModel.getProvider().getValue().getPhone()), MessageState.received));

        if (message.getMessage().contains(EMAIL))
            messages.add(new Message(chatActivityViewModel.getProvider().getValue().getID(), String.format("You can write us at %s.", chatActivityViewModel.getProvider().getValue().getEmail()), MessageState.received));

        if (message.getMessage().contains(HELP)) {
            messages.add(getHelp());
            chatActivityViewModel.addMessages(messages);
            return;
        }



        if (message.getMessage().contains(NO)) {
            messages.add(new Message(chatActivityViewModel.getProvider().getValue().getID(), "Have a good day!", MessageState.received));
            chatActivityViewModel.addMessages(messages);
            return;
        }
        if (message.getMessage().contains(YES)) {
            messages.add(getHelp());
            chatActivityViewModel.addMessages(messages);
            return;
        }

        if (messages.isEmpty()) {
            messages.add(new Message(chatActivityViewModel.getProvider().getValue().getID(), "Sorry, I didn't understand your request.", MessageState.received));
            messages.add(getHelp());
        }
        else
            messages.add(new Message(chatActivityViewModel.getProvider().getValue().getID(), "Is there anything else we can assist you with?", MessageState.received));
        chatActivityViewModel.addMessages(messages);
    }

    public Message getHelp() {
        return new Message(chatActivityViewModel.getProvider().getValue().getID(), "Some things you can ask me:\nbill total\nnew contract\nrenew contract\nstop contract\nassistance\nphone\nemail", MessageState.received);
    }

    private String getBillTotal() {
        String billTotal = "";
        for (Utility utility : chatActivityViewModel.getProvider().getValue().getUtilities()) {
            Collections.sort(chatActivityViewModel.getBills().getValue());
        }
        return billTotal;
    }
}
