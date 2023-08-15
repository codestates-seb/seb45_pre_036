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

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface AnswerMapper {


    // post - memberiD / content
    // response - answerId / content / memberId / createdAt / modifiedAt

     default Answer answerPostDtoToAnswer(AnswerDto.Post answerPostDto){
        Member member = new Member();
        member.setMemberId(answerPostDto.getMemberId());

        Question question = new Question();
        question.setQuestionId(answerPostDto.getQuestionId());

        Answer answer = new Answer();

        answer.setMember(member);
        answer.setQuestion(question);

        answer.setContent(answerPostDto.getContent());

        return answer;

    }

    // answer -> AnswerDto.Response

    List<AnswerDto.Response2> answersToAnswerResponseDtos(List<Answer> answers);

    default AnswerDto.Response2 answerToAnswerResponseDto(Answer answer){

         //     public static class Response2 {
        //        private long answerId;
        //        private String content;
        //        pirvate long questionId
//                private long memberId;
        //        private String email;
        //        private String nickname;
        //        private List<Comment> comments;

        //        private LocalDateTime createdAt;
        //        private LocalDateTime modifiedAt;
        //    }

        // answer

        AnswerDto.Response2 response2 = AnswerDto.Response2.builder()
                .answerId(answer.getAnswerId())
                .content(answer.getContent())
                .questionId(answer.getQuestion().getQuestionId())
                .memberId(answer.getMember().getMemberId())
                .email(answer.getMember().getEmail())
                .nickName(answer.getMember().getNickName())
                .createdAt(answer.getCreatedAt())
                .modifiedAt(answer.getModifiedAt()).build();

        // answer -> List<Comment> comments
        // List<Comment> -> List<CommentDto.ResponseDto>

        List<Comment> comments = answer.getComments();
        // [ [comment1], [comment2], ... ]

        List<CommentDto.ResponseDto> commentResponses
                =  comments.stream()
                .map(comment -> CommentDto.ResponseDto.builder()
                        .commentId(comment.getCommentId())
                        .memberId(comment.getMember().getMemberId())
                        .answerId(comment.getAnswer().getAnswerId())
                        .email(comment.getMember().getEmail())
                        .nickName(comment.getMember().getNickName())
                        .content(comment.getContent())
                        .createdAt(comment.getCreatedAt())
                        .modifiedAt(comment.getModifiedAt()).build()
                ).collect(Collectors.toList());

        response2.setComments(commentResponses);

        return response2;




        //        private long commentId;
        //        private long memberId;
        //        private long answerId;
        //
        //        private String email;
        //        private String nickName;
        //        private String content;
        //
        //        private LocalDateTime createdAt;
        //        private LocalDateTime modifiedAt;



    }







    Answer answerPatchDtoToAnswer(AnswerDto.Patch answerPatchDto);


//     default AnswerDto.Response answerToAnswerResponseDto(Answer answer) {
//
//        // answer -> AnswerDto.Response
//        // answer ->
//        // answer 엔티티 -> member 변수 -> member 객체
//
////        AnswerDto.Response response = new AnswerDto.Response();
////
////        response.setAnswerId(answer.getAnswerId());
////        response.setContent(answer.getContent());
////        response.setMemberId(answer.getMember().getMemberId());
////        response.setCreatedAt(answer.getCreatedAt());
////        response.setModifiedAt(answer.getModifiedAt());
////
////        return response;
//
////
////
////                //    public static class Response {
////                //        private long answerId;
////                //        private String content;
////                //        private long memberId;
////                //        private LocalDateTime createdAt;
////                //        private LocalDateTime modifiedAt;
////
////
////
//        AnswerDto.Response response = AnswerDto.Response.builder()
//                                    .answerId(answer.getAnswerId())
//                                    .content(answer.getContent())
//                                    .memberId(answer.getMember().getMemberId())
//                                    .createdAt(answer.getCreatedAt())
//                                    .modifiedAt(answer.getModifiedAt()).build();
////
////        MemberDto.Response memberResponseDto = new MemberDto.Response(
////                answer.getMember().getMemberId(), answer.getMember().getEmail()
////                , answer.getMember().getNickName(), answer.getMember().getModifiedAt()
////        );
////
////        response.setMemberInfo(memberResponseDto);
//
//
//
////        AnswerDto.Response response = new AnswerDto.Response();
////        response.setAnswerId(answer.getAnswerId());
////        response.setContent(answer.getContent());
////        response.setMemberId(answer.getMember().getMemberId());
////        response.setCreatedAt(answer.getCreatedAt());
////        response.setModifiedAt(answer.getModifiedAt());
//
//        return response;
//
//    }



}
