package com.playground.cascadeplayground.domain.reply.service;

import com.playground.cascadeplayground.domain.comment.dto.CommentWriteRequest;
import com.playground.cascadeplayground.domain.comment.entity.Comment;
import com.playground.cascadeplayground.domain.comment.repository.CommentRepository;
import com.playground.cascadeplayground.domain.reply.entity.Reply;
import com.playground.cascadeplayground.domain.reply.repository.ReplyRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final CommentRepository commentRepository;
    private final ReplyRepository replyRepository;

    @Transactional
    public void replyWrite(Long commentId, CommentWriteRequest commentWriteRequest) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow();
        Reply reply = Reply.builder()
                .writer(commentWriteRequest.getWriter())
                .content(commentWriteRequest.getContent())
                .comment(comment)
                .build();
        comment.addReply(reply);
    }

}
