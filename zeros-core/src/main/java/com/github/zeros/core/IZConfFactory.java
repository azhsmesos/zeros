package com.github.zeros.core;

import java.util.List;
import java.util.Map;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-01-11
 */
public interface IZConfFactory {

    ZConfBuilder<Integer> ofInteger(String key, int defaultValue);

    ZConfBuilder<Long> ofLong(String key, long defaultValue);

    ZConfBuilder<Double> ofDouble(String key, double defaultValue);

    ZConfBuilder<Boolean> ofBoolean(String key, boolean defaultValue);

    ZConfBuilder<String> ofString(String key, String defaultValue);

    <T> ZConfBuilder<T> ofJson(String key, T defaultValue, Class<T> clz);

    <V> ZConfBuilder<List<V>> ofJsonList(String key, List<V> defaultValue, Class<V> clz);

    <T extends Map<K, V>, K, V> ZConfBuilder<T> ofJsonMap(String key, T defaultValue, Class<K> keyType,
            Class<V> valueType);
}
