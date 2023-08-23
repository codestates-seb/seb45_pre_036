package com.seb45_pre_036.stackoverflow.member.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;

public class MemberDto {
    @Getter
    public static class Post{

        @NotBlank
        @Email(message = "유효한 이메일 주소를 입력해주세요.")
        private String email;

        @NotBlank
        @Size(max = 20)
        private String password;

        @NotBlank
        @Size(min = 2, max = 20, message = "문자 길이는 2에서 20 사이여야 합니다.")
        private String nickName;

    }

    @Getter
    @Setter
    public static class Patch{

        private long memberId;

        @NotNull
        @Size(min = 2, max = 20, message = "문자 길이는 2에서 20 사이여야 합니다.")
        private String nickName;

        @Size(max = 100, message = "100자 이하로 입력해 주세요.")
        private String title;

        @Size(max = 500, message = "500자 이하로 입력해 주세요.")
        private String aboutMe;

    }

    @Getter
    @Setter
    @Builder
    public static class Response{
        private long memberId;
        private String email;
        private String nickName;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
    }


    @Builder
    @Getter @Setter
    public static class AnswerResponse {
        private long answerId;
        private String content;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
        private long questionId;

    }


    @Getter
    @Setter
    @Builder
    public static class QuestionResponse{
        private long questionId;
        private String title;
        private String content;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;

    }

    @Getter
    @Setter
    @Builder
    public static class PatchInfoResponse{
        private String nickName;
        private String title;
        private String aboutMe;
    }

    @Getter
    @Setter
    @Builder
    public static class MyPageResponse{
        private long memberId;
        private String email;
        private String nickName;

        private PatchInfoResponse patchInfoResponse;

        private int questionCount; // questionCount 추가
        private int answerCount; // answerCount 추가

        private List<MemberDto.QuestionResponse> questions;
        private List<MemberDto.AnswerResponse> answers;
    }


}
