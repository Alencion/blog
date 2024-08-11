package com.alencion.blog.post;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.ZonedDateTime;

public record PostMeta(
        String title,
        String author,
        PostMimeType postMimeType,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZZ") ZonedDateTime createdAt,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZZ") ZonedDateTime updatedAt
) {
}
