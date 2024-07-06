package com.alencion.blog.post.application;

public record RequestedPostCommand(String title, String mimeType, String content) {

}
