package com.seb45_pre_036.stackoverflow.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class CommentDto {
    @Getter
    @Setter
    public static class PostDto {
        @Positive
        @NotNull
        private long memberId;

        @Positive
        @NotNull
        private long answerId;

        @NotBlank(message = "댓글 내용을 작성해주세요.")
        @Size(max = 500, message = "500자 이하로 입력해 주세요.")
        private String content;
    }


    @Getter @Setter
    public static class PatchDto {
        private long commentId;

        @NotBlank(message = "댓글 내용을 작성해주세요.")
        @Size(max = 500, message = "500자 이하로 입력해 주세요.")
        private String content;
    }


    @Getter @Setter
    @AllArgsConstructor
    @Builder
    public static class ResponseDto {
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
