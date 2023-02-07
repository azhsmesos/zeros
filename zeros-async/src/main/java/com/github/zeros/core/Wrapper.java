package com.github.zeros.core;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-02-07
 */
public class Wrapper<T, F> {

    private String id;


    public void work(ExecutorService executorService, long remainTime, Map<String, Wrapper<?, ?>> wrapperMap) {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static class Builder<K, V> {

        private final String id = UUID.randomUUID().toString();


        public Wrapper<K, V> build() {
            Wrapper<K, V> wrapper = new Wrapper<>();
            return wrapper;
        }
    }
}
