package com.github.zeros.core.callback;

import com.github.zeros.core.worker.WrokResult;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-02-07
 */
@FunctionalInterface
public interface ICallback<T, F> {

    default void listening() {

    }

    /**
     * 完成后给value注入值
     */
    void complete(boolean success, T param, WrokResult<F> res);
}
