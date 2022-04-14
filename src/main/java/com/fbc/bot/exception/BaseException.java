package com.fbc.bot.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class BaseException extends RuntimeException {

    private final String msg;
}