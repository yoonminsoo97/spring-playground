package com.playground.commonresponseplayground.domain.post.service;

import com.playground.commonresponseplayground.domain.post.dto.PostDetailResponse;
import com.playground.commonresponseplayground.domain.post.dto.PostWriteRequest;
import com.playground.commonresponseplayground.domain.post.entity.Post;
import com.playground.commonresponseplayground.domain.post.exception.NotFoundPostException;
import com.playground.commonresponseplayground.domain.post.repsitory.PostRepository;

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
    public PostDetailResponse postDetail(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(NotFoundPostException::new);
        return new PostDetailResponse(post);
    }

}
