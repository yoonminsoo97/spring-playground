package com.playground.springsecurityjwtplayground.member.service;

import com.playground.springsecurityjwtplayground.member.dto.ProfileResponse;
import com.playground.springsecurityjwtplayground.member.dto.SignupRequest;
import com.playground.springsecurityjwtplayground.member.entity.Member;
import com.playground.springsecurityjwtplayground.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signup(SignupRequest signupRequest) {
        Member member = Member.builder()
                .username(signupRequest.getUsername())
                .nickname(signupRequest.getNickname())
                .password(passwordEncoder.encode(signupRequest.getPassword()))
                .build();
        memberRepository.save(member);
    }

    @Transactional(readOnly = true)
    public ProfileResponse profile(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow();
        return ProfileResponse.of(member);
    }

}
