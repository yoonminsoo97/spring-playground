package com.playground.springsecurityjwtplayground.security.exception;

import org.springframework.security.core.AuthenticationException;

public class ExpiredTokenException extends AuthenticationException {

    public ExpiredTokenException(String message) {
        super(message);
    }

}
