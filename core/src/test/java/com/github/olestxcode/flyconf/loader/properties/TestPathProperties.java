package com.github.olestxcode.flyconf.loader.properties;

import com.github.olestxcode.flyconf.annotation.Configuration;

import java.time.Duration;
import java.util.Optional;

@Configuration
public interface TestPathProperties {

    String k1();

    int k2();

    Duration duration();

    Optional<String> opt();
}
