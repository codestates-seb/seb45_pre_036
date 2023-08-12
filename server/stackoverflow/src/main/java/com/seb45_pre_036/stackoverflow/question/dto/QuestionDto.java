package com.seb45_pre_036.stackoverflow.question.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

public class QuestionDto {
    @Getter
    @Setter
     public static class Post{

        @NotBlank(message = "제목을 입력해주세요")
        private String title;
        @NotBlank(message = "내용을 입력해주세요")
        private String content;
    }
    @Getter
    @Setter
    public static class Patch{

        private long questionId;
        @NotBlank(message = "제목을 입력해주세요")
        private String title;
        @NotBlank(message = "내용을 입력해주세요")
        private String content;

    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Response{

        private long questionId;
        private String title;
        private String content;
        private LocalDateTime createdAt;


    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class PatchResponse{

        private long questionId;
        private String title;
        private String content;
        private LocalDateTime modifiedAt;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class DetailResponse{
        private long questionId;
        private String title;
        private String content;
        private LocalDateTime createdAt;

//        private long memberId;
//        private String email;
//        private String nickName;
//        private List<AnswerDto.ResponseDto> answers;
//        private List<CommentDto.ResponseDto> comments;
    }





}
