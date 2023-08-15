package com.seb45_pre_036.stackoverflow.answer.dto;

import com.seb45_pre_036.stackoverflow.comment.dto.CommentDto;
import com.seb45_pre_036.stackoverflow.comment.entity.Comment;
import com.seb45_pre_036.stackoverflow.member.dto.MemberDto;
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
    @Setter
    public static class Post {
        @NotNull
        private long memberId;
//        @NotNull
        private long questionId;
        @NotBlank
        private String content;
//        @NotNull
//        private String adopt;
    }



    @Getter @Setter
    public static class Patch {
        @NotNull
        private long answerId;
        @NotBlank
        private String content;
    }

    @Getter
    @Setter
    @Builder
//    @Builder
    public static class Response2 {
        private long answerId;
        private String content;
        private long questionId;
        private long memberId;
        private String email;
        private String nickName;
        private List<CommentDto.ResponseDto> comments;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
    }

    @Getter
    @Setter
    @Builder
//    @Builder
    public static class Response {
        private long answerId;
        private String content;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;

        private long memberId;
        private String email;
        private String nickName;

    }
}
