package com.github.zeros.model;

import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;
import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

import org.apache.commons.lang3.StringUtils;

import com.github.zeros.util.ErrorCode;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-01-12
 */
public class CommonDataView<T> extends CommonView{

    private T data;

    public CommonDataView(ErrorCode code) {
        super(code);
    }

    public CommonDataView(String code, String msg) {
        super(code, msg);
    }

    public CommonDataView(ErrorCode code, T data) {
        super(code);
        this.data = data;
    }

    public CommonDataView(String code, String msg, T data) {
        super(code, msg);
        this.data = data;
    }

    public static <T> CommonDataView<T> of(ErrorCode code) {
        return new CommonDataView<>(code);
    }

    public static <T> CommonDataView<T> ofMsg(ErrorCode code, String msg) {
        if (StringUtils.isBlank(msg)) {
            return new CommonDataView<>(code);
        }
        return new CommonDataView<>(code.name(), msg);
    }

    public static <T> CommonDataView<T> ofMsg(String code, String msg) {
        return new CommonDataView<>(code, msg);
    }

    public static <T> CommonDataView<T> ofMsgData(ErrorCode code, String msg, T data) {
        if (StringUtils.isBlank(msg)) {
            return new CommonDataView<>(code, data);
        }
        return new CommonDataView<>(code.name(), msg, data);
    }

    public static <T> CommonDataView<T> ofMsgData(String code, String msg, T data) {
        return new CommonDataView<>(code, msg, data);
    }

    public static <T> CommonDataView<T> of(ErrorCode code, T data) {
        return new CommonDataView<>(code, data);
    }

    public static <T> CommonDataView<T> success(T data) {
        return new CommonDataView<>(ErrorCode.SUCCESS, data);
    }

    public static <T> CommonDataView<T> from(CommonView errorInfo) {
        return CommonDataView.ofMsg(errorInfo.getResult(), errorInfo.getMessage());
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return reflectionToString(this, SHORT_PREFIX_STYLE);
    }
}
