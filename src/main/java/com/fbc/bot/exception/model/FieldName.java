package com.fbc.bot.exception.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FieldName {
    USER_TELEGRAM_ID("telegramId");

    private final String value;
}