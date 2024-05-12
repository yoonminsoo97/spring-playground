package com.playground.querydslplayground.domain.post.repository;

import com.playground.querydslplayground.domain.post.dto.PostDetailResponse;
import com.playground.querydslplayground.domain.post.dto.PostListItem;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostCustomRepository {

    Page<PostListItem> findPosts(Pageable pageable);
    PostDetailResponse findPostWithComments(Long postId);

}
