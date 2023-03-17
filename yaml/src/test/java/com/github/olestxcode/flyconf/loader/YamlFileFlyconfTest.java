package com.github.olestxcode.flyconf.loader;

import com.github.olestxcode.flyconf.Flyconf;
import com.github.olestxcode.flyconf.FlyconfInstance;
import com.github.olestxcode.flyconf.exception.InvalidConfigurationException;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class YamlFileFlyconfTest {

    @Test
    void testYamlConfigurationLoader() {
        InputStream resource = getClass().getResourceAsStream("/test.yaml");
        FlyconfInstance instance = Flyconf.newInstance();
        var props = instance.load(new YamlConfigurationLoader(resource), TestProperties.class);

        assertEquals("val1", props.key1());
        assertEquals("propVal", props.myProp());
        assertEquals("mypropval", props.anotherProp());
        assertEquals(List.of("e1", "e2", "e3"), props.myList());
        assertEquals(List.of(5, 9, 12), props.ints());
        InvalidConfigurationException exception = assertThrows(InvalidConfigurationException.class,
                props::mandatoryProp);
        assertEquals("Mandatory property mandatoryProp is not found!", exception.getMessage());

        var path = props.path();

        assertEquals("v1", path.k1());
        assertEquals(1, path.k2());
        assertEquals(Duration.of(5, ChronoUnit.SECONDS), path.duration());
        assertEquals(Optional.empty(), path.opt());
    }
}
