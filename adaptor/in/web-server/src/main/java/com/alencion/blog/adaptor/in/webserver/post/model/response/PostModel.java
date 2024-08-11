package com.alencion.blog.adaptor.in.webserver.post.model.response;

import com.alencion.blog.post.Post;

import java.time.ZonedDateTime;

public record PostModel(String title,
                        String author,
                        String mimeType,
                        String content,
                        ZonedDateTime createdAt,
                        ZonedDateTime updatedAt) {

    public static PostModel from(Post post) {
        return new PostModel(post.getTitle(), post.getMimeType().getValue(), post.getContent());
    }

}
