package com.seb45_pre_036.stackoverflow.question.mapper;

import com.seb45_pre_036.stackoverflow.answer.dto.AnswerDto;
import com.seb45_pre_036.stackoverflow.answer.entity.Answer;
import com.seb45_pre_036.stackoverflow.comment.dto.CommentDto;
import com.seb45_pre_036.stackoverflow.comment.entity.Comment;
import com.seb45_pre_036.stackoverflow.member.dto.MemberDto;
import com.seb45_pre_036.stackoverflow.member.entity.Member;
import com.seb45_pre_036.stackoverflow.question.dto.QuestionDto;
import com.seb45_pre_036.stackoverflow.question.entity.Question;
import org.mapstruct.Mapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
    default Question questionPostDtoToQuestion(QuestionDto.Post questionPostDto){

        Member member = new Member();
        member.setMemberId(questionPostDto.getMemberId());

        Question question = new Question();
        question.setTitle(questionPostDto.getTitle());

        question.setContent(questionPostDto.getContent());
        question.setMember(member);

        return question;
    }

    Question questionPatchDtoToQuestion(QuestionDto.Patch questionPatchDto);

    default QuestionDto.Response questionToQuestionResponseDto(Question question){

        QuestionDto.Response response = QuestionDto.Response.builder()
                .questionId(question.getQuestionId())
                .memberId(question.getMember().getMemberId())
                .email(question.getMember().getEmail())
                .nickName(question.getMember().getNickName())
                .title(question.getTitle())
                .content(question.getContent())
                .createdAt(question.getCreatedAt())
                .modifiedAt(question.getModifiedAt())
                .view(question.getView()) // view 추가
                .answerCount(question.getAnswers().size()) // answerCount 추가
                .build();

        return response;

    }

    List<QuestionDto.Response> questionsToQuestionResponseDtos(List<Question> questions);

    default QuestionDto.DetailResponse questionToQuestionDetailResponseDto(Question question){

        QuestionDto.DetailResponse detailResponse = QuestionDto.DetailResponse.builder()
                .questionId(question.getQuestionId())
                .memberId(question.getMember().getMemberId())
                .email(question.getMember().getEmail())
                .nickName(question.getMember().getNickName())
                .title(question.getTitle())
                .content(question.getContent())
                .createdAt(question.getCreatedAt())
                .modifiedAt(question.getModifiedAt())
                .view(question.getView()) // view 추가
                .build();


        List<Answer> answers = question.getAnswers();

        List<QuestionDto.AnswerResponse> answerDtoResponses =
        answers.stream().map(answer -> QuestionDto.AnswerResponse.builder()
                .answerId(answer.getAnswerId())
                .content(answer.getContent())
                .createdAt(answer.getCreatedAt())
                .modifiedAt(answer.getModifiedAt())
                .memberId(answer.getMember().getMemberId())
                .email(answer.getMember().getEmail())
                .nickName(answer.getMember().getNickName())
                .questionId(answer.getQuestion().getQuestionId())
                .adopt(answer.getAdopt()) // 추가
                .comments(answer.getComments().stream()
                        .map(comment -> QuestionDto.CommentResponse.builder()
                                .commentId(comment.getCommentId())
                                .content(comment.getContent())
                                .answerId(comment.getAnswer().getAnswerId())
                                .memberId(comment.getMember().getMemberId())
                                .email(comment.getMember().getEmail())
                                .nickName(comment.getMember().getNickName())
                                .createdAt(comment.getCreatedAt())
                                .modifiedAt(comment.getModifiedAt())
                                .build())
                        .collect(Collectors.toList()))
                .build()
        ).collect(Collectors.toList());

        detailResponse.setAnswers(answerDtoResponses);


        return detailResponse;

    }


}
