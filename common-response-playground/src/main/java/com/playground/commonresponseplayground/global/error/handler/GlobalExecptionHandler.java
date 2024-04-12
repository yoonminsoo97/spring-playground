package com.playground.commonresponseplayground.global.error.handler;

import com.playground.commonresponseplayground.global.common.dto.ApiResponse;
import com.playground.commonresponseplayground.global.common.dto.ErrorResponse;
import com.playground.commonresponseplayground.global.error.exception.BaseException;
import com.playground.commonresponseplayground.global.error.exception.ErrorType;

import jakarta.servlet.http.HttpServletRequest;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GlobalExecptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ApiResponse<ErrorResponse>> handle(HttpServletRequest request, BaseException e) {
        String path = createPath(request);
        ErrorType errorType = e.getErrorType();;
        ErrorResponse errorResponse = ErrorResponse.of(errorType, path);
        return ResponseEntity.status(errorType.getStatus()).body(ApiResponse.failure(errorType.getStatus(),errorResponse));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<ErrorResponse>> handle(HttpServletRequest request, BindingResult e) {
        String path = createPath(request);
        ErrorResponse errorResponse = ErrorResponse.of(ErrorType.INVALID_INPUT, path, e);
        return ResponseEntity.badRequest().body(ApiResponse.failure(400, errorResponse));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiResponse<ErrorResponse>> handle(HttpServletRequest request) {
        String path = createPath(request);
        ErrorResponse errorResponse = ErrorResponse.of(ErrorType.METHOD_ARG_TYPE_MISMATCH, path);
        return ResponseEntity.badRequest().body(ApiResponse.failure(400, errorResponse));
    }

    private String createPath(HttpServletRequest request) {
        String queryString = request.getQueryString();
        if (StringUtils.hasText(queryString)) {
            return request.getRequestURI() + "?" + queryString;
        }
        return request.getRequestURI();
    }

}
