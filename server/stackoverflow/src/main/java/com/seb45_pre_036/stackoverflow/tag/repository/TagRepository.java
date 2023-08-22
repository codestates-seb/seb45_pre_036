package com.seb45_pre_036.stackoverflow.tag.repository;

import com.seb45_pre_036.stackoverflow.tag.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
