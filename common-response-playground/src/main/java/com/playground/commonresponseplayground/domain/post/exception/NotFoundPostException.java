package com.playground.commonresponseplayground.domain.post.exception;

import com.playground.commonresponseplayground.global.error.exception.BaseException;
import com.playground.commonresponseplayground.global.error.exception.ErrorType;

public class NotFoundPostException extends BaseException {

    public NotFoundPostException() {
        super(ErrorType.NOT_FOUND_POST);
    }

}
