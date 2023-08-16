package com.seb45_pre_036.stackoverflow.question.dto;

import com.seb45_pre_036.stackoverflow.answer.dto.AnswerDto;
import com.seb45_pre_036.stackoverflow.comment.dto.CommentDto;
import com.seb45_pre_036.stackoverflow.member.dto.MemberDto;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.List;

public class QuestionDto {
    @Getter
    @Setter
     public static class Post{

        private long memberId;

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
    @Builder
    public static class Response{

        private long questionId;
        private long memberId;
        private String email;
        private String nickName;
        private String title;
        private String content;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;


    }


    @Getter
    @Setter
    @Builder
    public static class DetailResponse{
        private long questionId;
        private String title;
        private String content;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
        private long memberId;
        private String email;
        private String nickName;
        private List<QuestionDto.AnswerResponse> answers;
    }

    @Builder
    @Getter @Setter
    public static class AnswerResponse { // Question 필요한 responseDto
        private long answerId;
        private String content;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
        private long memberId;
        private String email;
        private String nickName;
        private long questionId;
        private List<QuestionDto.CommentResponse> comments;

    }

    @Getter @Setter
    @Builder
    public static class CommentResponse {
        private long commentId;
        private String content;

        private long answerId;

        private long memberId;
        private String email;
        private String nickName;

        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
    }



}
