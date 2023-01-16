package com.github.zeros.storage;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-01-16
 */
public class IndexFile implements Closeable {

    private final static String SUFFIX = ".idx";

    private String rootPath;

    private String filename;

    public IndexFile(String rootPath, String filename) {
        this.rootPath = rootPath;
        this.filename = filename;
    }

    @Override
    public void close() throws IOException {

    }
}
