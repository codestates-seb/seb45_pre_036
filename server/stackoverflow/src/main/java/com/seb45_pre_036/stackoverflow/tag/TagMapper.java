package com.seb45_pre_036.stackoverflow.question.tag;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface TagMapper {

    TagDto tagToTagDto(Tag tag);

    Tag tagDtoToTag(TagDto.Post tagPostDto);
}