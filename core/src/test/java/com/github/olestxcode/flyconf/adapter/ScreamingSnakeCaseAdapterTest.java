package com.github.olestxcode.flyconf.adapter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScreamingSnakeCaseAdapterTest {

    @Test
    public void testAdapt() {
        ScreamingSnakeCaseAdapter adapter = new ScreamingSnakeCaseAdapter();
        String result = adapter.adapt("myTestString");
        assertEquals("MY_TEST_STRING", result);
    }
}
