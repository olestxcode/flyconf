package com.github.olestxcode.flyconf.io;

import lombok.AllArgsConstructor;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@AllArgsConstructor
public final class PathInputStreamSource implements InputStreamSource {

    private final Path path;

    @Override
    public InputStream getInputStream() throws IOException {
        return Files.newInputStream(path);
    }
}