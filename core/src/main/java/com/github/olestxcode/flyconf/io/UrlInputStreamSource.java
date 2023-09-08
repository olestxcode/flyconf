package com.github.olestxcode.flyconf.io;

import lombok.AllArgsConstructor;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

@AllArgsConstructor
public final class UrlInputStreamSource implements InputStreamSource {

    private final URL url;

    @Override
    public InputStream getInputStream() throws IOException {
        return url.openStream();
    }
}