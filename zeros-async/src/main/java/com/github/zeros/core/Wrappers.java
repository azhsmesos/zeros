package com.github.zeros.core;

import com.github.zeros.core.Wrapper.Builder;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-02-07
 */
public class Wrappers {

    private Wrappers(){}

    public static <T, F> Builder<T, F> newBuilder(Class<T> t, Class<F> f) {
        return new Builder<>();
    }

    public static <T, F> Builder<T, F> newBuilder() {
        return new Builder<>();
    }
}
