package com.playground.restdocs.post.exception;

import com.playground.restdocs.global.error.BaseException;

import org.springframework.http.HttpStatus;

public class NotFoundPostException extends BaseException {

    public NotFoundPostException() {
        super(HttpStatus.NOT_FOUND, "게시글을 찾을 수 없습니다.");
    }

}
