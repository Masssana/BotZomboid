package com.develop.project.botzomboid.handler;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class MessageHandler {

    public BotApiMethod<?> answerMessage(Message message) {
        //todo write answer this
        // обработка отправки текста
        return null;
    }
}
