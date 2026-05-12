package com.jh.oldcat.exception;

import com.jh.oldcat.utils.Result;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 * 使用 @RestControllerAdvice 拦截所有 Controller 抛出的异常，
 * 统一返回 Result 格式，避免将异常堆栈直接暴露给前端。
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 自定义业务异常
     */
    @ExceptionHandler(BusinessException.class)
    public Result handleBusinessException(BusinessException e) {
        return Result.error()
                .code(e.getCode())
                .message(e.getMessage());
    }

    /**
     * 请求参数缺失（例如 @RequestParam 必填参数未传）
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Result handleMissingParam(MissingServletRequestParameterException e) {
        return Result.error()
                .code(400)
                .message("缺少必填参数: " + e.getParameterName());
    }

    /**
     * 算术异常（如除零）
     */
    @ExceptionHandler(ArithmeticException.class)
    public Result handleArithmeticException(ArithmeticException e) {
        return Result.error()
                .code(9001)
                .message("算数异常: " + e.getMessage());
    }

    /**
     * 兜底异常 —— 所有未在上述方法中处理的异常最终会走到这里
     */
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        e.printStackTrace();
        return Result.error()
                .code(500)
                .message("服务器内部错误: " + e.getMessage());
    }
}
