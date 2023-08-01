package com.github.olestxcode.flyconf.adapter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScreamingSnakeCaseAdapterTest {

    @Test
    public void testAdapt() {
        assertEquals("MY_TEST_STRING", StandardAdapter.SCREAMING_SNAKE_CASE.adapt("myTestString"));
    }
}
