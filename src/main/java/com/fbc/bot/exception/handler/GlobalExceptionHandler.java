package com.fbc.bot.exception.handler;

import com.fbc.bot.exception.EntityNotFoundException;
import com.fbc.bot.exception.ExceptionResponse;
import com.fbc.bot.exception.SystemException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static java.time.OffsetDateTime.now;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleBaseException(EntityNotFoundException exc) {
        return new ResponseEntity<>(
                new ExceptionResponse(exc.getMsg(), NOT_FOUND.value(), now()),
                NOT_FOUND);
    }

    @ExceptionHandler(SystemException.class)
    public ResponseEntity<ExceptionResponse> handleSystemException(SystemException exc) {
        return new ResponseEntity<>(
                new ExceptionResponse(exc.getMsg(), INTERNAL_SERVER_ERROR.value(), now()),
                INTERNAL_SERVER_ERROR);
    }
}