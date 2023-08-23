package com.seb45_pre_036.stackoverflow.answer.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.seb45_pre_036.stackoverflow.audit.Auditable;
import com.seb45_pre_036.stackoverflow.comment.entity.Comment;
import com.seb45_pre_036.stackoverflow.member.entity.Member;
import com.seb45_pre_036.stackoverflow.question.entity.Question;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter @Setter
@Entity
public class Answer extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerId;

    @Column(length = 1000, nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "QUESTION_ID")
    private Question question;

    @OneToMany(mappedBy = "answer", cascade = CascadeType.REMOVE)
    private List<Comment> comments;


    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    private Adopt adopt = Adopt.NONE;

    public enum Adopt {
        NONE,
        ADOPT;

                @JsonCreator
        public static Adopt fromAdopt(String val) {
            for (Adopt adopt : Adopt.values()) {
                if (adopt.name().equals(val)) {
                    return adopt;
                }
            }
            return null;
        }
    }



}
