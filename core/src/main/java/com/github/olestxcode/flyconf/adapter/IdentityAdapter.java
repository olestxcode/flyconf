package com.github.olestxcode.flyconf.adapter;

import com.github.olestxcode.flyconf.annotation.Convention;

public class IdentityAdapter implements Convention.ConventionAdapter {

    @Override
    public String adapt(String methodName) {
        return methodName;
    }
}
