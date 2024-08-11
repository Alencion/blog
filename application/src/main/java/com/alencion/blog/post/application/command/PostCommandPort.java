package com.alencion.blog.post.application.command;

import com.alencion.blog.post.Post;
import reactor.core.publisher.Mono;

import java.io.IOException;

public interface PostCommandPort {

    Mono<Post> create(Post post);

}
