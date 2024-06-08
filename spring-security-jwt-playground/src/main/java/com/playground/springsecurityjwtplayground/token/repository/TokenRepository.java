package com.playground.springsecurityjwtplayground.token.repository;

import com.playground.springsecurityjwtplayground.token.entity.Token;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {

    Optional<Token> findByMemberId(Long memberId);

}
