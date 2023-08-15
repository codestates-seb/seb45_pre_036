package com.seb45_pre_036.stackoverflow.question.dto;

import com.seb45_pre_036.stackoverflow.answer.dto.AnswerDto;
import com.seb45_pre_036.stackoverflow.member.dto.MemberDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
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
//    @AllArgsConstructor
    @Builder
    public static class Response{

        private long questionId;
        private String title;
        private String content;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;

        private long memberId;
        private String email;
        private String nickName;

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
    @Builder
//    @AllArgsConstructor
    public static class DetailResponse{
        private long questionId;
        private String title;
        private String content;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;

        private long memberId;
        private String email;
        private String nickName;

        private List<AnswerDto.Response> answers;

//        private List<CommentDto.ResponseDto> comments;
    }
}
