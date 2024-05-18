package com.playground.jpaselfreference.comment.service;

import com.playground.jpaselfreference.comment.dto.CommentListItem;
import com.playground.jpaselfreference.comment.dto.CommentListResponse;
import com.playground.jpaselfreference.comment.dto.CommentModifyRequest;
import com.playground.jpaselfreference.comment.dto.CommentWriteRequest;
import com.playground.jpaselfreference.comment.entity.Comment;
import com.playground.jpaselfreference.comment.repository.CommentRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private static final int COMMENT_COUNT_PER_PAGE = 10;

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Transactional
    public void commentWrite(CommentWriteRequest commentWriteRequest) {
        Comment comment = Comment.builder()
                .writer(commentWriteRequest.getWriter())
                .content(commentWriteRequest.getContent())
                .build();
        commentRepository.save(comment);
    }

    @Transactional
    public void replyWrite(Long commentId, CommentWriteRequest commentWriteRequest) {
        Comment comment = commentRepository.findCommentByCommentId(commentId)
                .orElseThrow();
        if (comment.isReply()) {
            throw new RuntimeException("대댓글에 대댓글을 작성할 수 없습니다.");
        }
        Comment reply = Comment.builder()
                .writer(commentWriteRequest.getWriter())
                .content(commentWriteRequest.getContent())
                .comment(comment)
                .build();
        comment.addReply(reply);
    }

    @Transactional(readOnly = true)
    public CommentListResponse commentList(int page) {
        Pageable pageable = PageRequest.of(page, COMMENT_COUNT_PER_PAGE);
        Page<Comment> commentPage = commentRepository.findComments(pageable);
        List<Comment> replies = commentRepository.findReplies();
        return converToCommentListReponse(commentPage, replies);
    }

    private CommentListResponse converToCommentListReponse(Page<Comment> commentPage, List<Comment> replies) {
        long cntOfComment = commentPage.getTotalElements();
        long cntOfReply = replies.stream().filter(comment -> !comment.isDelete()).count();
        Map<Long, CommentListItem> map = commentPage.getContent().stream()
                .map(CommentListItem::new)
                .collect(Collectors.toMap(CommentListItem::getCommentId, Function.identity()));
        replies.forEach(reply -> {
            CommentListItem commentListItem = map.get(reply.getReference().getId());
            if (commentListItem != null) {
                commentListItem.getReplies().add(new CommentListItem.ReplyListItem(reply));
            }
        });
        List<CommentListItem> comments = new ArrayList<>(map.values());
        return new CommentListResponse(commentPage, comments, cntOfComment + cntOfReply);
    }


    @Transactional
    public void commentModify(Long commentId, CommentModifyRequest commentModifyRequest) {
        Comment comment = commentRepository.findCommentByCommentId(commentId)
                .orElseThrow();
        comment.modify(commentModifyRequest.getContent());
    }

    @Transactional
    public void commentDelete(Long commentId) {
        Comment comment = commentRepository.findCommentByCommentId(commentId)
                .orElseThrow();
        if (comment.isDelete()) {
            throw new RuntimeException("이미 삭제된 댓글입니다.");
        }
        comment.delete();
    }

}
