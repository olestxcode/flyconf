package com.github.olestxcode.flyconf.exception;

public class InvalidConfigurationException extends RuntimeException {

    public InvalidConfigurationException(String message) {
        super(message);
    }

    public InvalidConfigurationException(String message, Throwable t) {
        super(message, t);
    }
}
