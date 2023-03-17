package com.github.olestxcode.flyconf.loader.properties;

import com.github.olestxcode.flyconf.annotation.Configuration;
import com.github.olestxcode.flyconf.annotation.Mandatory;
import com.github.olestxcode.flyconf.annotation.MultiValue;
import com.github.olestxcode.flyconf.annotation.Property;
import com.github.olestxcode.flyconf.loader.Reloadable;

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
}
