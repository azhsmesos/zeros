package com.github.zeros.core.impl;

import static com.github.zeros.core.impl.ZConfBuilderImpl.configBuilder;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import com.github.zeros.core.IZConfFactory;
import com.github.zeros.core.ZConfBuilder;
import com.github.zeros.core.config.ConfigParsers;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-01-11
 */
public class ZConfFactory implements IZConfFactory {


    private static final ZConfFactory INSTANCE = new ZConfFactory();

    public ZConfFactory() {}

    public static ZConfFactory getInstance() {
        return INSTANCE;
    }

    @Override
    public ZConfBuilder<Integer> ofInteger(String key, int defaultValue) {
        return ZConfBuilderImpl.configBuilder(key, defaultValue, ConfigParsers.INT_PARSER);
    }

    @Override
    public ZConfBuilder<Long> ofLong(String key, long defaultValue) {
        return null;
    }

    @Override
    public ZConfBuilder<Double> ofDouble(String key, double defaultValue) {
        return null;
    }

    @Override
    public ZConfBuilder<Boolean> ofBoolean(String key, boolean defaultValue) {
        return null;
    }

    @Override
    public ZConfBuilder<String> ofString(String key, String defaultValue) {
        return null;
    }

    @Override
    public <T> ZConfBuilder<T> ofJson(String key, T defaultValue, Class<T> clz) {
        return null;
    }

    @Override
    public <V> ZConfBuilder<List<V>> ofJsonList(String key, List<V> defaultValue, Class<V> clz) {
        return null;
    }

    @Override
    public <T extends Map<K, V>, K, V> ZConfBuilder<T> ofJsonMap(String key, T defaultValue, Class<K> keyType,
            Class<V> valueType) {
        return null;
    }
}
