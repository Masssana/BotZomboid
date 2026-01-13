package com.develop.project.botzomboid.Config;

import com.develop.project.botzomboid.Bot;
import com.develop.project.botzomboid.CommandsHandler;
import com.develop.project.botzomboid.StartPoll;
import com.develop.project.botzomboid.ifaces.Poll;
import com.develop.project.botzomboid.ifaces.TextSender;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
@RequiredArgsConstructor
public class BotConfiguration {
    private final TextSender textSender;
    private final CommandsHandler commandsHandler;
    private final StartPoll startPoll;

    @Bean
    public Bot bot(@Value("${bot.token}") String token,
                   @Value("${bot.name}") String name) {
        return new Bot(token, name, textSender, commandsHandler, startPoll);
    }

    @Bean
    public TelegramBotsApi telegramBotsApi(Bot bot) throws TelegramApiException {
        TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
        api.registerBot(bot);
        return api;
    }
}
