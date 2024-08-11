package com.alencion.blog.adaptor.out.filesystem;

import com.alencion.blog.post.Post;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface FileSystemPort {

    void writeFile(String path, String content) throws IOException;
    List<Path> getFilePathsBy(String directoryPath) throws IOException;

    Mono<String> readFile(Path filePath);
}
