package com.seb45_pre_036.stackoverflow.answer.controller;

import com.seb45_pre_036.stackoverflow.answer.dto.AnswerDto;
import com.seb45_pre_036.stackoverflow.answer.entity.Answer;
import com.seb45_pre_036.stackoverflow.answer.mapper.AnswerMapper;
import com.seb45_pre_036.stackoverflow.answer.repository.AnswerRepository;
import com.seb45_pre_036.stackoverflow.answer.service.AnswerService;
import com.seb45_pre_036.stackoverflow.comment.entity.Comment;
import com.seb45_pre_036.stackoverflow.dto.MultiResponseDto;
import com.seb45_pre_036.stackoverflow.dto.SingleResponseDto;
import com.seb45_pre_036.stackoverflow.member.dto.MemberDto;
import com.seb45_pre_036.stackoverflow.member.entity.Member;
import com.seb45_pre_036.stackoverflow.question.entity.Question;
import com.seb45_pre_036.stackoverflow.utils.UriCreator;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/answers")
@Validated
public class AnswerController {

    private final static String ANSWER_DEFAULT_URL = "/answers";
    private final AnswerService answerService;
    private final AnswerMapper mapper;
    private final AnswerRepository answerRepository;

    public AnswerController(AnswerService answerService, AnswerMapper mapper, AnswerRepository answerRepository) {
        this.answerService = answerService;
        this.answerRepository = answerRepository;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postCoffee(@Valid @RequestBody AnswerDto.Post answerPostDto) {
        Answer answer = answerService.createAnswer(mapper.answerPostDtoToAnswer(answerPostDto));
        URI location = UriCreator.createUri(ANSWER_DEFAULT_URL, answer.getAnswerId());
        return ResponseEntity.created(location).build();

        /*Answer answer = answerService.createAnswer(mapper.answerPostDtoToAnswer(answerPostDto));
        return new ResponseEntity(
                new SingleResponseDto<>(mapper.answersToAnswerResponseDto(answer)),
                HttpStatus.CREATED
        );*/
    }

    @GetMapping("/{answer-id}")
    public ResponseEntity getAnswer(@PathVariable("answer-id") @Positive long answerId) {
        Answer answer = answerService.findAnswer(answerId);

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.answersToAnswerResponseDto(answer)),
                HttpStatus.OK);
    }

    @GetMapping("/{question-id}")
    public ResponseEntity getAnswers(@RequestParam @Positive int page,
                                     @RequestParam @Positive int size,
                                     @PathVariable("question-id") @Positive long questionId) {
        Page<Answer> pageAnswers = answerService.findAnswersByQuestionId(page - 1, size, questionId);
        List<Answer> answers = pageAnswers.getContent();

        return new ResponseEntity<>(
                new MultiResponseDto<>(mapper.answersToAnswerResponseDtos(answers),
                        pageAnswers),
                HttpStatus.OK);
    }

    @PatchMapping("/{answer-id}")
    public ResponseEntity patchAnswer(@PathVariable("answer-id") @Positive long answerId,
                                      @Valid @RequestBody AnswerDto.Patch answerPatchDto) {
        Answer answer = answerService.updateAnswer(mapper.answerPatchDtoToAnswer(answerPatchDto));

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.answersToAnswerResponseDto(answer)),
                HttpStatus.OK);
    }

    @DeleteMapping("/{answer-id}")
    public ResponseEntity deleteAnswer(@RequestBody @Valid AnswerDto.Patch answerPatchDto, @PathVariable("answer-id") @Positive long answerId) {
        answerService.deleteAnswer(answerId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
