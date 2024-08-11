package com.alencion.blog.post;

import lombok.Getter;

@Getter
public final class Post {
    public static Post of(PostMeta postMeta, String content) {
        return new Post(postMeta, content);
    }

    private final PostMeta postMeta;
    private final String content;

    private Post(PostMeta postMeta, String content) {
        this.postMeta = postMeta;
        this.content = content;
    }

}
