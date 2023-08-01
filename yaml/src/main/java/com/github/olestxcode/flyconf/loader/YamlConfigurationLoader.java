package com.github.olestxcode.flyconf.loader;

import com.github.olestxcode.flyconf.exception.InvalidConfigurationException;
import com.github.olestxcode.flyconf.io.InputStreamSource;
import org.jetbrains.annotations.NotNull;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;
import java.util.stream.Stream;

public final class YamlConfigurationLoader extends AbstractPropertyMapLoader {

    public YamlConfigurationLoader(@NotNull InputStreamSource streamSource) {
        super(streamSource);
    }

    @Override
    protected Stream<Map.Entry<String, Object>> load(InputStream stream) {
        try {
            return (new Yaml()).<Map<String, Object>>load(stream).entrySet().stream();
        } catch (Exception exception) {
            throw new InvalidConfigurationException("Failed to parse YAML configuration!", exception);
        }
    }
}