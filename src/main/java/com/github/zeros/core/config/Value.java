package com.github.zeros.core.config;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-01-11
 */
public class Value {

    private ConfigType type;

    private byte[] data;

    public ConfigType getType() {
        return type;
    }

    public byte[] getData() {
        return data;
    }
}
