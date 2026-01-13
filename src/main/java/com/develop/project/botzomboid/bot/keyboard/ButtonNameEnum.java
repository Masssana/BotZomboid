package com.develop.project.botzomboid.bot.keyboard;

import lombok.Getter;

@Getter
public enum ButtonNameEnum {
    CREATE_VOTE_BUTTON("Голосовать!"),
    TEST_BUTTON("Тестовая кнопка");

    private final String buttonName;

    ButtonNameEnum(String buttonName) {
        this.buttonName = buttonName;
    }
}
