package com.seb45_pre_036.stackoverflow.answer.dto;

import com.seb45_pre_036.stackoverflow.answer.entity.Answer;
import com.seb45_pre_036.stackoverflow.comment.dto.CommentDto;
import com.seb45_pre_036.stackoverflow.comment.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.List;

public class AnswerDto {

    @Getter
    public static class Post {
        @NotNull
        private long memberId;
        @NotNull
        private long questionId;
        @NotBlank(message = "컨텐츠는 비워둘 수 없습니다.")
        @Pattern(regexp = "^(?!\\s*$).+", message = "컨텐츠는 스페이스나 공백만 포함될 수 없습니다.")
        private String content;
    }

    @Getter @Setter
    public static class Patch {
        private long answerId;
        @NotBlank(message = "컨텐츠는 비워둘 수 없습니다.")
        @Pattern(regexp = "^(?!\\s*$).+", message = "컨텐츠는 스페이스나 공백만 포함될 수 없습니다.")
        private String content;
    }

    @Getter @Setter
    public static class PatchAdopt {
        private long answerId;
        private Answer.Adopt adopt;
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
        private Answer.Adopt adopt;
        private long memberId;
        private String email;
        private String nickName;
        private long questionId;
        List<AnswerDto.CommentResponse> comments;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
    }

    @Builder
    @Getter @Setter
    public static class AdoptResponse {
        private long answerId;
        private Answer.Adopt adopt;
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
