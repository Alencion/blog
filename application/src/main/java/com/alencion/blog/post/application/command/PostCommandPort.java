package com.alencion.blog.post.application.command;

import com.alencion.blog.post.Post;
import reactor.core.publisher.Mono;

public interface PostCommandPort {

    Mono<Post> create(Post post);

}
