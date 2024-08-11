package com.alencion.blog.adaptor.in.webserver.post.controller;

import com.alencion.blog.adaptor.in.webserver.post.model.request.PostQueryParams;
import com.alencion.blog.adaptor.in.webserver.post.model.response.PostMetaModel;
import com.alencion.blog.adaptor.in.webserver.post.model.response.Response;
import com.alencion.blog.post.application.query.PostQueryCommand;
import com.alencion.blog.post.application.query.PostQueryUseCase;
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
    public Mono<Response<List<PostMetaModel>>> getPosts(PostQueryParams postQueryParams) {
        return postQueryUseCase.queryPosts(new PostQueryCommand()).map(PostMetaModel::from).collectList().map(Response::new);
    }
}
