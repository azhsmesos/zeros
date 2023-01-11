package com.github.zeros.core.impl;

import com.github.zeros.core.ConfigMapper;
import com.github.zeros.core.ZConf;
import com.github.zeros.core.ZConfBuilder;
import com.github.zeros.core.parse.Parser;

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

    /**
     * 该方法用来根据key读取配置中心数据，其中T需要解压缩，节省传输数据大小
     */
    public static <T> ZConfBuilder<T> configBuilder(String key, T defaultValue, Parser<T> value) {
        return null;
    }
}
