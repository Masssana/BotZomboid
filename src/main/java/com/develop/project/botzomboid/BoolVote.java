package com.develop.project.botzomboid;

import com.develop.project.botzomboid.ifaces.BooleanCommand;
import com.develop.project.botzomboid.ifaces.Poll;
import com.develop.project.botzomboid.ifaces.Processor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.polls.SendPoll;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

@Component
public class BoolVote implements BooleanCommand, Poll, Processor {

    @Override
    public void startPoll(Long id) throws TelegramApiException {
        SendPoll sendPoll = new SendPoll();
        sendPoll.setChatId(id);
        sendPoll.setQuestion("Выберите опцию");

        List<String> options = new ArrayList<>();
        options.add("Да");
        options.add("Нет");

        sendPoll.setOptions(options);
        sendPoll.setType("regular");
        Bot.getInstance().execute(sendPoll);
    }

    @Override
    public void process() {

    }

    @Override
    public String getType() {
        return "";
    }
}
