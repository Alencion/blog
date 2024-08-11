package com.alencion.blog.post.application.command;

import com.alencion.blog.post.Post;
import com.alencion.blog.post.PostMeta;
import com.alencion.blog.post.PostMimeType;
import com.alencion.blog.post.application.query.RequestedPostCommand;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CreatePostService implements CreatePostUseCase {

    private final PostCommandPort postCommandPort;

    public CreatePostService(PostCommandPort postCommandPort) {
        this.postCommandPort = postCommandPort;
    }

    @Override
    public Mono<Post> createPostUseCase(RequestedPostCommand command) {
        Post post = Post.of(PostMeta.of(command), command.content());
        return postCommandPort.create(post);
    }
}
