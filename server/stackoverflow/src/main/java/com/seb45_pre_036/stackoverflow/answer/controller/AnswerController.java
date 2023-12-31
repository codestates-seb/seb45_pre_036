package com.seb45_pre_036.stackoverflow.answer.controller;

import com.seb45_pre_036.stackoverflow.answer.dto.AnswerDto;
import com.seb45_pre_036.stackoverflow.answer.entity.Answer;
import com.seb45_pre_036.stackoverflow.answer.mapper.AnswerMapper;
import com.seb45_pre_036.stackoverflow.answer.service.AnswerService;
import com.seb45_pre_036.stackoverflow.comment.entity.Comment;
import com.seb45_pre_036.stackoverflow.dto.MultiResponseDto;
import com.seb45_pre_036.stackoverflow.dto.SingleResponseDto;
import com.seb45_pre_036.stackoverflow.member.dto.MemberDto;
import com.seb45_pre_036.stackoverflow.member.entity.Member;
import com.seb45_pre_036.stackoverflow.question.entity.Question;
import com.seb45_pre_036.stackoverflow.utils.UriCreator;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
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

    public AnswerController(AnswerService answerService, AnswerMapper mapper) {
        this.answerService = answerService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postAnswer(@Valid @RequestBody AnswerDto.Post answerPostDto) {
        Answer answer = answerService.createAnswer(mapper.answerPostDtoToAnswer(answerPostDto));
        URI location = UriCreator.createUri(ANSWER_DEFAULT_URL, answer.getAnswerId());
        return ResponseEntity.created(location).build();

    }

    @PatchMapping("/{answer-id}")
    public ResponseEntity patchAnswer(@PathVariable("answer-id") @Positive long answerId,
                                      @Valid @RequestBody AnswerDto.Patch answerPatchDto,
                                      @RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken) {

        answerPatchDto.setAnswerId(answerId);

        Answer answer = answerService.updateAnswer(mapper.answerPatchDtoToAnswer(answerPatchDto), accessToken);

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.answerToAnswerResponsesDto(answer)),
                HttpStatus.OK);
    }

    @PatchMapping("/adopt/{answer-id}")
    public ResponseEntity patchAnswerAdopt(@PathVariable("answer-id") @Positive long answerId,
                                           @Valid @RequestBody AnswerDto.PatchAdopt answerAdoptPatchDto,
                                           @RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken) {
        answerAdoptPatchDto.setAnswerId(answerId);

        Answer getAnswer = mapper.answerPatchAdoptDtoToAnswer(answerAdoptPatchDto);

        Answer answer = answerService.updateAnswerAdopt(getAnswer, accessToken);

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.answerToAnswerAdoptResponseDto(answer)),
                HttpStatus.OK);
    }


    @DeleteMapping("/{answer-id}")
    public ResponseEntity deleteAnswer(@PathVariable("answer-id") @Positive long answerId,
                                       @RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken) {

        answerService.deleteAnswer(answerId, accessToken);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }




}
