package com.alencion.blog.adaptor.in.webserver.post.controller;

import com.alencion.blog.adaptor.in.webserver.post.model.request.PostQueryParams;
import com.alencion.blog.adaptor.in.webserver.post.model.response.PostModel;
import com.alencion.blog.adaptor.in.webserver.post.model.response.Response;
import com.alencion.blog.post.application.PostQueryCommand;
import com.alencion.blog.post.application.PostQueryUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class PostQueryController {

    private final PostQueryUseCase postQueryUseCase;

    public PostQueryController(PostQueryUseCase postQueryUseCase) {
        this.postQueryUseCase = postQueryUseCase;
    }

    @GetMapping("/posts")
    public Mono<Response<List<PostModel>>> getPosts(PostQueryParams postQueryParams) {
        return postQueryUseCase.queryPosts(new PostQueryCommand()).map(PostModel::from).collectList().map(Response::new);
    }
}
