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
            String[] parts = message.getText().split("\\s+", 2);
            if(parts.length != 2){
                return new SendMessage(message.getChatId().toString(), "Please use the format of <key> <value>!");
            }
            String key = parts[0];
            String value = parts[1];

            try {
                luaConfigService.updateConfigWithValidation(key, value);
                return new SendMessage(message.getChatId().toString(), "Succesfully changed");
            }catch (Exception e){
                return new SendMessage(message.getChatId().toString(), e.getMessage());
            }
        }
        return null;
    }
}
