package com.fbc.bot.exception;

import lombok.Getter;

@Getter
public abstract class BaseException extends RuntimeException {

    private final String msg;

    protected BaseException(String msg) {
        this.msg = msg;
    }
}