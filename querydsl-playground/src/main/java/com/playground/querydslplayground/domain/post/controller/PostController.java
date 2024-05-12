package com.playground.querydslplayground.domain.post.controller;

import com.playground.querydslplayground.domain.post.dto.PostDetailResponse;
import com.playground.querydslplayground.domain.post.dto.PostListResponse;
import com.playground.querydslplayground.domain.post.service.PostService;
import com.playground.querydslplayground.global.dto.ApiResponse;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public ResponseEntity<ApiResponse<PostListResponse>> postList(@RequestParam("page") int page) {
        page = page <= 0 ? 0 : page - 1;
        PostListResponse postListResponse = postService.postList(page);
        return ResponseEntity.ok().body(ApiResponse.success(postListResponse));
    }

    @GetMapping("/{postId}")
    public ResponseEntity<ApiResponse<PostDetailResponse>> postDetail(@PathVariable("postId") Long postId) {
        PostDetailResponse postDetailResponse = postService.postDetail(postId);
        return ResponseEntity.ok().body(ApiResponse.success(postDetailResponse));
    }

}
