package com.alencion.blog.adaptor.out.filesystem;

import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface FileSystemPort {

    List<Path> getFilePathsBy(String directoryPath) throws IOException;

    Mono<String> readFile(Path filePath);
}
