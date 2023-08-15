package com.seb45_pre_036.stackoverflow.question.repository;

import com.seb45_pre_036.stackoverflow.question.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
