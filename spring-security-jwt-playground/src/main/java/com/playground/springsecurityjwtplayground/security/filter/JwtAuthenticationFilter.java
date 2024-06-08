package com.playground.springsecurityjwtplayground.security.filter;

import com.playground.springsecurityjwtplayground.security.support.JwtManager;

import io.jsonwebtoken.Claims;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static org.springframework.security.core.authority.AuthorityUtils.createAuthorityList;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final AuthenticationEntryPoint authenticationEntryPoint;
    private final JwtManager jwtManager;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = extractToken(request);
            jwtManager.validateParseJws(token);
            Claims payload = jwtManager.getPayload(token);
            Authentication authentication = createAuthentication(payload);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);
        } catch (AuthenticationException e) {
            authenticationEntryPoint.commence(request, response, e);
        }

    }

    private String extractToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (StringUtils.hasText(header) && header.startsWith("Bearer")) {
            return header.substring("Bearer".length()).trim();
        }
        return null;
    }

    private Authentication createAuthentication(Claims payload) {
        Long memberId = Long.valueOf(payload.getSubject());
        String authority = payload.get("authority", String.class);
        return UsernamePasswordAuthenticationToken
                .authenticated(memberId, null, createAuthorityList(authority));
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        if (requestURI.equals("/api/auth/login") || requestURI.equals("/api/members/signup") || requestURI.equals("/api/token/reissue")) {
            return true;
        }
        return false;
    }

}
