package com.github.zeros.core;

import java.util.List;
import java.util.Map;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-01-11
 */
public class ZConfs {

    private static final IZConfFactory FACTORY;

    static {
        try {
            Class<?> clz = Class.forName("com.github.zeros.core.impl.ZConfFactory");
            FACTORY = (IZConfFactory) clz.getMethod("getInstance").invoke(null);
        } catch (Exception e) {
            throw new RuntimeException("ZConf init failed", e);
        }
    }

    private ZConfs() {}

    public static ZConfBuilder<Integer> ofInteger(String key, int defaultValue) {
        return FACTORY.ofInteger(key, defaultValue);
    }

    public static ZConfBuilder<Long> ofLong(String key, long defaultValue) {
        return FACTORY.ofLong(key, defaultValue);
    }

    public static ZConfBuilder<Double> ofDouble(String key, double defaultValue) {
        return FACTORY.ofDouble(key, defaultValue);
    }

    public static ZConfBuilder<Boolean> ofBoolean(String key, boolean defaultValue) {
        return FACTORY.ofBoolean(key, defaultValue);
    }

    public static ZConfBuilder<String> ofString(String key, String defaultValue) {
        return FACTORY.ofString(key, defaultValue);
    }

    public static <T> ZConfBuilder<T> ofJson(String key,T defaultValue, Class<T> clz) {
        return FACTORY.ofJson(key, defaultValue, clz);
    }

    public static <V> ZConfBuilder<List<V>> ofJsonList(String key, List<V> defaultValue, Class<V> clz) {
        return FACTORY.ofJsonList(key, defaultValue, clz);
    }

    public static <T extends Map<K, V>, K, V> ZConfBuilder<T> ofJsonMap(String key, T defaultValue, Class<K> keyType,
            Class<V> valueType) {
        return FACTORY.ofJsonMap(key, defaultValue, keyType, valueType);
    }
}
