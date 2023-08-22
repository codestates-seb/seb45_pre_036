package com.seb45_pre_036.stackoverflow.question.tag;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagId;

    @Column(unique = true, updatable = false)
    private String tagName;

    public Tag(String tagName) {
        this.tagName = tagName;
    }

}
