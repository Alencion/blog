package com.alencion.blog.adaptor.out.filesystem.post;

import com.alencion.blog.adaptor.out.filesystem.FileSystemPort;
import com.alencion.blog.post.PostMeta;
import com.alencion.blog.post.application.query.PostQueryPort;
import com.alencion.blog.post.application.query.ReadPostMetaCommand;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

@Slf4j
@Component
public class PostQueryFileSystemAdaptor implements PostQueryPort, PostCommonAdaptor {

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


    @Override
    public ObjectMapper getObjectMapper() {
        return this.objectMapper;
    }
}
