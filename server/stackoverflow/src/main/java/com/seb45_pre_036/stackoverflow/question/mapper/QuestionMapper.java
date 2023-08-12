package com.seb45_pre_036.stackoverflow.question.mapper;

import com.seb45_pre_036.stackoverflow.question.dto.QuestionDto;
import com.seb45_pre_036.stackoverflow.question.entity.Question;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
    Question questionPostDtoToQuestion(QuestionDto.Post questionPostDto);
    Question quesrionPatchDtoToQuestion(QuestionDto.Patch questionPatchDto);
    QuestionDto.Response questionToQuestionResponseDto(Question question);
    List<QuestionDto.Response> questionsToQuestionResponseDtos(List<Question> questions);

}
