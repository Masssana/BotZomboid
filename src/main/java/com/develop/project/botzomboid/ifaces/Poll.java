package com.develop.project.botzomboid.ifaces;

import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public interface Poll {
    void startPoll(Long id) throws TelegramApiException;
}
