package com.seb45_pre_036.stackoverflow.answer.repository;

import com.seb45_pre_036.stackoverflow.answer.entity.Answer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

    @Query("SELECT a FROM ANSWER a WHERE a.questionId = :questionId")
    default Page<Answer> findAllByQuestionId(Pageable pageable, long questionId) {
        return findAll(pageable);
    }
}
