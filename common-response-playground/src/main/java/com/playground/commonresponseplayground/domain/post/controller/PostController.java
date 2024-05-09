package com.playground.commonresponseplayground.domain.post.controller;

import com.playground.commonresponseplayground.domain.post.dto.PostDetailResponse;
import com.playground.commonresponseplayground.domain.post.dto.PostListResponse;
import com.playground.commonresponseplayground.domain.post.dto.PostWriteRequest;
import com.playground.commonresponseplayground.domain.post.service.PostService;
import com.playground.commonresponseplayground.global.common.dto.ApiResponse;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> postWrite(@RequestBody @Valid final PostWriteRequest postWriteRequest) {
        postService.postWrite(postWriteRequest);
        return ResponseEntity.ok().body(ApiResponse.success());
    }

    @GetMapping("/{postId}")
    public ResponseEntity<ApiResponse<PostDetailResponse>> postDetail(@PathVariable("postId") final Long postId) {
        PostDetailResponse postDetailResponse = postService.postDetail(postId);
        return ResponseEntity.ok().body(ApiResponse.success(postDetailResponse));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PostListResponse>> postList(@RequestParam("page") int page) {
        page = page <= 0 ? 0 : page - 1;
        PostListResponse postListResponse = postService.postList(page);
        return ResponseEntity.ok().body(ApiResponse.success(postListResponse));
    }

}
