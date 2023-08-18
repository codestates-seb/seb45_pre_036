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

    Answer answerPatchAdoptDtoToAnswer(AnswerDto.PatchAdopt answerPatchDto);


    /*
     * with comments
     */
    default AnswerDto.Responses answerToAnswerResponsesDto(Answer answer) {

        AnswerDto.Responses response = AnswerDto.Responses.builder()
                .answerId(answer.getAnswerId())
                .content(answer.getContent())
                .adopt(answer.getAdopt())
                .memberId(answer.getMember().getMemberId())
                .email(answer.getMember().getEmail())
                .nickName(answer.getMember().getNickName())
                .questionId(answer.getQuestion().getQuestionId())
                .createdAt(answer.getCreatedAt())
                .modifiedAt(answer.getModifiedAt())
                .build();

        // answer 엔티티 -> List<Comment> -> List<AnswerDto.CommentResponse>

        List<Comment> comments = answer.getComments();

        List<AnswerDto.CommentResponse> commentDto = comments.stream()
                .map(comment -> new AnswerDto.CommentResponse(
                        comment.getCommentId(),
                        comment.getContent(),
                        comment.getAnswer().getAnswerId(),
                        comment.getMember().getMemberId(),
                        comment.getMember().getEmail(),
                        comment.getMember().getNickName(),
                        comment.getCreatedAt(),
                        comment.getModifiedAt()))
                .collect(Collectors.toList());

        response.setComments(commentDto);

        return response;
    }

    AnswerDto.AdoptResponse answerToAnswerAdoptResponseDto(Answer answer);

    List<AnswerDto.Response> answerToAnswerResponseDtos(List<Answer> answers);

    List<AnswerDto.Responses> answersToAnswerResponseDtos(List<Answer> answers);

}
