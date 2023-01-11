package com.github.zeros.core.meta;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-01-11
 */
public class VersionData<T> {

    private final long version; // 配置版本号. 对应kconf历史记录中的版本号
    private final T data;

    public VersionData(long version, T data) {
        this.version = version;
        this.data = data;
    }

    public long getVersion() {
        return version;
    }

    public T getData() {
        return data;
    }
}
