package com.github.zeros.core.parse;

import com.github.zeros.core.config.Value;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-01-11
 */
public interface Parser<T> {

    T parse(Value value) throws Exception;
}
