package com.playground.cascadeplayground.domain.comment.service;

import com.playground.cascadeplayground.domain.comment.dto.CommentWriteRequest;
import com.playground.cascadeplayground.domain.comment.entity.Comment;
import com.playground.cascadeplayground.domain.comment.repository.CommentRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    @Transactional
    public void commentWrtie(CommentWriteRequest commentWriteRequest) {
        Comment comment = Comment.builder()
                .writer(commentWriteRequest.getWriter())
                .content(commentWriteRequest.getContent())
                .build();
        commentRepository.save(comment);
    }

}
