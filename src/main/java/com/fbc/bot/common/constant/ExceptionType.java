package com.fbc.bot.common.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExceptionType {

    ACCESS_DENIED("Access denied", InternalErrorCode.FORBIDDEN),
    EMPTY_INLINE_QUERY_NOT_ALLOWED("Empty inline query not allowed", InternalErrorCode.EMPTY_INLINE_QUERY_NOT_ALLOWED),
    TELEGRAM_MESSAGE_HANDLER_NOT_FOUND("Telegram message handler not found", InternalErrorCode.TELEGRAM_MESSAGE_HANDLER_NOT_FOUND),
    ANSWERING_ON_BOT_MESSAGE_ARE_NOT_HANDLED("Message representing answer on bot`s message cannot be handled", InternalErrorCode.ANSWERING_ON_BOT_MESSAGE_ARE_NOT_HANDLED);

    private final String message;
    private final InternalErrorCode errorCode;
}