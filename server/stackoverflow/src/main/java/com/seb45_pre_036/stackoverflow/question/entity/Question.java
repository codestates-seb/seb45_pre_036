package com.seb45_pre_036.stackoverflow.question.entity;

import com.seb45_pre_036.stackoverflow.answer.entity.Answer;
import com.seb45_pre_036.stackoverflow.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long questionId;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    @ManyToOne
    private Member member = new Member();

    //@OneToMany(mappedBy = "question")
    //private List<Answer> answers = new ArrayList<>();




}
