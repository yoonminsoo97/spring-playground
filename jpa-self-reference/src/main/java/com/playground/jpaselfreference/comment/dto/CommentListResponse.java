package com.playground.jpaselfreference.comment.dto;

import com.playground.jpaselfreference.comment.entity.Comment;

import lombok.Getter;

import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class CommentListResponse {

    private final List<CommentListItem> comments;
    private final int page;
    private final int totalPages;
    private final long totalComments;
    private final boolean prev;
    private final boolean next;
    private final boolean first;
    private final boolean last;

    public CommentListResponse(Page<Comment> commentPage,
                               List<CommentListItem> comments,
                               long totalComments) {
        this.comments = comments;
        this.page = commentPage.getNumber() + 1;
        this.totalPages = commentPage.getTotalPages();
        this.totalComments = totalComments;
        this.prev = commentPage.hasPrevious();
        this.next = commentPage.hasNext();
        this.first = commentPage.isFirst();
        this.last = commentPage.isLast();
    }

}
