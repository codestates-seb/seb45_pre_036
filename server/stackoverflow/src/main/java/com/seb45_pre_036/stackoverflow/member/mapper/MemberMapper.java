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

        MemberDto.PatchInfoResponse patchInfoResponse = MemberDto.PatchInfoResponse.builder()
                .nickName(member.getNickName())
                .title(member.getTitle())
                .aboutMe(member.getAboutMe())
                .build();

        MemberDto.MyPageResponse response = MemberDto.MyPageResponse.builder()
                                .memberId(member.getMemberId())
                                .email(member.getEmail())
                                .nickName(member.getNickName())
                .patchInfoResponse(patchInfoResponse)
                .questionCount(member.getQuestions().size())
                .answerCount(member.getAnswers().size())
                .build();


        List<Question> getQuestions = member.getQuestions();

        List<MemberDto.QuestionResponse> questionResponseDtos
                = getQuestions.stream()
                .map(question -> MemberDto.QuestionResponse.builder()
                        .questionId(question.getQuestionId())
                                .title(question.getTitle())
                                .content(question.getContent())
                                .createdAt(question.getCreatedAt())
                                .modifiedAt(question.getModifiedAt())
                                .build()
                        ).collect(Collectors.toList());

        response.setQuestions(questionResponseDtos);


        List<Answer> getAnswers = member.getAnswers();

        List<MemberDto.AnswerResponse> answerResponseDtos
                = getAnswers.stream()
                .map(answer -> MemberDto.AnswerResponse.builder()
                        .answerId(answer.getAnswerId())
                        .content(answer.getContent())
                        .createdAt(answer.getCreatedAt())
                        .modifiedAt(answer.getModifiedAt())
                        .questionId(answer.getQuestion().getQuestionId())
                        .build()
                ).collect(Collectors.toList());

        response.setAnswers(answerResponseDtos);

        return response;

    }

    List<MemberDto.Response> membersToMemberResponseDtos(List<Member> members);

}
