package com.github.olestxcode.flyconf.loader;

import com.github.olestxcode.flyconf.exception.InvalidConfigurationException;

import java.io.*;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

public class PropertiesConfigurationLoader implements PropertyMapLoader {

    private final InputStream inputStream;

    public PropertiesConfigurationLoader(File file) throws FileNotFoundException {
        this.inputStream = new FileInputStream(file);
    }

    public PropertiesConfigurationLoader(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public Map<String, Object> load() {
        try (inputStream) {
            Properties properties = new Properties();
            properties.load(inputStream);
            return properties.entrySet()
                    .stream()
                    .collect(Collectors.toMap(
                           objectObjectEntry -> objectObjectEntry.getKey().toString(),
                            Map.Entry::getValue
                    ));
        } catch (IOException e) {
            throw new InvalidConfigurationException("Failed to load property map!", e);
        }
    }
}
