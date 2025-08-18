package com.develop.project.botzomboid.handler;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

public interface BaseHandler<T> {

    BotApiMethod<?> sendAnswer(T request);
}
