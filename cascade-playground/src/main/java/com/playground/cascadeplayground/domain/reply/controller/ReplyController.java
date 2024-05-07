package com.playground.cascadeplayground.domain.reply.controller;

import com.playground.cascadeplayground.domain.comment.dto.CommentWriteRequest;
import com.playground.cascadeplayground.domain.reply.service.ReplyService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping("/{commentId}/reply")
    public ResponseEntity<Void> replyWrite(@PathVariable("commentId") Long commentId,
                                           @RequestBody CommentWriteRequest commentWriteRequest) {
        replyService.replyWrite(commentId, commentWriteRequest);
        return ResponseEntity.ok().build();
    }

}
