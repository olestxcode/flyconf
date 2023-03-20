package com.github.olestxcode.flyconf.loader.properties;

import com.github.olestxcode.flyconf.Flyconf;
import com.github.olestxcode.flyconf.FlyconfInstance;
import com.github.olestxcode.flyconf.adapter.KebabCaseAdapter;
import com.github.olestxcode.flyconf.exception.InvalidConfigurationException;
import com.github.olestxcode.flyconf.loader.PropertiesConfigurationLoader;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.InputStream;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PropertiesFileFlyconfTest {

    @ParameterizedTest
    @ValueSource(strings = {"test", "kebab-test"})
    void testPropertiesConfigurationLoader(String name) {
        Supplier<InputStream> resource = () -> getClass().getResourceAsStream(String.format("/%s.properties", name));
        FlyconfInstance instance = Flyconf.newInstance();
        if (name.equals("kebab-test")) {
            instance.setDefaultConvention(new KebabCaseAdapter());
        }
        var props = instance.load(new PropertiesConfigurationLoader(resource), TestProperties.class);

        assertEquals("val1", props.key1());
        assertEquals("propVal", props.myProp());
        assertEquals("mypropval", props.anotherProp());
        assertEquals(List.of("e1", "e2", "e3"), props.myList());
        assertEquals(List.of(5, 9, 12), props.ints());
        InvalidConfigurationException exception = assertThrows(InvalidConfigurationException.class,
                props::mandatoryProp);
        assertEquals(String.format(
                "Mandatory property %s is not found!", name.equals("test") ? "mandatoryProp" : "mandatory-prop"),
                exception.getMessage());
        assertEquals("Default", props.def());

        var path = props.path();

        assertEquals("v1", path.k1());
        assertEquals(1, path.k2());
        assertEquals(Duration.of(5, ChronoUnit.SECONDS), path.duration());
        assertEquals(Optional.empty(), path.opt());

        props.reload();
    }

    @Test
    void testGreetingPropertiesKebabCase() {
        Supplier<InputStream> resource = () -> getClass().getResourceAsStream("/kebab-test-greeting.properties");
        FlyconfInstance instance = Flyconf.newInstance();
        var props = instance.load(new PropertiesConfigurationLoader(resource), KebabTestGreetingProperties.class);

        assertEquals("Hello!", props.myGreetingText());
    }
}
