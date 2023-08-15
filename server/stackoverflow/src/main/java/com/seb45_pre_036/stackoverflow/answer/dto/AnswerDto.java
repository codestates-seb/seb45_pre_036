package com.seb45_pre_036.stackoverflow.answer.dto;

import com.seb45_pre_036.stackoverflow.comment.dto.CommentDto;
import com.seb45_pre_036.stackoverflow.comment.entity.Comment;
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
        @NotBlank
        private String content;
    }

    @Builder
    @Getter @Setter
    public static class Response {
        private long answerId;
        private String content;
        private long memberId;
        private String email;
        private String nickname;
        private long questionId;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
    }

    /*
    * with comments
    */
    @Builder
    @Getter @Setter
    public static class Responses {
        private long answerId;
        private String content;
        private long memberId;
        private String email;
        private String nickname;
        private long questionId;
        List<CommentDto.ResponseDto> comments;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
    }
}
