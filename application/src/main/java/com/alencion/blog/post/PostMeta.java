package com.alencion.blog.post;

import com.alencion.blog.post.application.query.RequestedPostCommand;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;

public record PostMeta(
        String title,
        String author,
        PostMimeType postMimeType,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZZ") ZonedDateTime createdAt,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZZ") ZonedDateTime updatedAt
) {

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZ");

    public static PostMeta of(RequestedPostCommand command) {
        return new PostMeta(command.title(), command.author(), PostMimeType.of(command.mimeType()), ZonedDateTime.now(), ZonedDateTime.now());
    }

    public String createFileName() {
        return this.title() + "-" + DATE_FORMAT.format(createdAt) + ".md";
    }
}
