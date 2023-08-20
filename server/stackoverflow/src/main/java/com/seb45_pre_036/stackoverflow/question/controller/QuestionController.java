package com.seb45_pre_036.stackoverflow.question.controller;

import com.seb45_pre_036.stackoverflow.dto.MultiResponseDto;
import com.seb45_pre_036.stackoverflow.dto.SingleResponseDto;
import com.seb45_pre_036.stackoverflow.question.dto.QuestionDto;
import com.seb45_pre_036.stackoverflow.question.entity.Question;
import com.seb45_pre_036.stackoverflow.question.mapper.QuestionMapper;
import com.seb45_pre_036.stackoverflow.question.service.QuestionService;
import com.seb45_pre_036.stackoverflow.utils.UriCreator;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/questions")
@Validated
public class QuestionController {

    private final QuestionService questionService;
    private final QuestionMapper mapper;

    private final static String QUESTION_DEFAULT_URL = "/questions";

    public QuestionController(QuestionService questionService,
                              QuestionMapper mapper) {
        this.questionService = questionService;
        this.mapper = mapper;
    }


    @PostMapping("/ask")
    public ResponseEntity postQuestion(@RequestBody @Valid QuestionDto.Post questionPostDto){

        Question question = questionService.createQuestion(mapper.questionPostDtoToQuestion(questionPostDto));


        URI location = UriCreator.createUri(QUESTION_DEFAULT_URL, question.getQuestionId());

        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/{question-id}")
    public ResponseEntity patchQuestion(@PathVariable("question-id")@Positive long questionId,
                                        @Valid @RequestBody QuestionDto.Patch questionPatchDto,
                                        @RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken){

        questionPatchDto.setQuestionId(questionId);

        Question question
                = questionService.updateQuestion(mapper.questionPatchDtoToQuestion(questionPatchDto), accessToken);

        return new ResponseEntity<>(
                new SingleResponseDto<>(
                        mapper.questionToQuestionDetailResponseDto(question)),HttpStatus.OK);

    }


    @GetMapping("/{question-id}")
    public ResponseEntity getQuestion(@PathVariable("question-id") @Positive long questionId){

        Question question = questionService.findQuestion(questionId);

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.questionToQuestionDetailResponseDto(question)), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getQuestions(@RequestParam(required = false, defaultValue = "1") int page,
                                       @RequestParam(required = false, defaultValue = "10") int size){
        Page<Question> pageQuestions = questionService.findQuestions(page-1,size);
        List<Question> questions = pageQuestions.getContent();

        return new ResponseEntity(
                new MultiResponseDto<>(mapper.questionsToQuestionResponseDtos(questions), pageQuestions), HttpStatus.OK);
    }

    @GetMapping("/view")
    public ResponseEntity getQuestionsView(@RequestParam(required = false, defaultValue = "1") int page,
                                           @RequestParam(required = false, defaultValue = "10") int size){
        Page<Question> pageQuestions = questionService.findQuestionsView(page-1,size);
        List<Question> questions = pageQuestions.getContent();

        return new ResponseEntity(
                new MultiResponseDto<>(mapper.questionsToQuestionResponseDtos(questions), pageQuestions), HttpStatus.OK);
    }

    @DeleteMapping("/{question-id}")
    public ResponseEntity deleteQuestion(@PathVariable("question-id") @Positive long questionId,
                                         @RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken){
        questionService.deleteQuestion(questionId, accessToken);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}
