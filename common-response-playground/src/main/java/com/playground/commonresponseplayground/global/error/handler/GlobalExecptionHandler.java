package com.playground.commonresponseplayground.global.error.handler;

import com.playground.commonresponseplayground.global.common.dto.ApiResponse;
import com.playground.commonresponseplayground.global.error.dto.ErrorResponse;
import com.playground.commonresponseplayground.global.error.exception.BaseException;
import com.playground.commonresponseplayground.global.error.exception.ErrorType;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExecptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ApiResponse<ErrorResponse>> handle(BaseException baseException) {
        final ErrorType errorType = baseException.getErrorType();
        final ErrorResponse errorResponse = ErrorResponse.of(errorType);
        return ResponseEntity.status(errorType.getStatus()).body(ApiResponse.fail(errorResponse));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<ErrorResponse>> handle(BindingResult bindingResult) {
        final ErrorResponse errorResponse = ErrorResponse.of(ErrorType.INVALID_INPUT, bindingResult);
        return ResponseEntity.badRequest().body(ApiResponse.fail(errorResponse));
    }

}
