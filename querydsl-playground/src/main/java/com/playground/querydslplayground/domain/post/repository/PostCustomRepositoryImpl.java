package com.playground.querydslplayground.domain.post.repository;

import com.playground.querydslplayground.domain.comment.dto.CommentListItem;
import com.playground.querydslplayground.domain.comment.entity.Delete;
import com.playground.querydslplayground.domain.post.dto.PostDetailResponse;
import com.playground.querydslplayground.domain.post.dto.PostListItem;

import com.playground.querydslplayground.domain.post.entity.QPost;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.playground.querydslplayground.domain.comment.entity.QComment.comment;
import static com.playground.querydslplayground.domain.post.entity.QPost.post;

import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.list;

@Repository
@RequiredArgsConstructor
public class PostCustomRepositoryImpl implements PostCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<PostListItem> findPosts(Pageable pageable) {
        List<PostListItem> content = jpaQueryFactory
                .select(Projections.constructor(PostListItem.class,
                        post.id,
                        post.title,
                        post.writer,
                        comment.id.count().intValue(),
                        post.createdAt
                ))
                .from(post)
                .leftJoin(post.comments, comment).on(comment.delete.eq(Delete.N))
                .groupBy(post.id)
                .orderBy(post.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        JPAQuery<Long> count = jpaQueryFactory
                .select(post.count())
                .from(post);
        return PageableExecutionUtils.getPage(content, pageable, count::fetchOne);
    }

    @Override
    public PostDetailResponse findPostWithComments(Long postId) {
        return jpaQueryFactory
                .from(comment)
                .where(post.id.eq(postId))
                .innerJoin(comment.post, post)
                .transform(
                        groupBy(comment.post.id)
                                .list(Projections.constructor(PostDetailResponse.class,
                                        post,
                                        list(Projections.constructor(CommentListItem.class,
                                                comment
                                        ))
                                ))
                ).get(0);
    }

}
