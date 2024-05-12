package com.playground.querydslplayground.domain.post.repository;

import com.playground.querydslplayground.domain.post.entity.Post;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long>, PostCustomRepository {
}
