package com.playground.jpaselfreference.comment.repository;

import com.playground.jpaselfreference.comment.entity.Comment;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.playground.jpaselfreference.comment.entity.QComment.comment;

@Repository
public class CommentCustomRepositoryImpl implements CommentCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public CommentCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public Page<Comment> findComments(Pageable pageable) {
        List<Comment> content = jpaQueryFactory
                .select(comment)
                .from(comment)
                .where(comment.reference.id.isNull())
                .orderBy(comment.id.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        JPAQuery<Long> count = jpaQueryFactory
                .select(comment.count())
                .from(comment)
                .where(comment.reference.id.isNull());
        return PageableExecutionUtils.getPage(content, pageable, count::fetchOne);
    }

}
