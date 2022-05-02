package com.fbc.bot.exception;

import static java.lang.String.format;

public class EntityNotFoundException extends BaseException {
    public EntityNotFoundException(Class<?> aClass, Long id) {
        super(format("No entity %s with id %d", aClass.getName(), id));
    }

    public EntityNotFoundException(Class<?> aClass, String fieldName, Object fieldValue) {
        super(format("No entity %s with field %s equals to %s", aClass, fieldName, fieldValue));
    }
}