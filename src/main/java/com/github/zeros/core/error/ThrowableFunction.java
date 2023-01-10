package com.github.zeros.core.error;

import java.util.Objects;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-01-10
 */
@FunctionalInterface
public interface ThrowableFunction<T, R, X extends Throwable> {

    static <T, X extends Throwable> ThrowableFunction<T, T, X> identity() {
        return (t) -> t;
    }

    R apply(T t) throws X;

    default <V> ThrowableFunction<V, R, X> compose(ThrowableFunction<? super V, ? extends T, X> function) {
        Objects.requireNonNull(function);
        return (v) -> this.apply(function.apply(v));
    }

    default <V> ThrowableFunction<T, V, X> after(ThrowableFunction<? super R, ? extends V, X> function) {
        Objects.requireNonNull(function);
        return (t) -> function.apply(this.apply(t));
    }
}
