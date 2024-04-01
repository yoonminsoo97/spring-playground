package com.playground.restdocs.post.service;

import com.playground.restdocs.post.dto.PostDetailResponse;
import com.playground.restdocs.post.dto.PostWriteRequest;
import com.playground.restdocs.post.entity.Post;
import com.playground.restdocs.post.exception.NotFoundPostException;
import com.playground.restdocs.post.repository.PostRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public void postWrite(PostWriteRequest postWriteRequest) {
        Post post = Post.builder()
                .title(postWriteRequest.getTitle())
                .writer(postWriteRequest.getWriter())
                .content(postWriteRequest.getContent())
                .build();
        postRepository.save(post);
    }

    @Transactional(readOnly = true)
    public PostDetailResponse postDetail(Long postNumber) {
        Post post = postRepository.findById(postNumber)
                .orElseThrow(NotFoundPostException::new);
        return new PostDetailResponse(post);
    }

}
