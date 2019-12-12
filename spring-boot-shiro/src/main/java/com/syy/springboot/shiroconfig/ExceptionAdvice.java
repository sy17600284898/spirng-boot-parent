package com.syy.springboot.shiroconfig;

import commercial.base.common.models.base.BaseResponse;
import commercial.base.common.util.LogUtils;
import commercial.base.common.util.ResultStatusCode;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;

/**
 * ExceptionAdvice
 *
 * @author: shiyan
 * @version: 2019-12-11 15:44
 * @Copyright: 2019 www.lenovo.com Inc. All rights reserved.
 */
@ControllerAdvice
@ResponseBody
public class ExceptionAdvice {
    /**
     * 400 - Bad Request
     *
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({HttpMessageNotReadableException.class, MissingServletRequestParameterException.class, BindException.class,
            ServletRequestBindingException.class, MethodArgumentNotValidException.class, ConstraintViolationException.class})
    public BaseResponse<Object> handleHttpMessageNotReadableException(Exception e) {
        LogUtils.error("Parameter parsing failed", e);
        if (e instanceof BindException) {
            return new BaseResponse(ResultStatusCode.BAD_REQUEST.getCode(),
                    ((BindException) e).getAllErrors().get(0).getDefaultMessage());
        }
        return new BaseResponse(ResultStatusCode.BAD_REQUEST.getCode(), e.getMessage());
    }

    /**
     * 405 - Method Not Allowed
     *
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public BaseResponse<Object> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        LogUtils.error("Does not support the current request method", e);
        return new BaseResponse(false, ResultStatusCode.METHOD_NOT_ALLOWED, null);
    }

    /**
     * shiro权限异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(UnauthorizedException.class)
    public BaseResponse<Object> unauthorizedException(UnauthorizedException e) {
        LogUtils.error(e.getMessage(), e);
        return new BaseResponse(false, ResultStatusCode.UNAUTHO_ERROR, null);
    }

    /**
     * 500
     *
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public BaseResponse<Object> handleException(Exception e) {
        e.printStackTrace();
        LogUtils.error("Service is running abnormally", e);
        return new BaseResponse(false, ResultStatusCode.SYSTEM_ERR, null);
    }
}
