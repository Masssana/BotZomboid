package com.develop.project.botzomboid.bot.keyboard;

import lombok.Getter;

@Getter
public enum PollPointEnum {

    HEALTH("Здоровье"),
    SPEED("Скорость"),
    RESPAWN("Респавн"),
    LOOT("Кол-во лута");

    private String name;

    PollPointEnum(String name) {
        this.name = name;
    }
}
