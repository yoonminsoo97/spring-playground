package com.playground.querydslplayground.domain.comment.dto;

import com.playground.querydslplayground.domain.comment.entity.Comment;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentListItem {

    private final Long commentId;
    private final String writer;
    private final String content;
    private final LocalDateTime createdAt;
    private final boolean delete;

    public CommentListItem(Comment comment) {
        this.commentId = comment.getId();
        this.writer = comment.getWriter();
        this.content = comment.getContent();
        this.createdAt = comment.getCreatedAt();
        this.delete = comment.getDelete().getStatus();
    }

}
