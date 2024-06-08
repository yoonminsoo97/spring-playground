package com.playground.springsecurityjwtplayground.token.service;

import com.playground.springsecurityjwtplayground.member.entity.Member;
import com.playground.springsecurityjwtplayground.security.dto.LoginMember;
import com.playground.springsecurityjwtplayground.security.support.JwtManager;
import com.playground.springsecurityjwtplayground.token.entity.Token;
import com.playground.springsecurityjwtplayground.token.repository.TokenRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final TokenRepository tokenRepository;
    private final JwtManager jwtManager;

    @Transactional
    public void tokenSave(String refreshToken, Member member) {
        tokenRepository.findByMemberId(member.getId())
                .ifPresentOrElse(
                        token -> token.update(refreshToken),
                        () -> tokenRepository.save(Token.builder()
                                .refreshToken(refreshToken)
                                .member(member)
                                .build()
                        )
                );
    }

    @Transactional
    public void tokenDelete(Long memberId) {
        Token token = tokenRepository.findByMemberId(memberId)
                .orElseThrow();
        tokenRepository.delete(token);
    }

    @Transactional
    public String reissue(String refreshToken) {
        Long memberId = Long.valueOf(jwtManager.getPayload(refreshToken).getSubject());
        Token token = tokenRepository.findByMemberId(memberId)
                .orElseThrow();
        if (!token.getRefreshToken().equals(refreshToken)) {
            throw new RuntimeException("invalid TokenException");
        }
        LoginMember loginMember = new LoginMember(token.getMember());
        return jwtManager.createAccessToken(loginMember);
    }

}
