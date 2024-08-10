package com.alencion.blog.post;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.ZonedDateTime;

public record PostMeta(
        String title,
        String author,
        PostMimeType postMimeType,
        @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX") ZonedDateTime createdAt,
        ZonedDateTime updatedAt
) {
}
