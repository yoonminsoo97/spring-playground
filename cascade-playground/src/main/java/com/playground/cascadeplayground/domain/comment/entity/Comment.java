package com.playground.cascadeplayground.domain.comment.entity;

import com.playground.cascadeplayground.domain.reply.entity.Reply;
import com.playground.cascadeplayground.global.entity.BaseEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @Column(nullable = false)
    private String writer;

    @Column(nullable = false)
    private String content;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "comment", cascade = CascadeType.PERSIST)
    private List<Reply> replies = new ArrayList<>();

    @Builder
    public Comment(String writer, String content) {
        this.writer = writer;
        this.content = content;
    }

    public void addReply(Reply reply) {
        this.replies.add(reply);
    }

}
