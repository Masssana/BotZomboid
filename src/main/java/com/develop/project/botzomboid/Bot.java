package com.develop.project.botzomboid;

import com.develop.project.botzomboid.ifaces.BotOperations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class Bot extends TelegramLongPollingBot implements BotOperations {
    @Value("${bot.token}")
    private String token;
    @Value("${bot.name}")
    private String name;

    @Override
    public void onUpdateReceived(Update update) {
        var message = update.getMessage();
        var user = message.getFrom();
        var id = user.getId();

        if(update.hasMessage() && message.hasText()) {

            switch (message.getText()){
                case "/vote":
                    startCommandOnReceivedVote(id);
                    break;
            }
        }

        sendText(id, message.getText());
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
    public void sendText(Long who, String text) {
        SendMessage sm = SendMessage.builder()
                .chatId(who.toString())
                .text(text).build();

        trySending(sm);
    }

    private void startCommandOnReceivedVote(Long id){
        String answer = "Hello, here is all commands that you can choose: " + "\n" + "/Add health to zombies, " + "\n" + "/Decrease zombies health";
        sendText(id, answer);
    }

    private void trySending(SendMessage message) {
        try {
            execute(message);
        }catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
