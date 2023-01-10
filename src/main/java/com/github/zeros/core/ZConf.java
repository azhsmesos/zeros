package com.github.zeros.core;

import java.util.function.Supplier;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-01-10
 */
public interface ZConf<T> extends Supplier<T> {

    T get();
}
