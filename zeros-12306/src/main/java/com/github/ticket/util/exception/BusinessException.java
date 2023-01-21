package com.github.ticket.util.exception;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-01-21
 */
public class BusinessException extends RuntimeException {

    public BusinessException() {
        super();
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    protected BusinessException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
