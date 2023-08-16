package com.seb45_pre_036.stackoverflow.member.dto;

import com.seb45_pre_036.stackoverflow.answer.dto.AnswerDto;
import com.seb45_pre_036.stackoverflow.answer.entity.Answer;
import com.seb45_pre_036.stackoverflow.question.dto.QuestionDto;
import com.seb45_pre_036.stackoverflow.question.entity.Question;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

public class MemberDto {
    @Getter
    public static class Post{
        @NotBlank
        private String email;
        @NotBlank
        private String password;
        @NotEmpty
        private String nickName;

    }

    @Getter
    @Setter
    public static class Patch{

        private long memberId;

        @NotNull
        private String nickName;

    }

    @Getter
    @Setter
    @Builder
    public static class Response{
        private long memberId;
        private String email;
        private String nickName;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
    }


    @Builder
    @Getter @Setter
    public static class AnswerResponse {
        private long answerId;
        private String content;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
        private long questionId;


    }

    @Getter
    @Setter
    @Builder
    public static class QuestionResponse{
        private long questionId;
        private String title;
        private String content;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;


    }

    @Getter
    @Setter
    @Builder
    public static class MyPageResponse{
        private long memberId;
        private String email;
        private String nickName;

        private List<MemberDto.QuestionResponse> questions;
        private List<MemberDto.AnswerResponse> answers;
    }


}
