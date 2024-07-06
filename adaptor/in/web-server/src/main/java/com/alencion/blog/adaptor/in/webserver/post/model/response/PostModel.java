package com.alencion.blog.adaptor.in.webserver.post.model.response;

import com.alencion.blog.post.Post;

public record PostModel(String title, String mimeType, String content) {

    public static PostModel from(Post post) {
        return new PostModel(post.getTitle(), post.getMimeType().getValue(), post.getContent());
    }

}
