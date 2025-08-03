package com.develop.project.botzomboid;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.polls.SendPoll;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

@Component
public class StartPoll {
    public void startPoll(Long id) throws TelegramApiException {
        SendPoll sendPoll = new SendPoll();
        sendPoll.setChatId(id);
        sendPoll.setQuestion("Какой диапазон вы хотите?");

        List<String> options = new ArrayList<>();
        options.add("0-25");
        options.add("25-50");
        options.add("50-75");
        options.add("75-100");

        sendPoll.setOptions(options);
        sendPoll.setType("regular");
        Bot.getInstance().execute(sendPoll);
    }
}
