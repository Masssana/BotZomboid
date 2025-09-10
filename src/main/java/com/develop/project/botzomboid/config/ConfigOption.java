package com.develop.project.botzomboid.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public class ConfigOption {
    private String key;
    private String description;
    private Class<?> type;

    public static final Map<String, ConfigOption> CONFIG_OPTIONS = Map.of(
            "Zombies", new ConfigOption("Zombies", "Population mulitplier", Integer.class),
            "StartYear", new ConfigOption("StartYear", "Year when games starts", Integer.class),
            "StarterKit", new ConfigOption("StarterKit", "Spawn with starter kit", Boolean.class)
    );
}
