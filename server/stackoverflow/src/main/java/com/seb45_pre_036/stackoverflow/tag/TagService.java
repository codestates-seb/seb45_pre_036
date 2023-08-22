package com.seb45_pre_036.stackoverflow.question.tag;

import com.seb45_pre_036.stackoverflow.exception.BusinessLogicException;
import com.seb45_pre_036.stackoverflow.exception.ExceptionCode;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TagService {

    private final TagRepository tagRepository;
    private final TagMapper tagMapper;

    public TagService(TagRepository tagRepository, TagMapper tagMapper) {
        this.tagRepository = tagRepository;
        this.tagMapper = tagMapper;
    }

    public Tag findOrCreateTag(String tagName) {
        Optional<Tag> optionalTag = tagRepository.findByTagName(tagName);

        if (optionalTag.isPresent()) {
            return optionalTag.get(); // 존재하는 태그 반환
        } else {
            Tag newTag = new Tag(tagName);// 새 태그 생성
            return tagRepository.save(newTag); // 새 태그 저장 및 반환
        }
    }

    public Tag createTag(Tag tag) {
        return tagRepository.save(tag);
    }

    public Tag findTag(long tagId){
        Optional<Tag> optionalTag = tagRepository.findById(tagId);
        Tag findTag = optionalTag
                .orElseThrow(()->new BusinessLogicException(ExceptionCode.TAG_NOT_FOUND));
        return findTag;
    }
}
