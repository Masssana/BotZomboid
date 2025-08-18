package com.develop.project.botzomboid.bot.keyboard;

import lombok.Getter;

@Getter
public enum ButtonNameEnum {
    vote("Голосовать!"),
    test("Тестовая кнопка");

    private final String buttonName;

    ButtonNameEnum(String buttonName) {
        this.buttonName = buttonName;
    }
}
