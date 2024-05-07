package com.playground.cascadeplayground.domain.comment.controller;

import com.playground.cascadeplayground.domain.comment.dto.CommentWriteRequest;
import com.playground.cascadeplayground.domain.comment.service.CommentService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Void> commentWrite(@RequestBody CommentWriteRequest commentWriteRequest) {
        commentService.commentWrtie(commentWriteRequest);
        return ResponseEntity.ok().build();
    }

}
