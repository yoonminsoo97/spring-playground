package com.playground.jpaselfreference.comment.entity;

import com.playground.jpaselfreference.global.entity.BaseEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NoArgsConstructor
@Getter
@ToString(of = {"id", "writer", "content", "delete"})
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @Column(nullable = false)
    private String writer;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private boolean delete;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reference_id")
    private Comment reference;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "reference", cascade = CascadeType.PERSIST)
    private List<Comment> replies = new ArrayList<>();

    @Builder
    public Comment(String writer, String content, Comment comment) {
        this.writer = writer;
        this.content = content;
        this.reference = comment;
        this.delete = false;
    }

    public boolean isReply() {
        return !Objects.isNull(reference);
    }

    public boolean isDelete() {
        return this.delete;
    }

    public void modify(String content) {
        this.content = content;
    }

    public void delete() {
        this.delete = true;
    }

    public void addReply(Comment reply) {
        replies.add(reply);
        reply.getReplies().add(this);
    }

}
