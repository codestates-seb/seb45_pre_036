package com.seb45_pre_036.stackoverflow.member.dto;

import com.seb45_pre_036.stackoverflow.answer.entity.Answer;
import com.seb45_pre_036.stackoverflow.question.entity.Question;
import lombok.AllArgsConstructor;
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
    @AllArgsConstructor
    public static class Response{
        private long memberId;
        private String email;
        private String nickName;
        private LocalDateTime createdAt;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class PatchResponse{
        private long memberId;
        private String email;
        private String nickName;
        private LocalDateTime modifiedAt;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class MyPageResponse{
        private long memberId;
        private String email;
        private String nickName;

//        private List<QuestionDto.ResponseDto> questions;
//        private List<AnswerDto.ResponseDto> answers;
    }




}
