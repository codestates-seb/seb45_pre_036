package com.seb45_pre_036.stackoverflow.question.tag;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/tags")
public class TagController {

    private final TagService tagService;
    private final TagMapper mapper;
    private final static String TAG_DEFAULT_URL = "/tags";

    public TagController(TagService tagService, TagMapper mapper) {
        this.tagService = tagService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<TagDto> postTag(@RequestBody @Valid TagDto.Post tagPostDto) {
        // TagDto.Post 객체를 Tag 엔티티로 변환
        Tag tag = mapper.tagDtoToTag(tagPostDto);

        // TagService를 사용하여 태그 생성
        Tag createdTag = tagService.createTag(tag);

        // 생성된 태그를 Response DTO로 변환
        TagDto responseDto = mapper.tagToTagDto(createdTag);

        // ResponseEntity로 응답 반환
        return ResponseEntity.ok(responseDto);
    }
}
