package com.fbc.bot.common.exception;

import com.fbc.bot.common.constant.InternalErrorCode;
import com.fbc.bot.common.dto.ErrorDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Log4j2
@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorDto> handleApiException(ApiException e) {
        return ResponseEntity
                .status(e.getErrorCode().getHttpStatus())
                .body(new ErrorDto(e.getErrorCode(), e.getMessage(), e.getPayload()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleUnknownException(Exception exception) {
        log.error(exception.getMessage(), exception);
        return ResponseEntity
                .status(InternalErrorCode.INTERNAL_ERROR.getHttpStatus())
                .body(new ErrorDto(InternalErrorCode.INTERNAL_ERROR));
    }
}