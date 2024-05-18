package com.playground.jpaselfreference.comment.dto;

import lombok.Getter;

@Getter
public class CommentWriteRequest {

    private String writer;
    private String content;

}
