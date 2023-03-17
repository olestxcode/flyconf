package com.github.olestxcode.flyconf;

import com.github.olestxcode.flyconf.annotation.Convention;
import com.github.olestxcode.flyconf.loader.PropertyMapLoader;

import java.util.function.Function;

public interface FlyconfInstance {

    <T> T load(PropertyMapLoader loader, Class<T> into);

    <T> FlyconfInstance registerCustomParser(Class<T> type, Function<String, T> parserFunction);

    FlyconfInstance setDefaultConvention(Convention.ConventionAdapter adapter);

    Convention.ConventionAdapter getConventionAdapter();
}
