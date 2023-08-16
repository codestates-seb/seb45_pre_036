package com.seb45_pre_036.stackoverflow.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class CommentDto {
    @Getter
    @Setter
    public static class PostDto {
        private long memberId;
        private long answerId;


        @NotBlank(message = "댓글 내용을 작성해주세요.")
        private String content;
    }


    @Getter @Setter
    public static class PatchDto {
        private long commentId;

        @NotBlank(message = "댓글 내용을 작성해주세요.")
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
