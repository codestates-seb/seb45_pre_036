package com.seb45_pre_036.stackoverflow.comment.service;

import com.seb45_pre_036.stackoverflow.comment.entity.Comment;
import com.seb45_pre_036.stackoverflow.comment.repository.CommentRepository;
import com.seb45_pre_036.stackoverflow.exception.BusinessLogicException;
import com.seb45_pre_036.stackoverflow.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;

    // 댓글 생성
    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    // 댓글 수정
    public Comment updateComment(Comment comment) {
        Optional<Comment> optional = commentRepository.findById(comment.getCommentId());
        Comment findComment = optional.orElseThrow(() -> new BusinessLogicException(ExceptionCode.COMMENT_NOT_FOUND));

        Optional.ofNullable(comment.getContent())
                .ifPresent(content -> findComment.setContent(content));

        return commentRepository.save(findComment);
    }

    // 댓글 조회
    public Comment findComment(long commentId) {
        Optional<Comment> optional = commentRepository.findById(commentId);
        Comment findComment = optional.orElseThrow(() -> new BusinessLogicException(ExceptionCode.COMMENT_NOT_FOUND));

        return findComment;
    }

    // 댓글 리스트 조회
    public Page<Comment> findComments(int page, int size) {
        return commentRepository.findAll(PageRequest.of(page, size, Sort.by("commentId").descending()));
    }

    // 댓글 삭제
    public void deleteComment(long commentId) {
        Optional<Comment> optional = commentRepository.findById(commentId);
        Comment findComment = optional.orElseThrow(() -> new BusinessLogicException(ExceptionCode.COMMENT_NOT_FOUND));

        commentRepository.delete(findComment);
    }


}
