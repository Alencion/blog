package com.alencion.blog.post;

import com.alencion.blog.post.application.query.RequestedPostCommand;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public record PostMeta(
        String title,
        String author,
        PostMimeType postMimeType,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZZ") ZonedDateTime createdAt,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZZ") ZonedDateTime updatedAt
) {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static PostMeta of(RequestedPostCommand command) {
        return new PostMeta(command.title(), command.author(), PostMimeType.of(command.mimeType()), ZonedDateTime.now(), ZonedDateTime.now());
    }

    public String createFileName() {
        return this.title() + "-" + DATE_FORMAT.format(createdAt) + ".md";
    }
}
