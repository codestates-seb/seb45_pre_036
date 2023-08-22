package com.seb45_pre_036.stackoverflow.tag.mapper;

import com.seb45_pre_036.stackoverflow.tag.dto.TagDto;
import com.seb45_pre_036.stackoverflow.tag.entity.Tag;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TagMapper {

    // postDto -> entity

    Tag tagPostDtoToTag(TagDto.Post tagPostDto);

}
