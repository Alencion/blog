package com.alencion.blog.adaptor.out.filesystem;

import com.alencion.blog.post.Post;
import com.alencion.blog.post.application.PostPort;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class PostFileSystemAdaptor implements PostPort {

    private final FileSystemAdaptor fileSystemAdaptor;

    public PostFileSystemAdaptor(FileSystemAdaptor fileSystemAdaptor) {
        this.fileSystemAdaptor = fileSystemAdaptor;
    }

    @Override
    public Mono<Post> create(Post post) {
        Flux<String> stringFlux = fileSystemAdaptor.readFile();


        return null;
    }
}
