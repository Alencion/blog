package com.alencion.blog.post;

import lombok.Getter;

@Getter
public final class Post {
    public static Post of(String title, PostMimeType postMimeType, String content) {
        return new Post(title, postMimeType, content);
    }

    private final String title;
    private final PostMimeType mimeType;
    private final String content;

    private Post(String title, PostMimeType mimeType, String content) {
        this.title = title;
        this.mimeType = mimeType;
        this.content = content;
    }

}
