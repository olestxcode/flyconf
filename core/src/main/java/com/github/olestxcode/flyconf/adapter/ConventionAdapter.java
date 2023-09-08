package com.github.olestxcode.flyconf.adapter;

@FunctionalInterface
public interface ConventionAdapter {

    String adapt(String methodName);
}