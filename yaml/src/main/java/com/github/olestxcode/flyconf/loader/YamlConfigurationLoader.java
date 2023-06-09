package com.github.olestxcode.flyconf.loader;

import com.github.olestxcode.flyconf.exception.InvalidConfigurationException;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class YamlConfigurationLoader implements PropertyMapLoader {

    private final Supplier<InputStream> inputStreamSupplier;

    public YamlConfigurationLoader(File file) {
        this.inputStreamSupplier = () -> {
            try {
                return new FileInputStream(file);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        };
    }

    public YamlConfigurationLoader(Supplier<InputStream> inputStreamSupplier) {
        this.inputStreamSupplier = inputStreamSupplier;
    }

    @Override
    public Map<String, Object> load() {
        try {
            Yaml yaml = new Yaml();
            Map<String, Object> data = yaml.load(inputStreamSupplier.get());
            return data.entrySet().stream()
                    .flatMap(this::flatten)
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        } catch (Exception e) {
            throw new InvalidConfigurationException("Failed to parse YAML configuration!", e);
        }
    }
    private Stream<Map.Entry<String, Object>> flatten(Map.Entry<String, Object> entry) {
        if (entry.getValue() instanceof Map<?, ?>) {
            Map<String, Object> nested = (Map<String, Object>) entry.getValue();

            return nested.entrySet().stream()
                    .map(e -> new AbstractMap.SimpleEntry(entry.getKey() + "." + e.getKey(), e.getValue()))
                    .flatMap(this::flatten);
        }
        return Stream.of(entry);
    }
}
