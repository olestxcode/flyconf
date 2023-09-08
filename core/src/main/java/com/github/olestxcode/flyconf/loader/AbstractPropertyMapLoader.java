package com.github.olestxcode.flyconf.loader;

import com.github.olestxcode.flyconf.exception.InvalidConfigurationException;
import com.github.olestxcode.flyconf.io.InputStreamSource;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.AbstractMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
public abstract class AbstractPropertyMapLoader implements PropertyMapLoader {

    @NotNull
    private final InputStreamSource streamSource;

    protected abstract Stream<Map.Entry<String, Object>> load(InputStream stream);

    @Override
    public final Map<String, Object> load() {
        try (InputStream stream = streamSource.getInputStream()) {
            return load(stream)
                    .flatMap(AbstractPropertyMapLoader::flatten)
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        } catch (IOException exception) {
            throw new InvalidConfigurationException("Failed to read configuration", exception);
        }
    }

    @SuppressWarnings("unchecked")
    private static Stream<Map.Entry<String, Object>> flatten(Map.Entry<String, Object> entry) {
        if (entry.getValue() instanceof Stream<?>) {
            return flatten(entry.getKey(), (Stream<Map.Entry<String, Object>>) entry.getValue());
        } else if (entry.getValue() instanceof Map<?, ?>) {
            return flatten(entry.getKey(), ((Map<String, Object>) entry.getValue()).entrySet().stream());
        }
        return Stream.of(entry);
    }

    private static Stream<Map.Entry<String, Object>> flatten(String parentPath, Stream<Map.Entry<String, Object>> stream) {
        return stream
                .map(entry -> new AbstractMap.SimpleEntry<>(parentPath + "." + entry.getKey(), entry.getValue()))
                .flatMap(AbstractPropertyMapLoader::flatten);
    }
}