package com.seb45_pre_036.stackoverflow.answer.repository;

import com.seb45_pre_036.stackoverflow.answer.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
