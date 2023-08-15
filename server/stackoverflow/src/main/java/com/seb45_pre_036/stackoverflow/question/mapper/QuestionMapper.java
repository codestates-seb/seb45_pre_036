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
                .title(question.getTitle())
                .content(question.getContent())
                .createdAt(question.getCreatedAt()).build();


        MemberDto.Response memberResponseDto = new MemberDto.Response(
                question.getMember().getMemberId(),
                question.getMember().getEmail(),
                question.getMember().getNickName(),
                question.getMember().getCreatedAt(),
                question.getMember().getModifiedAt()
        );


        response.setMemberInfo(memberResponseDto);

        return response;

    }
    default QuestionDto.DetailResponse questionToQuestionDetailResponseDto(Question question){
        QuestionDto.DetailResponse detailResponse = QuestionDto.DetailResponse.builder()
                .questionId(question.getQuestionId())
                .memberId(question.getMember().getMemberId())
                .title(question.getTitle())
                .content(question.getContent())
                .createdAt(question.getCreatedAt())
                .modifiedAt(question.getModifiedAt()).build();


        MemberDto.Response memberResponseDto = new MemberDto.Response(
                question.getMember().getMemberId(),
                question.getMember().getEmail(),
                question.getMember().getNickName(),
                question.getMember().getCreatedAt(),
                question.getMember().getModifiedAt()
        );

//        answer,coment List를 담아야함
//        private List<AnswerDto.Response> answers;

        detailResponse.setMemberInfo(memberResponseDto);


        List<Answer> answers = question.getAnswers();

        List<AnswerDto.Response> answerDtoResponses =
        answers.stream().map(answer -> AnswerDto.Response.builder()
                .answerId(answer.getAnswerId())
                .content(answer.getContent())
                .memberId(answer.getMember().getMemberId())
                //email?nickName 가져와야 할까?
                .createdAt(answer.getCreatedAt())
                .modifiedAt(answer.getModifiedAt())
                .build()
        ).collect(Collectors.toList());

        detailResponse.setAnswers(answerDtoResponses);

        List<Comment> comments = question.getComments();

        List<CommentDto.ResponseDto> commentDtoResponses=
        comments.stream().map(comment -> CommentDto.ResponseDto.builder()
                        .commentId(comment.getCommentId())
                        .content(comment.getContent())
                        .memberId(comment.getMember().getMemberId())
                        //email?nickName 가져와야 할까?
                        .answerId(comment.getAnswer().getAnswerId())
                        .createdAt(comment.getCreatedAt())
                        .modifiedAt(comment.getModifiedAt())
                        .build()).collect(Collectors.toList());

        detailResponse.setComments(commentDtoResponses);

        return detailResponse;
    }
    List<QuestionDto.Response> questionsToQuestionResponseDtos(List<Question> questions);

}
