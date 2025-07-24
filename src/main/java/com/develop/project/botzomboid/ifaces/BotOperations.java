package com.develop.project.botzomboid.ifaces;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface BotOperations {
    void sendText(Long who, String text);
    void onUpdateReceived(Update update);
}
