package com.github.zeros.core.callback;

import com.github.zeros.core.worker.WrokResult;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-02-07
 */
public class DefaultCallback<T, F> implements ICallback<T, F> {

    @Override
    public void listening() {

    }


    @Override
    public void complete(boolean success, T param, WrokResult<F> res) {

    }
}
