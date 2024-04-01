package com.playground.restdocs.post.repository;

import com.playground.restdocs.post.entity.Post;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
