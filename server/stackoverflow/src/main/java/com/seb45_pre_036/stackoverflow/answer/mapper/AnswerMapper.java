package com.seb45_pre_036.stackoverflow.answer.mapper;

import com.seb45_pre_036.stackoverflow.answer.dto.AnswerDto;
import com.seb45_pre_036.stackoverflow.answer.entity.Answer;
import com.seb45_pre_036.stackoverflow.comment.dto.CommentDto;
import com.seb45_pre_036.stackoverflow.comment.entity.Comment;
import com.seb45_pre_036.stackoverflow.member.dto.MemberDto;
import com.seb45_pre_036.stackoverflow.member.entity.Member;
import com.seb45_pre_036.stackoverflow.question.entity.Question;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface AnswerMapper {

    default Answer answerPostDtoToAnswer(AnswerDto.Post answerPostDto) {
        Answer answer = new Answer();
        Member member = new Member();
        Question question = new Question();

        member.setMemberId(answerPostDto.getMemberId());
        question.setQuestionId(answerPostDto.getQuestionId());

        answer.setMember(member);
        answer.setQuestion(question);
        answer.setContent(answerPostDto.getContent());

        return answer;
    }
    Answer answerPatchDtoToAnswer(AnswerDto.Patch answerPatchDto);
    default AnswerDto.Response answerToAnswerResponseDto(Answer answer) {
        AnswerDto.Response response = AnswerDto.Response.builder()
                                        .answerId(answer.getAnswerId())
                                        .content(answer.getContent())
                                        .memberId(answer.getMember().getMemberId())
                                        .email(answer.getMember().getEmail())
                                        .nickname(answer.getMember().getNickName())
                                        .questionId(answer.getQuestion().getQuestionId())
                                        .createdAt(answer.getCreatedAt())
                                        .modifiedAt(answer.getModifiedAt())
                                        .build();
        return response;
    }
    default List<AnswerDto.Response> answerToAnswerResponseDtos(List<Answer> answers) {
        return answers.stream()
                .map(answer -> answerToAnswerResponseDto(answer))
                .collect(Collectors.toList());
    }

    /*
    * with comments
    */
    default AnswerDto.Responses answersToAnswerResponseDto(Answer answer) {
        AnswerDto.Responses response = AnswerDto.Responses.builder()
                .answerId(answer.getAnswerId())
                .content(answer.getContent())
                .memberId(answer.getMember().getMemberId())
                .email(answer.getMember().getEmail())
                .nickname(answer.getMember().getNickName())
                .questionId(answer.getQuestion().getQuestionId())
                .createdAt(answer.getCreatedAt())
                .modifiedAt(answer.getModifiedAt())
                .build();

        List<Comment> comments = answer.getComments();

        List<CommentDto.ResponseDto> commentDto = comments.stream()
                .map(comment -> new CommentDto.ResponseDto(comment.getCommentId(),
                        comment.getMember().getMemberId(),
                        comment.getAnswer().getAnswerId(),
                        comment.getMember().getEmail(),
                        comment.getMember().getEmail(),
                        comment.getAnswer().getContent(),
                        comment.getCreatedAt(),
                        comment.getModifiedAt()))
                .collect(Collectors.toList());

        response.setComments(commentDto);

        return response;
    }

    default List<AnswerDto.Responses> answersToAnswerResponseDtos(List<Answer> answers) {
        return answers.stream()
                .map(answer -> answersToAnswerResponseDto(answer))
                .collect(Collectors.toList());
    }
}
