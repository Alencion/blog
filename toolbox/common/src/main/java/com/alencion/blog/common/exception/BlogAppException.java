package com.alencion.blog.common.exception;

import lombok.Getter;

@Getter
public class BlogAppException extends RuntimeException {

    private final String errorMessage;

    public BlogAppException(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
