package com.github.olestxcode.flyconf.adapter;

import com.github.olestxcode.flyconf.annotation.Convention;

public class KebabCaseAdapter implements Convention.ConventionAdapter {

    @Override
    public String adapt(String methodName) {
        return methodName.replaceAll("([a-z0-9])([A-Z])", "$1-$2").toLowerCase();
    }
}
