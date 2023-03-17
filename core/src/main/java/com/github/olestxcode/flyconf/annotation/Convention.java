package com.github.olestxcode.flyconf.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Convention {

    Class<? extends ConventionAdapter> adapter();

    interface ConventionAdapter {
        String adapt(String methodName);
    }
}
