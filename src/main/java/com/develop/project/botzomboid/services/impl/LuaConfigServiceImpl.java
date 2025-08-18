package com.develop.project.botzomboid.services.impl;

import com.develop.project.botzomboid.services.LuaConfigService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LuaConfigServiceImpl implements LuaConfigService {
    @Value("${config.lua.path}")
    private String pathToConfig;

    @Override
    public Map<String, String> parseConfig() throws IOException {
        Path path = Paths.get(pathToConfig);
        List<String> lines = Files.readAllLines(path);
        Map<String, String> configMapParsed = new HashMap<>();
        for(String line : lines) {
            line = line.trim();
            if(line.contains("=") && !line.startsWith("--")) {
                String[] parts = line.split("=");
                if(parts.length == 2) {
                    String key = parts[0].trim();
                    String value = parts[1].trim();
                    configMapParsed.put(key, value);
                }
            }
        }
        return configMapParsed;
    }

    @Override
    public void updateConfig(String keyToUpdate, String newValue) throws IOException {
        Path path = Paths.get(pathToConfig);
        List<String> lines = Files.readAllLines(path);
        List<String> newLines = new ArrayList<>();
        for(String line : lines) {
            if(line.trim().startsWith(keyToUpdate + " =")){
                String updatedLine = "    " + keyToUpdate + " = " + newValue + ","; //fixme рассмотреть возможность пофиксить добавление доп пробелов строкой на что то получше
                newLines.add(updatedLine);
            }else{
                newLines.add(line);
            }
        }
        Files.write(path, newLines);
    }

    @Override
    public String getConfigValue(String key) throws IOException {
        Map<String, String> configMap = parseConfig();
        return configMap.getOrDefault("key", "Not Found");
    }
}
