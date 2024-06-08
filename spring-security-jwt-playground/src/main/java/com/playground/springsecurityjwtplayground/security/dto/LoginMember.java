package com.playground.springsecurityjwtplayground.security.dto;

import com.playground.springsecurityjwtplayground.member.entity.Member;

import org.springframework.security.core.userdetails.User;

import static org.springframework.security.core.authority.AuthorityUtils.createAuthorityList;

public class LoginMember extends User {

    private final Member member;

    public LoginMember(Member member) {
        super(member.getUsername(), member.getPassword(), createAuthorityList(member.getAuthority()));
        this.member = member;
    }

    public Member getMember() {
        return member;
    }

    public Long getMemberId() {
        return member.getId();
    }

    public String getNickname() {
        return member.getNickname();
    }

    public String getAuthority() {
        return member.getAuthority();
    }

}
