package com.develop.project.botzomboid.handler;

import com.develop.project.botzomboid.bot.keyboard.BaseKeyboard;
import com.develop.project.botzomboid.bot.keyboard.PollKeyboard;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class CallbackQueryHandler {
    private final PollKeyboard pollKeyboard;
    private final BaseKeyboard baseKeyboard;
    private final MessageHandler messageHandler;


    public BotApiMethod<?> processCallbackQuery(CallbackQuery callbackQuery, Update update) {
        //todo write code this
        // обработка нажатия кнопки
        SendMessage sendMessage = new SendMessage();
        if(callbackQuery.getData().equals("health")){

            sendMessage.setChatId(callbackQuery.getMessage().getChatId().toString());
            sendMessage.setText("Выберите значение которое будет установлено");
            return sendMessage;
        }

        return null;

    }

    public BotApiMethod<?> processBaseKeyboardQuery(Update update) {

        Message message = update.getMessage();

        if (message.hasText()) {
            SendMessage sendMessage = new SendMessage();
            if("/start".equals(message.getText())) {
                sendMessage.setChatId(message.getChatId().toString());
                sendMessage.setText("Добро пожаловать в бот по проджект зомбоид!" + "\n" + "выберите опцию");
                sendMessage.setReplyMarkup(baseKeyboard.getMainMenuKeyboard());
                return sendMessage;
            }

            if("/vote".equals(message.getText())) {
                sendMessage.setText("Вот список команд для голосования");
                sendMessage.setChatId(message.getChatId());
                sendMessage.setReplyMarkup(pollKeyboard.getInlineMessageButtons());
                return sendMessage;
            }
        }
        return null;
    }
}
