package com.alencion.blog.adaptor.out.filesystem;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Component
public class FileSystemAdaptor implements FileSystemPort {

    @Override
    public void writeFile(String path, String content) throws IOException {
        Path newFilePath = Paths.get(path);
        Path newFile = Files.createFile(newFilePath);
        Files.writeString(newFile, content, StandardCharsets.UTF_8, StandardOpenOption.WRITE);
    }

    @Override
    public List<Path> getFilePathsBy(String directoryPath) throws IOException {
        try (Stream<Path> walk = Files.walk(Paths.get(directoryPath))) {
            return walk.filter(Files::isRegularFile).collect(Collectors.toList());
        } catch (IOException e) {
            log.error("[ERROR] Failed to get file paths", e);
            throw e;
        }
    }

    @Override
    public Mono<String> readFile(Path filePath) {
        return Mono.fromCallable(() -> {
            StringBuilder builder = new StringBuilder();
            try (BufferedReader br = Files.newBufferedReader(filePath, StandardCharsets.UTF_8)) {
                String str = null;
                while ((str = br.readLine()) != null) {
                    builder.append(str).append("\n");
                }
            }
            return builder.toString();
        });
    }
}