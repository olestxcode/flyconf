package com.github.olestxcode.flyconf.adapter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KebabCaseAdapterTest {

    @Test
    public void testAdapt() {
        KebabCaseAdapter adapter = new KebabCaseAdapter();
        String result = adapter.adapt("myTestString");
        assertEquals("my-test-string", result);
    }
}
