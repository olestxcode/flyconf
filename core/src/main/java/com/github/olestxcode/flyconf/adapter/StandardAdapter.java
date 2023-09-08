package com.github.olestxcode.flyconf.adapter;

public enum StandardAdapter implements ConventionAdapter {

    IDENTITY {
        @Override
        public String adapt(String methodName) {
            return methodName;
        }
    },
    KEBAB_CASE {
        @Override
        public String adapt(String methodName) {
            return StandardAdapter.adapt(methodName, '-').toLowerCase();
        }
    },
    SCREAMING_SNAKE_CASE {
        @Override
        public String adapt(String methodName) {
            return StandardAdapter.adapt(methodName, '_').toUpperCase();
        }
    },
    SNAKE_CASE {
        @Override
        public String adapt(String methodName) {
            return StandardAdapter.adapt(methodName, '_').toLowerCase();
        }
    };

    private static String adapt(String methodName, char separator) {
        return methodName.replaceAll("([a-z0-9])([A-Z])", "$1" + separator + "$2");
    }
}