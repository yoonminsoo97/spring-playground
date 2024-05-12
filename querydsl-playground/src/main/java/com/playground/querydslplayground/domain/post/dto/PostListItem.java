package com.playground.querydslplayground.domain.post.dto;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString(of = {"postId", "title", "writer", "commentCount", "createdAt"})
public class PostListItem {

    private final Long postId;
    private final String title;
    private final String writer;
    private final int commentCount;
    private final LocalDateTime createdAt;

    public PostListItem(Long postId, String title, String writer, int commentCount, LocalDateTime createdAt) {
        this.postId = postId;
        this.title = title;
        this.writer = writer;
        this.commentCount = commentCount;
        this.createdAt = createdAt;
    }

}
