package com.playground.cascadeplayground.domain.reply.repository;

import com.playground.cascadeplayground.domain.reply.entity.Reply;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
}
