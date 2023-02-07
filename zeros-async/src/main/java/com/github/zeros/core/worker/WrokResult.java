package com.github.zeros.core.worker;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-02-07
 */
public class WrokResult<F> {

    private F result;

    private State state;

    private Exception exception;

    public WrokResult(F res, State state) {
        this(res, state, null);
    }

    public WrokResult(F res, State state, Exception e) {
        this.result = res;
        this.state = state;
        this.exception = e;
    }

    public static <F> WrokResult<F> defaultResult() {
        return new WrokResult<>(null, State.DEFAULT);
    }
}
