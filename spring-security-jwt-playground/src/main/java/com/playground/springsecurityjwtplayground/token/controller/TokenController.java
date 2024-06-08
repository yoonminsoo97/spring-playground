package com.playground.springsecurityjwtplayground.token.controller;

import com.playground.springsecurityjwtplayground.token.service.TokenService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/token")
@RequiredArgsConstructor
public class TokenController {

    private final TokenService tokenService;

    @PostMapping("/reissue")
    public ResponseEntity<String> reissue(@RequestHeader(name = "Authorization") String authorization) {
        String refreshToken = extractRefreshToken(authorization);
        String accessToken = tokenService.reissue(refreshToken);
        return ResponseEntity.ok().body(accessToken);
    }

    private String extractRefreshToken(String authorization) {
        if (StringUtils.hasText(authorization) && authorization.startsWith("Bearer")) {
            return authorization.substring("Bearer".length()).trim();
        }
        return null;
    }

}
