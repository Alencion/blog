package com.alencion.blog.adaptor.in.webserver.post.controller;

import com.alencion.blog.adaptor.in.webserver.post.model.response.Response;
import com.alencion.blog.post.application.CreatePostUseCase;
import com.alencion.blog.post.application.RequestedPostCommand;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class PostCommandController {
    private final CreatePostUseCase createPostUseCase;

    public PostCommandController(CreatePostUseCase createPostUseCase) {
        this.createPostUseCase = createPostUseCase;
    }

    @PostMapping("/posts")
    public Mono<Response<String>> createPost(RequestedPostCommand requestedPost) {
        var requestedPostCommand = new RequestedPostCommand(requestedPost.title(), requestedPost.mimeType(), requestedPost.content());
        return createPostUseCase.createPostUseCase(requestedPostCommand).map(Response::new);
    }
}
