package com.develop.project.botzomboid.bot;

import com.develop.project.botzomboid.bot.keyboard.BaseKeyboard;
import com.develop.project.botzomboid.bot.keyboard.PollKeyboard;
import com.develop.project.botzomboid.handler.CallbackQueryHandler;
import com.develop.project.botzomboid.handler.MessageHandler;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.starter.SpringWebhookBot;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class Bot extends SpringWebhookBot {

    String botName;

    MessageHandler messageHandler;
    CallbackQueryHandler callbackQueryHandler;
    PollKeyboard pollKeyboard;
    BaseKeyboard baseKeyboard;

    public Bot(SetWebhook setWebhook, String botName, String botToken,
               MessageHandler messageHandler,
               CallbackQueryHandler callbackQueryHandler,
               PollKeyboard pollKeyboard,
               BaseKeyboard baseKeyboard) {
        super(setWebhook, botToken);
        this.botName = botName;
        this.messageHandler = messageHandler;
        this.callbackQueryHandler = callbackQueryHandler;
        this.pollKeyboard = pollKeyboard;
        this.baseKeyboard = baseKeyboard;
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

    private BotApiMethod<?> handleUpdate(Update update) {
        if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            return callbackQueryHandler.processCallbackQuery(callbackQuery);
        } else {
            Message message = update.getMessage();
            if (message.hasText()) {
                if("/start".equals(message.getText())) {
                    SendMessage sendMessage = new SendMessage();
                    sendMessage.setChatId(message.getChatId().toString());
                    sendMessage.setText("Добро пожаловать в бот по проджект зомбоид!" + "\n" + "выберите опцию");
                    sendMessage.setReplyMarkup(baseKeyboard.getMainMenuKeyboard());
                    return sendMessage;
                }
                return messageHandler.answerMessage(update.getMessage());
            }
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