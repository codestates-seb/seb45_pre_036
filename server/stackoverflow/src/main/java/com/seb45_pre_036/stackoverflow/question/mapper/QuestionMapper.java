package com.seb45_pre_036.stackoverflow.question.mapper;

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
public interface QuestionMapper {
    default Question questionPostDtoToQuestion(QuestionDto.Post questionPostDto) {

        // questionPostDto -> question

        // questionPostDto -> Question
        //       private long memberId;
        //
        //        @NotBlank(message = "제목을 입력해주세요")
        //        private String title;
        //        @NotBlank(message = "내용을 입력해주세요")
        //        private String content;
        //
        // 어떤 멤버가 질문을 작성했는지

        //     @Id
        //    @GeneratedValue(strategy = GenerationType.IDENTITY)
        //    private long questionId;
        //    private String title;
        //    private String content;
        //    private LocalDateTime createdAt;
        //    private LocalDateTime modifiedAt;
        //
        //    @ManyToOne
        //    @JoinColumn(name = "MEMBER_ID")
        //    private Member member = new Member();

        Member member = new Member();
        member.setMemberId(questionPostDto.getMemberId());

        Question question = new Question();

        question.setTitle(questionPostDto.getTitle());
        question.setContent(questionPostDto.getContent());
        question.setMember(member);

        return question;

    }

    Question quesrionPatchDtoToQuestion(QuestionDto.Patch questionPatchDto);

    default QuestionDto.DetailResponse questionToQuestionResponseDto(Question question) {

    //        // response -> member 정보 넣어서 보내준다고하면.
//
//        //       private long memberId;
//        //        private String email;
//        //        private String nickName;
//        //        private LocalDateTime createdAt;
//
//        //         private long questionId;
//        //        private long memberId;
//        //        private String title;
//        //        private String content;
//        //        private LocalDateTime createdAt;
//
//        ////        private MemberDto.Response memberInfo;
//
//        // question -> QuestionDto.Response
//
//        //         private List<AnswerDto.Response> answers;

        //   private long questionId;
        //        private String title;
        //        private String content;
        //        private LocalDateTime createdAt;
        //        private LocalDateTime modifiedAt;
        //
        //        private long memberId;
        //        private String email;
        //        private String nickName;
        //
        //        private List<AnswerDto.Response> answers;
//
    QuestionDto.DetailResponse response = QuestionDto.DetailResponse.builder()
            .questionId(question.getQuestionId())
            .title(question.getTitle())
            .content(question.getContent())
            .createdAt(question.getCreatedAt())
            .modifiedAt(question.getModifiedAt())
            .memberId(question.getMember().getMemberId())
            .email(question.getMember().getEmail())
            .nickName(question.getMember().getNickName())
            .build();

    //
    List<Answer> answers = question.getAnswers();
    List<AnswerDto.Response> answerDtoResponses
            = answers.stream().map(answer -> AnswerDto.Response.builder()
            .answerId(answer.getAnswerId())
            .content(answer.getContent())
            .memberId(answer.getMember().getMemberId())
            .createdAt(answer.getCreatedAt())
            .modifiedAt(answer.getModifiedAt())
            .build()
    ).collect(Collectors.toList());

        response.setAnswers(answerDtoResponses); //setAnswers(answerDtoResponses);

        return response;
}







        // question -> member 객체 -> member 객체 정보 -> memberDto.response

//        MemberDto.Response memberResponseDto = new MemberDto.Response(
//                question.getMember().getMemberId(),
//                question.getMember().getEmail(),
//                question.getMember().getNickName(),
//                question.getMember().getCreatedAt()
//        );
//
//        response.setMemberInfo(memberResponseDto);

//        return response;


        // private List<AnswerDto.Response> answers;

        // question -> List<Answer> answers -> [[answer1], [answer2], ... ]
        // List<Answer> -> List<AnswerDto.Response>





                //         private long answerId;
                //        private String content;
                //        private long memberId;
                //        private LocalDateTime createdAt;
                //        private LocalDateTime modifiedAt;










    List<QuestionDto.Response> questionsToQuestionResponseDtos(List<Question> questions);
}
