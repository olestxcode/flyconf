package com.github.olestxcode.flyconf.loader;

import com.github.olestxcode.flyconf.exception.InvalidConfigurationException;

import java.io.*;
import java.util.Map;
import java.util.Properties;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class PropertiesConfigurationLoader implements PropertyMapLoader {

    private final Supplier<InputStream> inputStreamSupplier;

    public PropertiesConfigurationLoader(Supplier<InputStream> inputStreamSupplier) {
        this.inputStreamSupplier = inputStreamSupplier;
    }

    @Override
    public Map<String, Object> load() {
        try (InputStream inputStream = inputStreamSupplier.get()) {
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
