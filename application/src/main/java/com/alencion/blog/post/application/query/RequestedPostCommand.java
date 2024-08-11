package com.alencion.blog.post.application.query;

import java.time.ZonedDateTime;

public record RequestedPostCommand(String title,
                                   String author,
                                   String mimeType,
                                   String content) {



}
