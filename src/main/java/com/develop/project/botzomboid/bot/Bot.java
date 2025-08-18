package com.develop.project.botzomboid.bot;

import com.develop.project.botzomboid.handler.CallbackQueryHandler;
import com.develop.project.botzomboid.handler.MessageHandler;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.starter.SpringWebhookBot;

import java.io.IOException;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class Bot extends SpringWebhookBot {

    String botName;

    MessageHandler messageHandler;
    CallbackQueryHandler callbackQueryHandler;

    public Bot(SetWebhook setWebhook, String botName, String botToken,
               MessageHandler messageHandler,
               CallbackQueryHandler callbackQueryHandler) {
        super(setWebhook, botToken);
        this.botName = botName;
        this.messageHandler = messageHandler;
        this.callbackQueryHandler = callbackQueryHandler;
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        System.out.println(update);
        try {
            return handleUpdate(update);
        } catch (IllegalArgumentException e) {
            return new SendMessage(update.getMessage().getChatId().toString(),
                    BotMessageEnum.EXCEPTION_ILLEGAL_MESSAGE.getMessage());
        } catch (Exception e) {
            return new SendMessage(update.getMessage().getChatId().toString(),
                    BotMessageEnum.UNDEFINED.getMessage());
        }
    }

    private BotApiMethod<?> handleUpdate(Update update) throws IOException {
        if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            return callbackQueryHandler.processCallbackQuery(callbackQuery, update);
        }

        if(update.getMessage().toString().contains("/")){
            return callbackQueryHandler.processBaseKeyboardQuery(update);
        }

        if(!update.getMessage().toString().startsWith("/")){
            return messageHandler.answerMessage(update);
        }
        return null;

    }

    @Override
    public String getBotPath() {
        return getSetWebhook().getUrl();
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public void onRegister() {
        super.onRegister();
        System.out.println("Registering bot " + botName);
    }
}