package com.develop.project.botzomboid.controller;

import com.develop.project.botzomboid.bot.Bot;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@RestController
public class WebhookController {
    private final Bot writeReadBot;

    public WebhookController(Bot writeReadBot) {
        this.writeReadBot = writeReadBot;
    }

    @PostMapping("/")
    public BotApiMethod<?> onUpdateReceived(@RequestBody Update update) {
        return writeReadBot.onWebhookUpdateReceived(update);
    }

    @GetMapping()
    public ResponseEntity<String> testStart(){
        return ResponseEntity.ok("Success");
    }
}
