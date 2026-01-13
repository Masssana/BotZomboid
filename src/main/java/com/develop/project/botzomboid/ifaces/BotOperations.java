package com.develop.project.botzomboid.ifaces;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public interface BotOperations {
    void onUpdateReceived(Update update);
    void execute(SendMessage message) throws TelegramApiException;
}

