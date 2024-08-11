package com.alencion.blog.post.application.command;

import com.alencion.blog.post.Post;
import com.alencion.blog.post.application.query.RequestedPostCommand;
import reactor.core.publisher.Mono;

public interface CreatePostUseCase {
    Mono<Post> createPostUseCase(RequestedPostCommand command);
}
