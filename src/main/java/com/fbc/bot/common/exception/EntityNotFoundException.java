package com.fbc.bot.common.exception;

import com.fbc.bot.common.constant.InternalErrorCode;

public class EntityNotFoundException extends ApiException {

    public EntityNotFoundException(Class<?> resource) {
        super(String.format("%s was not found", resource), InternalErrorCode.NOT_FOUND);
    }

    public EntityNotFoundException(Class<?> resource, Long id) {
        super(String.format("%s with id = %s was not found", resource, id), InternalErrorCode.NOT_FOUND);
    }
}