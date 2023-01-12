package com.github.zeros.engine.groovy;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-01-10
 */
public class GroovyEvaluator {

    public static Class<?> parse(String script, boolean cached) {
        return getInstance().parse(script, cached);
    }

    public static GroovyInstance getInstance() {
        return StaticHolder.INSTANCE;
    }

    static class StaticHolder {
        private static GroovyInstance INSTANCE = new GroovyInstance();
        private StaticHolder() {}
    }
}
