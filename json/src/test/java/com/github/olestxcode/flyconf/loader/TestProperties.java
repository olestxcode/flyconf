package com.github.olestxcode.flyconf.loader;

import com.github.olestxcode.flyconf.annotation.*;

import java.util.List;

@Configuration
public interface TestProperties extends Reloadable {

    String key1();

    TestPathProperties path();

    @Property("prop")
    String myProp();

    @Property("myprop.val")
    String anotherProp();

    @MultiValue
    List<String> myList();

    @MultiValue
    List<Integer> ints();

    @Mandatory
    String mandatoryProp();

    @DefaultValue("Default")
    String def();
}
