package com.github.olestxcode.flyconf.loader.properties;

import com.github.olestxcode.flyconf.adapter.KebabCaseAdapter;
import com.github.olestxcode.flyconf.annotation.Convention;

@Convention(adapter = KebabCaseAdapter.class)
public interface KebabTestGreetingProperties {

    String myGreetingText();
}
