package com.alencion.blog.adaptor.out.filesystem.post;

import com.alencion.blog.adaptor.out.filesystem.FileSystemAdaptor;
import com.alencion.blog.post.Post;
import com.alencion.blog.post.application.command.PostCommandPort;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class PostCommandFileSystemAdaptor implements PostCommandPort {

    private final FileSystemAdaptor fileSystemAdaptor;

    public PostCommandFileSystemAdaptor(FileSystemAdaptor fileSystemAdaptor) {
        this.fileSystemAdaptor = fileSystemAdaptor;
    }

    @Override
    public Mono<Post> create(Post post) {
        //        Flux<String> stringFlux = fileSystemAdaptor.readFile();
        return Mono.just(post);
    }


}
