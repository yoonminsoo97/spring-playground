package com.playground.jpaselfreference.comment.repository;

import com.playground.jpaselfreference.comment.entity.Comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long>, CommentCustomRepository {

    @Query("SELECT c FROM Comment AS c WHERE c.id = :commentId")
    Optional<Comment> findCommentByCommentId(@Param("commentId") Long commentId);

    @Query("SELECT c FROM Comment AS c WHERE c.reference.id IS NOT NULL ORDER BY c.reference.id ASC")
    List<Comment> findReplies();

}
