package com.playground.springsecurityjwtplayground.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.playground.springsecurityjwtplayground.security.dto.LoginMember;
import com.playground.springsecurityjwtplayground.security.dto.LoginResponse;
import com.playground.springsecurityjwtplayground.security.support.JwtManager;
import com.playground.springsecurityjwtplayground.token.service.TokenService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

@RequiredArgsConstructor
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final ObjectMapper objectMapper;
    private final TokenService tokenService;
    private final JwtManager jwtManager;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        LoginMember loginMember = (LoginMember) authentication.getPrincipal();
        String accessToken = jwtManager.createAccessToken(loginMember);
        String refreshToken = jwtManager.createRefreshToken(loginMember);
        tokenService.tokenSave(refreshToken, loginMember.getMember());
        LoginResponse loginResponse = LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.OK.value());
        objectMapper.writeValue(response.getOutputStream(), loginResponse);
    }

}
