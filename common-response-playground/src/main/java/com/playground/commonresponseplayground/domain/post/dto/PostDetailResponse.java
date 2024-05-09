package com.playground.commonresponseplayground.domain.post.dto;

import com.playground.commonresponseplayground.domain.post.entity.Post;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostDetailResponse {

    private final Long postId;
    private final String title;
    private final String writer;
    private final String content;
    private final LocalDateTime createdAt;

    public PostDetailResponse(Post post) {
        this.postId = post.getId();
        this.title = post.getTitle();
        this.writer = post.getWriter();
        this.content = post.getContent();
        this.createdAt = post.getCreatedAt();
    }

}
