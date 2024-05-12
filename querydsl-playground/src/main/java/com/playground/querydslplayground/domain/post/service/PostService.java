package com.playground.querydslplayground.domain.post.service;

import com.playground.querydslplayground.domain.post.dto.PostDetailResponse;
import com.playground.querydslplayground.domain.post.dto.PostListItem;
import com.playground.querydslplayground.domain.post.dto.PostListResponse;
import com.playground.querydslplayground.domain.post.repository.PostRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Transactional(readOnly = true)
    public PostListResponse postList(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<PostListItem> postPage = postRepository.findPosts(pageable);
        return new PostListResponse(postPage);
    }

    @Transactional(readOnly = true)
    public PostDetailResponse postDetail(Long postId) {
        return postRepository.findPostWithComments(postId);
    }

}
