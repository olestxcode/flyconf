package com.github.olestxcode.flyconf.adapter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KebabCaseAdapterTest {

    @Test
    public void testAdapt() {
        assertEquals("my-test-string", StandardAdapter.KEBAB_CASE.adapt("myTestString"));
    }
}
