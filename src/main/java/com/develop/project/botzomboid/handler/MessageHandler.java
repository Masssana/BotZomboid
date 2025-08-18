package com.develop.project.botzomboid.handler;

import com.develop.project.botzomboid.services.LuaConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
//обрабатывает сообщения с клавиатуры
public class MessageHandler {
    private final LuaConfigService luaConfigService;

    public BotApiMethod<?> answerMessage(Update update) {
        //todo write answer this
        // обработка отправки текста
        Message message = update.getMessage();
        if(message.hasText() && !message.getText().startsWith("/")){
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(message.getChatId().toString());
            try {
                int value = Integer.parseInt(message.getText());
                luaConfigService.updateConfig("StartYear", String.valueOf(value));
                sendMessage.setText("StartYear изменен на " + value);
                return sendMessage;
            }catch (Exception e){
                sendMessage.setText("Введите пожалуйста число: ");
                return sendMessage;
            }
        }
        return null;
    }
}
