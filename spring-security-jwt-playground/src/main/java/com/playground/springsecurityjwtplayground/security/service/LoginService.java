package com.playground.springsecurityjwtplayground.security.service;

import com.playground.springsecurityjwtplayground.member.entity.Member;
import com.playground.springsecurityjwtplayground.member.repository.MemberRepository;
import com.playground.springsecurityjwtplayground.security.dto.LoginMember;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("UserDetailsService");
        Member member = memberRepository.findByUsername(username)
                .orElseThrow();
        return new LoginMember(member);
    }

}
