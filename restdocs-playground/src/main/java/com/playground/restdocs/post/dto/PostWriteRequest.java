package com.playground.restdocs.post.dto;

import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostWriteRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String writer;

    @NotBlank
    private String content;

}
