package com.develop.project.botzomboid.handler;

import com.develop.project.botzomboid.bot.keyboard.PollPointEnum;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
//обрабатывает сообщения с клавиатуры
public class MessageHandler {

    public BotApiMethod<?> answerMessage(Message message) {
        //todo write answer this
        // обработка отправки текста

        if(message.getText().equals("/vote")){
            StringBuffer sb = new StringBuffer();

            for(PollPointEnum pollPointEnum : PollPointEnum.values()){
                sb.append("/").append(pollPointEnum.name()).append("\n");
            }
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(message.getChatId().toString());
            sendMessage.setText(sb.toString());
            return sendMessage;
        }
        return null;
    }
}
