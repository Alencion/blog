package com.alencion.blog.post.application;

import com.alencion.blog.post.Post;
import reactor.core.publisher.Mono;

public interface CreatePostUseCase {
    Mono<Post> createPostUseCase(RequestedPostCommand command);
}
