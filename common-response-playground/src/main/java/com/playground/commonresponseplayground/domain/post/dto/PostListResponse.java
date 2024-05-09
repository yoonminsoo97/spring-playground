package com.playground.commonresponseplayground.domain.post.dto;

import com.playground.commonresponseplayground.domain.post.entity.Post;

import lombok.Getter;

import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PostListResponse {

    private final List<Item> posts;
    private final int page;
    private final int totalPages;
    private final long totalElements;
    private final boolean prev;
    private final boolean next;
    private final boolean first;
    private final boolean last;

    public PostListResponse(Page<Post> postPage) {
        this.posts = postPage.getContent().stream()
                .map(Item::new)
                .collect(Collectors.toList());
        this.page = postPage.getNumber() + 1;
        this.totalPages = postPage.getTotalPages();
        this.totalElements = postPage.getTotalElements();
        this.prev = postPage.hasPrevious();
        this.next = postPage.hasNext();
        this.first = postPage.isFirst();
        this.last = postPage.isLast();
    }

    @Getter
    private static class Item {

        private final Long postId;
        private final String title;
        private final String writer;
        private final LocalDateTime createdAt;

        public Item(Post post) {
            this.postId = post.getId();
            this.title = post.getTitle();
            this.writer = post.getWriter();
            this.createdAt = post.getCreatedAt();
        }

    }

}
