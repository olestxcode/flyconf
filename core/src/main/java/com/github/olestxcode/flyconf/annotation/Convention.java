package com.github.olestxcode.flyconf.annotation;

import com.github.olestxcode.flyconf.adapter.ConventionAdapter;
import com.github.olestxcode.flyconf.adapter.StandardAdapter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Convention {

    Class<? extends ConventionAdapter> adapterType() default StandardAdapter.class;

    StandardAdapter adapter() default StandardAdapter.IDENTITY;
}
