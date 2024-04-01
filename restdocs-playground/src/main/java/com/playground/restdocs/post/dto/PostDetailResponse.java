package com.playground.restdocs.post.dto;

import com.playground.restdocs.post.entity.Post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostDetailResponse {

    private Long postNumber;
    private String title;
    private String writer;
    private String content;

    public PostDetailResponse(Post post) {
        this.postNumber = post.getId();
        this.title = post.getTitle();
        this.writer = post.getWriter();
        this.content = post.getContent();
    }

}
