package com.alencion.blog.post.application;

import com.alencion.blog.file.application.FileSystemPort;
import com.alencion.blog.post.Post;
import com.alencion.blog.post.PostMeta;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

@Slf4j
@Service
public class QueryPostFileService implements PostQueryUseCase {

    private static final String SPLIT_PATTERN = "\n={5,}\n";
    private static final TypeReference<PostMeta> POST_META_TYPE = new TypeReference<>() {};
    private final String postResourcePath;
    private final FileSystemPort fileSystemPort;
    private final ObjectMapper objectMapper;


    public QueryPostFileService(@Value("${alencion.blog.post.resource-path}") String postResourcePath,
                                FileSystemPort fileSystemPort,
                                ObjectMapper objectMapper) {
        this.postResourcePath = postResourcePath;
        this.fileSystemPort = fileSystemPort;
        this.objectMapper = objectMapper;
    }

    @Override
    public Flux<Post> queryPosts(PostQueryCommand command) {
        List<Path> filePaths;
        try {
            filePaths = fileSystemPort.getFilePathsBy(postResourcePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Flux.fromIterable(filePaths).map(fileSystemPort::readFile)
                .flatMap(this::parsePost);
    }

    private Mono<Post> parsePost(Mono<String> rawPost) {
        return rawPost.flatMap(r -> {
            PostMeta meta = parsePostMeta(r);
            String content = parsePostContent(r);
            return Mono.just(Post.of(meta.title(), meta.postMimeType(), content));
        });
    }

    private PostMeta parsePostMeta(String rawPost) {
        String[] split = rawPost.split(SPLIT_PATTERN);
        if (split.length != 2) {
            throw new IllegalStateException("not fave meta data.");
        }

        try {
            return objectMapper.readValue(split[0], POST_META_TYPE);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private String parsePostContent(String rawPost) {
        String[] split = rawPost.split(SPLIT_PATTERN);
        if (split.length != 2) {
            throw new IllegalStateException("not fave meta data.");
        }

        return split[1];
    }
}
