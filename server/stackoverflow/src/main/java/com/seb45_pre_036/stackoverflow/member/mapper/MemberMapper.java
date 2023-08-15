package com.seb45_pre_036.stackoverflow.member.mapper;

import com.seb45_pre_036.stackoverflow.answer.dto.AnswerDto;
import com.seb45_pre_036.stackoverflow.answer.entity.Answer;
import com.seb45_pre_036.stackoverflow.member.dto.MemberDto;
import com.seb45_pre_036.stackoverflow.member.entity.Member;
import com.seb45_pre_036.stackoverflow.question.dto.QuestionDto;
import com.seb45_pre_036.stackoverflow.question.entity.Question;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    Member memberPostDtoToMember(MemberDto.Post memberPostDto);

    Member memberPatchDtoToMember(MemberDto.Patch memberPatchDto);

    MemberDto.Response memberToMemberResponseDto(Member member);

    default MemberDto.MyPageResponse memberToMyPageResponseDto(Member member){

        MemberDto.MyPageResponse response = MemberDto.MyPageResponse.builder()
                                .memberId(member.getMemberId())
                                .email(member.getEmail())
                                .nickName(member.getNickName()).build();


        // List<QuestionDto.Response> questions;
        // List<Question> -> List<QuestionDto.Response>

        List<Question> getQuestions = member.getQuestions();

        List<QuestionDto.Response> questionResponseDtos
                = getQuestions.stream()
                .map(question -> QuestionDto.Response.builder()
                        .questionId(question.getQuestionId())
                                .title(question.getTitle())
                                .content(question.getContent())
                                .createdAt(question.getCreatedAt())
                                .modifiedAt(question.getModifiedAt())
                                .memberId(question.getMember().getMemberId()) // 해당 부분 필요 없을 듯
                                .email(question.getMember().getEmail())
                                .nickName(question.getMember().getNickName()) // 해당 부분 필요 없을 듯
                                .build()
                        ).collect(Collectors.toList());

        response.setQuestions(questionResponseDtos);

        // List<Answer> -> List<AnswerDto.Response>

        List<Answer> getAnswers = member.getAnswers();

        List<AnswerDto.Response> answerResponseDtos
                = getAnswers.stream()
                .map(answer -> AnswerDto.Response.builder()
                        .answerId(answer.getAnswerId())
                        .content(answer.getContent())
                        .createdAt(answer.getCreatedAt())
                        .modifiedAt(answer.getModifiedAt())
                        .memberId(answer.getMember().getMemberId()) // 해당 부분 필요 없을 듯
                        .email(answer.getMember().getEmail()) // 해당 부분 필요 없을 듯
                        .nickName(answer.getMember().getNickName()) // 해당 부분 필요 없을 듯
                        .build()
                ).collect(Collectors.toList());

        response.setAnswers(answerResponseDtos);

        return response;

    }

    List<MemberDto.MyPageResponse> membersToMemberResponseDtos(List<Member> members);

}
