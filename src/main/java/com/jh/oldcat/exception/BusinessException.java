package com.jh.oldcat.exception;

import com.jh.oldcat.utils.ResultCode;

/**
 * 自定义业务异常
 * 在需要主动抛出业务错误的地方使用，例如：
 * throw new BusinessException("用户名已存在");
 * throw new BusinessException(ResultCode.USER_ACCOUNT_NOT_EXIST);
 */
public class BusinessException extends RuntimeException {

    private Integer code;
    private String message;

    public BusinessException(String message) {
        this.code = 999;
        this.message = message;
    }

    public BusinessException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public BusinessException(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
