package com.playground.springsecurityjwtplayground.member.controller;

import com.playground.springsecurityjwtplayground.member.dto.ProfileResponse;
import com.playground.springsecurityjwtplayground.member.dto.SignupRequest;
import com.playground.springsecurityjwtplayground.member.service.MemberService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@RequestBody @Valid SignupRequest signupRequest) {
        memberService.signup(signupRequest);
        return ResponseEntity.ok().build();
    }

    @Secured("ROLE_MEMBER")
    @GetMapping("/me")
    public ResponseEntity<ProfileResponse> profile(@AuthenticationPrincipal Long memberId) {
        ProfileResponse profileResponse = memberService.profile(memberId);
        return ResponseEntity.ok().body(profileResponse);
    }

}
