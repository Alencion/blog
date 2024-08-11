package com.alencion.blog.adaptor.in.webserver.post.model.response;

import com.alencion.blog.post.PostMeta;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.ZonedDateTime;

public record PostMetaModel(String title,
                            String author,
                            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZZ", timezone = "Asia/Seoul") ZonedDateTime createdAt,
                            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZZ", timezone = "Asia/Seoul") ZonedDateTime updatedAt) {

    public static PostMetaModel from(PostMeta postMeta) {
        return new PostMetaModel(postMeta.title(), postMeta.author(), postMeta.createdAt(), postMeta.updatedAt());
    }
}
