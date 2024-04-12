package com.playground.commonresponseplayground.global.error.exception;

import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {

    private ErrorType errorType;

    public BaseException(ErrorType errorType) {
        this.errorType = errorType;
    }

}
