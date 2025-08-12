package com.develop.project.botzomboid.bot.keyboard;

import lombok.Getter;

@Getter
public enum PollPointEnum {

    HEALTH("/Health"),
    SPEED("/Speed"),
    RESPAWN("/Respawn"),
    LOOT("/AmountOfLoot");

    private String name;

    PollPointEnum(String name) {
        this.name = name;
    }
}
