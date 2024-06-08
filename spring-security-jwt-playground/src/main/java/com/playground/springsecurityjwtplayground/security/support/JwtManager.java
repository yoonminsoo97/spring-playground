package com.playground.springsecurityjwtplayground.security.support;

import com.playground.springsecurityjwtplayground.security.dto.LoginMember;
import com.playground.springsecurityjwtplayground.security.exception.ExpiredTokenException;
import com.playground.springsecurityjwtplayground.security.exception.InvalidTokenException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

import java.nio.charset.StandardCharsets;

import java.util.Date;

@Component
public class JwtManager {

    private final SecretKey secretKey;
    private final long accessTokenExpire;
    private final long refreshTokenExpire;

    public JwtManager(@Value("${jwt.secret-key}") String secretKey,
                      @Value("${jwt.access-token.expire}") long accessTokenExpire,
                      @Value("${jwt.refresh-token.expire}") long refreshTokenExpire) {
        this.secretKey = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        this.accessTokenExpire = accessTokenExpire;
        this.refreshTokenExpire = refreshTokenExpire;
    }

    public String createAccessToken(LoginMember loginMember) {
        Date iat = new Date();
        Date exp = new Date(iat.getTime() + accessTokenExpire);
        return Jwts.builder()
                .subject(String.valueOf(loginMember.getMemberId()))
                .claim("nickname", loginMember.getNickname())
                .claim("authority", loginMember.getAuthority())
                .issuedAt(iat)
                .expiration(exp)
                .signWith(secretKey, Jwts.SIG.HS256)
                .compact();
    }

    public String createRefreshToken(LoginMember loginMember) {
        Date iat = new Date();
        Date exp = new Date(iat.getTime() + refreshTokenExpire);
        return Jwts.builder()
                .subject(String.valueOf(loginMember.getMemberId()))
                .issuedAt(iat)
                .expiration(exp)
                .signWith(secretKey, Jwts.SIG.HS256)
                .compact();
    }

    public Claims getPayload(String token) {
        return validateParseJws(token).getPayload();
    }

    public Jws<Claims> validateParseJws(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token);
        } catch (ExpiredJwtException e) {
            throw new ExpiredTokenException("토큰이 만료되었습니다.");
        } catch (JwtException e) {
            throw new InvalidTokenException("토큰이 유효하지 않습니다.");
        }
    }

}
