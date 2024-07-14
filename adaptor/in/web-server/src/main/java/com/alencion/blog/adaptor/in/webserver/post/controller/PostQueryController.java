package com.alencion.blog.adaptor.in.webserver.post.controller;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostQueryController {

    //    private final PostQueryUseCase postQueryUseCase;

    //    public PostQueryController(PostQueryUseCase postQueryUseCase) {
    //        this.postQueryUseCase = postQueryUseCase;
    //    }
    //
    //    @GetMapping("/posts")
    //    public Mono<Response<List<PostModel>>> getPosts(PostQueryParams postQueryParams) {
    //        return postQueryUseCase.queryPosts(new PostQueryCommand()).map(PostModel::from).collectList().map(Response::new);
    //    }
}
