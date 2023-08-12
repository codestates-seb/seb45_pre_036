package com.seb45_pre_036.stackoverflow.answer.dto;

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
        @NotNull
        private String adopt;
    }

    @Getter @Setter
    public static class Patch {
        @NotNull
        private long answerId;
        @NotBlank
        private String content;
    }

    @Builder
    public static class Response {
        private long answerId;
        private String content;
        private long memberId;
        private String email;
        private String nickname;
        private long questionId;
        private List<Comment> comments;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
    }
}
