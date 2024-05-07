package com.playground.cascadeplayground.domain.comment.repository;

import com.playground.cascadeplayground.domain.comment.entity.Comment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
