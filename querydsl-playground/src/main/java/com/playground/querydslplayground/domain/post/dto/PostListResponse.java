package com.playground.querydslplayground.domain.post.dto;

import lombok.Getter;

import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class PostListResponse {

    private final List<PostListItem> posts;
    private final int page;
    private final int totalPages;
    private final long totalPosts;
    private final boolean prev;
    private final boolean next;
    private final boolean first;
    private final boolean last;

    public PostListResponse(Page<PostListItem> postPage) {
        this.posts = postPage.getContent();
        this.page = postPage.getNumber() + 1;
        this.totalPages = postPage.getTotalPages();
        this.totalPosts = postPage.getTotalElements();
        this.prev = postPage.hasPrevious();
        this.next = postPage.hasNext();
        this.first = postPage.isFirst();
        this.last = postPage.isLast();
    }

}
