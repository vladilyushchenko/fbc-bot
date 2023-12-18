package com.fbc.bot.common.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum InternalErrorCode {

    INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR),
    NOT_FOUND(HttpStatus.NOT_FOUND),
    FORBIDDEN(HttpStatus.FORBIDDEN),
    EMPTY_INLINE_QUERY_NOT_ALLOWED(HttpStatus.BAD_REQUEST),
    TELEGRAM_MESSAGE_HANDLER_NOT_FOUND(HttpStatus.BAD_REQUEST),
    ANSWERING_ON_BOT_MESSAGE_ARE_NOT_HANDLED(HttpStatus.BAD_REQUEST);

    private final HttpStatus httpStatus;
}