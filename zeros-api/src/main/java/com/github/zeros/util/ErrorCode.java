package com.github.zeros.util;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-01-12
 * 后面改名 state不合适
 */
public enum ErrorCode {

    UNKNOWN_ERROR(0),
    SUCCESS(1),
    SYSTEM_ERROR_RETRY_LATER(2),
    INVALID_PARAM(3),
    FAIL(4),
    NO_URI_PERMISSION(5),
    ;

    private final int value;

    ErrorCode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
