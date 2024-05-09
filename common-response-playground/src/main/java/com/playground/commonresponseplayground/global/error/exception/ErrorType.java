package com.playground.commonresponseplayground.global.error.exception;

import lombok.Getter;

import org.springframework.http.HttpStatus;

@Getter
public enum ErrorType {

    INVALID_INPUT(HttpStatus.BAD_REQUEST.value(), "E400001", "입력값이 잘못되었습니다."),
    NOT_FOUND_POST(HttpStatus.NOT_FOUND.value(), "E404001", "게시글을 찾을 수 없습니다.");

    private final int status;
    private final String errorCode;
    private final String message;

    ErrorType(int status, String errorCode, String message) {
        this.status = status;
        this.errorCode = errorCode;
        this.message = message;
    }

}
