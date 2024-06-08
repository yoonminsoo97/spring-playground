package com.playground.springsecurityjwtplayground.member.repository;

import com.playground.springsecurityjwtplayground.member.entity.Member;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByUsername(String username);

}
