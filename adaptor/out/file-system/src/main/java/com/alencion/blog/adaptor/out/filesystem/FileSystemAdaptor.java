package com.alencion.blog.adaptor.out.filesystem;


import com.alencion.blog.file.application.FileSystemPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Component
public class FileSystemAdaptor implements FileSystemPort {

    @Override
    public List<Path> getFilePathsBy(String directoryPath) throws IOException {
        try (Stream<Path> walk = Files.walk(Paths.get(directoryPath))) {
            return walk.filter(Files::isRegularFile).collect(Collectors.toList());
        } catch (IOException e) {
            log.error("[ERROR] Failed to get", e);
            throw e;
        }
    }

    @Override
    public Flux<String> readFile(String filePath) {
        return null;
    }
}