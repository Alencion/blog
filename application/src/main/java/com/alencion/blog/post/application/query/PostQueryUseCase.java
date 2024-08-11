package com.alencion.blog.post.application.query;

import com.alencion.blog.post.Post;
import com.alencion.blog.post.PostMeta;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PostQueryUseCase {

    Flux<PostMeta> queryPosts(PostQueryCommand command);

    Mono<Post> getPost(String id);

}
