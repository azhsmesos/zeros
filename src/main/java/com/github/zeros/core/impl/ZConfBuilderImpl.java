package com.github.zeros.core.impl;

import com.github.zeros.core.ConfigMapper;
import com.github.zeros.core.ZConf;
import com.github.zeros.core.ZConfBuilder;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-01-11
 */
public class ZConfBuilderImpl<T> implements ZConfBuilder<T> {


    @Override
    public <T2> ZConfBuilder<T2> mapper(ConfigMapper<T, T2> mapper) {
        return null;
    }

    @Override
    public ZConf<T> build() {
        return null;
    }
}
