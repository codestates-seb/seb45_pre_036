package com.seb45_pre_036.stackoverflow.question.controller;

import com.seb45_pre_036.stackoverflow.dto.MultiResponseDto;
import com.seb45_pre_036.stackoverflow.question.dto.QuestionDto;
import com.seb45_pre_036.stackoverflow.question.entity.Question;
import com.seb45_pre_036.stackoverflow.question.mapper.QuestionMapper;
import com.seb45_pre_036.stackoverflow.question.service.QuestionService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/question")
@Validated
public class QuestionController {

    private final QuestionService questionService;
    private final QuestionMapper mapper;

    private final static String QUESTION_DEFAULT_URL = "/question";

    public QuestionController(QuestionService questionService,
                              QuestionMapper mapper) {
        this.questionService = questionService;
        this.mapper = mapper;
    }


    @PostMapping("/ask")
    public ResponseEntity postQuestion(@RequestBody
                                       @Valid
                                       QuestionDto.Post questionPostDto){
        Question question = questionService.createQuestion(mapper.questionPostDtoToQuestion(questionPostDto));

        return ResponseEntity;
    }

    @PatchMapping("/{question-id}")
    public ResponseEntity patchQuestion(@PathVariable("question-id")@Positive long questionId,
                                        @Valid @RequestBody QuestionDto.Patch questionPatchDto){

        return null;
    }
    @GetMapping("/{question-id}")
    public ResponseEntity getQuestion(@PathVariable("question-id")@Positive long questionId){

        return null;
    }

    @GetMapping
    public ResponseEntity getQuestions(@RequestParam @Positive int page,
                                       @RequestParam @Positive int size){
        Page<Question> pageQuestions = questionService.findQuestions(page-1,size);
        List<Question> questions = pageQuestions.getContent();

        return new ResponseEntity(
                new MultiResponseDto<>(mapper.questionsToQuestionResponseDtos(questions),pageQuestions),HttpStatus.OK);
    }

    @DeleteMapping("/{question-id}")
    public ResponseEntity deleteQuestion(@PathVariable("question-id")@Positive long questionId){
        questionService.deleteQuestion(questionId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
