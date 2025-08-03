package com.develop.project.botzomboid.utils;

import com.develop.project.botzomboid.Bot;
import com.develop.project.botzomboid.CreateKeyBoard;
import com.develop.project.botzomboid.ifaces.TextSender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@RequiredArgsConstructor
@Component
public class TextSenderUtil implements TextSender {

    @Override
    public void sendText(Long who, String text) {
        SendMessage sm = SendMessage.builder()
                .chatId(who.toString())
                .text(text).build();
        CreateKeyBoard.startVote(sm);

        trySending(sm);
    }

    private void trySending(SendMessage message) {
            try {
                Bot.getInstance().execute(message);
            }catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
    }

}
