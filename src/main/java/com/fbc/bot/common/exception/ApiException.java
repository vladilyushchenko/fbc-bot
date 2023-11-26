package com.fbc.bot.common.exception;

import com.fbc.bot.common.constant.ExceptionType;
import com.fbc.bot.common.constant.InternalErrorCode;
import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {

    private InternalErrorCode errorCode;
    private Object payload;

    public ApiException(ExceptionType exceptionType) {
        super(exceptionType.getMessage());
        this.errorCode = exceptionType.getErrorCode();
    }

    public ApiException(String message, InternalErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public ApiException(ExceptionType exceptionType, Object payload) {
        this(exceptionType);
        this.payload = payload;
    }
}