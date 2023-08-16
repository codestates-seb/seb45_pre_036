package com.seb45_pre_036.stackoverflow.answer.dto;

import com.seb45_pre_036.stackoverflow.comment.dto.CommentDto;
import com.seb45_pre_036.stackoverflow.comment.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

public class AnswerDto {

    @Getter
    public static class Post {
        @NotNull
        private long memberId;
        @NotNull
        private long questionId;
        @NotBlank
        private String content;
    }

    @Getter @Setter
    public static class Patch {

        private long answerId;
        @NotBlank
        private String content;
    }


    @Builder
    @Getter @Setter
    public static class Response { // Question 필요한 responseDto
        private long answerId;
        private String content;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
        private long memberId;
        private String email;
        private String nickName;
        private long questionId;
    }


    /*
    * with comments
    */
    @Builder
    @Getter @Setter
    public static class Responses { // Answer 필요한 responseDto
        private long answerId;
        private String content;
        private long memberId;
        private String email;
        private String nickName;
        private long questionId;
        List<AnswerDto.CommentResponse> comments;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
    }

    // answer 객체 -> List<Comment> -> List<AnswerDto.CommentResponse>

    @Getter @Setter
    @Builder
    @AllArgsConstructor
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
