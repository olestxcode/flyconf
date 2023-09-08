package com.github.olestxcode.flyconf.loader;

import com.github.olestxcode.flyconf.exception.InvalidConfigurationException;
import com.github.olestxcode.flyconf.io.InputStreamSource;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.internal.LazilyParsedNumber;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Field;
import java.util.AbstractMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class JsonConfigurationLoader extends AbstractPropertyMapLoader {

    private static final Gson GSON = new Gson();
    private static final Field JSON_PRIMITIVE_VALUE_FIELD;
    private static final Field LAZILY_PARSED_NUMBER_VALUE_FIELD;

    static {
        try {
            JSON_PRIMITIVE_VALUE_FIELD = JsonPrimitive.class.getDeclaredField("value");
            JSON_PRIMITIVE_VALUE_FIELD.setAccessible(true);

            LAZILY_PARSED_NUMBER_VALUE_FIELD = LazilyParsedNumber.class.getDeclaredField("value");
            LAZILY_PARSED_NUMBER_VALUE_FIELD.setAccessible(true);
        } catch (NoSuchFieldException exception) {
            throw new RuntimeException(exception);
        }
    }

    public JsonConfigurationLoader(@NotNull InputStreamSource streamSource) {
        super(streamSource);
    }

    @Override
    protected Stream<Map.Entry<String, Object>> load(InputStream stream) {
        try (Reader reader = new InputStreamReader(stream)) {
            return resolveObject(GSON.fromJson(reader, JsonObject.class));
        } catch (IOException exception) {
            throw new InvalidConfigurationException("Failed to parse JSON configuration!", exception);
        }
    }

    private static Stream<Map.Entry<String, Object>> resolveObject(JsonObject object) {
        return object.asMap().entrySet().stream()
                .map(entry -> new AbstractMap.SimpleEntry<>(entry.getKey(), resolve(entry.getValue())));
    }

    private static Object resolve(JsonElement element) {
        if (element.isJsonNull()) {
            return null;
        } else if (element.isJsonPrimitive()) {
            try {
                Object primitiveValue = JSON_PRIMITIVE_VALUE_FIELD.get(element.getAsJsonPrimitive());

                if (primitiveValue instanceof LazilyParsedNumber) {
                    String number = (String) LAZILY_PARSED_NUMBER_VALUE_FIELD.get(primitiveValue);

                    if (number.contains(".")) {
                        return Double.parseDouble(number);
                    } else {
                        return Integer.parseInt(number);
                    }
                }

                return primitiveValue;
            } catch (IllegalAccessException exception) {
                throw new InvalidConfigurationException("Can't access value of JsonPrimitive", exception);
            }
        } else if (element.isJsonObject()) {
            return resolveObject(element.getAsJsonObject());
        } else {
            return element.getAsJsonArray().asList().stream()
                    .map(JsonConfigurationLoader::resolve)
                    .collect(Collectors.toList());
        }
    }
}