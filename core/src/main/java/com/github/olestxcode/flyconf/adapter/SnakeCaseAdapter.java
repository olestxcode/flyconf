package com.github.olestxcode.flyconf.adapter;

import com.github.olestxcode.flyconf.annotation.Convention;

public class SnakeCaseAdapter implements Convention.ConventionAdapter {

    @Override
    public String adapt(String methodName) {
        return methodName.replaceAll("([a-z0-9])([A-Z])", "$1_$2").toLowerCase();
    }
}
