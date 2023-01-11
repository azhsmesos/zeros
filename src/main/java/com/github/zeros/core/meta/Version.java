package com.github.zeros.core.meta;

import com.github.zeros.core.ZConf;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-01-11
 */
public interface Version<T> extends ZConf<T> {

    VersionData<T> getVersion();
}
