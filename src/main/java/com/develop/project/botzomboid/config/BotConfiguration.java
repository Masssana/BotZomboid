package com.develop.project.botzomboid.config;

import com.develop.project.botzomboid.bot.Bot;
import com.develop.project.botzomboid.handler.CallbackQueryHandler;
import com.develop.project.botzomboid.handler.MessageHandler;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;

@Configuration
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BotConfiguration {

    @Value("${telegram.webhook-path}")
    String webhookPath;
    @Value("${telegram.bot-name}")
    String botName;
    @Value("${telegram.bot-token}")
    String botToken;

    @Bean
    public Bot getBot(SetWebhook webhook,
                      MessageHandler messageHandler,
                      CallbackQueryHandler callbackQueryHandler){
        return new Bot(webhook, botName, botToken, messageHandler, callbackQueryHandler);
    }

    @Bean
    public SetWebhook createSetWebhook(){
        return SetWebhook.builder()
                .url(webhookPath)
                .build();
    }
}