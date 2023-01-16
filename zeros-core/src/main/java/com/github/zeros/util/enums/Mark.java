package com.github.zeros.util.enums;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-01-16
 */
public enum Mark {

    UPDATE(1),
    DELETE(0);

    private final int value;

    Mark(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
