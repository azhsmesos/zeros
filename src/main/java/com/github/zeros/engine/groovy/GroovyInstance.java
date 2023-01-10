package com.github.zeros.engine.groovy;

import static groovy.lang.GroovyShell.DEFAULT_CODE_BASE;

import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.FutureTask;

import org.apache.commons.codec.digest.DigestUtils;
import org.codehaus.groovy.control.CompilerConfiguration;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyCodeSource;
import groovy.lang.GroovyShell;

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
        if (script != null && script.trim().length() != 0) {
            if (cacheKey != null && cacheKey.trim().length() != 0) {
                if (!cached) {
                    return this.innerParse(script);
                } else {
                    FutureTask<Class<?>> beforeTask = null;
                    if (this.classLRUClass != null) {
                        boolean runTask = false;
                        synchronized (this.classLRUClass) {
                            beforeTask = this.classLRUClass.get(cacheKey);
                            if (beforeTask == null) {
                                beforeTask = this.newParseTask(script);
                                runTask = true;
                                this.classLRUClass.put(cacheKey, beforeTask);
                            }
                        }
                        if (runTask) {
                            beforeTask.run();
                        }
                    } else {
                        FutureTask<Class<?>> task = this.classCache.get(cacheKey);
                        if (task != null) {
                            return this.getParseClass(script, task);
                        }
                        task = this.newParseTask(script);
                        beforeTask = this.classCache.putIfAbsent(cacheKey, task);
                        if (beforeTask == null) {
                            beforeTask = task;
                            task.run();
                        }
                    }
                    return this.getParseClass(cacheKey, beforeTask);
                }
            } else {
                throw new RuntimeException("cacheKey is null");
            }
        } else {
            throw new RuntimeException("script is null");
        }
    }

    private FutureTask<Class<?>> newParseTask(String script) {
        return new FutureTask<>(() -> GroovyInstance.this.innerParse(script));
    }

    private Class<?> getParseClass(String cacheKey, FutureTask<Class<?>> task) {
        try {
            return task.get();
        } catch (Throwable throwable) {
            this.invalidCacheByKey(cacheKey);
            throw new RuntimeException(throwable.getCause());
        }
    }

    private void invalidCacheByKey(String cacheKey) {
        if (this.classLRUClass != null) {
            synchronized (this.classLRUClass) {
                this.classLRUClass.remove(cacheKey);
            }
        } else {
            this.classCache.remove(cacheKey);
        }
    }

    private Class<?> innerParse(String script) {
        GroovyCodeSource code = new GroovyCodeSource(script, getScriptName(), DEFAULT_CODE_BASE);
        CompilerConfiguration config = new CompilerConfiguration();
        config.setScriptBaseClass(ExtendScript.class.getName());
        GroovyClassLoader loader = new GroovyClassLoader(GroovyShell.class.getClassLoader(), config);
        return loader.parseClass(code);
    }

    private synchronized String getScriptName() {
        return "Script" + UUID.randomUUID() + "groovy";
    }
    protected String getCacheKey(String script) {
        if (script == null) {
            return "default";
        }
       return DigestUtils.md5Hex(script);
//        return DigestUtils.md5DigestAsHex(script.getBytes(StandardCharsets.UTF_8));
    }
}
