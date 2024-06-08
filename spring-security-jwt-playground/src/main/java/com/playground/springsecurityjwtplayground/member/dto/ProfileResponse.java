package com.playground.springsecurityjwtplayground.member.dto;

import com.playground.springsecurityjwtplayground.member.entity.Member;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProfileResponse {

    private String nickname;
    private String username;

    public static ProfileResponse of(Member member) {
        return ProfileResponse.builder()
                .nickname(member.getNickname())
                .username(member.getUsername())
                .build();
    }

}
