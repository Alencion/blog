package com.alencion.blog.post.application;

import com.alencion.blog.post.Post;
import com.alencion.blog.post.PostMimeType;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CreatePostService implements CreatePostUseCase {

    private final PostPort postPort;

    public CreatePostService(PostPort postPort) {
        this.postPort = postPort;
    }

    @Override
    public Mono<Post> createPostUseCase(RequestedPostCommand command) {
        Post post = Post.of(command.title(), PostMimeType.of(command.mimeType()), command.content());
        return postPort.create(post);
    }
}
