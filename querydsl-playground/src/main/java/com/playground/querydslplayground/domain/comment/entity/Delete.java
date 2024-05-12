package com.playground.querydslplayground.domain.comment.entity;

public enum Delete {

    Y(true),
    N(false);

    private final boolean status;

    Delete(boolean status) {
        this.status = status;
    }

    public boolean getStatus() {
        return status;
    }

}
