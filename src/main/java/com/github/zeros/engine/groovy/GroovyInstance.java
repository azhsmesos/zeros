package com.github.zeros.engine.groovy;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.FutureTask;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-01-10
 */
public class GroovyInstance {

    private final ConcurrentHashMap<String, FutureTask<Class<?>>> classCache;

    private LRUCache<String, FutureTask<Class<?>>> classLRUClass;

    public GroovyInstance() {
        this.classCache = new ConcurrentHashMap<>();
    }

    public Class<?> parse(String script, boolean cached) {
        return this.parse(getCacheKey(script), script, cached);
    }

    public Class<?> parse(String cacheKey, String script, boolean cached) {

        return null;
    }

    protected String getCacheKey(String script) {
        return "Digest";
    }
}
