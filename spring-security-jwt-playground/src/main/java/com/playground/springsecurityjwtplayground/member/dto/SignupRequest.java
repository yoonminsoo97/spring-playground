package com.playground.springsecurityjwtplayground.member.dto;

import jakarta.validation.constraints.NotBlank;

import lombok.Getter;

@Getter
public class SignupRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String nickname;

    @NotBlank
    private String password;

}
