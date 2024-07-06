package com.alencion.blog.file.application;

import reactor.core.publisher.Flux;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface FileSystemPort {

    List<Path> getFilePathsBy(String directoryPath) throws IOException;

    Flux<String> readFile(String filePath);
}
