package com.github.zeros.model;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;
import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

import com.github.zeros.util.ErrorCode;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-01-12
 */
public class CommonView {

    private String result;

    private String message;

    public CommonView(ErrorCode code) {
        setResAndMsg(code);
    }

    public CommonView(String code, String msg) {
        if ("SUCCESS".equals(code)) {
            this.result = "1";
        } else {
            this.result = code;
        }

        if (isBlank(msg)) {
            this.message = code;
        } else {
            this.message = msg;
        }
    }

    private void setResAndMsg(ErrorCode code) {
        if (code == ErrorCode.SUCCESS) {
            this.result = String.valueOf(ErrorCode.SUCCESS.getValue());
        } else {
            this.result = code.name();
        }
        this.message = code.name();
        if (code == ErrorCode.NO_URI_PERMISSION) {
            this.message = "您当前没有该服务权限，是否申请？";
        }
    }

    public String getResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return reflectionToString(this, SHORT_PREFIX_STYLE);
    }
}
