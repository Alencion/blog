package com.alencion.blog.adaptor.out.filesystem.post;

import com.alencion.blog.adaptor.out.filesystem.FileSystemPort;
import com.alencion.blog.post.Post;
import com.alencion.blog.post.PostMeta;
import com.alencion.blog.post.application.query.PostQueryPort;
import com.alencion.blog.post.application.query.ReadPostMetaCommand;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

@Slf4j
@Component
public class PostQueryFileSystemAdaptor implements PostQueryPort {

    private static final String SPLIT_PATTERN = "\n={5,}\n";
    private static final TypeReference<PostMeta> POST_META_TYPE = new TypeReference<>() {
    };
    private final String postResourcePath;
    private final FileSystemPort fileSystemPort;
    private final ObjectMapper objectMapper;

    public PostQueryFileSystemAdaptor(@Value("${alencion.blog.post.resource-path}") String postResourcePath,
                                      FileSystemPort fileSystemPort,
                                      ObjectMapper objectMapper) {
        this.postResourcePath = postResourcePath;
        this.fileSystemPort = fileSystemPort;
        this.objectMapper = objectMapper;
    }

    @Override
    public Flux<PostMeta> readPostMetas(ReadPostMetaCommand readPostMetaCommand) {

        List<Path> filePaths;
        try {
            filePaths = fileSystemPort.getFilePathsBy(postResourcePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Flux.fromIterable(filePaths).map(fileSystemPort::readFile)
                .flatMap(rawPost -> rawPost.map(this::parsePostMeta));
    }


    private Post parsePost(String rawPost) {
        PostMeta meta = parsePostMeta(rawPost);
        String content = parsePostContent(rawPost);
        return Post.of(meta.title(), meta.postMimeType(), content);
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
