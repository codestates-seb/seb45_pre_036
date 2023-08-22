package com.seb45_pre_036.stackoverflow.tag.controller;

import com.seb45_pre_036.stackoverflow.tag.dto.TagDto;
import com.seb45_pre_036.stackoverflow.tag.entity.Tag;
import com.seb45_pre_036.stackoverflow.tag.mapper.TagMapper;
import com.seb45_pre_036.stackoverflow.tag.sevice.TagService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tags")
public class TagController {


    private final TagService tagService;
    private final TagMapper mapper;

    public TagController(TagService tagService, TagMapper mapper) {
        this.tagService = tagService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postTag(@RequestBody TagDto.Post tagPostDto){

        Tag tag = tagService.createTag(mapper.tagPostDtoToTag(tagPostDto));

        return new ResponseEntity(HttpStatus.OK);
    }

}
