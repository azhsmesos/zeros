package com.github.zeros.core;

import java.util.UUID;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-02-07
 */
public class Wrapper<T, F> {

    public static class Builder<K, V> {

        private final String id = UUID.randomUUID().toString();


        public Wrapper<K, V> build() {
            Wrapper<K, V> wrapper = new Wrapper<>();
            return wrapper;
        }
    }
}
