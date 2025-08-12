package com.develop.project.botzomboid.handler;

import com.develop.project.botzomboid.bot.keyboard.PollPointEnum;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

@Component
public class CallbackQueryHandler {

    public BotApiMethod<?> processCallbackQuery(CallbackQuery callbackQuery) {
        //todo write code this
        // обработка нажатия кнопки
        if(callbackQuery.getData().equals("/vote")){
            StringBuffer sb = new StringBuffer();

            for(PollPointEnum pollPointEnum : PollPointEnum.values()){
                sb.append("/").append(pollPointEnum.name()).append("\n");
            }
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(callbackQuery.getMessage().getChatId().toString());
            sendMessage.setText(sb.toString());
            return sendMessage;
        }
        return null;
    }
}
