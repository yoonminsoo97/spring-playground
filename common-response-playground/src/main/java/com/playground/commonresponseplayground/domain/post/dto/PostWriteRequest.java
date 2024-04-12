package com.playground.commonresponseplayground.domain.post.dto;

import jakarta.validation.constraints.NotBlank;

import lombok.Getter;

@Getter
public class PostWriteRequest {

    @NotBlank(message = "제목을 입력해주세요.")
    private String title;

    @NotBlank(message = "작성자를 입력해주세요.")
    private String writer;

    @NotBlank(message = "본문을 입력해주세요.")
    private String content;

}
