package com.playground.jpaselfreference.comment.repository;

import com.playground.jpaselfreference.comment.entity.Comment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentCustomRepository {

    Page<Comment> findComments(Pageable pageable);

}
