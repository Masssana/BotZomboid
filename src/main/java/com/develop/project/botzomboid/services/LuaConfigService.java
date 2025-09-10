package com.develop.project.botzomboid.services;

import java.io.IOException;
import java.util.Map;

public interface LuaConfigService {
    Map<String, String> parseConfig() throws IOException;
    String getConfigValue(String key) throws IOException;

    void updateConfigWithValidation(String keyToUpdate, String newValue) throws IOException;
}
