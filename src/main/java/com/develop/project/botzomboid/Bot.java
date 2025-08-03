package com.develop.project.botzomboid;

import com.develop.project.botzomboid.ifaces.BotOperations;
import com.develop.project.botzomboid.ifaces.TextSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


public class Bot extends TelegramLongPollingBot implements BotOperations {

    private final String name;
    private final String token;
    private static volatile Bot instance;
    private final TextSender textSender;
    private final CommandsHandler commandsHandler;
    private final StartPoll startPoll;

    public Bot(@Value("${bot.token}") String token,
               @Value("${bot.name}") String name,
               TextSender textSender,
               CommandsHandler commandsHandler, 
               StartPoll startPoll) {
        super(token);
        this.token = token;
        this.name = name;
        this.textSender = textSender;
        this.commandsHandler = commandsHandler;
        this.startPoll = startPoll;
        instance = this;
    }

    @Override
    public void onUpdateReceived(Update update) {
        var message = update.getMessage();
        var user = message.getFrom();
        var id = user.getId();

        if(update.hasMessage() && message.hasText()) {

            switch (message.getText()){
                case "/vote":
                    commandsHandler.startCommandOnReceivedVote(id);
                    try {
                        startPoll.startPoll(id);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                    break;
            }
        }

        textSender.sendText(id, message.getText());
        System.out.println(user.getFirstName() + " wrote " + message.getText());
    }

    @Override
    public String getBotUsername() {
        return name;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void execute(SendMessage sendMessage) throws TelegramApiException{
        super.execute(sendMessage);
    }

    public static Bot getInstance() {
        if (instance == null) {
            throw new IllegalStateException("Bot has not been initialized yet");
        }
        return instance;
    }

}
