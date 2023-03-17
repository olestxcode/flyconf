package com.github.olestxcode.flyconf.loader;

import com.github.olestxcode.flyconf.annotation.Configuration;
import com.github.olestxcode.flyconf.annotation.Mandatory;
import com.github.olestxcode.flyconf.annotation.MultiValue;
import com.github.olestxcode.flyconf.annotation.Property;

import java.util.List;

@Configuration
public interface TestProperties {

    String key1();

    TestPathProperties path();

    @Property("prop")
    String myProp();

    @Property("myprop.val")
    String anotherProp();

    @MultiValue
    List<String> myList();

    @MultiValue(elementType = Integer.class)
    List<Integer> ints();

    @Mandatory
    String mandatoryProp();
}
