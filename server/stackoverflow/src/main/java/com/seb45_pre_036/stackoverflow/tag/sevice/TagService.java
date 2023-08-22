package com.seb45_pre_036.stackoverflow.tag.sevice;

import com.seb45_pre_036.stackoverflow.tag.entity.Tag;
import com.seb45_pre_036.stackoverflow.tag.repository.TagRepository;
import org.springframework.stereotype.Service;

@Service
public class TagService {

    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public Tag createTag(Tag tag){
        return tagRepository.save(tag);
    }

}
