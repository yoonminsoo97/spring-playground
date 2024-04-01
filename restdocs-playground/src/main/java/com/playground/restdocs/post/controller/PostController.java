package com.playground.restdocs.post.controller;

import com.playground.restdocs.post.dto.PostDetailResponse;
import com.playground.restdocs.post.dto.PostWriteRequest;
import com.playground.restdocs.post.service.PostService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<Void> postWrite(@RequestBody @Valid PostWriteRequest postWriteRequest) {
        postService.postWrite(postWriteRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{postNumber}")
    public ResponseEntity<?> postDetail(@PathVariable("postNumber") Long postNumber) {
        PostDetailResponse postDetailResponse = postService.postDetail(postNumber);
        return ResponseEntity.ok().body(postDetailResponse);
    }

}
