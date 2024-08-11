package com.alencion.blog.adaptor.out.filesystem.post;

import com.alencion.blog.adaptor.out.filesystem.FileSystemAdaptor;
import com.alencion.blog.post.Post;
import com.alencion.blog.post.application.command.PostCommandPort;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class PostCommandFileSystemAdaptor implements PostCommonAdaptor, PostCommandPort {

    private static final String infix = "=====";
    private final String postResourcePath;
    private final FileSystemAdaptor fileSystemAdaptor;
    private final ObjectMapper objectMapper;

    public PostCommandFileSystemAdaptor(@Value("${alencion.blog.post.resource-path}") String postResourcePath,
                                        FileSystemAdaptor fileSystemAdaptor,
                                        ObjectMapper objectMapper) {
        this.postResourcePath = postResourcePath;
        this.fileSystemAdaptor = fileSystemAdaptor;
        this.objectMapper = objectMapper;
    }

    @Override
    public Mono<Post> create(Post post) {
        String fileContent = makeFileContent(post);
        Path path = Paths.get(postResourcePath, post.getPostMeta().createFileName());
        try {
            fileSystemAdaptor.writeFile(path.toString(), fileContent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Mono.just(post);
    }

    private String makeFileContent(Post post) {
        String postMeta = null;
        try {
            postMeta = this.objectMapper.writeValueAsString(post.getPostMeta());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return String.join("\n", postMeta, infix, post.getContent());
    }


    @Override
    public ObjectMapper getObjectMapper() {
        return this.objectMapper;
    }
}
