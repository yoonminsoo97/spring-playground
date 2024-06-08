package com.playground.springsecurityjwtplayground.token.repository;

import com.playground.springsecurityjwtplayground.token.entity.Token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {

    Optional<Token> findTokenByMemberId(Long memberId);

    @Query("SELECT t FROM Token AS t JOIN FETCH t.member WHERE t.member.id = :memberId")
    Optional<Token> findTokenByMemberIdJoinFetchMember(@Param("memberId") Long memberId);

}
