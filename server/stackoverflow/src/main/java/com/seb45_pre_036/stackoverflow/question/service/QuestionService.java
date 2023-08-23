package com.seb45_pre_036.stackoverflow.question.service;

import com.seb45_pre_036.stackoverflow.auth.jwt.JwtTokenizer;
import com.seb45_pre_036.stackoverflow.exception.BusinessLogicException;
import com.seb45_pre_036.stackoverflow.exception.ExceptionCode;
import com.seb45_pre_036.stackoverflow.member.entity.Member;
import com.seb45_pre_036.stackoverflow.member.repository.MemberRepository;
import com.seb45_pre_036.stackoverflow.member.service.MemberService;
import com.seb45_pre_036.stackoverflow.question.entity.Question;
import com.seb45_pre_036.stackoverflow.question.mapper.QuestionMapper;
import com.seb45_pre_036.stackoverflow.question.repository.QuestionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final JwtTokenizer jwtTokenizer;

    public QuestionService(QuestionRepository questionRepository, JwtTokenizer jwtTokenizer) {
        this.questionRepository = questionRepository;
        this.jwtTokenizer = jwtTokenizer;
    }

    public Question findByQuestionId(long questionId) {
        Optional<Question> optionalQuestion = questionRepository.findById(questionId);
        Question findQuestion = optionalQuestion
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));

        return findQuestion;
    }

    public void checkMemberId(long memberId, String accessToken){

        String secretKey = jwtTokenizer.getSecretKey();
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(secretKey);

        long findMemberId = jwtTokenizer.getMemberIdFromAccessToken(accessToken, base64EncodedSecretKey);

        if(memberId != findMemberId){
            throw new BusinessLogicException(ExceptionCode.MEMBER_NOT_MATCHED);
        }

    }

    public Question createQuestion(Question question){

        return questionRepository.save(question);
    }


    public Question updateQuestion(Question question, String accessToken){

        Question findQuestion = findByQuestionId(question.getQuestionId());

        checkMemberId(findQuestion.getMember().getMemberId(), accessToken);

        Optional.ofNullable(question.getTitle())
                .ifPresent(title -> findQuestion.setTitle(title));
        Optional.ofNullable(question.getContent())
                .ifPresent(content -> findQuestion.setContent(content));

        return questionRepository.save(findQuestion);
    }

    public Question findQuestion(long questionId) {

        Question findQuestion = findByQuestionId(questionId);

        findQuestion.setView(findQuestion.getView() + 1);

        return findQuestion;

//        return questionRepository.save(findQuestion);
        // return findQuestion -> repository 를 통해 해당 값을 저장하지 않더라도
        // setter 를 통해 해당 값을 저장하면 -> 테이블에 자동 등록됨.
        // @Transaction
        // 해당 영역 -> 영속성 컨텍스트 영역
        // 트랜잭션 작업 종료 후 -> commit -> db 반영

//        return findByQuestionId(questionId);
    }

    public Page<Question> findQuestions(int page, int size){

        return questionRepository.findAll(
                PageRequest.of(page,size, Sort.by("questionId").descending()));
    }

    public Page<Question> findQuestionsView(int page, int size){

        return questionRepository.findAll(
                PageRequest.of(page,size, Sort.by("view").descending()));
    }



    public void deleteQuestion(long questionId, String accessToken){
        Question question = findByQuestionId(questionId);

        checkMemberId(question.getMember().getMemberId(), accessToken);

        questionRepository.delete(question);
    }

}
