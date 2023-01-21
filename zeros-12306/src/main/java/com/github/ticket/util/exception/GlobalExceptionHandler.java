package com.github.ticket.util.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.github.ticket.model.CommonDataView;
import com.github.ticket.model.CommonView;
import com.github.ticket.util.ErrorCode;

import lombok.extern.slf4j.Slf4j;


/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-01-21
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = RuntimeException.class)
    public CommonDataView<String> exceptionHandler(RuntimeException e) {
        log.error("exception: ", e);
        if (e instanceof ParamException) {
            return CommonDataView.ofMsg(ErrorCode.INVALID_PARAM, e.getMessage());
        }
        if (e instanceof BusinessException) {
            return CommonDataView.ofMsg(ErrorCode.SYSTEM_ERROR, e.getMessage());
        }
        return CommonDataView.ofMsg(ErrorCode.UNKNOWN_ERROR, e.getMessage());
    }

    @ExceptionHandler(value = Error.class)
    public CommonDataView<String> errorHanlder(Error error) {
        log.error("error: ", error);
        return CommonDataView.ofMsg(ErrorCode.SYSTEM_ERROR_RETRY_LATER, "系统异常，请联系管理员或者重启系统");
    }
}
