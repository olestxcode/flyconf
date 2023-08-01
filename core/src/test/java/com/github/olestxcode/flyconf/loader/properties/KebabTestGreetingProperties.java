package com.github.olestxcode.flyconf.loader.properties;

import com.github.olestxcode.flyconf.adapter.StandardAdapter;
import com.github.olestxcode.flyconf.annotation.Convention;

@Convention(adapter = StandardAdapter.KEBAB_CASE)
public interface KebabTestGreetingProperties {

    String myGreetingText();
}
