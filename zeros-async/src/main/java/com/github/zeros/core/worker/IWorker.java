package com.github.zeros.core.worker;

import java.util.Map;

import com.github.zeros.core.Wrapper;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-02-07
 */
@FunctionalInterface
public interface IWorker<T, F> {

    F apply(T object, Map<String, Wrapper<T, F>> wrapperMap);

    default F defautValue() {
        return null;
    }
}
