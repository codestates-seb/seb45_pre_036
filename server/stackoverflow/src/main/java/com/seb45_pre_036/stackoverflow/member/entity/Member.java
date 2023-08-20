package com.seb45_pre_036.stackoverflow.member.entity;

import com.seb45_pre_036.stackoverflow.answer.entity.Answer;
import com.seb45_pre_036.stackoverflow.audit.Auditable;
import com.seb45_pre_036.stackoverflow.question.entity.Question;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Member extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(nullable = false, unique = true, updatable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(length = 50, nullable = false)
    private String nickName;

    @Column(length = 100, nullable = true)
    private String title;

    @Column(length = 500, nullable = true)
    private String aboutMe;

    @ElementCollection(fetch = FetchType.EAGER)
    List<String> roles = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    List<Question> questions = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    List<Answer> answers = new ArrayList<>();


}
