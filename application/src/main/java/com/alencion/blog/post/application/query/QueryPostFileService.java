package com.alencion.blog.post.application.query;

import com.alencion.blog.post.Post;
import com.alencion.blog.post.PostMeta;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class QueryPostFileService implements PostQueryUseCase {


    private final PostQueryPort postQueryPort;


    public QueryPostFileService(PostQueryPort postQueryPort) {
        this.postQueryPort = postQueryPort;
    }

    @Override
    public Flux<PostMeta> queryPosts(PostQueryCommand command) {
        return postQueryPort.readPostMetas(new ReadPostMetaCommand());
    }

    @Override
    public Mono<Post> getPost(String id) {
        return null;
    }

}
