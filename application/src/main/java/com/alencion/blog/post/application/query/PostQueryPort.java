package com.alencion.blog.post.application.query;

import com.alencion.blog.post.PostMeta;
import reactor.core.publisher.Flux;

public interface PostQueryPort {

    Flux<PostMeta> readPostMetas(ReadPostMetaCommand readPostMetaCommand);
}
