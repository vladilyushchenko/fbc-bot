package com.fbc.bot.exception.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EntityName {
    USER("User");

    private final String value;
}