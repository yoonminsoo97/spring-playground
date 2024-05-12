package com.playground.querydslplayground.domain.post.dto;

import com.playground.querydslplayground.domain.comment.dto.CommentListItem;
import com.playground.querydslplayground.domain.post.entity.Post;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class PostDetailResponse {

    private final Long postId;
    private final String title;
    private final String writer;
    private final String content;
    private final LocalDateTime createdAt;
    private final List<CommentListItem> comments;

    public PostDetailResponse(Post post, List<CommentListItem> comments) {
        this.postId = post.getId();
        this.title = post.getTitle();
        this.writer = post.getWriter();
        this.content = post.getContent();
        this.createdAt = post.getCreatedAt();
        this.comments = comments;
    }

}
