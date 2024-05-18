package com.playground.jpaselfreference.comment.controller;

import com.playground.jpaselfreference.comment.dto.CommentListResponse;
import com.playground.jpaselfreference.comment.dto.CommentModifyRequest;
import com.playground.jpaselfreference.comment.dto.CommentWriteRequest;
import com.playground.jpaselfreference.comment.service.CommentService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<Void> commentWrite(@RequestBody final CommentWriteRequest commentWriteRequest) {
        commentService.commentWrite(commentWriteRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{commentId}/replies")
    public ResponseEntity<Void> replyWrite(@PathVariable("commentId") final Long commentId,
                                           @RequestBody final CommentWriteRequest commentWriteRequest) {
        commentService.replyWrite(commentId, commentWriteRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<CommentListResponse> commentList(@RequestParam("page") int page) {
        page = page <= 0 ? 0 : page - 1;
        CommentListResponse commentListResponse = commentService.commentList(page);
        return ResponseEntity.ok().body(commentListResponse);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<Void> commentModify(@PathVariable("commentId") final Long commentId,
                                              @RequestBody final CommentModifyRequest commentModifyRequest) {
        commentService.commentModify(commentId, commentModifyRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> commentDelete(@PathVariable("commentId") final Long commentId) {
        commentService.commentDelete(commentId);
        return ResponseEntity.ok().build();
    }


}
