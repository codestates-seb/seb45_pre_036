package com.seb45_pre_036.stackoverflow.question.tag;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TagDto {
    @Getter
    @Setter
    public static class Post{

        private long questionId;

        private String tagName;

    }
    @Getter
    @Setter
    public static class Patch{

        private long questionId;
        private long tagId;
        private String tagName;
    }

    public class Response {
        private long questionId;
        private long tagId;
        private String tagName;
    }
}
