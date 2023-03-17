package com.github.olestxcode.flyconf.loader;

import com.github.olestxcode.flyconf.exception.InvalidConfigurationException;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class YamlConfigurationLoader implements PropertyMapLoader {

    private final InputStream inputStream;

    public YamlConfigurationLoader(File file) throws FileNotFoundException {
        this.inputStream = new FileInputStream(file);
    }

    public YamlConfigurationLoader(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public Map<String, Object> load() {
        try {
            Yaml yaml = new Yaml();
            Map<String, Object> data = yaml.load(inputStream);
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
