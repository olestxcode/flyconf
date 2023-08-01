package com.github.olestxcode.flyconf.adapter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SnakeCaseAdapterTest {

    @Test
    public void testAdapt() {
        assertEquals("my_test_string", StandardAdapter.SNAKE_CASE.adapt("myTestString"));
    }
}
