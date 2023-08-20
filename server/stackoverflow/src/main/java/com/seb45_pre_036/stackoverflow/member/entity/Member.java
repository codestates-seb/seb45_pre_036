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

    @Column(nullable = true)
    private String password;
    // -> OAuth2 로그인 수행 시 -> password 정보 따로 db 저장하지 않기 때문에
    // -> nullable -> default or true 설정
    // -> 서버를 통해 회원가입 시 -> postDto -> password 입력 값 -> NotBlank 설정
    // -> 서버 측 가입한 회원 -> password -> table 에 null 값이 존재하지 않음.


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

    // Server 회원가입 유저 / OAuth2 로그인 유저 -> ENUM 타입으로 구분 처리할 수 있음.

}
