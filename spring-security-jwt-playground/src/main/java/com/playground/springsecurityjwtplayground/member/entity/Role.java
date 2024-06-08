package com.playground.springsecurityjwtplayground.member.entity;

public enum Role {

    MEMBER("ROLE_MEMBER");

    private final String authority;

    Role(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return authority;
    }

}
