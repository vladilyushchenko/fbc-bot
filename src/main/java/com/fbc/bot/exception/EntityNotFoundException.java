package com.fbc.bot.exception;

import static java.lang.String.format;

public class EntityNotFoundException extends BaseException {
    public EntityNotFoundException(String entityName, Long id) {
        super(format("No entity %s with id %d", entityName, id));
    }

    public EntityNotFoundException(String entityName, String fieldName, String fieldValue) {
        super(format("No entity %s with field %s equals to %s", entityName, fieldName, fieldValue));
    }
}