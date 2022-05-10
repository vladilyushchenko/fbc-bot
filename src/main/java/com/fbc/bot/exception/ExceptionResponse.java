package com.fbc.bot.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.OffsetDateTime;

@Getter
@AllArgsConstructor
public class ExceptionResponse {
    private String message;
    private Integer code;
    private OffsetDateTime timestamp;
}