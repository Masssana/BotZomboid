package com.develop.project.botzomboid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class Bot extends TelegramLongPollingBot {
    @Value("${bot.token}")
    private String token;

    @Override
    public void onUpdateReceived(Update update) {
        var message = update.getMessage();
        var user = message.getFrom();
        var id = user.getId();
        sendText(id, message.getText());
        System.out.println(user.getFirstName() + " wrote " + message.getText());
    }

    @Override
    public String getBotUsername() {
        return "ZomboidBot";
    }

    @Override
    public String getBotToken() {
        return token;
    }

    public void sendText(Long who, String text) {
        SendMessage sm = SendMessage.builder()
                .chatId(who.toString())
                .text(text).build();

        trySending(sm);
    }

    public void trySending(SendMessage message) {
        try {
            execute(message);
        }catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
