package com.develop.project.botzomboid.bot;

import lombok.Getter;

@Getter
public enum BotMessageEnum {
    EXCEPTION_ILLEGAL_MESSAGE("Пришло некорректное сообщение"),
    UNDEFINED("Неизвестная ошибка");

    private String message;

    BotMessageEnum(String message) {
        this.message = message;
    }
}
