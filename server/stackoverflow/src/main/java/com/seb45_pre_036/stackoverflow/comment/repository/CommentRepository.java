package com.seb45_pre_036.stackoverflow.comment.repository;

import com.seb45_pre_036.stackoverflow.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByAnswer(long answerId);
}
