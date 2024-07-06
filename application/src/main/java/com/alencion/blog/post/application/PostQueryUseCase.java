package com.alencion.blog.post.application;

import com.alencion.blog.post.Post;
import reactor.core.publisher.Flux;

public interface PostQueryUseCase {

    Flux<Post> queryPosts(PostQueryCommand command);
}
