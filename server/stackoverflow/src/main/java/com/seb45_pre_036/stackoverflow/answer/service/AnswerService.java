package com.seb45_pre_036.stackoverflow.answer.service;

import com.seb45_pre_036.stackoverflow.answer.dto.AnswerDto;
import com.seb45_pre_036.stackoverflow.answer.entity.Answer;
import com.seb45_pre_036.stackoverflow.answer.repository.AnswerRepository;
import com.seb45_pre_036.stackoverflow.auth.jwt.JwtTokenizer;
import com.seb45_pre_036.stackoverflow.exception.BusinessLogicException;
import com.seb45_pre_036.stackoverflow.exception.ExceptionCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final JwtTokenizer jwtTokenizer;

    public AnswerService(AnswerRepository answerRepository, JwtTokenizer jwtTokenizer) {
        this.answerRepository = answerRepository;
        this.jwtTokenizer = jwtTokenizer;
    }

    public Answer findVerifiedAnswer(long answerId) {
        Optional<Answer> optionalAnswer = answerRepository.findById(answerId);
        Answer findAnswer = optionalAnswer.orElseThrow(() -> new BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND));
        return findAnswer;
    }

    public void checkMemberId(long memberId, String accessToken) {

        String secretKey = jwtTokenizer.getSecretKey();
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(secretKey);

        long findMemberId = jwtTokenizer.getMemberIdFromAccessToken(accessToken, base64EncodedSecretKey);

        if (memberId != findMemberId) {
            throw new BusinessLogicException(ExceptionCode.MEMBER_NOT_MATCHED);
        }
    }

    public Answer createAnswer(Answer answer) {
        return answerRepository.save(answer);
    }

    public Answer updateAnswer(Answer answer, String accessToken) {

        Answer findAnswer = findVerifiedAnswer(answer.getAnswerId());

        checkMemberId(findAnswer.getMember().getMemberId(), accessToken);

        Optional.ofNullable(answer.getContent())
                .ifPresent(content -> findAnswer.setContent(content));

        return answerRepository.save(findAnswer);
    }

    public void deleteAnswer(Long answerId, String accessToken) {
        Answer answer = findVerifiedAnswer(answerId);

        checkMemberId(answer.getMember().getMemberId(), accessToken);

        answerRepository.delete(answer);
    }


}
