package com.github.olestxcode.flyconf.adapter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SnakeCaseAdapterTest {

    @Test
    public void testAdapt() {
        SnakeCaseAdapter adapter = new SnakeCaseAdapter();
        String result = adapter.adapt("myTestString");
        assertEquals("my_test_string", result);
    }
}
