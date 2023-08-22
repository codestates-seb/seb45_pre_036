package com.seb45_pre_036.stackoverflow.question.dto;

import com.seb45_pre_036.stackoverflow.answer.dto.AnswerDto;
import com.seb45_pre_036.stackoverflow.answer.entity.Answer;
import com.seb45_pre_036.stackoverflow.comment.dto.CommentDto;
import com.seb45_pre_036.stackoverflow.member.dto.MemberDto;
import com.seb45_pre_036.stackoverflow.question.entity.QuestionTag;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;

public class QuestionDto {
    @Getter
    @Setter
     public static class Post{

        @NotNull(message = "회원 ID를 입력해주세요")
        @Digits(integer = Integer.MAX_VALUE, fraction = 0, message = "유효한 회원 ID를 입력해주세요")
        private long memberId;

        @NotBlank(message = "제목을 입력해주세요")
        @Size(min = 1, max = 100, message = "제목은 1자 이상 100자 이하로 입력해주세요")
        private String title;

        @NotBlank(message = "내용을 입력해주세요")
        @Size(min = 1, max = 2000, message = "내용은 1자 이상 2000자 이하로 입력해주세요")
        private String content;

        private List<QuestionTagDto> questionTagDtos;
        // List<QuestionTagDto> -> List<QuestionTag>
        // postDto -> question entity

    }


    @Getter
    @Setter
    public static class Patch{

        private long questionId;

        @NotBlank(message = "제목을 입력해주세요")
        @Size(min = 1, max = 100, message = "제목은 1자 이상 100자 이하로 입력해주세요")
        private String title;

        @NotBlank(message = "내용을 입력해주세요")
        @Size(min = 1, max = 2000, message = "내용은 1자 이상 2000자 이하로 입력해주세요")
        private String content;

    }

    @Getter
    @Setter
    @Builder
    public static class Response{

        private long questionId;
        private int view;
        private long memberId;
        private String email;
        private String nickName;
        private String title;
        private String content;
        private int answerCount; // answerCount 추가
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;


    }

    @Getter
    @Setter
    @Builder
    public static class QuestionTagResponse{
        private long tagId;
        private String tagName;
    }


    @Getter
    @Setter
    @Builder
    public static class DetailResponse{
        private long questionId;
        private String title;
        private String content;
        private int view;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
        private long memberId;
        private String email;
        private String nickName;
        private List<QuestionDto.AnswerResponse> answers;
        private List<QuestionDto.QuestionTagResponse> questionTagResponses;
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
        private Answer.Adopt adopt;
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
