package com.fbc.bot.common.dto;

import com.fbc.bot.common.constant.InternalErrorCode;
import lombok.Getter;

@Getter
public class ErrorDto implements BaseDto {

    private InternalErrorCode code;

    private String message;

    private Object payload;

    public ErrorDto(InternalErrorCode code, String message, Object payload) {
        this.code = code;
        this.message = message;
        this.payload = payload;
    }

    public ErrorDto(InternalErrorCode code) {
        this.code = code;
    }
}