package com.playground.jpaselfreference.comment.dto;

import com.playground.jpaselfreference.comment.entity.Comment;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@ToString(of = {"commentId", "writer", "content", "createdAt", "delete"})
public class CommentListItem {

    private final Long commentId;
    private final String writer;
    private final String content;
    private final LocalDateTime createdAt;
    private final boolean delete;
    private final List<ReplyListItem> replies;

    public CommentListItem(Comment comment) {
        this.commentId = comment.getId();
        this.writer = comment.getWriter();
        this.content = comment.getContent();
        this.createdAt = comment.getCreatedAt();
        this.delete = comment.isDelete();
        this.replies = new ArrayList<>();
    }

    @Getter
    public static class ReplyListItem {

        private final Long commentId;
        private final Long referenceId;
        private final String writer;
        private final String content;
        private final LocalDateTime createdAt;
        private final boolean delete;

        public ReplyListItem(Comment comment) {
            this.commentId = comment.getId();
            this.referenceId = comment.getReference().getId();
            this.writer = comment.getWriter();
            this.content = comment.getContent();
            this.createdAt = comment.getCreatedAt();
            this.delete = comment.isDelete();
        }

    }

}
